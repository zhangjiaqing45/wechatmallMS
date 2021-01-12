package com.fante.project.api.appView.service;

import com.fante.common.utils.Checker;
import com.fante.project.business.accBalanceRecord.domain.AccBalanceRecord;
import com.fante.project.business.accBalanceRecord.service.IAccBalanceRecordService;
import com.fante.project.business.accCommissionRecord.domain.AccCommissionRecord;
import com.fante.project.business.accCommissionRecord.service.IAccCommissionRecordService;
import com.fante.project.business.accMemberCashApply.domain.AccMemberCashApply;
import com.fante.project.business.accMemberCashApply.service.IAccMemberCashApplyService;
import com.fante.project.business.umsMember.domain.UmsMember;
import com.fante.project.business.umsMember.service.IUmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;

/**
 * @author ftnet
 * @Description AccCashApplyService
 * @CreatTime 2020/5/7
 */
@Service
public class AccCashApplyService {
    /**
     * 用户佣金记录Service接口
     */
    @Autowired
    IAccCommissionRecordService iAccCommissionRecordService;
    /**
     * 用户余额记录Service接口
     */
    @Autowired
    IAccBalanceRecordService iAccBalanceRecordService;
    /**
     *  会员Service接口
     */
    @Autowired
    IUmsMemberService iUmsMemberService;
    /**
     * 用户提现申请
     */
    @Autowired
    IAccMemberCashApplyService iAccMemberCashApplyService;
    /**
     * 用户提现申请
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public int applyCashOfMember(AccMemberCashApply param) {
        Checker.check(ObjectUtils.isEmpty(param.getMemberId()),"申请提现金额参数缺失");
        //插入提现申请
        int i = iAccMemberCashApplyService.insertAccMemberCashApply(param);
        //扣减余额
        i += iUmsMemberService.memberBalanceToCash(param.getMemberId(), param.getMoney());
        Checker.check(i!=2,"申请提现失败");
        return i;
    }
    /**
     * 佣金转余额
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public int waitCommissionToBalance(AccCommissionRecord param) {
        //转入余额-->扣减佣金
        BigDecimal money = param.getMoney();
        param.setMoney(money.negate());
        int i = iAccCommissionRecordService.exchangeBrokerageToBalance(param);
        //余额记录
        AccBalanceRecord insert= new AccBalanceRecord();
        insert.setMoney(money);
        insert.setMemberId(param.getMemberId());
        i += iAccBalanceRecordService.insertAccBalanceRecordOfCommission(insert);
        //佣金转余额
        i += iUmsMemberService.waitCommissionToBalance(param.getMemberId(),money);
        Checker.check(i!=3,"佣金转余额失败");
        return i;
    }
}
