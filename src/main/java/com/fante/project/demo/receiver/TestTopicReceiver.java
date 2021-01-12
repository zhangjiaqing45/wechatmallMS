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
// * @Description: 测试主题模式队列--消费者
// */
//@Component
//public class TestTopicReceiver {
//
//    private static Logger log = LoggerFactory.getLogger(TestTopicReceiver.class);
//
//    /**
//     * 接收测试队列D消息，完全匹配
//     * @param message
//     * @param channel
//     * @param msg
//     */
//    @RabbitListener(queues = "#{testTopicD.name}", containerFactory = "singleListenerContainer")
//    public void receiveComplete(Message message, Channel channel, String msg) {
//
//        long startTime = System.currentTimeMillis();
//        try {
//            log.info("队列：测试队列D，接收内容：{}", msg);
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
//                log.error("队列：测试队列D，拒绝消息，异常：{}", e1.toString());
//            }
//        } finally {
//            log.info("队列：测试队列D，处理耗时：{}", (System.currentTimeMillis() - startTime));
//        }
//    }
//
//    /**
//     * 接收测试队列E消息，模糊匹配
//     * @param message
//     * @param channel
//     * @param msg
//     */
//    @RabbitListener(queues = "#{testTopicE.name}", containerFactory = "singleListenerContainer")
//    public void receiveFuzzy (Message message, Channel channel, String msg) {
//
//        long startTime = System.currentTimeMillis();
//        try {
//            log.info("队列：测试队列E，接收内容：{}", msg);
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
//                log.error("队列：测试队列E，拒绝消息，异常：{}", e1.toString());
//            }
//        } finally {
//            log.info("队列：测试队列E，处理耗时：{}", (System.currentTimeMillis() - startTime));
//        }
//    }
//
//}
