package com.fante.project.weixin.core.domain;

import java.io.Serializable;

/**
 * @program: Fante
 * @Date: 2020/5/7 14:22
 * @Author: Mr.Z
 * @Description: 申请退款结果
 */
public class OrderRefundRsp implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean status;

    private String msg;

    public OrderRefundRsp() {
    }

    public OrderRefundRsp(boolean status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
