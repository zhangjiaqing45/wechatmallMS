package com.fante.project.business.smsGroupGameSku.controller;

import java.util.List;

import com.fante.common.utils.Checker;
import com.fante.project.business.smsSetting.utils.SmsSettingUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fante.framework.aspectj.lang.annotation.Log;
import com.fante.framework.aspectj.lang.enums.BusinessType;
import io.swagger.annotations.*;
import com.fante.project.business.smsGroupGameSku.domain.SmsGroupGameSku;
import com.fante.project.business.smsGroupGameSku.service.ISmsGroupGameSkuService;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.common.utils.poi.ExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fante.framework.web.page.TableDataInfo;

/**
 * 团购商品skuController
 *
 * @author fante
 * @date 2020-03-30
 */
@Api(tags = "SmsGroupGameSkuController", description = "团购商品sku")
@Controller
@RequestMapping("/business/smsGroupGameSku")
public class SmsGroupGameSkuController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(SmsGroupGameSkuController.class);

    private String prefix = "business/smsGroupGameSku";
    @Autowired
    private SmsSettingUtils smsSettingUtils;
    @Autowired
    private ISmsGroupGameSkuService smsGroupGameSkuService;

    @RequiresPermissions("business:smsGroupGameSku:view")
    @GetMapping()
    public String smsGroupGameSku() {
        Checker.check(ObjectUtils.isEmpty(smsSettingUtils.groupEnable()),"活动尚未开启");
        return prefix + "/smsGroupGameSku";
    }

    @ApiOperation("条件查询团购商品sku列表")
    @RequiresPermissions("business:smsGroupGameSku:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SmsGroupGameSku smsGroupGameSku) {
        startPage();
        List<SmsGroupGameSku> list = smsGroupGameSkuService.selectSmsGroupGameSkuList(smsGroupGameSku);
        return getDataTable(list);
    }

    @ApiOperation("导出团购商品sku列表")
    @RequiresPermissions("business:smsGroupGameSku:export")
    @Log(title = "团购商品sku", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SmsGroupGameSku smsGroupGameSku) {
        List<SmsGroupGameSku> list = smsGroupGameSkuService.selectSmsGroupGameSkuList(smsGroupGameSku);
        ExcelUtil<SmsGroupGameSku> util = new ExcelUtil<SmsGroupGameSku>(SmsGroupGameSku. class);
        return util.exportExcel(list, "smsGroupGameSku");
    }

    @ApiOperation("[跳转] 到新增团购商品sku页面")
    @GetMapping("/add")
    public String add() {
        Checker.check(ObjectUtils.isEmpty(smsSettingUtils.groupEnable()),"活动尚未开启");
        return prefix + "/add";
    }

    @ApiOperation("新增保存团购商品sku")
    @RequiresPermissions("business:smsGroupGameSku:add")
    @Log(title = "团购商品sku", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SmsGroupGameSku smsGroupGameSku) {
        return toAjax(smsGroupGameSkuService.insertSmsGroupGameSku(smsGroupGameSku));
    }

    @ApiOperation("[跳转] 到团购商品sku编辑页面")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        Checker.check(ObjectUtils.isEmpty(smsSettingUtils.groupEnable()),"活动尚未开启");
        SmsGroupGameSku smsGroupGameSku =smsGroupGameSkuService.selectSmsGroupGameSkuById(id);
        mmap.put("smsGroupGameSku", smsGroupGameSku);
        return prefix + "/edit";
    }

    @ApiOperation("修改保存团购商品sku")
    @RequiresPermissions("business:smsGroupGameSku:edit")
    @Log(title = "团购商品sku", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SmsGroupGameSku smsGroupGameSku) {
        return toAjax(smsGroupGameSkuService.updateSmsGroupGameSku(smsGroupGameSku));
    }

    @ApiOperation("批量删除团购商品sku")
    @RequiresPermissions("business:smsGroupGameSku:remove")
    @Log(title = "团购商品sku", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(smsGroupGameSkuService.deleteSmsGroupGameSkuByIds(ids));
    }

    @ApiOperation("团购商品sku唯一校验")
    @PostMapping("/checkUnique")
    @ResponseBody
    public String checkUnique(SmsGroupGameSku smsGroupGameSku) {
        return smsGroupGameSkuService.checkSmsGroupGameSkuUnique(smsGroupGameSku);
    }

}
