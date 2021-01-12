package com.fante.project.business.wxSettings.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fante.framework.aspectj.lang.annotation.Excel;
import com.fante.framework.web.domain.BaseEntity;

/**
 * 微信公众号设置对象 wx_settings
 * 
 * @author fante
 * @date 2020-02-21
 */
public class WxSettings extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 关注提示 */
    @Excel(name = "关注提示")
    private String prompt;

    /** 公众号二维码 */
    @Excel(name = "公众号二维码")
    private String qrcode;

    /** 客服电话 */
    @Excel(name = "客服电话")
    private String serviceTel;

    /** 分享图片 */
    @Excel(name = "分享图片")
    private String shareImg;

    /** 分享标题 */
    @Excel(name = "分享标题")
    private String shareTitle;

    /** 分享描述 */
    @Excel(name = "分享描述")
    private String shareDesc;

    /** 删除标记 */
    private String delFlag;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public String getQrcode() {
        return qrcode;
    }
    public void setServiceTel(String serviceTel) {
        this.serviceTel = serviceTel;
    }

    public String getServiceTel() {
        return serviceTel;
    }
    public void setShareImg(String shareImg) {
        this.shareImg = shareImg;
    }

    public String getShareImg() {
        return shareImg;
    }
    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }

    public String getShareTitle() {
        return shareTitle;
    }
    public void setShareDesc(String shareDesc) {
        this.shareDesc = shareDesc;
    }

    public String getShareDesc() {
        return shareDesc;
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
            .append("prompt", getPrompt())
            .append("qrcode", getQrcode())
            .append("serviceTel", getServiceTel())
            .append("shareImg", getShareImg())
            .append("shareTitle", getShareTitle())
            .append("shareDesc", getShareDesc())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
