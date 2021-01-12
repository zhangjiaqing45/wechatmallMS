package com.fante.project.api.mq.receiver;

import com.fante.common.business.enums.OrderConst;
import com.fante.common.exception.BusinessException;
import com.fante.common.utils.Checker;
import com.fante.common.utils.StringUtils;
import com.fante.common.utils.bean.BeanUtils;
import com.fante.framework.redis.RedisUtil;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.project.api.mq.req.JoinGroupHandleParam;
import com.fante.project.api.mq.req.OrderHandleOfCartParam;
import com.fante.project.api.mq.req.OrderHandleParam;
import com.fante.project.api.mq.resp.OrderHandleResult;
import com.fante.project.api.omsOrderProcess.service.IOmsConfirmOrderService;
import com.fante.project.api.omsOrderProcess.service.OmsOrderCallBackService;
import com.fante.project.api.utils.OrderRedis;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @program: Fante
 * @Date: 2020/4/15 22:22
 * @Author: Mr.Z
 * @Description: 测试直连模式队列--消费者
 */
@Component
public class ConfirmOrderReceiver {

    private static Logger log = LoggerFactory.getLogger(ConfirmOrderReceiver.class);
    /**
     * 确认下单服务
     */
    @Autowired
    private IOmsConfirmOrderService iOmsConfirmOrderService;
    /**
     * 付款回掉
     */
    @Autowired
    private OmsOrderCallBackService omsOrderCallBackService;
    /**
     * redis工具
     */
    @Autowired
    private OrderRedis orderRedis;

    /**
     * 确认订单队列信息
     */
    private static final String QUEUE_INFO = "确认订单";
    /**
     * 支付成功后组团处理 队列信息
     */
    private static final String QUEUE_GROUP_INFO = "支付成功-组团处理";

    /**
     * 确认普通订单-接受消息 <br/>
     * 注解@RabbitListener <br/>
     * queues 需要填写常量，也可以使用Spring SPEL表达式 <br/>
     * containerFactory 选择使用的模式
     *
     * @param message
     * @param channel
     * @param param   接收到的消息
     */
    @RabbitListener(queues = "#{confirmGeneralOrderQueue.name}", containerFactory = "singleListenerContainer")
    public void gerenalOrderOfCartReceive(Message message, Channel channel, OrderHandleOfCartParam param) {
        log.info("收到消息了....");
        System.out.println("paramgetPaymentType="+param.getPaymentType());
        long startTime = System.currentTimeMillis();
        Long memberId = param.getPayOrder().getMemberId();
        try {
            String payOrderSn = "";
            log.info("队列: {}，接收内容：{}", QUEUE_INFO, BeanUtils.beanToString(param));
            if (StringUtils.equals(param.getCartType(), OrderConst.CartType.CART.getType())) {
                payOrderSn = iOmsConfirmOrderService.completeGeneralOrderOfCart(param);
            } else if (StringUtils.equals(param.getCartType(), OrderConst.CartType.BUY_NOW.getType())) {
                //立即购买有且只有一个商品的订单信息
                OrderHandleParam handleParam = param.getOrderHandleList().get(0);
                payOrderSn = iOmsConfirmOrderService.completeGeneralOrderOfNow(handleParam.getPayOrder(),
                        handleParam.getOrder(),
                        handleParam.getOrderItemList(),
                        handleParam.getStockList(),
                        handleParam.getCartIds(),
                        handleParam.getPromotionId(),
                        param.getPaymentType());
            } else {
                throw new BusinessException(AjaxResult.Type.ERROR.value(), "订单类型异常，类型值:" + param.getCartType());
            }
            //设置redis中订单状态
            orderRedis.setOrderRedis(memberId, OrderHandleResult.success(payOrderSn));
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            log.error("队列: {}，异常：{}", QUEUE_INFO, e.toString());
            // 保存异常信息至Redis
            orderRedis.setOrderRedis(memberId, OrderHandleResult.error(e.getMessage()));
            try {
                // 出现异常，拒绝该消息
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            } catch (IOException e1) {
                log.error("队列: {}，拒绝消息异常：{}", QUEUE_INFO, e1.toString());
            }
        } finally {
            log.info("队列: {}，处理耗时：{} ms", QUEUE_INFO, (System.currentTimeMillis() - startTime));
        }
    }


    /**
     * 确认秒杀订单-接受消息 <br/>
     * 注解@RabbitListener <br/>
     * queues 需要填写常量，也可以使用Spring SPEL表达式 <br/>
     * containerFactory 选择使用的模式
     *
     * @param message
     * @param channel
     * @param param   接收到的消息
     */
    @RabbitListener(queues = "#{confirmSeckillOrderQueue.name}", containerFactory = "singleListenerContainer")
    public void seckillOrderReceive(Message message, Channel channel, OrderHandleParam param) {
        long startTime = System.currentTimeMillis();
        Long memberId = param.getPayOrder().getMemberId();
        try {
            log.info("队列: {}，接收内容：{}", QUEUE_INFO, BeanUtils.beanToString(param));

            String payOrderSn = iOmsConfirmOrderService.completeFlashOrder(param.getPayOrder(), param.getOrder(), param.getOrderItemList(), param.getStockList(), param.getPromotionId());
            orderRedis.setOrderRedis(memberId, OrderHandleResult.success(payOrderSn));

            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            log.error("队列: {}，异常：{}", QUEUE_INFO, e.toString());
            // 保存异常信息至Redis
            orderRedis.setOrderRedis(memberId, OrderHandleResult.error(e.getMessage()));
            try {
                // 出现异常，拒绝该消息
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            } catch (IOException e1) {
                log.error("队列: {}，拒绝消息异常：{}", QUEUE_INFO, e1.toString());
            }
        } finally {
            log.info("队列: {}，处理耗时：{} ms", QUEUE_INFO, (System.currentTimeMillis() - startTime));
        }
    }

