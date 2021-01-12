package com.fante.project.business.smsHomeSignSetting.controller;

import com.fante.common.business.enums.SmsSignConst;
import com.fante.common.utils.Checker;
import com.fante.common.utils.poi.ExcelUtil;
import com.fante.framework.aspectj.lang.annotation.Log;
import com.fante.framework.aspectj.lang.enums.BusinessType;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.framework.web.page.TableDataInfo;
import com.fante.project.business.smsHomeSignSetting.domain.SmsHomeSignSetting;
import com.fante.project.business.smsHomeSignSetting.service.ISmsHomeSignSettingService;
import com.fante.project.business.smsSetting.utils.SmsSettingUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 签到奖励设置Controller
 *
 * @author fante
 * @date 2020-03-11
 */
@Api(tags = "SmsHomeSignSettingController", description = "签到奖励设置")
@Controller
@RequestMapping("/business/smsHomeSignSetting")
public class SmsHomeSignSettingController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(SmsHomeSignSettingController.class);

    private String prefix = "business/smsHomeSignSetting";

    @Autowired
    private ISmsHomeSignSettingService smsHomeSignSettingService;
    @Autowired
    private SmsSettingUtils smsSettingUtils;
    @RequiresPermissions("business:smsHomeSignSetting:view")
    @GetMapping()
    public String smsHomeSignSetting() {
        Checker.check(smsSettingUtils.signEnable(),"签到活动暂未开启");
        return prefix + "/smsHomeSignSetting";
    }

    @ApiOperation("条件查询签到奖励设置列表")
    @RequiresPermissions("business:smsHomeSignSetting:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SmsHomeSignSetting smsHomeSignSetting) {
        startPage();
        List<SmsHomeSignSetting> list = smsHomeSignSettingService.selectSmsHomeSignSettingList(smsHomeSignSetting);
        return getDataTable(list);
    }

    @ApiOperation("导出签到奖励设置列表")
    @RequiresPermissions("business:smsHomeSignSetting:export")
    @Log(title = "签到奖励设置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SmsHomeSignSetting smsHomeSignSetting) {
        List<SmsHomeSignSetting> list = smsHomeSignSettingService.selectSmsHomeSignSettingList(smsHomeSignSetting);
        ExcelUtil<SmsHomeSignSetting> util = new ExcelUtil<SmsHomeSignSetting>(SmsHomeSignSetting. class);
        return util.exportExcel(list, "smsHomeSignSetting");
    }

    @ApiOperation("[跳转] 到新增签到奖励设置页面")
    @GetMapping("/add")
    public String add(ModelMap modelMap) {
        Checker.check(smsSettingUtils.signEnable(),"签到活动暂未开启");
        modelMap.put("daily", SmsSignConst.SignType.DAILY.getType());
        return prefix + "/add";
    }

    @ApiOperation("新增保存签到奖励设置")
    @RequiresPermissions("business:smsHomeSignSetting:add")
    @Log(title = "签到奖励设置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SmsHomeSignSetting smsHomeSignSetting) {
        return toAjax(smsHomeSignSettingService.insertSmsHomeSignSetting(smsHomeSignSetting));
    }

    @ApiOperation("[跳转] 到签到奖励设置编辑页面")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        Checker.check(smsSettingUtils.signEnable(),"签到活动暂未开启");
        SmsHomeSignSetting smsHomeSignSetting =smsHomeSignSettingService.selectSmsHomeSignSettingById(id);
        mmap.put("smsHomeSignSetting", smsHomeSignSetting);
        mmap.put("daily", SmsSignConst.SignType.DAILY.getType());
        return prefix + "/edit";
    }

    @ApiOperation("修改保存签到奖励设置")
    @RequiresPermissions("business:smsHomeSignSetting:edit")
    @Log(title = "签到奖励设置", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SmsHomeSignSetting smsHomeSignSetting) {
        return toAjax(smsHomeSignSettingService.updateSmsHomeSignSetting(smsHomeSignSetting));
    }

    @ApiOperation("批量删除签到奖励设置")
    @RequiresPermissions("business:smsHomeSignSetting:remove")
    @Log(title = "签到奖励设置", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(smsHomeSignSettingService.deleteSmsHomeSignSettingByIds(ids));
    }

    @PostMapping("/checkSignSettingUnique")
    @ResponseBody
    public String checkSignSettingUnique(SmsHomeSignSetting smsHomeSignSetting) {
        return smsHomeSignSettingService.checkSignSettingUnique(smsHomeSignSetting);
    }


}
