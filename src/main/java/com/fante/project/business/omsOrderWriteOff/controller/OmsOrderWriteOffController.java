package com.fante.project.business.omsOrderWriteOff.controller;

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
import com.fante.project.business.omsOrderWriteOff.domain.OmsOrderWriteOff;
import com.fante.project.business.omsOrderWriteOff.service.IOmsOrderWriteOffService;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.common.utils.poi.ExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fante.framework.web.page.TableDataInfo;

/**
 * 订单核销记录Controller
 *
 * @author fante
 * @date 2020-11-14
 */
@Api(tags = "OmsOrderWriteOffController", description = "订单核销记录")
@Controller
@RequestMapping("/business/omsOrderWriteOff")
public class OmsOrderWriteOffController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(OmsOrderWriteOffController.class);

    private String prefix = "business/omsOrderWriteOff";

    @Autowired
    private IOmsOrderWriteOffService omsOrderWriteOffService;

    @RequiresPermissions("business:omsOrderWriteOff:view")
    @GetMapping()
    public String omsOrderWriteOff() {
        return prefix + "/omsOrderWriteOff";
    }

    @ApiOperation("条件查询订单核销记录列表")
    @RequiresPermissions("business:omsOrderWriteOff:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(OmsOrderWriteOff omsOrderWriteOff) {
        startPage();
        List<OmsOrderWriteOff> list = omsOrderWriteOffService.selectOmsOrderWriteOffList(omsOrderWriteOff);
        return getDataTable(list);
    }

    @ApiOperation("导出订单核销记录列表")
    @RequiresPermissions("business:omsOrderWriteOff:export")
    @Log(title = "订单核销记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OmsOrderWriteOff omsOrderWriteOff) {
        List<OmsOrderWriteOff> list = omsOrderWriteOffService.selectOmsOrderWriteOffList(omsOrderWriteOff);
        ExcelUtil<OmsOrderWriteOff> util = new ExcelUtil<OmsOrderWriteOff>(OmsOrderWriteOff. class);
        return util.exportExcel(list, "omsOrderWriteOff");
    }

    @ApiOperation("[跳转] 到新增订单核销记录页面")
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    @ApiOperation("新增保存订单核销记录")
    @RequiresPermissions("business:omsOrderWriteOff:add")
    @Log(title = "订单核销记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(OmsOrderWriteOff omsOrderWriteOff) {
        return toAjax(omsOrderWriteOffService.insertOmsOrderWriteOff(omsOrderWriteOff));
    }

    @ApiOperation("[跳转] 到订单核销记录编辑页面")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        OmsOrderWriteOff omsOrderWriteOff =omsOrderWriteOffService.selectOmsOrderWriteOffById(id);
        mmap.put("omsOrderWriteOff", omsOrderWriteOff);
        return prefix + "/edit";
    }

    @ApiOperation("修改保存订单核销记录")
    @RequiresPermissions("business:omsOrderWriteOff:edit")
    @Log(title = "订单核销记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(OmsOrderWriteOff omsOrderWriteOff) {
        return toAjax(omsOrderWriteOffService.updateOmsOrderWriteOff(omsOrderWriteOff));
    }

    @ApiOperation("批量删除订单核销记录")
    @RequiresPermissions("business:omsOrderWriteOff:remove")
    @Log(title = "订单核销记录", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(omsOrderWriteOffService.deleteOmsOrderWriteOffByIds(ids));
    }

    @ApiOperation("订单核销记录唯一校验")
    @PostMapping("/checkUnique")
    @ResponseBody
    public String checkUnique(OmsOrderWriteOff omsOrderWriteOff) {
        return omsOrderWriteOffService.checkOmsOrderWriteOffUnique(omsOrderWriteOff);
    }

}
