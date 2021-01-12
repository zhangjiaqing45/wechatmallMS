package com.fante.project.business.smsFlashPromotion.controller;

import com.fante.common.business.enums.CommonUse;
import com.fante.common.business.enums.ProductAttributeCategoryConst;
import com.fante.common.business.enums.SmsFlashConst;
import com.fante.common.utils.Checker;
import com.fante.common.utils.poi.ExcelUtil;
import com.fante.framework.aspectj.lang.annotation.Log;
import com.fante.framework.aspectj.lang.enums.BusinessType;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.framework.web.page.TableDataInfo;
import com.fante.project.business.pmsSkuStock.domain.PmsSkuStock;
import com.fante.project.business.pmsSkuStock.service.IPmsSkuStockService;
import com.fante.project.business.smsFlashPromotion.domain.SmsFlashPromotion;
import com.fante.project.business.smsFlashPromotion.domain.SmsFlashPromotionListDTO;
import com.fante.project.business.smsFlashPromotion.service.ISmsFlashPromotionService;
import com.fante.project.business.smsFlashPromotionProduct.domain.SmsFlashProductAddDTO;
import com.fante.project.business.smsFlashPromotionProduct.domain.SmsFlashProductEditDTO;
import com.fante.project.business.smsFlashPromotionProduct.domain.SmsFlashPromotionProductDTO;
import com.fante.project.business.smsFlashPromotionProduct.domain.SmsFlashPromotionProductSelectDTO;
import com.fante.project.business.smsFlashPromotionProduct.service.ISmsFlashPromotionProductService;
import com.fante.project.business.smsFlashPromotionSession.domain.SmsFlashPromotionSession;
import com.fante.project.business.smsFlashPromotionSession.service.ISmsFlashPromotionSessionService;
import com.fante.project.business.smsFlashPromotionSku.domain.SmsFlashPromotionSku;
import com.fante.project.business.smsFlashPromotionSku.service.ISmsFlashPromotionSkuService;
import com.fante.project.business.smsSetting.utils.SmsSettingUtils;
import com.fante.project.system.user.domain.User;
import com.google.common.collect.Lists;
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

import java.util.List;

/**
 * 秒杀活动Controller
 *
 * @author fante
 * @date 2020-03-21
 */
