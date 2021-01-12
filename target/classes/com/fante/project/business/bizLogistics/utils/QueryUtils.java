package com.fante.project.business.bizLogistics.utils;

import com.fante.framework.config.AliyunWlConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @ClassName queryUtils
 * @Description TOOD
 * @Author LiuQingyu
 * @Date 2020-02-06 16:10
 * @Version 1.0
 **/
@Component
public class QueryUtils {

    private static Logger log = LoggerFactory.getLogger(QueryUtils.class);

    @Autowired
    AliyunWlConfig aliyunWlConfig;

    /**
     * @Author LiuQingyu
     * @Description 查询物流
     * @Date 16:11 2020-02-06
     * @Param [number物流单号
     * , type公司名称代码（选填）]
     * @return java.lang.String
     **/
    public String query(String number,String type) throws IOException {
        //请求地址  支持http 和 https 及 WEBSOCKET
        String host = aliyunWlConfig.getHost();
        //后缀
        String path = aliyunWlConfig.getPath();
        //AppCode  你自己的AppCode 在买家中心查看
        String appcode = aliyunWlConfig.getAppCode();
        //拼接请求链接
        String urlSend = host + path + "?no=" + number + "&type=" + type;
        URL url = new URL(urlSend);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        //格式Authorization:APPCODE (中间是英文空格)
        httpURLConnection.setRequestProperty("Authorization", "APPCODE " + appcode);
        int httpCode = httpURLConnection.getResponseCode();
        String json = read(httpURLConnection.getInputStream());
        log.info("/* 获取服务器响应状态码 200 正常；400 权限错误 ； 403 次数用完； */ ");
        log.info("响应状态码: {}", httpCode);
        log.info("获取返回的json: {}", json);
        return json;
    }

    /**
     * @Author LiuQingyu
     * @Description 读取返回结果
     * @Date 16:23 2020-02-06
     * @Param [is]
     * @return java.lang.String
     **/
    private String read(InputStream is) throws IOException {
        StringBuffer sb = new StringBuffer();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = null;
        while ((line = br.readLine()) != null) {
            line = new String(line.getBytes(), "utf-8");
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }
}
