package com.fante.project.business.wxPayLog.controller;

import com.fante.common.utils.poi.ExcelUtil;
import com.fante.framework.aspectj.lang.annotation.Log;
import com.fante.framework.aspectj.lang.enums.BusinessType;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.framework.web.page.TableDataInfo;
import com.fante.project.business.wxPayLog.domain.WxPayLog;
import com.fante.project.business.wxPayLog.service.IWxPayLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 微信支付日志Controller
 *
 * @author fante
 * @date 2020-02-21
 */
@Controller
@RequestMapping("/business/wxPayLog")
public class WxPayLogController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(WxPayLogController.class);

    private String prefix = "business/wxPayLog";

    @Autowired
    private IWxPayLogService wxPayLogService;

    @RequiresPermissions("business:wxPayLog:view")
    @GetMapping()
    public String wxPayLog(ModelMap map) {
        return prefix + "/wxPayLog";
    }

            /**
         * 查询微信支付日志列表
         */
        @RequiresPermissions("business:wxPayLog:list")
        @PostMapping("/list")
        @ResponseBody
        public TableDataInfo list(WxPayLog wxPayLog) {
            startPage();
            List<WxPayLog> list = wxPayLogService.selectWxPayLogList(wxPayLog);
            return getDataTable(list);
        }
    
    /**
     * 导出微信支付日志列表
     */
    @RequiresPermissions("business:wxPayLog:export")
    @Log(title = "微信支付日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WxPayLog wxPayLog) {
        List<WxPayLog> list = wxPayLogService.selectWxPayLogList(wxPayLog);
        ExcelUtil<WxPayLog> util = new ExcelUtil<WxPayLog>(WxPayLog. class);
        return util.exportExcel(list, "wxPayLog");
    }

            /**
         * 新增微信支付日志
         */
        @GetMapping("/add")
        public String add() {
            return prefix + "/add";
        }
    
    /**
     * 新增保存微信支付日志
     */
    @RequiresPermissions("business:wxPayLog:add")
    @Log(title = "微信支付日志", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WxPayLog wxPayLog) {
        return toAjax(wxPayLogService.insertWxPayLog(wxPayLog));
    }

    /**
     * 修改微信支付日志
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        WxPayLog wxPayLog =wxPayLogService.selectWxPayLogById(id);
        mmap.put("wxPayLog", wxPayLog);
        return prefix + "/edit";
    }

    /**
     * 修改保存微信支付日志
     */
    @RequiresPermissions("business:wxPayLog:edit")
    @Log(title = "微信支付日志", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WxPayLog wxPayLog) {
        return toAjax(wxPayLogService.updateWxPayLog(wxPayLog));
    }

            /**
         * 删除微信支付日志
         */
        @RequiresPermissions("business:wxPayLog:remove")
        @Log(title = "微信支付日志", businessType = BusinessType.DELETE)
        @PostMapping("/remove")
        @ResponseBody
        public AjaxResult remove(String ids) {
            return toAjax(wxPayLogService.deleteWxPayLogByIds(ids));
        }
        }
