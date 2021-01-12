package com.fante.project.business.cmsTopicCategory.service.impl;

import com.fante.common.constant.Constants;
import com.fante.common.utils.Checker;
import com.fante.common.utils.DateUtils;
import com.fante.common.utils.StringUtils;
import com.fante.common.utils.security.ShiroUtils;
import com.fante.common.utils.text.Convert;
import com.fante.project.business.cmsTopic.service.ICmsTopicService;
import com.fante.project.business.cmsTopicCategory.domain.CmsTopicCategory;
import com.fante.project.business.cmsTopicCategory.mapper.CmsTopicCategoryMapper;
import com.fante.project.business.cmsTopicCategory.service.ICmsTopicCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * 话题分类Service业务层处理
 *
 * @author fante
 * @date 2020-03-17
 */
@Service
public class CmsTopicCategoryServiceImpl implements ICmsTopicCategoryService {

    private static Logger log = LoggerFactory.getLogger(CmsTopicCategoryServiceImpl.class);

    @Autowired
    private CmsTopicCategoryMapper cmsTopicCategoryMapper;
    @Autowired
    private ICmsTopicService cmsTopicService;

    /**
     * 查询话题分类
     *
     * @param id 话题分类ID
     * @return 话题分类
     */
    @Override
    public CmsTopicCategory selectCmsTopicCategoryById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        return cmsTopicCategoryMapper.selectCmsTopicCategoryById(id);
    }

    /**
     * 查询话题分类列表
     *
     * @param cmsTopicCategory 话题分类
     * @return 话题分类集合
     */
    @Override
    public List<CmsTopicCategory> selectCmsTopicCategoryList(CmsTopicCategory cmsTopicCategory) {
        return cmsTopicCategoryMapper.selectCmsTopicCategoryList(cmsTopicCategory);
    }

    @Override
    public List<CmsTopicCategory> selectJoinList(CmsTopicCategory cmsTopicCategory) {
        return cmsTopicCategoryMapper.selectJoinList(cmsTopicCategory);
    }

    /**
     * 查询话题分类数量
     *
     * @param cmsTopicCategory 话题分类
     * @return 结果
     */
    @Override
    public int countCmsTopicCategory(CmsTopicCategory cmsTopicCategory){
        return cmsTopicCategoryMapper.countCmsTopicCategory(cmsTopicCategory);
    }

    /**
     * 唯一校验
     *
     * @param cmsTopicCategory 话题分类
     * @return 结果
     */
    @Override
    public String checkCmsTopicCategoryUnique(CmsTopicCategory cmsTopicCategory) {
        return cmsTopicCategoryMapper.checkCmsTopicCategoryUnique(cmsTopicCategory) > 0 ? Constants.NOT_UNIQUE : Constants.UNIQUE;
    }

    /**
     * 新增话题分类
     *
     * @param cmsTopicCategory 话题分类
     * @return 新增话题分类数量
     */
    @Override
    public int insertCmsTopicCategory(CmsTopicCategory cmsTopicCategory) {
        if (StringUtils.isBlank(cmsTopicCategory.getCreateBy())) {
            cmsTopicCategory.setCreateBy(ShiroUtils.getLoginName());
        }
        cmsTopicCategory.setCreateTime(DateUtils.getNowDate());
        return cmsTopicCategoryMapper.insertCmsTopicCategory(cmsTopicCategory);
    }

    /**
     * 修改话题分类
     *
     * @param cmsTopicCategory 话题分类
     * @return 修改话题分类数量
     */
    @Override
    public int updateCmsTopicCategory(CmsTopicCategory cmsTopicCategory) {
        if (StringUtils.isBlank(cmsTopicCategory.getUpdateBy())) {
            cmsTopicCategory.setUpdateBy(ShiroUtils.getLoginName());
        }
        cmsTopicCategory.setUpdateTime(DateUtils.getNowDate());
        return cmsTopicCategoryMapper.updateCmsTopicCategory(cmsTopicCategory);
    }

    /**
     * 删除话题分类对象
     *
     * @param ids 需要删除的数据ID
     * @return 删除话题分类数量
     */
    @Override
    public int deleteCmsTopicCategoryByIds(String ids) {
        return cmsTopicCategoryMapper.deleteCmsTopicCategoryByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除话题分类信息
     *
     * @param id 话题分类ID
     * @return 删除话题分类数量
     */
    @Override
    public int deleteCmsTopicCategoryById(Long id) {
        int count  = cmsTopicService.countCmsTopicByCategoryId(id);
        Checker.check(count > 0, "该分类下存在话题，暂时无法删除");
        return cmsTopicCategoryMapper.deleteCmsTopicCategoryById(id);
    }
}
