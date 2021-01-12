package com.fante.project.business.wxOffiaccountConfig.controller;

import com.fante.framework.aspectj.lang.annotation.Log;
import com.fante.framework.aspectj.lang.enums.BusinessType;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.project.business.wxOffiaccountConfig.domain.WxOffiaccountConfig;
import com.fante.project.business.wxOffiaccountConfig.service.IWxOffiaccountConfigService;
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
 * 微信公众号配置Controller
 *
 * @author fante
 * @date 2020-04-09
 */
@Api(tags = "WxOffiaccountConfigController", description = "微信公众号配置")
@Controller
@RequestMapping("/business/wxOffiaccountConfig")
public class WxOffiaccountConfigController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(WxOffiaccountConfigController.class);

    private String prefix = "business/wxOffiaccountConfig";

    @Autowired
    private IWxOffiaccountConfigService wxOffiaccountConfigService;

    @RequiresPermissions("business:wxOffiaccountConfig:view")
    @GetMapping()
    public String wxOffiaccountConfig(ModelMap map) {
        WxOffiaccountConfig config = wxOffiaccountConfigService.selectWxOffiaccountConfigRecent();
        map.put("config", config);
        return prefix + "/wxOffiaccountConfig";
    }

    @ApiOperation("新增保存微信公众号配置")
    @RequiresPermissions("business:wxOffiaccountConfig:add")
    @Log(title = "微信公众号配置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WxOffiaccountConfig wxOffiaccountConfig) {
        return toAjax(wxOffiaccountConfigService.insertWxOffiaccountConfig(wxOffiaccountConfig));
    }

    //@ApiOperation("条件查询微信公众号配置列表")
    //@RequiresPermissions("business:wxOffiaccountConfig:list")
    //@PostMapping("/list")
    //@ResponseBody
    //public TableDataInfo list(WxOffiaccountConfig wxOffiaccountConfig) {
    //    startPage();
    //    List<WxOffiaccountConfig> list = wxOffiaccountConfigService.selectWxOffiaccountConfigList(wxOffiaccountConfig);
    //    return getDataTable(list);
    //}
    //
    //@ApiOperation("导出微信公众号配置列表")
    //@RequiresPermissions("business:wxOffiaccountConfig:export")
    //@Log(title = "微信公众号配置", businessType = BusinessType.EXPORT)
    //@PostMapping("/export")
    //@ResponseBody
    //public AjaxResult export(WxOffiaccountConfig wxOffiaccountConfig) {
    //    List<WxOffiaccountConfig> list = wxOffiaccountConfigService.selectWxOffiaccountConfigList(wxOffiaccountConfig);
    //    ExcelUtil<WxOffiaccountConfig> util = new ExcelUtil<WxOffiaccountConfig>(WxOffiaccountConfig. class);
    //    return util.exportExcel(list, "wxOffiaccountConfig");
    //}
    //
    //@ApiOperation("[跳转] 到新增微信公众号配置页面")
    //@GetMapping("/add")
    //public String add() {
    //    return prefix + "/add";
    //}
    //
    //@ApiOperation("[跳转] 到微信公众号配置编辑页面")
    //@GetMapping("/edit/{id}")
    //public String edit(@PathVariable("id") Long id, ModelMap mmap) {
    //    WxOffiaccountConfig wxOffiaccountConfig =wxOffiaccountConfigService.selectWxOffiaccountConfigById(id);
    //    mmap.put("wxOffiaccountConfig", wxOffiaccountConfig);
    //    return prefix + "/edit";
    //}
    //
    //@ApiOperation("修改保存微信公众号配置")
    //@RequiresPermissions("business:wxOffiaccountConfig:edit")
    //@Log(title = "微信公众号配置", businessType = BusinessType.UPDATE)
    //@PostMapping("/edit")
    //@ResponseBody
    //public AjaxResult editSave(WxOffiaccountConfig wxOffiaccountConfig) {
    //    return toAjax(wxOffiaccountConfigService.updateWxOffiaccountConfig(wxOffiaccountConfig));
    //}
    //
    //@ApiOperation("批量删除微信公众号配置")
    //@RequiresPermissions("business:wxOffiaccountConfig:remove")
    //@Log(title = "微信公众号配置", businessType = BusinessType.DELETE)
    //@PostMapping("/remove")
    //@ResponseBody
    //public AjaxResult remove(String ids) {
    //    return toAjax(wxOffiaccountConfigService.deleteWxOffiaccountConfigByIds(ids));
    //}
    //
    //@ApiOperation("微信公众号配置唯一校验")
    //@PostMapping("/checkUnique")
    //@ResponseBody
    //public String checkUnique(WxOffiaccountConfig wxOffiaccountConfig) {
    //    return wxOffiaccountConfigService.checkWxOffiaccountConfigUnique(wxOffiaccountConfig);
    //}

}
