package com.fante.project.business.bizMainCategory.mapper;

import com.fante.project.business.bizMainCategory.domain.BizMainCategory;
import com.fante.project.mapperBase.BizMainCategoryMapperBase;

/**
 * 店铺主营类目Mapper扩展接口
 *
 * @author fante
 * @date 2020-03-10
 */

public interface BizMainCategoryMapper extends BizMainCategoryMapperBase {

    /**
     * 检测字段唯一
     * @param bizMainCategory
     * @return
     */
    public int checkCategoryUnique(BizMainCategory bizMainCategory);

}
