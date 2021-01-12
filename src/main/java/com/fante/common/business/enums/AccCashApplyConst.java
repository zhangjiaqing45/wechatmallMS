package com.fante.common.business.enums;

import com.fante.common.business.constant.BizConstants;
import org.apache.commons.codec.binary.StringUtils;

import java.util.*;

/**
 * @Date: 2020/2/21 17:22
 * @Author: WZ
 * @Description: 财务记录
 */
public class AccCashApplyConst {
    private AccCashApplyConst(){}

    /**
     * @Description: 审核状态配置
     */
    public enum AuditType {

        /**
         * 待审核
         */
        WAIT("01", "待审核", BizConstants.style.INFO),
        /**
         * 审核通过
         */
        SUCCESS("02", "审核通过", BizConstants.style.SUCCESS),
        /**
         * 审核拒绝
         */
        FAIL("03", "审核拒绝", BizConstants.style.DANGER),
        ;


        private String type;
        private String describe;
        private String style;


        AuditType(String type, String describe, String style) {
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
            for (AuditType value : AuditType.values()) {
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
            for (AuditType value :AuditType.values()) {
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
        public static AuditType get(String code){
            for (AuditType value : AuditType.values()) {
                if(StringUtils.equals(value.getType(),code)){
                    return value;
                }
            }
            return null;
        }
    }


}
