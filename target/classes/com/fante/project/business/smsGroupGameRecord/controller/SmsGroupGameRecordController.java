package com.fante.project.business.smsGroupGameRecord.controller;

import com.fante.common.utils.Checker;
import com.fante.common.utils.poi.ExcelUtil;
import com.fante.framework.aspectj.lang.annotation.Log;
import com.fante.framework.aspectj.lang.enums.BusinessType;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.framework.web.page.TableDataInfo;
import com.fante.project.business.smsGroupGameRecord.domain.SmsGroupGameRecord;
import com.fante.project.business.smsGroupGameRecord.domain.SmsGroupGameRecordDetail;
import com.fante.project.business.smsGroupGameRecord.service.ISmsGroupGameRecordService;
import com.fante.project.business.smsSetting.utils.SmsSettingUtils;
import com.fante.project.system.user.domain.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 团购记录Controller
 *
 * @author fante
 * @date 2020-03-30
 */
@Api(tags = "SmsGroupGameRecordController", description = "团购记录")
@Controller
@RequestMapping("/business/smsGroupGameRecord")
public class SmsGroupGameRecordController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(SmsGroupGameRecordController.class);

    private String prefix = "business/smsGroupGameRecord";
    @Autowired
    private SmsSettingUtils smsSettingUtils;
    @Autowired
    private ISmsGroupGameRecordService smsGroupGameRecordService;

    @RequiresPermissions("business:smsGroupGameRecord:view")
    @GetMapping()
    public String smsGroupGameRecord(ModelMap map) {
        Checker.check(ObjectUtils.isEmpty(smsSettingUtils.groupEnable()),"活动尚未开启");
        map.put("isAdmin",getSysUser().isAdmin()||getSysUser().isSystem());
        return prefix + "/smsGroupGameRecord";
    }

    @ApiOperation("条件查询团购记录列表")
    @RequiresPermissions("business:smsGroupGameRecord:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SmsGroupGameRecord smsGroupGameRecord) {
        User user = getSysUser();
        if (user.isFranchisee()) {
            smsGroupGameRecord.setShopId(user.getDeptId());
        }
        startPage();
        List<SmsGroupGameRecord> list = smsGroupGameRecordService.selectSmsGroupGameRecordList(smsGroupGameRecord);
        return getDataTable(list);
    }

    @ApiOperation("导出团购记录列表")
    @RequiresPermissions("business:smsGroupGameRecord:export")
    @Log(title = "团购记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SmsGroupGameRecord smsGroupGameRecord) {
        User user = getSysUser();
        if (user.isFranchisee()) {
            smsGroupGameRecord.setShopId(user.getDeptId());
        }
        List<SmsGroupGameRecord> list = smsGroupGameRecordService.selectSmsGroupGameRecordList(smsGroupGameRecord);
        ExcelUtil<SmsGroupGameRecord> util = new ExcelUtil<SmsGroupGameRecord>(SmsGroupGameRecord. class);
        return util.exportExcel(list, "smsGroupGameRecord");
    }

    @ApiOperation("[跳转] 到新增团购记录页面")
    @GetMapping("/add")
    public String add() {
        Checker.check(ObjectUtils.isEmpty(smsSettingUtils.groupEnable()),"活动尚未开启");
        return prefix + "/add";
    }

    @ApiOperation("新增保存团购记录")
    @RequiresPermissions("business:smsGroupGameRecord:add")
    @Log(title = "团购记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SmsGroupGameRecord smsGroupGameRecord) {
        return toAjax(smsGroupGameRecordService.insertSmsGroupGameRecord(smsGroupGameRecord));
    }

    @ApiOperation("[跳转] 到团购记录编辑页面")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        Checker.check(ObjectUtils.isEmpty(smsSettingUtils.groupEnable()),"活动尚未开启");
        SmsGroupGameRecord smsGroupGameRecord =smsGroupGameRecordService.selectSmsGroupGameRecordById(id);
        mmap.put("smsGroupGameRecord", smsGroupGameRecord);
        return prefix + "/edit";
    }
    @ApiOperation("[跳转] 到团购记录详情页面")
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, ModelMap mmap) {
        Checker.check(ObjectUtils.isEmpty(smsSettingUtils.groupEnable()),"活动尚未开启");
        SmsGroupGameRecordDetail smsGroupGameRecord =smsGroupGameRecordService.detail(id);
        mmap.put("detail", smsGroupGameRecord);
        return prefix + "/detail";
    }


    @ApiOperation("修改保存团购记录")
    @RequiresPermissions("business:smsGroupGameRecord:edit")
    @Log(title = "团购记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SmsGroupGameRecord smsGroupGameRecord) {
        return toAjax(smsGroupGameRecordService.updateSmsGroupGameRecord(smsGroupGameRecord));
    }

    @ApiOperation("批量删除团购记录")
    @RequiresPermissions("business:smsGroupGameRecord:remove")
    @Log(title = "团购记录", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(smsGroupGameRecordService.deleteSmsGroupGameRecordByIds(ids));
    }

    @ApiOperation("团购记录唯一校验")
    @PostMapping("/checkUnique")
    @ResponseBody
    public String checkUnique(SmsGroupGameRecord smsGroupGameRecord) {
        return smsGroupGameRecordService.checkSmsGroupGameRecordUnique(smsGroupGameRecord);
    }

}
