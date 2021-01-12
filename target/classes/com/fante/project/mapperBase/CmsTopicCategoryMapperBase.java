package com.fante.project.mapperBase;

import com.fante.project.business.cmsTopicCategory.domain.CmsTopicCategory;
import java.util.List;

/**
 * 话题分类Mapper基础接口
 *
 * @author fante
 * @date 2020-03-17
 */
public interface CmsTopicCategoryMapperBase {
    /**
     * 查询话题分类
     *
     * @param id 话题分类ID
     * @return 话题分类
     */
    public CmsTopicCategory selectCmsTopicCategoryById(Long id);

    /**
     * 查询话题分类列表
     *
     * @param cmsTopicCategory 话题分类
     * @return 话题分类集合
     */
    public List<CmsTopicCategory> selectCmsTopicCategoryList(CmsTopicCategory cmsTopicCategory);


    /**
     * 查询话题分类数量
     *
     * @param cmsTopicCategory 话题分类
     * @return 结果
     */
    public int countCmsTopicCategory(CmsTopicCategory cmsTopicCategory);

    /**
     * 唯一校验
     *
     * @param cmsTopicCategory 话题分类
     * @return 结果
     */
    public int checkCmsTopicCategoryUnique(CmsTopicCategory cmsTopicCategory);

    /**
     * 新增话题分类
     *
     * @param cmsTopicCategory 话题分类
     * @return 结果
     */
    public int insertCmsTopicCategory(CmsTopicCategory cmsTopicCategory);

    /**
     * 修改话题分类
     *
     * @param cmsTopicCategory 话题分类
     * @return 结果
     */
    public int updateCmsTopicCategory(CmsTopicCategory cmsTopicCategory);

    /**
     * 删除话题分类
     *
     * @param id 话题分类ID
     * @return 结果
     */
    public int deleteCmsTopicCategoryById(Long id);

    /**
     * 批量删除话题分类
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCmsTopicCategoryByIds(String[] ids);

}
