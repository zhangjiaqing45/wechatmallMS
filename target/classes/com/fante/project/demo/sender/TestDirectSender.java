//package com.fante.project.demo.sender;
//
//import com.fante.common.utils.idgen.IdGenerator;
//import com.fante.framework.rabbitmq.enums.QueueEnum;
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
// * @Description: 测试直连模式队列--生产者
// */
//@Component
//public class TestDirectSender implements RabbitTemplate.ConfirmCallback {
//
//    private static Logger log = LoggerFactory.getLogger(TestDirectSender.class);
//
//    @Autowired
//    RabbitTemplate rabbitTemplate;
//
//    public void send() {
//        rabbitTemplate.setConfirmCallback(this);
//
//        String msgA = "I'm msgA [q.test.direct.a] MSG!";
//        CorrelationData cDataA = new CorrelationData("cDataA:" + IdGenerator.nextId());
//        rabbitTemplate.convertAndSend(QueueEnum.Q_TEST_DIRECT_A.getExChange(), QueueEnum.Q_TEST_DIRECT_A.getRouteKey(), msgA, cDataA);
//
//        String msgB = "I'm msgB [q.test.direct.b] MSG!";
//        CorrelationData cDataB = new CorrelationData("cDataB:" + IdGenerator.nextId());
//        rabbitTemplate.convertAndSend(QueueEnum.Q_TEST_DIRECT_B.getExChange(), QueueEnum.Q_TEST_DIRECT_B.getRouteKey(), msgB, cDataB);
//
//    }
//
//    @Override
//    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//        if (ack) {
//            log.info("测试直连模式--发送成功，消息标识：{}", correlationData.getId());
//        } else {
//            log.info("测试直连模式--发送失败，消息标识：{}，原因：{}", correlationData.getId(), cause);
//        }
//    }
//}
