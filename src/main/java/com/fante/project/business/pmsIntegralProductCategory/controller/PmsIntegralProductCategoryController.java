package com.fante.project.business.pmsIntegralProductCategory.controller;

import com.fante.common.utils.Checker;
import com.fante.common.utils.poi.ExcelUtil;
import com.fante.framework.aspectj.lang.annotation.Log;
import com.fante.framework.aspectj.lang.enums.BusinessType;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.framework.web.page.TableDataInfo;
import com.fante.project.business.pmsIntegralProductCategory.domain.PmsIntegralProductCategory;
import com.fante.project.business.pmsIntegralProductCategory.service.IPmsIntegralProductCategoryService;
import com.fante.project.business.smsSetting.utils.SmsSettingUtils;
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
 * 积分商品分类Controller
 *
 * @author fante
 * @date 2020-03-18
 */
@Api(tags = "PmsIntegralProductCategoryController", description = "积分商品分类")
@Controller
@RequestMapping("/business/pmsIntegralProductCategory")
public class PmsIntegralProductCategoryController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(PmsIntegralProductCategoryController.class);

    private String prefix = "business/pmsIntegralProductCategory";

    @Autowired
    private IPmsIntegralProductCategoryService pmsIntegralProductCategoryService;
    @Autowired
    private SmsSettingUtils smsSettingUtils;
    @RequiresPermissions("business:pmsIntegralProductCategory:view")
    @GetMapping()
    public String pmsIntegralProductCategory() {
        Checker.check(smsSettingUtils.signEnable(),"签到活动暂未开启");
        return prefix + "/pmsIntegralProductCategory";
    }

    @ApiOperation("条件查询积分商品分类列表")
    @RequiresPermissions("business:pmsIntegralProductCategory:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PmsIntegralProductCategory pmsIntegralProductCategory) {
        startPage();
        List<PmsIntegralProductCategory> list = pmsIntegralProductCategoryService.selectJoinList(pmsIntegralProductCategory);
        return getDataTable(list);
    }

    @ApiOperation("导出积分商品分类列表")
    @RequiresPermissions("business:pmsIntegralProductCategory:export")
    @Log(title = "积分商品分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PmsIntegralProductCategory pmsIntegralProductCategory) {
        List<PmsIntegralProductCategory> list = pmsIntegralProductCategoryService.selectPmsIntegralProductCategoryList(pmsIntegralProductCategory);
        ExcelUtil<PmsIntegralProductCategory> util = new ExcelUtil<PmsIntegralProductCategory>(PmsIntegralProductCategory. class);
        return util.exportExcel(list, "pmsIntegralProductCategory");
    }

    @ApiOperation("[跳转] 到新增积分商品分类页面")
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    @ApiOperation("新增保存积分商品分类")
    @RequiresPermissions("business:pmsIntegralProductCategory:add")
    @Log(title = "积分商品分类", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PmsIntegralProductCategory pmsIntegralProductCategory) {
        return toAjax(pmsIntegralProductCategoryService.insertPmsIntegralProductCategory(pmsIntegralProductCategory));
    }

    @ApiOperation("[跳转] 到积分商品分类编辑页面")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        PmsIntegralProductCategory pmsIntegralProductCategory =pmsIntegralProductCategoryService.selectPmsIntegralProductCategoryById(id);
        mmap.put("pmsIntegralProductCategory", pmsIntegralProductCategory);
        return prefix + "/edit";
    }

    @ApiOperation("修改保存积分商品分类")
    @RequiresPermissions("business:pmsIntegralProductCategory:edit")
    @Log(title = "积分商品分类", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PmsIntegralProductCategory pmsIntegralProductCategory) {
        return toAjax(pmsIntegralProductCategoryService.updatePmsIntegralProductCategory(pmsIntegralProductCategory));
    }

    @ApiOperation("批量删除积分商品分类")
    @RequiresPermissions("business:pmsIntegralProductCategory:remove")
    @Log(title = "积分商品分类", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(Long ids) {
        return toAjax(pmsIntegralProductCategoryService.deletePmsIntegralProductCategoryById(ids));
    }

    @ApiOperation("积分商品分类唯一校验")
    @PostMapping("/checkUnique")
    @ResponseBody
    public String checkUnique(PmsIntegralProductCategory pmsIntegralProductCategory) {
        return pmsIntegralProductCategoryService.checkPmsIntegralProductCategoryUnique(pmsIntegralProductCategory);
    }

}
