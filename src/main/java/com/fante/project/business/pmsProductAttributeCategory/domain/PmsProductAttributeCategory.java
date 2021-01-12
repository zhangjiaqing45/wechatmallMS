package com.fante.project.business.pmsProductAttributeCategory.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fante.framework.aspectj.lang.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import com.fante.framework.web.domain.BaseEntity;

/**
 * 产品属性分类对象 pms_product_attribute_category
 * 
 * @author fante
 * @date 2020-03-09
 */
public class PmsProductAttributeCategory extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "店铺id")
    private Long shopId;

    @ApiModelProperty(value = "商品分类id")
    @Excel(name = "商品分类id")
    private Long categoryId;

    @ApiModelProperty(value = "名称")
    @Excel(name = "名称")
    private String name;

    @ApiModelProperty(value = "属性数量")
    @Excel(name = "属性数量")
    private Long attributeCount;

    @ApiModelProperty(value = "参数数量")
    @Excel(name = "参数数量")
    private Long paramCount;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "删除标记")
    private String delFlag;

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
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getCategoryId() {
        return categoryId;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setAttributeCount(Long attributeCount) {
        this.attributeCount = attributeCount;
    }

    public Long getAttributeCount() {
        return attributeCount;
    }
    public void setParamCount(Long paramCount) {
        this.paramCount = paramCount;
    }

    public Long getParamCount() {
        return paramCount;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("shopId", getShopId())
            .append("categoryId", getCategoryId())
            .append("name", getName())
            .append("attributeCount", getAttributeCount())
            .append("paramCount", getParamCount())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
