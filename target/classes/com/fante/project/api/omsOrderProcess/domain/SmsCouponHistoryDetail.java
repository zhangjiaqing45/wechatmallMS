package com.fante.project.api.omsOrderProcess.domain;

import com.fante.project.business.smsCoupon.domain.SmsCoupon;
import com.fante.project.business.smsCouponHistory.domain.SmsCouponHistory;
import com.fante.project.business.smsCouponProductCateRelation.domain.SmsCouponProductCateRelation;
import com.fante.project.business.smsCouponProductRelation.domain.SmsCouponProductRelation;

import java.util.List;

/**
 * 优惠券领取历史详情封装
 */
public class SmsCouponHistoryDetail extends SmsCouponHistory {
    //相关优惠券信息
    private SmsCoupon coupon;
    //优惠券关联商品
    private List<SmsCouponProductRelation> productRelationList;
    //优惠券关联商品分类
    private List<SmsCouponProductCateRelation> categoryRelationList;

    public SmsCoupon getCoupon() {
        return coupon;
    }

    public void setCoupon(SmsCoupon coupon) {
        this.coupon = coupon;
    }

    public List<SmsCouponProductRelation> getProductRelationList() {
        return productRelationList;
    }

    public void setProductRelationList(List<SmsCouponProductRelation> productRelationList) {
        this.productRelationList = productRelationList;
    }

    public List<SmsCouponProductCateRelation> getCategoryRelationList() {
        return categoryRelationList;
    }

    public void setCategoryRelationList(List<SmsCouponProductCateRelation> categoryRelationList) {
        this.categoryRelationList = categoryRelationList;
    }
}
