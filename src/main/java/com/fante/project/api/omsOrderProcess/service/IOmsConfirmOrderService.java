package com.fante.project.api.omsOrderProcess.service;

import com.fante.project.api.mq.req.OrderHandleOfCartParam;
import com.fante.project.api.omsOrderProcess.domain.ConfirmOrderParam;
import com.fante.project.api.omsOrderProcess.domain.ValidateStockDTO;
import com.fante.project.business.omsOrder.domain.OmsOrder;
import com.fante.project.business.omsOrderItem.domain.OmsOrderItem;
import com.fante.project.business.omsPayOrder.domain.OmsPayOrder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 各种下单的服务
 */
public interface IOmsConfirmOrderService {
    /**
     * 从购物车中下单购买
     *
     * @param params
     * @return
     */
    public int confirmByCart(List<ConfirmOrderParam> params, Long memberId);

    /**
     * 立即购买购买
     *
     * @param params
     * @return
     */
    public int confirmByNow(ConfirmOrderParam params);

    /**
     * 团购购买
     *
     * @param params
     * @return
     */
    public int confirmByGroup(ConfirmOrderParam params);

    /**
     * 秒杀购买
     *
     * @param params
     * @return
     */
    public int confirmBySeckill(ConfirmOrderParam params);

    /**
     * 未支付订单重新支付
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public String confirmAgain(Long orderId);

    /**
     * 队列下单-普通订单-购物车
     */
    @Transactional(rollbackFor = Exception.class)
    public String completeGeneralOrderOfCart(OrderHandleOfCartParam params);

    /**
     * 队列下单-普通订单-立即购买
     */
    @Transactional(rollbackFor = Exception.class)
    public String completeGeneralOrderOfNow(OmsPayOrder payOrder, OmsOrder order, List<OmsOrderItem> orderItemList, List<ValidateStockDTO> stockList, String cartIds, Long promotionId,String paymentType);

    /**
     * 队列下单-秒杀订单
     */
    @Transactional(rollbackFor = Exception.class)
    public String completeFlashOrder(OmsPayOrder payOrder, OmsOrder order, List<OmsOrderItem> orderItemList, List<ValidateStockDTO> stockList, Long promotionId);

    /**
     * 队列下单-团购订单
     */
    @Transactional(rollbackFor = Exception.class)
    public String completeGroupOrder(OmsPayOrder payOrder, OmsOrder order, List<OmsOrderItem> orderItemList, List<ValidateStockDTO> stockList, Long promotionId);

}
