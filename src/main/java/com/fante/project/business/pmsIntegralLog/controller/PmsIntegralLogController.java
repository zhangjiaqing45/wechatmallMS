package com.fante.project.business.pmsIntegralLog.controller;

import com.fante.common.utils.poi.ExcelUtil;
import com.fante.framework.aspectj.lang.annotation.Log;
import com.fante.framework.aspectj.lang.enums.BusinessType;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.framework.web.page.TableDataInfo;
import com.fante.project.business.pmsIntegralLog.domain.PmsIntegralLog;
import com.fante.project.business.pmsIntegralLog.domain.PmsIntegralLogDTO;
import com.fante.project.business.pmsIntegralLog.service.IPmsIntegralLogService;
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
 * 积分兑换记录Controller
 *
 * @author fante
 * @date 2020-05-01
 */
@Api(tags = "PmsIntegralLogController", description = "积分兑换记录")
@Controller
@RequestMapping("/business/pmsIntegralLog")
public class PmsIntegralLogController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(PmsIntegralLogController.class);

    private String prefix = "business/pmsIntegralLog";

    @Autowired
    private IPmsIntegralLogService pmsIntegralLogService;

    @RequiresPermissions("business:pmsIntegralLog:view")
    @GetMapping()
    public String pmsIntegralLog(ModelMap map) {
        map.put("isAdmin", getSysUser().isAdmin());
        return prefix + "/pmsIntegralLog";
    }

    @ApiOperation("条件查询积分兑换记录列表")
    @RequiresPermissions("business:pmsIntegralLog:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PmsIntegralLogDTO dto) {
        startPage();
        List<PmsIntegralLogDTO> list = pmsIntegralLogService.selectJoinList(dto);
        return getDataTable(list);
    }
    //public TableDataInfo list(PmsIntegralLog pmsIntegralLog) {
    //    startPage();
    //    List<PmsIntegralLog> list = pmsIntegralLogService.selectPmsIntegralLogList(pmsIntegralLog);
    //    return getDataTable(list);
    //}

    @ApiOperation("导出积分兑换记录列表")
    @RequiresPermissions("business:pmsIntegralLog:export")
    @Log(title = "积分兑换记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PmsIntegralLogDTO dto) {
        List<PmsIntegralLogDTO> list = pmsIntegralLogService.selectJoinList(dto);
        ExcelUtil<PmsIntegralLogDTO> util = new ExcelUtil<PmsIntegralLogDTO>(PmsIntegralLogDTO. class);
        return util.exportExcel(list, "积分记录");
    }

    //public AjaxResult export(PmsIntegralLog pmsIntegralLog) {
    //    List<PmsIntegralLog> list = pmsIntegralLogService.selectPmsIntegralLogList(pmsIntegralLog);
    //    ExcelUtil<PmsIntegralLog> util = new ExcelUtil<PmsIntegralLog>(PmsIntegralLog. class);
    //    return util.exportExcel(list, "pmsIntegralLog");
    //}

    @ApiOperation("[跳转] 到新增积分兑换记录页面")
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    @ApiOperation("新增保存积分兑换记录")
    @RequiresPermissions("business:pmsIntegralLog:add")
    @Log(title = "积分兑换记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PmsIntegralLog pmsIntegralLog) {
        return toAjax(pmsIntegralLogService.insertPmsIntegralLog(pmsIntegralLog));
    }

    @ApiOperation("[跳转] 到积分兑换记录编辑页面")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        PmsIntegralLog pmsIntegralLog =pmsIntegralLogService.selectPmsIntegralLogById(id);
        mmap.put("pmsIntegralLog", pmsIntegralLog);
        return prefix + "/edit";
    }

    @ApiOperation("修改保存积分兑换记录")
    @RequiresPermissions("business:pmsIntegralLog:edit")
    @Log(title = "积分兑换记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PmsIntegralLog pmsIntegralLog) {
        return toAjax(pmsIntegralLogService.updatePmsIntegralLog(pmsIntegralLog));
    }

    @ApiOperation("批量删除积分兑换记录")
    @RequiresPermissions("business:pmsIntegralLog:remove")
    @Log(title = "积分兑换记录", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(pmsIntegralLogService.deletePmsIntegralLogByIds(ids));
    }

    @ApiOperation("积分兑换记录唯一校验")
    @PostMapping("/checkUnique")
    @ResponseBody
    public String checkUnique(PmsIntegralLog pmsIntegralLog) {
        return pmsIntegralLogService.checkPmsIntegralLogUnique(pmsIntegralLog);
    }

}
