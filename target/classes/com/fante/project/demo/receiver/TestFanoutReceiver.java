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
// * @Description: 测试扇形模式队列--消费者
// */
//@Component
//public class TestFanoutReceiver {
//
//    private static Logger log = LoggerFactory.getLogger(TestFanoutReceiver.class);
//
//    /**
//     * 接收测试队列F消息
//     * @param message
//     * @param channel
//     * @param msg
//     */
//    @RabbitListener(queues = "#{testFanoutF.name}", containerFactory = "singleListenerContainer")
//    public void receiveF(Message message, Channel channel, String msg) {
//
//        long startTime = System.currentTimeMillis();
//        try {
//            log.info("队列：测试队列F，接收内容：{}", msg);
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
//                log.error("队列：测试队列F，拒绝消息，异常：{}", e1.toString());
//            }
//        } finally {
//            log.info("队列：测试队列F，处理耗时：{}", (System.currentTimeMillis() - startTime));
//        }
//    }
//
//    /**
//     * 接收测试队列G消息，模糊匹配
//     * @param message
//     * @param channel
//     * @param msg
//     */
//    @RabbitListener(queues = "#{testFanoutG.name}", containerFactory = "singleListenerContainer")
//    public void receiveG (Message message, Channel channel, String msg) {
//
//        long startTime = System.currentTimeMillis();
//        try {
//            log.info("队列：测试队列G，接收内容：{}", msg);
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
//                log.error("队列：测试队列G，拒绝消息，异常：{}", e1.toString());
//            }
//        } finally {
//            log.info("队列：测试队列G，处理耗时：{}", (System.currentTimeMillis() - startTime));
//        }
//    }
//
//}
