package com.fante.project.api.omsOrderProcess.domain;

import com.fante.framework.aspectj.lang.annotation.Excel;
import com.fante.framework.web.domain.BaseEntity;
import com.fante.project.business.smsCoupon.domain.SmsCoupon;
import com.fante.project.business.umsMemberReceiveAddress.domain.UmsMemberReceiveAddress;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 订单对象 oms_order
 *
 * @author fante
 * @date 2020-04-04
 */
public class OmsOrderShow implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "店铺id")
    private Long shopId;
    @ApiModelProperty(value = "店铺名称")
    private String shopName;

    @ApiModelProperty(value = "默认地址")
    private UmsMemberReceiveAddress defaultAddress;

    @ApiModelProperty(value = "错误消息:key错误类型,value:商品id的集合对象")
    private Map<String, String> errMsg;

    @ApiModelProperty(value = "运费")
    private BigDecimal freightPrice;

    @ApiModelProperty(value = "购物车sku商品信息")
    private List<CartSkuDto> CartSkuList;

    @ApiModelProperty(value = "购物车可用优惠券信息")
    private List<SmsCoupon> couponList;

    @ApiModelProperty(value = "计算的金额总和")
    private BigDecimal totalPrice;

    @ApiModelProperty(value = "用户余额")
    private String memberBalance;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public List<CartSkuDto> getCartSkuList() {
        return CartSkuList;
    }

    public void setCartSkuList(List<CartSkuDto> cartSkuList) {
        CartSkuList = cartSkuList;
    }

    public List<SmsCoupon> getCouponList() {
        return couponList;
    }

    public void setCouponList(List<SmsCoupon> couponList) {
        this.couponList = couponList;
    }

    public UmsMemberReceiveAddress getDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(UmsMemberReceiveAddress defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    public Map<String, String> getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(Map<String, String> errMsg) {
        this.errMsg = errMsg;
    }

    public BigDecimal getFreightPrice() {
        return freightPrice;
    }

    public void setFreightPrice(BigDecimal freightPrice) {
        this.freightPrice = freightPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getMemberBalance() {
        return memberBalance;
    }

    public void setMemberBalance(String memberBalance) {
        this.memberBalance = memberBalance;
    }
}
