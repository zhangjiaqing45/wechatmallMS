package com.fante.project.business.omsCartItem.domain;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 购物车对象 oms_cart_item
 * 
 * @author fante
 * @date 2020-03-28
 */
public class OmsCartItemParam implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Long memberId;
    /**
     * 秒杀 / 团购skuId
     */
    @ApiModelProperty(value = "活动id")
    private Long promotionId;
    /**
     * 添加购物车
     */
    @ApiModelProperty(value = "购物车id")
    private Long cartId;
    /**
     * 秒杀
     * 团购
     * 立即
     * 秒杀
     * 购物车
     */
    @ApiModelProperty(value = "skuId")
    private Long productSkuId;
    /**
     * 秒杀
     * 团购
     * 立即
     * 秒杀
     * 购物车
     */
    @ApiModelProperty(value = "购买数量")
    private Long quantity;

    @ApiModelProperty(value = "购物车类型")
    private String cartType;

    @ApiModelProperty(value = "地址id")
    private Long addressId;

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Long promotionId) {
        this.promotionId = promotionId;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public String getCartType() {
        return cartType;
    }

    public void setCartType(String cartType) {
        this.cartType = cartType;
    }

    public Long getProductSkuId() {
        return productSkuId;
    }

    public void setProductSkuId(Long productSkuId) {
        this.productSkuId = productSkuId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

}
