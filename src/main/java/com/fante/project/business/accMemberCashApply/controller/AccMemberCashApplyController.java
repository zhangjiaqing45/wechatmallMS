package com.fante.project.business.accMemberCashApply.controller;

import com.fante.common.business.enums.AccCashApplyConst;
import com.fante.common.utils.Checker;
import com.fante.common.utils.poi.ExcelUtil;
import com.fante.framework.aspectj.lang.annotation.Log;
import com.fante.framework.aspectj.lang.enums.BusinessType;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.framework.web.page.TableDataInfo;
import com.fante.project.business.accMemberCashApply.domain.AccMemberCashApply;
import com.fante.project.business.accMemberCashApply.domain.AccMemberCashApplyDTO;
import com.fante.project.business.accMemberCashApply.service.IAccMemberCashApplyService;
import com.fante.project.system.user.domain.User;
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
 * 用户提现记录Controller
 *
 * @author fante
 * @date 2020-05-07
 */
@Api(tags = "AccMemberCashApplyController", description = "用户提现记录")
@Controller
@RequestMapping("/business/accMemberCashApply")
public class AccMemberCashApplyController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(AccMemberCashApplyController.class);

    private String prefix = "business/accMemberCashApply";

    @Autowired
    private IAccMemberCashApplyService accMemberCashApplyService;

    @RequiresPermissions("business:accMemberCashApply:view")
    @GetMapping()
    public String accMemberCashApply(ModelMap map) {
        map.put("isAdmin", getSysUser().isAdmin());
        map.put("auditStatus", AccCashApplyConst.AuditType.WAIT.getType());
        return prefix + "/accMemberCashApply";
    }

    @ApiOperation("条件查询用户提现记录列表")
    @RequiresPermissions("business:accMemberCashApply:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AccMemberCashApplyDTO dto) {
        User user = getSysUser();
        if (user.isFranchisee()) {
            dto.setShopId(user.getDeptId());
        }
        startPage();
        List<AccMemberCashApplyDTO> list = accMemberCashApplyService.selectJoinList(dto);
        return getDataTable(list);
    }

    //public TableDataInfo list(AccMemberCashApply accMemberCashApply) {
    //    startPage();
    //    List<AccMemberCashApply> list = accMemberCashApplyService.selectAccMemberCashApplyList(accMemberCashApply);
    //    return getDataTable(list);
    //}

    @ApiOperation("导出用户提现记录列表")
    @RequiresPermissions("business:accMemberCashApply:export")
    @Log(title = "用户提现记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AccMemberCashApplyDTO dto) {
        List<AccMemberCashApplyDTO> list = accMemberCashApplyService.selectJoinList(dto);
        ExcelUtil<AccMemberCashApplyDTO> util = new ExcelUtil<AccMemberCashApplyDTO>(AccMemberCashApplyDTO. class);
        return util.exportExcel(list, "用户提现记录");
    }

    //public AjaxResult export(AccMemberCashApply accMemberCashApply) {
    //    List<AccMemberCashApply> list = accMemberCashApplyService.selectAccMemberCashApplyList(accMemberCashApply);
    //    ExcelUtil<AccMemberCashApply> util = new ExcelUtil<AccMemberCashApply>(AccMemberCashApply. class);
    //    return util.exportExcel(list, "accMemberCashApply");
    //}

    @ApiOperation("[跳转] 到新增用户提现记录页面")
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    @ApiOperation("新增保存用户提现记录")
    @RequiresPermissions("business:accMemberCashApply:add")
    @Log(title = "用户提现记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AccMemberCashApply accMemberCashApply) {
        return toAjax(accMemberCashApplyService.insertAccMemberCashApply(accMemberCashApply));
    }

    @ApiOperation("[跳转] 到用户提现记录编辑页面")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        AccMemberCashApply accMemberCashApply =accMemberCashApplyService.selectAccMemberCashApplyById(id);
        mmap.put("accMemberCashApply", accMemberCashApply);
        return prefix + "/edit";
    }

    @ApiOperation("修改保存用户提现记录")
    @RequiresPermissions("business:accMemberCashApply:edit")
    @Log(title = "用户提现记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AccMemberCashApply accMemberCashApply) {
        return toAjax(accMemberCashApplyService.updateAccMemberCashApply(accMemberCashApply));
    }

    @ApiOperation("批量删除用户提现记录")
    @RequiresPermissions("business:accMemberCashApply:remove")
    @Log(title = "用户提现记录", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(accMemberCashApplyService.deleteAccMemberCashApplyByIds(ids));
    }

    @ApiOperation("用户提现记录唯一校验")
    @PostMapping("/checkUnique")
    @ResponseBody
    public String checkUnique(AccMemberCashApply accMemberCashApply) {
        return accMemberCashApplyService.checkAccMemberCashApplyUnique(accMemberCashApply);
    }

    @ApiOperation("拒绝提现")
    @PostMapping("/refuse")
    @ResponseBody
    public AjaxResult refuseCashApply(Long id, String remark) {
        Checker.checkOp(getSysUser().isFranchisee(), "登录用户非店铺用户");
        return toAjax(accMemberCashApplyService.refuseCashApply(id, remark));
    }

    @ApiOperation("同意提现")
    @PostMapping("/agree")
    @ResponseBody
    public AjaxResult agreeCashApply(Long id, String remark) {
        Checker.checkOp(getSysUser().isFranchisee(), "登录用户非店铺用户");
        return toAjax(accMemberCashApplyService.agreeCashApply(id, remark));
    }

}
