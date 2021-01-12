package com.fante.common.business.enums;

import com.fante.common.business.constant.BizConstants;
import org.apache.commons.codec.binary.StringUtils;

import java.util.*;

/**
 * @program: Fante
 * @Date: 2020/1/3 14:12
 * @Author: Mr.Z
 * @Description: 审核状态配置
 */
public enum AuditTypeEnum {

    /**
     * 待创建
     */
    CREATE("00", "待创建", BizConstants.style.WARNING),
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

    AuditTypeEnum(String type, String describe) {
        this.type = type;
        this.describe = describe;
    }

    AuditTypeEnum(String type, String describe, String style) {
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

    /**
     * 注册人可以进行编辑的状态
     */
    public static final List<String> EDITABLE = Arrays.asList(CREATE.getType(), FAIL.getType());

    /**
     * 平台可以进行审核的状态
     */
    public static final List<String> AUDITABLE = Arrays.asList(WAIT.getType());

    private static final Map<String, Object> SELF_MAP = new LinkedHashMap<>();

    private static final Map<String, Object> ALL_MAP = new LinkedHashMap<>();

    /**
     * 前台不展示的状态
     */
    private static final List<String> EXCLUDES = Arrays.asList(CREATE.getType());

    public static Map<String, Object> toMap() {
        for (AuditTypeEnum value : AuditTypeEnum.values()) {
            if (!EXCLUDES.contains(value.getType())) {
                SELF_MAP.put(value.getType(), value.getDescribe());
            }
        }
        return SELF_MAP;
    }
    /**
     * 显示所有状态
     */
    public static Map<String, Object> toAllMap() {
        for (AuditTypeEnum value : AuditTypeEnum.values()) {
            ALL_MAP.put(value.getType(), value.getDescribe());
        }
        return ALL_MAP;
    }

    private static final Map<String, Object> ALL_MAP_PLUS = new LinkedHashMap<>();

    public static Map<String, Object> toAllMapPlus() {
        Map<String, Object> inner = null;
        for (AuditTypeEnum value : AuditTypeEnum.values()) {
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
    public static AuditTypeEnum get(String code){
        for (AuditTypeEnum value : AuditTypeEnum.values()) {
            if(StringUtils.equals(value.getType(),code)){
                return value;
            }
        }
        return null;
    }
}
