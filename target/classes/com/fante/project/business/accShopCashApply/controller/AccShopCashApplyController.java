package com.fante.project.business.accShopCashApply.controller;

import com.fante.common.utils.Checker;
import com.fante.common.utils.poi.ExcelUtil;
import com.fante.framework.aspectj.lang.annotation.Log;
import com.fante.framework.aspectj.lang.enums.BusinessType;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.framework.web.page.TableDataInfo;
import com.fante.project.business.accShopCashApply.domain.AccShopCashApply;
import com.fante.project.business.accShopCashApply.domain.AccShopCashApplyDTO;
import com.fante.project.business.accShopCashApply.service.IAccShopCashApplyService;
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

import java.math.BigDecimal;
import java.util.List;

/**
 * 店铺提现记录Controller
 *
 * @author fante
 * @date 2020-05-07
 */
@Api(tags = "AccShopCashApplyController", description = "店铺提现记录")
@Controller
@RequestMapping("/business/accShopCashApply")
public class AccShopCashApplyController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(AccShopCashApplyController.class);

    private String prefix = "business/accShopCashApply";

    @Autowired
    private IAccShopCashApplyService accShopCashApplyService;

    @RequiresPermissions("business:accShopCashApply:view")
    @GetMapping()
    public String accShopCashApply(ModelMap map) {
        //map.put("isAdmin", getSysUser().isAdmin());
        return prefix + "/accShopCashApply";
    }

    @ApiOperation("条件查询店铺提现记录列表")
    @RequiresPermissions("business:accShopCashApply:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AccShopCashApplyDTO dto) {
        startPage();
        List<AccShopCashApplyDTO> list = accShopCashApplyService.selectJoinList(dto);
        return getDataTable(list);
    }

    //public TableDataInfo list(AccShopCashApply accShopCashApply) {
    //    startPage();
    //    List<AccShopCashApply> list = accShopCashApplyService.selectAccShopCashApplyList(accShopCashApply);
    //    return getDataTable(list);
    //}

    @ApiOperation("导出店铺提现记录列表")
    @RequiresPermissions("business:accShopCashApply:export")
    @Log(title = "店铺提现记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AccShopCashApplyDTO dto) {
        List<AccShopCashApplyDTO> list = accShopCashApplyService.selectJoinList(dto);
        ExcelUtil<AccShopCashApplyDTO> util = new ExcelUtil<AccShopCashApplyDTO>(AccShopCashApplyDTO. class);
        return util.exportExcel(list, "店铺提现记录");
    }

    //public AjaxResult export(AccShopCashApply accShopCashApply) {
    //    List<AccShopCashApply> list = accShopCashApplyService.selectAccShopCashApplyList(accShopCashApply);
    //    ExcelUtil<AccShopCashApply> util = new ExcelUtil<AccShopCashApply>(AccShopCashApply. class);
    //    return util.exportExcel(list, "accShopCashApply");
    //}

    @ApiOperation("[跳转] 到新增店铺提现记录页面")
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    @ApiOperation("新增保存店铺提现记录")
    @RequiresPermissions("business:accShopCashApply:add")
    @Log(title = "店铺提现记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AccShopCashApply accShopCashApply) {
        return toAjax(accShopCashApplyService.insertAccShopCashApply(accShopCashApply));
    }

    @ApiOperation("[跳转] 到店铺提现记录编辑页面")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        AccShopCashApply accShopCashApply =accShopCashApplyService.selectAccShopCashApplyById(id);
        mmap.put("accShopCashApply", accShopCashApply);
        return prefix + "/edit";
    }

    @ApiOperation("修改保存店铺提现记录")
    @RequiresPermissions("business:accShopCashApply:edit")
    @Log(title = "店铺提现记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AccShopCashApply accShopCashApply) {
        return toAjax(accShopCashApplyService.updateAccShopCashApply(accShopCashApply));
    }

    @ApiOperation("批量删除店铺提现记录")
    @RequiresPermissions("business:accShopCashApply:remove")
    @Log(title = "店铺提现记录", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(accShopCashApplyService.deleteAccShopCashApplyByIds(ids));
    }

    @ApiOperation("店铺提现记录唯一校验")
    @PostMapping("/checkUnique")
    @ResponseBody
    public String checkUnique(AccShopCashApply accShopCashApply) {
        return accShopCashApplyService.checkAccShopCashApplyUnique(accShopCashApply);
    }

    @ApiOperation("获取可提现金额")
    @RequiresPermissions("business:accShopCashApply:apply")
    @PostMapping("/getWithdraw")
    @ResponseBody
    public AjaxResult getWithdraw() {
        User user = getSysUser();
        Checker.checkOp(user.isFranchisee(), "登录用户非店铺用户");
        return AjaxResult.success().put("withdraw", accShopCashApplyService.getWithdrawable(user.getDeptId()));
    }

    @ApiOperation("提现申请")
    @RequiresPermissions("business:accShopCashApply:apply")
    @PostMapping("/apply")
    @ResponseBody
    public AjaxResult applyCash(BigDecimal money) {
        User user = getSysUser();
        Checker.checkOp(user.isFranchisee(), "登录用户非店铺用户");
        return toAjax(accShopCashApplyService.applyCash(user.getDeptId(), money));
    }


}
