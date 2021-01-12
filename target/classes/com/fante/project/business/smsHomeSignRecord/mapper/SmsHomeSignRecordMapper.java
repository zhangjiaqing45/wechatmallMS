package com.fante.project.business.smsHomeSignRecord.mapper;

import com.fante.project.business.smsHomeSignRecord.domain.SmsHomeSignRecord;
import com.fante.project.mapperBase.SmsHomeSignRecordMapperBase;

import java.util.List;

/**
 * 签到记录Mapper扩展接口
 *
 * @author fante
 * @date 2020-03-12
 */

public interface SmsHomeSignRecordMapper extends SmsHomeSignRecordMapperBase {

    /**
     * 查询用户签到记录（当月）
     *
     * @param smsHomeSignRecord 签到记录
     * @return 签到记录集合
     */
    List<SmsHomeSignRecord> selectUserSignRecordList(SmsHomeSignRecord smsHomeSignRecord);
}
