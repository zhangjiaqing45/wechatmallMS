package com.fante.project.business.txmap.domain;

/**
 * @program: Fante
 * @Date: 2020/5/2 16:56
 * @Author: Mr.Z
 * @Description:
 */
public class GeocoderOpResult {

    /**
     * 地址描述
     */
    private String address;
    /**
     * 位置描述
     */
    private Object formatted_addresses;
    /**
     * 地址部件
     */
    private GeocoderOpAddressComponent address_component;
    /**
     * 行政区划信息
     */
    private Object ad_info;
    /**
     * 坐标相对位置参考
     */
    private Object address_reference;
    /**
     * 查询的周边poi的总数
     */
    private int poi_count;
    /**
     * POI数组
     */
    private Object[] pois;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Object getFormatted_addresses() {
        return formatted_addresses;
    }

    public void setFormatted_addresses(Object formatted_addresses) {
        this.formatted_addresses = formatted_addresses;
    }

    public GeocoderOpAddressComponent getAddress_component() {
        return address_component;
    }

    public void setAddress_component(GeocoderOpAddressComponent address_component) {
        this.address_component = address_component;
    }

    public Object getAd_info() {
        return ad_info;
    }

    public void setAd_info(Object ad_info) {
        this.ad_info = ad_info;
    }

    public Object getAddress_reference() {
        return address_reference;
    }

    public void setAddress_reference(Object address_reference) {
        this.address_reference = address_reference;
    }

    public int getPoi_count() {
        return poi_count;
    }

    public void setPoi_count(int poi_count) {
        this.poi_count = poi_count;
    }

    public Object[] getPois() {
        return pois;
    }

    public void setPois(Object[] pois) {
        this.pois = pois;
    }
}
