package com.fante.project.business.omsOrderSetting.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fante.framework.aspectj.lang.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import com.fante.framework.web.domain.BaseEntity;

/**
 * 订单设置对象 oms_order_setting
 * 
 * @author fante
 * @date 2020-05-13
 */
public class OmsOrderSetting extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "状态")
    @Excel(name = "状态")
    private String status;

    @ApiModelProperty(value = "自动收货时间（天）")
    @Excel(name = "自动收货时间", readConverterExp = "天=")
    private Long autoConfirmReceive;

    @ApiModelProperty(value = "自动取消普通订单（分钟）")
    @Excel(name = "自动取消普通订单", readConverterExp = "分=钟")
    private Long autoCancelGeneralOrder;

    @ApiModelProperty(value = "自动取消团购订单（分钟）")
    @Excel(name = "自动取消团购订单", readConverterExp = "分=钟")
    private Long autoCancelGroupOrder;

    @ApiModelProperty(value = "自动取消秒杀订单（分钟）")
    @Excel(name = "自动取消秒杀订单", readConverterExp = "分=钟")
    private Long autoCancelFlashOrder;

    @ApiModelProperty(value = "自动取消积分订单（分钟）")
    @Excel(name = "自动取消积分订单", readConverterExp = "分=钟")
    private Long autoCancelIntegralOrder;

    @ApiModelProperty(value = "获取自动关闭订单的时间（天）")
    @Excel(name = "获取自动关闭订单的时间", readConverterExp = "天=")
    private Long autoCloseOrder;

    @ApiModelProperty(value = "佣金有效期（天）")
    @Excel(name = "佣金有效期", readConverterExp = "天=")
    private Long commissionAging;

    @ApiModelProperty(value = "删除标记")
    private String delFlag;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
    public void setAutoConfirmReceive(Long autoConfirmReceive) {
        this.autoConfirmReceive = autoConfirmReceive;
    }

    public Long getAutoConfirmReceive() {
        return autoConfirmReceive;
    }
    public void setAutoCancelGeneralOrder(Long autoCancelGeneralOrder) {
        this.autoCancelGeneralOrder = autoCancelGeneralOrder;
    }

    public Long getAutoCancelGeneralOrder() {
        return autoCancelGeneralOrder;
    }
    public void setAutoCancelGroupOrder(Long autoCancelGroupOrder) {
        this.autoCancelGroupOrder = autoCancelGroupOrder;
    }

    public Long getAutoCancelGroupOrder() {
        return autoCancelGroupOrder;
    }
    public void setAutoCancelFlashOrder(Long autoCancelFlashOrder) {
        this.autoCancelFlashOrder = autoCancelFlashOrder;
    }

    public Long getAutoCancelFlashOrder() {
        return autoCancelFlashOrder;
    }
    public void setAutoCancelIntegralOrder(Long autoCancelIntegralOrder) {
        this.autoCancelIntegralOrder = autoCancelIntegralOrder;
    }

    public Long getAutoCancelIntegralOrder() {
        return autoCancelIntegralOrder;
    }
    public void setAutoCloseOrder(Long autoCloseOrder) {
        this.autoCloseOrder = autoCloseOrder;
    }

    public Long getAutoCloseOrder() {
        return autoCloseOrder;
    }
    public void setCommissionAging(Long commissionAging) {
        this.commissionAging = commissionAging;
    }

    public Long getCommissionAging() {
        return commissionAging;
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
            .append("status", getStatus())
            .append("autoConfirmReceive", getAutoConfirmReceive())
            .append("autoCancelGeneralOrder", getAutoCancelGeneralOrder())
            .append("autoCancelGroupOrder", getAutoCancelGroupOrder())
            .append("autoCancelFlashOrder", getAutoCancelFlashOrder())
            .append("autoCancelIntegralOrder", getAutoCancelIntegralOrder())
            .append("autoCloseOrder", getAutoCloseOrder())
            .append("commissionAging", getCommissionAging())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
