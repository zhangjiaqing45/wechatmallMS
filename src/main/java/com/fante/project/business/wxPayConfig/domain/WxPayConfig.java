package com.fante.project.business.wxPayConfig.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fante.framework.aspectj.lang.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import com.fante.framework.web.domain.BaseEntity;

/**
 * 微信支付配置对象 wx_pay_config
 * 
 * @author fante
 * @date 2020-04-09
 */
public class WxPayConfig extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "商户ID")
    @Excel(name = "商户ID")
    private String partnerId;

    @ApiModelProperty(value = "商户密钥")
    @Excel(name = "商户密钥")
    private String partnerKey;

    @ApiModelProperty(value = "通知URL")
    @Excel(name = "通知URL")
    private String notifyUrl;

    @ApiModelProperty(value = "证书路径")
    @Excel(name = "证书路径")
    private String certPath;

    @ApiModelProperty(value = "证书密匙")
    @Excel(name = "证书密匙")
    private String certSecret;

    @ApiModelProperty(value = "删除标记")
    private String delFlag;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getPartnerId() {
        return partnerId;
    }
    public void setPartnerKey(String partnerKey) {
        this.partnerKey = partnerKey;
    }

    public String getPartnerKey() {
        return partnerKey;
    }
    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }
    public void setCertPath(String certPath) {
        this.certPath = certPath;
    }

    public String getCertPath() {
        return certPath;
    }
    public void setCertSecret(String certSecret) {
        this.certSecret = certSecret;
    }

    public String getCertSecret() {
        return certSecret;
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
            .append("partnerId", getPartnerId())
            .append("partnerKey", getPartnerKey())
            .append("notifyUrl", getNotifyUrl())
            .append("certPath", getCertPath())
            .append("certSecret", getCertSecret())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
