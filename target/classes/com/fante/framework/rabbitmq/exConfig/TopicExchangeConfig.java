//package com.fante.framework.rabbitmq.exConfig;
//
//import com.fante.framework.rabbitmq.enums.ExchangeEnum;
//import com.fante.framework.rabbitmq.enums.QueueEnum;
//import com.fante.framework.rabbitmq.enums.RouteKeyEnum;
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.core.TopicExchange;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @program: Fante
// * @Date:
// * @Author: Mr.Z
// * @Description: 主题交换机配置
// */
//@Configuration
//public class TopicExchangeConfig {
//
//    /**
//     * 定义主题交换机
//     * @return
//     */
//    @Bean
//    TopicExchange testTopicExchange() {
//        return new TopicExchange(ExchangeEnum.EX_TOPIC_TEST.getName());
//    }
//
//    /**
//     * 定义测试队列D
//     * @return
//     */
//    @Bean
//    Queue testTopicD() {
//        return new Queue(QueueEnum.Q_TEST_TOPIC_D.getName());
//    }
//
//    /**
//     * 定义测试队列E
//     * @return
//     */
//    @Bean
//    Queue testTopicE() {
//        return new Queue(QueueEnum.Q_TEST_TOPIC_E.getName());
//    }
//
//    /**
//     * 将测试队列D绑定到测试主题交换机，routingKey为topic.message，即完全匹配
//     * @param testTopicD
//     * @param testTopicExchange
//     * @return
//     */
//    @Bean
//    Binding bindingComplete(Queue testTopicD, TopicExchange testTopicExchange) {
//        return BindingBuilder.bind(testTopicD).to(testTopicExchange).with(RouteKeyEnum.TOPIC_COMPLETE.getName());
//    }
//
//    /**
//     * 将测试队列E绑定到测试主题交换机，routingKey为topic.#，即模糊匹配
//     * @param testTopicE
//     * @param testTopicExchange
//     * @return
//     */
//    @Bean
//    Binding bindingFuzzy(Queue testTopicE, TopicExchange testTopicExchange) {
//        return BindingBuilder.bind(testTopicE).to(testTopicExchange).with(RouteKeyEnum.TOPIC_FUZZY.getName());
//    }
//
//}
