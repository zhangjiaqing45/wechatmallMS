package com.fante.project.business.smsCoupon.domain;

import com.fante.project.business.smsCouponProductCateRelation.domain.SmsCouponProductCateRelationDTO;
import com.fante.project.business.smsCouponProductRelation.domain.SmsCouponProductRelationDTO;

import java.util.List;

/**
 * @program: Fante
 * @Date: 2020/3/26 18:36
 * @Author: Mr.Z
 * @Description: 优惠券DTO，包含指定分类、指定商品信息
 */
public class SmsCouponDTO extends SmsCoupon {

    private static final long serialVersionUID = 1L;

    private List<SmsCouponProductCateRelationDTO> cateRelations;

    private List<SmsCouponProductRelationDTO> productRelations;

    public List<SmsCouponProductCateRelationDTO> getCateRelations() {
        return cateRelations;
    }

    public void setCateRelations(List<SmsCouponProductCateRelationDTO> cateRelations) {
        this.cateRelations = cateRelations;
    }

    public List<SmsCouponProductRelationDTO> getProductRelations() {
        return productRelations;
    }

    public void setProductRelations(List<SmsCouponProductRelationDTO> productRelations) {
        this.productRelations = productRelations;
    }
}
