package com.fante.project.business.omsOrderSetting.controller;

import com.fante.framework.aspectj.lang.annotation.Log;
import com.fante.framework.aspectj.lang.enums.BusinessType;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.project.business.omsOrderSetting.domain.OmsOrderSetting;
import com.fante.project.business.omsOrderSetting.service.IOmsOrderSettingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 订单设置Controller
 *
 * @author fante
 * @date 2020-05-13
 */
@Api(tags = "OmsOrderSettingController", description = "订单设置")
@Controller
@RequestMapping("/business/omsOrderSetting")
public class OmsOrderSettingController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(OmsOrderSettingController.class);

    private String prefix = "business/omsOrderSetting";

    @Autowired
    private IOmsOrderSettingService omsOrderSettingService;

    @RequiresPermissions("business:omsOrderSetting:view")
    @GetMapping()
    public String omsOrderSetting(ModelMap map) {
        OmsOrderSetting setting = omsOrderSettingService.selectOmsOrderSettingRecent();
        map.put("setting", setting);
        return prefix + "/omsOrderSetting";
    }

    //@ApiOperation("条件查询订单设置列表")
    //@RequiresPermissions("business:omsOrderSetting:list")
    //@PostMapping("/list")
    //@ResponseBody
    //public TableDataInfo list(OmsOrderSetting omsOrderSetting) {
    //    startPage();
    //    List<OmsOrderSetting> list = omsOrderSettingService.selectOmsOrderSettingList(omsOrderSetting);
    //    return getDataTable(list);
    //}
    //
    //@ApiOperation("导出订单设置列表")
    //@RequiresPermissions("business:omsOrderSetting:export")
    //@Log(title = "订单设置", businessType = BusinessType.EXPORT)
    //@PostMapping("/export")
    //@ResponseBody
    //public AjaxResult export(OmsOrderSetting omsOrderSetting) {
    //    List<OmsOrderSetting> list = omsOrderSettingService.selectOmsOrderSettingList(omsOrderSetting);
    //    ExcelUtil<OmsOrderSetting> util = new ExcelUtil<OmsOrderSetting>(OmsOrderSetting. class);
    //    return util.exportExcel(list, "omsOrderSetting");
    //}
    //
    //@ApiOperation("[跳转] 到新增订单设置页面")
    //@GetMapping("/add")
    //public String add() {
    //    return prefix + "/add";
    //}

    @ApiOperation("新增保存订单设置")
    @RequiresPermissions("business:omsOrderSetting:add")
    @Log(title = "订单设置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(OmsOrderSetting omsOrderSetting) {
        return toAjax(omsOrderSettingService.insertOmsOrderSetting(omsOrderSetting));
    }

    //@ApiOperation("[跳转] 到订单设置编辑页面")
    //@GetMapping("/edit/{id}")
    //public String edit(@PathVariable("id") Long id, ModelMap mmap) {
    //    OmsOrderSetting omsOrderSetting =omsOrderSettingService.selectOmsOrderSettingById(id);
    //    mmap.put("omsOrderSetting", omsOrderSetting);
    //    return prefix + "/edit";
    //}
    //
    //@ApiOperation("修改保存订单设置")
    //@RequiresPermissions("business:omsOrderSetting:edit")
    //@Log(title = "订单设置", businessType = BusinessType.UPDATE)
    //@PostMapping("/edit")
    //@ResponseBody
    //public AjaxResult editSave(OmsOrderSetting omsOrderSetting) {
    //    return toAjax(omsOrderSettingService.updateOmsOrderSetting(omsOrderSetting));
    //}
    //
    //@ApiOperation("批量删除订单设置")
    //@RequiresPermissions("business:omsOrderSetting:remove")
    //@Log(title = "订单设置", businessType = BusinessType.DELETE)
    //@PostMapping("/remove")
    //@ResponseBody
    //public AjaxResult remove(String ids) {
    //    return toAjax(omsOrderSettingService.deleteOmsOrderSettingByIds(ids));
    //}
    //
    //@ApiOperation("订单设置唯一校验")
    //@PostMapping("/checkUnique")
    //@ResponseBody
    //public String checkUnique(OmsOrderSetting omsOrderSetting) {
    //    return omsOrderSettingService.checkOmsOrderSettingUnique(omsOrderSetting);
    //}

}
