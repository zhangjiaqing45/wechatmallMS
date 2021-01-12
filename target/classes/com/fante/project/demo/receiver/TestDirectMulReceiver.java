//package com.fante.project.demo.receiver;
//
//import com.rabbitmq.client.Channel;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
///**
// * @program: Fante
// * @Date:
// * @Author: Mr.Z
// * @Description: 测试直连模式多人消费队列--消费者
// */
//@Component
//public class TestDirectMulReceiver {
//
//    private static Logger log = LoggerFactory.getLogger(TestDirectMulReceiver.class);
//
//    /**
//     * 接收测试队列C消息
//     * 注解@RabbitListener中
//     * queues 需要填写常量，也可以使用Spring SPEL表达式
//     * containerFactory 选择使用的模式
//     * @param message
//     * @param channel
//     * @param msg
//     */
//    @RabbitListener(queues = "#{testMulDirectC.name}", containerFactory = "multiListenerContainer")
//    public void receiveC (Message message, Channel channel, String msg) {
//
//        long startTime = System.currentTimeMillis();
//        try {
//            log.info("队列：测试队列C，接收内容：{}", msg);
//            // 模拟业务处理
//            Thread.sleep(1000);
//            // 消息确认
//            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//        } catch (Exception e) {
//            log.error(e.toString());
//            try {
//                // 出现异常，拒绝该消息
//                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
//            } catch (IOException e1) {
//                log.error("队列：测试队列C，拒绝消息，异常：{}", e1.toString());
//            }
//        } finally {
//            log.info("队列：测试队列C，处理耗时：{}", (System.currentTimeMillis() - startTime));
//        }
//    }
//
//}
