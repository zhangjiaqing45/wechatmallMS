package com.fante.project.business.pmsProductCategory.mapper;

import com.fante.project.business.pmsProductCategory.domain.PmsProductCategory;
import com.fante.project.business.pmsProductCategory.domain.PmsProductCategoryDetail;
import com.fante.project.business.pmsProductCategory.domain.PmsShopProductCategory;
import com.fante.project.mapperBase.PmsProductCategoryMapperBase;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 产品分类Mapper扩展接口
 *
 * @author fante
 * @date 2020-03-09
 */

public interface PmsProductCategoryMapper extends PmsProductCategoryMapperBase {

    /**
     * 查询当前店铺未选商品分类
     *
     * @return 当前店铺未选商品分类列表
     */
    List<PmsProductCategory> selectableCategory(@Param("name") String name, @Param("shopId") Long shopId);
    /**
     * 获取所有分类app展示
     *
     * @return 所有可用商品分类
     */
    List<PmsProductCategory> getCategoryForNavInPlatform(@Param("showStatus") String showStatus, @Param("level") Long level);
    /**
     * 获取分类app店铺展示-
     *
     * @return 所有可用商品分类
     */
    List<PmsShopProductCategory> getCategoryForNavInShop(@Param("shopStatus") String shopSatatus,
                                                         @Param("status") String status,
                                                         @Param("shopId") Long shopId,
                                                         @Param("level") Long level
                                                        );
    
    /**
     * 查询带有父级名称的分类列表
     * @return
     */
    List<PmsProductCategoryDetail> getPmsProductCategoryList(Map<String, Object> param);

    /**
     * 通过id校验这个分类是否可用
     * @param id 产品分类id
     * @return 结果
     */
    PmsProductCategory validatePmsProductCategoryById(@Param("id") Long id, @Param("shopId") Long shopId);

    /**
     * 修改删除状态和导航禁用状态
     * @param ids 产品分类ids
     * @return 结果
     */
    int deleteCategoryByIds(String[] ids);



}
