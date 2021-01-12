package com.fante.project.business.smsHomeRecommendProduct.domain;

import com.fante.framework.aspectj.lang.annotation.Excel;
import com.fante.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * @program: Fante
 * @Date: 2020/3/23 16:12
 * @Author: Mr.Z
 * @Description:
 */
public class SmsHomeRecommendProductDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty(value = "商品主键ID")
    @Excel(name = "商品主键ID")
    private Long productId;

    @ApiModelProperty(value = "货号")
    @Excel(name = "货号")
    private String productSn;

    @ApiModelProperty(value = "店铺id")
    private Long shopId;

    @ApiModelProperty(value = "商品分类")
    @Excel(name = "商品分类")
    private String productCategoryName;

    @ApiModelProperty(value = "品牌")
    @Excel(name = "品牌")
    private String brandName;

    @ApiModelProperty(value = "商品名称")
    @Excel(name = "商品名称")
    private String productName;

    @ApiModelProperty(value = "上架状态")
    @Excel(name = "上架状态")
    private String publishStatus;

    @ApiModelProperty(value = "图片")
    @Excel(name = "图片")
    private String pic;

    @ApiModelProperty(value = "价格")
    @Excel(name = "价格")
    private BigDecimal price;

    @ApiModelProperty(value = "市场价")
    @Excel(name = "市场价")
    private BigDecimal originalPrice;

    @ApiModelProperty(value = "排序")
    @Excel(name = "排序")
    private Long sort;

    @ApiModelProperty(value = "状态")
    @Excel(name = "状态", readConverterExp = "0=启用,1=停用")
    private String status;

    @ApiModelProperty(value = "推荐类型")
    @Excel(name = "推荐类型", readConverterExp = "01=热门商品,02=精品推荐,03=新品推荐")
    private String type;

    @ApiModelProperty(value = "销量")
    @Excel(name = "销量")
    private Long sale;

    public SmsHomeRecommendProductDTO() {
    }

    public SmsHomeRecommendProductDTO(Long productId, String productSn, Long shopId, String productCategoryName, String brandName, String productName, String publishStatus, String pic, BigDecimal price, BigDecimal originalPrice) {
        this.productId = productId;
        this.productSn = productSn;
        this.shopId = shopId;
        this.productCategoryName = productCategoryName;
        this.brandName = brandName;
        this.productName = productName;
        this.publishStatus = publishStatus;
        this.pic = pic;
        this.price = price;
        this.originalPrice = originalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductSn() {
        return productSn;
    }

    public void setProductSn(String productSn) {
        this.productSn = productSn;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getProductCategoryName() {
        return productCategoryName;
    }

    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(String publishStatus) {
        this.publishStatus = publishStatus;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Long getSale() {
        return sale;
    }

    public void setSale(Long sale) {
        this.sale = sale;
    }
}
