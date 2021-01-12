package com.fante.project.api.appView.domain;

import com.fante.project.business.pmsProduct.domain.PmsProduct;
import com.fante.project.business.pmsSkuStock.domain.PmsSkuStock;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author ftnet
 * @Description PmsProductParam
 * @CreatTime 2020/3/14
 */
public class PmsProductDetailPageInfo extends PmsProduct implements IDetailPageInfo{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "店铺名称")
    private String shopName;

    @ApiModelProperty(value = "店铺客服")
    private String servicePhone;

    @ApiModelProperty(value = "sku集合")
    private List<PmsSkuStock> skuList;

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

    @ApiModelProperty(value = "核销数量")
    private String smscount;


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
        return  getId();
    }

    public int getShopProductCount() {
        return shopProductCount;
    }

    public void setShopProductCount(int shopProductCount) {
        this.shopProductCount = shopProductCount;
    }

    public int getShopStarCount() {
        return shopStarCount;
    }

    public void setShopStarCount(int shopStarCount) {
        this.shopStarCount = shopStarCount;
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

    public String getServicePhone() {
        return servicePhone;
    }

    public void setServicePhone(String servicePhone) {
        this.servicePhone = servicePhone;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
    public String getShopName() {
        return shopName;
    }
    public List<PmsSkuStock> getSkuList() {
        return skuList;
    }
    public void setSkuList(List<PmsSkuStock> skuList) {
        this.skuList = skuList;
    }


    public String getSmscount() {
        return smscount;
    }

    public void setSmscount(String smscount) {
        this.smscount = smscount;
    }
}
