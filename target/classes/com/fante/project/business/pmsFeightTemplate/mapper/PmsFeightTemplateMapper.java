package com.fante.project.business.pmsFeightTemplate.mapper;

import com.fante.project.business.pmsFeightTemplate.domain.PmsFeightTemplateUseDTO;
import com.fante.project.mapperBase.PmsFeightTemplateMapperBase;

import java.util.List;

/**
 * 运费模版Mapper扩展接口
 *
 * @author fante
 * @date 2020-03-16
 */

public interface PmsFeightTemplateMapper extends PmsFeightTemplateMapperBase {

    /**
     * 模板使用情况
     * @param useDTO
     * @return
     */
    List<PmsFeightTemplateUseDTO> selectTemplateUse(PmsFeightTemplateUseDTO useDTO);

}
