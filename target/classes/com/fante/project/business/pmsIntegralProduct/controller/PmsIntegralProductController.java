package com.fante.project.business.pmsIntegralProduct.controller;

import com.fante.common.business.constant.BizConstants;
import com.fante.common.business.enums.ProductAttributeCategoryConst;
import com.fante.common.utils.Checker;
import com.fante.common.utils.StringUtils;
import com.fante.common.utils.poi.ExcelUtil;
import com.fante.framework.aspectj.lang.annotation.Log;
import com.fante.framework.aspectj.lang.enums.BusinessType;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.framework.web.page.TableDataInfo;
import com.fante.project.business.pmsIntegralProduct.domain.PmsIntegralProduct;
import com.fante.project.business.pmsIntegralProduct.domain.PmsIntegralProductListDTO;
import com.fante.project.business.pmsIntegralProduct.service.IPmsIntegralProductService;
import com.fante.project.business.smsSetting.utils.SmsSettingUtils;
import com.fante.project.system.config.service.IConfigService;
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
 * 积分商品Controller
 *
 * @author fante
 * @date 2020-03-19
 */
@Api(tags = "PmsIntegralProductController", description = "积分商品")
@Controller
@RequestMapping("/business/pmsIntegralProduct")
public class PmsIntegralProductController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(PmsIntegralProductController.class);

    private String prefix = "business/pmsIntegralProduct";

    @Autowired
    private IPmsIntegralProductService pmsIntegralProductService;
    @Autowired
    private IConfigService configService;
    @Autowired
    private SmsSettingUtils smsSettingUtils;
    @RequiresPermissions("business:pmsIntegralProduct:view")
    @GetMapping()
    public String pmsIntegralProduct(ModelMap map) {
        Checker.check(smsSettingUtils.integralEnable(),"积分商城活动暂未开启");
        return prefix + "/pmsIntegralProduct";
    }

    @ApiOperation("条件查询积分商品列表")
    @RequiresPermissions("business:pmsIntegralProduct:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PmsIntegralProduct pmsIntegralProduct) {
        startPage();
        List<PmsIntegralProduct> list = pmsIntegralProductService.selectJoinList(pmsIntegralProduct);
        List<PmsIntegralProductListDTO> dtos = Lists.newArrayList();
        if (!ObjectUtils.isEmpty(list)) {
            list.forEach(product -> {
                dtos.add(new PmsIntegralProductListDTO(
                        product,
                        ProductAttributeCategoryConst.IntegralProductBtnEnum.getBtns(product.getPublishStatus()))
                );
            });
        }
        return getDataTable(dtos);
    }

    @ApiOperation("导出积分商品列表")
    @RequiresPermissions("business:pmsIntegralProduct:export")
    @Log(title = "积分商品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PmsIntegralProduct pmsIntegralProduct) {
        List<PmsIntegralProduct> list = pmsIntegralProductService.selectJoinList(pmsIntegralProduct);
        ExcelUtil<PmsIntegralProduct> util = new ExcelUtil<PmsIntegralProduct>(PmsIntegralProduct. class);
        return util.exportExcel(list, "pmsIntegralProduct");
    }

    @ApiOperation("[跳转] 到新增积分商品页面")
    @GetMapping("/add")
    public String add(ModelMap mmap) {
        mmap.put("productAlbumLimit", StringUtils.defaultString(configService.selectConfigByKey(BizConstants.smsIntegral.BIZ_INTEGRAL_PRODUCT_ALBUM), BizConstants.smsIntegral.BIZ_INTEGRAL_PRODUCT_ALBUM_DEF));
        return prefix + "/add";
    }

    @ApiOperation("新增保存积分商品")
    @RequiresPermissions("business:pmsIntegralProduct:add")
    @Log(title = "积分商品", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PmsIntegralProduct pmsIntegralProduct) {
        return toAjax(pmsIntegralProductService.insertPmsIntegralProduct(pmsIntegralProduct));
    }

    @ApiOperation("[跳转] 到积分商品编辑页面")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        PmsIntegralProduct pmsIntegralProduct =pmsIntegralProductService.selectPmsIntegralProductById(id);
        pmsIntegralProductService.operateValidate(pmsIntegralProduct, ProductAttributeCategoryConst.IntegralProductBtnEnum.BTN_EIDT);
        mmap.put("pmsIntegralProduct", pmsIntegralProduct);
        mmap.put("productAlbumLimit", StringUtils.defaultString(configService.selectConfigByKey(BizConstants.smsIntegral.BIZ_INTEGRAL_PRODUCT_ALBUM), BizConstants.smsIntegral.BIZ_INTEGRAL_PRODUCT_ALBUM_DEF));
        return prefix + "/edit";
    }

    @ApiOperation("修改保存积分商品")
    @RequiresPermissions("business:pmsIntegralProduct:edit")
    @Log(title = "积分商品", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PmsIntegralProduct pmsIntegralProduct) {
        pmsIntegralProductService.operateValidate(pmsIntegralProduct.getId(), ProductAttributeCategoryConst.IntegralProductBtnEnum.BTN_EIDT);
        return toAjax(pmsIntegralProductService.updatePmsIntegralProduct(pmsIntegralProduct));
    }

    @ApiOperation("批量删除积分商品")
    @RequiresPermissions("business:pmsIntegralProduct:remove")
    @Log(title = "积分商品", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(pmsIntegralProductService.deletePmsIntegralProductByIds(ids));
    }

    @ApiOperation("积分商品唯一校验")
    @PostMapping("/checkUnique")
    @ResponseBody
    public String checkUnique(PmsIntegralProduct pmsIntegralProduct) {
        return pmsIntegralProductService.checkPmsIntegralProductUnique(pmsIntegralProduct);
    }

    @ApiOperation("积分商品上架")
    @PostMapping("/putaway")
    @ResponseBody
    public AjaxResult putaway(Long id) {
        return pmsIntegralProductService.putawayPmsIntegralProduct(id) > 0
                ? AjaxResult.success("积分商品上架成功")
                : AjaxResult.error("积分商品上架失败");
    }

    @ApiOperation("积分商品下架")
    @PostMapping("/soldout")
    @ResponseBody
    public AjaxResult soldout(Long id) {
        return pmsIntegralProductService.soldoutPmsIntegralProduct(id) > 0
                ? AjaxResult.success("积分商品下架成功")
                : AjaxResult.error("积分商品下架失败");
    }

    @ApiOperation("积分商品更改排序")
    @PostMapping("/changeSort")
    @ResponseBody
    public AjaxResult changeSort(Long id, Long sort) {
        return pmsIntegralProductService.changeSort(id, sort) > 0
                ? AjaxResult.success("更改排序成功")
                : AjaxResult.error("更改排序失败");
    }
}
