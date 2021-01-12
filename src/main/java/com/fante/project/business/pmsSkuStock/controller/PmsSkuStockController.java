package com.fante.project.business.pmsSkuStock.controller;

import java.util.List;

import com.fante.project.business.pmsSkuStock.domain.PmsSkuStockResult;
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
import com.fante.project.business.pmsSkuStock.domain.PmsSkuStock;
import com.fante.project.business.pmsSkuStock.service.IPmsSkuStockService;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.common.utils.poi.ExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fante.framework.web.page.TableDataInfo;

/**
 * sku的库存Controller
 *
 * @author fante
 * @date 2020-03-14
 */
@Api(tags = "PmsSkuStockController", description = "sku的库存")
@Controller
@RequestMapping("/business/pmsSkuStock")
public class PmsSkuStockController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(PmsSkuStockController.class);

    private String prefix = "business/pmsSkuStock";

    @Autowired
    private IPmsSkuStockService pmsSkuStockService;

    @RequiresPermissions("business:pmsSkuStock:view")
    @GetMapping()
    public String pmsSkuStock() {
        return prefix + "/pmsSkuStock";
    }

    @ApiOperation("条件查询sku的库存列表")
    @RequiresPermissions("business:pmsSkuStock:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PmsSkuStock pmsSkuStock) {
        startPage();
        List<PmsSkuStock> list = pmsSkuStockService.selectPmsSkuStockList(pmsSkuStock);
        return getDataTable(list);
    }


    @ApiOperation("条件查询缺货的sku的库存列表")
    @RequiresPermissions("business:pmsSkuStock:list")
    @PostMapping("/stockoutList")
    @ResponseBody
    public TableDataInfo stockoutList(PmsSkuStockResult pmsSkuStock) {
        startPage();
        List<PmsSkuStock> list = pmsSkuStockService.stockoutList(pmsSkuStock);
        return getDataTable(list);
    }

    @ApiOperation("导出sku的库存列表")
    @RequiresPermissions("business:pmsSkuStock:export")
    @Log(title = "sku的库存", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PmsSkuStock pmsSkuStock) {
        List<PmsSkuStock> list = pmsSkuStockService.selectPmsSkuStockList(pmsSkuStock);
        ExcelUtil<PmsSkuStock> util = new ExcelUtil<PmsSkuStock>(PmsSkuStock. class);
        return util.exportExcel(list, "pmsSkuStock");
    }

    @ApiOperation("[跳转] 到新增sku的库存页面")
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    @ApiOperation("新增保存sku的库存")
    @RequiresPermissions("business:pmsSkuStock:add")
    @Log(title = "sku的库存", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PmsSkuStock pmsSkuStock) {
        return toAjax(pmsSkuStockService.insertPmsSkuStock(pmsSkuStock));
    }

    @ApiOperation("[跳转] 到sku的库存编辑页面")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        PmsSkuStock pmsSkuStock =pmsSkuStockService.selectPmsSkuStockById(id);
        mmap.put("pmsSkuStock", pmsSkuStock);
        return prefix + "/edit";
    }

    @ApiOperation("[跳转] 到sku的对应字段编辑页面")
    @GetMapping("/edit/{field}/{id}")
    public String editSth(@PathVariable("field") String field,@PathVariable("id") Long id, ModelMap mmap) {
        PmsSkuStock pmsSkuStock =pmsSkuStockService.selectPmsSkuStockById(id);
        mmap.put("pmsSkuStock", pmsSkuStock);
        mmap.put("field", field);
        return prefix + "/edit";
    }

    @ApiOperation("修改保存sku的库存")
    @RequiresPermissions("business:pmsSkuStock:edit")
    @Log(title = "sku字段修改", businessType = BusinessType.UPDATE)
    @PostMapping("/{field}/saveField")
    @ResponseBody
    public AjaxResult saveSth(@PathVariable("field") String field,PmsSkuStock pmsSkuStock) {
        return toAjax(pmsSkuStockService.updateEditField(pmsSkuStock,field));
    }

    @ApiOperation("修改保存sku的库存")
    @RequiresPermissions("business:pmsSkuStock:edit")
    @Log(title = "sku的库存", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PmsSkuStock pmsSkuStock) {
        return toAjax(pmsSkuStockService.updatePmsSkuStock(pmsSkuStock));
    }

    @ApiOperation("唯一校验")
    @RequiresPermissions("business:pmsSkuStock:add")
    @Log(title = "sku的库存", businessType = BusinessType.INSERT)
    @PostMapping("/checkUnique")
    @ResponseBody
    public String checkPmsSkuStockUnique(PmsSkuStock pmsSkuStock) {
        return pmsSkuStockService.checkPmsSkuStockUnique(pmsSkuStock);
    }

    @ApiOperation("批量删除sku的库存")
    @RequiresPermissions("business:pmsSkuStock:remove")
    @Log(title = "sku的库存", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(pmsSkuStockService.deletePmsSkuStockByIds(ids));
    }
}
