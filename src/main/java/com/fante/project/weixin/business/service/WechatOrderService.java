package com.fante.project.weixin.business.service;

import com.fante.common.business.enums.OrderConst;
import com.fante.common.utils.Arith;
import com.fante.common.utils.Checker;
import com.fante.common.utils.StringUtils;
import com.fante.common.utils.idgen.IdGenerator;
import com.fante.framework.config.WechatConfig;
import com.fante.project.api.omsOrderProcess.service.OmsOrderCallBackService;
import com.fante.project.business.omsOrder.domain.OmsOrder;
import com.fante.project.business.omsOrder.domain.OmsOrderDetail;
import com.fante.project.business.omsOrder.service.IOmsOrderService;
import com.fante.project.business.omsOrderReturnApply.domain.OmsOrderReturnApply;
import com.fante.project.business.omsOrderReturnApply.service.IOmsOrderReturnApplyService;
import com.fante.project.business.omsPayOrder.domain.OmsPayOrder;
import com.fante.project.business.omsPayOrder.service.IOmsPayOrderService;
import com.fante.project.business.smsGroupMemberRecord.domain.SmsGroupMemberRecord;
import com.fante.project.business.smsGroupMemberRecord.service.ISmsGroupMemberRecordService;
import com.fante.project.business.umsMember.domain.UmsMember;
import com.fante.project.business.umsMember.service.IUmsMemberService;
import com.fante.project.weixin.business.domain.GroupBuyRefundDTO;
import com.fante.project.weixin.core.domain.OrderRefundRsp;
import com.fante.project.weixin.core.domain.UnifiedOrderRsp;
import com.fante.project.weixin.core.service.impl.WeixinPayService;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * @program: Fante
 * @Date:
 * @Author: Mr.Z
 * @Description:
 */
@Service
public class WechatOrderService {

    private static Logger log = LoggerFactory.getLogger(WechatOrderService.class);
    // 金额正则
    private static final String REGEX_MONEY = "(0|([1-9][0-9]{0,8})|(([0]\\.\\d{1,2}|[1-9][0-9]{0,8}\\.\\d{1,2})))";

    @Autowired
    WechatConfig wechatConfig;
    @Autowired
    WeixinPayService weixinPayService;
    @Autowired
    OmsOrderCallBackService omsOrderCallBackService;
    @Autowired
    IOmsPayOrderService omsPayOrderService;
    @Autowired
    IOmsOrderReturnApplyService omsOrderReturnApplyService;
    @Autowired
    IOmsOrderService omsOrderService;
    @Autowired
    IUmsMemberService umsMemberService;
    @Autowired
    ISmsGroupMemberRecordService smsGroupMemberRecordService;

    /**
     * 团购退款
     * @param records
     */
    public void wxRefundOrder(List<SmsGroupMemberRecord> records) {
        log.info("团购退款:: 开始");
        if (ObjectUtils.isEmpty(records)) {
            return;
        }
        // 退货数据
        List<GroupBuyRefundDTO> dtos = Lists.newArrayList();

        // 检验并组装数据
        for (SmsGroupMemberRecord record : records) {
            dtos.add(groupRefundPrepare(record));
        }

        // 批量退款
        for (GroupBuyRefundDTO dto : dtos) {
            SmsGroupMemberRecord record = dto.getRecord();
            OrderRefundRsp rsp = weixinPayService.wxRefundForJsApi(
                    dto.getOpenid(),
                    dto.getOutTradeNo(),
                    record.getReturnApplySn(),
                    dto.getTotalFee(),
                    dto.getTotalFee()
            );
            record.setReturnFlag(rsp.getStatus() ? "1" : "0");
            smsGroupMemberRecordService.updateSmsGroupMemberRecord(record);
        }
    }

    private GroupBuyRefundDTO groupRefundPrepare(SmsGroupMemberRecord record) {
        // 获取用户信息
        UmsMember member = umsMemberService.selectUmsMemberById(record.getMemberId());
        Checker.check(ObjectUtils.isEmpty(member), "未找到退款申请人信息");

        // 获取订单信息
        OmsOrder order = omsOrderService.selectOmsOrderByOrderSn(record.getOrderSn());
        Checker.check(ObjectUtils.isEmpty(order), "未找到退款的店铺订单信息");

        // 获取支付订单信息
        OmsPayOrder payOrder = omsPayOrderService.selectOmsPayOrderById(order.getPayOrderId());
        Checker.check(ObjectUtils.isEmpty(payOrder), "未找到退款的支付订单信息");

        // 生成退款单号
        record.setReturnApplySn(String.valueOf(IdGenerator.nextId()));
        // 订单金额
        String totalFee = formatFee(payOrder.getPayTotalPrice(), "团购订单金额");

        // 组装退货数据预处理
        return new GroupBuyRefundDTO(member.getOpenid(), payOrder.getPayOrderSn(), totalFee, record);
    }


