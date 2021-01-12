package com.fante.project.business.pmsProductAttributeCategory.controller;

import java.util.List;

import com.fante.project.system.user.domain.User;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fante.framework.aspectj.lang.annotation.Log;
import com.fante.framework.aspectj.lang.enums.BusinessType;
import io.swagger.annotations.*;
import com.fante.project.business.pmsProductAttributeCategory.domain.PmsProductAttributeCategory;
import com.fante.project.business.pmsProductAttributeCategory.service.IPmsProductAttributeCategoryService;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.common.utils.poi.ExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fante.framework.web.page.TableDataInfo;

/**
 * 产品属性分类Controller
 *
 * @author fante
 * @date 2020-03-09
 */
@Api(tags = "PmsProductAttributeCategoryController", description = "产品属性分类")
@Controller
@RequestMapping("/business/pmsProductAttributeCategory")
public class PmsProductAttributeCategoryController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(PmsProductAttributeCategoryController.class);

    private String prefix = "business/pmsProductAttributeCategory";

    private String productAttributeUrl = "business/pmsProductAttribute/pmsProductAttribute";

    @Autowired
    private IPmsProductAttributeCategoryService pmsProductAttributeCategoryService;

    @RequiresPermissions("business:pmsProductAttributeCategory:view")
    @GetMapping()
    public String pmsProductAttributeCategory() {
        return prefix + "/pmsProductAttributeCategory";
    }

    @ApiOperation("条件查询产品属性分类列表")
    @RequiresPermissions("business:pmsProductAttributeCategory:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PmsProductAttributeCategory pmsProductAttributeCategory) {
        User user = getSysUser();
        if (user.isFranchisee()) {
            pmsProductAttributeCategory.setShopId(user.getDeptId());
        }
        startPage();
        List<PmsProductAttributeCategory> list = pmsProductAttributeCategoryService.selectPmsProductAttributeCategoryList(pmsProductAttributeCategory);
        return getDataTable(list);
    }

    @ApiOperation("导出产品属性分类列表")
    @RequiresPermissions("business:pmsProductAttributeCategory:export")
    @Log(title = "产品属性分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PmsProductAttributeCategory pmsProductAttributeCategory) {
        User user = getSysUser();
        if (user.isFranchisee()) {
            pmsProductAttributeCategory.setShopId(user.getDeptId());
        }
        List<PmsProductAttributeCategory> list = pmsProductAttributeCategoryService.selectPmsProductAttributeCategoryList(pmsProductAttributeCategory);
        ExcelUtil<PmsProductAttributeCategory> util = new ExcelUtil<PmsProductAttributeCategory>(PmsProductAttributeCategory. class);
        return util.exportExcel(list, "pmsProductAttributeCategory");
    }

    @ApiOperation("[跳转] 到新增产品属性分类页面")
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    @ApiOperation("新增保存产品属性分类")
    @RequiresPermissions("business:pmsProductAttributeCategory:add")
    @Log(title = "产品属性分类", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PmsProductAttributeCategory pmsProductAttributeCategory) {
        return toAjax(pmsProductAttributeCategoryService.insertPmsProductAttributeCategory(pmsProductAttributeCategory));
    }

    @ApiOperation("[跳转] 到产品属性分类编辑页面")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        PmsProductAttributeCategory pmsProductAttributeCategory =pmsProductAttributeCategoryService.selectPmsProductAttributeCategoryById(id);
        mmap.put("pmsProductAttributeCategory", pmsProductAttributeCategory);
        return prefix + "/edit";
    }

    @ApiOperation("修改保存产品属性分类")
    @RequiresPermissions("business:pmsProductAttributeCategory:edit")
    @Log(title = "产品属性分类", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PmsProductAttributeCategory pmsProductAttributeCategory) {
        return toAjax(pmsProductAttributeCategoryService.updatePmsProductAttributeCategory(pmsProductAttributeCategory));
    }

    @ApiOperation("批量删除产品属性分类")
    @RequiresPermissions("business:pmsProductAttributeCategory:remove")
    @Log(title = "产品属性分类", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(pmsProductAttributeCategoryService.deletePmsProductAttributeCategoryByIds(ids));
    }

    @ApiOperation("[跳转] 到属性列表页面")
    @GetMapping("/attr/{id}")
    public String attr(@PathVariable Long id,ModelMap mmap) {
        mmap.put("parentId",id);
        mmap.put("typeId",0);
        return productAttributeUrl;
    }

    @ApiOperation("[跳转] 到参数列表页面")
    @GetMapping("/param/{id}")
    public String param(@PathVariable Long id,ModelMap mmap) {
        mmap.put("parentId",id);
        mmap.put("typeId",1);
        return productAttributeUrl;
    }

    @ApiOperation("唯一校验")
    @RequiresPermissions("business:pmsProductAttributeCategory:add")
    @Log(title = "产品属性分类", businessType = BusinessType.INSERT)
    @PostMapping("/checkUnique")
    @ResponseBody
    public String checkPmsProductAttributeCategoryUnique(PmsProductAttributeCategory pmsProductAttributeCategory) {
        return pmsProductAttributeCategoryService.checkPmsProductAttributeCategoryUnique(pmsProductAttributeCategory);
    }
}
