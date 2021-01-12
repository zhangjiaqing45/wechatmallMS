package com.fante.project.business.cmsDocument.service.impl;

import java.util.List;
import com.fante.common.utils.DateUtils;
import com.fante.common.constant.Constants;
import com.fante.common.utils.StringUtils;
import com.fante.common.utils.security.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fante.project.business.cmsDocument.mapper.CmsDocumentMapper;
import com.fante.project.business.cmsDocument.domain.CmsDocument;
import com.fante.project.business.cmsDocument.service.ICmsDocumentService;
import com.fante.common.utils.text.Convert;
import org.springframework.util.ObjectUtils;

/**
 * 文档管理Service业务层处理
 *
 * @author fante
 * @date 2020-04-08
 */
@Service
public class CmsDocumentServiceImpl implements ICmsDocumentService {

    private static Logger log = LoggerFactory.getLogger(CmsDocumentServiceImpl.class);

    @Autowired
    private CmsDocumentMapper cmsDocumentMapper;

    /**
     * 查询文档管理
     *
     * @param id 文档管理ID
     * @return 文档管理
     */
    @Override
    public CmsDocument selectCmsDocumentById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        return cmsDocumentMapper.selectCmsDocumentById(id);
    }

    /**
     * 查询文档管理列表
     *
     * @param cmsDocument 文档管理
     * @return 文档管理集合
     */
    @Override
    public List<CmsDocument> selectCmsDocumentList(CmsDocument cmsDocument) {
        return cmsDocumentMapper.selectCmsDocumentList(cmsDocument);
    }

    /**
     * 查询文档管理数量
     *
     * @param cmsDocument 文档管理
     * @return 结果
     */
    @Override
    public int countCmsDocument(CmsDocument cmsDocument){
        return cmsDocumentMapper.countCmsDocument(cmsDocument);
    }

    /**
     * 唯一校验
     *
     * @param cmsDocument 文档管理
     * @return 结果
     */
    @Override
    public String checkCmsDocumentUnique(CmsDocument cmsDocument) {
        return cmsDocumentMapper.checkCmsDocumentUnique(cmsDocument) > 0 ? Constants.NOT_UNIQUE : Constants.UNIQUE;
    }

    /**
     * 新增文档管理
     *
     * @param cmsDocument 文档管理
     * @return 新增文档管理数量
     */
    @Override
    public int insertCmsDocument(CmsDocument cmsDocument) {
        if (StringUtils.isBlank(cmsDocument.getCreateBy())) {
            cmsDocument.setCreateBy(ShiroUtils.getLoginName());
        }
        cmsDocument.setCreateTime(DateUtils.getNowDate());
        return cmsDocumentMapper.insertCmsDocument(cmsDocument);
    }

    /**
     * 修改文档管理
     *
     * @param cmsDocument 文档管理
     * @return 修改文档管理数量
     */
    @Override
    public int updateCmsDocument(CmsDocument cmsDocument) {
        if (StringUtils.isBlank(cmsDocument.getUpdateBy())) {
            cmsDocument.setUpdateBy(ShiroUtils.getLoginName());
        }
        cmsDocument.setUpdateTime(DateUtils.getNowDate());
        return cmsDocumentMapper.updateCmsDocument(cmsDocument);
    }

    /**
     * 删除文档管理对象
     *
     * @param ids 需要删除的数据ID
     * @return 删除文档管理数量
     */
    @Override
    public int deleteCmsDocumentByIds(String ids) {
        return cmsDocumentMapper.deleteCmsDocumentByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除文档管理信息
     *
     * @param id 文档管理ID
     * @return 删除文档管理数量
     */
    @Override
    public int deleteCmsDocumentById(Long id) {
        return cmsDocumentMapper.deleteCmsDocumentById(id);
    }

    /**
     * 根据文档键值查找文档
     * @param docKey
     * @return
     */
    @Override
    public CmsDocument selectCmsDocumentByDocKey(String docKey) {
        if (StringUtils.isBlank(docKey)) {
            return null;
        }
        return cmsDocumentMapper.selectCmsDocumentByDocKey(docKey);
    }
}
