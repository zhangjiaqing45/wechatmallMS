package com.fante.project.api.mq.receiver;

import com.rabbitmq.client.Channel;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @program: Fante
 * @Date: 2020/4/15 22:22
 * @Author: Mr.Z
 * @Description: 测试直连模式队列--消费者
 */
@Component
public class TestReceiver {

    private static Logger log = LoggerFactory.getLogger(TestReceiver.class);

    private static final String QUEUE_INFO = "测试队列";

    /**
     * 接受消息 <br/>
     * 注解@RabbitListener <br/>
     * queues 需要填写常量，也可以使用Spring SPEL表达式 <br/>
     * containerFactory 选择使用的模式
     * @param message
     * @param channel
     * @param msg 接收到的消息
     */
    @RabbitListener(queues = "#{testQueue.name}", containerFactory = "singleListenerContainer")
    public void receive(Message message, Channel channel, String msg ){
        long startTime = System.currentTimeMillis();
        try {
            log.info("队列: {}，接收内容：{}", QUEUE_INFO, msg);

            // 业务处理：1、调用处理流程；2、保存处理结果至Redis
            Thread.sleep(RandomUtils.nextInt(100, 1000));


            // 消息确认 (仅确认当前消息)
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            log.error("队列: {}，异常：{}", QUEUE_INFO, e.toString());
            // 保存异常信息至Redis

            try {
                // 出现异常，拒绝该消息
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            } catch (IOException e1) {
                log.error("队列: {}，拒绝消息异常：{}", QUEUE_INFO, e1.toString());
            }
        } finally {
            log.info("队列: {}，处理耗时：{} ms", QUEUE_INFO, (System.currentTimeMillis() - startTime));
        }
    }

}
