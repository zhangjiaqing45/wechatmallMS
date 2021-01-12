package com.fante.project.business.pmsIntegralProductCategory.mapper;

import com.fante.project.business.pmsIntegralProductCategory.domain.PmsIntegralProductCategory;
import com.fante.project.mapperBase.PmsIntegralProductCategoryMapperBase;

import java.util.List;

/**
 * 积分商品分类Mapper扩展接口
 *
 * @author fante
 * @date 2020-03-18
 */

public interface PmsIntegralProductCategoryMapper extends PmsIntegralProductCategoryMapperBase {

    /**
     * 查询积分商品分类列表
     *
     * @param pmsIntegralProductCategory 积分商品分类
     * @return 积分商品分类集合
     */
    public List<PmsIntegralProductCategory> selectJoinList(PmsIntegralProductCategory pmsIntegralProductCategory);
    /**
     * (app)积分商品分类查询
     * @return
     */
    List<PmsIntegralProductCategory> getPmsIntegralProductCategoryForDisplay(String showStatus);
}
