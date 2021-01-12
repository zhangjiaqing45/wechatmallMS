package com.fante.project.business.omsPayOrder.mapper;

import com.fante.project.business.omsPayOrder.domain.OmsPayOrder;
import com.fante.project.mapperBase.OmsPayOrderMapperBase;
import org.apache.ibatis.annotations.Param;

/**
 * 支付订单Mapper扩展接口
 *
 * @author fante
 * @date 2020-04-18
 */

public interface OmsPayOrderMapper extends OmsPayOrderMapperBase {
    /**
     * 修改支付订单支付状态为已经支付
     * @param payOrderSn
     * @return
     */
    int paySuccess(String payOrderSn);

    /**
     * 查询支付订单
     * @param payOrderSn
     * @param status
     * @return
     */
    OmsPayOrder selectOmsPayOrderByOrderSn(@Param("payOrderSn") String payOrderSn, @Param("status") String status);
}
