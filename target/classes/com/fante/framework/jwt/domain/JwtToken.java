package com.fante.framework.jwt.domain;

import java.io.Serializable;

/**
 * @program: Fante
 * @Date:
 * @Author: Mr.Z
 * @Description: JWT生成Token的自定义声明内容
 */
public class JwtToken implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户标识
     */
    private long userId;

    /**
     * 用户端标识
     */
    private String clientId;

    /**
     * 用户端来源
     */
    private String source;

    public JwtToken() {
    }

    public JwtToken(long userId, String clientId, String source) {
        this.userId = userId;
        this.clientId = clientId;
        this.source = source;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
