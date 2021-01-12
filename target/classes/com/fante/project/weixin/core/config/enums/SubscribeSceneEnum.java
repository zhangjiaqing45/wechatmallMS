package com.fante.project.weixin.core.config.enums;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @program: Fante
 * @Date:
 * @Author: Mr.Z
 * @Description: 用户关注的渠道来源
 */
public enum SubscribeSceneEnum {

    /**
     * 公众号搜索
     */
    ADD_SCENE_SEARCH("ADD_SCENE_SEARCH", "公众号搜索"),
    /**
     * 扫描二维码
     */
    ADD_SCENE_QR_CODE("ADD_SCENE_QR_CODE", "扫描二维码"),
    /**
     * 公众号迁移
     */
    ADD_SCENE_ACCOUNT_MIGRATION("ADD_SCENE_ACCOUNT_MIGRATION", "公众号迁移"),
    /**
     * 名片分享
     */
    ADD_SCENE_PROFILE_CARD("ADD_SCENE_PROFILE_CARD", "名片分享"),
    /**
     * 图文页内名称点击
     */
    ADD_SCENE_PROFILE_LINK("ADD_SCENE_PROFILE_LINK", "图文页内名称点击"),
    /**
     * 图文页右上角菜单
     */
    ADD_SCENE_PROFILE_ITEM("ADD_SCENE_PROFILE_ITEM", "图文页右上角菜单"),
    /**
     * 支付后关注
     */
    ADD_SCENE_PAID("ADD_SCENE_PAID", "支付后关注"),
    /**
     * 其他
     */
    ADD_SCENE_OTHERS("ADD_SCENE_OTHERS", "其他"),
    ;

    private String type;
    private String describe;

    SubscribeSceneEnum(String type, String describe) {
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
        for (SubscribeSceneEnum value : SubscribeSceneEnum.values()) {
            SELF_MAP.put(value.getType(), value.getDescribe());
        }
        return SELF_MAP;
    }
}
