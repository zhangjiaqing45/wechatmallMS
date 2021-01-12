package com.fante.project.business.omsOrder.service.impl;

import com.fante.common.business.enums.OrderConst;
import com.fante.common.utils.Checker;
import com.fante.common.utils.StringUtils;
import com.fante.project.api.setting.OrderStting;
import com.fante.project.business.omsOrder.domain.OmsOrderDetail;
import com.fante.project.business.omsOrder.mapper.OmsOrderMapper;
import com.fante.project.business.omsOrderOperateHistory.service.IOmsOrderOperateHistoryService;
import com.fante.project.business.omsPayOrder.domain.OmsPayOrder;
import com.fante.project.business.omsPayOrder.service.IOmsPayOrderService;
import com.fante.project.business.pmsSkuStock.service.IPmsSkuStockService;
import com.fante.project.business.smsFlashPromotionSku.service.ISmsFlashPromotionSkuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.*;

/**
 * 订单Service业务层处理
 *
 * @author fante
 * @date 2020-04-01
 */
@Service
public class OmsCancelOrderOperationService {

    private static Logger log = LoggerFactory.getLogger(OmsCancelOrderOperationService.class);

    @Autowired
    private OmsOrderMapper omsOrderMapper;
    /**
     * 取消订单
     */
    @Autowired
    private OmsCancelOrderService omsCancelOrderService;
    /**
     * 支付订单
     */
    @Autowired
    private IOmsPayOrderService iOmsPayOrderService;
    /**
     * 订单设置
     */
    @Autowired
    private OrderStting orderStting;

    /**
     * 队列调用:根据订单id取消超时订单
     * @param paySn
     * @return 返回错误信息 或 Constants.SUCCESS='0'
     */
    public void cancleOutTimeOmsOrder(String paySn) {
        OmsPayOrder payOrder = iOmsPayOrderService.selectOmsPayOrderByOrderSn(paySn);
        Checker.check(ObjectUtils.isEmpty(payOrder),"支付订单不存在！");
        List<OmsOrderDetail> orderDetails = omsOrderMapper.selectOrderDetailByPayOrderSn(paySn);
        //查询不到则返回订单不存在
        Checker.check(ObjectUtils.isEmpty(orderDetails),StringUtils.format("根据支付订单号:{},找不到订单！",paySn));
        orderDetails.stream().forEach(order->{
            //状态不对则返回订单状态错误
           // Checker.checkOp(Objects.equals(order.getStatus(), OrderConst.OrderStatus.WAIT_PAY.getType()),StringUtils.format("订单已处理->订单id:{},状态为[{}].！",String.valueOf(order.getId()), OrderConst.OrderStatus.get(order.getStatus()).getDescribe()));
            omsCancelOrderService.cancelOrder(order,payOrder);
        });
    }

    /**
     * 定时任务:自动取消超时订单(扫描)
     */
    public void autoCancelTimeOutOrder() {
        //查询超时、未支付的订单及订单详情
        List<OmsOrderDetail> timeOutOrders = omsOrderMapper.getTimeOutOrders(orderStting.getAutoCancelOrderTime(OrderConst.OrderType.GENERAL),
                orderStting.getAutoCancelOrderTime(OrderConst.OrderType.GROUP),
                orderStting.getAutoCancelOrderTime(OrderConst.OrderType.FLASH));
        if (CollectionUtils.isEmpty(timeOutOrders)) {
            log.info("暂无超时订单");
            return;
        }
        timeOutOrders.stream().forEach(order->{
            try {
                omsCancelOrderService.cancelOrder(order,null);
                log.info(StringUtils.format("订单号:{},超时自动取消！",String.valueOf(order.getId())));
            }catch (Exception e){
                log.warn(StringUtils.format("订单号:{},库存异常,取消失败！",String.valueOf(order.getId())));
            }
        });
    }

    /**
     * (app)用户自己:根据订单id取消订单
     * @param id
     * @return
     */
    public int memberCancleOmsOrderById(Long id,Long memberId) {
        OmsOrderDetail order = omsOrderMapper.getOmsOrderDetailById(id);
        Checker.check(ObjectUtils.isEmpty(order),"该订单不存在！");
        //检查会员是否操作的是自己的订单
        Checker.checkOp(Objects.equals(order.getMemberId(),memberId),"只能操作自己的订单！");
        //Checker.checkOp(Objects.equals(order.getStatus(), OrderConst.OrderStatus.WAIT_PAY.getType()),"只能取消未支付的订单！");
        //不抛异常返回操作结果
        try{
            omsCancelOrderService.cancelOrder(order,null);
        }catch(Exception e){
            log.error("订单:{} 取消失败！错误信息:{}",id,e.getMessage());
            return 0;
        }
        return 1;
    }
}
