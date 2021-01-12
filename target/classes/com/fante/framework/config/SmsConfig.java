package com.fante.framework.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @program: Fante
 * @Date: 2020/2/04
 * @Author: Liuqingyu
 * @Description: 短信服务配置
 */
@Component
@ConfigurationProperties(prefix = "sms")
public class SmsConfig {

    /**是否启用*/
    private boolean enabled;

    /**短信宝账号*/
    private String smsUserName;

    /**短信宝密码*/
    private String smsPassword;

    /**请求地址*/
    private String smsUrl;

    /**公司签名 */
    private String coSign;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getSmsUserName() {
        return smsUserName;
    }

    public void setSmsUserName(String smsUserName) {
        this.smsUserName = smsUserName;
    }

    public String getSmsPassword() {
        return smsPassword;
    }

    public void setSmsPassword(String smsPassword) {
        this.smsPassword = smsPassword;
    }

    public String getSmsUrl() {
        return smsUrl;
    }

    public void setSmsUrl(String smsUrl) {
        this.smsUrl = smsUrl;
    }

    public String getCoSign() {
        return coSign;
    }

    public void setCoSign(String coSign) {
        this.coSign = coSign;
    }
}
