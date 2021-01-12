package com.fante.project.business.smsCouponHistory.domain;

import com.fante.framework.aspectj.lang.annotation.Excel;
import com.fante.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 优惠券使用、领取历史对象 sms_coupon_history
 * 
 * @author fante
 * @date 2020-03-20
 */
public class SmsCouponHistoryDTO extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "优惠券ID")
    private Long couponId;

    @ApiModelProperty(value = "用户ID")
    @Excel(name = "用户ID")
    private Long userId;
    private String userName;
    private String userImg;

    @ApiModelProperty(value = "订单id")
    @Excel(name = "订单id")
    private Long orderId;

    @ApiModelProperty(value = "优惠券编号")
    private String couponCode;

    @ApiModelProperty(value = "获取类型")
    @Excel(name = "获取类型")
    private Integer getType;

    @ApiModelProperty(value = "使用状态")
    @Excel(name = "使用状态")
    private Integer useStatus;

    @ApiModelProperty(value = "使用时间")
    @Excel(name = "使用时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date useTime;

    @ApiModelProperty(value = "订单号码")
   // @Excel(name = "订单号码")
    private String orderSn;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public Long getCouponId() {
        return couponId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }
    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getCouponCode() {
        return couponCode;
    }
    public void setGetType(Integer getType) {
        this.getType = getType;
    }

    public Integer getGetType() {
        return getType;
    }
    public void setUseStatus(Integer useStatus) {
        this.useStatus = useStatus;
    }

    public Integer getUseStatus() {
        return useStatus;
    }
    public void setUseTime(Date useTime) {
        this.useTime = useTime;
    }

    public Date getUseTime() {
        return useTime;
    }
    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public String getOrderSn() {
        return orderSn;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("couponId", getCouponId())
            .append("userId", getUserId())
            .append("orderId", getOrderId())
            .append("couponCode", getCouponCode())
            .append("getType", getGetType())
            .append("createTime", getCreateTime())
            .append("useStatus", getUseStatus())
            .append("useTime", getUseTime())
            .append("orderSn", getOrderSn())
            .toString();
    }
}
