package com.fante.common.business.enums;

import com.fante.common.business.constant.BizConstants;
import com.fante.common.utils.StringUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.*;

/**
 * @program: Fante
 * @Date: 2020/3/26 15:58
 * @Author: Mr.Z
 * @Description: 优惠券相关设置
 */
public class SmsCouponConst {

    private SmsCouponConst() {
    }


    /**
     * 按钮控制
     */
    public enum operateBtnEnum {

        /**
         * 编辑按钮
         */
        BTN_EIDT("btn-eidt", "编辑", new String[]{StatusEnum.AWAIT.getType()}),
        /**
         * 上架按钮
         */
        BTN_PUTAWAY("btn-putaway", "上架", new String[]{StatusEnum.AWAIT.getType()}),
        /**
         * 下架按钮
         */
        BTN_SOLDOUT("btn-soldout", "下架", new String[]{StatusEnum.PUTAWAY.getType()}),
        /**
         * 历史按钮
         */
        BTN_HISTORY("btn-history", "历史", new String[]{StatusEnum.PUTAWAY.getType(), StatusEnum.SOLDOUT.getType()}),
        /**
         * 删除按钮
         */
        BTN_DELETE("btn-delete", "删除", new String[]{StatusEnum.AWAIT.getType()}),
        /**
         * 发放优惠券
         */
        BTN_GIFT("btn-gift", "发券", new String[]{StatusEnum.PUTAWAY.getType()}),
        ;

        private String type;
        private String describe;
        private String[] states;

        operateBtnEnum(String type, String describe, String[] states) {
            this.type = type;
            this.describe = describe;
            this.states = states;
        }

        public String getType() {
            return type;
        }

        public String getDescribe() {
            return describe;
        }

        public String[] getStates() {
            return states;
        }

        /**
         * 根据优惠券状态显示按钮
         *
         * @param status
         * @return
         */
        public static List<String> getBtns(String status) {
            if (StringUtils.isBlank(status)) {
                return Collections.emptyList();
            }
            List<String> btns = Lists.newArrayList();
            for (operateBtnEnum value : operateBtnEnum.values()) {
                if (Arrays.asList(value.getStates()).contains(status)) {
                    btns.add(value.getType());
                }
            }
            return btns;
        }
    }


    /**
     * 优惠券状态
     */
    public enum StatusEnum {

        /**
         * 待上架
         */
        AWAIT("0", "待上架", BizConstants.style.WARNING),
        /**
         * 已上架
         */
        PUTAWAY("1", "已上架", BizConstants.style.PRIMARY),
        /**
         * 已下架
         */
        SOLDOUT("2", "已下架", BizConstants.style.DANGER),
        ;


        private String type;
        private String describe;
        private String style;

        StatusEnum(String type, String describe, String style) {
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

        private static final Map<String, Object> SELF_MAP = new LinkedHashMap<>();

        public static Map<String, Object> toMap() {
            for (StatusEnum value : StatusEnum.values()) {
                SELF_MAP.put(value.getType(), value.getDescribe());
            }
            return SELF_MAP;
        }

        private static final Map<String, Object> ALL_MAP = new LinkedHashMap<>();

        public static Map<String, Object> toAllMap() {
            Map<String, Object> inner = null;
            for (StatusEnum value : StatusEnum.values()) {
                inner = Maps.newHashMap();
                inner.put("desc", value.getDescribe());
                inner.put("style", value.getStyle());
                ALL_MAP.put(value.getType(), inner);
            }
            return ALL_MAP;
        }

    }


    /**
     * @Author LiuQingyu
     * @Description 优惠券获取类型（商家赠送/主动领取）
     * @Date 17:04 2020-03-20
     **/
    public enum getTypeEnum {

        /**
         * 商家赠送
         */
        GIVE("0", "商家赠送", BizConstants.style.PRIMARY),
        /**
         * 主动领取
         */
        SELF("1", "主动领取", BizConstants.style.SUCCESS),
        ;

        private String type;
        private String describe;
        private String style;

        getTypeEnum(String type, String describe, String style) {
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

        private static final Map<String, Object> SELF_MAP = new LinkedHashMap<>();

        public static Map<String, Object> toMap() {
            for (SmsCouponConst.getTypeEnum value : SmsCouponConst.getTypeEnum.values()) {
                SELF_MAP.put(value.getType(), value.getDescribe());
            }
            return SELF_MAP;
        }

        private static final Map<String, Object> ALL_MAP = new LinkedHashMap<>();

        public static Map<String, Object> toAllMap() {
            Map<String, Object> inner = null;
            for (getTypeEnum value : getTypeEnum.values()) {
                inner = Maps.newHashMap();
                inner.put("desc", value.getDescribe());
                inner.put("style", value.getStyle());
                ALL_MAP.put(value.getType(), inner);
            }
            return ALL_MAP;
        }
    }
    /**
     * @Author wz
     * @Description 优惠券类型（满减券/折扣券）
     * @Date 17:04 2020-03-20
     **/
    public enum CouponTypeEnum {

        /**
         * 满减券
         */
        FULL("0", "满减券", BizConstants.style.PRIMARY),
        /**
         * 折扣券
         */
        DICOUNT("1", "折扣券", BizConstants.style.SUCCESS),
        ;

        private String type;
        private String describe;
        private String style;

