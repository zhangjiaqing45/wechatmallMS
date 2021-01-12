package com.fante.project.business.cmsTopicCategory.mapper;

import com.fante.project.business.cmsTopicCategory.domain.CmsTopicCategory;
import com.fante.project.mapperBase.CmsTopicCategoryMapperBase;

import java.util.List;

/**
 * 话题分类Mapper扩展接口
 *
 * @author fante
 * @date 2020-03-17
 */

public interface CmsTopicCategoryMapper extends CmsTopicCategoryMapperBase {


    public List<CmsTopicCategory> selectJoinList(CmsTopicCategory cmsTopicCategory);

}
