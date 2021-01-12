package com.fante.project.api.mq.sender;

import com.fante.common.business.enums.OrderConst;
import com.fante.project.api.mq.enums.QueueEnum;
import com.fante.project.api.setting.OrderStting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: Fante
 * @Date: 2020/4/15 22:03
 * @Author: Mr.Z
 * @Description: 测试直连模式队列--生产者
 */
@Component
public class CancelOrderSender implements RabbitTemplate.ConfirmCallback {

    private static Logger log = LoggerFactory.getLogger(CancelOrderSender.class);

    @Autowired
    RabbitTemplate rabbitTemplate;
    /**
     * 订单设置
     */
    @Autowired
    private OrderStting orderStting;

    private static final String QUEUE_INFO = "延时取消订单";
    /**
     * 延时取消订单的消息标志前缀
     */
    private static final String CANCEL_ORDER_SIGN = "cancelOrder:payOrderSn:";

    /**
     * 发送延时队列到点取消一般订单
     *
     * @param payOrderSn 支付单号
     */
    public void sendDelayMessageCancelGeneralOrder(String payOrderSn) {
        //获取订单超时时间
        long delayTimes = orderStting.getAutoCancelOrderTime(OrderConst.OrderType.GENERAL) * 60 * 1000;
        // 消息标识
        CorrelationData cData = new CorrelationData(CANCEL_ORDER_SIGN + payOrderSn);
        // 发送消息（参数：交换机、路由键、消息内容、消息标识）
        rabbitTemplate.convertAndSend(QueueEnum.Q_DELAY_ORDER_GENERAL_CANCEL.getExChange(), QueueEnum.Q_DELAY_ORDER_GENERAL_CANCEL.getRouteKey(), payOrderSn, message -> {
            //给消息设置延迟毫秒值
            message.getMessageProperties().setExpiration(String.valueOf(delayTimes));
            return message;
        }, cData);
    }

    /**
     * 发送延时队列到点取消团购订单
     *
     * @param payOrderSn 支付单号
     */
    public void sendDelayMessageCancelGroupOrder(String payOrderSn) {
        //获取订单超时时间
        long delayTimes = orderStting.getAutoCancelOrderTime(OrderConst.OrderType.GENERAL) * 60 * 1000;
        // 消息标识
        CorrelationData cData = new CorrelationData(CANCEL_ORDER_SIGN + payOrderSn);
        // 发送消息（参数：交换机、路由键、消息内容、消息标识）
        rabbitTemplate.convertAndSend(QueueEnum.Q_DELAY_ORDER_GROUP_CANCEL.getExChange(), QueueEnum.Q_DELAY_ORDER_GROUP_CANCEL.getRouteKey(), payOrderSn, message -> {
            //给消息设置延迟毫秒值
            message.getMessageProperties().setExpiration(String.valueOf(delayTimes));
            return message;
        }, cData);
    }

    /**
     * 发送延时队列到点取消秒杀订单
     *
     * @param payOrderSn 支付单号
     */
    public void sendDelayMessageCancelSeckillOrder(String payOrderSn) {
        //获取订单超时时间
        long delayTimes = orderStting.getAutoCancelOrderTime(OrderConst.OrderType.GENERAL) * 60 * 1000;
        // 消息标识
        CorrelationData cData = new CorrelationData(CANCEL_ORDER_SIGN + payOrderSn);
        // 发送消息（参数：交换机、路由键、消息内容、消息标识）
        rabbitTemplate.convertAndSend(QueueEnum.Q_DELAY_ORDER_SECKILL_CANCEL.getExChange(), QueueEnum.Q_DELAY_ORDER_SECKILL_CANCEL.getRouteKey(), payOrderSn, message -> {
            //给消息设置延迟毫秒值
            message.getMessageProperties().setExpiration(String.valueOf(delayTimes));
            return message;
        }, cData);
    }

    @Override
    public void confirm(CorrelationData cData, boolean ack, String cause) {
        // 这里的ack是Broker对发布者消息达到服务端的确认
        if (!ack) {
            log.error("[{}]--发送失败, 消息标识: {}, 原因: {}", QUEUE_INFO, cData.getId(), cause);
        }
    }
}
