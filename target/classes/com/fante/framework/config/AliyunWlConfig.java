package com.fante.framework.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @program: Fante
 * @Date: 2020/2/06
 * @Author: Liuqingyu
 * @Description: 阿里云物流配置
 */
@Component
@ConfigurationProperties(prefix = "aliyunwl")
public class AliyunWlConfig {

    /**API签名认证调用方法（AppKey & AppSecret）*/
    private String AppKey;
    private String AppSecret;

    /**API 简单身份认证调用方法（APPCODE)*/
    private String AppCode;
    /**服务器请求地址*/
    private String host;
    /**请求路径*/
    private String path;
    /**请求方法*/
    private String method;
    /**自定义请求接口间隔时间（秒）*/
    private long interval;

    public String getAppKey() {
        return AppKey;
    }

    public void setAppKey(String appKey) {
        AppKey = appKey;
    }

    public String getAppSecret() {
        return AppSecret;
    }

    public void setAppSecret(String appSecret) {
        AppSecret = appSecret;
    }

    public String getAppCode() {
        return AppCode;
    }

    public void setAppCode(String appCode) {
        AppCode = appCode;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public long getInterval() {
        return interval;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }
}
