package com.fante.project.business.wxPayConfig.controller;

import com.fante.framework.aspectj.lang.annotation.Log;
import com.fante.framework.aspectj.lang.enums.BusinessType;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.project.business.wxPayConfig.domain.WxPayConfig;
import com.fante.project.business.wxPayConfig.service.IWxPayConfigService;
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
 * 微信支付配置Controller
 *
 * @author fante
 * @date 2020-04-09
 */
@Api(tags = "WxPayConfigController", description = "微信支付配置")
@Controller
@RequestMapping("/business/wxPayConfig")
public class WxPayConfigController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(WxPayConfigController.class);

    private String prefix = "business/wxPayConfig";

    @Autowired
    private IWxPayConfigService wxPayConfigService;

    @RequiresPermissions("business:wxPayConfig:view")
    @GetMapping()
    public String wxPayConfig(ModelMap map) {
        WxPayConfig config = wxPayConfigService.selectWxPayConfigRecent();
        map.put("config", config);
        return prefix + "/wxPayConfig";
    }

    @ApiOperation("新增保存微信支付配置")
    @RequiresPermissions("business:wxPayConfig:add")
    @Log(title = "微信支付配置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WxPayConfig wxPayConfig) {
        return toAjax(wxPayConfigService.insertWxPayConfig(wxPayConfig));
    }


    //@ApiOperation("条件查询微信支付配置列表")
    //@RequiresPermissions("business:wxPayConfig:list")
    //@PostMapping("/list")
    //@ResponseBody
    //public TableDataInfo list(WxPayConfig wxPayConfig) {
    //    startPage();
    //    List<WxPayConfig> list = wxPayConfigService.selectWxPayConfigList(wxPayConfig);
    //    return getDataTable(list);
    //}
    //
    //@ApiOperation("导出微信支付配置列表")
    //@RequiresPermissions("business:wxPayConfig:export")
    //@Log(title = "微信支付配置", businessType = BusinessType.EXPORT)
    //@PostMapping("/export")
    //@ResponseBody
    //public AjaxResult export(WxPayConfig wxPayConfig) {
    //    List<WxPayConfig> list = wxPayConfigService.selectWxPayConfigList(wxPayConfig);
    //    ExcelUtil<WxPayConfig> util = new ExcelUtil<WxPayConfig>(WxPayConfig. class);
    //    return util.exportExcel(list, "wxPayConfig");
    //}
    //
    //@ApiOperation("[跳转] 到新增微信支付配置页面")
    //@GetMapping("/add")
    //public String add() {
    //    return prefix + "/add";
    //}
    //
    //@ApiOperation("[跳转] 到微信支付配置编辑页面")
    //@GetMapping("/edit/{id}")
    //public String edit(@PathVariable("id") Long id, ModelMap mmap) {
    //    WxPayConfig wxPayConfig =wxPayConfigService.selectWxPayConfigById(id);
    //    mmap.put("wxPayConfig", wxPayConfig);
    //    return prefix + "/edit";
    //}
    //
    //@ApiOperation("修改保存微信支付配置")
    //@RequiresPermissions("business:wxPayConfig:edit")
    //@Log(title = "微信支付配置", businessType = BusinessType.UPDATE)
    //@PostMapping("/edit")
    //@ResponseBody
    //public AjaxResult editSave(WxPayConfig wxPayConfig) {
    //    return toAjax(wxPayConfigService.updateWxPayConfig(wxPayConfig));
    //}
    //
    //@ApiOperation("批量删除微信支付配置")
    //@RequiresPermissions("business:wxPayConfig:remove")
    //@Log(title = "微信支付配置", businessType = BusinessType.DELETE)
    //@PostMapping("/remove")
    //@ResponseBody
    //public AjaxResult remove(String ids) {
    //    return toAjax(wxPayConfigService.deleteWxPayConfigByIds(ids));
    //}
    //
    //@ApiOperation("微信支付配置唯一校验")
    //@PostMapping("/checkUnique")
    //@ResponseBody
    //public String checkUnique(WxPayConfig wxPayConfig) {
    //    return wxPayConfigService.checkWxPayConfigUnique(wxPayConfig);
    //}

}
