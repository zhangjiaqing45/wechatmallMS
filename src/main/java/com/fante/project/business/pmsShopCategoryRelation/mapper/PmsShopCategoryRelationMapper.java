package com.fante.project.business.pmsShopCategoryRelation.mapper;

import com.fante.project.business.pmsProductCategory.domain.PmsProductCategory;
import com.fante.project.business.pmsShopCategoryRelation.domain.PmsShopCategoryRelationParam;
import com.fante.project.business.pmsShopCategoryRelation.domain.PmsShopCategoryRelationResult;
import com.fante.project.mapperBase.PmsShopCategoryRelationMapperBase;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 店铺从平台选择的分类Mapper扩展接口
 *
 * @author fante
 * @date 2020-03-10
 */

public interface PmsShopCategoryRelationMapper extends PmsShopCategoryRelationMapperBase {
    /**
     * 查询店铺从平台选择的分类列表
     * note: 需要去除掉平台删除的分类
     * @return 店铺从平台选择的分类集合
     */
    List<PmsShopCategoryRelationResult> selectPmsShopCategoryList(PmsShopCategoryRelationParam pmsProductCategoryParam);
    /**
     * 批量插入店铺选择的商品分类
     * @param ids
     * @return
     */
    int insertPmsShopCategoryRelationByIds(@Param("userName") String userName, @Param("shopId") Long shopId, @Param("ids") String[] ids);
    /**
     * 通过关系表获取对应店铺的商品分类
     * @param id 店铺与商品分类关系表id
     * @return
     */
    PmsProductCategory selectPmsShopCategoryByRelationId(Long id);
    /**
     * 通过商品ids是否已经存在店铺中
     * @param shopId 店铺id
     * @param ids 要查询的商品ids
     * @return
     */
    int countShopCategoryRelationByPmsCateId(@Param("shopId") Long shopId, @Param("ids") String[] ids);

}
