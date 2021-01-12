package com.fante.project.business.cmsTopicCategory.controller;

import com.fante.common.utils.poi.ExcelUtil;
import com.fante.framework.aspectj.lang.annotation.Log;
import com.fante.framework.aspectj.lang.enums.BusinessType;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.framework.web.page.TableDataInfo;
import com.fante.project.business.cmsTopicCategory.domain.CmsTopicCategory;
import com.fante.project.business.cmsTopicCategory.service.ICmsTopicCategoryService;
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
 * 话题分类Controller
 *
 * @author fante
 * @date 2020-03-17
 */
@Api(tags = "CmsTopicCategoryController", description = "话题分类")
@Controller
@RequestMapping("/business/cmsTopicCategory")
public class CmsTopicCategoryController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(CmsTopicCategoryController.class);

    private String prefix = "business/cmsTopicCategory";

    @Autowired
    private ICmsTopicCategoryService cmsTopicCategoryService;

    @RequiresPermissions("business:cmsTopicCategory:view")
    @GetMapping()
    public String cmsTopicCategory() {
        return prefix + "/cmsTopicCategory";
    }

    @ApiOperation("条件查询话题分类列表")
    @RequiresPermissions("business:cmsTopicCategory:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CmsTopicCategory cmsTopicCategory) {
        startPage();
        List<CmsTopicCategory> list = cmsTopicCategoryService.selectJoinList(cmsTopicCategory);
        return getDataTable(list);
    }

    @ApiOperation("导出话题分类列表")
    @RequiresPermissions("business:cmsTopicCategory:export")
    @Log(title = "话题分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CmsTopicCategory cmsTopicCategory) {
        List<CmsTopicCategory> list = cmsTopicCategoryService.selectCmsTopicCategoryList(cmsTopicCategory);
        ExcelUtil<CmsTopicCategory> util = new ExcelUtil<CmsTopicCategory>(CmsTopicCategory. class);
        return util.exportExcel(list, "cmsTopicCategory");
    }

    @ApiOperation("[跳转] 到新增话题分类页面")
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    @ApiOperation("新增保存话题分类")
    @RequiresPermissions("business:cmsTopicCategory:add")
    @Log(title = "话题分类", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CmsTopicCategory cmsTopicCategory) {
        return toAjax(cmsTopicCategoryService.insertCmsTopicCategory(cmsTopicCategory));
    }

    @ApiOperation("[跳转] 到话题分类编辑页面")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        CmsTopicCategory cmsTopicCategory =cmsTopicCategoryService.selectCmsTopicCategoryById(id);
        mmap.put("cmsTopicCategory", cmsTopicCategory);
        return prefix + "/edit";
    }

    @ApiOperation("修改保存话题分类")
    @RequiresPermissions("business:cmsTopicCategory:edit")
    @Log(title = "话题分类", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CmsTopicCategory cmsTopicCategory) {
        return toAjax(cmsTopicCategoryService.updateCmsTopicCategory(cmsTopicCategory));
    }

    @ApiOperation("批量删除话题分类")
    @RequiresPermissions("business:cmsTopicCategory:remove")
    @Log(title = "话题分类", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(Long ids) {
        return toAjax(cmsTopicCategoryService.deleteCmsTopicCategoryById(ids));
    }


    @ApiOperation("话题分类唯一校验")
    @PostMapping("/checkUnique")
    @ResponseBody
    public String checkUnique(CmsTopicCategory cmsTopicCategory) {
        return cmsTopicCategoryService.checkCmsTopicCategoryUnique(cmsTopicCategory);
    }

}
