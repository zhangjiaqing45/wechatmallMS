package com.fante.project.weixin.business.domain;

import com.fante.project.business.smsGroupMemberRecord.domain.SmsGroupMemberRecord;

import java.io.Serializable;

/**
 * @program: Fante
 * @Date: 2020/5/9 13:57
 * @Author: Mr.Z
 * @Description: 团购退款
 */
public class GroupBuyRefundDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String openid;
    private String outTradeNo;
    private String totalFee;
    private SmsGroupMemberRecord record;

    public GroupBuyRefundDTO() {
    }

    public GroupBuyRefundDTO(String openid, String outTradeNo, String totalFee, SmsGroupMemberRecord record) {
        this.openid = openid;
        this.outTradeNo = outTradeNo;
        this.totalFee = totalFee;
        this.record = record;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    public SmsGroupMemberRecord getRecord() {
        return record;
    }

    public void setRecord(SmsGroupMemberRecord record) {
        this.record = record;
    }
}
