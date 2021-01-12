package com.fante.project.business.smsHomeSignSetting.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fante.framework.aspectj.lang.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import com.fante.framework.web.domain.BaseEntity;

import java.math.BigDecimal;

/**
 * 签到奖励设置对象 sms_home_sign_setting
 * 
 * @author fante
 * @date 2020-03-11
 */
public class SmsHomeSignSetting extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "签到奖励名称")
    @Excel(name = "签到奖励名称")
    private String name;

    @ApiModelProperty(value = "签到类型（每日/连续/累计）")
    @Excel(name = "签到类型", readConverterExp = "每=日/连续/累计")
    private String signType;

    @ApiModelProperty(value = "奖励类型（暂时只有积分）")
    private String rewardType;

    @ApiModelProperty(value = "签到次数")
    @Excel(name = "签到次数")
    private int signTimes;

    @ApiModelProperty(value = "奖励值")
    @Excel(name = "奖励值")
    private BigDecimal rewardVal;

    @ApiModelProperty(value = "状态")
    @Excel(name = "状态")
    private String status;

    @ApiModelProperty(value = "删除标识（0、未删除；1、删除）")
    private Long delFlag;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getSignType() {
        return signType;
    }
    public void setRewardType(String rewardType) {
        this.rewardType = rewardType;
    }

    public String getRewardType() {
        return rewardType;
    }

    public int getSignTimes() {
        return signTimes;
    }

    public void setSignTimes(int signTimes) {
        this.signTimes = signTimes;
    }

    public BigDecimal getRewardVal() {
        return rewardVal;
    }

    public void setRewardVal(BigDecimal rewardVal) {
        this.rewardVal = rewardVal;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
    public void setDelFlag(Long delFlag) {
        this.delFlag = delFlag;
    }

    public Long getDelFlag() {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("signType", getSignType())
            .append("rewardType", getRewardType())
            .append("signTimes", getSignTimes())
            .append("rewardVal", getRewardVal())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
