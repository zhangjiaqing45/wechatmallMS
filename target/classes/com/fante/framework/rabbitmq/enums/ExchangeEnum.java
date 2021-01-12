package com.fante.framework.rabbitmq.enums;

/**
 * @program: Fante
 * @Date:
 * @Author: Mr.Z
 * @Description: 交换机枚举配置
 */
public enum ExchangeEnum {

    /**
     * 直连交换机名称
     */
    EX_DIRECT("ex.driect"),

    /**
     * 直连延时交换机名称
     */
    EX_DELAY_DIRECT("ex.delay.driect"),

    /**
     * 直连死信交换机名称
     */
    EX_DEAD_LETTER_DIRECT("ex.dead.letter.driect"),
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
