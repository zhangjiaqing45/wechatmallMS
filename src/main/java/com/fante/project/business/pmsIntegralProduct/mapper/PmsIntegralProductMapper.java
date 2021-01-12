package com.fante.project.business.pmsIntegralProduct.mapper;

import com.fante.project.business.pmsIntegralProduct.domain.PmsIntegralProduct;
import com.fante.project.mapperBase.PmsIntegralProductMapperBase;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 积分商品Mapper扩展接口
 *
 * @author fante
 * @date 2020-03-19
 */

public interface PmsIntegralProductMapper extends PmsIntegralProductMapperBase {

    /**
     * 查询列表
     *
     * @param pmsIntegralProduct
     * @return
     */
    List<PmsIntegralProduct> selectJoinList(PmsIntegralProduct pmsIntegralProduct);

    /**
     * 兑换成功>减库存
     *
     * @param quantity
     * @param id
     * @return
     */
    int subStrock(@Param("quantity") Long quantity, @Param("id") Long id);

    /**
     * （app）获取积分商品列表
     *
     * @param categoryId
     * @param name
     * @return
     */
    List<PmsIntegralProduct> getPmsIntegralProductListForDisplay(@Param("categoryId") Long categoryId, @Param("name") String name);
    /**
     * （app）获取积分商品详情
     *
     * @param id
     * @return
     */
    PmsIntegralProduct getPmsIntegralProductDetailById(Long id);
}
