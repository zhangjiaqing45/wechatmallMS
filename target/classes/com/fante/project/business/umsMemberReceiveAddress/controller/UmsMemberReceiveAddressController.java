package com.fante.project.business.umsMemberReceiveAddress.controller;

import java.util.List;

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
import com.fante.project.business.umsMemberReceiveAddress.domain.UmsMemberReceiveAddress;
import com.fante.project.business.umsMemberReceiveAddress.service.IUmsMemberReceiveAddressService;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.common.utils.poi.ExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fante.framework.web.page.TableDataInfo;

/**
 * 会员收货地址Controller
 *
 * @author fante
 * @date 2020-04-10
 */
@Api(tags = "UmsMemberReceiveAddressController", description = "会员收货地址")
@Controller
@RequestMapping("/business/umsMemberReceiveAddress")
public class UmsMemberReceiveAddressController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(UmsMemberReceiveAddressController.class);

    private String prefix = "business/umsMemberReceiveAddress";

    @Autowired
    private IUmsMemberReceiveAddressService umsMemberReceiveAddressService;

    @RequiresPermissions("business:umsMemberReceiveAddress:view")
    @GetMapping()
    public String umsMemberReceiveAddress() {
        return prefix + "/umsMemberReceiveAddress";
    }

    @ApiOperation("条件查询会员收货地址列表")
    @RequiresPermissions("business:umsMemberReceiveAddress:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(UmsMemberReceiveAddress umsMemberReceiveAddress) {
        startPage();
        List<UmsMemberReceiveAddress> list = umsMemberReceiveAddressService.selectUmsMemberReceiveAddressList(umsMemberReceiveAddress);
        return getDataTable(list);
    }

    @ApiOperation("导出会员收货地址列表")
    @RequiresPermissions("business:umsMemberReceiveAddress:export")
    @Log(title = "会员收货地址", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(UmsMemberReceiveAddress umsMemberReceiveAddress) {
        List<UmsMemberReceiveAddress> list = umsMemberReceiveAddressService.selectUmsMemberReceiveAddressList(umsMemberReceiveAddress);
        ExcelUtil<UmsMemberReceiveAddress> util = new ExcelUtil<UmsMemberReceiveAddress>(UmsMemberReceiveAddress. class);
        return util.exportExcel(list, "umsMemberReceiveAddress");
    }

    @ApiOperation("[跳转] 到新增会员收货地址页面")
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    @ApiOperation("新增保存会员收货地址")
    @RequiresPermissions("business:umsMemberReceiveAddress:add")
    @Log(title = "会员收货地址", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(UmsMemberReceiveAddress umsMemberReceiveAddress) {
        return toAjax(umsMemberReceiveAddressService.insertUmsMemberReceiveAddress(umsMemberReceiveAddress));
    }

    @ApiOperation("[跳转] 到会员收货地址编辑页面")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        UmsMemberReceiveAddress umsMemberReceiveAddress =umsMemberReceiveAddressService.selectUmsMemberReceiveAddressById(id);
        mmap.put("umsMemberReceiveAddress", umsMemberReceiveAddress);
        return prefix + "/edit";
    }

    @ApiOperation("修改保存会员收货地址")
    @RequiresPermissions("business:umsMemberReceiveAddress:edit")
    @Log(title = "会员收货地址", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(UmsMemberReceiveAddress umsMemberReceiveAddress) {
        return toAjax(umsMemberReceiveAddressService.updateUmsMemberReceiveAddress(umsMemberReceiveAddress));
    }

    @ApiOperation("批量删除会员收货地址")
    @RequiresPermissions("business:umsMemberReceiveAddress:remove")
    @Log(title = "会员收货地址", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(umsMemberReceiveAddressService.deleteUmsMemberReceiveAddressByIds(ids));
    }

    @ApiOperation("会员收货地址唯一校验")
    @PostMapping("/checkUnique")
    @ResponseBody
    public String checkUnique(UmsMemberReceiveAddress umsMemberReceiveAddress) {
        return umsMemberReceiveAddressService.checkUmsMemberReceiveAddressUnique(umsMemberReceiveAddress);
    }

}
