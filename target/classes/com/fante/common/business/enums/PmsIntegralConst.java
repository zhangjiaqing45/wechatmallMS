package com.fante.common.business.enums;

import com.fante.common.business.constant.BizConstants;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @program: Fante
 * @Date: 2020/2/21 17:22
 * @Author: Mr.Z
 * @Description: 退货状态
 */
public class PmsIntegralConst {
    private PmsIntegralConst(){}

    /**积分兑换记录类型*/
    public enum IntegralLogType{
        /**
         * 活动获得
         */
        SIGNIN("0","活动获得",BizConstants.style.INFO),
        /**
         * 积分兑换
         */
        EXCHANGE("1","积分兑换",BizConstants.style.WARNING),
        ;

        private String type;
        private String describe;
        private String style;
        IntegralLogType(String type, String describe,String style) {
            this.type = type;
            this.describe = describe;
            this.style = style;
        }

        public String getType() {
            return type;
        }

        public String getStyle() {
            return style;
        }
        public String getDescribe() {
            return describe;
        }

        private static final Map<String, Object> SELF_MAP = new LinkedHashMap<>();
        private static final Map<String, Object> MAP_PLUS = new LinkedHashMap<>();

        public static Map<String, Object> toMap() {
            for (IntegralLogType value : IntegralLogType.values()) {
                SELF_MAP.put(value.getType(), value.getDescribe());
            }
            return SELF_MAP;
        }
        public static Map<String, Object> toMapPlus() {
            Map<String, Object> inner = null;
            for (IntegralLogType value : IntegralLogType.values()) {
                inner = new HashMap<>();
                inner.put("style", value.getStyle());
                inner.put("desc", value.getDescribe());
                MAP_PLUS.put(value.getType(), inner);
            }
            return MAP_PLUS;
        }
        public static IntegralLogType get(String type){
            for (IntegralLogType value : IntegralLogType.values()) {
                if(Objects.equals(value.getType(),type)){
                    return value;
                }
            }
            return null;
        }
    }
}
