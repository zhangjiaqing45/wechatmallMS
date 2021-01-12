package com.fante.project.business.smsCouponProductRelation.domain;

import java.io.Serializable;

/**
 * 优惠券和产品的关系对象DTO
 * 
 * @author fante
 * @date 2020-03-20
 */
public class SmsCouponProductRelationDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long couponId;

    private Long productId;

    private String productName;

    private String productSn;

    private String publishStatus;

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
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }
    public void setProductSn(String productSn) {
        this.productSn = productSn;
    }

    public String getProductSn() {
        return productSn;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(String publishStatus) {
        this.publishStatus = publishStatus;
    }
}
