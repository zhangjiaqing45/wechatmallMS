package com.fante.project.business.bizShopInfo.controller;

import com.fante.common.business.constant.BizConstants;
import com.fante.common.business.enums.AccRecordConst;
import com.fante.common.business.enums.AuditTypeEnum;
import com.fante.common.business.enums.UmsMemberConst;
import com.fante.common.constant.Constants;
import com.fante.common.utils.Checker;
import com.fante.common.utils.StringUtils;
import com.fante.common.utils.poi.ExcelUtil;
import com.fante.common.utils.spring.SpringUtils;
import com.fante.framework.aspectj.lang.annotation.Log;
import com.fante.framework.aspectj.lang.enums.BusinessType;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.framework.web.page.TableDataInfo;
import com.fante.project.business.bizMainCategory.domain.BizMainCategory;
import com.fante.project.business.bizMainCategory.service.IBizMainCategoryService;
import com.fante.project.business.bizShopInfo.domain.BizShopInfo;
import com.fante.project.business.bizShopInfo.domain.BizShopInfoDTO;
import com.fante.project.business.bizShopInfo.service.IBizShopInfoService;
import com.fante.project.business.bizShopInfo.utils.ShopRedis;
import com.fante.project.system.config.service.IConfigService;
import com.fante.project.system.user.domain.User;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
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
import org.weixin4j.spring.WeixinTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 店铺信息Controller
 *
 * @author fante
 * @date 2020-03-11
 */
