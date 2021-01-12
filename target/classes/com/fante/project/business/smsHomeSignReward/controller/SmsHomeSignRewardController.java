package com.fante.project.business.smsHomeSignReward.controller;

import java.util.List;

import com.fante.common.utils.Checker;
import com.fante.project.business.smsSetting.utils.SmsSettingUtils;
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
import com.fante.project.business.smsHomeSignReward.domain.SmsHomeSignReward;
import com.fante.project.business.smsHomeSignReward.service.ISmsHomeSignRewardService;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.common.utils.poi.ExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fante.framework.web.page.TableDataInfo;

/**
 * 签到奖励记录Controller
 *
 * @author fante
 * @date 2020-03-12
 */
@Api(tags = "SmsHomeSignRewardController", description = "签到奖励记录")
@Controller
@RequestMapping("/business/smsHomeSignReward")
public class SmsHomeSignRewardController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(SmsHomeSignRewardController.class);

    private String prefix = "business/smsHomeSignReward";

    @Autowired
    private ISmsHomeSignRewardService smsHomeSignRewardService;
    @Autowired
    private SmsSettingUtils smsSettingUtils;
    @RequiresPermissions("business:smsHomeSignReward:view")
    @GetMapping()
    public String smsHomeSignReward() {
        Checker.check(smsSettingUtils.signEnable(),"签到活动暂未开启");
        return prefix + "/smsHomeSignReward";
    }

    @ApiOperation("条件查询签到奖励记录列表")
    @RequiresPermissions("business:smsHomeSignReward:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SmsHomeSignReward smsHomeSignReward) {
        startPage();
        List<SmsHomeSignReward> list = smsHomeSignRewardService.selectSmsHomeSignRewardList(smsHomeSignReward);
        return getDataTable(list);
    }

    @ApiOperation("导出签到奖励记录列表")
    @RequiresPermissions("business:smsHomeSignReward:export")
    @Log(title = "签到奖励记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SmsHomeSignReward smsHomeSignReward) {
        List<SmsHomeSignReward> list = smsHomeSignRewardService.selectSmsHomeSignRewardList(smsHomeSignReward);
        ExcelUtil<SmsHomeSignReward> util = new ExcelUtil<SmsHomeSignReward>(SmsHomeSignReward. class);
        return util.exportExcel(list, "smsHomeSignReward");
    }

    @ApiOperation("[跳转] 到新增签到奖励记录页面")
    @GetMapping("/add")
    public String add() {
        Checker.check(smsSettingUtils.signEnable(),"签到活动暂未开启");
        return prefix + "/add";
    }

    @ApiOperation("新增保存签到奖励记录")
    @RequiresPermissions("business:smsHomeSignReward:add")
    @Log(title = "签到奖励记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SmsHomeSignReward smsHomeSignReward) {
        return toAjax(smsHomeSignRewardService.insertSmsHomeSignReward(smsHomeSignReward));
    }

    @ApiOperation("[跳转] 到签到奖励记录编辑页面")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        Checker.check(smsSettingUtils.signEnable(),"签到活动暂未开启");
        SmsHomeSignReward smsHomeSignReward =smsHomeSignRewardService.selectSmsHomeSignRewardById(id);
        mmap.put("smsHomeSignReward", smsHomeSignReward);
        return prefix + "/edit";
    }

    @ApiOperation("修改保存签到奖励记录")
    @RequiresPermissions("business:smsHomeSignReward:edit")
    @Log(title = "签到奖励记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SmsHomeSignReward smsHomeSignReward) {
        return toAjax(smsHomeSignRewardService.updateSmsHomeSignReward(smsHomeSignReward));
    }

    @ApiOperation("批量删除签到奖励记录")
    @RequiresPermissions("business:smsHomeSignReward:remove")
    @Log(title = "签到奖励记录", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(smsHomeSignRewardService.deleteSmsHomeSignRewardByIds(ids));
    }
}
