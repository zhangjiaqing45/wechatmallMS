package com.fante.project.business.wxPayConfig.mapper;

import com.fante.project.business.wxPayConfig.domain.WxPayConfig;
import com.fante.project.mapperBase.WxPayConfigMapperBase;

/**
 * 微信支付配置Mapper扩展接口
 *
 * @author fante
 * @date 2020-04-09
 */

public interface WxPayConfigMapper extends WxPayConfigMapperBase {

    /**
     * 获取最新的配置
     * @return
     */
    WxPayConfig selectWxPayConfigRecent();

}
