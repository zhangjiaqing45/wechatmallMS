package com.fante.project.api.mq.exConfig;

import com.fante.project.api.mq.enums.ExchangeEnum;
import com.fante.project.api.mq.enums.QueueEnum;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Fante
 * @Date:
 * @Description: 团购失效延时队列的配置
 */
@Configuration
public class CancelGroupAgingOutTimeExchangeConfig {

/*************************************************共用 EX_DELAY_ORDER_CANCEL 延时交换机*********************************************************/
/*************************************************定义团购失效延时队列*********************************************************/
    /**
     * 团购失效延时队列-定义延时队列 并绑定 到期后的转发死信队列
     *
     * @return
     */
    @Bean
    public Queue delayCancelGroupQueue() {
        return QueueBuilder.durable(QueueEnum.Q_DELAY_GROUP_CANCEL.getName())
                // 到期后转发的交换机
                .withArgument("x-dead-letter-exchange", QueueEnum.Q_DEAD_ORDER_CANCEL.getExChange())
                // 到期后转发的路由键
                .withArgument("x-dead-letter-routing-key", QueueEnum.Q_DEAD_ORDER_CANCEL.getRouteKey())
                .build();
    }

    /**
     * 延时取消订单-延时队列绑定到延时交换机
     *
     * @param delayCancelGroupQueue
     * @param delayCancelOrderExchange
     * @return
     */
    @Bean
    Binding bindingWithCancelOrderQueueAndExchange(Queue delayCancelGroupQueue, DirectExchange delayCancelOrderExchange) {
        return BindingBuilder.bind(delayCancelGroupQueue).to(delayCancelOrderExchange).with(QueueEnum.Q_DELAY_GROUP_CANCEL.getRouteKey());
    }
/*************************************************定义死信交换机*********************************************************/
    /**
     * 延时取消组团-定义死信交换机
     *
     * @return
     */
    @Bean
    public DirectExchange deadCancelGroupExchange() {
        return new DirectExchange(ExchangeEnum.Q_DEAD_GROUP_CANCEL.getName());
    }


    /**
     * 延时取消组团-定义死信队列，接收延时队列的消息
     *
     * @return
     */
    @Bean
    public Queue deadCancelGroupQueue() {
        return new Queue(QueueEnum.Q_DEAD_GROUP_CANCEL.getName());
    }

    /**
     * 延时取消组团-死信队列绑定到死信交换机
     *
     * @param deadCancelGroupQueue
     * @param deadCancelGroupExchange
     * @return
     */
    @Bean
    Binding bindingWithCancelGroupDeadQueueAndExchange(Queue deadCancelGroupQueue, DirectExchange deadCancelGroupExchange) {
        return BindingBuilder.bind(deadCancelGroupQueue).to(deadCancelGroupExchange).with(QueueEnum.Q_DEAD_GROUP_CANCEL.getRouteKey());
    }

}
