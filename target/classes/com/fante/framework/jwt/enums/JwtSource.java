package com.fante.framework.jwt.enums;

/**
 * @program: Fante
 * @Date: 2020/1/15 18:05
 * @Author: Mr.Z
 * @Description: 创建JWT的来源
 */
public enum JwtSource {

    /**
     * PC端
     */
    PC("pc"),
    /**
     * APP端
     */
    APP("app"),
    /**
     * 微信公众号端
     */
    WECHAT("wechat"),
    /**
     * 微信小程序端
     */
    WECHAT_MINI("wechat_mini"),
    ;


    private String type;

    JwtSource(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
