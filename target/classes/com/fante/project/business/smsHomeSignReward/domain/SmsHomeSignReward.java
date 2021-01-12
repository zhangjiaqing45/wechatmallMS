package com.fante.project.business.smsHomeSignReward.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fante.framework.aspectj.lang.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import com.fante.framework.web.domain.BaseEntity;

import java.math.BigDecimal;

/**
 * 签到奖励记录对象 sms_home_sign_reward
 * 
 * @author fante
 * @date 2020-03-12
 */
public class SmsHomeSignReward extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "用户昵称")
    @Excel(name = "用户昵称")
    private String nickname;

    @ApiModelProperty(value = "奖励设置ID")
    private Long settingId;

    @ApiModelProperty(value = "本次获得奖励的签到类型(每日/连续/累计)")
    @Excel(name = "本次获得奖励的签到类型(每日/连续/累计)")
    private String signType;

    @ApiModelProperty(value = "签到次数")
    @Excel(name = "签到次数")
    private int signTimes;

    @ApiModelProperty(value = "获得的奖励值")
    @Excel(name = "获得的奖励值")
    private BigDecimal rewardVal;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }
    public void setSettingId(Long settingId) {
        this.settingId = settingId;
    }

    public Long getSettingId() {
        return settingId;
    }
    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getSignType() {
        return signType;
    }

    public BigDecimal getRewardVal() {
        return rewardVal;
    }

    public void setRewardVal(BigDecimal rewardVal) {
        this.rewardVal = rewardVal;
    }

    public int getSignTimes() {
        return signTimes;
    }

    public void setSignTimes(int signTimes) {
        this.signTimes = signTimes;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("nickname", getNickname())
            .append("settingId", getSettingId())
            .append("signType", getSignType())
            .append("rewardVal", getRewardVal())
            .append("createTime", getCreateTime())
            .toString();
    }
}
