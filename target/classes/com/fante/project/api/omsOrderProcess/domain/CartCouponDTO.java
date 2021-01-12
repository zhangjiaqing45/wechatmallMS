package com.fante.project.api.omsOrderProcess.domain;

import com.fante.common.business.enums.SmsCouponConst;
import com.fante.project.business.pmsProduct.domain.PmsProduct;
import com.fante.project.business.pmsSkuStock.domain.PmsSkuStock;
import com.fante.project.business.smsCoupon.domain.SmsCoupon;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 订单对象 oms_order
 *
 * @author fante
 * @date 2020-04-04
 */
public class CartCouponDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "优惠券类型")
    private String couponType;

    @ApiModelProperty(value = "组合优惠金额")
    private BigDecimal Amount;

    @ApiModelProperty(value = "优惠券ids")
    private String couponIds;

    public CartCouponDTO(String couponType, String couponIds, BigDecimal amount) {
        this.couponType = couponType;
        Amount = amount;
        this.couponIds = couponIds;
    }

    public CartCouponDTO() {
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

    public BigDecimal getAmount() {
        return Amount;
    }

    public void setAmount(BigDecimal amount) {
        Amount = amount;
    }

    public String getCouponIds() {
        return couponIds;
    }

    public void setCouponIds(String couponIds) {
        this.couponIds = couponIds;
    }
}
