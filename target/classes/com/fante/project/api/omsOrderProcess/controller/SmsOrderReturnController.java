package com.fante.project.api.omsOrderProcess.controller;

import com.fante.common.utils.Checker;
import com.fante.common.utils.StringUtils;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.framework.web.page.TableDataInfo;
import com.fante.project.api.omsOrderProcess.domain.OmsOrderReturnApplyParam;
import com.fante.project.api.omsOrderProcess.domain.SmsCouponHistoryDetail;
import com.fante.project.api.omsOrderProcess.service.ISmsMemberCouponService;
import com.fante.project.api.omsOrderProcess.service.ISmsOrderReturnService;
import com.fante.project.api.umsProcess.service.UmsMemberProcessService;
import com.fante.project.business.omsOrder.domain.OmsOrder;
import com.fante.project.business.omsOrderReturnApply.domain.OmsOrderReturnApply;
import com.fante.project.business.omsOrderReturnReason.domain.OmsOrderReturnReason;
import com.fante.project.business.umsMember.domain.UmsMember;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author wz
 * @Description SmsMemberCouponController
 * @CreatTime 2020/4/11
 */
@Api(tags = "SmsOrderReturnController", description = "退货")
@Controller
@RequestMapping("/api/return")
public class SmsOrderReturnController extends BaseController {
    private static Logger log = LoggerFactory.getLogger(SmsOrderReturnController.class);

    @Autowired
    private ISmsOrderReturnService iSmsOrderReturnService;
    @Autowired
    private UmsMemberProcessService umsMemberProcessService;

    @ApiOperation("查询用户退货订单信息")
    @PostMapping("/list")
    @ResponseBody
    public AjaxResult list() {
        startPage();
        List<OmsOrderReturnApply> list = iSmsOrderReturnService.getMemberReturnApply(getTokenUserId());
        boolean canLoad = new PageInfo(list).isHasNextPage();
        return AjaxResult.success()
                .put("applylist",list)
                .put("canLoad", canLoad);
    }

    @ApiOperation("申请退货")
    @PostMapping("/apply")
    @ResponseBody
    public AjaxResult apply(OmsOrderReturnApplyParam param) {
        UmsMember member = umsMemberProcessService.get(getTokenClientId());
        param.setMember(member);
        return AjaxResult.success(iSmsOrderReturnService.applyForReturn(param));
    }

    @ApiOperation("更新退货信息")
    @PostMapping("/confirm")
    @ResponseBody
    public AjaxResult updateReturnApply(OmsOrderReturnApplyParam param) {
        UmsMember member = umsMemberProcessService.get(getTokenClientId());
        param.setMember(member);
        return AjaxResult.success(iSmsOrderReturnService.updateReturnApply(param));
    }

    @ApiOperation("退货原因")
    @PostMapping("/reason")
    @ResponseBody
    public AjaxResult reason(Long shopId) {
        startPage();
        List<OmsOrderReturnReason> reason = iSmsOrderReturnService.getReturnReason(shopId);
        return AjaxResult.success(reason);
    }

}
