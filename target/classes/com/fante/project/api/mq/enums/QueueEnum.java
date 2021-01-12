package com.fante.project.api.mq.enums;

/**
 * @program: Fante
 * @Date:
 * @Author: Mr.Z
 * @Description: 消息队列枚举配置
 */
public enum QueueEnum {
    /**##############################[下单队列]######################################*/
    /**
     * 普通订单下单队列，绑定直连普通订单交换机
     */
    Q_DRIECT_ORDER_GENERAL(ExchangeEnum.EX_DIRECT_ORDER.getName(), "q.driect.order.general", "q.driect.order.general.key"),
    /**
     * 秒杀订单下单队列，绑定直连秒杀订单交换机
     */
    Q_DRIECT_ORDER_SECKILL(ExchangeEnum.EX_DIRECT_ORDER.getName(), "q.driect.order.seckill", "q.driect.order.seckill.key"),
    /**
     * 团购订单下单队列，绑定直连团购订单交换机
     */
    Q_DRIECT_ORDER_GROUP(ExchangeEnum.EX_DIRECT_ORDER.getName(), "q.driect.order.group", "q.driect.order.group.key"),


    /**##############################[延时取消-订单队列]##############################*/
    /**
     * 取消订单-延时测试队列，绑定延时直连交换机
     */
    Q_DELAY_ORDER_GENERAL_CANCEL(ExchangeEnum.EX_DELAY_ORDER_CANCEL.getName(), "q.delay.order.general.cancel", "q.delay.order.general.cancel.key"),
    /**
     * 取消订单-延时测试队列，绑定延时直连交换机
     */
    Q_DELAY_ORDER_SECKILL_CANCEL(ExchangeEnum.EX_DELAY_ORDER_CANCEL.getName(), "q.delay.order.seckill.cancel", "q.delay.order.seckill.cancel.key"),
    /**
     * 取消订单-延时测试队列，绑定延时直连交换机
     */
    Q_DELAY_ORDER_GROUP_CANCEL(ExchangeEnum.EX_DELAY_ORDER_CANCEL.getName(), "q.delay.order.group.cancel", "q.delay.order.group.cancel.key"),

    /**##############################[支付成功-组团处理队列]##############################*/
    /**
     * 团购组团操作的队列，绑定直连团购订单交换机
     */
    Q_DRIECT_ORDER_JOIN_GROUP(ExchangeEnum.EX_DIRECT_ORDER.getName(), "q.driect.order.join.group", "q.driect.order.join.group.key"),

    /**##############################[延时取消-组团队列]##################################*/
    /**
     * 取消组团-延时测试队列，绑定延时直连交换机
     */
    Q_DELAY_GROUP_CANCEL(ExchangeEnum.EX_DELAY_ORDER_CANCEL.getName(), "q.delay.group.cancel", "q.delay.group.cancel.key"),

    /**##############################[死信队列]#########################################*/
    /**
     * 取消订单-死信队列，绑定死信交换直连机
     */
    Q_DEAD_ORDER_CANCEL(ExchangeEnum.EX_DEAD_ORDER_CANCEL.getName(), "q.dead.order.cancel", "q.dead.order.cancel.key"),

    /**
     * 取消组团-死信队列，绑定死信交换直连机
     */
    Q_DEAD_GROUP_CANCEL(ExchangeEnum.Q_DEAD_GROUP_CANCEL.getName(), "q.dead.group.cancel", "q.dead.group.cancel.key");


    /**
     * 交换名称
     */
    private String exChange;
    /**
     * 队列名称
     */
    private String name;
    /**
     * 路由键
     */
    private String routeKey;


    QueueEnum(String exChange, String name, String routeKey) {
        this.exChange = exChange;
        this.name = name;
        this.routeKey = routeKey;
    }

    public String getExChange() {
        return exChange;
    }

    public String getName() {
        return name;
    }

    public String getRouteKey() {
        return routeKey;
    }
}
