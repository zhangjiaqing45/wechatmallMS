package com.fante.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.fante.common.utils.http.HttpUtils;
import com.fante.framework.config.FanteConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 获取地址类
 *
 * @author fante
 */
public class AddressUtils {
   /* public static final String IP_URL = "http://ip.taobao.com/service/getIpInfo.php" ;
    private static final Logger log = LoggerFactory.getLogger(AddressUtils.class);

    public static String getRealAddressByIP(String ip) {
        String address = "XX XX" ;
        // 内网不查询
        if (IpUtils.internalIp(ip)) {
            return "内网IP" ;
        }
        if (FanteConfig.isAddressEnabled()) {
            String rspStr = HttpUtils.sendPost(IP_URL, "ip=" + ip);
            if (StringUtils.isEmpty(rspStr)) {
                log.error("获取地理位置异常 {}" , ip);
                return address;
            }
            JSONObject obj = JSONObject.parseObject(rspStr);
            JSONObject data = obj.getObject("data" , JSONObject.class);
            String region = data.getString("region");
            String city = data.getString("city");
            address = region + " " + city;
        }
        return address;
    }*/
}
