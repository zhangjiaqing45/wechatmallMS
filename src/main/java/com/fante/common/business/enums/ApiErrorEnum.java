package com.fante.common.business.enums;

/**
 * 应用模块名称<p>
 * 代码描述<p>
 * Copyright: Copyright (C) 2020 Inc. All rights reserved. <p>
 * Company: 凡特网络技术<p>
 *
 * @author Hunk
 * @date 2020/7/21 18:23
 * @since 1.0
 */
public enum ApiErrorEnum {
    /**
     * 接口访问成功
     */
    API_ERROR_CODE_SUCCESS(200,"接口访问成功"),

    /**
     * 找不到资源
     */
    API_ERROR_CODE_RESOURCE_NOT_FOUND(404,"找不到资源"),

    /**
     * 业务逻辑异常
     */
    API_ERROR_CODE_BUSINESS_EXCEPTION(409,"业务逻辑异常"),

    /**
     * 数据库唯一性校验异常
     */
    API_ERROR_CODE_DS_EXCEPTION(410, "数据库异常"),

    /**
     * 参数校验异常
     */
    API_ERROR_CODE_PARAMS_CHECK_EXCEPTION(422,"参数校验异常"),

    /**
     * 服务器内部错误
     */
    API_ERROR_CODE_SERVER_EXCEPTION(500,"服务器内部错误"),

    /**
     * Hystrix异常
     */
    API_ERROR_CODE_HYSTRIX_EXCEPTION(503,"Hystrix异常");


    ApiErrorEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private int code;

    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static String getDescByValue(int value) {
        for (ApiErrorEnum enums : ApiErrorEnum.values()) {
            if (enums.getCode() == value) {
                return enums.getMsg();
            }
        }
        return "";
    }

}
