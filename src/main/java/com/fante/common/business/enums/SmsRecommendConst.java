package com.fante.common.business.enums;

import org.apache.commons.codec.binary.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @program: Fante
 * @Date: 2020/4/6 18:25
 * @Author: Mr.Z
 * @Description: 商品推荐相关设置
 */
public class SmsRecommendConst {

    private SmsRecommendConst() {
    }

    /**
     * 推荐类型
     */
    public enum ProductType {
        /**
         * 热门商品
         */
        HOT("hot", "热门商品"),
        /**
         * 精品推荐
         */
        BOUTIQUE("boutique", "精品推荐"),
        /**
         * 新品推荐
         */
        NEW("new", "新品推荐"),

        ;

        private String type;
        private String describe;

        ProductType(String type, String describe) {
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
            for (ProductType value : ProductType.values()) {
                SELF_MAP.put(value.getType(), value.getDescribe());
            }
            return SELF_MAP;
        }

        /**
         *  包含则返回,否则返回null
         * @param code
         * @return
         */
        public static ProductType get(String code){
            for (ProductType value : ProductType.values()) {
                if(StringUtils.equals(value.getType(),code)){
                    return value;
                }
            }
            return null;
        }
    }
}
