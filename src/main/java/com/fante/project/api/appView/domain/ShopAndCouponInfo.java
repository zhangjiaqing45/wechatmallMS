package com.fante.project.api.appView.domain;

import com.fante.framework.aspectj.lang.annotation.Excel;
import com.fante.project.business.smsCoupon.domain.SmsCoupon;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author ftnet
 * @Description ShopAndCouponInfo
 * @CreatTime 2020/6/30
 */
public class ShopAndCouponInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "店铺编号")
    private Long shopId;
    
    @ApiModelProperty(value = "销量")
    private String pSum;
    
    @ApiModelProperty(value = "距离/米")
    private String distance;
    
    @ApiModelProperty(value = "标签")
    private String tag;

    @ApiModelProperty(value = "店铺主图")
    private String pic;

    @ApiModelProperty(value = "店铺名")
    private String shopName;

    @ApiModelProperty(value = "省")
    private String province;

    @ApiModelProperty(value = "市")
    private String city;

    @ApiModelProperty(value = "区/县")
    private String district;

    @ApiModelProperty(value = "详细地址")
    private String address;

    @ApiModelProperty(value = "经度")
    private String longitude;

    @ApiModelProperty(value = "纬度")
    private String latitude;

    @ApiModelProperty(value = "店铺优惠券集合")
    private List<SmsCoupon> couponList;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public List<SmsCoupon> getCouponList() {
        return couponList;
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

    public void setCouponList(List<SmsCoupon> couponList) {
        this.couponList = couponList;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getpSum(){
        return pSum;
    }
    
    public void setpSum(String pSum){
        this.pSum = pSum;
    }
    
    public String getDistance(){
        return distance;
    }
    
    public void setDistance(String distance){
        this.distance = distance;
    }
}
