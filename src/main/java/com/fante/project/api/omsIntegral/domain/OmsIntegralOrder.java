package com.fante.project.api.omsIntegral.domain;

import com.fante.project.business.omsOrder.domain.OmsOrder;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @author ftnet
 * @Description OmsIntegralOrder
 * @CreatTime 2020/4/22
 */
public class OmsIntegralOrder extends OmsOrder {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "购买数量")
    private Long quantity;

    @ApiModelProperty(value = "单价")
    private BigDecimal singlePrice;

    @ApiModelProperty(value = "市场价")
    private BigDecimal singleOriginalPrice;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "品牌")
    private String productBrand;

    @ApiModelProperty(value = "商品图片")
    private String productPic;

    @ApiModelProperty(value = "商品id")
    private Long productId;

    @ApiModelProperty(value = "商品货号")
    private String productSn;

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSinglePrice() {
        return singlePrice;
    }

    public void setSinglePrice(BigDecimal singlePrice) {
        this.singlePrice = singlePrice;
    }

    public BigDecimal getSingleOriginalPrice() {
        return singleOriginalPrice;
    }

    public void setSingleOriginalPrice(BigDecimal singleOriginalPrice) {
        this.singleOriginalPrice = singleOriginalPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getProductPic() {
        return productPic;
    }

    public void setProductPic(String productPic) {
        this.productPic = productPic;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductSn() {
        return productSn;
    }

    public void setProductSn(String productSn) {
        this.productSn = productSn;
    }
}
