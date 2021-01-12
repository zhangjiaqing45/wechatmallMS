package com.fante.project.business.accBalanceRecord.controller;

import com.fante.common.utils.poi.ExcelUtil;
import com.fante.framework.aspectj.lang.annotation.Log;
import com.fante.framework.aspectj.lang.enums.BusinessType;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.framework.web.page.TableDataInfo;
import com.fante.project.business.accBalanceRecord.domain.AccBalanceRecord;
import com.fante.project.business.accBalanceRecord.domain.AccBalanceRecordDTO;
import com.fante.project.business.accBalanceRecord.service.IAccBalanceRecordService;
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
 * 用户余额记录Controller
 *
 * @author fante
 * @date 2020-05-07
 */
@Api(tags = "AccBalanceRecordController", description = "用户余额记录")
@Controller
@RequestMapping("/business/accBalanceRecord")
public class AccBalanceRecordController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(AccBalanceRecordController.class);

    private String prefix = "business/accBalanceRecord";

    @Autowired
    private IAccBalanceRecordService accBalanceRecordService;

    @RequiresPermissions("business:accBalanceRecord:view")
    @GetMapping()
    public String accBalanceRecord(ModelMap map) {
        map.put("isAdmin", getSysUser().isAdmin());
        return prefix + "/accBalanceRecord";
    }

    @ApiOperation("条件查询用户余额记录列表")
    @RequiresPermissions("business:accBalanceRecord:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AccBalanceRecordDTO dto) {
        startPage();
        List<AccBalanceRecordDTO> list = accBalanceRecordService.selectJoinList(dto);
        return getDataTable(list);
    }

    //public TableDataInfo list(AccBalanceRecord accBalanceRecord) {
    //    startPage();
    //    List<AccBalanceRecord> list = accBalanceRecordService.selectAccBalanceRecordList(accBalanceRecord);
    //    return getDataTable(list);
    //}

    @ApiOperation("导出用户余额记录列表")
    @RequiresPermissions("business:accBalanceRecord:export")
    @Log(title = "用户余额记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AccBalanceRecordDTO dto) {
        List<AccBalanceRecordDTO> list = accBalanceRecordService.selectJoinList(dto);
        ExcelUtil<AccBalanceRecordDTO> util = new ExcelUtil<AccBalanceRecordDTO>(AccBalanceRecordDTO. class);
        return util.exportExcel(list, "用户现金明细");
    }

    //public AjaxResult export(AccBalanceRecord accBalanceRecord) {
    //    List<AccBalanceRecord> list = accBalanceRecordService.selectAccBalanceRecordList(accBalanceRecord);
    //    ExcelUtil<AccBalanceRecord> util = new ExcelUtil<AccBalanceRecord>(AccBalanceRecord. class);
    //    return util.exportExcel(list, "accBalanceRecord");
    //}

    @ApiOperation("[跳转] 到新增用户余额记录页面")
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    @ApiOperation("新增保存用户余额记录")
    @RequiresPermissions("business:accBalanceRecord:add")
    @Log(title = "用户余额记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AccBalanceRecord accBalanceRecord) {
        return toAjax(accBalanceRecordService.insertAccBalanceRecord(accBalanceRecord));
    }

    @ApiOperation("[跳转] 到用户余额记录编辑页面")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        AccBalanceRecord accBalanceRecord =accBalanceRecordService.selectAccBalanceRecordById(id);
        mmap.put("accBalanceRecord", accBalanceRecord);
        return prefix + "/edit";
    }

    @ApiOperation("修改保存用户余额记录")
    @RequiresPermissions("business:accBalanceRecord:edit")
    @Log(title = "用户余额记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AccBalanceRecord accBalanceRecord) {
        return toAjax(accBalanceRecordService.updateAccBalanceRecord(accBalanceRecord));
    }

    @ApiOperation("批量删除用户余额记录")
    @RequiresPermissions("business:accBalanceRecord:remove")
    @Log(title = "用户余额记录", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(accBalanceRecordService.deleteAccBalanceRecordByIds(ids));
    }

    @ApiOperation("用户余额记录唯一校验")
    @PostMapping("/checkUnique")
    @ResponseBody
    public String checkUnique(AccBalanceRecord accBalanceRecord) {
        return accBalanceRecordService.checkAccBalanceRecordUnique(accBalanceRecord);
    }

}
