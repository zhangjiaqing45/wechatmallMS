package com.fante.project.weixin.core.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: Fante
 * @Date: 2020/5/7 10:42
 * @Author: Mr.Z
 * @Description: 退款请求参数
 */
public class OrderRefund {

    private String appid;
    private String mch_id;
    private String nonce_str;
    private String sign;
    private String transaction_id;
    private String out_trade_no;
    private String out_refund_no;
    private String total_fee;
    private String refund_fee;

    public OrderRefund() {
    }

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("appid", appid);
        map.put("mch_id", mch_id);
        map.put("nonce_str", nonce_str);
        map.put("out_trade_no", out_trade_no);
        map.put("out_refund_no", out_refund_no);
        map.put("refund_fee", refund_fee);
        map.put("total_fee", total_fee);
        //map.put("transaction_id", transaction_id);
        return map;
    }

    public String toXML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<xml>");
        sb.append("<appid><![CDATA[").append(this.appid).append("]]></appid>");
        sb.append("<mch_id><![CDATA[").append(this.mch_id).append("]]></mch_id>");
        sb.append("<nonce_str><![CDATA[").append(this.nonce_str).append("]]></nonce_str>");
        sb.append("<out_refund_no><![CDATA[").append(this.out_refund_no).append("]]></out_refund_no>");
        sb.append("<out_trade_no><![CDATA[").append(this.out_trade_no).append("]]></out_trade_no>");
        sb.append("<refund_fee><![CDATA[").append(this.refund_fee).append("]]></refund_fee>");
        sb.append("<total_fee><![CDATA[").append(this.total_fee).append("]]></total_fee>");
        //sb.append("<transaction_id><![CDATA[").append(this.transaction_id).append("]]></transaction_id>");
        sb.append("<sign><![CDATA[").append(this.sign).append("]]></sign>");
        sb.append("</xml>");
        return sb.toString();
    }


    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getOut_refund_no() {
        return out_refund_no;
    }

    public void setOut_refund_no(String out_refund_no) {
        this.out_refund_no = out_refund_no;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    public String getRefund_fee() {
        return refund_fee;
    }

    public void setRefund_fee(String refund_fee) {
        this.refund_fee = refund_fee;
    }
}
