package com.fante.project.business.smsHomeSignSetting.mapper;

import com.fante.project.business.bizMainCategory.domain.BizMainCategory;
import com.fante.project.business.smsHomeSignSetting.domain.SmsHomeSignSetting;
import com.fante.project.mapperBase.SmsHomeSignSettingMapperBase;

/**
 * 签到奖励设置Mapper扩展接口
 *
 * @author fante
 * @date 2020-03-11
 */

public interface SmsHomeSignSettingMapper extends SmsHomeSignSettingMapperBase {
    /**
     * 通过签到次数和类型查询
     *
     * @param setting
     * @return 通过签到次数和类型查询
     */
    SmsHomeSignSetting selectByTypeAndTimes(SmsHomeSignSetting setting);

    /**
     * 检测字段唯一
     * @param smsHomeSignSetting
     * @return
     */
    public int checkSignSettingUnique(SmsHomeSignSetting smsHomeSignSetting);

}