@Api(tags = "SmsFlashPromotionController", description = "秒杀活动")
@Controller
@RequestMapping("/business/smsFlashPromotion")
public class SmsFlashPromotionController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(SmsFlashPromotionController.class);

    private String prefix = "business/smsFlashPromotion";

    @Autowired
    private ISmsFlashPromotionSessionService smsFlashPromotionSessionService;
    @Autowired
    private ISmsFlashPromotionService smsFlashPromotionService;
    @Autowired
    private ISmsFlashPromotionProductService smsFlashPromotionProductService;
    @Autowired
    private ISmsFlashPromotionSkuService smsFlashPromotionSkuService;
    @Autowired
    private IPmsSkuStockService pmsSkuStockService;
    @Autowired
    private SmsSettingUtils smsSettingUtils;
    @RequiresPermissions("business:smsFlashPromotion:view")
    @GetMapping()
    public String smsFlashPromotion(ModelMap modelMap) {
        Checker.check(smsSettingUtils.flashEnable(),"秒杀活动暂未开启");
        modelMap.put("isAdmin", getSysUser().isAdmin() || getSysUser().isSystem());
        return prefix + "/smsFlashPromotion";
    }

    @ApiOperation("条件查询秒杀活动列表")
    @RequiresPermissions("business:smsFlashPromotion:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SmsFlashPromotion smsFlashPromotion) {
        User user = getSysUser();
        if (user.isFranchisee()) {
            smsFlashPromotion.setShopId(user.getDeptId());
        }
        startPage();
        List<SmsFlashPromotion> list = smsFlashPromotionService.selectSmsFlashPromotionList(smsFlashPromotion);
        List<SmsFlashPromotionListDTO> dtos = Lists.newArrayList();
        list.forEach(promotion -> {
            dtos.add(new SmsFlashPromotionListDTO(promotion, SmsFlashConst.operateBtnEnum.getBtns(promotion.getStatus())));
        });
        return getDataTable(dtos);
    }

    @ApiOperation("导出秒杀活动列表")
    @RequiresPermissions("business:smsFlashPromotion:export")
    @Log(title = "秒杀活动", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SmsFlashPromotion smsFlashPromotion) {
        User user = getSysUser();
        if (user.isFranchisee()) {
            smsFlashPromotion.setShopId(user.getDeptId());
        }
        List<SmsFlashPromotion> list = smsFlashPromotionService.selectSmsFlashPromotionList(smsFlashPromotion);
        ExcelUtil<SmsFlashPromotion> util = new ExcelUtil<SmsFlashPromotion>(SmsFlashPromotion. class);
        return util.exportExcel(list, "smsFlashPromotion");
    }

    @ApiOperation("[跳转] 到新增秒杀活动页面")
    @GetMapping("/add")
    public String add() {
        Checker.check(smsSettingUtils.flashEnable(),"秒杀活动暂未开启");
        User user = getSysUser();
        Checker.checkOp(user.isFranchisee(), "登录用户非店铺用户");
        return prefix + "/add";
    }

    @ApiOperation("新增保存秒杀活动")
    @RequiresPermissions("business:smsFlashPromotion:add")
    @Log(title = "秒杀活动", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SmsFlashPromotion smsFlashPromotion) {
        User user = getSysUser();
        Checker.checkOp(user.isFranchisee(), "登录用户非店铺用户");
        smsFlashPromotion.setShopId(user.getDeptId());
        smsFlashPromotion.validate();
        return toAjax(smsFlashPromotionService.insertSmsFlashPromotion(smsFlashPromotion));
    }

    @ApiOperation("[跳转] 到秒杀活动编辑页面")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        Checker.check(smsSettingUtils.flashEnable(),"秒杀活动暂未开启");
        User user = getSysUser();
        Checker.checkOp(user.isFranchisee(), "登录用户非店铺用户");
        SmsFlashPromotion smsFlashPromotion =smsFlashPromotionService.selectSmsFlashPromotionById(id);
        smsFlashPromotionService.operateValidate(user.getDeptId(), smsFlashPromotion, SmsFlashConst.operateBtnEnum.BTN_EIDT);
        mmap.put("smsFlashPromotion", smsFlashPromotion);
        return prefix + "/edit";
    }

    @ApiOperation("修改保存秒杀活动")
    @RequiresPermissions("business:smsFlashPromotion:edit")
    @Log(title = "秒杀活动", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SmsFlashPromotion smsFlashPromotion) {
        User user = getSysUser();
        Checker.checkOp(user.isFranchisee(), "登录用户非店铺用户");
        smsFlashPromotion.validate();
        smsFlashPromotionService.operateValidate(user.getDeptId(), smsFlashPromotion.getId(), SmsFlashConst.operateBtnEnum.BTN_EIDT);
        return toAjax(smsFlashPromotionService.updateSmsFlashPromotion(smsFlashPromotion));
    }

    @ApiOperation("批量删除秒杀活动")
    @RequiresPermissions("business:smsFlashPromotion:remove")
    @Log(title = "秒杀活动", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(Long ids) {
        User user = getSysUser();
        Checker.checkOp(user.isFranchisee(), "登录用户非店铺用户");
        return toAjax(smsFlashPromotionService.deleteSmsFlashPromotionById(ids));
    }

    @ApiOperation("秒杀活动唯一校验")
    @PostMapping("/checkUnique")
    @ResponseBody
    public String checkUnique(SmsFlashPromotion smsFlashPromotion) {
        return smsFlashPromotionService.checkSmsFlashPromotionUnique(smsFlashPromotion);
    }

    @ApiOperation("秒杀活动开启")
    @RequiresPermissions("business:smsFlashPromotion:putaway")
    @Log(title = "秒杀活动开启", businessType = BusinessType.UPDATE)
    @PostMapping("/putaway")
    @ResponseBody
    public AjaxResult putaway(Long id) {
        User user = getSysUser();
        Checker.checkOp(user.isFranchisee(), "登录用户非店铺用户");
        Checker.check(ObjectUtils.isEmpty(id), "缺少秒杀活动标识");
        return smsFlashPromotionService.putawayPromotion(id) > 0
                ? AjaxResult.success("秒杀活动开启成功")
                : AjaxResult.error("秒杀活动开启失败");
    }

    @ApiOperation("秒杀活动结束")
    @RequiresPermissions("business:smsFlashPromotion:soldout")
    @Log(title = "秒杀活动结束", businessType = BusinessType.UPDATE)
    @PostMapping("/soldout")
    @ResponseBody
    public AjaxResult soldout(Long id) {
        Checker.check(ObjectUtils.isEmpty(id), "缺少秒杀活动标识");
        return smsFlashPromotionService.soldoutPromotion(id) > 0
                ? AjaxResult.success("秒杀活动结束成功")
                : AjaxResult.error("秒杀活动结束失败");
    }

    @ApiOperation("秒杀活动设置商品页面")
    @RequiresPermissions("business:smsFlashPromotion:setting")
    @GetMapping("/setting/{gameId}")
    public String productSetting(ModelMap modelMap, @PathVariable("gameId") Long gameId) {
        User user = getSysUser();
        Checker.checkOp(user.isFranchisee(), "登录用户非店铺用户");
        Checker.check(ObjectUtils.isEmpty(gameId), "缺少活动标识");
        smsFlashPromotionService.operateValidate(null, gameId, SmsFlashConst.operateBtnEnum.BTN_PRODUCT);
        List<SmsFlashPromotionSession> sessions = smsFlashPromotionSessionService.selectAvailableSessionList();
        Checker.check(ObjectUtils.isEmpty(sessions), "未设置秒杀时间段");
        modelMap.put("gameId",gameId);
        modelMap.put("sessions",sessions);
        return prefix + "/setting/list";
    }

    @ApiOperation("秒杀活动设置商品列表")
    @RequiresPermissions("business:smsFlashPromotion:setting")
    @PostMapping("/setting")
    @ResponseBody
    public TableDataInfo productSettingList(SmsFlashPromotionProductDTO productDTO) {
        User user = getSysUser();
        Checker.checkOp(user.isFranchisee(), "登录用户非店铺用户");
        Checker.check(ObjectUtils.isEmpty(productDTO.getFlashPromotionId()), "缺少活动标识");
        Checker.check(ObjectUtils.isEmpty(productDTO.getFlashPromotionSessionId()), "缺少时间段动标识");
        startPage();
        List<SmsFlashPromotionProductDTO> list = smsFlashPromotionProductService.selectJoinList(productDTO);
        return getDataTable(list);
    }

    @ApiOperation("秒杀活动添加商品页面")
    @RequiresPermissions("business:smsFlashPromotionProduct:add")
    @GetMapping("/setting/add/{gameId}/{timeId}")
    public String productSettingAdd(ModelMap map, @PathVariable("gameId") Long gameId, @PathVariable("timeId") Long timeId) {
        Checker.check(smsSettingUtils.flashEnable(),"秒杀活动暂未开启");
        User user = getSysUser();
        Checker.checkOp(user.isFranchisee(), "登录用户非店铺用户");
        Checker.check(ObjectUtils.isEmpty(gameId), "缺少活动标识");
        Checker.check(ObjectUtils.isEmpty(timeId), "缺少时间段动标识");
        map.put("gameId", gameId);
        map.put("timeId", timeId);
        return prefix + "/setting/add";
    }

    @ApiOperation("秒杀活动添加商品")
    @RequiresPermissions("business:smsFlashPromotionProduct:add")
    @Log(title = "秒杀活动添加商品", businessType = BusinessType.INSERT)
    @PostMapping("/setting/add")
    @ResponseBody
    public AjaxResult productSettingAddSave(@RequestBody SmsFlashProductAddDTO product) {
        smsFlashPromotionService.insertProductProcess(product);
        return AjaxResult.success();
    }

    @ApiOperation("秒杀活动删除商品")
    @RequiresPermissions("business:smsFlashPromotionProduct:remove")
    @Log(title = "秒杀活动删除商品", businessType = BusinessType.DELETE)
    @PostMapping("/setting/remove")
    @ResponseBody
    public AjaxResult productSettingRemove(Long flashPromotionId, Long promotionProductId) {
        smsFlashPromotionService.deleteProductProcess(flashPromotionId, promotionProductId);
        return AjaxResult.success();
    }

    @ApiOperation("秒杀活动编辑商品页面")
    @RequiresPermissions("business:smsFlashPromotionProduct:edit")
    @GetMapping("/setting/edit/{gameId}/{promotionProductId}")
    public String productSettingEdit(ModelMap map, @PathVariable("gameId") Long gameId, @PathVariable Long promotionProductId) {
        Checker.check(smsSettingUtils.flashEnable(),"秒杀活动暂未开启");
        User user = getSysUser();
        Checker.checkOp(user.isFranchisee(), "登录用户非店铺用户");
        Checker.check(ObjectUtils.isEmpty(gameId), "缺少活动标识");
        Checker.check(ObjectUtils.isEmpty(promotionProductId), "缺少秒杀商品标识");
        map.put("gameId", gameId);
        map.put("promotionProductId", promotionProductId);
        return prefix + "/setting/edit";
    }

    @ApiOperation("秒杀活动编辑商品页面获取SKU信息")
    @PostMapping("/setting/skulist")
    @ResponseBody
    public TableDataInfo productSettingSku(Long promotionProductId) {
        Checker.check(ObjectUtils.isEmpty(promotionProductId), "缺少秒杀商品标识");
        List<SmsFlashPromotionSku> list = smsFlashPromotionSkuService.selectProductSkuInSet(promotionProductId);
        return getDataTable(list);
    }

    @ApiOperation("秒杀活动编辑商品")
    @RequiresPermissions("business:smsFlashPromotionProduct:edit")
    @Log(title = "秒杀活动删除商品", businessType = BusinessType.UPDATE)
    @PostMapping("/setting/edit")
    @ResponseBody
    public AjaxResult productSettingEditSave(@RequestBody SmsFlashProductEditDTO product){
        smsFlashPromotionService.updateProductProcess(product);
        return AjaxResult.success();
    }

    @ApiOperation("秒杀活动商品选择页")
    @GetMapping("/select/product/{gameId}/{timeId}")
    public String productSelect(ModelMap map, @PathVariable("gameId") Long gameId, @PathVariable("timeId") Long timeId) {
        Checker.check(smsSettingUtils.flashEnable(),"秒杀活动暂未开启");
        User user = getSysUser();
        Checker.checkOp(user.isFranchisee(), "登录用户非店铺用户");
        Checker.check(ObjectUtils.isEmpty(gameId), "缺少活动标识");
        Checker.check(ObjectUtils.isEmpty(timeId), "缺少时间段动标识");
        map.put("gameId", gameId);
        map.put("timeId", timeId);
        return prefix + "/select/product";
    }

    @ApiOperation("秒杀活动商品选择列表")
    @PostMapping("/select/product")
    @ResponseBody
    public TableDataInfo productSelectList(SmsFlashPromotionProductSelectDTO selectDTO) {
        User user = getSysUser();
        Checker.checkOp(user.isFranchisee(), "登录用户非店铺用户");
        Checker.check(ObjectUtils.isEmpty(selectDTO.getFlashPromotionId()), "缺少活动标识");
        Checker.check(ObjectUtils.isEmpty(selectDTO.getFlashPromotionSessionId()), "缺少时间段动标识");
        selectDTO.setShopId(user.getDeptId());
        selectDTO.setPublishStatus(ProductAttributeCategoryConst.publicStatusEnum.PUTAWAY.getType());
        startPage();
        List<SmsFlashPromotionProductSelectDTO> list = smsFlashPromotionProductService.selectProductSelectList(selectDTO);
        return getDataTable(list);
    }

    @ApiOperation("秒杀活动根据选择商品获取SKU信息")
    @PostMapping("/select/skuStock")
    @ResponseBody
    public AjaxResult productSkuStock(PmsSkuStock pmsSkuStock) {
        User user = getSysUser();
        Checker.checkOp(user.isFranchisee(), "登录用户非店铺用户");
        Checker.check(ObjectUtils.isEmpty(pmsSkuStock.getProductId()), "缺少商品标识");
        pmsSkuStock.setShopId(user.getDeptId());
        return AjaxResult.success()
                .put("sku", pmsSkuStockService.selectPmsSkuStockList(pmsSkuStock));
    }

    @ApiOperation("设置秒杀活动商品排序")
    @Log(title = "设置秒杀活动商品排序", businessType = BusinessType.UPDATE)
    @PostMapping("/changeProductSort")
    @ResponseBody
    public AjaxResult changeProductSort(Long id, Integer sort) {
        return smsFlashPromotionProductService.changeSort(id, sort) > 0
                ? AjaxResult.success("设置排序成功")
                : AjaxResult.error("设置排序失败");
    }


    @ApiOperation("秒杀活动查看商品页面")
    @RequiresPermissions("business:smsFlashPromotion:browse")
    @GetMapping("/browse/{gameId}")
    public String productBrowse(ModelMap modelMap, @PathVariable("gameId") Long gameId) {
        Checker.check(smsSettingUtils.flashEnable(),"秒杀活动暂未开启");
        //User user = getSysUser();
        //Checker.checkOp(user.isFranchisee(), "登录用户非店铺用户");
        Checker.check(ObjectUtils.isEmpty(gameId), "缺少活动标识");
        smsFlashPromotionService.operateValidate(null, gameId, SmsFlashConst.operateBtnEnum.BTN_BROWSE);
        List<SmsFlashPromotionSession> sessions = smsFlashPromotionSessionService.selectAvailableSessionList();
        Checker.check(ObjectUtils.isEmpty(sessions), "未设置秒杀时间段");
        modelMap.put("gameId",gameId);
        modelMap.put("sessions",sessions);
        modelMap.put("enable", CommonUse.Status.ENABLE.getType());
        return prefix + "/browse/list";
    }

    @ApiOperation("秒杀活动查看商品列表")
    @RequiresPermissions("business:smsFlashPromotion:browse")
    @PostMapping("/browse")
    @ResponseBody
    public TableDataInfo productBrowseList(SmsFlashPromotionProductDTO productDTO) {
        //User user = getSysUser();
        //Checker.checkOp(user.isFranchisee(), "登录用户非店铺用户");
        Checker.check(ObjectUtils.isEmpty(productDTO.getFlashPromotionId()), "缺少活动标识");
        Checker.check(ObjectUtils.isEmpty(productDTO.getFlashPromotionSessionId()), "缺少时间段动标识");
        startPage();
        List<SmsFlashPromotionProductDTO> list = smsFlashPromotionProductService.selectJoinList(productDTO);
        return getDataTable(list);
    }

    @ApiOperation("秒杀活动商品详情页面")
    @RequiresPermissions("business:smsFlashPromotionProduct:detail")
    @GetMapping("/browse/detail/{gameId}/{promotionProductId}")
    public String productBrowseDetail(ModelMap map, @PathVariable("gameId") Long gameId, @PathVariable Long promotionProductId) {
        Checker.check(smsSettingUtils.flashEnable(),"秒杀活动暂未开启");
        //User user = getSysUser();
        //Checker.checkOp(user.isFranchisee(), "登录用户非店铺用户");
        Checker.check(ObjectUtils.isEmpty(gameId), "缺少活动标识");
        Checker.check(ObjectUtils.isEmpty(promotionProductId), "缺少秒杀商品标识");
        map.put("gameId", gameId);
        map.put("promotionProductId", promotionProductId);
        return prefix + "/browse/detail";
    }

    @ApiOperation("秒杀活动商品详情页面获取SKU信息")
    @PostMapping("/browse/skulist")
    @ResponseBody
    public TableDataInfo productBrowseSku(Long promotionProductId) {
        Checker.check(ObjectUtils.isEmpty(promotionProductId), "缺少秒杀商品标识");
        List<SmsFlashPromotionSku> list = smsFlashPromotionSkuService.selectSmsFlashPromotionSkuByPromotionPriductId(promotionProductId);
        return getDataTable(list);
    }

    @ApiOperation("秒杀活动商品停用")
    @RequiresPermissions("business:smsFlashPromotionProduct:block")
    @Log(title = "秒杀活动商品停用", businessType = BusinessType.UPDATE)
    @PostMapping("/browse/productOff")
    @ResponseBody
    public AjaxResult changeProductOff(Long id) {
        return smsFlashPromotionProductService.changeStatus(id, CommonUse.Status.DISABLE.getType()) > 0
                ? AjaxResult.success("活动商品停用成功")
                : AjaxResult.error("活动商品停用失败");
    }

    @ApiOperation("秒杀活动商品启用")
    @RequiresPermissions("business:smsFlashPromotionProduct:block")
    @Log(title = "秒杀活动商品启用", businessType = BusinessType.UPDATE)
    @PostMapping("/browse/productOn")
    @ResponseBody
    public AjaxResult changeProductOn(Long id) {
        return smsFlashPromotionProductService.changeStatus(id, CommonUse.Status.ENABLE.getType()) > 0
                ? AjaxResult.success("活动商品启用成功")
                : AjaxResult.error("活动商品启用失败");
    }


}
