package com.fante.project.api.mq.exConfig;

import com.fante.project.api.mq.enums.ExchangeEnum;
import com.fante.project.api.mq.enums.QueueEnum;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Fante
 * @Date:
 * @Description: 延时取消订单队列交换机的配置
 */
@Configuration
public class ConfirmOrderExchangeConfig {

/*************************************************定义确认订单交换机*********************************************************/
    /**
     * 确认订单-定义直连交换机
     *
     * @return
     */
    @Bean
    DirectExchange confirmOrderExchange() {
        return new DirectExchange(ExchangeEnum.EX_DIRECT_ORDER.getName());
    }
/*************************************************定义普通订单队列*********************************************************/
    /**
     * 普通订单-定义队列
     *
     * @return
     */
    @Bean
    Queue confirmGeneralOrderQueue() {
        return new Queue(QueueEnum.Q_DRIECT_ORDER_GENERAL.getName());
    }

    /**
     * 普通订单-将队列绑定到直连交换机
     *
     * @param confirmGeneralOrderQueue
     * @param confirmOrderExchange
     * @return
     */
    @Bean
    Binding bindingWithConfrimGeneralOrderQueueAndExchange(Queue confirmGeneralOrderQueue, DirectExchange confirmOrderExchange) {
        return BindingBuilder.bind(confirmGeneralOrderQueue).to(confirmOrderExchange).with(QueueEnum.Q_DRIECT_ORDER_GENERAL.getRouteKey());
    }

/*************************************************定义秒杀订单队列*********************************************************/

    /**
     * 秒杀订单-定义队列
     *
     * @return
     */
    @Bean
    Queue confirmSeckillOrderQueue() {
        return new Queue(QueueEnum.Q_DRIECT_ORDER_SECKILL.getName());
    }

    /**
     * 秒杀订单-将队列绑定到直连交换机
     *
     * @param confirmSeckillOrderQueue
     * @param confirmOrderExchange
     * @return
     */
    @Bean
    Binding bindingWithConfrimSeckillOrderQueueAndExchange(Queue confirmSeckillOrderQueue, DirectExchange confirmOrderExchange) {
        return BindingBuilder.bind(confirmSeckillOrderQueue).to(confirmOrderExchange).with(QueueEnum.Q_DRIECT_ORDER_SECKILL.getRouteKey());
    }

/*************************************************定义团购订单队列*********************************************************/

    /**
     * 团购订单-定义队列
     *
     * @return
     */
    @Bean
    Queue confirmGroupOrderQueue() {
        return new Queue(QueueEnum.Q_DRIECT_ORDER_GROUP.getName());
    }

    /**
     * 团购订单-将队列绑定到直连交换机
     *
     * @param confirmGroupOrderQueue
     * @param confirmOrderExchange
     * @return
     */
    @Bean
    Binding bindingWithConfrimGroupOrderQueueAndExchange(Queue confirmGroupOrderQueue, DirectExchange confirmOrderExchange) {
        return BindingBuilder.bind(confirmGroupOrderQueue).to(confirmOrderExchange).with(QueueEnum.Q_DRIECT_ORDER_GROUP.getRouteKey());
    }
/*************************************************定义加入组团或者创建新团的队列*********************************************************/

    /**
     * 团购组团操作-定义队列
     *
     * @return
     */
    @Bean
    Queue confirmJoinGroupQueue() {
        return new Queue(QueueEnum.Q_DRIECT_ORDER_JOIN_GROUP.getName());
    }

    /**
     * 团购组团操作-将队列绑定到直连交换机
     *
     * @param confirmJoinGroupQueue
     * @param confirmOrderExchange
     * @return
     */
    @Bean
    Binding bindingWithConfrimJoinGroupQueueAndExchange(Queue confirmJoinGroupQueue, DirectExchange confirmOrderExchange) {
        return BindingBuilder.bind(confirmJoinGroupQueue).to(confirmOrderExchange).with(QueueEnum.Q_DRIECT_ORDER_JOIN_GROUP.getRouteKey());
    }


}
