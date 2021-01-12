package com.fante.project.business.pmsProductAuditLog.controller;

import java.util.List;

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
import com.fante.project.business.pmsProductAuditLog.domain.PmsProductAuditLog;
import com.fante.project.business.pmsProductAuditLog.service.IPmsProductAuditLogService;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.common.utils.poi.ExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fante.framework.web.page.TableDataInfo;

/**
 * 商品审核日志Controller
 *
 * @author fante
 * @date 2020-03-19
 */
@Api(tags = "PmsProductAuditLogController", description = "商品审核日志")
@Controller
@RequestMapping("/business/pmsProductAuditLog")
public class PmsProductAuditLogController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(PmsProductAuditLogController.class);

    private String prefix = "business/pmsProductAuditLog";

    @Autowired
    private IPmsProductAuditLogService pmsProductAuditLogService;

    @RequiresPermissions("business:pmsProductAuditLog:view")
    @GetMapping()
    public String pmsProductAuditLog() {
        return prefix + "/pmsProductAuditLog";
    }

    @ApiOperation("条件查询商品审核日志列表")
    @RequiresPermissions("business:pmsProductAuditLog:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PmsProductAuditLog pmsProductAuditLog) {
        startPage();
        List<PmsProductAuditLog> list = pmsProductAuditLogService.selectPmsProductAuditLogList(pmsProductAuditLog);
        return getDataTable(list);
    }

    @ApiOperation("导出商品审核日志列表")
    @RequiresPermissions("business:pmsProductAuditLog:export")
    @Log(title = "商品审核日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PmsProductAuditLog pmsProductAuditLog) {
        List<PmsProductAuditLog> list = pmsProductAuditLogService.selectPmsProductAuditLogList(pmsProductAuditLog);
        ExcelUtil<PmsProductAuditLog> util = new ExcelUtil<PmsProductAuditLog>(PmsProductAuditLog. class);
        return util.exportExcel(list, "pmsProductAuditLog");
    }

    @ApiOperation("[跳转] 到新增商品审核日志页面")
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    @ApiOperation("新增保存商品审核日志")
    @RequiresPermissions("business:pmsProductAuditLog:add")
    @Log(title = "商品审核日志", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PmsProductAuditLog pmsProductAuditLog) {
        return toAjax(pmsProductAuditLogService.insertPmsProductAuditLog(pmsProductAuditLog));
    }

    @ApiOperation("[跳转] 到商品审核日志编辑页面")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        PmsProductAuditLog pmsProductAuditLog =pmsProductAuditLogService.selectPmsProductAuditLogById(id);
        mmap.put("pmsProductAuditLog", pmsProductAuditLog);
        return prefix + "/edit";
    }

    @ApiOperation("修改保存商品审核日志")
    @RequiresPermissions("business:pmsProductAuditLog:edit")
    @Log(title = "商品审核日志", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PmsProductAuditLog pmsProductAuditLog) {
        return toAjax(pmsProductAuditLogService.updatePmsProductAuditLog(pmsProductAuditLog));
    }

    @ApiOperation("批量删除商品审核日志")
    @RequiresPermissions("business:pmsProductAuditLog:remove")
    @Log(title = "商品审核日志", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(pmsProductAuditLogService.deletePmsProductAuditLogByIds(ids));
    }

    @ApiOperation("商品审核日志唯一校验")
    @PostMapping("/checkUnique")
    @ResponseBody
    public String checkUnique(PmsProductAuditLog pmsProductAuditLog) {
        return pmsProductAuditLogService.checkPmsProductAuditLogUnique(pmsProductAuditLog);
    }

}
