package com.fante.project.business.pmsIntegralProductCategory.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fante.framework.aspectj.lang.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import com.fante.framework.web.domain.BaseEntity;

/**
 * 积分产品分类对象 pms_integral_product_category
 *
 * @author fante
 * @date 2021-01-12
 */
public class PmsIntegralProductCategory extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "店铺id")
    @Excel(name = "店铺id")
    private Long shopId;

    @ApiModelProperty(value = "名称")
    @Excel(name = "名称")
    private String name;

    @ApiModelProperty(value = "分类级别：0->1级；1->2级")
    @Excel(name = "分类级别：0->1级；1->2级")
    private String level;

    @ApiModelProperty(value = "图标")
    @Excel(name = "图标")
    private String icon;

    @ApiModelProperty(value = "商品数量")
    @Excel(name = "商品数量")
    private Long productCount;

    @ApiModelProperty(value = "(不用)是否显示在导航栏：0->不显示；1->显示")
    @Excel(name = "(不用)是否显示在导航栏：0->不显示；1->显示")
    private String navStatus;

    @ApiModelProperty(value = "显示状态：0->不显示；1->显示")
    @Excel(name = "显示状态：0->不显示；1->显示")
    private String showStatus;

    @ApiModelProperty(value = "描述")
    @Excel(name = "描述")
    private String description;

    @ApiModelProperty(value = "排序")
    @Excel(name = "排序")
    private Long sort;

    @ApiModelProperty(value = "删除标记")
    private String delFlag;

    @ApiModelProperty(value = "分类id")
    @Excel(name = "分类id")
    private Long categoryId;

    @ApiModelProperty(value = "分类名称")
    @Excel(name = "分类名称")
    private String categoryName;

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
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setLevel(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }
    public void setProductCount(Long productCount) {
        this.productCount = productCount;
    }

    public Long getProductCount() {
        return productCount;
    }
    public void setNavStatus(String navStatus) {
        this.navStatus = navStatus;
    }

    public String getNavStatus() {
        return navStatus;
    }
    public void setShowStatus(String showStatus) {
        this.showStatus = showStatus;
    }

    public String getShowStatus() {
        return showStatus;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
    public void setSort(Long sort) {
        this.sort = sort;
    }

    public Long getSort() {
        return sort;
    }
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getCategoryId() {
        return categoryId;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("shopId", getShopId())
                .append("name", getName())
                .append("level", getLevel())
                .append("icon", getIcon())
                .append("productCount", getProductCount())
                .append("navStatus", getNavStatus())
                .append("showStatus", getShowStatus())
                .append("description", getDescription())
                .append("sort", getSort())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("remark", getRemark())
                .append("delFlag", getDelFlag())
                .append("categoryId", getCategoryId())
                .append("categoryName", getCategoryName())
                .toString();
    }
}
