package com.fante.project.api.test;

import com.fante.common.utils.DateUtils;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.project.api.mq.sender.TestDelaySender;
import com.fante.project.api.mq.sender.TestSender;
import com.fante.project.api.omsOrderProcess.service.OmsOrderCallBackService;
import com.fante.project.business.smsCoupon.service.ISmsCouponService;
import com.fante.project.weixin.core.service.impl.WeixinPayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: Fante
 * @Date: 2020/4/15 22:40
 * @Author: Mr.Z
 * @Description: api测试
 */
@Api(tags = "ApiTestController", description = "api测试")
@Controller
@RequestMapping("/api/test")
public class ApiTestController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(ApiTestController.class);

    @Autowired
    TestSender testSender;
    @Autowired
    TestDelaySender testDelaySender;
    @Autowired
    WeixinPayService weixinPayService;
    @Autowired
    OmsOrderCallBackService omsOrderCallBackService;
    @Autowired
    ISmsCouponService iSmsCouponService;

    @ApiOperation("直连交换机--测试队列")
    @GetMapping("direct")
    @ResponseBody
    public AjaxResult direct() {
        for (int i = 0; i < 30; i++) {
            testSender.send("<- " + i + " ->");
        }
        return AjaxResult.success();
    }

    @ApiOperation("直连交换机--测试延时队列")
    @GetMapping("delay")
    @ResponseBody
    public AjaxResult delayDirect() {
        String info = "当前时间：" + DateUtils.getTime();
        log.info("向延时队列发送信息：{}", info);
        testDelaySender.send(info);
        return AjaxResult.success();
    }

    @ApiOperation("test")
    @GetMapping("test")
    @ResponseBody
    public AjaxResult test(String a,String b,String c) {

        return AjaxResult.success();
    }


    /********************************* 模拟支付成功回掉******************************************/
    @ApiOperation("模拟支付成功回掉")
    @PostMapping("/paySuccess")
    @ResponseBody
    public AjaxResult changeAddress(String payOrderSn) {
        return toAjax(omsOrderCallBackService.paySuccessCallBack( payOrderSn));
    }

    /********************************* 模拟第一次登录商家赠送******************************************/
    @ApiOperation("模拟支付成功回掉")
    @PostMapping("/offerCoupons")
    @ResponseBody
    public AjaxResult changeAddress(Long memberId, Long shopId) {
        return AjaxResult.success(iSmsCouponService.offerCoupons(memberId,shopId));
    }


}
