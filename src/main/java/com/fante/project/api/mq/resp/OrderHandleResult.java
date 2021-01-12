package com.fante.project.api.mq.resp;

import com.fante.common.utils.StringUtils;
import com.fante.framework.web.domain.AjaxResult;

import java.io.Serializable;

/**
 * @author ftnet
 * @Description OrderHandleDTO
 * @CreatTime 2020/4/16
 */
public class OrderHandleResult implements Serializable {
    private static final long serialVersionUID = 1L;

    private int code;
    private String msg;
    private String payOrderSn;

    /**
     * 500 : 服务器遇到了一个未曾预料的状况，导致了它无法完成对请求的处理。
     *
     * @param msg
     * @return
     */
    public static OrderHandleResult error(String msg) {
        return new OrderHandleResult(AjaxResult.Type.ERROR.value(), msg, StringUtils.EMPTY);
    }

    /**
     * 500 : 服务器成功处理了请求，且没有返回任何内容。
     *
     * @return
     */
    public static OrderHandleResult noneOrder() {
        return new OrderHandleResult(AjaxResult.Type.ERROR.value(), "暂无订单！", StringUtils.EMPTY);
    }

    /**
     * 202: 服务器已接受请求，但尚未处理。
     *
     * @return
     */
    public static OrderHandleResult handling() {
        return new OrderHandleResult(AjaxResult.Type.INFO.value(), "处理中...", StringUtils.EMPTY);
    }

    /**
     * 200 :请求已成功，请求所希望的响应头或数据体将随此响应返回。
     *
     * @param payOrderSn
     * @return
     */
    public static OrderHandleResult success(String payOrderSn) {
        return new OrderHandleResult(AjaxResult.Type.SUCCESS.value(), StringUtils.EMPTY, payOrderSn);
    }


    public OrderHandleResult(int code, String msg, String payOrderSn) {
        this.code = code;
        this.msg = msg;
        this.payOrderSn = payOrderSn;
    }

    public OrderHandleResult() {
    }

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

    public String getPayOrderSn() {
        return payOrderSn;
    }

    public void setPayOrderSn(String payOrderSn) {
        this.payOrderSn = payOrderSn;
    }
}
