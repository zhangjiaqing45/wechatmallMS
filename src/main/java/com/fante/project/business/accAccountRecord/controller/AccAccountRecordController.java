package com.fante.project.business.accAccountRecord.controller;

import com.fante.common.utils.poi.ExcelUtil;
import com.fante.framework.aspectj.lang.annotation.Log;
import com.fante.framework.aspectj.lang.enums.BusinessType;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.framework.web.page.TableDataInfo;
import com.fante.project.business.accAccountRecord.domain.AccAccountRecord;
import com.fante.project.business.accAccountRecord.domain.AccAccountRecordDTO;
import com.fante.project.business.accAccountRecord.service.IAccAccountRecordService;
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
 * 账户出入账记录Controller
 *
 * @author fante
 * @date 2020-05-07
 */
@Api(tags = "AccAccountRecordController", description = "账户出入账记录")
@Controller
@RequestMapping("/business/accAccountRecord")
public class AccAccountRecordController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(AccAccountRecordController.class);

    private String prefix = "business/accAccountRecord";

    @Autowired
    private IAccAccountRecordService accAccountRecordService;

    @RequiresPermissions("business:accAccountRecord:view")
    @GetMapping()
    public String accAccountRecord(ModelMap map) {
        map.put("isAdmin", getSysUser().isAdmin());
        return prefix + "/accAccountRecord";
    }

    @ApiOperation("条件查询账户出入账记录列表")
    @RequiresPermissions("business:accAccountRecord:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AccAccountRecordDTO dto) {
        startPage();
        List<AccAccountRecordDTO> list = accAccountRecordService.selectJoinList(dto);
        return getDataTable(list);
    }

    //public TableDataInfo list(AccAccountRecord accAccountRecord) {
    //    startPage();
    //    List<AccAccountRecord> list = accAccountRecordService.selectAccAccountRecordList(accAccountRecord);
    //    return getDataTable(list);
    //}

    @ApiOperation("导出账户出入账记录列表")
    @RequiresPermissions("business:accAccountRecord:export")
    @Log(title = "账户出入账记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AccAccountRecordDTO dto) {
        List<AccAccountRecordDTO> list = accAccountRecordService.selectJoinList(dto);
        ExcelUtil<AccAccountRecordDTO> util = new ExcelUtil<AccAccountRecordDTO>(AccAccountRecordDTO. class);
        return util.exportExcel(list, "出入账记录");
    }

    //public AjaxResult export(AccAccountRecord accAccountRecord) {
    //    List<AccAccountRecord> list = accAccountRecordService.selectAccAccountRecordList(accAccountRecord);
    //    ExcelUtil<AccAccountRecord> util = new ExcelUtil<AccAccountRecord>(AccAccountRecord. class);
    //    return util.exportExcel(list, "accAccountRecord");
    //}

    @ApiOperation("[跳转] 到新增账户出入账记录页面")
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    @ApiOperation("新增保存账户出入账记录")
    @RequiresPermissions("business:accAccountRecord:add")
    @Log(title = "账户出入账记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AccAccountRecord accAccountRecord) {
        return toAjax(accAccountRecordService.insertAccAccountRecord(accAccountRecord));
    }

    @ApiOperation("[跳转] 到账户出入账记录编辑页面")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        AccAccountRecord accAccountRecord =accAccountRecordService.selectAccAccountRecordById(id);
        mmap.put("accAccountRecord", accAccountRecord);
        return prefix + "/edit";
    }

    @ApiOperation("修改保存账户出入账记录")
    @RequiresPermissions("business:accAccountRecord:edit")
    @Log(title = "账户出入账记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AccAccountRecord accAccountRecord) {
        return toAjax(accAccountRecordService.updateAccAccountRecord(accAccountRecord));
    }

    @ApiOperation("批量删除账户出入账记录")
    @RequiresPermissions("business:accAccountRecord:remove")
    @Log(title = "账户出入账记录", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(accAccountRecordService.deleteAccAccountRecordByIds(ids));
    }

    @ApiOperation("核算平台欠我多少钱")
    @RequiresPermissions("business:accAccountRecord:list")
    @PostMapping("/countCash")
    @ResponseBody
    public AjaxResult sumCashAccountMoney() {
        return AjaxResult.success().put("cash",accAccountRecordService.sumCashAccountMoney(getSysUser().getDeptId()));
    }

    @ApiOperation("核算我欠合伙人等多少钱")
    @RequiresPermissions("business:accAccountRecord:list")
    @PostMapping("/countCommission")
    @ResponseBody
    public AjaxResult sumCommissionAccountMoney() {
        return AjaxResult.success().put("commission",accAccountRecordService.sumCommissionAccountMoney(getSysUser().getDeptId()));
    }

    @ApiOperation("账户出入账记录唯一校验")
    @PostMapping("/checkUnique")
    @ResponseBody
    public String checkUnique(AccAccountRecord accAccountRecord) {
        return accAccountRecordService.checkAccAccountRecordUnique(accAccountRecord);
    }

}
