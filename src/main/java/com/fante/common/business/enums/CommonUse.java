package com.fante.common.business.enums;

import com.fante.common.business.constant.BizConstants;
import com.google.common.collect.Maps;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @program: Fante
 * @Date: 2020/3/28 9:37
 * @Author: Mr.Z
 * @Description: 常用设置
 */
public class CommonUse {


    private CommonUse() {
    }

    /**
     * 通用状态
     */
    public enum Status {

        /**
         * 启用
         */
        ENABLE("0", "启用", BizConstants.style.PRIMARY),

        /**
         * 停用
         */
        DISABLE("1", "停用", BizConstants.style.DANGER),
        ;

        private String type;
        private String describe;
        private String style;

        Status(String type, String describe, String style) {
            this.type = type;
            this.describe = describe;
            this.style = style;
        }

        private static final Map<String, Object> SELF_MAP = new LinkedHashMap<>();

        public static Map<String, Object> toMap() {
            for (Status value : Status.values()) {
                SELF_MAP.put(value.getType(), value.getDescribe());
            }
            return SELF_MAP;
        }

        private static final Map<String, Object> ALL_MAP = new LinkedHashMap<>();

        public static Map<String, Object> toAllMap() {
            Map<String, Object> inner = null;
            for (Status value : Status.values()) {
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
    }


}
