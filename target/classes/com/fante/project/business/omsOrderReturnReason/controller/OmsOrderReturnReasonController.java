package com.fante.project.business.omsOrderReturnReason.controller;

import java.util.List;

import com.fante.common.utils.Checker;
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
import com.fante.project.business.omsOrderReturnReason.domain.OmsOrderReturnReason;
import com.fante.project.business.omsOrderReturnReason.service.IOmsOrderReturnReasonService;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.common.utils.poi.ExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fante.framework.web.page.TableDataInfo;

/**
 * 退货原因设置Controller
 *
 * @author fante
 * @date 2020-03-27
 */
@Api(tags = "OmsOrderReturnReasonController", description = "退货原因设置")
@Controller
@RequestMapping("/business/omsOrderReturnReason")
public class OmsOrderReturnReasonController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(OmsOrderReturnReasonController.class);

    private String prefix = "business/omsOrderReturnReason";

    @Autowired
    private IOmsOrderReturnReasonService omsOrderReturnReasonService;

    @RequiresPermissions("business:omsOrderReturnReason:view")
    @GetMapping()
    public String omsOrderReturnReason() {
        return prefix + "/omsOrderReturnReason";
    }

    @ApiOperation("条件查询退货原因设置列表")
    @RequiresPermissions("business:omsOrderReturnReason:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(OmsOrderReturnReason omsOrderReturnReason) {
        User user= getSysUser();
        if (user.isFranchisee()){
            omsOrderReturnReason.setShopId(user.getDeptId());
        }
        startPage();
        List<OmsOrderReturnReason> list = omsOrderReturnReasonService.selectOmsOrderReturnReasonList(omsOrderReturnReason);
        return getDataTable(list);
    }

    @ApiOperation("导出退货原因设置列表")
    @RequiresPermissions("business:omsOrderReturnReason:export")
    @Log(title = "退货原因设置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OmsOrderReturnReason omsOrderReturnReason) {
        User user= getSysUser();
        if (user.isFranchisee()){
            omsOrderReturnReason.setShopId(user.getDeptId());
        }
        List<OmsOrderReturnReason> list = omsOrderReturnReasonService.selectOmsOrderReturnReasonList(omsOrderReturnReason);
        ExcelUtil<OmsOrderReturnReason> util = new ExcelUtil<OmsOrderReturnReason>(OmsOrderReturnReason. class);
        return util.exportExcel(list, "omsOrderReturnReason");
    }

    @ApiOperation("[跳转] 到新增退货原因设置页面")
    @GetMapping("/add")
    public String add() {
        User user = getSysUser();
        Checker.checkOp(user.isFranchisee(),"登录用户非店铺用户");
        return prefix + "/add";
    }

    @ApiOperation("新增保存退货原因设置")
    @RequiresPermissions("business:omsOrderReturnReason:add")
    @Log(title = "退货原因设置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(OmsOrderReturnReason omsOrderReturnReason) {
        User user = getSysUser();
        if (user.isFranchisee()){
            omsOrderReturnReason.setShopId(user.getDeptId());
            omsOrderReturnReason.setCreateBy(user.getLoginName());
        }
        return toAjax(omsOrderReturnReasonService.insertOmsOrderReturnReason(omsOrderReturnReason));
    }

    @ApiOperation("[跳转] 到退货原因设置编辑页面")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        User user = getSysUser();
        Checker.checkOp(user.isFranchisee(),"登录用户非店铺用户");
        OmsOrderReturnReason omsOrderReturnReason =omsOrderReturnReasonService.selectOmsOrderReturnReasonById(id);
        mmap.put("omsOrderReturnReason", omsOrderReturnReason);
        return prefix + "/edit";
    }

    @ApiOperation("修改保存退货原因设置")
    @RequiresPermissions("business:omsOrderReturnReason:edit")
    @Log(title = "退货原因设置", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(OmsOrderReturnReason omsOrderReturnReason) {
        return toAjax(omsOrderReturnReasonService.updateOmsOrderReturnReason(omsOrderReturnReason));
    }

    @ApiOperation("批量删除退货原因设置")
    @RequiresPermissions("business:omsOrderReturnReason:remove")
    @Log(title = "退货原因设置", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(omsOrderReturnReasonService.deleteOmsOrderReturnReasonByIds(ids));
    }

    @ApiOperation("退货原因设置唯一校验")
    @PostMapping("/checkUnique")
    @ResponseBody
    public String checkUnique(OmsOrderReturnReason omsOrderReturnReason) {
        //同一个店铺验证不唯一
        omsOrderReturnReason.setShopId(getSysUser().getDeptId());
        return omsOrderReturnReasonService.checkOmsOrderReturnReasonUnique(omsOrderReturnReason);
    }

}
