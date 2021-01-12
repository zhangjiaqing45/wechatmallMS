package com.fante.project.business.txmap.service;

import com.fante.common.utils.StringUtils;
import com.fante.framework.config.TxMapConfig;
import com.fante.project.business.txmap.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

/**
 * @program: Fante
 * @Date: 2020/2/11 14:44
 * @Author: Mr.Z
 * @Description: 腾讯地图服务
 */
@Service
public class TxMapService {

    private static Logger log = LoggerFactory.getLogger(TxMapService.class);

    /**
     * 地址解析正常
     */
    private static final int GEOCODER_SUCCESS = 0;

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    TxMapConfig txMapConfig;

    /**
     * 地址解析，由地址描述到所述位置坐标的转换
     * @param address
     */
    private GeocoderResult geocoder(String address) {
        log.info("地址解析: {}", address);
        String url = txMapConfig.getGeocoderUrl(address);
        GeocoderRsp rsp = restTemplate.getForObject(url, GeocoderRsp.class);
        log.info("状态码: {}, 状态说明: {}", rsp.getStatus(), rsp.getMessage());
        return Objects.equals(rsp.getStatus(), GEOCODER_SUCCESS) ? rsp.getResult() : null;
    }

    /**
     * 地址转坐标
     * @param address
     * @return
     */
    public GeocoderResultLocation addrToCoord(String address) {
        if (StringUtils.isBlank(address)) {
            return null;
        }
        GeocoderResult result = geocoder(address);
        if (ObjectUtils.isEmpty(result)) {
            log.info("地址转坐标异常");
            return null;
        }
        if (ObjectUtils.isEmpty(result.getLocation())) {
            log.info("未解析到的坐标");
            return null;
        }
        return result.getLocation();
    }

    /**
     * 坐标转地址
     * @param lat
     * @param lng
     */
    public GeocoderOpAddressComponent coordToAddr(String lat, String lng) {
        String location = StringUtils.format("{},{}", lat, lng);
        GeocoderOpResult result = geocoderOp(location);
        if (ObjectUtils.isEmpty(result)) {
            log.info("坐标转地址异常");
            return null;
        }
        if (ObjectUtils.isEmpty(result.getAddress_component())) {
            log.info("未解析到的位置");
            return null;
        }
        return result.getAddress_component();
    }

    public GeocoderOpResult geocoderOp(String location) {
        log.info("逆地址解析: {}", location);
        String url = txMapConfig.getGeocoderOpUrl(location);
        GeocoderOpRsp rsp = restTemplate.getForObject(url, GeocoderOpRsp.class);
        log.info("状态码: {}, 状态说明: {}", rsp.getStatus(), rsp.getMessage());
        return Objects.equals(rsp.getStatus(), GEOCODER_SUCCESS) ? rsp.getResult() : null;
    }
}
