package com.fante.project.api.mq.sender;

import com.fante.common.utils.StringUtils;
import com.fante.common.utils.idgen.IdGenerator;
import com.fante.framework.rabbitmq.enums.QueueEnum;
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
public class TestSender implements RabbitTemplate.ConfirmCallback {

    private static Logger log = LoggerFactory.getLogger(TestSender.class);

    @Autowired
    RabbitTemplate rabbitTemplate;

    private static final String QUEUE_INFO = "测试队列";
    private static final String CDATA_SIGN = "test::";

    /**
     * 发送消息
     * @param info <br>
     * 消息为Object, 可接收自定义对象
     */
    public void send(String info) {
        rabbitTemplate.setConfirmCallback(this);
        // 消息内容
        String msg = StringUtils.format("send info is :: {} !", info);
        // 消息标识
        CorrelationData cData = new CorrelationData(CDATA_SIGN + IdGenerator.nextId());
        // 发送消息（参数：交换机、路由键、消息内容、消息标识）
        rabbitTemplate.convertAndSend(QueueEnum.Q_TEST_DIRECT.getExChange(), QueueEnum.Q_TEST_DIRECT.getRouteKey(), msg, cData);
    }


    @Override
    public void confirm(CorrelationData cData, boolean ack, String cause) {
        // 这里的ack是Broker对发布者消息达到服务端的确认
        if (!ack) {
            log.error("[{}]--发送失败, 消息标识: {}, 原因: {}", QUEUE_INFO, cData.getId(), cause);
        }
    }
}
