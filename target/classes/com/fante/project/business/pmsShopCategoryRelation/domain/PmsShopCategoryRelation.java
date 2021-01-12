package com.fante.project.business.pmsShopCategoryRelation.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fante.framework.aspectj.lang.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import com.fante.framework.web.domain.BaseEntity;

/**
 * 店铺从平台选择的分类对象 pms_shop_category_relation
 *
 * @author fante
 * @date 2020-03-11
 */
public class PmsShopCategoryRelation extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "店铺id")
    @Excel(name = "店铺id")
    private Long shopId;

    @ApiModelProperty(value = "商品分类id")
    @Excel(name = "商品分类id")
    private Long productCategoryId;

    @ApiModelProperty(value = "商品数量")
    @Excel(name = "商品数量")
    private Long shopProductCount;

    @ApiModelProperty(value = "状态")
    @Excel(name = "状态")
    private String shopStatus;

    @ApiModelProperty(value = "删除标记")
    private String delFlag;

    @ApiModelProperty(value = "排序")
    @Excel(name = "排序")
    private Long sort;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getShopId() {
        return shopId;
    }
    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public Long getProductCategoryId() {
        return productCategoryId;
    }
    public void setShopProductCount(Long shopProductCount) {
        this.shopProductCount = shopProductCount;
    }

    public Long getShopProductCount() {
        return shopProductCount;
    }
    public void setShopStatus(String shopStatus) {
        this.shopStatus = shopStatus;
    }

    public String getShopStatus() {
        return shopStatus;
    }
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }
    public void setSort(Long sort) {
        this.sort = sort;
    }

    public Long getSort() {
        return sort;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("shopId", getShopId())
                .append("productCategoryId", getProductCategoryId())
                .append("shopProductCount", getShopProductCount())
                .append("shopStatus", getShopStatus())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .append("delFlag", getDelFlag())
                .append("sort", getSort())
                .toString();
    }
}
