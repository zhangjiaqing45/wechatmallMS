package com.fante.project.business.smsHomeRecommendProduct.controller;

import com.fante.common.business.enums.SmsRecommendConst;
import com.fante.common.constant.Constants;
import com.fante.common.utils.poi.ExcelUtil;
import com.fante.framework.aspectj.lang.annotation.Log;
import com.fante.framework.aspectj.lang.enums.BusinessType;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.framework.web.page.TableDataInfo;
import com.fante.project.business.smsHomeRecommendProduct.domain.SmsHomeRecommendProduct;
import com.fante.project.business.smsHomeRecommendProduct.domain.SmsHomeRecommendProductDTO;
import com.fante.project.business.smsHomeRecommendProduct.service.ISmsHomeRecommendProductService;
import com.fante.project.system.user.domain.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 精品推荐商品Controller
 *
 * @author fante
 * @date 2020-03-10
 */
@Api(tags = "SmsHomeRecommendProductBoutiqueController", description = "精品推荐商品")
@Controller
@RequestMapping("/business/smsHomeRecommendProductBoutique")
public class SmsHomeRecommendProductBoutiqueController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(SmsHomeRecommendProductBoutiqueController.class);

    private String prefix = "business/smsHomeRecommendProduct/boutique";

    @Autowired
    private ISmsHomeRecommendProductService smsHomeRecommendProductService;

    @RequiresPermissions("business:smsHomeRecommendProductBoutique:view")
    @GetMapping()
    public String smsHomeRecommendProduct(ModelMap modelMap) {
        modelMap.put("recommend", Constants.ENABLE);
        modelMap.put("recommendType", SmsRecommendConst.ProductType.BOUTIQUE.getType());
        modelMap.put("isAdmin", getSysUser().isAdmin() || getSysUser().isSystem());
        return prefix + "/smsHomeRecommendProduct";
    }

    @ApiOperation("条件查询精品推荐商品列表")
    @RequiresPermissions("business:smsHomeRecommendProductBoutique:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SmsHomeRecommendProductDTO productDTO) {
        User user = getSysUser();
        if (user.isFranchisee()) {
            productDTO.setShopId(user.getDeptId());
        }
        productDTO.setType(SmsRecommendConst.ProductType.BOUTIQUE.getType());
        startPage();
        List<SmsHomeRecommendProductDTO> list = smsHomeRecommendProductService.selectJoinList(productDTO);
        return getDataTable(list);
    }

    @ApiOperation("导出精品推荐商品列表")
    @RequiresPermissions("business:smsHomeRecommendProductBoutique:export")
    @Log(title = "精品推荐商品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SmsHomeRecommendProductDTO productDTO) {
        User user = getSysUser();
        if (user.isFranchisee()) {
            productDTO.setShopId(user.getDeptId());
        }
        productDTO.setType(SmsRecommendConst.ProductType.BOUTIQUE.getType());
        List<SmsHomeRecommendProductDTO> list = smsHomeRecommendProductService.selectJoinList(productDTO);
        ExcelUtil<SmsHomeRecommendProductDTO> util = new ExcelUtil<SmsHomeRecommendProductDTO>(SmsHomeRecommendProductDTO. class);
        return util.exportExcel(list, "推荐商品列表");
    }

    @ApiOperation("[跳转] 到新增精品推荐商品页面")
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    @ApiOperation("新增保存精品推荐商品")
    @RequiresPermissions("business:smsHomeRecommendProductBoutique:add")
    @Log(title = "精品推荐商品", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SmsHomeRecommendProduct smsHomeRecommendProduct) {
        smsHomeRecommendProduct.setType(SmsRecommendConst.ProductType.BOUTIQUE.getType());
        return toAjax(smsHomeRecommendProductService.insertSmsHomeRecommendProduct(smsHomeRecommendProduct));
    }

    @ApiOperation("[跳转] 到精品推荐商品编辑页面")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        SmsHomeRecommendProduct smsHomeRecommendProduct =smsHomeRecommendProductService.selectSmsHomeRecommendProductById(id);
        mmap.put("smsHomeRecommendProduct", smsHomeRecommendProduct);
        return prefix + "/edit";
    }

    @ApiOperation("修改保存精品推荐商品")
    @RequiresPermissions("business:smsHomeRecommendProductBoutique:edit")
    @Log(title = "精品推荐商品", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SmsHomeRecommendProduct smsHomeRecommendProduct) {
        return toAjax(smsHomeRecommendProductService.updateSmsHomeRecommendProduct(smsHomeRecommendProduct));
    }

    @ApiOperation("批量删除精品推荐商品")
    @RequiresPermissions("business:smsHomeRecommendProductBoutique:remove")
    @Log(title = "精品推荐商品", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(smsHomeRecommendProductService.deleteSmsHomeRecommendProductByIds(ids));
    }

    @ApiOperation("批量添加精品推荐")
    @RequiresPermissions("business:smsHomeRecommendProductBoutique:add")
    @Log(title = "批量添加精品推荐", businessType = BusinessType.INSERT)
    @PostMapping("/batchAdd")
    @ResponseBody
    public AjaxResult batchAdd(String ids) {
        return smsHomeRecommendProductService.batchInsertSmsHomeRecommendProducts(ids, SmsRecommendConst.ProductType.BOUTIQUE.getType(), getLoginName()) > 0
                ? AjaxResult.success("批量添加成功")
                : AjaxResult.error("批量添加失败");
    }

    @ApiOperation("设置精品推荐")
    @RequiresPermissions("business:smsHomeRecommendProductHot:edit")
    @Log(title = "设置精品推荐", businessType = BusinessType.UPDATE)
    @PostMapping("/setRecommend")
    @ResponseBody
    public AjaxResult setRecommend(Long id) {
        return smsHomeRecommendProductService.recommendSetting(id, Constants.ENABLE) > 0
                ? AjaxResult.success("设置精品推荐成功")
                : AjaxResult.error("设置精品推荐失败");
    }

    @ApiOperation("取消精品推荐")
    @RequiresPermissions("business:smsHomeRecommendProductHot:edit")
    @Log(title = "取消精品推荐", businessType = BusinessType.UPDATE)
    @PostMapping("/cancelRecommend")
    @ResponseBody
    public AjaxResult cancelRecommend(Long id) {
        return smsHomeRecommendProductService.recommendSetting(id, Constants.DISABLE) > 0
                ? AjaxResult.success("取消精品推荐成功")
                : AjaxResult.error("取消精品推荐失败");
    }


    @ApiOperation("设置精品推荐排序")
    @RequiresPermissions("business:smsHomeRecommendProductHot:edit")
    @Log(title = "设置精品推荐排序", businessType = BusinessType.UPDATE)
    @PostMapping("/changeSort")
    @ResponseBody
    public AjaxResult changeSort(Long id, Long sort) {
        return smsHomeRecommendProductService.changeSort(id, sort) > 0
                ? AjaxResult.success("设置排序成功")
                : AjaxResult.error("设置排序失败");
    }
}
