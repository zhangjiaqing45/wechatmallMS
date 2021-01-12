package com.fante.project.api.mq.req;

import com.fante.project.api.omsOrderProcess.domain.ValidateStockDTO;
import com.fante.project.business.omsOrder.domain.OmsOrder;
import com.fante.project.business.omsOrder.domain.OmsOrderDetail;
import com.fante.project.business.omsOrderItem.domain.OmsOrderItem;
import com.fante.project.business.omsPayOrder.domain.OmsPayOrder;
import com.fante.project.business.smsGroupGameRecord.domain.SmsGroupGameRecord;
import com.fante.project.business.smsGroupMemberRecord.domain.SmsGroupMemberRecord;

import java.io.Serializable;
import java.util.List;

/**
 * @author ftnet
 * @Description JoinGroupHandleParam
 * @CreatTime 2020/4/16
 */
public class JoinGroupHandleParam implements Serializable {
    private static final long serialVersionUID = 1L;
    private SmsGroupGameRecord gameRecord;
    private SmsGroupMemberRecord groupMember;
    private OmsOrderDetail detail;

    public OmsOrderDetail getDetail() {
        return detail;
    }

    public void setDetail(OmsOrderDetail detail) {
        this.detail = detail;
    }

    public SmsGroupGameRecord getGameRecord() {
        return gameRecord;
    }

    public void setGameRecord(SmsGroupGameRecord gameRecord) {
        this.gameRecord = gameRecord;
    }

    public SmsGroupMemberRecord getGroupMember() {
        return groupMember;
    }

    public void setGroupMember(SmsGroupMemberRecord groupMember) {
        this.groupMember = groupMember;
    }
}
