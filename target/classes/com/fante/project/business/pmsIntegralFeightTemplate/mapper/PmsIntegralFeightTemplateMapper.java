package com.fante.project.business.pmsIntegralFeightTemplate.mapper;

import com.fante.project.business.pmsIntegralFeightTemplate.domain.PmsIntegralFeightTemplate;
import com.fante.project.mapperBase.PmsIntegralFeightTemplateMapperBase;

/**
 * 积分商品运费设置Mapper扩展接口
 *
 * @author fante
 * @date 2020-04-13
 */

public interface PmsIntegralFeightTemplateMapper extends PmsIntegralFeightTemplateMapperBase {

    /**
     * 查询最新设置
     * @return
     */
    PmsIntegralFeightTemplate selectPmsIntegralFeightTemplateRecent();

}
