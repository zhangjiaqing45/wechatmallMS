package com.fante.framework.rabbitmq.enums;

/**
 * @program: Fante
 * @Date:
 * @Author: Mr.Z
 * @Description: 消息队列枚举配置
 */
public enum QueueEnum {

    /**
     * 测试队列，绑定直连交换机
     */
    Q_TEST_DIRECT(ExchangeEnum.EX_DIRECT.getName(), "q.test.direct", "q.test.direct.key"),

    /**
     * 延时测试队列，绑定延时直连交换机
     */
    Q_DELAY_TEST_DIRECT(ExchangeEnum.EX_DELAY_DIRECT.getName(), "q.delay.test.direct", "q.delay.test.direct.key"),

    /**
     * 死信测试队列，绑定死信交换直连机
     */
    Q_DEAD_LETTER_TEST_DIRECT(ExchangeEnum.EX_DEAD_LETTER_DIRECT.getName(), "q.dead.letter.test.direct", "q.dead.letter.test.direct.key")
    ;


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
