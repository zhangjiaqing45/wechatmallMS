package com.fante.project.business.wxSettings.controller;

import com.fante.framework.aspectj.lang.annotation.Log;
import com.fante.framework.aspectj.lang.enums.BusinessType;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.project.business.wxSettings.domain.WxSettings;
import com.fante.project.business.wxSettings.service.IWxSettingsService;
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
 * 微信公众号设置Controller
 *
 * @author fante
 * @date 2020-02-21
 */
@Controller
@RequestMapping("/business/wxSettings")
public class WxSettingsController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(WxSettingsController.class);

    private String prefix = "business/wxSettings";

    @Autowired
    private IWxSettingsService wxSettingsService;

    @RequiresPermissions("business:wxSettings:view")
    @GetMapping()
    public String wxSettings(ModelMap mmap) {
        WxSettings wxSettings = wxSettingsService.selectWxSettingsRecent();
        mmap.put("wxSettings", wxSettings);
        return prefix + "/wxSettings";
    }

    /**
     * 查询微信公众号设置列表
     */
    //@RequiresPermissions("business:wxSettings:list")
    //@PostMapping("/list")
    //@ResponseBody
    //public TableDataInfo list(WxSettings wxSettings) {
    //    startPage();
    //    List<WxSettings> list = wxSettingsService.selectWxSettingsList(wxSettings);
    //    return getDataTable(list);
    //}

    /**
     * 导出微信公众号设置列表
     */
    //@RequiresPermissions("business:wxSettings:export")
    //@Log(title = "微信公众号设置", businessType = BusinessType.EXPORT)
    //@PostMapping("/export")
    //@ResponseBody
    //public AjaxResult export(WxSettings wxSettings) {
    //    List<WxSettings> list = wxSettingsService.selectWxSettingsList(wxSettings);
    //    ExcelUtil<WxSettings> util = new ExcelUtil<WxSettings>(WxSettings.class);
    //    return util.exportExcel(list, "wxSettings");
    //}

    /**
     * 新增微信公众号设置
     */
    //@GetMapping("/add")
    //public String add() {
    //    return prefix + "/add";
    //}

    /**
     * 新增保存微信公众号设置
     */
    @RequiresPermissions("business:wxSettings:add")
    @Log(title = "微信公众号设置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WxSettings wxSettings) {
        return toAjax(wxSettingsService.insertWxSettings(wxSettings));
    }

    /**
     * 修改微信公众号设置
     */
    //@GetMapping("/edit/{id}")
    //public String edit(@PathVariable("id") Long id, ModelMap mmap) {
    //    WxSettings wxSettings = wxSettingsService.selectWxSettingsById(id);
    //    mmap.put("wxSettings", wxSettings);
    //    return prefix + "/edit";
    //}

    /**
     * 修改保存微信公众号设置
     */
    //@RequiresPermissions("business:wxSettings:edit")
    //@Log(title = "微信公众号设置", businessType = BusinessType.UPDATE)
    //@PostMapping("/edit")
    //@ResponseBody
    //public AjaxResult editSave(WxSettings wxSettings) {
    //    return toAjax(wxSettingsService.updateWxSettings(wxSettings));
    //}

    /**
     * 删除微信公众号设置
     */
    //@RequiresPermissions("business:wxSettings:remove")
    //@Log(title = "微信公众号设置", businessType = BusinessType.DELETE)
    //@PostMapping("/remove")
    //@ResponseBody
    //public AjaxResult remove(String ids) {
    //    return toAjax(wxSettingsService.deleteWxSettingsByIds(ids));
    //}
}
