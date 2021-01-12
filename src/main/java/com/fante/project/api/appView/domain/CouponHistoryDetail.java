package com.fante.project.api.appView.domain;

import java.io.Serializable;

/**
 * @author ftnet
 * @Description CouponHistoryDetail
 * @CreatTime 2020/6/19
 */
public class CouponHistoryDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long couponId;
    private Long shopId;
    private Long id;
    private Long memberId;

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
}
