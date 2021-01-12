package com.fante.project.business.smsCoupon.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.fante.common.business.enums.SmsCouponConst;
import com.fante.common.utils.Checker;
import com.fante.common.utils.JsonUtils;
import com.fante.common.utils.poi.ExcelUtil;
import com.fante.framework.aspectj.lang.annotation.Excel;
import com.fante.framework.aspectj.lang.annotation.Log;
import com.fante.framework.aspectj.lang.enums.BusinessType;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.framework.web.page.TableDataInfo;
import com.fante.project.api.appView.domain.MemberDataRsp;
import com.fante.project.api.appView.domain.SmsMemberCouponDetail;
import com.fante.project.business.bizShopInfo.domain.BizShopInfo;
import com.fante.project.business.bizShopInfo.service.IBizShopInfoService;
import com.fante.project.business.smsCoupon.domain.*;
import com.fante.project.business.smsCoupon.service.ISmsCouponService;
import com.fante.project.business.smsCouponHistory.domain.SmsCouponHistory;
import com.fante.project.business.smsCouponHistory.domain.SmsCouponHistoryDTO;
import com.fante.project.business.smsCouponHistory.service.ISmsCouponHistoryService;
import com.fante.project.business.smsCouponProductCateRelation.domain.SmsCouponProductCateRelationDTO;
import com.fante.project.business.smsCouponProductCateRelation.service.ISmsCouponProductCateRelationService;
import com.fante.project.business.smsCouponProductRelation.domain.SmsCouponProductRelationDTO;
import com.fante.project.business.smsCouponProductRelation.service.ISmsCouponProductRelationService;
import com.fante.project.business.smsSetting.utils.SmsSettingUtils;
import com.fante.project.business.umsMember.domain.UmsMember;
import com.fante.project.business.umsMember.service.IUmsMemberService;
import com.fante.project.system.user.domain.User;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.sun.javafx.collections.MappingChange;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 优惠券Controller
 *
 * @author fante
 * @date 2020-03-19
 */
