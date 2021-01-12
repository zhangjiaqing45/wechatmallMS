package com.fante.project.api.omsOrderProcess.domain;

import com.fante.project.business.smsCoupon.domain.SmsCoupon;
import com.fante.project.business.umsMemberReceiveAddress.domain.UmsMemberReceiveAddress;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 订单对象 oms_order
 *
 * @author fante
 * @date 2020-04-04
 */
public class OmsOrderCommissionDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单详情id")
    private Long orderItemId;
    @ApiModelProperty(value = "佣金")
    private BigDecimal money;
    @ApiModelProperty(value = "数量")
    private int quantity;

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
