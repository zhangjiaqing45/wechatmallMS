package com.fante.project.business.smsCouponProductCateRelation.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fante.framework.aspectj.lang.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import com.fante.framework.web.domain.BaseEntity;

/**
 * 优惠券和产品分类关系对象 sms_coupon_product_cate_relation
 * 
 * @author fante
 * @date 2020-03-20
 */
public class SmsCouponProductCateRelation extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "$column.columnComment")
    private Long id;

    @ApiModelProperty(value = "$column.columnComment")
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long couponId;

    @ApiModelProperty(value = "$column.columnComment")
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long productCategoryId;

    @ApiModelProperty(value = "产品分类名称")
    @Excel(name = "产品分类名称")
    private String productCategoryName;


    public SmsCouponProductCateRelation() {
    }

    public SmsCouponProductCateRelation(Long couponId, Long productCategoryId) {
        this.couponId = couponId;
        this.productCategoryId = productCategoryId;
    }

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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("couponId", getCouponId())
            .append("productCategoryId", getProductCategoryId())
            .append("productCategoryName", getProductCategoryName())
            .toString();
    }
}
