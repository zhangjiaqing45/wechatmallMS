package com.fante.project.business.pmsBrand.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fante.framework.aspectj.lang.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import com.fante.framework.web.domain.BaseEntity;

/**
 * 品牌对象 pms_brand
 * 
 * @author fante
 * @date 2020-03-09
 */
public class PmsBrand extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "店铺id")
    private Long shopId;

    @ApiModelProperty(value = "名称")
    @Excel(name = "名称")
    private String name;

    @ApiModelProperty(value = "首字母")
    @Excel(name = "首字母")
    private String firstLetter;

    @ApiModelProperty(value = "是")
    private Integer factoryStatus;

    @ApiModelProperty(value = "是否显示")
    @Excel(name = "是否显示")
    private Integer showStatus;

    @ApiModelProperty(value = "产品数量")
    @Excel(name = "产品数量")
    private Long productCount;

    @ApiModelProperty(value = "品牌logo")
    @Excel(name = "品牌logo")
    private String logo;

    @ApiModelProperty(value = "专区大图")
    private String bigPic;

    @ApiModelProperty(value = "品牌故事")
    @Excel(name = "品牌故事")
    private String brandStory;

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
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }

    public String getFirstLetter() {
        return firstLetter;
    }
    public void setFactoryStatus(Integer factoryStatus) {
        this.factoryStatus = factoryStatus;
    }

    public Integer getFactoryStatus() {
        return factoryStatus;
    }
    public void setShowStatus(Integer showStatus) {
        this.showStatus = showStatus;
    }

    public Integer getShowStatus() {
        return showStatus;
    }
    public void setProductCount(Long productCount) {
        this.productCount = productCount;
    }

    public Long getProductCount() {
        return productCount;
    }
    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getLogo() {
        return logo;
    }
    public void setBigPic(String bigPic) {
        this.bigPic = bigPic;
    }

    public String getBigPic() {
        return bigPic;
    }
    public void setBrandStory(String brandStory) {
        this.brandStory = brandStory;
    }

    public String getBrandStory() {
        return brandStory;
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
            .append("name", getName())
            .append("firstLetter", getFirstLetter())
            .append("factoryStatus", getFactoryStatus())
            .append("showStatus", getShowStatus())
            .append("productCount", getProductCount())
            .append("logo", getLogo())
            .append("bigPic", getBigPic())
            .append("brandStory", getBrandStory())
            .append("delFlag", getDelFlag())
            .append("sort", getSort())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
