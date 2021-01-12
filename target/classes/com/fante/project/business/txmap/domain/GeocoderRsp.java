package com.fante.project.business.txmap.domain;

import java.io.Serializable;

/**
 * @program: Fante
 * @Date: 2020/2/11 14:53
 * @Author: Mr.Z
 * @Description: 地址解析响应结果
 */
public class GeocoderRsp implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 状态码 <br/>
     * 0为正常 <br/>
     * 310请求参数信息有误 <br/>
     * 311Key格式错误 <br/>
     * 306请求有护持信息请检查字符串 <br/>
     * 110请求来源未被授权 <br/>
     */
    private int status;
    /**
     * 状态说明
     */
    private String message;

    /**
     * 地址解析结果
     */
    private GeocoderResult result;


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public GeocoderResult getResult() {
        return result;
    }

    public void setResult(GeocoderResult result) {
        this.result = result;
    }
}


