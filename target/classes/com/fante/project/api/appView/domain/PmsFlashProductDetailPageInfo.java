package com.fante.project.api.appView.domain;

import com.fante.project.business.smsFlashPromotionProduct.domain.SmsFlashPromotionProduct;
import com.fante.project.business.smsFlashPromotionSku.domain.SmsFlashPromotionSku;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author ftnet
 * @Description 秒杀商品详情页信息
 * @CreatTime 2020/3/14
 */
public class PmsFlashProductDetailPageInfo extends SmsFlashPromotionProduct implements IDetailPageInfo{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "sku集合")
    private List<SmsFlashPromotionSku> skuList;

    @ApiModelProperty(value = "开始时间")
    private String startTime;

    @ApiModelProperty(value = "市场价")
    private BigDecimal originalPrice;

    @ApiModelProperty(value = "结束时间")
    private String endTime;

    @ApiModelProperty(value = "店铺id")
    private Long shopId;

    @ApiModelProperty(value = "店铺名称")
    private String shopName;

    @ApiModelProperty(value = "副标题")
    private String subTitle;

    @ApiModelProperty(value = "商品图册")
    private String albumPics;

    @ApiModelProperty(value = "商品描述")
    private String description;

    @ApiModelProperty(value = "商品详情标题")
    private String detailTitle;

    @ApiModelProperty(value = "商品详情描述")
    private String detailDesc;

    @ApiModelProperty(value = "商品详情html")
    private String detailHtml;

    @ApiModelProperty(value = "商品参数")
    private String paramData;

    @ApiModelProperty(value = "经度")
    private String longitude;

    @ApiModelProperty(value = "纬度")
    private String latitude;

    @ApiModelProperty(value = "店铺关注数量")
    private int shopStarCount;

    @ApiModelProperty(value = "店铺商品数量")
    private int shopProductCount;

    @ApiModelProperty(value = "是否关注该店铺")
    private boolean shopStar;

    @ApiModelProperty(value = "是否关注该店铺")
    private boolean productStar;

    @Override
    public void setPageInfoData(int shopStarCount,int shopProductCount,boolean shopStar,boolean productStar){
        this.shopStarCount = shopStarCount;
        this.shopProductCount = shopProductCount;
        this.shopStar = shopStar;
        this.productStar = productStar;
    }

    @Override
    public Long getPageInfoShopId() {
        return getShopId();
    }

    @Override
    public Long getPageInfoProductId() {
        return  getProductId();
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetailTitle() {
        return detailTitle;
    }

    public void setDetailTitle(String detailTitle) {
        this.detailTitle = detailTitle;
    }

    public String getDetailDesc() {
        return detailDesc;
    }

    public void setDetailDesc(String detailDesc) {
        this.detailDesc = detailDesc;
    }

    public String getDetailHtml() {
        return detailHtml;
    }

    public void setDetailHtml(String detailHtml) {
        this.detailHtml = detailHtml;
    }

    public String getParamData() {
        return paramData;
    }

    public void setParamData(String paramData) {
        this.paramData = paramData;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getAlbumPics() {
        return albumPics;
    }

    public void setAlbumPics(String albumPics) {
        this.albumPics = albumPics;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public List<SmsFlashPromotionSku> getSkuList() {
        return skuList;
    }

    public void setSkuList(List<SmsFlashPromotionSku> skuList) {
        this.skuList = skuList;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public int getShopStarCount() {
        return shopStarCount;
    }

    public void setShopStarCount(int shopStarCount) {
        this.shopStarCount = shopStarCount;
    }

    public int getShopProductCount() {
        return shopProductCount;
    }

    public void setShopProductCount(int shopProductCount) {
        this.shopProductCount = shopProductCount;
    }

    public boolean isShopStar() {
        return shopStar;
    }

    public void setShopStar(boolean shopStar) {
        this.shopStar = shopStar;
    }

    public boolean isProductStar() {
        return productStar;
    }

    public void setProductStar(boolean productStar) {
        this.productStar = productStar;
    }
}
