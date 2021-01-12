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
public class CancelOrderExchangeConfig {

/*************************************************定义延时交换机*********************************************************/
    /**
     * 延时取消订单-定义延时交换机-A_E
     *
     * @return
     */
    @Bean
    public DirectExchange delayCancelOrderExchange() {
        return new DirectExchange(ExchangeEnum.EX_DELAY_ORDER_CANCEL.getName());
    }
/*************************************************定义普通订单队列*********************************************************/
    /**
     * 延时取消订单-定义延时队列 并绑定 到期后的转发死信队列
     *
     * @return
     */
    @Bean
    public Queue delayCancelGeneralOrderQueue() {
        return QueueBuilder.durable(QueueEnum.Q_DELAY_ORDER_GENERAL_CANCEL.getName())
                // 到期后转发的交换机
                .withArgument("x-dead-letter-exchange", QueueEnum.Q_DEAD_ORDER_CANCEL.getExChange())
                // 到期后转发的路由键
                .withArgument("x-dead-letter-routing-key", QueueEnum.Q_DEAD_ORDER_CANCEL.getRouteKey())
                .build();
    }

    /**
     * 延时取消订单-延时队列绑定到延时交换机
     *
     * @param delayCancelGeneralOrderQueue
     * @param delayCancelOrderExchange
     * @return
     */
    @Bean
    Binding bindingWithCancelOrderQueueAndExchange(Queue delayCancelGeneralOrderQueue, DirectExchange delayCancelOrderExchange) {
        return BindingBuilder.bind(delayCancelGeneralOrderQueue).to(delayCancelOrderExchange).with(QueueEnum.Q_DELAY_ORDER_GENERAL_CANCEL.getRouteKey());
    }
/*************************************************定义秒杀订单队列*********************************************************/
    /**
     * 延时取消订单-定义延时队列 并绑定 到期后的转发死信队列
     *
     * @return
     */
    @Bean
    public Queue delayCancelSeckillOrderQueue() {
        return QueueBuilder.durable(QueueEnum.Q_DELAY_ORDER_SECKILL_CANCEL.getName())
                // 到期后转发的交换机
                .withArgument("x-dead-letter-exchange", QueueEnum.Q_DEAD_ORDER_CANCEL.getExChange())
                // 到期后转发的路由键
                .withArgument("x-dead-letter-routing-key", QueueEnum.Q_DEAD_ORDER_CANCEL.getRouteKey())
                .build();
    }

    /**
     * 延时取消订单-延时队列绑定到延时交换机
     *
     * @param delayCancelSeckillOrderQueue
     * @param delayCancelOrderExchange
     * @return
     */
    @Bean
    Binding bindingWithCancelSeckillOrderQueueAndExchange(Queue delayCancelSeckillOrderQueue, DirectExchange delayCancelOrderExchange) {
        return BindingBuilder.bind(delayCancelSeckillOrderQueue).to(delayCancelOrderExchange).with(QueueEnum.Q_DELAY_ORDER_SECKILL_CANCEL.getRouteKey());
    }
/*************************************************定义团购订单队列*********************************************************/
    /**
     * 延时取消订单-定义延时队列 并绑定 到期后的转发死信队列
     *
     * @return
     */
    @Bean
    public Queue delayCancelGroupOrderQueue() {
        return QueueBuilder.durable(QueueEnum.Q_DELAY_ORDER_GROUP_CANCEL.getName())
                // 到期后转发的交换机
                .withArgument("x-dead-letter-exchange", QueueEnum.Q_DEAD_ORDER_CANCEL.getExChange())
                // 到期后转发的路由键
                .withArgument("x-dead-letter-routing-key", QueueEnum.Q_DEAD_ORDER_CANCEL.getRouteKey())
                .build();
    }

    /**
     * 延时取消订单-延时队列绑定到延时交换机
     *
     * @param delayCancelGroupOrderQueue
     * @param delayCancelOrderExchange
     * @return
     */
    @Bean
    Binding bindingWithCancelGroupOrderQueueAndExchange(Queue delayCancelGroupOrderQueue, DirectExchange delayCancelOrderExchange) {
        return BindingBuilder.bind(delayCancelGroupOrderQueue).to(delayCancelOrderExchange).with(QueueEnum.Q_DELAY_ORDER_GROUP_CANCEL.getRouteKey());
    }
/*************************************************定义死信交换机*********************************************************/
    /**
     * 延时取消订单-定义死信交换机
     *
     * @return
     */
    @Bean
    public DirectExchange deadCancelOrderExchange() {
        return new DirectExchange(ExchangeEnum.EX_DEAD_ORDER_CANCEL.getName());
    }


    /**
     * 延时取消订单-定义死信队列，接收延时队列的消息
     *
     * @return
     */
    @Bean
    public Queue deadCancelOrderQueue() {
        return new Queue(QueueEnum.Q_DEAD_ORDER_CANCEL.getName());
    }

    /**
     * 延时取消订单-死信队列绑定到死信交换机
     *
     * @param deadCancelOrderQueue
     * @param deadCancelOrderExchange
     * @return
     */
    @Bean
    Binding bindingWithCancelOrderDeadQueueAndExchange(Queue deadCancelOrderQueue, DirectExchange deadCancelOrderExchange) {
        return BindingBuilder.bind(deadCancelOrderQueue).to(deadCancelOrderExchange).with(QueueEnum.Q_DEAD_ORDER_CANCEL.getRouteKey());
    }

}
