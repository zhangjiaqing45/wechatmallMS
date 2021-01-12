package com.fante.project.business.accCommissionRecord.controller;

import com.fante.common.utils.poi.ExcelUtil;
import com.fante.framework.aspectj.lang.annotation.Log;
import com.fante.framework.aspectj.lang.enums.BusinessType;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.framework.web.page.TableDataInfo;
import com.fante.project.business.accCommissionRecord.domain.AccCommissionRecord;
import com.fante.project.business.accCommissionRecord.domain.AccCommissionRecordDTO;
import com.fante.project.business.accCommissionRecord.service.IAccCommissionRecordService;
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
 * 用户佣金记录Controller
 *
 * @author fante
 * @date 2020-05-07
 */
@Api(tags = "AccCommissionRecordController", description = "用户佣金记录")
@Controller
@RequestMapping("/business/accCommissionRecord")
public class AccCommissionRecordController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(AccCommissionRecordController.class);

    private String prefix = "business/accCommissionRecord";

    @Autowired
    private IAccCommissionRecordService accCommissionRecordService;

    @RequiresPermissions("business:accCommissionRecord:view")
    @GetMapping()
    public String accCommissionRecord(ModelMap map) {
        map.put("isAdmin", getSysUser().isAdmin());
        return prefix + "/accCommissionRecord";
    }

    @ApiOperation("条件查询用户佣金记录列表")
    @RequiresPermissions("business:accCommissionRecord:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AccCommissionRecordDTO dto) {
        startPage();
        List<AccCommissionRecordDTO> list = accCommissionRecordService.selectJoinList(dto);
        return getDataTable(list);
    }

    //public TableDataInfo list(AccCommissionRecord accCommissionRecord) {
    //    startPage();
    //    List<AccCommissionRecord> list = accCommissionRecordService.selectAccCommissionRecordList(accCommissionRecord);
    //    return getDataTable(list);
    //}

    @ApiOperation("导出用户佣金记录列表")
    @RequiresPermissions("business:accCommissionRecord:export")
    @Log(title = "用户佣金记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AccCommissionRecordDTO dto) {
        List<AccCommissionRecordDTO> list = accCommissionRecordService.selectJoinList(dto);
        ExcelUtil<AccCommissionRecordDTO> util = new ExcelUtil<AccCommissionRecordDTO>(AccCommissionRecordDTO. class);
        return util.exportExcel(list, "用户佣金记录");
    }

    //public AjaxResult export(AccCommissionRecord accCommissionRecord) {
    //    List<AccCommissionRecord> list = accCommissionRecordService.selectAccCommissionRecordList(accCommissionRecord);
    //    ExcelUtil<AccCommissionRecord> util = new ExcelUtil<AccCommissionRecord>(AccCommissionRecord. class);
    //    return util.exportExcel(list, "accCommissionRecord");
    //}

    @ApiOperation("[跳转] 到新增用户佣金记录页面")
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    @ApiOperation("新增保存用户佣金记录")
    @RequiresPermissions("business:accCommissionRecord:add")
    @Log(title = "用户佣金记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AccCommissionRecord accCommissionRecord) {
        return toAjax(accCommissionRecordService.insertAccCommissionRecord(accCommissionRecord));
    }

    @ApiOperation("[跳转] 到用户佣金记录编辑页面")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        AccCommissionRecord accCommissionRecord =accCommissionRecordService.selectAccCommissionRecordById(id);
        mmap.put("accCommissionRecord", accCommissionRecord);
        return prefix + "/edit";
    }

    @ApiOperation("修改保存用户佣金记录")
    @RequiresPermissions("business:accCommissionRecord:edit")
    @Log(title = "用户佣金记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AccCommissionRecord accCommissionRecord) {
        return toAjax(accCommissionRecordService.updateAccCommissionRecord(accCommissionRecord));
    }

    @ApiOperation("批量删除用户佣金记录")
    @RequiresPermissions("business:accCommissionRecord:remove")
    @Log(title = "用户佣金记录", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(accCommissionRecordService.deleteAccCommissionRecordByIds(ids));
    }

    @ApiOperation("用户佣金记录唯一校验")
    @PostMapping("/checkUnique")
    @ResponseBody
    public String checkUnique(AccCommissionRecord accCommissionRecord) {
        return accCommissionRecordService.checkAccCommissionRecordUnique(accCommissionRecord);
    }

}
