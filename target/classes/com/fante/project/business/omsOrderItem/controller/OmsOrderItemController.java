package com.fante.project.business.omsOrderItem.controller;

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
import com.fante.project.business.omsOrderItem.domain.OmsOrderItem;
import com.fante.project.business.omsOrderItem.service.IOmsOrderItemService;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.common.utils.poi.ExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fante.framework.web.page.TableDataInfo;

/**
 * 订单中所包含的商品Controller
 *
 * @author fante
 * @date 2020-04-01
 */
@Api(tags = "OmsOrderItemController", description = "订单中所包含的商品")
@Controller
@RequestMapping("/business/omsOrderItem")
public class OmsOrderItemController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(OmsOrderItemController.class);

    private String prefix = "business/omsOrderItem";

    @Autowired
    private IOmsOrderItemService omsOrderItemService;

    @RequiresPermissions("business:omsOrderItem:view")
    @GetMapping()
    public String omsOrderItem() {
        return prefix + "/omsOrderItem";
    }

    @ApiOperation("条件查询订单中所包含的商品列表")
    @RequiresPermissions("business:omsOrderItem:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(OmsOrderItem omsOrderItem) {
        startPage();
        List<OmsOrderItem> list = omsOrderItemService.selectOmsOrderItemList(omsOrderItem);
        return getDataTable(list);
    }

    @ApiOperation("导出订单中所包含的商品列表")
    @RequiresPermissions("business:omsOrderItem:export")
    @Log(title = "订单中所包含的商品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OmsOrderItem omsOrderItem) {
        List<OmsOrderItem> list = omsOrderItemService.selectOmsOrderItemList(omsOrderItem);
        ExcelUtil<OmsOrderItem> util = new ExcelUtil<OmsOrderItem>(OmsOrderItem. class);
        return util.exportExcel(list, "omsOrderItem");
    }

    @ApiOperation("[跳转] 到新增订单中所包含的商品页面")
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    @ApiOperation("新增保存订单中所包含的商品")
    @RequiresPermissions("business:omsOrderItem:add")
    @Log(title = "订单中所包含的商品", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(OmsOrderItem omsOrderItem) {
        return toAjax(omsOrderItemService.insertOmsOrderItem(omsOrderItem));
    }

    @ApiOperation("[跳转] 到订单中所包含的商品编辑页面")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        OmsOrderItem omsOrderItem =omsOrderItemService.selectOmsOrderItemById(id);
        mmap.put("omsOrderItem", omsOrderItem);
        return prefix + "/edit";
    }

    @ApiOperation("修改保存订单中所包含的商品")
    @RequiresPermissions("business:omsOrderItem:edit")
    @Log(title = "订单中所包含的商品", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(OmsOrderItem omsOrderItem) {
        return toAjax(omsOrderItemService.updateOmsOrderItem(omsOrderItem));
    }

    @ApiOperation("批量删除订单中所包含的商品")
    @RequiresPermissions("business:omsOrderItem:remove")
    @Log(title = "订单中所包含的商品", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(omsOrderItemService.deleteOmsOrderItemByIds(ids));
    }

    @ApiOperation("订单中所包含的商品唯一校验")
    @PostMapping("/checkUnique")
    @ResponseBody
    public String checkUnique(OmsOrderItem omsOrderItem) {
        return omsOrderItemService.checkOmsOrderItemUnique(omsOrderItem);
    }

}
