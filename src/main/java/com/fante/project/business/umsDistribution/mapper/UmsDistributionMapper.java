package com.fante.project.business.umsDistribution.mapper;

import com.fante.project.api.omsOrderProcess.domain.OmsOrderCommissionDTO;
import com.fante.project.business.umsDistribution.domain.UmsDistributionResult;
import com.fante.project.mapperBase.UmsDistributionMapperBase;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * 分销比例商品角色关系Mapper扩展接口
 *
 * @author fante
 * @date 2020-04-30
 */

public interface UmsDistributionMapper extends UmsDistributionMapperBase {
    /**
     * 根据商品id查询该商品sku 到各个分销金
     * @param pid
     * @param pid
     * @return
     */
    List<UmsDistributionResult> selectUmsDistributionByProudctId(@Param("pid") Long pid,@Param("shopId") Long shopId);
    /**
     * 查询分销时所需商品信息
     * @return
     */
    UmsDistributionResult selectSkuInfoForDistribution(@Param("pid") Long pid,@Param("shopId")  Long shopId);
    /**
     * 根据获取分销金额
     * @param itemIds
     * @param memberId
     * @return
     */
    List<OmsOrderCommissionDTO> getCommissionByOrderItemIds(@Param("itemIds") List<Long> itemIds, @Param("memberId")  Long memberId);
    /**
     * 根据订单详情获取单个分销金额
     * @param orderItemId
     * @param memberId
     * @return
     */
    BigDecimal getCommissionByOrderItem(@Param("orderItemId") Long orderItemId,@Param("memberId")  Long memberId);
}
