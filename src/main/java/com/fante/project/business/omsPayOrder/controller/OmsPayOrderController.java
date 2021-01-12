package com.fante.project.business.omsPayOrder.controller;

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
import com.fante.project.business.omsPayOrder.domain.OmsPayOrder;
import com.fante.project.business.omsPayOrder.service.IOmsPayOrderService;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.common.utils.poi.ExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fante.framework.web.page.TableDataInfo;

/**
 * 支付订单Controller
 *
 * @author fante
 * @date 2020-04-18
 */
@Api(tags = "OmsPayOrderController", description = "支付订单")
@Controller
@RequestMapping("/business/omsPayOrder")
public class OmsPayOrderController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(OmsPayOrderController.class);

    private String prefix = "business/omsPayOrder";

    @Autowired
    private IOmsPayOrderService omsPayOrderService;

    @RequiresPermissions("business:omsPayOrder:view")
    @GetMapping()
    public String omsPayOrder() {
        return prefix + "/omsPayOrder";
    }

    @ApiOperation("条件查询支付订单列表")
    @RequiresPermissions("business:omsPayOrder:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(OmsPayOrder omsPayOrder) {
        startPage();
        List<OmsPayOrder> list = omsPayOrderService.selectOmsPayOrderList(omsPayOrder);
        return getDataTable(list);
    }

    @ApiOperation("导出支付订单列表")
    @RequiresPermissions("business:omsPayOrder:export")
    @Log(title = "支付订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OmsPayOrder omsPayOrder) {
        List<OmsPayOrder> list = omsPayOrderService.selectOmsPayOrderList(omsPayOrder);
        ExcelUtil<OmsPayOrder> util = new ExcelUtil<OmsPayOrder>(OmsPayOrder. class);
        return util.exportExcel(list, "omsPayOrder");
    }

    @ApiOperation("[跳转] 到新增支付订单页面")
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    @ApiOperation("新增保存支付订单")
    @RequiresPermissions("business:omsPayOrder:add")
    @Log(title = "支付订单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(OmsPayOrder omsPayOrder) {
        return toAjax(omsPayOrderService.insertOmsPayOrder(omsPayOrder));
    }

    @ApiOperation("[跳转] 到支付订单编辑页面")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        OmsPayOrder omsPayOrder =omsPayOrderService.selectOmsPayOrderById(id);
        mmap.put("omsPayOrder", omsPayOrder);
        return prefix + "/edit";
    }

    @ApiOperation("修改保存支付订单")
    @RequiresPermissions("business:omsPayOrder:edit")
    @Log(title = "支付订单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(OmsPayOrder omsPayOrder) {
        return toAjax(omsPayOrderService.updateOmsPayOrder(omsPayOrder));
    }

    @ApiOperation("批量删除支付订单")
    @RequiresPermissions("business:omsPayOrder:remove")
    @Log(title = "支付订单", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(omsPayOrderService.deleteOmsPayOrderByIds(ids));
    }

    @ApiOperation("支付订单唯一校验")
    @PostMapping("/checkUnique")
    @ResponseBody
    public String checkUnique(OmsPayOrder omsPayOrder) {
        return omsPayOrderService.checkOmsPayOrderUnique(omsPayOrder);
    }

}
