package com.fante.project.business.txmap.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @program: Fante
 * @Date:
 * @Author: Mr.Z
 * @Description: 解析到的坐标
 */
public class GeocoderResultLocation implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 经度
     */
    private BigDecimal lng;

    /**
     * 纬度
     */
    private BigDecimal lat;

    public BigDecimal getLng() {
        return lng;
    }

    public void setLng(BigDecimal lng) {
        this.lng = lng;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }
}
