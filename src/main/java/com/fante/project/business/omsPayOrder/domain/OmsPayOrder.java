package com.fante.project.business.omsPayOrder.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fante.framework.aspectj.lang.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import com.fante.framework.web.domain.BaseEntity;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 支付订单对象 oms_pay_order
 * 
 * @author fante
 * @date 2020-04-18
 */
public class OmsPayOrder extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "会员id")
    @Excel(name = "会员id")
    private Long memberId;

    @ApiModelProperty(value = "拼团的id")
    @Excel(name = "拼团的id")
    private Long groupId;

    @ApiModelProperty(value = "支付订单编号")
    @Excel(name = "支付订单编号")
    private String payOrderSn;

    @ApiModelProperty(value = "所有订单支付总价格")
    @Excel(name = "所有订单支付总价格")
    private BigDecimal payTotalPrice;

    @ApiModelProperty(value = "状态(未支付,已支付)")
    @Excel(name = "状态(未支付,已支付)")
    private String status;

    @ApiModelProperty(value = "删除标记")
    private String delFlag;

    public OmsPayOrder() {
    }

    public OmsPayOrder(Long memberId, String payOrderSn, BigDecimal payTotalPrice, Date createTime) {
        this.memberId = memberId;
        this.payOrderSn = payOrderSn;
        this.payTotalPrice = payTotalPrice;
        super.setCreateTime(createTime);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getMemberId() {
        return memberId;
    }
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getGroupId() {
        return groupId;
    }
    public void setPayOrderSn(String payOrderSn) {
        this.payOrderSn = payOrderSn;
    }

    public String getPayOrderSn() {
        return payOrderSn;
    }
    public void setPayTotalPrice(BigDecimal payTotalPrice) {
        this.payTotalPrice = payTotalPrice;
    }

    public BigDecimal getPayTotalPrice() {
        return payTotalPrice;
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
            .append("memberId", getMemberId())
            .append("groupId", getGroupId())
            .append("payOrderSn", getPayOrderSn())
            .append("payTotalPrice", getPayTotalPrice())
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
