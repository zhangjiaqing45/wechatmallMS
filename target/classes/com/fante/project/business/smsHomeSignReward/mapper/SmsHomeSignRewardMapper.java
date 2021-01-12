package com.fante.project.business.smsHomeSignReward.mapper;

import com.fante.project.business.smsHomeSignReward.domain.SmsHomeSignReward;
import com.fante.project.mapperBase.SmsHomeSignRewardMapperBase;

import java.util.List;

/**
 * 签到奖励记录Mapper扩展接口
 *
 * @author fante
 * @date 2020-03-12
 */

public interface SmsHomeSignRewardMapper extends SmsHomeSignRewardMapperBase {

    /**
     * 批量添加
     *
     * @param rewards 签到奖励记录
     * @return void
     */
    void insertBatch(List<SmsHomeSignReward> rewards);
}
