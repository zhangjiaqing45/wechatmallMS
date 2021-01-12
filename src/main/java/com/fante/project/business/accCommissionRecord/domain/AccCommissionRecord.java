package com.fante.project.business.accCommissionRecord.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fante.framework.aspectj.lang.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import com.fante.framework.web.domain.BaseEntity;
import java.math.BigDecimal;

/**
 * 用户佣金记录对象 acc_commission_record
 * 
 * @author fante
 * @date 2020-05-09
 */
public class AccCommissionRecord extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号")
    private Long id;

    @ApiModelProperty(value = "用户id")
    @Excel(name = "用户id")
    private Long memberId;

    @ApiModelProperty(value = "金额(可以是负数)")
    @Excel(name = "金额(可以是负数)")
    private BigDecimal money;

    @ApiModelProperty(value = "0待入佣金 1可提现佣金")
    @Excel(name = "0待入佣金 1可提现佣金")
    private String status;

    @ApiModelProperty(value = "0:获得佣金 1:退货 2:转入余额")
    @Excel(name = "0:获得佣金 1:退货 2:转入余额")
    private String operation;

    @ApiModelProperty(value = "描述:{}购买商品获得佣金/{}退货清除佣金/转入余额清除佣金")
    @Excel(name = "描述:{}购买商品获得佣金/{}退货清除佣金/转入余额清除佣金")
    private String description;

    @ApiModelProperty(value = "申请单号(退货申请/提现申请)")
    @Excel(name = "申请单号(退货申请/提现申请)")
    private Long applyId;

    @ApiModelProperty(value = "订单号")
    @Excel(name = "订单号")
    private Long orderId;

    @ApiModelProperty(value = "删除标记")
    private String delFlag;

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
    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getMoney() {
        return money;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getOperation() {
        return operation;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
    public void setApplyId(Long applyId) {
        this.applyId = applyId;
    }

    public Long getApplyId() {
        return applyId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
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
            .append("money", getMoney())
            .append("status", getStatus())
            .append("operation", getOperation())
            .append("description", getDescription())
            .append("applyId", getApplyId())
            .append("orderId", getOrderId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
