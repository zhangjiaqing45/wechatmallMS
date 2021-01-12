package com.fante.project.api.omsOrderProcess.service;

import com.fante.project.business.accAccountRecord.domain.AccAccountRecord;
import com.fante.project.business.accAccountRecord.service.IAccAccountRecordService;
import com.fante.project.business.accBalanceRecord.domain.AccBalanceRecord;
import com.fante.project.business.accBalanceRecord.service.IAccBalanceRecordService;
import com.fante.project.business.accCommissionRecord.domain.AccCommissionRecord;
import com.fante.project.business.accCommissionRecord.service.IAccCommissionRecordService;
import com.fante.project.business.accMemberCashApply.domain.AccMemberCashApply;
import com.fante.project.business.accMemberCashApply.service.IAccMemberCashApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ftnet
 * @Description IAccRecordService
 * @CreatTime 2020/5/7
 */
@Service
public class IAccRecordService {
    /**
     * 佣金记录明细
     */
    @Autowired
    IAccCommissionRecordService iAccCommissionRecordService;
    /**
     * 店铺账户记录
     */
    @Autowired
    IAccAccountRecordService iAccAccountRecordService;
    /**
     * 用户余额记录
     */
    @Autowired
    IAccBalanceRecordService iAccBalanceRecordService;
    /**
     * 用户提现申请记录
     */
    @Autowired
    IAccMemberCashApplyService iAccMemberCashApplyService;


    /**
     * 查询用户资金流水记录
     * @param memberId
     * @return
     */
    public List<AccCommissionRecord> getMemberCommissionRecord(Long memberId) {
        AccCommissionRecord param = new AccCommissionRecord();
        param.setMemberId(memberId);
        return iAccCommissionRecordService.selectAccCommissionRecordList(param);
    }

    /**
     * 查询用户余额流水记录
     * @param memberId
     * @return
     */
    public List<AccBalanceRecord> getMemberBalanceRecord(Long memberId) {
        AccBalanceRecord select = new AccBalanceRecord();
        select.setMemberId(memberId);
        return iAccBalanceRecordService.selectAccBalanceRecordList(select);
    }

    /**
     * 查询用户提现申请记录
     * @param memberId
     * @return
     */
    public List<AccMemberCashApply> memberCashApplyList(Long memberId) {
        AccMemberCashApply select = new AccMemberCashApply();
        select.setMemberId(memberId);
        return iAccMemberCashApplyService.selectAccMemberCashApplyList(select);
    }
}