@Api(tags = "SmsCouponController", description = "优惠券")
@Controller
@RequestMapping("/business/smsCoupon")
public class SmsCouponController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(SmsCouponController.class);

    private String prefix = "business/smsCoupon";

    /**
     * 获取市行公众号上存款分组标识
     */
    private final String url = "http://www.henangaiyin.com/sunshinecredit/RealNameCertification/getAllbiaoshi";

    /**
     * 获取市行公众号上存款分组标识、姓名、身份证号
     */
    private final String urlforidcard = "http://www.henangaiyin.com/sunshinecredit/RealNameCertification/getAllbiaoshiForNameAndIDcard";

    @Autowired
    private ISmsCouponService iSmsCouponService;
    @Autowired
    private IBizShopInfoService iBizShopInfoService;
    @Autowired
    private ISmsCouponProductCateRelationService smsCouponProductCateRelationService;
    @Autowired
    private ISmsCouponProductRelationService smsCouponProductRelationService;
    @Autowired
    private ISmsCouponHistoryService smsCouponHistoryService;
    @Autowired
    private IUmsMemberService iUmsMemberService;
    @Autowired
    private SmsSettingUtils smsSettingUtils;
    @Autowired
    RestTemplate restTemplate;

    @RequiresPermissions("business:smsCoupon:view")
    @GetMapping()
    public String smsCoupon(ModelMap modelMap) {
        Checker.check(smsSettingUtils.couponEnable(),"暂未启用优惠券功能");
        modelMap.put("isAdmin", getSysUser().isAdmin() || getSysUser().isSystem());
        return prefix + "/smsCoupon";
    }

    @ApiOperation("条件查询优惠券列表")
    @RequiresPermissions("business:smsCoupon:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SmsCoupon smsCoupon) {
        User user = getSysUser();
        if (user.isFranchisee()){
            smsCoupon.setShopId(user.getDeptId());
        }
        startPage();
        List<SmsCouponListDTO> list = iSmsCouponService.selectSmsCouponListDTOList(smsCoupon);
        if (!ObjectUtils.isEmpty(list)) {
            list.forEach(coupon -> coupon.setBtns(SmsCouponConst.operateBtnEnum.getBtns(coupon.getStatus())));
        }
        return getDataTable(list);
    }

    @ApiOperation("导出优惠券列表")
    @RequiresPermissions("business:smsCoupon:export")
    @Log(title = "优惠券", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SmsCoupon smsCoupon) {
        User user = getSysUser();
        if (user.isFranchisee()){
            smsCoupon.setShopId(user.getDeptId());
        }
        List<SmsMemberCouponDetail> list = iSmsCouponService.selectSmsCouponList(smsCoupon);
        ExcelUtil<SmsMemberCouponDetail> util = new ExcelUtil<SmsMemberCouponDetail>(SmsMemberCouponDetail. class);
        return util.exportExcel(list, "smsCoupon");
    }

    @ApiOperation("[跳转] 到新增优惠券页面")
    @GetMapping("/add")
    public String add() {
        Checker.check(smsSettingUtils.couponEnable(),"暂未启用优惠券功能");
        User user = getSysUser();
        Checker.checkOp(user.isSystem(), "登录用户非平台用户");
        return prefix + "/add";
    }

    @ApiOperation("新增保存优惠券")
    @RequiresPermissions("business:smsCoupon:add")
    @Log(title = "优惠券", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SmsCoupon smsCoupon, String selectIds) {
        User user = getSysUser();
        Checker.checkOp(user.isSystem(), "登录用户非平台用户");
        //smsCoupon.setShopId(user.getDeptId());
        smsCoupon.validate();
        iSmsCouponService.insertProcess(smsCoupon, selectIds);
        return AjaxResult.success();
    }

    @ApiOperation("[跳转] 到优惠券编辑页面")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        Checker.check(smsSettingUtils.couponEnable(),"暂未启用优惠券功能");
        User user = getSysUser();
        Checker.checkOp(user.isSystem(), "登录用户非平台用户");
        SmsCouponDTO smsCoupon = iSmsCouponService.selectSmsCouponAllById(id);
        iSmsCouponService.operateValidate(smsCoupon, SmsCouponConst.operateBtnEnum.BTN_EIDT);
        Long shopId = smsCoupon.getShopId();
        BizShopInfo shopInfo = iBizShopInfoService.selectBizShopInfoById(shopId);
        String companyName = shopInfo.getCompanyName();
        mmap.put("smsCoupon", smsCoupon);
        mmap.put("shopName", companyName);
        return prefix + "/edit";
    }

    @ApiOperation("修改保存优惠券")
    @RequiresPermissions("business:smsCoupon:edit")
    @Log(title = "优惠券", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SmsCoupon smsCoupon, String selectIds) {
        User user = getSysUser();
        Checker.checkOp(user.isSystem(), "登录用户非平台用户");
//        Checker.checkOp(Objects.equals(smsCoupon.getShopId(), user.getDeptId()), "非该店铺优惠券");
        smsCoupon.validate();
        iSmsCouponService.updateProcess(smsCoupon, selectIds);
        return AjaxResult.success();
    }

    @ApiOperation("批量删除优惠券")
    @RequiresPermissions("business:smsCoupon:remove")
    @Log(title = "优惠券", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(Long ids) {
        User user = getSysUser();
        Checker.checkOp(user.isSystem(), "登录用户非平台用户");
        Checker.check(ObjectUtils.isEmpty(ids), "缺少优惠券标识");
        return toAjax(iSmsCouponService.deleteSmsCouponById(ids));
    }

    @ApiOperation("批量发放优惠券")
    @RequiresPermissions("business:smsCoupon:give")
    @Log(title = "批量发放优惠券", businessType = BusinessType.UPDATE)
    @PostMapping("/beachGiveCoupon")
    @ResponseBody
    public AjaxResult beachGiveCoupon(String memberIds,Long couponId) {
        return AjaxResult.success(iSmsCouponService.beachGiveCoupon(memberIds,couponId));
    }

    @ApiOperation("优惠券唯一校验")
    @PostMapping("/checkUnique")
    @ResponseBody
    public String checkUnique(SmsCoupon smsCoupon) {
        return iSmsCouponService.checkSmsCouponUnique(smsCoupon);
    }


    @ApiOperation("[跳转] 到优惠券选择指定分类页面")
    @GetMapping("/selectCategory/{shopId}")
    public String selectCategory(@PathVariable Long shopId,ModelMap map) {
        User user = getSysUser();
        Checker.checkOp(user.isSystem(), "登录用户非平台用户");
        map.put("shopId",shopId);
        return prefix + "/category/select";
    }

    @ApiOperation("优惠券选择指定分类")
    @PostMapping("/selectCategory")
    @ResponseBody
    public TableDataInfo selectCategoryList(SmsCouponProductCateRelationDTO relationDTO) {
        User user = getSysUser();
        Checker.checkOp(user.isSystem(), "登录用户非平台用户");
        Checker.check(ObjectUtils.isEmpty(relationDTO.getShopId()),"未选择店铺");
        //relationDTO.setShopId(user.getDeptId());
        startPage();
        List<SmsCouponProductCateRelationDTO> list = smsCouponProductCateRelationService.selectCateForCoupon(relationDTO);
        return getDataTable(list);
    }

    @ApiOperation("[跳转] 到优惠券选择指定商品页面")
    @GetMapping("/selectProduct/{shopId}")
    public String selectProduct(@PathVariable Long shopId, ModelMap map){
        Checker.check(smsSettingUtils.couponEnable(),"暂未启用优惠券功能");
        User user = getSysUser();
        Checker.checkOp(user.isSystem(), "登录用户非平台用户");
        map.put("shopId",shopId);
        return prefix + "/product/select";
    }

    @ApiOperation("[跳转] 到选择发券用户页面")
    @GetMapping("/giveCoupon/{couponId}")
    public String giveCoupon(@PathVariable Long couponId, ModelMap map){
        Checker.check(smsSettingUtils.couponEnable(),"暂未启用优惠券功能");
        User user = getSysUser();
        Checker.checkOp(user.isSystem(), "登录用户非平台用户");
        SmsCoupon smsCoupon = iSmsCouponService.selectSmsCouponById(couponId);
        Checker.check(ObjectUtils.isEmpty(smsCoupon),"该优惠券不存在或已过期");
        map.put("coupon",smsCoupon);
        return prefix + "/member/select";
    }

    @ApiOperation("同步存款分组标识")
    @GetMapping("/synchronousDepositgroup")
    @ResponseBody
    public AjaxResult synchronousDepositgroup(){
        User user = getSysUser();
        Checker.checkOp(user.isSystem(), "登录用户非平台用户");
        try{



            //获取会员信息中openid以逗号隔开
           // List<String> openidlist=iUmsMemberService.selectMemberConcat();

            //从市行公众号接口中获取存款分组信息---将字符串list转以逗号隔开的字符串
            String url_1 = url +"?openid=";
            MemberDataRsp rsp = restTemplate.getForObject(url_1, MemberDataRsp.class);
            log.info(rsp.getAttributes().toString());

            //将市行公众号上获取的数据转为list
          //  List<RealNameCertification> depositgroupListupdate = JsonUtils.json2ListBean(JsonUtils.toJson(rsp.getAttributes().get("update")), RealNameCertification.class);
           // log.info(depositgroupListupdate.size()+"");
            //批量更新会员信息的存款分组标识


            //将市行公众号上获取的数据转为list
            List<RealNameCertification> depositgroupListinsert = JsonUtils.json2ListBean(JsonUtils.toJson(rsp.getAttributes().get("insert")), RealNameCertification.class);
            log.info(depositgroupListinsert.size()+"");
            if(depositgroupListinsert.size()>0){
                int i=iUmsMemberService.bathUpdateDepositgroupAndNameAndIDcard(depositgroupListinsert);
                int j=iUmsMemberService.bathInsertDepositgroup(depositgroupListinsert);
            }


            return AjaxResult.success();
        }catch (Exception e) {
            log.info(e.toString());
            return AjaxResult.error();
        }
    }

    @ApiOperation("查询优惠券可以领取的用户")
    @PostMapping("/selectMember")
    @ResponseBody
    public TableDataInfo selectMember(SelectMemberParam param) {
        User user = getSysUser();
        Checker.checkOp(user.isSystem(), "登录用户非平台用户");
        startPage();
        List<UmsMember> list = iUmsMemberService.selectMemberByCouponId(param);
        return getDataTable(list);
    }

    @ApiOperation("优惠券选择指定商品")
    @PostMapping("/selectProduct")
    @ResponseBody
    public TableDataInfo selectProductList(SmsCouponProductRelationDTO relationDTO) {
        User user = getSysUser();
        Checker.checkOp(user.isSystem(), "登录用户非平台用户");
        // relationDTO.setShopId(user.getDeptId());
        Checker.check(ObjectUtils.isEmpty(relationDTO.getShopId()),"未选择店铺");
        startPage();
        List<SmsCouponProductRelationDTO> list = smsCouponProductRelationService.selectProductForCoupon(relationDTO);
        return getDataTable(list);
    }

    @ApiOperation("优惠券上架")
    @RequiresPermissions("business:smsCoupon:putaway")
    @PostMapping("/putaway")
    @ResponseBody
    public AjaxResult putaway(Long id) {
        Checker.check(ObjectUtils.isEmpty(id), "缺少优惠券标识");
        return iSmsCouponService.putawayCoupon(id) > 0
                ? AjaxResult.success("优惠券上架成功")
                : AjaxResult.error("优惠券上架失败");
    }

    @ApiOperation("优惠券下架")
    @RequiresPermissions("business:smsCoupon:soldout")
    @PostMapping("/soldout")
    @ResponseBody
    public AjaxResult soldout(Long id) {
        Checker.check(ObjectUtils.isEmpty(id), "缺少优惠券标识");
        return iSmsCouponService.soldoutCoupon(id) > 0
                ? AjaxResult.success("优惠券下架成功")
                : AjaxResult.error("优惠券下架失败");
    }

    @ApiOperation("优惠券使用、领取历史")
    @RequiresPermissions("business:smsCoupon:history")
    @GetMapping("/history/{couponId}")
    public String history(@PathVariable("couponId") Long couponId, ModelMap modelMap) {
        Checker.check(ObjectUtils.isEmpty(couponId), "缺少优惠券标识");
        SmsCouponDTO smsCoupon = iSmsCouponService.selectSmsCouponAllById(couponId);
        Checker.check(ObjectUtils.isEmpty(smsCoupon), "优惠券数据异常");
        //获取店铺信息放入其中
        Long shopId = smsCoupon.getShopId();
        BizShopInfo shopInfo = iBizShopInfoService.selectBizShopInfoById(shopId);

        modelMap.put("shopName", shopInfo.getCompanyName());
        modelMap.put("couponId", couponId);
        modelMap.put("smsCoupon", smsCoupon);
        return prefix + "/history/detail";
    }

    @ApiOperation("条件查询优惠券使用、领取历史列表")
    @RequiresPermissions("business:smsCoupon:history")
    @PostMapping("/history")
    @ResponseBody
    public TableDataInfo list(SmsCouponHistoryDTO smsCouponHistory) {
        Checker.check(ObjectUtils.isEmpty(smsCouponHistory.getCouponId()), "缺少优惠券标识");
        startPage();
        List<SmsCouponHistoryDTO> list = smsCouponHistoryService.selectSmsCouponHistoryListDTO(smsCouponHistory);
        return getDataTable(list);
    }

    @ApiOperation("导出优惠券领取历史列表")
    @RequiresPermissions("business:smsCouponHistory:export")
    @Log(title = "优惠券", businessType = BusinessType.EXPORT)
    @PostMapping("/exportHistory")
    @ResponseBody
    public AjaxResult exportHistory(SmsCouponHistoryDTO smsCouponHistory) {
        Long couponId = smsCouponHistory.getCouponId();
        Checker.check(ObjectUtils.isEmpty(couponId), "缺少优惠券标识");
        //查询优惠券信息
        SmsCouponDTO smsCoupon = iSmsCouponService.selectSmsCouponAllById(couponId);
        String code = smsCoupon.getCode();
        String name =  smsCoupon.getName();
        String type = smsCoupon.getCouponType();

        Checker.check(ObjectUtils.isEmpty(smsCoupon), "优惠券数据异常");
        //获取店铺信息放入其中
        Long shopId = smsCoupon.getShopId();
        BizShopInfo shopInfo = iBizShopInfoService.selectBizShopInfoById(shopId);
        String shopName = shopInfo.getCompanyName();
        List<SmsCouponExport> list = smsCouponHistoryService.selectSmsCouponHistoryListDTO(smsCouponHistory)
                .stream()
                .map(item-> new SmsCouponExport(shopName,name,code,type,item))
                .collect(Collectors.toList());
        ExcelUtil<SmsCouponExport> util = new ExcelUtil<SmsCouponExport>(SmsCouponExport. class);
        return util.exportExcel(list, "优惠券领取历史");
    }
}
