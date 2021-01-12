package com.fante.project.business.omsOrderSetting.mapper;

import com.fante.project.business.omsOrderSetting.domain.OmsOrderSetting;
import com.fante.project.mapperBase.OmsOrderSettingMapperBase;

/**
 * 订单设置Mapper扩展接口
 *
 * @author fante
 * @date 2020-05-13
 */

public interface OmsOrderSettingMapper extends OmsOrderSettingMapperBase {

    /**
     * 获取最新数据
     * @return
     */
    OmsOrderSetting selectOmsOrderSettingRecent();
}
