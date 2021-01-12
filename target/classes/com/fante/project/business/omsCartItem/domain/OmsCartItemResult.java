package com.fante.project.business.omsCartItem.domain;

import com.fante.framework.aspectj.lang.annotation.Excel;
import com.fante.framework.web.domain.BaseEntity;
import com.fante.project.api.omsOrderProcess.domain.CartDetail;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.List;

/**
 * 购物车对象 oms_cart_item
 * 
 * @author fante
 * @date 2020-03-28
 */
public class OmsCartItemResult implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "店铺id")
    private Long shopId;
    @ApiModelProperty(value = "店铺名称")
    private String shopName;
    @ApiModelProperty(value = "购物车列表")
    private List<CartDetail> cartList;

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

    public List<CartDetail> getCartList() {
        return cartList;
    }

    public void setCartList(List<CartDetail> cartList) {
        this.cartList = cartList;
    }
}
