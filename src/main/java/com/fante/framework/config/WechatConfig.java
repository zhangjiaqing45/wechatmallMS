package com.fante.framework.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @program: Fante
 * @Date: 2020/1/15 11:16
 * @Author: Mr.Z
 * @Description: 微信公众号配置
 */
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatConfig {

    /**
     * 服务器配置，启用/停用
     */
    private boolean wxServConfig;

    /**
     * 系统微信网页授权链接
     */
    private String accessTokenUrl;
    /**
     * 公众号页面首页
     */
    private String indexPage;

    /**
     * 商品描述 (公众号支付: 商家名称-销售商品类目)
     */
    private String wxPayBody;

    /**
     * 金额元转换为分的系数
     */
    private String transYuanToFen;

    public boolean getWxServConfig() {
        return wxServConfig;
    }

    public void setWxServConfig(boolean wxServConfig) {
        this.wxServConfig = wxServConfig;
    }

    public String getAccessTokenUrl() {
        return accessTokenUrl;
    }

    public void setAccessTokenUrl(String accessTokenUrl) {
        this.accessTokenUrl = accessTokenUrl;
    }

    public String getIndexPage() {
        return indexPage;
    }

    public void setIndexPage(String indexPage) {
        this.indexPage = indexPage;
    }

    public String getWxPayBody() {
        return wxPayBody;
    }

    public void setWxPayBody(String wxPayBody) {
        this.wxPayBody = wxPayBody;
    }

    public String getTransYuanToFen() {
        return transYuanToFen;
    }

    public void setTransYuanToFen(String transYuanToFen) {
        this.transYuanToFen = transYuanToFen;
    }
}
