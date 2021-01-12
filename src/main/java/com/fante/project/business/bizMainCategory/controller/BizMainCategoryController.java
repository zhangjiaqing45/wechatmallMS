package com.fante.project.business.bizMainCategory.controller;

import com.fante.common.business.constant.BizConstants;
import com.fante.common.constant.Constants;
import com.fante.common.utils.StringUtils;
import com.fante.common.utils.poi.ExcelUtil;
import com.fante.framework.aspectj.lang.annotation.Log;
import com.fante.framework.aspectj.lang.enums.BusinessType;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.framework.web.page.TableDataInfo;
import com.fante.project.business.bizMainCategory.domain.BizMainCategory;
import com.fante.project.business.bizMainCategory.service.IBizMainCategoryService;
import com.fante.project.system.config.service.IConfigService;
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
 * 店铺主营类目Controller
 *
 * @author fante
 * @date 2020-03-10
 */
@Api(tags = "BizMainCategoryController", description = "店铺主营类目")
@Controller
@RequestMapping("/business/bizMainCategory")
public class BizMainCategoryController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(BizMainCategoryController.class);

    private String prefix = "business/bizMainCategory";

    @Autowired
    private IBizMainCategoryService bizMainCategoryService;
    @Autowired
    private IConfigService configService;

    @RequiresPermissions("business:bizMainCategory:view")
    @GetMapping()
    public String bizMainCategory() {
        return prefix + "/bizMainCategory";
    }

    @ApiOperation("条件查询店铺主营类目列表")
    @RequiresPermissions("business:bizMainCategory:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BizMainCategory bizMainCategory) {
        startPage();
        List<BizMainCategory> list = bizMainCategoryService.selectBizMainCategoryList(bizMainCategory);
        return getDataTable(list);
    }

    @ApiOperation("导出店铺主营类目列表")
    @RequiresPermissions("business:bizMainCategory:export")
    @Log(title = "店铺主营类目", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BizMainCategory bizMainCategory) {
        List<BizMainCategory> list = bizMainCategoryService.selectBizMainCategoryList(bizMainCategory);
        ExcelUtil<BizMainCategory> util = new ExcelUtil<BizMainCategory>(BizMainCategory. class);
        return util.exportExcel(list, "bizMainCategory");
    }

    @ApiOperation("[跳转] 到新增店铺主营类目页面")
    @GetMapping("/add")
    public String add(ModelMap map) {
        map.put("categoryInfoMax", StringUtils.defaultString(configService.selectConfigByKey(BizConstants.shop.BIZ_MAIN_CATEGORY_INFO_MAX), BizConstants.shop.BIZ_MAIN_CATEGORY_INFO_MAX_DEF));
        return prefix + "/add";
    }

    @ApiOperation("新增保存店铺主营类目")
    @RequiresPermissions("business:bizMainCategory:add")
    @Log(title = "店铺主营类目", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BizMainCategory bizMainCategory) {
        return toAjax(bizMainCategoryService.insertBizMainCategory(bizMainCategory));
    }

    @ApiOperation("[跳转] 到店铺主营类目编辑页面")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        BizMainCategory bizMainCategory =bizMainCategoryService.selectBizMainCategoryById(id);
        mmap.put("bizMainCategory", bizMainCategory);
        mmap.put("categoryInfoMax", StringUtils.defaultString(configService.selectConfigByKey(BizConstants.shop.BIZ_MAIN_CATEGORY_INFO_MAX), BizConstants.shop.BIZ_MAIN_CATEGORY_INFO_MAX_DEF));
        return prefix + "/edit";
    }

    @ApiOperation("修改保存店铺主营类目")
    @RequiresPermissions("business:bizMainCategory:edit")
    @Log(title = "店铺主营类目", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BizMainCategory bizMainCategory) {
        return toAjax(bizMainCategoryService.updateBizMainCategory(bizMainCategory));
    }

    @ApiOperation("批量删除店铺主营类目")
    @RequiresPermissions("business:bizMainCategory:remove")
    @Log(title = "店铺主营类目", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(bizMainCategoryService.deleteBizMainCategoryByIds(ids));
    }

    @ApiOperation("校验主营类目名称唯一")
    @PostMapping("/checkCategoryUnique")
    @ResponseBody
    public String checkCategoryUnique(BizMainCategory bizMainCategory) {
        return bizMainCategoryService.checkCategoryUnique(bizMainCategory);
    }

    @GetMapping("/select")
    public String bizMainCategorySelect() {
        return prefix + "/bizMainCategorySelect";
    }

    @ApiOperation("选择店铺主营类目列表")
    @PostMapping("/selectList")
    @ResponseBody
    public TableDataInfo selectList(BizMainCategory bizMainCategory) {
        startPage();
        bizMainCategory.setStatus(Constants.ENABLE);
        List<BizMainCategory> list = bizMainCategoryService.selectBizMainCategoryList(bizMainCategory);
        return getDataTable(list);
    }
}
