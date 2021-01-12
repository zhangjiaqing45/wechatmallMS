package com.fante.project.api.omsOrderProcess.domain;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author ftnet
 * @Description ValidateStockDTO
 * @CreatTime 2020/4/15
 */
public class ValidateStockDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "商品规格")
    private String spData;

    @ApiModelProperty(value = "商品规格id")
    private Long skuId;

    @ApiModelProperty(value = "商品购买数量")
    private Long quantity;

    public ValidateStockDTO(Long skuId, Long quantity) {
        this.skuId = skuId;
        this.quantity = quantity;
    }

    public ValidateStockDTO() {
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSpData() {
        return spData;
    }

    public void setSpData(String spData) {
        this.spData = spData;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
