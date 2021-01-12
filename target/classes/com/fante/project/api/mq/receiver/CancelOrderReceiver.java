package com.fante.project.api.mq.receiver;

import com.fante.project.business.omsOrder.service.IOmsOrderService;
import com.fante.project.business.omsOrder.service.impl.OmsCancelOrderOperationService;
import com.fante.project.business.omsOrder.service.impl.OmsCancelOrderService;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author: Fante
 * @Description: 测试直连模式队列--消费者
 */
@Component
public class CancelOrderReceiver {

    private static Logger log = LoggerFactory.getLogger(CancelOrderReceiver.class);

    private static final String QUEUE_INFO = "延时取消订单";
    /**
     * 订单
     */
    @Autowired
    private OmsCancelOrderOperationService cancelOrderService;

    /**
     * 接受消息取消超时订单 <br/>
     * 注解@RabbitListener <br/>
     * queues 需要填写常量，也可以使用Spring SPEL表达式 <br/>
     * containerFactory 选择使用的模式
     *
     * @param message
     * @param channel
     * @param paySn   支付单sn
     */
    @RabbitListener(queues = "#{deadCancelOrderQueue.name}", containerFactory = "singleListenerContainer")
    public void receive(Message message, Channel channel, String paySn) {
        try {
            cancelOrderService.cancleOutTimeOmsOrder(paySn);
            // 消息确认 (仅确认当前消息)
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            log.error("处理订单: {}时,队列: {},异常：{}", paySn, QUEUE_INFO, e.toString());
            try {
                // 出现异常，拒绝该消息
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            } catch (IOException e1) {
                log.error("队列: {}，拒绝消息异常：{}", QUEUE_INFO, e1.toString());
            }
        }
    }

}
