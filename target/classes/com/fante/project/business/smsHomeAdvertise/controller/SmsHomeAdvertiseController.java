package com.fante.project.business.smsHomeAdvertise.controller;

import com.fante.common.business.enums.ProductAttributeCategoryConst;
import com.fante.common.constant.Constants;
import com.fante.common.utils.poi.ExcelUtil;
import com.fante.framework.aspectj.lang.annotation.Log;
import com.fante.framework.aspectj.lang.enums.BusinessType;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.framework.web.page.TableDataInfo;
import com.fante.project.business.smsHomeAdvertise.domain.SmsHomeAdvertise;
import com.fante.project.business.smsHomeAdvertise.domain.SmsHomeAdvertiseProductSelectDTO;
import com.fante.project.business.smsHomeAdvertise.service.ISmsHomeAdvertiseService;
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
 * 广告管理Controller
 *
 * @author fante
 * @date 2020-04-07
 */
@Api(tags = "SmsHomeAdvertiseController", description = "广告管理")
@Controller
@RequestMapping("/business/smsHomeAdvertise")
public class SmsHomeAdvertiseController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(SmsHomeAdvertiseController.class);

    private String prefix = "business/smsHomeAdvertise";

    @Autowired
    private ISmsHomeAdvertiseService smsHomeAdvertiseService;

    @RequiresPermissions("business:smsHomeAdvertise:view")
    @GetMapping()
    public String smsHomeAdvertise(ModelMap map) {
        map.put("enable", Constants.ENABLE);
        return prefix + "/smsHomeAdvertise";
    }

    @ApiOperation("条件查询广告管理列表")
    @RequiresPermissions("business:smsHomeAdvertise:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SmsHomeAdvertise smsHomeAdvertise) {
        startPage();
        List<SmsHomeAdvertise> list = smsHomeAdvertiseService.selectSmsHomeAdvertiseList(smsHomeAdvertise);
        return getDataTable(list);
    }

    @ApiOperation("导出广告管理列表")
    @RequiresPermissions("business:smsHomeAdvertise:export")
    @Log(title = "广告管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SmsHomeAdvertise smsHomeAdvertise) {
        List<SmsHomeAdvertise> list = smsHomeAdvertiseService.selectSmsHomeAdvertiseList(smsHomeAdvertise);
        ExcelUtil<SmsHomeAdvertise> util = new ExcelUtil<SmsHomeAdvertise>(SmsHomeAdvertise. class);
        return util.exportExcel(list, "smsHomeAdvertise");
    }

    @ApiOperation("[跳转] 到新增广告管理页面")
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    @ApiOperation("新增保存广告管理")
    @RequiresPermissions("business:smsHomeAdvertise:add")
    @Log(title = "广告管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SmsHomeAdvertise smsHomeAdvertise) {
        return toAjax(smsHomeAdvertiseService.insertProcess(smsHomeAdvertise));
    }

    @ApiOperation("[跳转] 到广告管理编辑页面")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        SmsHomeAdvertise smsHomeAdvertise =smsHomeAdvertiseService.selectSmsHomeAdvertiseById(id);
        mmap.put("smsHomeAdvertise", smsHomeAdvertise);
        return prefix + "/edit";
    }

    @ApiOperation("修改保存广告管理")
    @RequiresPermissions("business:smsHomeAdvertise:edit")
    @Log(title = "广告管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SmsHomeAdvertise smsHomeAdvertise) {
        return toAjax(smsHomeAdvertiseService.updateProcess(smsHomeAdvertise));
    }

    @ApiOperation("批量删除广告管理")
    @RequiresPermissions("business:smsHomeAdvertise:remove")
    @Log(title = "广告管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(smsHomeAdvertiseService.deleteSmsHomeAdvertiseByIds(ids));
    }

    @ApiOperation("广告管理唯一校验")
    @PostMapping("/checkUnique")
    @ResponseBody
    public String checkUnique(SmsHomeAdvertise smsHomeAdvertise) {
        return smsHomeAdvertiseService.checkSmsHomeAdvertiseUnique(smsHomeAdvertise);
    }

    @GetMapping("/select/product")
    public String smsHomeRecommendProduct(ModelMap modelMap) {
        return prefix + "/select/product";
    }

    @ApiOperation("选择跳转商品")
    @PostMapping("/select/product")
    @ResponseBody
    public TableDataInfo recommendSelect(SmsHomeAdvertiseProductSelectDTO productSelectDTO) {
        startPage();
        productSelectDTO.setPublishStatus(ProductAttributeCategoryConst.publicStatusEnum.PUTAWAY.getType());
        List<SmsHomeAdvertiseProductSelectDTO> list = smsHomeAdvertiseService.advertiseProductSelect(productSelectDTO);
        return getDataTable(list);
    }

    @ApiOperation("广告启用")
    @RequiresPermissions("business:smsHomeAdvertise:edit")
    @Log(title = "广告启用", businessType = BusinessType.UPDATE)
    @PostMapping("/enable")
    @ResponseBody
    public AjaxResult enable(Long id) {
        return smsHomeAdvertiseService.changeStatus(id, Constants.ENABLE) > 0
                ? AjaxResult.success("广告启用成功")
                : AjaxResult.error("广告启用失败");
    }

    @ApiOperation("广告停用")
    @RequiresPermissions("business:smsHomeAdvertise:edit")
    @Log(title = "广告停用", businessType = BusinessType.UPDATE)
    @PostMapping("/cancel")
    @ResponseBody
    public AjaxResult cancelRecommend(Long id) {
        return smsHomeAdvertiseService.changeStatus(id, Constants.DISABLE) > 0
                ? AjaxResult.success("广告停用成功")
                : AjaxResult.error("广告停用失败");
    }


    @ApiOperation("设置广告排序")
    @RequiresPermissions("business:smsHomeAdvertise:edit")
    @Log(title = "设置广告排序", businessType = BusinessType.UPDATE)
    @PostMapping("/changeSort")
    @ResponseBody
    public AjaxResult changeSort(Long id, Integer sort) {
        return smsHomeAdvertiseService.changeSort(id, sort) > 0
                ? AjaxResult.success("设置排序成功")
                : AjaxResult.error("设置排序失败");
    }

}
