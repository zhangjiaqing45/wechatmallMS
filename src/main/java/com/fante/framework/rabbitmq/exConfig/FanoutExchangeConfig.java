//package com.fante.framework.rabbitmq.exConfig;
//
//import com.fante.framework.rabbitmq.enums.ExchangeEnum;
//import com.fante.framework.rabbitmq.enums.QueueEnum;
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.FanoutExchange;
//import org.springframework.amqp.core.Queue;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @program: Fante
// * @Date:
// * @Author: Mr.Z
// * @Description: 扇形交换机配置
// */
//@Configuration
//public class FanoutExchangeConfig {
//
//    /**
//     * 定义扇形交换机，扇形交换机会把消息投递给所有绑定了它的队列，而这个与routing_key（路由键）无关
//     * @return
//     */
//    @Bean
//    FanoutExchange testFanoutExchange() {
//        return new FanoutExchange(ExchangeEnum.EX_FANOUT_TEST.getName());
//    }
//
//    /**
//     * 定义测试队列F
//     * @return
//     */
//    @Bean
//    Queue testFanoutF() {
//        return new Queue(QueueEnum.Q_TEST_FANOUT_F.getName());
//    }
//
//    /**
//     * 定义测试队列G
//     * @return
//     */
//    @Bean
//    Queue testFanoutG() {
//        return new Queue(QueueEnum.Q_TEST_FANOUT_G.getName());
//    }
//
//    /**
//     * 将测试队列F绑定到测试扇形交换机
//     * @param testFanoutF
//     * @param testFanoutExchange
//     * @return
//     */
//    @Bean
//    Binding bindingWithQueueF(Queue testFanoutF, FanoutExchange testFanoutExchange) {
//        return BindingBuilder.bind(testFanoutF).to(testFanoutExchange);
//    }
//
//    /**
//     * 将测试队列G绑定到测试扇形交换机
//     * @param testFanoutG
//     * @param testFanoutExchange
//     * @return
//     */
//    @Bean
//    Binding bindingWithQueueG(Queue testFanoutG, FanoutExchange testFanoutExchange) {
//        return BindingBuilder.bind(testFanoutG).to(testFanoutExchange);
//    }
//}
