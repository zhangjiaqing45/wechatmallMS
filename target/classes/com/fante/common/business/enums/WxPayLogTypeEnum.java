package com.fante.common.business.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @program: Fante
 * @Date: 2020/2/21 17:22
 * @Author: Mr.Z
 * @Description: 微信支付日志类型
 */
public enum WxPayLogTypeEnum {
    /**
     * 统一下单
     */
    UNIFIEDORDER("unifiedorder", "统一下单"),
    /**
     * 支付结果通知
     */
    PAYNOTIFY("paynotify", "结果通知"),
    /**
     * 申请退款
     */
    REFUND("refund", "申请退款"),

    ;

    private String type;
    private String describe;

    WxPayLogTypeEnum(String type, String describe) {
        this.type = type;
        this.describe = describe;
    }

    public String getType() {
        return type;
    }

    public String getDescribe() {
        return describe;
    }

    private static final Map<String, Object> SELF_MAP = new LinkedHashMap<>();

    public static Map<String, Object> toMap() {
        for (WxPayLogTypeEnum value : WxPayLogTypeEnum.values()) {
            SELF_MAP.put(value.getType(), value.getDescribe());
        }
        return SELF_MAP;
    }
}