@Api(tags = "BizShopInfoController", description = "店铺信息")
@Controller
@RequestMapping("/business/bizShopInfo")
public class BizShopInfoController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(BizShopInfoController.class);

    private String prefix = "business/bizShopInfo";

    @Autowired
    private IBizShopInfoService bizShopInfoService;
    @Autowired
    private IBizMainCategoryService bizMainCategoryService;
    @Autowired
    private IConfigService configService;
    @Autowired
    private ShopRedis shopRedis;

    @RequiresPermissions("business:bizShopInfo:view")
    @GetMapping()
    public String bizShopInfo() {
        return prefix + "/bizShopInfo";
    }

    @ApiOperation("条件查询店铺信息列表")
    @RequiresPermissions("business:bizShopInfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BizShopInfoDTO shopInfoDTO) {
        startPage();
        User user = getSysUser();
        List<BizShopInfoDTO> list = Collections.emptyList();
        if (user.isAdmin() || user.isSystem()) {
            shopInfoDTO.setAudit(AuditTypeEnum.SUCCESS.getType());
            list = bizShopInfoService.selectJoinList(shopInfoDTO);
        }
        return getDataTable(list);
    }

    @ApiOperation("导出店铺信息列表")
    @RequiresPermissions("business:bizShopInfo:export")
    @Log(title = "店铺信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BizShopInfoDTO shopInfoDTO) {
        User user = getSysUser();
        List<BizShopInfoDTO> list = Collections.emptyList();
        if (user.isAdmin() || user.isSystem()) {
            shopInfoDTO.setAudit(AuditTypeEnum.SUCCESS.getType());
            list = bizShopInfoService.selectJoinList(shopInfoDTO);
        }
        ExcelUtil<BizShopInfoDTO> util = new ExcelUtil<BizShopInfoDTO>(BizShopInfoDTO. class);
        return util.exportExcel(list, "店铺信息");
    }

    @ApiOperation("[跳转] 到新增店铺信息页面")
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    @ApiOperation("新增保存店铺信息")
    @RequiresPermissions("business:bizShopInfo:add")
    @Log(title = "店铺信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BizShopInfo bizShopInfo) {
        bizShopInfo.setAudit(AuditTypeEnum.SUCCESS.getType());
        return toAjax(bizShopInfoService.insertBizShopInfo(bizShopInfo));
    }

    @ApiOperation("[跳转] 到店铺信息编辑页面")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        BizShopInfo bizShopInfo =bizShopInfoService.selectBizShopInfoById(id);
        BizMainCategory mainCategory = null;
        if (!ObjectUtils.isEmpty(bizShopInfo) && !ObjectUtils.isEmpty(bizShopInfo.getCategoryId())) {
            mainCategory = bizMainCategoryService.selectBizMainCategoryById(bizShopInfo.getCategoryId());
        }
        mmap.put("bizShopInfo", bizShopInfo);
        mmap.put("mainCategory", mainCategory);
        return prefix + "/edit";
    }

    @ApiOperation("设为推荐")
    @RequiresPermissions("business:bizShopInfo:edit")
    @Log(title = "设为推荐", businessType = BusinessType.UPDATE)
    @PostMapping("/recommend")
    @ResponseBody
    public AjaxResult setRecommend(BizShopInfo bizShopInfo) {
        Checker.check(ObjectUtils.isEmpty(bizShopInfo.getId()),"店铺编号参数缺失");
        Checker.check(ObjectUtils.isEmpty(bizShopInfo.getRecommend()),"店铺推荐状态参数缺失");
        BizShopInfo updateParam = new BizShopInfo();
        updateParam.setId(bizShopInfo.getId());
        if(Constants.REMMOEND.equals(bizShopInfo.getRecommend())){
            updateParam.setRecommend(Constants.REMMOEND);
        }else{
            updateParam.setRecommend(Constants.NOT_REMMOEND);
        }
        return toAjax(bizShopInfoService.updateBizShopInfo(updateParam));
    }

    @ApiOperation("设为热门")
    @RequiresPermissions("business:bizShopInfo:edit")
    @Log(title = "设为热门", businessType = BusinessType.UPDATE)
    @PostMapping("/ishot")
    @ResponseBody
    public AjaxResult setishot(BizShopInfo bizShopInfo) {
        Checker.check(ObjectUtils.isEmpty(bizShopInfo.getId()),"店铺编号参数缺失");
        Checker.check(ObjectUtils.isEmpty(bizShopInfo.getIshot()),"店铺热门状态参数缺失");
        BizShopInfo updateParam = new BizShopInfo();
        updateParam.setId(bizShopInfo.getId());
        if(Constants.ISHOT.equals(bizShopInfo.getIshot())){
            updateParam.setIshot(Constants.ISHOT);
        }else{
            updateParam.setIshot(Constants.NOT_ISHOT);
        }
        return toAjax(bizShopInfoService.updateBizShopInfo(updateParam));
    }


    @ApiOperation("设为支付")
    @RequiresPermissions("business:bizShopInfo:edit")
    @Log(title = "设为支付", businessType = BusinessType.UPDATE)
    @PostMapping("/paymentDisplay")
    @ResponseBody
    public AjaxResult setPaymentDisplay(BizShopInfo bizShopInfo) {
        Checker.check(ObjectUtils.isEmpty(bizShopInfo.getId()),"店铺编号参数缺失");
        Checker.check(ObjectUtils.isEmpty(bizShopInfo.getPaymentDisplay()),"店铺支付状态参数缺失");
        BizShopInfo updateParam = new BizShopInfo();
        updateParam.setId(bizShopInfo.getId());
        if(Constants.PAYMENTDISPLAY.equals(bizShopInfo.getPaymentDisplay())){
            updateParam.setPaymentDisplay(Constants.PAYMENTDISPLAY);
        }else{
            updateParam.setPaymentDisplay(Constants.NOT_PAYMENTDISPLAY);
        }
        return toAjax(bizShopInfoService.updateBizShopInfo(updateParam));
    }


    @ApiOperation("修改保存店铺信息")
    @RequiresPermissions("business:bizShopInfo:edit")
    @Log(title = "店铺信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BizShopInfo bizShopInfo) {
        return toAjax(bizShopInfoService.updateBizShopInfo(bizShopInfo));
    }

    @ApiOperation("批量删除店铺信息")
    @RequiresPermissions("business:bizShopInfo:remove")
    @Log(title = "店铺信息", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(bizShopInfoService.deleteBizShopInfoByIds(ids));
    }

    @ApiOperation("店铺唯一校验")
    @PostMapping("/checkShopUnique")
    @ResponseBody
    public String checkShopUnique(BizShopInfo bizShopInfo) {
        return bizShopInfoService.checkShopUnique(bizShopInfo);
    }

    @GetMapping("/select")
    public String bizShopInfoSelect() {
        return prefix + "/bizShopInfoSelect";
    }

    @ApiOperation("选择店铺信息列表")
    @PostMapping("/selectList")
    @ResponseBody
    public TableDataInfo selectList(BizShopInfoDTO shopInfoDTO) {
        startPage();
        shopInfoDTO.setAudit(AuditTypeEnum.SUCCESS.getType());
        shopInfoDTO.setStatus(Constants.ENABLE);
        List<BizShopInfoDTO> list = bizShopInfoService.selectJoinList(shopInfoDTO);
        return getDataTable(list);
    }

    @RequiresPermissions("business:bizShopInfo:myshop")
    @GetMapping("/myshop")
    public String myshop(ModelMap mmap){
        User user = getSysUser();
        Checker.checkOp(user.isFranchisee(), "登录用户非店铺用户");
        Long deptId = user.getDeptId();
        Checker.check(ObjectUtils.isEmpty(deptId), "登录用户缺少店铺信息");
        BizShopInfo bizShopInfo =bizShopInfoService.selectBizShopInfoById(deptId);
        BizMainCategory mainCategory = null;
        List<Map<String, Object>> inviteList = Collections.emptyList();
        if (!ObjectUtils.isEmpty(bizShopInfo) && !ObjectUtils.isEmpty(bizShopInfo.getCategoryId())) {
            mainCategory = bizMainCategoryService.selectBizMainCategoryById(bizShopInfo.getCategoryId());
            inviteList = getInviteList(bizShopInfo.isSelfShop());
        }
        mmap.put("bizShopInfo", bizShopInfo);
        mmap.put("mainCategory", mainCategory);
        mmap.put("inviteList", inviteList);
        return prefix + "/my/shop";
    }

    private List<Map<String, Object>> getInviteList(boolean isSelf) {
        List<String> roles = isSelf ? UmsMemberConst.RoleType.SELF_SHOP_INVITE_ROLES : UmsMemberConst.RoleType.SHOP_INVITE_ROLES;
        List<Map<String, Object>> list = Lists.newArrayList();
        Map<String, Object> inner = null;
        for (UmsMemberConst.RoleType value : UmsMemberConst.RoleType.values()) {
            if (roles.contains(value.getType())) {
                inner = Maps.newHashMap();
                inner.put("type", value.getType());
                inner.put("desc", value.getDescribe());
                inner.put("style", value.getStyle());
                list.add(inner);
            }
        }
        return list;
    }

    @GetMapping("/invite/{type}")
    public String invite(ModelMap mmap, @PathVariable("type") String type) {
        User user = getSysUser();
        Checker.checkOp(user.isFranchisee(), "登录用户非店铺用户");
        String entrance = configService.selectConfigByKey(BizConstants.base.BIZ_SHOP_INVITE_ENTRANCE);
        Checker.check(StringUtils.isBlank(entrance), "未设置邀请入口");
        BizShopInfo bizShopInfo =bizShopInfoService.selectBizShopInfoById(user.getDeptId());

        String key = shopRedis.setInviteEntrance();
        String shopUrl = StringUtils.format(entrance, bizShopInfo.getCode(), type, key);
        WeixinTemplate weixinTemplate = SpringUtils.getBean(WeixinTemplate.class);
        mmap.put("url", weixinTemplate.getWeixinConfig().getOauthUrl() + shopUrl);
        return prefix + "/my/invite";
    }


    @GetMapping("/account/base/{shopId}")
    public String accAccountRecordOfBase(ModelMap map, @PathVariable("shopId") Long shopId) {
        map.put("shopId", shopId);
        map.put("accountMoneyType", AccRecordConst.AccountMoneyType.BASE.getType());
        return prefix + "/account/record";
    }

    @GetMapping("/account/commission/{shopId}")
    public String accAccountRecordOfCommission(ModelMap map, @PathVariable("shopId") Long shopId) {
        map.put("isAdmin", getSysUser().isAdmin());
        map.put("shopId", shopId);
        map.put("accountMoneyType", AccRecordConst.AccountMoneyType.COMMISSION.getType());
        return prefix + "/account/record";
    }

}
