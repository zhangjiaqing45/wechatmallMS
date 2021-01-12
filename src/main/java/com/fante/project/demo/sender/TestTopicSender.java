//package com.fante.project.demo.sender;
//
//import com.fante.common.utils.idgen.IdGenerator;
//import com.fante.framework.rabbitmq.enums.QueueEnum;
//import com.fante.framework.rabbitmq.enums.RouteKeyEnum;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.amqp.rabbit.connection.CorrelationData;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
///**
// * @program: Fante
// * @Date:
// * @Author: Mr.Z
// * @Description: 测试主题模式队列--生产者
// */
//@Component
//public class TestTopicSender implements RabbitTemplate.ConfirmCallback {
//
//    private static Logger log = LoggerFactory.getLogger(TestTopicSender.class);
//
//    @Autowired
//    RabbitTemplate rabbitTemplate;
//
//    public void send() {
//        rabbitTemplate.setConfirmCallback(this);
//
//        String msgD = "I'm msgD [routeKey: " + RouteKeyEnum.TOPIC_COMPLETE.getName() + " ] MSG!";
//        log.info("sendD: {}", msgD);
//        CorrelationData cDataD = new CorrelationData("cDataD:" + IdGenerator.nextId());
//        rabbitTemplate.convertAndSend(QueueEnum.Q_TEST_TOPIC_D.getExChange(), RouteKeyEnum.TOPIC_COMPLETE.getName(), msgD, cDataD);
//
//        String msgE = "I'm msgE [routeKey: " + RouteKeyEnum.TOPIC_FUZZY.getName() + " ] MSG!";
//        log.info("sendE: {}", msgE);
//        CorrelationData cDataE = new CorrelationData("cDataE:" + IdGenerator.nextId());
//        rabbitTemplate.convertAndSend(QueueEnum.Q_TEST_TOPIC_E.getExChange(), RouteKeyEnum.TOPIC_FUZZY.getName(), msgE, cDataE);
//
//    }
//
//    @Override
//    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//        if (ack) {
//            log.info("测试主题模式--发送成功，消息标识：{}", correlationData.getId());
//        } else {
//            log.info("测试主题模式--发送失败，消息标识：{}，原因：{}", correlationData.getId(), cause);
//        }
//    }
//}
