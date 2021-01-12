package com.fante.project.business.smsSetting.mapper;

import com.fante.project.business.smsSetting.domain.SmsSetting;
import com.fante.project.mapperBase.SmsSettingMapperBase;

/**
 * 营销设置(优惠券、团购、签到、秒杀、积分商城是否开启)Mapper扩展接口
 *
 * @author fante
 * @date 2020-03-10
 */

public interface SmsSettingMapper extends SmsSettingMapperBase {

    /**
     * 查询最新一条设置
     *
     * @return
     */
    SmsSetting selectSmsSettingRecent();
}
