package com.fante.project.api.omsOrderProcess.domain;

import com.fante.framework.aspectj.lang.annotation.Excel;
import com.fante.project.business.pmsSkuStock.domain.PmsSkuStock;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单对象 oms_order
 *
 * @author fante
 * @date 2020-04-04
 */
public class CartSkuDto extends PmsSkuStock {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "活动sku关系表id")
    private Long gameSkuId;

    @ApiModelProperty(value = "购物车id")
    private Long cartId;

    @ApiModelProperty(value = "购买数量")
    private Long quantity;

    @ApiModelProperty(value = "商品分类id")
    private Long productCategoryId;

    public Long getGameSkuId() {
        return gameSkuId;
    }

    public void setGameSkuId(Long gameSkuId) {
        this.gameSkuId = gameSkuId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
