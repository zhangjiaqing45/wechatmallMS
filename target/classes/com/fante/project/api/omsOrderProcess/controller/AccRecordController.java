package com.fante.project.api.omsOrderProcess.controller;

import com.fante.common.business.enums.AccCashApplyConst;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.project.api.omsOrderProcess.service.IAccRecordService;
import com.fante.project.business.accBalanceRecord.domain.AccBalanceRecord;
import com.fante.project.business.accCommissionRecord.domain.AccCommissionRecord;
import com.fante.project.business.accMemberCashApply.domain.AccMemberCashApply;
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
 * @Description AccRecordController
 * @CreatTime 2020/4/11
 */
@Api(tags = "AccRecordController", description = "资金流水记录")
@Controller
@RequestMapping("/api/account")
public class AccRecordController extends BaseController {
    private static Logger log = LoggerFactory.getLogger(AccRecordController.class);
    @Autowired
    private IAccRecordService iAccRecordService;

    @ApiOperation("查询用户佣金流水记录")
    @PostMapping("/commission")
    @ResponseBody
    public AjaxResult memberCommissionList() {
        startPage();
        List<AccCommissionRecord> list = iAccRecordService.getMemberCommissionRecord(getTokenUserId());
        boolean canLoad = new PageInfo(list).isHasNextPage();
        return AjaxResult.success()
                .put("commissionList", list)
                .put("canLoad", canLoad);
    }

    @ApiOperation("查询用户余额流水记录")
    @PostMapping("/balance")
    @ResponseBody
    public AjaxResult memberBalanceList() {
        startPage();
        List<AccBalanceRecord> list = iAccRecordService.getMemberBalanceRecord(getTokenUserId());
        boolean canLoad = new PageInfo(list).isHasNextPage();
        return AjaxResult.success()
                .put("balanceList", list)
                .put("canLoad", canLoad);
    }


    @ApiOperation("查询用户提现申请记录")
    @PostMapping("/cashApply")
    @ResponseBody
    public AjaxResult memberCashApplyList() {
        startPage();
        List<AccMemberCashApply> list = iAccRecordService.memberCashApplyList(getTokenUserId());
        boolean canLoad = new PageInfo(list).isHasNextPage();
        return AjaxResult.success()
                .put("cashApplyList", list)
                .put("auditType", AccCashApplyConst.AuditType.toMap())
                .put("canLoad", canLoad);
    }
}
