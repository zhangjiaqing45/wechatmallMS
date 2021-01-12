package com.fante.framework.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @program: Fante
 * @Date: 2020/4/10 11:22
 * @Author: Mr.Z
 * @Description: 阿里云存储配置
 */
@Component
@ConfigurationProperties(prefix = "aliyunoss")
public class AliyunOSSConfig {

    /**
     * 是否启用
     */
    private boolean enabled;
    /**
     * 用户AccessKey ID
     */
    private String accessKeyID;
    /**
     * 用户AccessKey Secret
     */
    private String accessKeySecret;
    /**
     * 地域节点(外网访问)
     */
    private String endPoint;
    /**
     * Bucket 名称
     */
    private String bucketName;


    /**
     * Bucket 域名
     * @return
     */
    public String getBucketDomian() {
        return "https://" + getBucketName() + "." + getEndPoint() + "/";
    }


    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getAccessKeyID() {
        return accessKeyID;
    }

    public void setAccessKeyID(String accessKeyID) {
        this.accessKeyID = accessKeyID;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }
}
