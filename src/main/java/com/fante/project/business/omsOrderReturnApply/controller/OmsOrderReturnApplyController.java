package com.fante.project.business.omsOrderReturnApply.controller;

import com.fante.common.utils.poi.ExcelUtil;
import com.fante.framework.aspectj.lang.annotation.Log;
import com.fante.framework.aspectj.lang.enums.BusinessType;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.framework.web.page.TableDataInfo;
import com.fante.project.business.omsOrderReturnAddress.domain.OmsOrderReturnAddress;
import com.fante.project.business.omsOrderReturnAddress.service.IOmsOrderReturnAddressService;
import com.fante.project.business.omsOrderReturnApply.domain.OmsOrderReturnApply;
import com.fante.project.business.omsOrderReturnApply.domain.OrderReturnOperationParam;
import com.fante.project.business.omsOrderReturnApply.service.IOmsOrderReturnApplyService;
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

import java.util.Collections;
import java.util.List;

/**
 * 订单退货申请Controller
 *
 * @author fante
 * @date 2020-04-09
 */
@Api(tags = "OmsOrderReturnApplyController", description = "订单退货申请")
@Controller
@RequestMapping("/business/omsOrderReturnApply")
public class OmsOrderReturnApplyController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(OmsOrderReturnApplyController.class);

    private String prefix = "business/omsOrderReturnApply";

    @Autowired
    private IOmsOrderReturnApplyService omsOrderReturnApplyService;
    @Autowired
    private IOmsOrderReturnAddressService iOmsOrderReturnAddressService;

    @RequiresPermissions("business:omsOrderReturnApply:view")
    @GetMapping()
    public String omsOrderReturnApply(ModelMap map) {
        map.put("isAdmin", getSysUser().isAdmin() || getSysUser().isSystem());
        return prefix + "/omsOrderReturnApply";
    }

    @ApiOperation("条件查询订单退货申请列表")
    @RequiresPermissions("business:omsOrderReturnApply:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(OmsOrderReturnApply omsOrderReturnApply) {
        if(getSysUser().isFranchisee()){
            omsOrderReturnApply.setShopId(getSysUser().getDeptId());
        }
        startPage();
        List<OmsOrderReturnApply> list = omsOrderReturnApplyService.selectOmsOrderReturnApplyList(omsOrderReturnApply);
        return getDataTable(list);
    }

    @ApiOperation("导出订单退货申请列表")
    @RequiresPermissions("business:omsOrderReturnApply:export")
    @Log(title = "订单退货申请", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OmsOrderReturnApply omsOrderReturnApply) {
        if(getSysUser().isFranchisee()){
            omsOrderReturnApply.setShopId(getSysUser().getDeptId());
        }
        List<OmsOrderReturnApply> list = omsOrderReturnApplyService.selectOmsOrderReturnApplyList(omsOrderReturnApply);
        ExcelUtil<OmsOrderReturnApply> util = new ExcelUtil<OmsOrderReturnApply>(OmsOrderReturnApply. class);
        return util.exportExcel(list, "omsOrderReturnApply");
    }

    @ApiOperation("新增保存订单退货申请")
    @RequiresPermissions("business:omsOrderReturnApply:add")
    @Log(title = "订单退货申请", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(OmsOrderReturnApply omsOrderReturnApply) {
        return toAjax(omsOrderReturnApplyService.insertOmsOrderReturnApply(omsOrderReturnApply));
    }

    @ApiOperation("[跳转] 到订单退货申请编辑页面")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        User user = getSysUser();
        boolean isFranchisee = user.isFranchisee();
        //将地址信息和退货详情信息返回给页面
        OmsOrderReturnApply omsOrderReturnApply =omsOrderReturnApplyService.selectOmsOrderReturnApplyById(id);

        List<OmsOrderReturnAddress> receiveDatas = Collections.emptyList();
        if (isFranchisee) {
            OmsOrderReturnAddress searchAddr = new OmsOrderReturnAddress();
            searchAddr.setShopId(getSysUser().getDeptId());
            receiveDatas = iOmsOrderReturnAddressService.selectOmsOrderReturnAddressList(searchAddr);
        }
        mmap.put("omsOrderReturnApply", omsOrderReturnApply);
        mmap.put("receiveDatas", receiveDatas);
        mmap.put("isFranchisee", isFranchisee);
        return prefix + "/edit";
    }

    @ApiOperation("修改保存订单退货申请")
    @RequiresPermissions("business:omsOrderReturnApply:edit")
    @Log(title = "订单退货申请", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(OmsOrderReturnApply omsOrderReturnApply) {
        return toAjax(omsOrderReturnApplyService.updateOmsOrderReturnApply(omsOrderReturnApply));
    }

    @ApiOperation("批量删除订单退货申请")
    @RequiresPermissions("business:omsOrderReturnApply:remove")
    @Log(title = "订单退货申请", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(omsOrderReturnApplyService.deleteOmsOrderReturnApplyByIds(ids));
    }

    @ApiOperation("订单退货申请唯一校验")
    @PostMapping("/checkUnique")
    @ResponseBody
    public String checkUnique(OmsOrderReturnApply omsOrderReturnApply) {
        return omsOrderReturnApplyService.checkOmsOrderReturnApplyUnique(omsOrderReturnApply);
    }

    @ApiOperation("确认退货")
    @RequiresPermissions("business:omsOrderReturnApply:edit")
    @Log(title = "订单退货申请", businessType = BusinessType.UPDATE)
    @PostMapping("/confirmReturn")
    @ResponseBody
    public AjaxResult confirmReturn(OrderReturnOperationParam param) {
        return toAjax(omsOrderReturnApplyService.confirmReturn(param));
    }
    @ApiOperation("拒绝退货")
    @RequiresPermissions("business:omsOrderReturnApply:edit")
    @Log(title = "订单退货申请", businessType = BusinessType.UPDATE)
    @PostMapping("/refuseReturn")
    @ResponseBody
    public AjaxResult refuseReturn(OrderReturnOperationParam param) {
        return toAjax(omsOrderReturnApplyService.refuseReturn(param));
    }
    @ApiOperation("确认收货")
    @RequiresPermissions("business:omsOrderReturnApply:edit")
    @Log(title = "订单退货申请", businessType = BusinessType.UPDATE)
    @PostMapping("/confirmReceive")
    @ResponseBody
    public AjaxResult confirmReceive(OrderReturnOperationParam param) {
        return toAjax(omsOrderReturnApplyService.confirmReceive(param));
    }

}
