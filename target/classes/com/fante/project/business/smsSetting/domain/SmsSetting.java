package com.fante.project.business.smsSetting.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fante.framework.aspectj.lang.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import com.fante.framework.web.domain.BaseEntity;

/**
 * 营销设置(优惠券、团购、签到、秒杀、积分商城是否开启)对象 sms_setting
 * 
 * @author fante
 * @date 2020-03-10
 */
public class SmsSetting extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "优惠券活动开启/关闭")
    @Excel(name = "优惠券活动开启/关闭")
    private String couponStatus;

    @ApiModelProperty(value = "团购活动状态")
    @Excel(name = "团购活动状态")
    private String groupStatus;

    @ApiModelProperty(value = "签到活动状态")
    @Excel(name = "签到活动状态")
    private String signStatus;

    @ApiModelProperty(value = "秒杀活动状态")
    @Excel(name = "秒杀活动状态")
    private String flashStatus;

    @ApiModelProperty(value = "积分商城状态")
    @Excel(name = "积分商城状态")
    private String integralStatus;

    @ApiModelProperty(value = "状态")
    @Excel(name = "状态")
    private String status;

    @ApiModelProperty(value = "删除标记")
    private String delFlag;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public void setCouponStatus(String couponStatus) {
        this.couponStatus = couponStatus;
    }

    public String getCouponStatus() {
        return couponStatus;
    }
    public void setGroupStatus(String groupStatus) {
        this.groupStatus = groupStatus;
    }

    public String getGroupStatus() {
        return groupStatus;
    }
    public void setSignStatus(String signStatus) {
        this.signStatus = signStatus;
    }

    public String getSignStatus() {
        return signStatus;
    }
    public void setFlashStatus(String flashStatus) {
        this.flashStatus = flashStatus;
    }

    public String getFlashStatus() {
        return flashStatus;
    }
    public void setIntegralStatus(String integralStatus) {
        this.integralStatus = integralStatus;
    }

    public String getIntegralStatus() {
        return integralStatus;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
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
            .append("couponStatus", getCouponStatus())
            .append("groupStatus", getGroupStatus())
            .append("signStatus", getSignStatus())
            .append("flashStatus", getFlashStatus())
            .append("integralStatus", getIntegralStatus())
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
