package com.fante.project.business.pmsProductCategory.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fante.framework.aspectj.lang.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import com.fante.framework.web.domain.BaseEntity;

/**
 * 产品分类对象 pms_product_category
 * 
 * @author fante
 * @date 2020-03-09
 */
public class PmsProductCategory extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "店铺id")
    private Long shopId;

    @ApiModelProperty(value = "名称")
    @Excel(name = "名称")
    private String name;

    @ApiModelProperty(value = "目录等级")
    private Long level;

    @ApiModelProperty(value = "图标")
    @Excel(name = "图标")
    private String icon;

    @ApiModelProperty(value = "轮播图")
    @Excel(name = "轮播图")
    private String albumPics;

    @ApiModelProperty(value = "商品数量")
    @Excel(name = "商品数量")
    private Long productCount;

    @ApiModelProperty(value = "显示")
    @Excel(name = "显示")
    private Integer navStatus;

    @ApiModelProperty(value = "显示")
    @Excel(name = "显示")
    private Integer showStatus;

    @ApiModelProperty(value = "删除标记")
    private String delFlag;

    @ApiModelProperty(value = "描述")
    @Excel(name = "描述")
    private String description;

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
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setLevel(Long level) {
        this.level = level;
    }

    public Long getLevel() {
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
    public void setNavStatus(Integer navStatus) {
        this.navStatus = navStatus;
    }

    public Integer getNavStatus() {
        return navStatus;
    }
    public void setShowStatus(Integer showStatus) {
        this.showStatus = showStatus;
    }

    public Integer getShowStatus() {
        return showStatus;
    }
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlag() {
        return delFlag;
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
    
    public String getAlbumPics(){
        return albumPics;
    }
    
    public void setAlbumPics(String albumPics){
        this.albumPics = albumPics;
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
            .append("delFlag", getDelFlag())
            .append("description", getDescription())
            .append("sort", getSort())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