    /**
     * 调用微信申请付款
     * @param apply
     * @return
     * @throws Exception
     */
    public OrderRefundRsp wxRefundOrder(OmsOrderReturnApply apply) {

        log.info("调用微信退款:: 开始");

        // 获取用户信息
        UmsMember member = umsMemberService.selectUmsMemberById(apply.getMemberId());
        Checker.check(ObjectUtils.isEmpty(member), "未找到退款申请人信息");

        // 获取订单信息
        OmsOrder order = omsOrderService.selectOmsOrderById(apply.getOrderId());
        Checker.check(ObjectUtils.isEmpty(order), "未找到退款的店铺订单信息");

        // 获取支付订单信息
        OmsPayOrder payOrder = omsPayOrderService.selectOmsPayOrderById(order.getPayOrderId());
        Checker.check(ObjectUtils.isEmpty(payOrder), "未找到退款的支付订单信息");

        String totalFee = formatFee(payOrder.getPayTotalPrice(), "订单金额");
        String refundFee = formatFee(apply.getReturnAmount(), "退款金额");

        return weixinPayService.wxRefundForJsApi(
                member.getOpenid(),
                payOrder.getPayOrderSn(),
                apply.getReturnApplySn(),
                totalFee,
                refundFee
        );
    }
    /**
     * 付款未发货前退款
     * @param orderId
     */
    public OrderRefundRsp wxRefundOrder(Long orderId,String returnSn) {
        log.info("普通退款:: 开始");
        // 退货数据
        OmsOrderDetail detail = omsOrderService.getOmsOrderDetailById(orderId);
        Checker.check(ObjectUtils.isEmpty(detail), "未找到退款的店铺订单信息");

        Long payOrderId = detail.getPayOrderId();
        OmsPayOrder payOrder = omsPayOrderService.selectOmsPayOrderById(payOrderId);
        Checker.check(ObjectUtils.isEmpty(payOrder), "未找到退款的支付订单信息");
        //获取用户
        Long memberId = detail.getMemberId();
        UmsMember member = umsMemberService.selectUmsMemberById(memberId);
        Checker.check(ObjectUtils.isEmpty(member), "未找到退款申请人信息");

        String totalFee = formatFee(payOrder.getPayTotalPrice(), "订单金额");
        //退款
        OrderRefundRsp rsp = weixinPayService.wxRefundForJsApi(
                member.getOpenid(),
                payOrder.getPayOrderSn(),
                returnSn,
                totalFee,
                totalFee
        );
        return  rsp;
    }

    /**
     * 调用微信统一下单
     * @param request
     * @param url
     * @param payOrderSn
     * @return
     * @throws Exception
     */
    public UnifiedOrderRsp wxUnifiedorder(HttpServletRequest request, String url, String payOrderSn, Long memberId, String memberOpenid) throws Exception {
        log.info("调用微信下单:: 开始");
        Checker.check(StringUtils.isBlank(payOrderSn), "缺少订单号");
        Checker.check(StringUtils.isBlank(url), "缺少下单网页的URL链接");

        OmsPayOrder payOrder = omsPayOrderService.selectOmsPayOrderByOrderSn(payOrderSn, OrderConst.PayStatus.NO_PAY.getType());
        Checker.check(ObjectUtils.isEmpty(payOrder), "未找到待支付的订单");
        Checker.checkOp(Objects.equals(memberId, payOrder.getMemberId()), "该订单非当前用户的订单");

        String totalTee = formatFee(payOrder.getPayTotalPrice(), "下单金额");
        return weixinPayService.wxPayForJsApi(
                request,
                url,
                memberOpenid,
                payOrder.getPayOrderSn(),
                totalTee);
    }

    /**
     * 检验并转换金额
     * @param fee
     * @param tip
     * @return
     */
    private String formatFee(BigDecimal fee, String tip) {
        Checker.check(ObjectUtils.isEmpty(fee), "缺少" + tip);
        Checker.checkOp(Pattern.matches(REGEX_MONEY, fee.toString()), tip + "格式异常");
        Checker.checkOp(fee.compareTo(BigDecimal.ZERO) > 0, tip + "需大于0");
        return Arith.mul(fee.toString(), wechatConfig.getTransYuanToFen(), 0, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 支付回调处理
     * @param outTradeNo
     */
    public void payNotify(String outTradeNo) {
        omsOrderCallBackService.paySuccessCallBack(outTradeNo);
    }

}
