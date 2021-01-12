package com.fante.common.business.enums;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: Fante
 * @Date: 2019/12/31 15:55
 * @Author: Mr.Z
 * @Description: 用户类型设置
 */
public enum UserTypeEnum {

    /**
     * 超级管理员
     */
    ADMIN("00", "超级管理员"),

    /**
     * 平台用户
     */
    SYSTEM("01", "平台"),
    /**
     * 店铺用户
     */
    FRANCHISEE("02", "店铺"),

    /**
     * 店铺注册用户
     */
    FRANCHISEE_REG("20", "店铺注册"),
    ;

    private String type;
    private String describe;

    UserTypeEnum(String type, String describe) {
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

    private static final List<String> EXCLUDES = Arrays.asList(FRANCHISEE_REG.getType());

    /**
     * 管理后台显示
     * @return
     */
    public static Map<String, Object> toMap() {
        for (UserTypeEnum value : UserTypeEnum.values()) {
            if (!EXCLUDES.contains(value.getType())) {
                SELF_MAP.put(value.getType(), value.getDescribe());
            }
        }
        return SELF_MAP;
    }

    private static final List<String> EXCLUDES2 = Arrays.asList(ADMIN.getType(), FRANCHISEE_REG.getType());

    /**
     * 管理后台用户类型
     * @return
     */
    public static Map<String, Object> toMapMore() {
        for (UserTypeEnum value : UserTypeEnum.values()) {
            if (!EXCLUDES2.contains(value.getType())) {
                SELF_MAP.put(value.getType(), value.getDescribe());
            }
        }
        return SELF_MAP;
    }
}
