package com.fante.common.business.enums;

import com.fante.common.business.constant.BizConstants;
import com.fante.common.utils.StringUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.*;

/**
 * @program: Fante
 * @Date: 2020/3/31 13:38
 * @Author: Mr.Z
 * @Description: 限时秒杀相关设置
 */
public class SmsFlashConst {

    private SmsFlashConst() {
    }


    /**
     * 按钮控制
     */
    public enum operateBtnEnum {
        /**
         * 设置商品
         */
        BTN_PRODUCT("btn-product", "设置商品", new String[] {StatusEnum.AWAIT.getType()}),
        /**
         * 查看商品
         */
        BTN_BROWSE("btn-browse", "查看商品", new String[] {StatusEnum.PUTAWAY.getType(), StatusEnum.SOLDOUT.getType()}),
        /**
         * 开启活动
         */
        BTN_PUTAWAY("btn-putaway", "开启活动", new String[] {StatusEnum.AWAIT.getType()}),
        /**
         * 下架按钮
         */
        BTN_SOLDOUT("btn-soldout", "结束活动", new String[] {StatusEnum.PUTAWAY.getType()}),
        /**
         * 编辑按钮
         */
        BTN_EIDT("btn-eidt", "编辑活动", new String[] {StatusEnum.AWAIT.getType()}),
        /**
         * 删除按钮
         */
        BTN_DELETE("btn-delete", "删除活动", new String[] {StatusEnum.AWAIT.getType()}),

        /**
         * 新增秒杀商品
         */
        BTN_PRODUCT_ADD("btn-product-add", "新增秒杀商品", new String[] {StatusEnum.AWAIT.getType()}),
        /**
         * 编辑秒杀商品
         */
        BTN_PRODUCT_EIDT("btn-product-edit", "编辑秒杀商品", new String[] {StatusEnum.AWAIT.getType()}),
        /**
         * 删除秒杀商品
         */
        BTN_PRODUCT_DELETE("btn-product-delete", "删除秒杀商品", new String[] {StatusEnum.AWAIT.getType()}),

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
         * 活动列表按钮
         */
        public static final List<String> LIST_BTNS = Arrays.asList(
                BTN_PRODUCT.getType(),
                BTN_BROWSE.getType(),
                BTN_PUTAWAY.getType(),
                BTN_SOLDOUT.getType(),
                BTN_EIDT.getType(),
                BTN_DELETE.getType()
        );

        /**
         * 根据秒杀活动状态显示活动列表按钮
         * @param status
         * @return
         */
        public static List<String> getBtns(String status){
            if (StringUtils.isBlank(status)) {
                return Collections.emptyList();
            }
            List<String> btns = Lists.newArrayList();
            for (operateBtnEnum value : operateBtnEnum.values()) {
                if (LIST_BTNS.contains(value.getType()) && Arrays.asList(value.getStates()).contains(status)) {
                    btns.add(value.getType());
                }
            }
            return btns;
        }
    }

    /**
     * 秒杀活动状态
     */
    public enum StatusEnum {

        /**
         * 未开始
         */
        AWAIT("0", "未开始", BizConstants.style.WARNING),
        /**
         * 进行中
         */
        PUTAWAY("1", "进行中", BizConstants.style.PRIMARY),
        /**
         * 已结束
         */
        SOLDOUT("2", "已结束", BizConstants.style.DANGER),
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


}
