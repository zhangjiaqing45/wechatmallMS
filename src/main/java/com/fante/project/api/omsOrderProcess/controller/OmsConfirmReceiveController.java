package com.fante.project.api.omsOrderProcess.controller;

import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.project.api.omsOrderProcess.domain.OmsOrderReturnApplyParam;
import com.fante.project.api.omsOrderProcess.service.IOmsConfirmReceiveService;
import com.fante.project.api.omsOrderProcess.service.ISmsOrderReturnService;
import com.fante.project.api.umsProcess.service.UmsMemberProcessService;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author wz
 * @Description OmsConfirmReceiveController
 * @CreatTime 2020/4/11
 */
@Api(tags = "OmsConfirmReceiveController", description = "确认收货")
@Controller
@RequestMapping("/api/receive")
public class OmsConfirmReceiveController extends BaseController {
    private static Logger log = LoggerFactory.getLogger(OmsConfirmReceiveController.class);

    @Autowired
    private IOmsConfirmReceiveService iOmsConfirmReceiveService;

    @ApiOperation("确认收货")
    @PostMapping("/confirm")
    @ResponseBody
    public AjaxResult confirmReceiveOrder(Long orderId) {
        return toAjax( iOmsConfirmReceiveService.confirmReceiveOrder(orderId,getTokenUserId()));
    }


}
