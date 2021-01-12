package com.fante.project.yapi.business.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.Map;

@XmlRootElement(
        name = "xml"
)
public class YpayNotifyResult {
    private String amount;
    private String timePaid;
    private String clientId;
    private String orderNo;
    private String bankType;
    private String resultCode;
    private String cardType;
    private String sign;
    private String outOrderNo;
    private String deviceId;
    private String resultMsg;

    public YpayNotifyResult() {
    }

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap();
        map.put("amount", this.amount);
        map.put("timePaid", this.timePaid);
        map.put("clientId", this.clientId);
        map.put("orderNo", this.orderNo);
        map.put("bankType", this.bankType);
        map.put("resultCode", this.resultCode);
        map.put("cardType", this.cardType);
        map.put("sign", this.sign);
        map.put("outOrderNo", this.outOrderNo);
        map.put("deviceId", this.deviceId);
        map.put("resultMsg", this.resultMsg);
        return map;
    }

    public String getAmount() {
        return amount;
    }
    @XmlElement(
            name = "amount"
    )
    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTimePaid() {
        return timePaid;
    }
    @XmlElement(
            name = "timePaid"
    )
    public void setTimePaid(String timePaid) {
        this.timePaid = timePaid;
    }

    public String getClientId() {
        return clientId;
    }
    @XmlElement(
            name = "clientId"
    )
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getOrderNo() {
        return orderNo;
    }
    @XmlElement(
            name = "orderNo"
    )
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getBankType() {
        return bankType;
    }
    @XmlElement(
            name = "bankType"
    )
    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public String getResultCode() {
        return resultCode;
    }
    @XmlElement(
            name = "resultCode"
    )
    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getCardType() {
        return cardType;
    }
    @XmlElement(
            name = "cardType"
    )
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getSign() {
        return sign;
    }
    @XmlElement(
            name = "sign"
    )
    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getOutOrderNo() {
        return outOrderNo;
    }
    @XmlElement(
            name = "outOrderNo"
    )
    public void setOutOrderNo(String outOrderNo) {
        this.outOrderNo = outOrderNo;
    }

    public String getDeviceId() {
        return deviceId;
    }
    @XmlElement(
            name = "deviceId"
    )
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getResultMsg() {
        return resultMsg;
    }
    @XmlElement(
            name = "resultMsg"
    )
    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }
}
