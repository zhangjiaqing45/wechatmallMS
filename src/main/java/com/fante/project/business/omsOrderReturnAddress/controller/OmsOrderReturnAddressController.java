package com.fante.project.business.omsOrderReturnAddress.controller;

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
import com.fante.project.business.omsOrderReturnAddress.domain.OmsOrderReturnAddress;
import com.fante.project.business.omsOrderReturnAddress.service.IOmsOrderReturnAddressService;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.common.utils.poi.ExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fante.framework.web.page.TableDataInfo;

/**
 * 店铺收发货地址Controller
 *
 * @author fante
 * @date 2020-03-31
 */
@Api(tags = "OmsOrderReturnAddressController", description = "店铺收发货地址")
@Controller
@RequestMapping("/business/omsOrderReturnAddress")
public class OmsOrderReturnAddressController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(OmsOrderReturnAddressController.class);

    private String prefix = "business/omsOrderReturnAddress";

    @Autowired
    private IOmsOrderReturnAddressService omsOrderReturnAddressService;

    @RequiresPermissions("business:omsOrderReturnAddress:view")
    @GetMapping()
    public String omsOrderReturnAddress() {
        return prefix + "/omsOrderReturnAddress";
    }

    @ApiOperation("条件查询店铺收发货地址列表")
    @RequiresPermissions("business:omsOrderReturnAddress:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(OmsOrderReturnAddress omsOrderReturnAddress) {
        User user = getSysUser();
        if (user.isFranchisee()) {
            omsOrderReturnAddress.setShopId(user.getDeptId());
        }
        startPage();
        List<OmsOrderReturnAddress> list = omsOrderReturnAddressService.selectOmsOrderReturnAddressList(omsOrderReturnAddress);
        return getDataTable(list);
    }

    @ApiOperation("导出店铺收发货地址列表")
    @RequiresPermissions("business:omsOrderReturnAddress:export")
    @Log(title = "店铺收发货地址", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OmsOrderReturnAddress omsOrderReturnAddress) {
        List<OmsOrderReturnAddress> list = omsOrderReturnAddressService.selectOmsOrderReturnAddressList(omsOrderReturnAddress);
        ExcelUtil<OmsOrderReturnAddress> util = new ExcelUtil<OmsOrderReturnAddress>(OmsOrderReturnAddress. class);
        return util.exportExcel(list, "omsOrderReturnAddress");
    }

    @ApiOperation("[跳转] 到新增店铺收发货地址页面")
    @GetMapping("/add")
    public String add() {
        Checker.checkOp(getSysUser().isFranchisee(),"登录用户非店铺用户");
        return prefix + "/add";
    }

    @ApiOperation("新增保存店铺收发货地址")
    @RequiresPermissions("business:omsOrderReturnAddress:add")
    @Log(title = "店铺收发货地址", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(OmsOrderReturnAddress omsOrderReturnAddress) {
        return toAjax(omsOrderReturnAddressService.insertOmsOrderReturnAddress(omsOrderReturnAddress));
    }

    @ApiOperation("[跳转] 到店铺收发货地址编辑页面")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        Checker.checkOp(getSysUser().isFranchisee(),"登录用户非店铺用户");
        OmsOrderReturnAddress omsOrderReturnAddress =omsOrderReturnAddressService.selectOmsOrderReturnAddressById(id);
        mmap.put("omsOrderReturnAddress", omsOrderReturnAddress);
        return prefix + "/edit";
    }

    @ApiOperation("设置默认发货地址")
    @RequiresPermissions("business:omsOrderReturnAddress:edit")
    @Log(title = "店铺收发货地址", businessType = BusinessType.UPDATE)
    @PostMapping("/sendEnable")
    @ResponseBody
    public AjaxResult sendEnable(Long id) {
        return toAjax(omsOrderReturnAddressService.sendEnable(id));
    }

    @ApiOperation("设置默认收货地址")
    @RequiresPermissions("business:omsOrderReturnAddress:edit")
    @Log(title = "店铺收发货地址", businessType = BusinessType.UPDATE)
    @PostMapping("/receiveEnable")
    @ResponseBody
    public AjaxResult receiveEnable(Long id) {
        return toAjax(omsOrderReturnAddressService.receiveEnable(id));
    }

    @ApiOperation("修改保存店铺收发货地址")
    @RequiresPermissions("business:omsOrderReturnAddress:edit")
    @Log(title = "店铺收发货地址", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(OmsOrderReturnAddress omsOrderReturnAddress) {
        return toAjax(omsOrderReturnAddressService.updateOmsOrderReturnAddress(omsOrderReturnAddress));
    }

    @ApiOperation("批量删除店铺收发货地址")
    @RequiresPermissions("business:omsOrderReturnAddress:remove")
    @Log(title = "店铺收发货地址", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(omsOrderReturnAddressService.deleteOmsOrderReturnAddressByIds(ids));
    }

    @ApiOperation("店铺收发货地址唯一校验")
    @PostMapping("/checkUnique")
    @ResponseBody
    public String checkUnique(OmsOrderReturnAddress omsOrderReturnAddress) {
        omsOrderReturnAddress.setShopId(getSysUser().getDeptId());
        return omsOrderReturnAddressService.checkOmsOrderReturnAddressUnique(omsOrderReturnAddress);
    }

}
