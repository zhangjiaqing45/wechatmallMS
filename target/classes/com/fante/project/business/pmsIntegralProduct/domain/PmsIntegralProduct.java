package com.fante.project.business.pmsIntegralProduct.domain;

import com.fante.framework.aspectj.lang.annotation.Excel;
import com.fante.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 积分商品对象 pms_integral_product
 *
 * @author fante
 * @date 2020-03-19
 */
public class PmsIntegralProduct extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "商品货号")
    @Excel(name = "商品货号")
    private String productSn;

    @ApiModelProperty(value = "店铺id")
    //@Excel(name = "店铺id")
    private Long shopId;

    @ApiModelProperty(value = "品牌id")
    //@Excel(name = "品牌id")
    private Long brandId;

    @ApiModelProperty(value = "商品分类id")
    //@Excel(name = "商品分类id")
    private Long productCategoryId;

    @ApiModelProperty(value = "运费")
    @Excel(name = "运费")
    private Long feightFee;

    @ApiModelProperty(value = "属性分类id")
    //@Excel(name = "属性分类id")
    private Long productAttributeCategoryId;

    @ApiModelProperty(value = "商品分类")
    @Excel(name = "商品分类")
    private String productCategoryName;

    @ApiModelProperty(value = "品牌名称")
    //@Excel(name = "品牌名称")
    private String brandName;

    @ApiModelProperty(value = "商品名称")
    @Excel(name = "商品名称")
    private String name;

    @ApiModelProperty(value = "删除标记")
    private String delFlag;

    @ApiModelProperty(value = "上架状态")
    @Excel(name = "上架状态", readConverterExp = "0=下架,1=上架")
    private String publishStatus;

    @ApiModelProperty(value = "审核状态")
    //@Excel(name = "审核状态")
    private String verifyStatus;

    @ApiModelProperty(value = "兑换积分")
    @Excel(name = "兑换积分")
    private Long price;

    @ApiModelProperty(value = "市场价")
    //@Excel(name = "市场价")
    private Long originalPrice;

    @ApiModelProperty(value = "销量")
    @Excel(name = "销量")
    private Long sale;

    @ApiModelProperty(value = "库存")
    @Excel(name = "库存")
    private Long stock;

    @ApiModelProperty(value = "库存预警值")
    @Excel(name = "库存预警值")
    private Long lowStock;

    @ApiModelProperty(value = "图片")
    //@Excel(name = "图片")
    private String pic;

    @ApiModelProperty(value = "画册图片")
    //@Excel(name = "画册图片")
    private String albumPics;

    @ApiModelProperty(value = "商品销售参数")
    //@Excel(name = "商品销售参数")
    private String paramData;

    @ApiModelProperty(value = "关键字")
    //@Excel(name = "关键字")
    private String keywords;

    @ApiModelProperty(value = "副标题")
    @Excel(name = "副标题")
    private String subTitle;

    @ApiModelProperty(value = "商品描述")
    private String description;

    @ApiModelProperty(value = "详情页标题")
    private String detailTitle;

    @ApiModelProperty(value = "详情页描述")
    private String detailDesc;

    @ApiModelProperty(value = "产品详情网页内容")
    //@Excel(name = "产品详情网页内容")
    private String detailHtml;

    @ApiModelProperty(value = "排序")
    @Excel(name = "排序")
    private Long sort;

    @ApiModelProperty(value = "审核信息")
    //@Excel(name = "审核信息")
    private String auditMsg;

    @ApiModelProperty(value = "(不用)单位")
    private String unit;

    @ApiModelProperty(value = "(不用)商品重量，默认为克")
    private Double weight;

    @ApiModelProperty(value = "(不用)平台分类id")
    private Long platformCategory;

    @ApiModelProperty(value = "(不用)赠送的成长值")
    private Long giftGrowth;

    @ApiModelProperty(value = "(不用)赠送的积分")
    private Long giftPoint;

    @ApiModelProperty(value = "(不用)限制使用的积分数")
    private Long usePointLimit;

    @ApiModelProperty(value = "免费包邮")
    private String serviceIds;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setProductSn(String productSn) {
        this.productSn = productSn;
    }

    public String getProductSn() {
        return productSn;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public Long getProductCategoryId() {
        return productCategoryId;
    }

    public Long getFeightFee() {
        return feightFee;
    }

    public void setFeightFee(Long feightFee) {
        this.feightFee = feightFee;
    }

    public void setProductAttributeCategoryId(Long productAttributeCategoryId) {
        this.productAttributeCategoryId = productAttributeCategoryId;
    }

    public Long getProductAttributeCategoryId() {
        return productAttributeCategoryId;
    }

    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }

    public String getProductCategoryName() {
        return productCategoryName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setPublishStatus(String publishStatus) {
        this.publishStatus = publishStatus;
    }

    public String getPublishStatus() {
        return publishStatus;
    }

    public void setVerifyStatus(String verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public String getVerifyStatus() {
        return verifyStatus;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Long originalPrice) {
        this.originalPrice = originalPrice;
    }

    public void setSale(Long sale) {
        this.sale = sale;
    }

    public Long getSale() {
        return sale;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public Long getStock() {
        return stock;
    }

    public Long getLowStock() {
        return lowStock;
    }

    public void setLowStock(Long lowStock) {
        this.lowStock = lowStock;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPic() {
        return pic;
    }

    public void setAlbumPics(String albumPics) {
        this.albumPics = albumPics;
    }

    public String getAlbumPics() {
        return albumPics;
    }

    public void setParamData(String paramData) {
        this.paramData = paramData;
    }

    public String getParamData() {
        return paramData;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDetailTitle(String detailTitle) {
        this.detailTitle = detailTitle;
    }

    public String getDetailTitle() {
        return detailTitle;
    }

    public void setDetailDesc(String detailDesc) {
        this.detailDesc = detailDesc;
    }

    public String getDetailDesc() {
        return detailDesc;
    }

    public void setDetailHtml(String detailHtml) {
        this.detailHtml = detailHtml;
    }

    public String getDetailHtml() {
        return detailHtml;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public Long getSort() {
        return sort;
    }

    public void setAuditMsg(String auditMsg) {
        this.auditMsg = auditMsg;
    }

    public String getAuditMsg() {
        return auditMsg;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getWeight() {
        return weight;
    }

    public void setPlatformCategory(Long platformCategory) {
        this.platformCategory = platformCategory;
    }

    public Long getPlatformCategory() {
        return platformCategory;
    }

    public void setGiftGrowth(Long giftGrowth) {
        this.giftGrowth = giftGrowth;
    }

    public Long getGiftGrowth() {
        return giftGrowth;
    }

    public void setGiftPoint(Long giftPoint) {
        this.giftPoint = giftPoint;
    }

    public Long getGiftPoint() {
        return giftPoint;
    }

    public void setUsePointLimit(Long usePointLimit) {
        this.usePointLimit = usePointLimit;
    }

    public Long getUsePointLimit() {
        return usePointLimit;
    }

    public void setServiceIds(String serviceIds) {
        this.serviceIds = serviceIds;
    }

    public String getServiceIds() {
        return serviceIds;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("productSn", getProductSn())
                .append("shopId", getShopId())
                .append("brandId", getBrandId())
                .append("productCategoryId", getProductCategoryId())
                .append("feightFee", getFeightFee())
                .append("productAttributeCategoryId", getProductAttributeCategoryId())
                .append("productCategoryName", getProductCategoryName())
                .append("brandName", getBrandName())
                .append("name", getName())
                .append("delFlag", getDelFlag())
                .append("publishStatus", getPublishStatus())
                .append("verifyStatus", getVerifyStatus())
                .append("price", getPrice())
                .append("originalPrice", getOriginalPrice())
                .append("sale", getSale())
                .append("stock", getStock())
                .append("lowStock", getLowStock())
                .append("pic", getPic())
                .append("albumPics", getAlbumPics())
                .append("paramData", getParamData())
                .append("keywords", getKeywords())
                .append("subTitle", getSubTitle())
                .append("description", getDescription())
                .append("detailTitle", getDetailTitle())
                .append("detailDesc", getDetailDesc())
                .append("detailHtml", getDetailHtml())
                .append("sort", getSort())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("auditMsg", getAuditMsg())
                .append("remark", getRemark())
                .append("unit", getUnit())
                .append("weight", getWeight())
                .append("platformCategory", getPlatformCategory())
                .append("giftGrowth", getGiftGrowth())
                .append("giftPoint", getGiftPoint())
                .append("usePointLimit", getUsePointLimit())
                .append("serviceIds", getServiceIds())
                .toString();
    }
}