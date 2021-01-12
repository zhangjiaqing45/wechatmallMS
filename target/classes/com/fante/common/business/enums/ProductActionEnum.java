package com.fante.common.business.enums;

import com.fante.common.business.constant.BizConstants;

import java.util.*;

/**
 * @program: Fante
 * @Date: 2020/1/3 14:12
 * @Author: Mr.Z
 * @Description: 审核状态配置
 */
public enum ProductActionEnum {
//1编辑/2提交审核/3库存/4上下架/5删除/6导出
    /**
     * 创建商品
     */
    CREATE("0", "创建商品"),
    /**
     * 编辑
     */
    EDIT("1", "编辑商品"),
    /**
     * 提交审核
     */
    AUDIT("2", "提交审核"),
    /**
     * 修改库存
     */
    STOCK("3", "修改库存"),

    /**
     * 上下架
     */
    PUTAWAY("4", "上架下架"),
    /**
     * 删除
     */
    DELETE("5", "删除商品"),
    /**
     * 导出
     */
    EXPORT("6", "导出商品"),
    ;


    private String type;
    private String describe;

    ProductActionEnum(String type, String describe) {
        this.type = type;
        this.describe = describe;
    }

    ProductActionEnum(String type, String describe, String style) {
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
        for (ProductActionEnum value : ProductActionEnum.values()) {
                SELF_MAP.put(value.getType(), value.getDescribe());
        }
        return SELF_MAP;
    }
}
