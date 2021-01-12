package com.fante.project.business.accAccountRecord.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fante.framework.aspectj.lang.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import com.fante.framework.web.domain.BaseEntity;
import java.math.BigDecimal;

/**
 * 账户出入账记录对象 acc_account_record
 * 
 * @author fante
 * @date 2020-05-09
 */
public class AccAccountRecord extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号")
    private Long id;

    @ApiModelProperty(value = "到账店铺id")
    @Excel(name = "到账店铺id")
    private Long shopId;

    @ApiModelProperty(value = "金额(可以是负数)")
    @Excel(name = "金额(可以是负数)")
    private BigDecimal money;

    @ApiModelProperty(value = "操作:0佣金收入 1订单金额入账 2退货订单出账 3退货佣金出账4店铺提现出账5.用户提现出账")
    @Excel(name = "操作:0佣金收入 1订单金额入账 2退货订单出账 3退货佣金出账4店铺提现出账5.用户提现出账")
    private String operation;

    @ApiModelProperty(value = "金额类型->0:佣金1:商品")
    @Excel(name = "金额类型->0:佣金1:商品")
    private String type;

    @ApiModelProperty(value = "描述:{}:商品购买,转入金额/{}:商品退货,资金返还/{}:申请提现,资金转出")
    @Excel(name = "描述:{}:商品购买,转入金额/{}:商品退货,资金返还/{}:申请提现,资金转出")
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
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getShopId() {
        return shopId;
    }
    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getMoney() {
        return money;
    }
    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getOperation() {
        return operation;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
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
            .append("shopId", getShopId())
            .append("money", getMoney())
            .append("operation", getOperation())
            .append("type", getType())
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
