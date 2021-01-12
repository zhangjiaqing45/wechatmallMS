package com.fante.project.business.pmsProductLog.domain;

import com.fante.common.business.enums.ProductAttributeCategoryConst;
import com.fante.project.business.pmsProduct.domain.PmsProduct;
import com.fante.project.business.pmsSkuStock.domain.PmsSkuStock;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 商品信息对象 pms_product
 * 
 * @author fante
 * @date 2020-03-21
 */
public class PmsProductLogParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "sku信息")
    private PmsProduct product;

    @ApiModelProperty(value = "sku信息")
    private PmsSkuStock sku;

    @ApiModelProperty(value = "动作")
    private ProductAttributeCategoryConst.ActivityEnum action;

    @ApiModelProperty(value = "旧值")
    private String oldValue;

    @ApiModelProperty(value = "新值")
    private String newValue;

    public PmsProduct getProduct() {
        return product;
    }

    public void setProduct(PmsProduct product) {
        this.product = product;
    }

    public PmsSkuStock getSku() {
        return sku;
    }

    public void setSku(PmsSkuStock sku) {
        this.sku = sku;
    }

    public ProductAttributeCategoryConst.ActivityEnum getAction() {
        return action;
    }

    public void setAction(ProductAttributeCategoryConst.ActivityEnum action) {
        this.action = action;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }
}
