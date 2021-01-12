package com.fante.project.business.cmsDocument.mapper;

import com.fante.project.business.cmsDocument.domain.CmsDocument;
import com.fante.project.mapperBase.CmsDocumentMapperBase;
import org.apache.ibatis.annotations.Param;

/**
 * 文档管理Mapper扩展接口
 *
 * @author fante
 * @date 2020-04-08
 */

public interface CmsDocumentMapper extends CmsDocumentMapperBase {

    /**
     * 根据文档键值查找文档
     * @param docKey
     * @return
     */
    public CmsDocument selectCmsDocumentByDocKey(@Param("docKey") String docKey);

}
