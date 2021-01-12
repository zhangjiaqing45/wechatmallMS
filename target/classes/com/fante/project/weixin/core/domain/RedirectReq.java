package com.fante.project.weixin.core.domain;

import com.fante.common.utils.StringUtils;

import java.io.Serializable;

/**
 * @program: Fante
 * @Date: 2020/4/18 10:09
 * @Author: Mr.Z
 * @Description: 网页授权重定向参数
 */
public class RedirectReq implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 邀请人openid
     */
    private String inviterId;


    public boolean effective() {
        if (StringUtils.isBlank(getInviterId())) {
            return false;
        }
        return true;
    }


    public String getInviterId() {
        return inviterId;
    }

    public void setInviterId(String inviterId) {
        this.inviterId = inviterId;
    }
}
