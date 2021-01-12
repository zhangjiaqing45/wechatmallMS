package com.fante.project.business.cmsDocument.service;

import com.fante.project.business.cmsDocument.domain.CmsDocument;

import java.util.List;

/**
 * 文档管理Service接口
 *
 * @author fante
 * @date 2020-04-08
 */
public interface ICmsDocumentService {
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
    public String checkCmsDocumentUnique(CmsDocument cmsDocument);

    /**
     * 新增文档管理
     *
     * @param cmsDocument 文档管理
     * @return 新增文档管理数量
     */
    public int insertCmsDocument(CmsDocument cmsDocument);

    /**
     * 修改文档管理
     *
     * @param cmsDocument 文档管理
     * @return 修改文档管理数量
     */
    public int updateCmsDocument(CmsDocument cmsDocument);

    /**
     * 批量删除文档管理
     *
     * @param ids 需要删除的数据ID
     * @return 删除文档管理数量
     */
    public int deleteCmsDocumentByIds(String ids);

    /**
     * 删除文档管理信息
     *
     * @param id 文档管理ID
     * @return 删除文档管理数量
     */
    public int deleteCmsDocumentById(Long id);

    /**
     * 根据文档键值查找文档
     * @param docKey
     * @return
     */
    public CmsDocument selectCmsDocumentByDocKey(String docKey);

    }
