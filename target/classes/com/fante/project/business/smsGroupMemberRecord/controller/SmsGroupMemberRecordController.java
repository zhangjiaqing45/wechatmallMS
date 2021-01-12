package com.fante.project.business.smsGroupMemberRecord.controller;

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
import com.fante.project.business.smsGroupMemberRecord.domain.SmsGroupMemberRecord;
import com.fante.project.business.smsGroupMemberRecord.service.ISmsGroupMemberRecordService;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.common.utils.poi.ExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fante.framework.web.page.TableDataInfo;

/**
 * 团购记录人员Controller
 *
 * @author fante
 * @date 2020-03-30
 */
@Api(tags = "SmsGroupMemberRecordController", description = "团购记录人员")
@Controller
@RequestMapping("/business/smsGroupMemberRecord")
public class SmsGroupMemberRecordController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(SmsGroupMemberRecordController.class);

    private String prefix = "business/smsGroupMemberRecord";

    @Autowired
    private ISmsGroupMemberRecordService smsGroupMemberRecordService;
    @Autowired
    private SmsSettingUtils smsSettingUtils;

    @RequiresPermissions("business:smsGroupMemberRecord:view")
    @GetMapping()
    public String smsGroupMemberRecord() {
        Checker.check(ObjectUtils.isEmpty(smsSettingUtils.groupEnable()),"活动尚未开启");
        return prefix + "/smsGroupMemberRecord";
    }

    @ApiOperation("条件查询团购记录人员列表")
    @RequiresPermissions("business:smsGroupMemberRecord:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SmsGroupMemberRecord smsGroupMemberRecord) {
        startPage();
        List<SmsGroupMemberRecord> list = smsGroupMemberRecordService.selectSmsGroupMemberRecordList(smsGroupMemberRecord);
        return getDataTable(list);
    }

    @ApiOperation("导出团购记录人员列表")
    @RequiresPermissions("business:smsGroupMemberRecord:export")
    @Log(title = "团购记录人员", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SmsGroupMemberRecord smsGroupMemberRecord) {
        List<SmsGroupMemberRecord> list = smsGroupMemberRecordService.selectSmsGroupMemberRecordList(smsGroupMemberRecord);
        ExcelUtil<SmsGroupMemberRecord> util = new ExcelUtil<SmsGroupMemberRecord>(SmsGroupMemberRecord. class);
        return util.exportExcel(list, "smsGroupMemberRecord");
    }

    @ApiOperation("[跳转] 到新增团购记录人员页面")
    @GetMapping("/add")
    public String add() {
        Checker.check(ObjectUtils.isEmpty(smsSettingUtils.groupEnable()),"活动尚未开启");
        return prefix + "/add";
    }

    @ApiOperation("新增保存团购记录人员")
    @RequiresPermissions("business:smsGroupMemberRecord:add")
    @Log(title = "团购记录人员", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SmsGroupMemberRecord smsGroupMemberRecord) {
        return toAjax(smsGroupMemberRecordService.insertSmsGroupMemberRecord(smsGroupMemberRecord));
    }

    @ApiOperation("[跳转] 到团购记录人员编辑页面")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        Checker.check(ObjectUtils.isEmpty(smsSettingUtils.groupEnable()),"活动尚未开启");
        SmsGroupMemberRecord smsGroupMemberRecord =smsGroupMemberRecordService.selectSmsGroupMemberRecordById(id);
        mmap.put("smsGroupMemberRecord", smsGroupMemberRecord);
        return prefix + "/edit";
    }

    @ApiOperation("修改保存团购记录人员")
    @RequiresPermissions("business:smsGroupMemberRecord:edit")
    @Log(title = "团购记录人员", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SmsGroupMemberRecord smsGroupMemberRecord) {
        return toAjax(smsGroupMemberRecordService.updateSmsGroupMemberRecord(smsGroupMemberRecord));
    }

    @ApiOperation("批量删除团购记录人员")
    @RequiresPermissions("business:smsGroupMemberRecord:remove")
    @Log(title = "团购记录人员", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(smsGroupMemberRecordService.deleteSmsGroupMemberRecordByIds(ids));
    }

    @ApiOperation("团购记录人员唯一校验")
    @PostMapping("/checkUnique")
    @ResponseBody
    public String checkUnique(SmsGroupMemberRecord smsGroupMemberRecord) {
        return smsGroupMemberRecordService.checkSmsGroupMemberRecordUnique(smsGroupMemberRecord);
    }

}
