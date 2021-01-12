package com.fante.project.weixin.core.service.impl;

import com.fante.common.business.enums.WxPayLogTypeEnum;
import com.fante.common.constant.Constants;
import com.fante.common.utils.Checker;
import com.fante.common.utils.IpUtils;
import com.fante.common.utils.StringUtils;
import com.fante.common.utils.UUIDUtils;
import com.fante.common.utils.bean.BeanUtils;
import com.fante.common.utils.spring.SpringUtils;
import com.fante.framework.config.WechatConfig;
import com.fante.project.business.wxPayLog.domain.WxPayLog;
import com.fante.project.business.wxPayLog.service.IWxPayLogService;
import com.fante.project.weixin.core.component.RefundComponent;
import com.fante.project.weixin.core.domain.OrderRefundRsp;
import com.fante.project.weixin.core.domain.UnifiedOrderRsp;
import com.fante.project.weixin.core.model.OrderRefund;
import com.fante.project.weixin.core.model.OrderRefundResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.weixin4j.model.pay.UnifiedOrder;
import org.weixin4j.model.pay.UnifiedOrderResult;
import org.weixin4j.model.pay.WXPay;
import org.weixin4j.spring.WeixinTemplate;
import org.weixin4j.util.PayUtil;
import org.weixin4j.util.SignUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @program: Fante
 * @Date:
 * @Author: Mr.Z
 * @Description: 微信支付-JSAPI
 */
@Service
public class WeixinPayService {

    private static Logger log = LoggerFactory.getLogger(WeixinPayService.class);

    private static final String TRADE_TYPE_JSAPI = "JSAPI";
    // 通信成功标识
    public static final String SIGN_SUCCESS = "SUCCESS";

    @Autowired
    WechatConfig wechatConfig;
    @Autowired
    IWxPayLogService wxPayLogService;
    @Autowired
    RefundComponent refundComponent;

    /**
     * JSAPI申请退款
     * @param openid
     * @param outTradeNo
     * @param outRefundNo
     * @param totalFee
     * @param refundFee
     * @return
     * @throws Exception
     */
    public OrderRefundRsp wxRefundForJsApi(String openid, String outTradeNo, String outRefundNo, String totalFee, String refundFee) {
        log.info("JSAPI-申请退款入参:: openid: {}, 商户订单号: {}, 订单金额(分): {}, 商户退款单号: {}, 退款金额(分): {}",
                openid, outTradeNo, totalFee, outRefundNo, refundFee);

        WeixinTemplate weixinTemplate = SpringUtils.getBean(WeixinTemplate.class);

        OrderRefundResult result = jsApiRefundOrder(weixinTemplate, outTradeNo, outRefundNo, totalFee, refundFee);
        log.info("申请退款:: {}", BeanUtils.beanToString(result));
        Checker.check(ObjectUtils.isEmpty(result), "申请退款响应解析异常");

        OrderRefundRsp rsp = refundResultHandle(result);
        recordRefundResult(result, rsp, openid, outRefundNo, refundFee);
        return rsp;
    }

    /**
     * 记录申请退款结果
     * @param result
     * @param rsp
     * @param openid
     * @param outRefundNo
     * @param refundFee
     */
    private void recordRefundResult(OrderRefundResult result, OrderRefundRsp rsp, String openid, String outRefundNo, String refundFee) {
        WxPayLog payLog = new WxPayLog();
        payLog.setStatus(rsp.getStatus() ? Constants.SUCCESS : Constants.FAIL);
        payLog.setType(WxPayLogTypeEnum.REFUND.getType());
        payLog.setOpenid(openid);
        payLog.setOutTradeNo(outRefundNo);
        payLog.setTotalFee(refundFee);
        payLog.setMsg(rsp.getMsg());
        payLog.setContent(BeanUtils.beanToString(result));
        wxPayLogService.insertWxPayLog(payLog);
    }

    /**
     * 申请退款结果处理
     * @param result
     * @return
     */
    private OrderRefundRsp refundResultHandle(OrderRefundResult result) {
        log.info("返回状态码: {}, 返回信息: {}, 业务结果: {}, 错误代码: {}, 错误代码描述: {}",
                result.getReturn_code(), result.getReturn_msg(), result.getResult_code(),
                result.getErr_code(), result.getErr_code_des());
        boolean status = false;
        if (!result.isSuccess()) {
            return new OrderRefundRsp(status, result.getReturn_msg());
        }
        if (StringUtils.isNotBlank(result.getResult_code()) && Objects.equals(result.getResult_code().toUpperCase(), SIGN_SUCCESS)) {
            status = true;
            return new OrderRefundRsp(status, result.getReturn_msg());
        } else {
            return new OrderRefundRsp(status, "[" + result.getErr_code() + "]" + result.getErr_code_des());
        }
    }

    /**
     * 调用申请退款
     * @param weixinTemplate
     * @param outTradeNo
     * @param outRefundNo
     * @param totalFee
     * @param refundFee
     * @return
     * @throws Exception
     */
    private OrderRefundResult jsApiRefundOrder(WeixinTemplate weixinTemplate, String outTradeNo, String outRefundNo, String totalFee, String refundFee) {
        log.info("构建OrderRefund");
        OrderRefund refund = new OrderRefund();
        refund.setAppid(weixinTemplate.getWeixinConfig().getAppid());
        refund.setMch_id(weixinTemplate.getWeixinPayConfig().getPartnerId());
        refund.setNonce_str(UUIDUtils.general());
        refund.setOut_trade_no(outTradeNo);
        refund.setOut_refund_no(outRefundNo);
        refund.setTotal_fee(totalFee);
        refund.setRefund_fee(refundFee);
        String sign = SignUtil.getSign(refund.toMap(), weixinTemplate.getWeixinPayConfig().getPartnerKey());
        refund.setSign(sign);

        log.info("调用申请退款API");
        return refundComponent.payOrderRefund(refund, weixinTemplate.getWeixinPayConfig().getPartnerId(), weixinTemplate.getWeixinPayConfig().getCertPath());
    }


