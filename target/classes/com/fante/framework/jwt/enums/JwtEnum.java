package com.fante.framework.jwt.enums;

/**
 * @program: Fante
 * @Date:
 * @Author: Mr.Z
 * @Description: JWT校验结果
 */
public enum JwtEnum {
    /** 未登录 */
    TOKEN_NULL(false, "您尚未登录"),
    /** 登录过期 */
    EXPIRE(false, "您的登录已失效，请重新登录"),
    /** 别处登录 */
    KICK_OUT(false, "您已在别处登录，请您修改密码或重新登录"),
    /** 校验失败 */
    FAIL(false, "登录认证校验失败，请重新登录"),
    /** 校验成功 */
    SUCCESS(true, "校验成功"),
    ;

    private boolean pass;

    private String msg;

    JwtEnum(boolean pass, String msg) {
        this.pass = pass;
        this.msg = msg;
    }

    public boolean isPass() {
        return pass;
    }

    public String getMsg() {
        return msg;
    }
}
