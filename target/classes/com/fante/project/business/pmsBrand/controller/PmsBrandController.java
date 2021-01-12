package com.fante.project.business.pmsBrand.controller;

import java.util.List;

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
import com.fante.project.business.pmsBrand.domain.PmsBrand;
import com.fante.project.business.pmsBrand.service.IPmsBrandService;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.common.utils.poi.ExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fante.framework.web.page.TableDataInfo;

/**
 * 品牌Controller
 *
 * @author fante
 * @date 2020-03-09
 */
@Api(tags = "PmsBrandController", description = "品牌")
@Controller
@RequestMapping("/business/pmsBrand")
public class PmsBrandController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(PmsBrandController.class);

    private String prefix = "business/pmsBrand";

    @Autowired
    private IPmsBrandService pmsBrandService;

    @RequiresPermissions("business:pmsBrand:view")
    @GetMapping()
    public String pmsBrand() {
        return prefix + "/pmsBrand";
    }

    @ApiOperation("条件查询品牌列表")
    @RequiresPermissions("business:pmsBrand:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PmsBrand pmsBrand) {
        startPage();
        List<PmsBrand> list = pmsBrandService.selectPmsBrandList(pmsBrand);
        return getDataTable(list);
    }

    @ApiOperation("导出品牌列表")
    @RequiresPermissions("business:pmsBrand:export")
    @Log(title = "品牌", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PmsBrand pmsBrand) {
        List<PmsBrand> list = pmsBrandService.selectPmsBrandList(pmsBrand);
        ExcelUtil<PmsBrand> util = new ExcelUtil<PmsBrand>(PmsBrand. class);
        return util.exportExcel(list, "pmsBrand");
    }

    @ApiOperation("[跳转] 到新增品牌页面")
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    @ApiOperation("新增保存品牌")
    @RequiresPermissions("business:pmsBrand:add")
    @Log(title = "品牌", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PmsBrand pmsBrand) {
        return toAjax(pmsBrandService.insertPmsBrand(pmsBrand));
    }

    @ApiOperation("[跳转] 到品牌编辑页面")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        PmsBrand pmsBrand =pmsBrandService.selectPmsBrandById(id);
        mmap.put("pmsBrand", pmsBrand);
        return prefix + "/edit";
    }

    @ApiOperation("修改保存品牌")
    @RequiresPermissions("business:pmsBrand:edit")
    @Log(title = "品牌", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PmsBrand pmsBrand) {
        return toAjax(pmsBrandService.updatePmsBrand(pmsBrand));
    }

    @ApiOperation("批量删除品牌")
    @RequiresPermissions("business:pmsBrand:remove")
    @Log(title = "品牌", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(pmsBrandService.deletePmsBrandByIds(ids));
    }

    @ApiOperation("唯一校验")
    @RequiresPermissions("business:pmsBrand:add")
    @Log(title = "品牌", businessType = BusinessType.INSERT)
    @PostMapping("/checkUnique")
    @ResponseBody
    public String checkPmsBrandUnique(PmsBrand pmsBrand) {
        //本店唯一校验,其他店铺可以重复
        pmsBrand.setShopId(getSysUser().getDeptId());
        return pmsBrandService.checkPmsBrandUnique(pmsBrand);
    }

}
