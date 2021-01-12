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
// * @Description: 测试扇形模式队列--生产者
// */
//@Component
//public class TestFanoutSender implements RabbitTemplate.ConfirmCallback {
//
//    private static Logger log = LoggerFactory.getLogger(TestFanoutSender.class);
//
//    @Autowired
//    RabbitTemplate rabbitTemplate;
//
//    public void send() {
//        rabbitTemplate.setConfirmCallback(this);
//
//        String msg = "I'm msg [ hello, world! ] Fanout!";
//        log.info("send: {}", msg);
//        CorrelationData cData = new CorrelationData("cData:" + IdGenerator.nextId());
//        rabbitTemplate.convertAndSend(QueueEnum.Q_TEST_FANOUT_F.getExChange(), "", msg, cData);
//    }
//
//    @Override
//    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//        if (ack) {
//            log.info("测试扇形模式--发送成功，消息标识：{}", correlationData.getId());
//        } else {
//            log.info("测试扇形模式--发送失败，消息标识：{}，原因：{}", correlationData.getId(), cause);
//        }
//    }
//}
