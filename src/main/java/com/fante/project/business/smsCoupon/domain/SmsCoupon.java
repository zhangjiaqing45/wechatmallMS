package com.fante.project.business.smsCoupon.domain;

import com.fante.common.business.enums.SmsCouponConst;
import com.fante.common.utils.Checker;
import com.fante.common.utils.StringUtils;
import com.fante.framework.aspectj.lang.annotation.Excel;
import com.fante.framework.web.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 优惠券对象 sms_coupon
 * 
 * @author fante
 * @date 2020-03-19
 */
public class SmsCoupon extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "null")
    private Long id;

    @ApiModelProperty(value = "店铺ID")
    private Long shopId;

    @ApiModelProperty(value = "优惠券类型")
    @Excel(name = "优惠券类型")
    private String type;

    @ApiModelProperty(value = "优惠券名称")
    @Excel(name = "优惠券名称")
    private String name;

    @ApiModelProperty(value = "数量")
    @Excel(name = "数量")
    private Integer count;

    @ApiModelProperty(value = "金额")
    @Excel(name = "金额")
    private BigDecimal amount;

    @ApiModelProperty(value = "每人限领张数")
    @Excel(name = "每人限领张数")
    private Long perLimit;

    @ApiModelProperty(value = "使用门槛")
    @Excel(name = "使用门槛")
    private Long minPoint;

    @ApiModelProperty(value = "有效时间")
    @Excel(name = "有效时间", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date startTime;

    @ApiModelProperty(value = "到期时间")
    @Excel(name = "到期时间", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date endTime;

    @ApiModelProperty(value = "使用类型")
    @Excel(name = "使用类型")
    private String useType;

    @ApiModelProperty(value = "发行数量")
    @Excel(name = "发行数量")
    private Long publishCount;

    @ApiModelProperty(value = "已使用数量")
    @Excel(name = "已使用数量")
    private Long useCount;

    @ApiModelProperty(value = "领取数量")
    @Excel(name = "领取数量")
    private Long receiveCount;

    @ApiModelProperty(value = "可以领取的日期（不用）")
    @Excel(name = "可以领取的日期", width = 30, dateFormat = "yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date enableTime;

    @ApiModelProperty(value = "优惠码")
    private String code;

    @ApiModelProperty(value = "可领取的会员类型")
    @Excel(name = "可领取的会员类型")
    private String memberLevel;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "删除标记")
    private String delFlag;

    @ApiModelProperty(value = "优惠券类型")
    @Excel(name = "优惠券类型")
    private String couponType;

    @ApiModelProperty(value = "折扣值")
    @Excel(name = "折扣值")
    private BigDecimal discount;


    public void validate() {
        Checker.check(StringUtils.isBlank(getCouponType()), "缺少优惠券类型");
        if(StringUtils.equals(this.couponType, SmsCouponConst.CouponTypeEnum.FULL.getType())){
            //验证满减
            Checker.check(ObjectUtils.isEmpty(getAmount()), "缺少优惠券面额");
        }else if(StringUtils.equals(this.couponType, SmsCouponConst.CouponTypeEnum.DICOUNT.getType())){
            //验证折扣
            Checker.check(ObjectUtils.isEmpty(getDiscount()), "缺少优惠券折扣值");
            //范围 0-10
            Checker.checkOp(0< this.discount.doubleValue()&&this.discount.doubleValue()<10, "折扣值格式不正确");
        }
        Checker.check(ObjectUtils.isEmpty(getShopId()), "缺少店铺标识");
        //Checker.check(StringUtils.isBlank(getType()), "缺少获取类型");
        Checker.check(StringUtils.isBlank(getName()), "缺少优惠券名称");
        Checker.check(ObjectUtils.isEmpty(getCount()), "缺少发行量");
        Checker.check(ObjectUtils.isEmpty(getShopId()), "缺少店铺信息");
        Checker.check(ObjectUtils.isEmpty(getPerLimit()), "缺少每人限领");
        Checker.check(ObjectUtils.isEmpty(getMinPoint()), "缺少使用门槛");
        Checker.check(ObjectUtils.isEmpty(getStartTime()) || ObjectUtils.isEmpty(getEndTime()), "缺少有效期");
        Checker.check(StringUtils.isBlank(getUseType()), "缺少使用范围类型");
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getShopId() {
        return shopId;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getCount() {
        return count;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }
    public void setPerLimit(Long perLimit) {
        this.perLimit = perLimit;
    }

    public Long getPerLimit() {
        return perLimit;
    }

    public Long getMinPoint() {
        return minPoint;
    }

    public void setMinPoint(Long minPoint) {
        this.minPoint = minPoint;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getStartTime() {
        return startTime;
    }
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getEndTime() {
        return endTime;
    }
    public void setUseType(String useType) {
        this.useType = useType;
    }

    public String getUseType() {
        return useType;
    }
    public void setPublishCount(Long publishCount) {
        this.publishCount = publishCount;
    }

    public Long getPublishCount() {
        return publishCount;
    }
    public void setUseCount(Long useCount) {
        this.useCount = useCount;
    }

    public Long getUseCount() {
        return useCount;
    }
    public void setReceiveCount(Long receiveCount) {
        this.receiveCount = receiveCount;
    }

    public Long getReceiveCount() {
        return receiveCount;
    }
    public void setEnableTime(Date enableTime) {
        this.enableTime = enableTime;
    }

    public Date getEnableTime() {
        return enableTime;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
    public void setMemberLevel(String memberLevel) {
        this.memberLevel = memberLevel;
    }

    public String getMemberLevel() {
        return memberLevel;
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
            .append("shopId", getShopId())
            .append("type", getType())
            .append("name", getName())
            .append("count", getCount())
            .append("amount", getAmount())
            .append("perLimit", getPerLimit())
            .append("minPoint", getMinPoint())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("useType", getUseType())
            .append("publishCount", getPublishCount())
            .append("useCount", getUseCount())
            .append("receiveCount", getReceiveCount())
            .append("enableTime", getEnableTime())
            .append("code", getCode())
            .append("memberLevel", getMemberLevel())
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
