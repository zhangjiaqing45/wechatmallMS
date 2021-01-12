package com.fante.project.api.mq.enums;

/**
 * @program: Fante
 * @Date:
 * @Author: Mr.Z
 * @Description: 交换机枚举配置
 */
public enum ExchangeEnum {

    /**
     * 订单下单-交换机
     */
    EX_DIRECT_ORDER("ex.driect.order"),

    /**
     * 取消订单-直连延时交换机名称
     */
    EX_DELAY_ORDER_CANCEL("ex.delay.order.cancel"),

    /**
     * 取消订单-直连死信交换机名称
     */
    EX_DEAD_ORDER_CANCEL("ex.dead.order.cancel"),
    /**
     * 取消组团-直连死信交换机名称
     */
    Q_DEAD_GROUP_CANCEL("ex.dead.group.cancel"),
    ;

    /**
     * 交换机名称
     */
    private String name;

    ExchangeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
