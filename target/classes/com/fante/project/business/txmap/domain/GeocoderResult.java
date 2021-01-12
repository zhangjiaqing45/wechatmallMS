package com.fante.project.business.txmap.domain;

import java.io.Serializable;

/**
 * @program: Fante
 * @Date:
 * @Author: Mr.Z
 * @Description: 地址解析结果
 */
public class GeocoderResult implements Serializable {
    private static final long serialVersionUID = 1L;

    private String title;
    /**
     * 解析到的坐标
     */
    private GeocoderResultLocation location;
    /**
     * 行政区划信息
     */
    private Object ad_info;
    /**
     * 解析后的地址部件
     */
    private Object address_components;
    /**
     * 可信度参考：值范围 1 <低可信> - 10 <高可信>, 该值>=7时，解析结果较为准确
     */
    private String reliability;
    /**
     * 解析精度级别，分为11个级别，一般>=9即可采用
     */
    private String level;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public GeocoderResultLocation getLocation() {
        return location;
    }

    public void setLocation(GeocoderResultLocation location) {
        this.location = location;
    }

    public Object getAd_info() {
        return ad_info;
    }

    public void setAd_info(Object ad_info) {
        this.ad_info = ad_info;
    }

    public Object getAddress_components() {
        return address_components;
    }

    public void setAddress_components(Object address_components) {
        this.address_components = address_components;
    }

    public String getReliability() {
        return reliability;
    }

    public void setReliability(String reliability) {
        this.reliability = reliability;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
