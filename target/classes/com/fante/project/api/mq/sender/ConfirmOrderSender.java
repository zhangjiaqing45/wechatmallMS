package com.fante.project.api.mq.sender;

import com.fante.common.business.enums.OrderConst;
import com.fante.common.utils.idgen.IdGenerator;
import com.fante.framework.redis.RedisUtil;
import com.fante.project.api.mq.enums.QueueEnum;
import com.fante.project.api.mq.req.JoinGroupHandleParam;
import com.fante.project.api.mq.req.OrderHandleOfCartParam;
import com.fante.project.api.mq.req.OrderHandleParam;
import com.fante.project.api.mq.resp.OrderHandleResult;
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
public class ConfirmOrderSender implements RabbitTemplate.ConfirmCallback {

    private static Logger log = LoggerFactory.getLogger(ConfirmOrderSender.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;


    private static final String QUEUE_INFO = "确认订单";
    /**
     * 普通订单
     */
    private static final String CDATA_GENERAL_SIGN = "general:orderSn:";
    /**
     * 秒杀订单
     */
    private static final String CDATA_SECKILL_SIGN = "seckill:orderSn:";
    /**
     * 团购订单
     */
    private static final String CDATA_GROUP_SIGN = "group:orderSn:";
    /**
     * 组团动作
     */
    private static final String CDATA_JOIN_SIGN = "paysuccessOfHandleGroupOrder:orderSn:";

    /**
     * 普通订单购物车下单-发送消息
     *
     * @param info <br>
     *             消息为Object, 可接收自定义对象
     */
    public void sendGeneralOrderByCart(OrderHandleOfCartParam info) {
        rabbitTemplate.setConfirmCallback(this);
        // 消息标识
        CorrelationData cData = new CorrelationData(CDATA_GENERAL_SIGN + info.getPayOrder().getPayOrderSn());
        // 发送消息（参数：交换机、路由键、消息内容、消息标识）
        rabbitTemplate.convertAndSend(QueueEnum.Q_DRIECT_ORDER_GENERAL.getExChange(), QueueEnum.Q_DRIECT_ORDER_GENERAL.getRouteKey(), info, cData);
    }
    /**
     * 普通订单下单-发送消息
     * @param info <br>
     * 消息为Object, 可接收自定义对象
     *//*
    public void sendGeneralOrder(OrderHandleOfCartParam info) {
        rabbitTemplate.setConfirmCallback(this);
        // 消息标识
        CorrelationData cData = new CorrelationData(CDATA_GENERAL_SIGN + info.getPayOrder().getPayOrderSn());
        // 发送消息（参数：交换机、路由键、消息内容、消息标识）
        rabbitTemplate.convertAndSend(QueueEnum.Q_DRIECT_ORDER_GENERAL.getExChange(), QueueEnum.Q_DRIECT_ORDER_GENERAL.getRouteKey(), info, cData);
    }*/

    /**
     * 秒杀订单下单-发送消息
     *
     * @param info <br>
     *             消息为Object, 可接收自定义对象
     */
    public void sendSeckillOrder(OrderHandleParam info) {
        rabbitTemplate.setConfirmCallback(this);
        // 消息标识
        CorrelationData cData = new CorrelationData(CDATA_SECKILL_SIGN + info.getPayOrder().getPayOrderSn());
        // 发送消息（参数：交换机、路由键、消息内容、消息标识）
        rabbitTemplate.convertAndSend(QueueEnum.Q_DRIECT_ORDER_SECKILL.getExChange(), QueueEnum.Q_DRIECT_ORDER_SECKILL.getRouteKey(), info, cData);
    }

    /**
     * 团购订单下单-发送消息
     *
     * @param info <br>
     *             消息为Object, 可接收自定义对象
     */
    public void sendGroupOrder(OrderHandleParam info) {
        rabbitTemplate.setConfirmCallback(this);
        // 消息标识
        CorrelationData cData = new CorrelationData(CDATA_GROUP_SIGN + info.getPayOrder().getPayOrderSn());
        // 发送消息（参数：交换机、路由键、消息内容、消息标识）
        rabbitTemplate.convertAndSend(QueueEnum.Q_DRIECT_ORDER_GROUP.getExChange(), QueueEnum.Q_DRIECT_ORDER_GROUP.getRouteKey(), info, cData);
    }

    /**
     * 支付成功后-处理团购订单
     *
     * @param info <br>
     *             消息为Object, 可接收自定义对象
     */
    public void sendPaySuccessOfGroup(JoinGroupHandleParam info) {
        rabbitTemplate.setConfirmCallback(this);
        // 消息标识
        CorrelationData cData = new CorrelationData(CDATA_JOIN_SIGN + info.getGroupMember().getOrderSn());
        // 发送消息（参数：交换机、路由键、消息内容、消息标识）
        rabbitTemplate.convertAndSend(QueueEnum.Q_DRIECT_ORDER_JOIN_GROUP.getExChange(), QueueEnum.Q_DRIECT_ORDER_JOIN_GROUP.getRouteKey(), info, cData);
    }

    @Override
    public void confirm(CorrelationData cData, boolean ack, String cause) {
        // 这里的ack是Broker对发布者消息达到服务端的确认
        if (!ack) {
            log.error("[{}]--发送失败, 消息标识: {}, 原因: {}", QUEUE_INFO, cData.getId(), cause);
        }
    }


}
