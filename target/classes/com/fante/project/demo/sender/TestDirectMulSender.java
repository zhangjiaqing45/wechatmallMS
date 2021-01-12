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
// * @Description: 测试直连模式多人消费队列--生产者
// */
//@Component
//public class TestDirectMulSender implements RabbitTemplate.ConfirmCallback {
//
//    private static Logger log = LoggerFactory.getLogger(TestDirectMulSender.class);
//
//    @Autowired
//    RabbitTemplate rabbitTemplate;
//
//    public void send(int times) {
//        rabbitTemplate.setConfirmCallback(this);
//
//        String msgC = "I'm msgC -- " + times + " -- [q.test.direct.c] MSG!";
//        CorrelationData cDataC = new CorrelationData("cDataC:" + IdGenerator.nextId() + "-" + times);
//        rabbitTemplate.convertAndSend(QueueEnum.Q_TEST_DIRECT_C.getExChange(), QueueEnum.Q_TEST_DIRECT_C.getRouteKey(), msgC, cDataC);
//
//    }
//
//    @Override
//    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//        if (ack) {
//            //log.info("测试直连模式多人消费--发送成功，消息标识：{}", correlationData.getId());
//        } else {
//            log.info("测试直连模式多人消费--发送失败，消息标识：{}，原因：{}", correlationData.getId(), cause);
//        }
//    }
//}
