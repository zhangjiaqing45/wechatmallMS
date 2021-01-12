package com.fante.common.business.enums;

import com.fante.common.business.constant.BizConstants;
import com.google.common.collect.Maps;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @program: Fante
 * @Date: 2020/4/6 18:20
 * @Author: Mr.Z
 * @Description: 签到活动相关设置
 */
public class SmsSignConst {

    private SmsSignConst() {
    }

    /**
     * 签到类型
     */
    public enum SignType {

        /**
         * 每日
         */
        DAILY("0","每日", BizConstants.style.SUCCESS),
        /**
         * 连续
         */
        CONTINUOUS("1","连续", BizConstants.style.PRIMARY),
        /**
         * 累计
         */
        CUMULATIVE("2","累计", BizConstants.style.INFO),
        ;

        private String type;
        private String describe;
        private String style;

        SignType(String type, String describe, String style) {
            this.type = type;
            this.describe = describe;
            this.style = style;
        }

        private static final Map<String, Object> SELF_MAP = new LinkedHashMap<>();

        public static Map<String, Object> toMap() {
            for (SignType value : SignType.values()) {
                SELF_MAP.put(value.getType(), value.getDescribe());
            }
            return SELF_MAP;
        }

        private static final Map<String, Object> ALL_MAP = new LinkedHashMap<>();

        public static Map<String, Object> toAllMap() {
            Map<String, Object> inner = null;
            for (SignType value : SignType.values()) {
                inner = Maps.newHashMap();
                inner.put("desc", value.getDescribe());
                inner.put("style", value.getStyle());
                ALL_MAP.put(value.getType(), inner);
            }
            return ALL_MAP;
        }

        public String getType() {
            return type;
        }

        public String getDescribe() {
            return describe;
        }

        public String getStyle() {
            return style;
        }

        public void setStyle(String style) {
            this.style = style;
        }
    }


}
