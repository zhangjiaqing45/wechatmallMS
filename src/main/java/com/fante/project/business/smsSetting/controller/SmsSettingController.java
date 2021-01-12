package com.fante.project.business.smsSetting.controller;

import com.fante.framework.aspectj.lang.annotation.Log;
import com.fante.framework.aspectj.lang.enums.BusinessType;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.project.business.smsSetting.domain.SmsSetting;
import com.fante.project.business.smsSetting.service.ISmsSettingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * 营销设置(优惠券、团购、签到、秒杀、积分商城是否开启)Controller
 *
 * @author fante
 * @date 2020-03-10
 */
@Api(tags = "SmsSettingController", description = "营销设置")
@Controller
@RequestMapping("/business/smsSetting")
public class SmsSettingController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(SmsSettingController.class);

    private String prefix = "business/smsSetting";

    @Autowired
    private ISmsSettingService smsSettingService;

    @RequiresPermissions("business:smsSetting:view")
    @GetMapping()
    public String smsSetting(ModelMap modelMap) {
        SmsSetting smsSetting = smsSettingService.selectSmsSettingRecent();
        modelMap.put("smsSetting",smsSetting);
        return prefix + "/smsSetting";
    }

    @ApiOperation("新增营销设置")
    @RequiresPermissions("business:smsSetting:add")
    @Log(title = "营销设置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SmsSetting smsSetting) {
        return toAjax(smsSettingService.insertSmsSetting(smsSetting));
    }

    //@ApiOperation("条件查询营销设置(优惠券、团购、签到、秒杀、积分商城是否开启)列表")
    //@RequiresPermissions("business:smsSetting:list")
    //@PostMapping("/list")
    //@ResponseBody
    //public TableDataInfo list(SmsSetting smsSetting) {
    //    startPage();
    //    List<SmsSetting> list = smsSettingService.selectSmsSettingList(smsSetting);
    //    return getDataTable(list);
    //}
    //
    //@ApiOperation("导出营销设置(优惠券、团购、签到、秒杀、积分商城是否开启)列表")
    //@RequiresPermissions("business:smsSetting:export")
    //@Log(title = "营销设置(优惠券、团购、签到、秒杀、积分商城是否开启)", businessType = BusinessType.EXPORT)
    //@PostMapping("/export")
    //@ResponseBody
    //public AjaxResult export(SmsSetting smsSetting) {
    //    List<SmsSetting> list = smsSettingService.selectSmsSettingList(smsSetting);
    //    ExcelUtil<SmsSetting> util = new ExcelUtil<SmsSetting>(SmsSetting. class);
    //    return util.exportExcel(list, "smsSetting");
    //}

    //@ApiOperation("[跳转] 到新增营销设置(优惠券、团购、签到、秒杀、积分商城是否开启)页面")
    //@GetMapping("/add")
    //public String add() {
    //    return prefix + "/add";
    //}

    //@ApiOperation("[跳转] 到营销设置(优惠券、团购、签到、秒杀、积分商城是否开启)编辑页面")
    //@GetMapping("/edit/{id}")
    //public String edit(@PathVariable("id") Long id, ModelMap mmap) {
    //    SmsSetting smsSetting =smsSettingService.selectSmsSettingById(id);
    //    mmap.put("smsSetting", smsSetting);
    //    return prefix + "/edit";
    //}
    //
    //@ApiOperation("修改保存营销设置(优惠券、团购、签到、秒杀、积分商城是否开启)")
    //@RequiresPermissions("business:smsSetting:edit")
    //@Log(title = "营销设置(优惠券、团购、签到、秒杀、积分商城是否开启)", businessType = BusinessType.UPDATE)
    //@PostMapping("/edit")
    //@ResponseBody
    //public AjaxResult editSave(SmsSetting smsSetting) {
    //    return toAjax(smsSettingService.updateSmsSetting(smsSetting));
    //}
    //
    //@ApiOperation("批量删除营销设置(优惠券、团购、签到、秒杀、积分商城是否开启)")
    //@RequiresPermissions("business:smsSetting:remove")
    //@Log(title = "营销设置(优惠券、团购、签到、秒杀、积分商城是否开启)", businessType = BusinessType.DELETE)
    //@PostMapping("/remove")
    //@ResponseBody
    //public AjaxResult remove(String ids) {
    //    return toAjax(smsSettingService.deleteSmsSettingByIds(ids));
    //}
}
