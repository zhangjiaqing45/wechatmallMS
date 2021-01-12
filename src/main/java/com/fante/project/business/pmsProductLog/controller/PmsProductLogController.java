package com.fante.project.business.pmsProductLog.controller;

import java.util.List;

import com.fante.common.utils.StringUtils;
import com.fante.project.system.user.domain.User;
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
import com.fante.project.business.pmsProductLog.domain.PmsProductLog;
import com.fante.project.business.pmsProductLog.service.IPmsProductLogService;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.common.utils.poi.ExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fante.framework.web.page.TableDataInfo;

/**
 * 商品操作日志Controller
 *
 * @author fante
 * @date 2020-03-21
 */
@Api(tags = "PmsProductLogController", description = "商品操作日志")
@Controller
@RequestMapping("/business/pmsProductLog")
public class PmsProductLogController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(PmsProductLogController.class);

    private String prefix = "business/pmsProductLog";

    @Autowired
    private IPmsProductLogService pmsProductLogService;

    @RequiresPermissions("business:pmsProductLog:view")
    @GetMapping()
    public String pmsProductLog(ModelMap mmap) {
        mmap.put("isAdmin",getSysUser().isAdmin() || getSysUser().isSystem());
        return prefix + "/pmsProductLog";
    }

    @ApiOperation("条件查询商品操作日志列表")
    @RequiresPermissions("business:pmsProductLog:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PmsProductLog pmsProductLog) {
        User user = getSysUser();
        if (user.isFranchisee()) {
            pmsProductLog.setShopId(user.getDeptId());
            pmsProductLog.setShopName(StringUtils.EMPTY);
        }
        startPage();
        List<PmsProductLog> list = pmsProductLogService.selectPmsProductLogList(pmsProductLog);
        return getDataTable(list);
    }

    @ApiOperation("导出商品操作日志列表")
    @RequiresPermissions("business:pmsProductLog:export")
    @Log(title = "商品操作日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PmsProductLog pmsProductLog) {
        User user = getSysUser();
        if (user.isFranchisee()) {
            pmsProductLog.setShopId(user.getDeptId());
        }
        List<PmsProductLog> list = pmsProductLogService.selectPmsProductLogList(pmsProductLog);
        ExcelUtil<PmsProductLog> util = new ExcelUtil<PmsProductLog>(PmsProductLog. class);
        return util.exportExcel(list, "pmsProductLog");
    }

    @ApiOperation("[跳转] 到新增商品操作日志页面")
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    @ApiOperation("新增保存商品操作日志")
    @RequiresPermissions("business:pmsProductLog:add")
    @Log(title = "商品操作日志", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PmsProductLog pmsProductLog) {
        return toAjax(pmsProductLogService.insertPmsProductLog(pmsProductLog));
    }

    @ApiOperation("[跳转] 到商品操作日志编辑页面")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        PmsProductLog pmsProductLog =pmsProductLogService.selectPmsProductLogById(id);
        mmap.put("pmsProductLog", pmsProductLog);
        return prefix + "/edit";
    }

    @ApiOperation("修改保存商品操作日志")
    @RequiresPermissions("business:pmsProductLog:edit")
    @Log(title = "商品操作日志", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PmsProductLog pmsProductLog) {
        return toAjax(pmsProductLogService.updatePmsProductLog(pmsProductLog));
    }

    @ApiOperation("批量删除商品操作日志")
    @RequiresPermissions("business:pmsProductLog:remove")
    @Log(title = "商品操作日志", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(pmsProductLogService.deletePmsProductLogByIds(ids));
    }

    @ApiOperation("商品操作日志唯一校验")
    @PostMapping("/checkUnique")
    @ResponseBody
    public String checkUnique(PmsProductLog pmsProductLog) {
        return pmsProductLogService.checkPmsProductLogUnique(pmsProductLog);
    }

}
