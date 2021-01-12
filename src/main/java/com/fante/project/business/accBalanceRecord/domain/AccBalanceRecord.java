package com.fante.project.business.accBalanceRecord.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fante.framework.aspectj.lang.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import com.fante.framework.web.domain.BaseEntity;
import java.math.BigDecimal;

/**
 * 用户余额记录对象 acc_balance_record
 * 
 * @author fante
 * @date 2020-05-07
 */
public class AccBalanceRecord extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号")
    private Long id;

    @ApiModelProperty(value = "用户id")
    @Excel(name = "用户id")
    private Long memberId;

    @ApiModelProperty(value = "金额(可以是负数)")
    @Excel(name = "金额(可以是负数)")
    private BigDecimal money;

    @ApiModelProperty(value = "0佣金转入")
    @Excel(name = "0佣金转入")
    private String type;

    @ApiModelProperty(value = "0:佣金转入 1:提现转出")
    @Excel(name = "0:佣金转入 1:提现转出")
    private String operation;

    @ApiModelProperty(value = "描述:佣金转入余额/提现申请成功，余额转出")
    @Excel(name = "描述:佣金转入余额/提现申请成功，余额转出")
    private String description;

    @ApiModelProperty(value = "提现申请表id")
    @Excel(name = "提现申请表id")
    private Long cashApplyId;

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
    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
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
    public void setCashApplyId(Long cashApplyId) {
        this.cashApplyId = cashApplyId;
    }

    public Long getCashApplyId() {
        return cashApplyId;
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
            .append("type", getType())
            .append("operation", getOperation())
            .append("description", getDescription())
            .append("cashApplyId", getCashApplyId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
