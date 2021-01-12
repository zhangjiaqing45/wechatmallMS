package com.fante.framework.rabbitmq.exConfig;

import com.fante.framework.rabbitmq.enums.ExchangeEnum;
import com.fante.framework.rabbitmq.enums.QueueEnum;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: Fante
 * @Date:
 * @Author: Mr.Z
 * @Description: 直连交换机模式配置
 */
@Configuration
public class DirectExchangeConfig {

    /**
     * 定义直连交换机
     * @return
     */
    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(ExchangeEnum.EX_DIRECT.getName());
    }

    /**
     * 定义测试队列
     * @return
     */
    @Bean
    Queue testQueue() {
        return new Queue(QueueEnum.Q_TEST_DIRECT.getName());
    }


    /**
     * 将测试队列绑定到直连交换机
     * @param testQueue
     * @param directExchange
     * @return
     */
    @Bean
    Binding bindingWithTestQueue(Queue testQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(testQueue).to(directExchange).with(QueueEnum.Q_TEST_DIRECT.getRouteKey());
    }


    /**
     * 定义延时交换机
     * @return
     */
    @Bean
    public DirectExchange delayExchange(){
        return new DirectExchange(ExchangeEnum.EX_DELAY_DIRECT.getName());
    }

    /**
     * 定义测试延时队列
     * @return
     */
    @Bean
    public Queue testDelayQueue() {
        return QueueBuilder.durable(QueueEnum.Q_DELAY_TEST_DIRECT.getName())
                // 到期后转发的交换机
                .withArgument("x-dead-letter-exchange", QueueEnum.Q_DEAD_LETTER_TEST_DIRECT.getExChange())
                // 到期后转发的路由键
                .withArgument("x-dead-letter-routing-key", QueueEnum.Q_DEAD_LETTER_TEST_DIRECT.getRouteKey())
                .build();
    }

    /**
     * 测试延时队列绑定到延时交换机
     * @param testDelayQueue
     * @param delayExchange
     * @return
     */
    @Bean
    Binding bindingWithTestDelayQueue(Queue testDelayQueue, DirectExchange delayExchange) {
        return BindingBuilder.bind(testDelayQueue).to(delayExchange).with(QueueEnum.Q_DELAY_TEST_DIRECT.getRouteKey());
    }


    /**
     * 定义死信交换机
     * @return
     */
    @Bean
    public DirectExchange deadLetterExchange(){
        return new DirectExchange(ExchangeEnum.EX_DEAD_LETTER_DIRECT.getName());
    }


    /**
     * 定义测试死信队列，接收测试延时队列的消息
     * @return
     */
    @Bean
    public Queue testDeadLetterQueue() {
        return new Queue(QueueEnum.Q_DEAD_LETTER_TEST_DIRECT.getName());
    }

    /**
     * 测试死信队列绑定到死信交换机
     * @param testDeadLetterQueue
     * @param deadLetterExchange
     * @return
     */
    @Bean
    Binding bindingWithTestDeadLetterQueue(Queue testDeadLetterQueue, DirectExchange deadLetterExchange) {
        return BindingBuilder.bind(testDeadLetterQueue).to(deadLetterExchange).with(QueueEnum.Q_DEAD_LETTER_TEST_DIRECT.getRouteKey());
    }

}
