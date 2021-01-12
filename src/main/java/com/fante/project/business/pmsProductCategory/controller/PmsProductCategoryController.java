package com.fante.project.business.pmsProductCategory.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fante.project.business.pmsProductCategory.domain.PmsProductCategoryDetail;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.fante.framework.aspectj.lang.annotation.Log;
import com.fante.framework.aspectj.lang.enums.BusinessType;
import io.swagger.annotations.*;
import com.fante.project.business.pmsProductCategory.domain.PmsProductCategory;
import com.fante.project.business.pmsProductCategory.service.IPmsProductCategoryService;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.common.utils.poi.ExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fante.framework.web.page.TableDataInfo;

/**
 * 产品分类Controller
 *
 * @author fante
 * @date 2020-03-09
 */
@Api(tags = "PmsProductCategoryController", description = "产品分类")
@Controller
@RequestMapping("/business/pmsProductCategory")
public class PmsProductCategoryController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(PmsProductCategoryController.class);

    private String prefix = "business/pmsProductCategory";

    @Autowired
    private IPmsProductCategoryService pmsProductCategoryService;

    @RequiresPermissions("business:pmsProductCategory:view")
    @GetMapping()
    public String pmsProductCategory() {
        return prefix + "/pmsProductCategory";
    }

    @ApiOperation("条件查询产品分类列表")
    @RequiresPermissions("business:pmsProductCategory:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(@RequestParam Map<String, Object> param) {
        startPage();
        List<PmsProductCategoryDetail> list = pmsProductCategoryService.getPmsProductCategoryList(param);
        return getDataTable(list);
    }
    
    @ApiOperation("查询商品分类下拉选择数据")
    @GetMapping("/selectList")
    @ResponseBody
    public TableDataInfo selectList(@RequestParam Map<String, Object> param){
        List<PmsProductCategoryDetail> list = pmsProductCategoryService.getPmsProductCategoryList(param);
        return getDataTable(list);
    }

    @ApiOperation("导出产品分类列表")
    @RequiresPermissions("business:pmsProductCategory:export")
    @Log(title = "产品分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PmsProductCategory pmsProductCategory) {
        List<PmsProductCategory> list = pmsProductCategoryService.selectPmsProductCategoryList(pmsProductCategory);
        ExcelUtil<PmsProductCategory> util = new ExcelUtil<PmsProductCategory>(PmsProductCategory. class);
        return util.exportExcel(list, "pmsProductCategory");
    }

    @ApiOperation("[跳转] 到新增产品分类页面")
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    @ApiOperation("新增保存产品分类")
    @RequiresPermissions("business:pmsProductCategory:add")
    @Log(title = "产品分类", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PmsProductCategory pmsProductCategory) {
        return toAjax(pmsProductCategoryService.insertPmsProductCategory(pmsProductCategory));
    }

    @ApiOperation("[跳转] 到产品分类编辑页面")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        PmsProductCategory pmsProductCategory =pmsProductCategoryService.selectPmsProductCategoryById(id);
        mmap.put("pmsProductCategory", pmsProductCategory);
        Map<String, Object> param = new HashMap<>();
        param.put("level", "0");
        List<PmsProductCategoryDetail> list = pmsProductCategoryService.getPmsProductCategoryList(param);
        mmap.put("selectList", list);
        return prefix + "/edit";
    }

    @ApiOperation("修改保存产品分类")
    @RequiresPermissions("business:pmsProductCategory:edit")
    @Log(title = "产品分类", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PmsProductCategory pmsProductCategory) {
        return toAjax(pmsProductCategoryService.updatePmsProductCategory(pmsProductCategory));
    }

    @ApiOperation("批量删除产品分类")
    @RequiresPermissions("business:pmsProductCategory:remove")
    @Log(title = "产品分类", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(pmsProductCategoryService.deletePmsProductCategoryByIds(ids));
    }

    @ApiOperation("查询当前店铺未选商品分类")
    @RequiresPermissions("business:pmsProductCategory:list")
    @PostMapping("/selectableCategory")
    @ResponseBody
    public TableDataInfo selectableCategory(PmsProductCategory pmsProductCategory) {
        startPage();
        List<PmsProductCategory> list = pmsProductCategoryService.selectableCategory(pmsProductCategory.getName());
        return getDataTable(list);
    }

    @ApiOperation("唯一校验")
    @RequiresPermissions("business:pmsProductCategory:add")
    @Log(title = "产品分类", businessType = BusinessType.INSERT)
    @PostMapping("/checkUnique")
    @ResponseBody
    public String checkPmsProductCategoryUnique(PmsProductCategory pmsProductCategory) {
        return pmsProductCategoryService.checkPmsProductCategoryUnique(pmsProductCategory);
    }
}
