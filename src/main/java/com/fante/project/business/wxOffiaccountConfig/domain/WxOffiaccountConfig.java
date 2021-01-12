package com.fante.project.business.wxOffiaccountConfig.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fante.framework.aspectj.lang.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import com.fante.framework.web.domain.BaseEntity;

/**
 * 微信公众号配置对象 wx_offiaccount_config
 * 
 * @author fante
 * @date 2020-04-09
 */
public class WxOffiaccountConfig extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "开发者ID")
    @Excel(name = "开发者ID")
    private String appid;

    @ApiModelProperty(value = "开发者密码")
    @Excel(name = "开发者密码")
    private String secret;

    @ApiModelProperty(value = "公众号原始ID")
    @Excel(name = "公众号原始ID")
    private String originalid;

    @ApiModelProperty(value = "消息加密方式")
    @Excel(name = "消息加密方式")
    private Integer encodingtype;

    @ApiModelProperty(value = "消息加密密钥")
    @Excel(name = "消息加密密钥")
    private String encodingaeskey;

    @ApiModelProperty(value = "网页安全授权URL")
    @Excel(name = "网页安全授权URL")
    private String oauthUrl;

    @ApiModelProperty(value = "公众平台接口域名")
    @Excel(name = "公众平台接口域名")
    private String apiDomain;

    @ApiModelProperty(value = "删除标记")
    private String delFlag;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getAppid() {
        return appid;
    }
    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getSecret() {
        return secret;
    }
    public void setOriginalid(String originalid) {
        this.originalid = originalid;
    }

    public String getOriginalid() {
        return originalid;
    }
    public void setEncodingtype(Integer encodingtype) {
        this.encodingtype = encodingtype;
    }

    public Integer getEncodingtype() {
        return encodingtype;
    }
    public void setEncodingaeskey(String encodingaeskey) {
        this.encodingaeskey = encodingaeskey;
    }

    public String getEncodingaeskey() {
        return encodingaeskey;
    }
    public void setOauthUrl(String oauthUrl) {
        this.oauthUrl = oauthUrl;
    }

    public String getOauthUrl() {
        return oauthUrl;
    }
    public void setApiDomain(String apiDomain) {
        this.apiDomain = apiDomain;
    }

    public String getApiDomain() {
        return apiDomain;
    }
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("appid", getAppid())
            .append("secret", getSecret())
            .append("originalid", getOriginalid())
            .append("encodingtype", getEncodingtype())
            .append("encodingaeskey", getEncodingaeskey())
            .append("oauthUrl", getOauthUrl())
            .append("apiDomain", getApiDomain())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
