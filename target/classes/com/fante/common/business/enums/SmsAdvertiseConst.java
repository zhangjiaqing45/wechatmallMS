package com.fante.common.business.enums;

import com.fante.common.business.constant.BizConstants;
import com.google.common.collect.Maps;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @program: Fante
 * @Date: 2020/4/7 14:02
 * @Author: Mr.Z
 * @Description: 广告相关配置
 */
public class SmsAdvertiseConst {

    private SmsAdvertiseConst() {
    }

    /**
     * 广告位置
     */
    public enum Position {
        /**
         * 首页轮播
         */
        HOME_BANNER("hbanner", "首页轮播图", BizConstants.style.PRIMARY),
        /**
         * 首页轮播
         */
        HOME_TOP("htop", "首页上部广告", BizConstants.style.SUCCESS),
        /**
         * 首页中部广告
         */
        HOME_MIDDLE("hmiddle", "首页中部广告", BizConstants.style.WARNING),
        /**
         * 首页底部部广告
         */
        HOME_BOTTOM("hbottom", "首页底部广告", BizConstants.style.INFO),

        /**
         * 首页浮动
         */
        HOME_FLOAT("hfloat", "首页浮动图", BizConstants.style.DEFAULT),

        ;

        private String type;
        private String describe;
        private String style;

        Position(String type, String describe, String style) {
            this.type = type;
            this.describe = describe;
            this.style = style;
        }

        private static final Map<String, Object> SELF_MAP = new LinkedHashMap<>();

        public static Map<String, Object> toMap() {
            for (Position value : Position.values()) {
                SELF_MAP.put(value.getType(), value.getDescribe());
            }
            return SELF_MAP;
        }

        private static final Map<String, Object> ALL_MAP = new LinkedHashMap<>();

        public static Map<String, Object> toAllMap() {
            Map<String, Object> inner = null;
            for (Position value : Position.values()) {
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

    /**
     * 链接类型
     */
    public enum UrlType{
        /**
         * 无跳转
         */
        NONE("none", "无跳转", BizConstants.style.DEFAULT),
        /**
         * 跳转店铺
         */
        SHOP("shop", "跳转店铺", BizConstants.style.PRIMARY),
        /**
         * 跳转活动
         */
        PROMOTION("promotion", "跳转活动", BizConstants.style.SUCCESS),
        /**
         * 跳转商品
         */
        PRODUCT("product", "跳转商品", BizConstants.style.INFO),

        OTHER("other", "外部链接", BizConstants.style.WARNING),
        ;


        private String type;
        private String describe;
        private String style;

        UrlType(String type, String describe, String style) {
            this.type = type;
            this.describe = describe;
            this.style = style;
        }

        public static UrlType get(String type) {
            for (UrlType value : UrlType.values()) {
                if (Objects.equals(type, value.getType())) {
                    return value;
                }
            }
            return null;
        }

        private static final Map<String, Object> SELF_MAP = new LinkedHashMap<>();

        public static Map<String, Object> toMap() {
            for (UrlType value : UrlType.values()) {
                SELF_MAP.put(value.getType(), value.getDescribe());
            }
            return SELF_MAP;
        }

        private static final Map<String, Object> ALL_MAP = new LinkedHashMap<>();

        public static Map<String, Object> toAllMap() {
            Map<String, Object> inner = null;
            for (UrlType value : UrlType.values()) {
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
