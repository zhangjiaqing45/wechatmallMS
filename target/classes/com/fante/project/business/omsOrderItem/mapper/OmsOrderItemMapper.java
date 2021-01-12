package com.fante.project.business.omsOrderItem.mapper;

import com.fante.project.api.omsOrderProcess.domain.OmsMemberOrderDetail;
import com.fante.project.business.omsOrderItem.domain.OmsOrderItem;
import com.fante.project.mapperBase.OmsOrderItemMapperBase;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单中所包含的商品Mapper扩展接口
 *
 * @author fante
 * @date 2020-04-01
 */

public interface OmsOrderItemMapper extends OmsOrderItemMapperBase {
    /**
     * 批量插入
     * @param list
     * @return
     */
    int batchInsert(@Param("list") List<OmsOrderItem> list);
    /**
     * (app) 查询订单详情 和 订单信息
     * @param orderItemId
     * @param memberId
     * @return
     */
    OmsMemberOrderDetail getOrderDetailForReturnByOrderItemId(@Param("orderItemId") Long orderItemId
                                                                ,@Param("memberId") Long memberId);
}
