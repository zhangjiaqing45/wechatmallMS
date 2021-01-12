package com.fante.project.business.smsGroupMemberRecord.mapper;

import com.fante.project.mapperBase.SmsGroupMemberRecordMapperBase;

import java.util.List;

/**
 * 团购记录人员Mapper扩展接口
 *
 * @author fante
 * @date 2020-03-30
 */

public interface SmsGroupMemberRecordMapper extends SmsGroupMemberRecordMapperBase {

    /**
     * 获取所有团购失败未取消订单
     * @param status 拼团状态
     * @return
     */
    List<Long> selectTimeOutGroupRecord(String status);
}