    /**
     * JSAPI统一下单
     * @param request
     * @param url
     * @param openid
     * @param outTradeNo
     * @param totalTee
     * @return
     * @throws Exception
     */
    public UnifiedOrderRsp wxPayForJsApi(HttpServletRequest request, String url, String openid, String outTradeNo, String totalTee) throws Exception {
        log.info("下单网页的URL: {}", url);
        log.info("JSAPI-统一下单入参:: openid: {}, 商户订单号: {}, 标价金额(分): {}", openid, outTradeNo, totalTee);

        WeixinTemplate weixinTemplate = SpringUtils.getBean(WeixinTemplate.class);

        UnifiedOrderResult result = jsApiUnifiedOrder(request, weixinTemplate, openid, outTradeNo, totalTee);
        log.info("下单结果:: {}", BeanUtils.beanToString(result));
        Checker.check(ObjectUtils.isEmpty(result), "统一下单响应解析异常");

        UnifiedOrderRsp rsp = resultHandle(result);
        if (rsp.getStatus()) {
            log.info("生成发起微信支付请求");
            WXPay wxPay = PayUtil.getChooseWXPay(
                    weixinTemplate.getWeixinConfig().getAppid(),
                    weixinTemplate.getJsApiTicket().getTicket(),
                    result.getPrepay_id(),
                    url,
                    weixinTemplate.getWeixinPayConfig().getPartnerKey());
            rsp.setWxPay(wxPay);
        }
        recordResult(result, rsp, openid, outTradeNo, totalTee);
        return rsp;
    }

    /**
     * 统一下单结果处理
     * @param result
     * @return
     */
    private UnifiedOrderRsp resultHandle(UnifiedOrderResult result) {
        log.info("返回状态码: {}, 返回信息: {}, 业务结果: {}, 错误代码: {}, 错误代码描述: {}",
                result.getReturn_code(), result.getReturn_msg(), result.getResult_code(),
                result.getErr_code(), result.getErr_code_des());
        boolean status = false;
        if (!result.isSuccess()) {
            return new UnifiedOrderRsp(status, result.getReturn_msg());
        }
        if (StringUtils.isNotBlank(result.getResult_code()) && Objects.equals(result.getResult_code().toUpperCase(), SIGN_SUCCESS)) {
            status = true;
            return new UnifiedOrderRsp(status, result.getReturn_msg());
        } else {
            return new UnifiedOrderRsp(status, "[" + result.getErr_code() + "]" + result.getErr_code_des());
        }
    }


    /**
     * 记录统一下单结果
     * @param result
     */
    private void recordResult(UnifiedOrderResult result, UnifiedOrderRsp rsp, String openid, String outTradeNo, String totalTee) {
        WxPayLog payLog = new WxPayLog();
        payLog.setStatus(rsp.getStatus() ? Constants.SUCCESS : Constants.FAIL);
        payLog.setType(WxPayLogTypeEnum.UNIFIEDORDER.getType());
        payLog.setOpenid(openid);
        payLog.setOutTradeNo(outTradeNo);
        payLog.setTotalFee(totalTee);
        payLog.setMsg(rsp.getMsg());
        payLog.setContent(BeanUtils.beanToString(result));
        wxPayLogService.insertWxPayLog(payLog);
    }

    /**
     * 调用统一下单
     * @param request
     * @param weixinTemplate
     * @param openid
     * @param outTradeNo
     * @param totalTee
     * @return
     * @throws Exception
     */
    private UnifiedOrderResult jsApiUnifiedOrder(HttpServletRequest request, WeixinTemplate weixinTemplate, String openid, String outTradeNo, String totalTee) throws Exception {
        log.info("构建UnifiedOrder");
        UnifiedOrder unifiedOrder = new UnifiedOrder();
        unifiedOrder.setAppid(weixinTemplate.getWeixinConfig().getAppid());
        unifiedOrder.setBody(wechatConfig.getWxPayBody());
        unifiedOrder.setMch_id(weixinTemplate.getWeixinPayConfig().getPartnerId());
        unifiedOrder.setNonce_str(UUIDUtils.general());
        unifiedOrder.setNotify_url(weixinTemplate.getWeixinPayConfig().getNotifyUrl());
        unifiedOrder.setOpenid(openid);
        unifiedOrder.setOut_trade_no(outTradeNo);
        unifiedOrder.setSpbill_create_ip(IpUtils.getIpAddr(request));
        unifiedOrder.setTotal_fee(totalTee);
        unifiedOrder.setTrade_type(TRADE_TYPE_JSAPI);
        // 生成Sign
        String sign = SignUtil.getSign(unifiedOrder.toMap(), weixinTemplate.getWeixinPayConfig().getPartnerKey());
        unifiedOrder.setSign(sign);

        log.info("调用统一下单API, 提交XML数据: " + unifiedOrder.toXML());
        return weixinTemplate.pay().payUnifiedOrder(unifiedOrder);
    }


}
