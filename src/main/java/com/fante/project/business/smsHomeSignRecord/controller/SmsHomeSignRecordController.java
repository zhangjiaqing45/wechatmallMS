package com.fante.project.business.smsHomeSignRecord.controller;

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
import com.fante.project.business.smsHomeSignRecord.domain.SmsHomeSignRecord;
import com.fante.project.business.smsHomeSignRecord.service.ISmsHomeSignRecordService;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.common.utils.poi.ExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fante.framework.web.page.TableDataInfo;

/**
 * 签到记录Controller
 *
 * @author fante
 * @date 2020-03-12
 */
@Api(tags = "SmsHomeSignRecordController", description = "签到记录")
@Controller
@RequestMapping("/business/smsHomeSignRecord")
public class SmsHomeSignRecordController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(SmsHomeSignRecordController.class);

    private String prefix = "business/smsHomeSignRecord";

    @Autowired
    private ISmsHomeSignRecordService smsHomeSignRecordService;
    @Autowired
    private SmsSettingUtils smsSettingUtils;

    @RequiresPermissions("business:smsHomeSignRecord:view")
    @GetMapping()
    public String smsHomeSignRecord() {
        Checker.check(smsSettingUtils.signEnable(),"签到活动暂未开启");
        return prefix + "/smsHomeSignRecord";
    }

    @ApiOperation("条件查询签到记录列表")
    @RequiresPermissions("business:smsHomeSignRecord:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SmsHomeSignRecord smsHomeSignRecord) {
        startPage();
        List<SmsHomeSignRecord> list = smsHomeSignRecordService.selectSmsHomeSignRecordList(smsHomeSignRecord);
        return getDataTable(list);
    }

    @ApiOperation("导出签到记录列表")
    @RequiresPermissions("business:smsHomeSignRecord:export")
    @Log(title = "签到记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SmsHomeSignRecord smsHomeSignRecord) {
        List<SmsHomeSignRecord> list = smsHomeSignRecordService.selectSmsHomeSignRecordList(smsHomeSignRecord);
        ExcelUtil<SmsHomeSignRecord> util = new ExcelUtil<SmsHomeSignRecord>(SmsHomeSignRecord. class);
        return util.exportExcel(list, "smsHomeSignRecord");
    }

    @ApiOperation("[跳转] 到新增签到记录页面")
    @GetMapping("/add")
    public String add() {
        Checker.check(smsSettingUtils.signEnable(),"签到活动暂未开启");
        return prefix + "/add";
    }

    @ApiOperation("新增保存签到记录")
    @RequiresPermissions("business:smsHomeSignRecord:add")
    @Log(title = "签到记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SmsHomeSignRecord smsHomeSignRecord) {
        return toAjax(smsHomeSignRecordService.insertSmsHomeSignRecord(smsHomeSignRecord));
    }

    @ApiOperation("[跳转] 到签到记录编辑页面")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        Checker.check(smsSettingUtils.signEnable(),"签到活动暂未开启");
        SmsHomeSignRecord smsHomeSignRecord =smsHomeSignRecordService.selectSmsHomeSignRecordById(id);
        mmap.put("smsHomeSignRecord", smsHomeSignRecord);
        return prefix + "/edit";
    }

    @ApiOperation("修改保存签到记录")
    @RequiresPermissions("business:smsHomeSignRecord:edit")
    @Log(title = "签到记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SmsHomeSignRecord smsHomeSignRecord) {
        return toAjax(smsHomeSignRecordService.updateSmsHomeSignRecord(smsHomeSignRecord));
    }

    @ApiOperation("批量删除签到记录")
    @RequiresPermissions("business:smsHomeSignRecord:remove")
    @Log(title = "签到记录", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(smsHomeSignRecordService.deleteSmsHomeSignRecordByIds(ids));
    }
}
