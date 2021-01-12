package com.fante.framework.config;

import com.fante.common.utils.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @program: Fante
 * @Date: 2020/1/15 11:16
 * @Author: Mr.Z
 * @Description:
 */
@Component
@ConfigurationProperties(prefix = "qqmap")
public class TxMapConfig {

    /**
     * 密匙
     */
    private String key;
    /**
     * 地图选点组件
     */
    private String locpicker;
    /**
     * 地址解析（地址转坐标）
     */
    private String geocoder;
    /**
     * 逆地址解析（坐标位置描述）
     */
    private String geocoderOp;
    /**
     * API服务
     */
    private String api;

    /**
     * API服务URL
     * @return
     */
    public String getApiUrl() {
        return StringUtils.format(getApi(), getKey());
    }

    /**
     * 地址解析URL
     * @param address 地址（注：地址中请包含城市名称，否则会影响解析效果）
     * @return
     */
    public String getGeocoderUrl(String address) {
        return StringUtils.format(getGeocoder(), getKey(), address);
    }

    /**
     * 逆地址解析URL
     * @param location
     * @return
     */
    public String getGeocoderOpUrl(String location) {
        return StringUtils.format(getGeocoderOp(), getKey(), location);
    }

    /**
     * 地图选点URL
     * @return
     */
    public String getLocpickerUrl() {
        return getLocpickerUrl("");
    }

    /**
     * 地图选点URL
     * @param coord 在指定位置附近进行位置选择,coord用英文逗号分隔，纬度在前，经度在后,coord=40.022964,116.319723
     * @return
     */
    public String getLocpickerUrl(String coord) {
        return StringUtils.format(getLocpicker(), getKey(), coord);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLocpicker() {
        return locpicker;
    }

    public void setLocpicker(String locpicker) {
        this.locpicker = locpicker;
    }

    public String getGeocoder() {
        return geocoder;
    }

    public void setGeocoder(String geocoder) {
        this.geocoder = geocoder;
    }

    public String getGeocoderOp() {
        return geocoderOp;
    }

    public void setGeocoderOp(String geocoderOp) {
        this.geocoderOp = geocoderOp;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }
}
