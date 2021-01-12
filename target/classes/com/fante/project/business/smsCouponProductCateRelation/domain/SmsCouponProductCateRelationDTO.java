package com.fante.project.business.smsCouponProductCateRelation.domain;

import java.io.Serializable;

/**
 * 优惠券和产品分类关系对象DTO
 * 
 * @author fante
 * @date 2020-03-20
 */
public class SmsCouponProductCateRelationDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long couponId;

    private Long productCategoryId;

    private String productCategoryName;

    private String description;

    private Long shopId;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public Long getCouponId() {
        return couponId;
    }
    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public Long getProductCategoryId() {
        return productCategoryId;
    }
    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }

    public String getProductCategoryName() {
        return productCategoryName;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
