package com.fante.project.business.txmap.domain;

import java.io.Serializable;

/**
 * @program: Fante
 * @Date: 2020/5/2 17:20
 * @Author: Mr.Z
 * @Description:
 */
public class GeocoderOpAddressComponent implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 国家
     */
    private String nation;
    /**
     * 省
     */
    private String province;
    /**
     * 市
     */
    private String city;
    /**
     * 区，可能为空字串
     */
    private String district;
    /**
     * 街道，可能为空字串
     */
    private String street;
    /**
     * 门牌，可能为空字串
     */
    private String street_number;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet_number() {
        return street_number;
    }

    public void setStreet_number(String street_number) {
        this.street_number = street_number;
    }
}
