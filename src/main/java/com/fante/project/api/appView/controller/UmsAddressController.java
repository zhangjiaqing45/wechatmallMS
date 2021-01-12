package com.fante.project.api.appView.controller;

import com.fante.common.business.enums.UmsAddressConst;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.project.api.appView.domain.PmsProductCommentParam;
import com.fante.project.api.appView.service.PmsCommentService;
import com.fante.project.api.appView.service.UmsAddressService;
import com.fante.project.business.pmsProductComment.domain.PmsProductComment;
import com.fante.project.business.umsMemberReceiveAddress.domain.UmsMemberReceiveAddress;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Date: 2020/4/22 10:54
 * @Author: wz
 * @Description: 用户地址
 */
@Api(tags = "UmsAddressController", description = "用户地址")
@Controller
@RequestMapping("/api/address")
public class UmsAddressController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(UmsAddressController.class);

    @Autowired
    com.fante.project.api.appView.service.UmsAddressService umsAddressService;


    @ApiOperation("查询用户地址")
    @PostMapping("/list")
    @ResponseBody
    public AjaxResult member() {
        return AjaxResult.success().put("addressList",umsAddressService.getMemberAddressList(getTokenUserId()));
    }

    @ApiOperation("添加用户地址")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult add(UmsMemberReceiveAddress param) {
        param.setMemberId(getTokenUserId());
        return toAjax(umsAddressService.addReceiveAddress(param));
    }


    @ApiOperation("删除用户地址")
    @PostMapping("/delete")
    @ResponseBody
    public AjaxResult delReceiveAddress(Long addressId) {
        return toAjax(umsAddressService.delReceiveAddress(addressId,getTokenUserId()));
    }

    @ApiOperation("设置默认用户地址")
    @PostMapping("/default")
    @ResponseBody
    public AjaxResult setDefaultAddress(Long addressId) {
        return toAjax(umsAddressService.setDefaultAddress(addressId,getTokenUserId()));
    }

    @ApiOperation("编辑地址")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult edit(UmsMemberReceiveAddress param) {
        param.setMemberId(getTokenUserId());
        return toAjax(umsAddressService.editReceiveAddress(param));
    }

    @ApiOperation("根据id查询用户地址")
    @PostMapping("/get")
    @ResponseBody
    public AjaxResult getAddress(Long id) {
        return AjaxResult.success(umsAddressService.getAddress(id,getTokenUserId()));
    }
}