        CouponTypeEnum(String type, String describe, String style) {
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

        private static final Map<String, Object> SELF_MAP = new LinkedHashMap<>();

        public static Map<String, Object> toMap() {
            for (SmsCouponConst.CouponTypeEnum value : SmsCouponConst.CouponTypeEnum.values()) {
                SELF_MAP.put(value.getType(), value.getDescribe());
            }
            return SELF_MAP;
        }

        private static final Map<String, Object> ALL_MAP = new LinkedHashMap<>();

        public static Map<String, Object> toAllMap() {
            Map<String, Object> inner = null;
            for (CouponTypeEnum value : CouponTypeEnum.values()) {
                inner = Maps.newHashMap();
                inner.put("desc", value.getDescribe());
                inner.put("style", value.getStyle());
                ALL_MAP.put(value.getType(), inner);
            }
            return ALL_MAP;
        }
    }
    /**
     * @Author wz
     * @Description 优惠券类型（满减券/折扣券）
     * @Date 17:04 2020-03-20
     **/
    public enum MemberLevelEnum {

        /**
         * 普通会员
         */
        COMMON("0", "普通会员", BizConstants.style.PRIMARY),
        /**
         * 高级会员
         */
        VIP("1", "高级会员", BizConstants.style.SUCCESS),
        ;

        private String type;
        private String describe;
        private String style;

        MemberLevelEnum(String type, String describe, String style) {
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

        private static final Map<String, Object> SELF_MAP = new LinkedHashMap<>();

        public static Map<String, Object> toMap() {
            for (SmsCouponConst.MemberLevelEnum value : SmsCouponConst.MemberLevelEnum.values()) {
                SELF_MAP.put(value.getType(), value.getDescribe());
            }
            return SELF_MAP;
        }

        private static final Map<String, Object> ALL_MAP = new LinkedHashMap<>();

        public static Map<String, Object> toAllMap() {
            Map<String, Object> inner = null;
            for (MemberLevelEnum value : MemberLevelEnum.values()) {
                inner = Maps.newHashMap();
                inner.put("desc", value.getDescribe());
                inner.put("style", value.getStyle());
                ALL_MAP.put(value.getType(), inner);
            }
            return ALL_MAP;
        }
    }

    /**
     * @Author LiuQingyu
     * @Description 优惠券使用类型（全场通用/指定分类/指定商品）
     * @Date 13:47 2020-03-20
     **/
    public enum useTypeEnum {

        /**
         * 店铺通用
         */
        ALLUSE("0", "店铺通用", "all", BizConstants.style.PRIMARY),
        /**
         * 指定分类
         */
        CLASSIFYUSE("1", "指定分类", "category", BizConstants.style.SUCCESS),
        /**
         * 指定商品
         */
        PRODUCTUSE("2", "指定商品", "product", BizConstants.style.INFO),
        ;

        private String type;
        private String describe;
        private String sign;
        private String style;

        useTypeEnum(String type, String describe, String sign, String style) {
            this.type = type;
            this.describe = describe;
            this.sign = sign;
            this.style = style;
        }

        public String getType() {
            return type;
        }

        public String getDescribe() {
            return describe;
        }

        public String getSign() {
            return sign;
        }

        public String getStyle() {
            return style;
        }


        public static SmsCouponConst.useTypeEnum get(String type) {
            for (SmsCouponConst.useTypeEnum value : SmsCouponConst.useTypeEnum.values()) {
                if (Objects.equals(type, value.getType())) {
                    return value;
                }
            }
            return null;
        }


        private static final Map<String, Object> SELF_MAP = new LinkedHashMap<>();

        public static Map<String, Object> toMap() {
            for (SmsCouponConst.useTypeEnum value : SmsCouponConst.useTypeEnum.values()) {
                SELF_MAP.put(value.getType(), value.getDescribe());
            }
            return SELF_MAP;
        }

        private static final Map<String, Object> ALL_MAP = new LinkedHashMap<>();

        public static Map<String, Object> toAllMap() {
            Map<String, Object> inner = null;
            for (SmsCouponConst.useTypeEnum value : SmsCouponConst.useTypeEnum.values()) {
                inner = Maps.newHashMap();
                inner.put("desc", value.getDescribe());
                inner.put("sign", value.getSign());
                inner.put("style", value.getStyle());
                ALL_MAP.put(value.getType(), inner);
            }
            return ALL_MAP;
        }
    }

    /**
     * @Author LiuQingyu
     * @Description 优惠券使用状态（未使用/已使用/已过期）
     * @Date 17:02 2020-03-20
     **/
    public enum useStatusEnum {

        /**
         * 未使用
         */
        UNUSED("0", "未使用", BizConstants.style.SUCCESS),
        /**
         * 已使用
         */
        USED("1", "已使用", BizConstants.style.PRIMARY),
        /**
         * 已过期
         */
        EXPIRED("2", "已过期", BizConstants.style.WARNING),
        ;

        private String type;
        private String describe;
        private String style;

        useStatusEnum(String type, String describe, String style) {
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

        private static final Map<String, Object> SELF_MAP = new LinkedHashMap<>();

        public static Map<String, Object> toMap() {
            for (useStatusEnum value : useStatusEnum.values()) {
                SELF_MAP.put(value.getType(), value.getDescribe());
            }
            return SELF_MAP;
        }

        private static final Map<String, Object> ALL_MAP = new LinkedHashMap<>();

        public static Map<String, Object> toAllMap() {
            Map<String, Object> inner = null;
            for (useStatusEnum value : useStatusEnum.values()) {
                inner = Maps.newHashMap();
                inner.put("desc", value.getDescribe());
                inner.put("style", value.getStyle());
                ALL_MAP.put(value.getType(), inner);
            }
            return ALL_MAP;
        }
    }
}
