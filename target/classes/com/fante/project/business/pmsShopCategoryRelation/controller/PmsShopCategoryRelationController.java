package com.fante.project.business.pmsShopCategoryRelation.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.fante.project.business.pmsBrand.domain.PmsBrand;
import com.fante.project.business.pmsProductCategory.domain.PmsProductCategory;
import com.fante.project.business.pmsShopCategoryRelation.domain.PmsShopCategoryRelationParam;
import com.fante.project.business.pmsShopCategoryRelation.domain.PmsShopCategoryRelationResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fante.framework.aspectj.lang.annotation.Log;
import com.fante.framework.aspectj.lang.enums.BusinessType;
import io.swagger.annotations.*;
import com.fante.project.business.pmsShopCategoryRelation.domain.PmsShopCategoryRelation;
import com.fante.project.business.pmsShopCategoryRelation.service.IPmsShopCategoryRelationService;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.common.utils.poi.ExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fante.framework.web.page.TableDataInfo;

/**
 * 店铺从平台选择的分类Controller
 *
 * @author fante
 * @date 2020-03-10
 */
@Api(tags = "PmsShopCategoryRelationController", description = "店铺从平台选择的分类")
@Controller
@RequestMapping("/business/pmsShopCategoryRelation")
public class PmsShopCategoryRelationController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(PmsShopCategoryRelationController.class);

    private String prefix = "business/pmsShopCategoryRelation";

    @Autowired
    private IPmsShopCategoryRelationService pmsShopCategoryRelationService;

    @RequiresPermissions("business:pmsShopCategoryRelation:view")
    @GetMapping()
    public String pmsShopCategoryRelation() {
        return prefix + "/pmsShopCategoryRelation";
    }

    @ApiOperation("条件查询店铺从平台选择的分类列表")
    @RequiresPermissions("business:pmsShopCategoryRelation:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PmsShopCategoryRelationParam pmsProductCategoryParam) {
        startPage();
        List<PmsShopCategoryRelationResult> list = pmsShopCategoryRelationService.selectPmsShopCategoryList(pmsProductCategoryParam);
        return getDataTable(list);
    }

    @ApiOperation("导出店铺从平台选择的分类列表")
    @RequiresPermissions("business:pmsShopCategoryRelation:export")
    @Log(title = "店铺从平台选择的分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export() {
        List<PmsShopCategoryRelationResult> list = pmsShopCategoryRelationService.selectPmsShopCategoryList(new PmsShopCategoryRelationParam());
        ExcelUtil<PmsShopCategoryRelationResult> util = new ExcelUtil<PmsShopCategoryRelationResult>(PmsShopCategoryRelationResult. class);
        return util.exportExcel(list, "PmsProductCategory");
    }

    @ApiOperation("[跳转] 到新增店铺从平台选择的分类页面")
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    @ApiOperation("新增保存店铺从平台选择的分类")
    @RequiresPermissions("business:pmsShopCategoryRelation:add")
    @Log(title = "店铺从平台选择的分类", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(String ids) {
        return toAjax(pmsShopCategoryRelationService.insertPmsShopCategoryRelationList(ids));
    }

    @ApiOperation("[跳转] 到店铺从平台选择的分类编辑页面")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        PmsProductCategory pmsShopCategory = pmsShopCategoryRelationService.selectPmsShopCategoryByRelationId(id);
        mmap.put("pmsShopCategory", pmsShopCategory);
        mmap.put("relationId", id);
        return prefix + "/edit";
    }

    @ApiOperation("修改保存店铺从平台选择的分类")
    @RequiresPermissions("business:pmsShopCategoryRelation:edit")
    @Log(title = "店铺从平台选择的分类", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PmsShopCategoryRelation pmsShopCategoryRelation) {
        return toAjax(pmsShopCategoryRelationService.updatePmsShopCategoryRelation(pmsShopCategoryRelation));
    }

    @ApiOperation("批量删除店铺从平台选择的分类")
    @RequiresPermissions("business:pmsShopCategoryRelation:remove")
    @Log(title = "店铺从平台选择的分类", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(pmsShopCategoryRelationService.deletePmsShopCategoryRelationById(ids));
    }

    @ApiOperation("[跳转] 到选择商品分类页面")
    @GetMapping("/selectCategory")
    public String selectCategory() {
        return prefix + "/selectCategory";
    }

    @ApiOperation("唯一校验")
    @RequiresPermissions("business:pmsShopCategoryRelation:add")
    @Log(title = "店铺从平台选择的分类", businessType = BusinessType.INSERT)
    @PostMapping("/checkUnique")
    @ResponseBody
    public String checkPmsShopCategoryRelationUnique(PmsShopCategoryRelation pmsShopCategoryRelation) {
        return pmsShopCategoryRelationService.checkPmsShopCategoryRelationUnique(pmsShopCategoryRelation);
    }
}
