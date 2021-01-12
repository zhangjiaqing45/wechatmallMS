package com.fante.common.business.enums;

import com.fante.common.business.constant.BizConstants;
import org.apache.commons.codec.binary.StringUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Date: 2020/2/21 17:22
 * @Author: WZ
 * @Description: 核销状态
 */
public class WriteOffConst {
    private WriteOffConst(){}

    /**
     * @Description: 核销状态配置
     */
    public enum writeOffType {

        /**
         * 待核销
         */
        WAIT("0", "待核销", BizConstants.style.INFO),
        /**
         * 已核销
         */
        SUCCESS("1", "已核销", BizConstants.style.SUCCESS),
        /**
         * 核销失败
         */
        FAIL("-1", "核销码未生成", BizConstants.style.DANGER),
        ;


        private String type;
        private String describe;
        private String style;


        writeOffType(String type, String describe, String style) {
            this.type = type;
            this.describe = describe;
            this.style = style;
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


        private static final Map<String, Object> ALL_MAP = new LinkedHashMap<>();
        /**
         * 显示所有状态
         */
        public static Map<String, Object> toMap() {
            for (writeOffType value : writeOffType.values()) {
                ALL_MAP.put(value.getType(), value.getDescribe());
            }
            return ALL_MAP;
        }
        /**
         * 显示所有状态 包含样式
         */
        private static final Map<String, Object> ALL_MAP_PLUS = new LinkedHashMap<>();
        public static Map<String, Object> toAllMap() {
            Map<String, Object> inner = null;
            for (writeOffType value : writeOffType.values()) {
                inner = new HashMap<>();
                inner.put("style", value.getStyle());
                inner.put("desc", value.getDescribe());
                ALL_MAP_PLUS.put(value.getType(), inner);
            }
            return ALL_MAP_PLUS;
        }

        /**
         *  包含则返回,否则返回""
         * @param code
         * @return
         */
        public static writeOffType get(String code){
            for (writeOffType value : writeOffType.values()) {
                if(StringUtils.equals(value.getType(),code)){
                    return value;
                }
            }
            return null;
        }
    }


}
