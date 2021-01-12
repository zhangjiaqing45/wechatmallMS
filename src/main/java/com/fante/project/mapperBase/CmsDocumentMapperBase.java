package com.fante.project.mapperBase;

import com.fante.project.business.cmsDocument.domain.CmsDocument;
import java.util.List;

/**
 * 文档管理Mapper基础接口
 *
 * @author fante
 * @date 2020-04-08
 */
public interface CmsDocumentMapperBase {
    /**
     * 查询文档管理
     *
     * @param id 文档管理ID
     * @return 文档管理
     */
    public CmsDocument selectCmsDocumentById(Long id);

    /**
     * 查询文档管理列表
     *
     * @param cmsDocument 文档管理
     * @return 文档管理集合
     */
    public List<CmsDocument> selectCmsDocumentList(CmsDocument cmsDocument);

    /**
     * 查询文档管理数量
     *
     * @param cmsDocument 文档管理
     * @return 结果
     */
    public int countCmsDocument(CmsDocument cmsDocument);

    /**
     * 唯一校验
     *
     * @param cmsDocument 文档管理
     * @return 结果
     */
    public int checkCmsDocumentUnique(CmsDocument cmsDocument);

    /**
     * 新增文档管理
     *
     * @param cmsDocument 文档管理
     * @return 结果
     */
    public int insertCmsDocument(CmsDocument cmsDocument);

    /**
     * 修改文档管理
     *
     * @param cmsDocument 文档管理
     * @return 结果
     */
    public int updateCmsDocument(CmsDocument cmsDocument);

    /**
     * 删除文档管理
     *
     * @param id 文档管理ID
     * @return 结果
     */
    public int deleteCmsDocumentById(Long id);

    /**
     * 批量删除文档管理
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCmsDocumentByIds(String[] ids);

}
