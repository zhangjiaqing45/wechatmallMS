package com.fante.common.business.enums;

import com.fante.common.business.constant.BizConstants;
import com.google.common.collect.Maps;

import java.util.*;

/**
 * @program: Fante
 * @Date: 2020/4/14 17:09
 * @Author: Mr.Z
 * @Description: 会员相关设置
 */
public class UmsMemberConst {

    private UmsMemberConst() {
    }

    /**
     * 用户来源
     */
    public enum SourceEnum {
        /**
         * PC端
         */
        PC("pc", "管理系统", BizConstants.style.SUCCESS),
        /**
         * 微信公众号
         */
        WECHAT("wechat", "微信公众号", BizConstants.style.PRIMARY),
        ;

        private String type;
        private String describe;
        private String style;

        SourceEnum(String type, String describe, String style) {
            this.type = type;
            this.describe = describe;
            this.style = style;
        }

        private static final Map<String, Object> SELF_MAP = new LinkedHashMap<>();

        public static Map<String, Object> toMap() {
            for (SourceEnum value : SourceEnum.values()) {
                SELF_MAP.put(value.getType(), value.getDescribe());
            }
            return SELF_MAP;
        }

        private static final Map<String, Object> ALL_MAP = new LinkedHashMap<>();

        public static Map<String, Object> toAllMap() {
            Map<String, Object> inner = null;
            for (SourceEnum value : SourceEnum.values()) {
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
     * 订阅公众号
     */
    public enum SubscribeEnum {

        /**
         * 已关注标识
         */
        YES("1", "已关注", BizConstants.style.PRIMARY),
        /**
         * 取消关注
         */
        NOT("0", "取消关注", BizConstants.style.WARNING),
        ;

        private String type;
        private String describe;
        private String style;

        SubscribeEnum(String type, String describe, String style) {
            this.type = type;
            this.describe = describe;
            this.style = style;
        }

        private static final Map<String, Object> SELF_MAP = new LinkedHashMap<>();

        public static Map<String, Object> toMap() {
            for (SubscribeEnum value : SubscribeEnum.values()) {
                SELF_MAP.put(value.getType(), value.getDescribe());
            }
            return SELF_MAP;
        }

        private static final Map<String, Object> ALL_MAP = new LinkedHashMap<>();

        public static Map<String, Object> toAllMap() {
            Map<String, Object> inner = null;
            for (SubscribeEnum value : SubscribeEnum.values()) {
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
     * 是否核销员
     */
    public enum IsVerifier {

        /**
         * 是
         */
        YES("1", "是", BizConstants.style.PRIMARY),
        /**
         * 否
         */
        NOT("0", "否", BizConstants.style.WARNING),
        ;

        private String type;
        private String describe;
        private String style;

        IsVerifier(String type, String describe, String style) {
            this.type = type;
            this.describe = describe;
            this.style = style;
        }

        private static final Map<String, Object> SELF_MAP = new LinkedHashMap<>();

        public static Map<String, Object> toMap() {
            for (IsVerifier value : IsVerifier.values()) {
                SELF_MAP.put(value.getType(), value.getDescribe());
            }
            return SELF_MAP;
        }

        private static final Map<String, Object> ALL_MAP = new LinkedHashMap<>();

        public static Map<String, Object> toAllMap() {
            Map<String, Object> inner = null;
            for (IsVerifier value : IsVerifier.values()) {
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
     * 用户等级
     * 是否实名认证过标识  0未认证  1已认证
     */
    public enum MemberLevel {

        /**
         * 是
         */
        YES("1", "已认证", BizConstants.style.PRIMARY),
        /**
         * 否
         */
        NOT("0", "未认证", BizConstants.style.WARNING),
        ;

        private String type;
        private String describe;
        private String style;

        MemberLevel(String type, String describe, String style) {
            this.type = type;
            this.describe = describe;
            this.style = style;
        }

        private static final Map<String, Object> SELF_MAP = new LinkedHashMap<>();

        public static Map<String, Object> toMap() {
            for (MemberLevel value : MemberLevel.values()) {
                SELF_MAP.put(value.getType(), value.getDescribe());
            }
            return SELF_MAP;
        }

        private static final Map<String, Object> ALL_MAP = new LinkedHashMap<>();

        public static Map<String, Object> toAllMap() {
            Map<String, Object> inner = null;
            for (MemberLevel value : MemberLevel.values()) {
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
     * 用户角色
     */
    public enum RoleType {
        /**
         * 普通用户
         */
        GENERAL("1", "普通用户", BizConstants.style.DEFAULT),
        /**
         * 合伙人
         */
        PARTNER("2", "合伙人", BizConstants.style.SUCCESS),
        /**
         * 设计师
         */
        STYLIST("3", "设计师", BizConstants.style.INFO),
        /**
         * 导购
         */
        SALESMAN("4", "导购", BizConstants.style.WARNING)
        ;

        private String type;
        private String describe;
        private String style;

        RoleType(String type, String describe, String style) {
            this.type = type;
            this.describe = describe;
            this.style = style;
        }

        private static final Map<String, Object> SELF_MAP = new LinkedHashMap<>();

        public static Map<String, Object> toMap() {
            for (RoleType value : RoleType.values()) {
                SELF_MAP.put(value.getType(), value.getDescribe());
            }
            return SELF_MAP;
        }
        //高级分销人员:合伙人 设计师(可分享其他店铺获得佣金)
        private static final List<String> HEIGHT_LEVEL =  Arrays.asList(
                STYLIST.getType()
                ,PARTNER.getType()
        );
        //判断是否高级分销角色
        public static boolean isHighLevel(String type) {
            return  HEIGHT_LEVEL.contains(type);
        }
        //高级分销人员:合伙人 设计师(可分享其他店铺获得佣金)
        private static final List<String> DISTRIBUTION_PEOPLE =  Arrays.asList(
                STYLIST.getType()
                ,PARTNER.getType()
                ,SALESMAN.getType()
        );
        //判断是否是分销角色
        public static boolean isDistribution(String type) {
            return  DISTRIBUTION_PEOPLE.contains(type);
        }

        public static RoleType get(String type){
            for (RoleType value : RoleType.values()) {
                if(Objects.equals(value.getType(),type)){
                    return value;
                }
            }
            return null;
        }

        private static final Map<String, Object> ALL_MAP = new LinkedHashMap<>();

        public static Map<String, Object> toAllMap() {
            Map<String, Object> inner = null;
            for (RoleType value : RoleType.values()) {
                inner = Maps.newHashMap();
                inner.put("desc", value.getDescribe());
                inner.put("style", value.getStyle());
                ALL_MAP.put(value.getType(), inner);
            }
            return ALL_MAP;
        }

        /**
         * 普通店铺可邀请的分销人员
         */
        public static final List<String> SHOP_INVITE_ROLES = Arrays.asList(SALESMAN.getType());

        /**
         * 自营店铺可邀请的分销人员
         */
        public static final List<String> SELF_SHOP_INVITE_ROLES = Arrays.asList(PARTNER.getType(), STYLIST.getType());


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
