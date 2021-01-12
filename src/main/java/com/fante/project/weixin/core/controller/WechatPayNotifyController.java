package com.fante.project.weixin.core.controller;

import com.fante.common.business.enums.WxPayLogTypeEnum;
import com.fante.common.constant.Constants;
import com.fante.common.utils.StringUtils;
import com.fante.common.utils.bean.BeanUtils;
import com.fante.common.utils.spring.SpringUtils;
import com.fante.framework.interceptor.annotation.RepeatSubmit;
import com.fante.project.business.wxPayLog.domain.WxPayLog;
import com.fante.project.business.wxPayLog.service.IWxPayLogService;
import com.fante.project.weixin.business.service.WechatOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.weixin4j.model.pay.PayNotifyResult;
import org.weixin4j.spring.WeixinTemplate;
import org.weixin4j.util.PayUtil;
import org.weixin4j.util.XStreamFactory;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.util.Objects;

/**
 * @program: Fante
 * @Date:
 * @Author: Mr.Z
 * @Description: 支付结果通知回调
 */
@Controller
public class WechatPayNotifyController {

    private static Logger log = LoggerFactory.getLogger(WechatPayNotifyController.class);

    // 通信成功标识
    public static final String SIGN_SUCCESS = "SUCCESS";

    @Autowired
    IWxPayLogService wxPayLogService;
    @Autowired
    WechatOrderService wechatOrderService;

    @RequestMapping("/wechat/notify")
    @RepeatSubmit
    public void payNotify(HttpServletRequest req, HttpServletResponse resp) {

        try {
            log.info("微信支付回调:: 开始");
            // 获取请求流
            ServletInputStream in = req.getInputStream();
            // 将流转换为字符串
            String xmlMsg = XStreamFactory.inputStream2String(in);
            // 解析回调
            JAXBContext context = JAXBContext.newInstance(PayNotifyResult.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            // 回调结果
            PayNotifyResult result = (PayNotifyResult)unmarshaller.unmarshal(new StringReader(xmlMsg));
            // 记录回调结果
            recordResult(result);

            if (StringUtils.isNotBlank(result.getReturn_code()) && Objects.equals(SIGN_SUCCESS, result.getReturn_code())) {

                WeixinTemplate weixinTemplate = SpringUtils.getBean(WeixinTemplate.class);

                log.info("验证签名");
                boolean verify = PayUtil.verifySign(xmlMsg, weixinTemplate.getWeixinPayConfig().getPartnerKey());
                if (verify) {
                    log.info("到账处理业务:: 开始");
                    wechatOrderService.payNotify(result.getOut_trade_no());
                    log.info("到账处理业务:: 结束");

                    log.info("微信支付回调-成功");
                    resp.getWriter().write("<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>");
                } else {
                    log.error("微信支付回调-签名失败");
                    resp.getWriter().write("<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[签名失败]]></return_msg></xml>");
                }

            } else {
                log.error("微信支付回调 支付失败");
                resp.getWriter().write("<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>");
            }

        } catch (Exception e) {
            log.error("微信支付回调-异常: {}", e.getMessage());
            e.printStackTrace();
            try {
                resp.getWriter().write("<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[参数格式校验错误]]></return_msg></xml>");
            } catch (IOException ex) {
                log.info(ex.getMessage());
            }
        }
    }

    /**
     * 记录回调结果
     * @param result
     */
    private void recordResult (PayNotifyResult result) {
        log.info("返回状态码: {}, 返回信息: {}, 业务结果: {}, 错误代码: {}, 错误代码描述: {}",
                result.getReturn_code(), result.getReturn_msg(), result.getResult_code(),
                result.getErr_code(), result.getErr_code_des());
        log.info("微信支付回调:: openid: {}, 商户订单号: {}, 标价金额: {}",
                result.getOpenid(), result.getOut_trade_no(), result.getTotal_fee());

        boolean status = false;
        String msg = result.getReturn_msg();
        if (StringUtils.isNotBlank(result.getReturn_code()) && Objects.equals(SIGN_SUCCESS, result.getReturn_code())) {
            if (StringUtils.isNotBlank(result.getResult_code()) && Objects.equals(SIGN_SUCCESS, result.getResult_code())){
                status = true;
            } else {
                msg = "[" + result.getErr_code() + "]" + result.getErr_code_des();
            }
        }
        WxPayLog payLog = new WxPayLog();
        payLog.setStatus(status ? Constants.SUCCESS : Constants.FAIL);
        payLog.setType(WxPayLogTypeEnum.PAYNOTIFY.getType());
        payLog.setOpenid(result.getOpenid());
        payLog.setOutTradeNo(result.getOut_trade_no());
        payLog.setTotalFee(result.getTotal_fee());
        payLog.setMsg(msg);
        payLog.setContent(BeanUtils.beanToString(result));
        wxPayLogService.insertWxPayLog(payLog);
    }

}
