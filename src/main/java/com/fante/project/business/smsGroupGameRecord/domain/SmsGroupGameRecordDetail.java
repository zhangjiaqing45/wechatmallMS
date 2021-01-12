package com.fante.project.business.smsGroupGameRecord.domain;

import com.fante.framework.aspectj.lang.annotation.Excel;
import com.fante.framework.web.domain.BaseEntity;
import com.fante.project.business.smsGroupMemberRecord.domain.SmsGroupMemberRecord;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * 团购记录对象 sms_group_game_record
 * 
 * @author fante
 * @date 2020-03-30
 */
public class SmsGroupGameRecordDetail extends SmsGroupGameRecord {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "过时时间")
    private Long timeOut;
    @ApiModelProperty(value = "团购记录下的会员信息集合")
    private List<SmsGroupMemberRecord> groupMemberRecordList;

    public List<SmsGroupMemberRecord> getGroupMemberRecordList() {
        return groupMemberRecordList;
    }

    public Long getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(Long timeOut) {
        this.timeOut = timeOut;
    }

    public void setGroupMemberRecordList(List<SmsGroupMemberRecord> groupMemberRecordList) {
        this.groupMemberRecordList = groupMemberRecordList;
    }
}
