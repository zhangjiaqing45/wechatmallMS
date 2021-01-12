package com.fante.project.weixin.core.domain;

import org.weixin4j.model.pay.WXPay;

import java.io.Serializable;

/**
 * @program: Fante
 * @Date: 2020/2/21 15:26
 * @Author: Mr.Z
 * @Description: 微信统一下单结果
 */
public class UnifiedOrderRsp implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean status;

    private String msg;

    private WXPay wxPay;

    public UnifiedOrderRsp() {
    }

    public UnifiedOrderRsp(boolean status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public UnifiedOrderRsp(boolean status, String msg, WXPay wxPay) {
        this.status = status;
        this.msg = msg;
        this.wxPay = wxPay;
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

    public WXPay getWxPay() {
        return wxPay;
    }

    public void setWxPay(WXPay wxPay) {
        this.wxPay = wxPay;
    }
}
