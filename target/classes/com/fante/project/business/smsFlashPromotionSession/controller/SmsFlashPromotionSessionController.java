package com.fante.project.business.smsFlashPromotionSession.controller;

import com.fante.common.business.constant.BizConstants;
import com.fante.common.constant.Constants;
import com.fante.common.utils.Checker;
import com.fante.common.utils.StringUtils;
import com.fante.common.utils.poi.ExcelUtil;
import com.fante.framework.aspectj.lang.annotation.Log;
import com.fante.framework.aspectj.lang.enums.BusinessType;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.framework.web.page.TableDataInfo;
import com.fante.project.business.smsFlashPromotionSession.domain.SmsFlashPromotionSession;
import com.fante.project.business.smsFlashPromotionSession.service.ISmsFlashPromotionSessionService;
import com.fante.project.business.smsSetting.utils.SmsSettingUtils;
import com.fante.project.system.config.service.IConfigService;
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
 * 秒杀活动时间段Controller
 *
 * @author fante
 * @date 2020-03-21
 */
@Api(tags = "SmsFlashPromotionSessionController", description = "秒杀活动时间段")
@Controller
@RequestMapping("/business/smsFlashPromotionSession")
public class SmsFlashPromotionSessionController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(SmsFlashPromotionSessionController.class);

    private String prefix = "business/smsFlashPromotionSession";

    @Autowired
    private ISmsFlashPromotionSessionService smsFlashPromotionSessionService;
    @Autowired
    private IConfigService configService;
    @Autowired
    private SmsSettingUtils smsSettingUtils;

    @RequiresPermissions("business:smsFlashPromotionSession:view")
    @GetMapping()
    public String smsFlashPromotionSession() {
        Checker.check(smsSettingUtils.flashEnable(),"秒杀活动暂未开启");
        return prefix + "/smsFlashPromotionSession";
    }

    @ApiOperation("条件查询秒杀活动时间段列表")
    @RequiresPermissions("business:smsFlashPromotionSession:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SmsFlashPromotionSession smsFlashPromotionSession) {
        startPage();
        List<SmsFlashPromotionSession> list = smsFlashPromotionSessionService.selectSmsFlashPromotionSessionList(smsFlashPromotionSession);
        return getDataTable(list);
    }

    @ApiOperation("导出秒杀活动时间段列表")
    @RequiresPermissions("business:smsFlashPromotionSession:export")
    @Log(title = "秒杀活动时间段", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SmsFlashPromotionSession smsFlashPromotionSession) {
        List<SmsFlashPromotionSession> list = smsFlashPromotionSessionService.selectSmsFlashPromotionSessionList(smsFlashPromotionSession);
        ExcelUtil<SmsFlashPromotionSession> util = new ExcelUtil<SmsFlashPromotionSession>(SmsFlashPromotionSession. class);
        return util.exportExcel(list, "smsFlashPromotionSession");
    }

    @ApiOperation("[跳转] 到新增秒杀活动时间段页面")
    @GetMapping("/add")
    public String add(ModelMap map) {
        Checker.check(smsSettingUtils.flashEnable(),"秒杀活动暂未开启");
        map.put("sessionBegin", StringUtils.defaultString(configService.selectConfigByKey(BizConstants.smsFlash.BIZ_FLASH_SESSION_BEGIN), BizConstants.smsFlash.BIZ_FLASH_SESSION_BEGIN_DEF));
        map.put("sessionEnd", StringUtils.defaultString(configService.selectConfigByKey(BizConstants.smsFlash.BIZ_FLASH_SESSION_END), BizConstants.smsFlash.BIZ_FLASH_SESSION_END_DEF));
        return prefix + "/add";
    }

    @ApiOperation("新增保存秒杀活动时间段")
    @RequiresPermissions("business:smsFlashPromotionSession:add")
    @Log(title = "秒杀活动时间段", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SmsFlashPromotionSession smsFlashPromotionSession) {
        return toAjax(smsFlashPromotionSessionService.insertSmsFlashPromotionSession(smsFlashPromotionSession));
    }

    @ApiOperation("[跳转] 到秒杀活动时间段编辑页面")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap map) {
        Checker.check(smsSettingUtils.flashEnable(),"秒杀活动暂未开启");
        SmsFlashPromotionSession smsFlashPromotionSession =smsFlashPromotionSessionService.selectSmsFlashPromotionSessionById(id);
        map.put("smsFlashPromotionSession", smsFlashPromotionSession);
        map.put("sessionBegin", StringUtils.defaultString(configService.selectConfigByKey(BizConstants.smsFlash.BIZ_FLASH_SESSION_BEGIN), BizConstants.smsFlash.BIZ_FLASH_SESSION_BEGIN_DEF));
        map.put("sessionEnd", StringUtils.defaultString(configService.selectConfigByKey(BizConstants.smsFlash.BIZ_FLASH_SESSION_END), BizConstants.smsFlash.BIZ_FLASH_SESSION_END_DEF));
        return prefix + "/edit";
    }

    @ApiOperation("修改保存秒杀活动时间段")
    @RequiresPermissions("business:smsFlashPromotionSession:edit")
    @Log(title = "秒杀活动时间段", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SmsFlashPromotionSession smsFlashPromotionSession) {
        return toAjax(smsFlashPromotionSessionService.updateSmsFlashPromotionSession(smsFlashPromotionSession));
    }

    @ApiOperation("批量删除秒杀活动时间段")
    @RequiresPermissions("business:smsFlashPromotionSession:remove")
    @Log(title = "秒杀活动时间段", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(smsFlashPromotionSessionService.deleteSmsFlashPromotionSessionByIds(ids));
    }


}
