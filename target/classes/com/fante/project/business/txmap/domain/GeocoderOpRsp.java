package com.fante.project.business.txmap.domain;

import java.io.Serializable;

/**
 * @program: Fante
 * @Date: 2020/2/11 14:53
 * @Author: Mr.Z
 * @Description: 地址解析响应结果
 */
public class GeocoderOpRsp implements Serializable {
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
     * 本次请求的唯一标识
     */
    private String request_id;
    /**
     * 地址解析结果
     */
    private GeocoderOpResult result;

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

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public GeocoderOpResult getResult() {
        return result;
    }

    public void setResult(GeocoderOpResult result) {
        this.result = result;
    }
}


