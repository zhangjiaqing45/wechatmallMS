package com.fante.common.business.enums;

import com.fante.common.business.constant.BizConstants;
import com.google.common.collect.Maps;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @program: Fante
 * @Date: 2020/4/21 11:06
 * @Author: Mr.Z
 * @Description: 前端导航相关设置
 */
public class CmsAppNavConst {

    private CmsAppNavConst() {
    }

    /**
     * 链接类型
     */
    public enum NavLinkType {

        /**
         * 应用内页
         */
        NAV("0", "应用内页", BizConstants.style.PRIMARY),
        /**
         * 应用内页
         */
        TAB("1", "tabBar页", BizConstants.style.SUCCESS),
        ;

        private String type;
        private String describe;
        private String style;

        NavLinkType(String type, String describe, String style) {
            this.type = type;
            this.describe = describe;
            this.style = style;
        }

        private static final Map<String, Object> SELF_MAP = new LinkedHashMap<>();

        public static Map<String, Object> toMap() {
            for (NavLinkType value : NavLinkType.values()) {
                SELF_MAP.put(value.getType(), value.getDescribe());
            }
            return SELF_MAP;
        }

        private static final Map<String, Object> ALL_MAP = new LinkedHashMap<>();

        public static Map<String, Object> toAllMap() {
            Map<String, Object> inner = null;
            for (NavLinkType value : NavLinkType.values()) {
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
     * 显示范围
     */
    public enum NavShowType {
        /**
         * 全部
         */
        ALL("0", "全部", BizConstants.style.PRIMARY),
        /**
         * 仅App首页
         */
        APP_INDEX("1", "仅App首页", BizConstants.style.SUCCESS),
        /**
         * 仅店铺首页
         */
        SHOP_INDEX("2", "仅店铺首页", BizConstants.style.WARNING),
        ;

        private String type;
        private String describe;
        private String style;

        NavShowType(String type, String describe, String style) {
            this.type = type;
            this.describe = describe;
            this.style = style;
        }

        /**
         * APP首页显示的导航
         */
        public static final String[] APP_SHOW = {ALL.getType(), APP_INDEX.getType()};

        /**
         * 店铺首页显示的导航
         */
        public static final String[] SHOP_SHOW = {ALL.getType(), SHOP_INDEX.getType()};


        private static final Map<String, Object> SELF_MAP = new LinkedHashMap<>();

        public static Map<String, Object> toMap() {
            for (NavShowType value : NavShowType.values()) {
                SELF_MAP.put(value.getType(), value.getDescribe());
            }
            return SELF_MAP;
        }

        private static final Map<String, Object> ALL_MAP = new LinkedHashMap<>();

        public static Map<String, Object> toAllMap() {
            Map<String, Object> inner = null;
            for (NavShowType value : NavShowType.values()) {
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
