package com.fante.project.business.omsOrderOperateHistory.controller;

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
import com.fante.project.business.omsOrderOperateHistory.domain.OmsOrderOperateHistory;
import com.fante.project.business.omsOrderOperateHistory.service.IOmsOrderOperateHistoryService;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.common.utils.poi.ExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fante.framework.web.page.TableDataInfo;

/**
 * 订单操作历史记录Controller
 *
 * @author fante
 * @date 2020-04-02
 */
@Api(tags = "OmsOrderOperateHistoryController", description = "订单操作历史记录")
@Controller
@RequestMapping("/business/omsOrderOperateHistory")
public class OmsOrderOperateHistoryController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(OmsOrderOperateHistoryController.class);

    private String prefix = "business/omsOrderOperateHistory";

    @Autowired
    private IOmsOrderOperateHistoryService omsOrderOperateHistoryService;

    @RequiresPermissions("business:omsOrderOperateHistory:view")
    @GetMapping()
    public String omsOrderOperateHistory() {
        return prefix + "/omsOrderOperateHistory";
    }

    @ApiOperation("条件查询订单操作历史记录列表")
    @RequiresPermissions("business:omsOrderOperateHistory:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(OmsOrderOperateHistory omsOrderOperateHistory) {
        startPage();
        List<OmsOrderOperateHistory> list = omsOrderOperateHistoryService.selectOmsOrderOperateHistoryList(omsOrderOperateHistory);
        return getDataTable(list);
    }

    @ApiOperation("导出订单操作历史记录列表")
    @RequiresPermissions("business:omsOrderOperateHistory:export")
    @Log(title = "订单操作历史记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OmsOrderOperateHistory omsOrderOperateHistory) {
        List<OmsOrderOperateHistory> list = omsOrderOperateHistoryService.selectOmsOrderOperateHistoryList(omsOrderOperateHistory);
        ExcelUtil<OmsOrderOperateHistory> util = new ExcelUtil<OmsOrderOperateHistory>(OmsOrderOperateHistory. class);
        return util.exportExcel(list, "omsOrderOperateHistory");
    }

    @ApiOperation("[跳转] 到新增订单操作历史记录页面")
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    @ApiOperation("新增保存订单操作历史记录")
    @RequiresPermissions("business:omsOrderOperateHistory:add")
    @Log(title = "订单操作历史记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(OmsOrderOperateHistory omsOrderOperateHistory) {
        return toAjax(omsOrderOperateHistoryService.insertOmsOrderOperateHistory(omsOrderOperateHistory));
    }

    @ApiOperation("[跳转] 到订单操作历史记录编辑页面")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        OmsOrderOperateHistory omsOrderOperateHistory =omsOrderOperateHistoryService.selectOmsOrderOperateHistoryById(id);
        mmap.put("omsOrderOperateHistory", omsOrderOperateHistory);
        return prefix + "/edit";
    }

    @ApiOperation("修改保存订单操作历史记录")
    @RequiresPermissions("business:omsOrderOperateHistory:edit")
    @Log(title = "订单操作历史记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(OmsOrderOperateHistory omsOrderOperateHistory) {
        return toAjax(omsOrderOperateHistoryService.updateOmsOrderOperateHistory(omsOrderOperateHistory));
    }

    @ApiOperation("批量删除订单操作历史记录")
    @RequiresPermissions("business:omsOrderOperateHistory:remove")
    @Log(title = "订单操作历史记录", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(omsOrderOperateHistoryService.deleteOmsOrderOperateHistoryByIds(ids));
    }

    @ApiOperation("订单操作历史记录唯一校验")
    @PostMapping("/checkUnique")
    @ResponseBody
    public String checkUnique(OmsOrderOperateHistory omsOrderOperateHistory) {
        return omsOrderOperateHistoryService.checkOmsOrderOperateHistoryUnique(omsOrderOperateHistory);
    }

}
