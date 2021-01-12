package com.fante.project.business.wxOffiaccountConfig.mapper;

import com.fante.project.business.wxOffiaccountConfig.domain.WxOffiaccountConfig;
import com.fante.project.mapperBase.WxOffiaccountConfigMapperBase;

/**
 * 微信公众号配置Mapper扩展接口
 *
 * @author fante
 * @date 2020-04-09
 */

public interface WxOffiaccountConfigMapper extends WxOffiaccountConfigMapperBase {

    /**
     * 获取最新的配置
     * @return
     */
    WxOffiaccountConfig selectWxOffiaccountConfigRecent();

}