    /**
     * 确认团购订单-接受消息 <br/>
     * 注解@RabbitListener <br/>
     * queues 需要填写常量，也可以使用Spring SPEL表达式 <br/>
     * containerFactory 选择使用的模式
     *
     * @param message
     * @param channel
     * @param param   接收到的消息
     */
    @RabbitListener(queues = "#{confirmGroupOrderQueue.name}", containerFactory = "singleListenerContainer")
    public void groupOrderReceive(Message message, Channel channel, OrderHandleParam param) {
        long startTime = System.currentTimeMillis();
        Long memberId = param.getPayOrder().getMemberId();
        try {
            log.info("队列: {}，接收内容：{}", QUEUE_INFO, BeanUtils.beanToString(param));
            String payOrderSn = iOmsConfirmOrderService.completeGroupOrder(param.getPayOrder(), param.getOrder(), param.getOrderItemList(), param.getStockList(), param.getPromotionId());
            orderRedis.setOrderRedis(memberId, OrderHandleResult.success(payOrderSn));
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            log.error("队列: {}，异常：{}", QUEUE_INFO, e.toString());
            // 保存异常信息至Redis
            orderRedis.setOrderRedis(memberId, OrderHandleResult.error(e.getMessage()));
            try {
                // 出现异常，拒绝该消息
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            } catch (IOException e1) {
                log.error("队列: {}，拒绝消息异常：{}", QUEUE_INFO, e1.toString());
            }
        } finally {
            log.info("队列: {}，处理耗时：{} ms", QUEUE_INFO, (System.currentTimeMillis() - startTime));
        }
    }

    /**
     * 支付成功加入或创建组团-接受消息 <br/>
     * 注解@RabbitListener <br/>
     * queues 需要填写常量，也可以使用Spring SPEL表达式 <br/>
     * containerFactory 选择使用的模式
     *
     * @param message
     * @param channel
     * @param param   接收到的消息
     */
    @RabbitListener(queues = "#{confirmJoinGroupQueue.name}", containerFactory = "singleListenerContainer")
    public void joinGroupReceive(Message message, Channel channel, JoinGroupHandleParam param) {
        long startTime = System.currentTimeMillis();
        try {
            log.info("队列: {}，接收内容：{}", QUEUE_GROUP_INFO, BeanUtils.beanToString(param));
            omsOrderCallBackService.paySuccessOfGroup(param);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            log.error("队列: {}，异常：{}", QUEUE_GROUP_INFO, e.toString());
            try {
                // 出现异常，拒绝该消息
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            } catch (IOException e1) {
                log.error("队列: {}，拒绝消息异常：{}", QUEUE_GROUP_INFO, e1.toString());
            }
        } finally {
            log.info("队列: {}，处理耗时：{} ms", QUEUE_GROUP_INFO, (System.currentTimeMillis() - startTime));
        }
    }


    /**
     * 确认普通订单-接受消息 <br/>
     * 注解@RabbitListener <br/>
     * queues 需要填写常量，也可以使用Spring SPEL表达式 <br/>
     * containerFactory 选择使用的模式
     * @param message
     * @param channel
     * @param param 接收到的消息

     @RabbitListener(queues = "#{confirmGeneralOrderQueue.name}", containerFactory = "singleListenerContainer")
     public void gerenalOrderReceive(Message message, Channel channel, OrderHandleParam param){
     log.info("收到消息了....");
     long startTime = System.currentTimeMillis();
     String redisKey=(OrderConst.REDIS_ORDER_PREFIX+param.getPayOrder().getMemberId());
     try {
     log.info("队列: {}，接收内容：{}", QUEUE_INFO, BeanUtils.beanToString(param));
     String payOrderSn = iOmsConfirmOrderService.completeGeneralOrderOfNow(param.getPayOrder(),param.getOrder(),param.getOrderItemList(),param.getStockList(),param.getCartIds(),param.getPromotionId());
     redisUtil.set(redisKey,OrderHandleResult.success(payOrderSn),OrderConst.REDIS_ORDER_LIVE_TIME);
     channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
     } catch (Exception e) {
     log.error("队列: {}，异常：{}", QUEUE_INFO, e.toString());
     // 保存异常信息至Redis
     redisUtil.set(redisKey,OrderHandleResult.error(e.getMessage()),OrderConst.REDIS_ORDER_LIVE_TIME);
     try {
     // 出现异常，拒绝该消息
     channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
     } catch (IOException e1) {
     log.error("队列: {}，拒绝消息异常：{}", QUEUE_INFO, e1.toString());
     }
     } finally {
     log.info("队列: {}，处理耗时：{} ms", QUEUE_INFO, (System.currentTimeMillis() - startTime));
     }
     } */
}
