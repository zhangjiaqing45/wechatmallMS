package com.fante.project.business.smsCouponHistory.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fante.framework.aspectj.lang.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import com.fante.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 优惠券使用、领取历史对象 sms_coupon_history
 * 
 * @author fante
 * @date 2020-03-31
 */
public class SmsCouponHistory extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "$column.columnComment")
    private Long id;

    @ApiModelProperty(value = "领取用户")
    @Excel(name = "领取用户")
    private Long userId;

    @ApiModelProperty(value = "优惠券ID")
    @Excel(name = "优惠券ID")
    private Long couponId;

    @ApiModelProperty(value = "$column.columnComment")
    @Excel(name = "优惠券ID")
    private String couponCode;

    @ApiModelProperty(value = "获取类型：0->后台赠送；1->主动获取")
    @Excel(name = "获取类型：0->后台赠送；1->主动获取")
    private String getType;

    @ApiModelProperty(value = "使用状态：0->未使用；1->已使用；2->已过期")
    @Excel(name = "使用状态：0->未使用；1->已使用；2->已过期")
    private String useStatus;

    @ApiModelProperty(value = "使用时间")
    @Excel(name = "使用时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date useTime;

    @ApiModelProperty(value = "订单id")
    @Excel(name = "订单id")
    private Long orderId;

    @ApiModelProperty(value = "订单号码")
    @Excel(name = "订单号码")
    private String orderSn;

    @ApiModelProperty(value = "核销员")
    @Excel(name = "核销员")
    private String updateBy;

    @Override
    public String getUpdateBy() {
        return updateBy;
    }

    @Override
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

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
    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public Long getCouponId() {
        return couponId;
    }
    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getCouponCode() {
        return couponCode;
    }
    public void setGetType(String getType) {
        this.getType = getType;
    }

    public String getGetType() {
        return getType;
    }
    public void setUseStatus(String useStatus) {
        this.useStatus = useStatus;
    }

    public String getUseStatus() {
        return useStatus;
    }
    public void setUseTime(Date useTime) {
        this.useTime = useTime;
    }

    public Date getUseTime() {
        return useTime;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }
    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public String getOrderSn() {
        return orderSn;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("couponId", getCouponId())
            .append("couponCode", getCouponCode())
            .append("getType", getGetType())
            .append("createTime", getCreateTime())
            .append("useStatus", getUseStatus())
            .append("useTime", getUseTime())
            .append("orderId", getOrderId())
            .append("orderSn", getOrderSn())
            .toString();
    }
}
