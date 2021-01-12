package com.fante.project.api.omsOrderProcess.service.impl;

import com.alibaba.fastjson.JSON;
import com.fante.common.business.enums.OrderConst;
import com.fante.common.business.enums.SmsCouponConst;
import com.fante.common.exception.BusinessException;
import com.fante.common.utils.Arith;
import com.fante.common.utils.Checker;
import com.fante.common.utils.DateUtils;
import com.fante.common.utils.StringUtils;
import com.fante.common.utils.idgen.IdGenerator;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.project.api.mq.req.OrderHandleOfCartParam;
import com.fante.project.api.mq.req.OrderHandleParam;
import com.fante.project.api.mq.resp.OrderHandleResult;
import com.fante.project.api.mq.sender.CancelOrderSender;
import com.fante.project.api.mq.sender.ConfirmOrderSender;
import com.fante.project.api.omsOrderProcess.domain.CartSkuDto;
import com.fante.project.api.omsOrderProcess.domain.ConfirmOrderParam;
import com.fante.project.api.omsOrderProcess.domain.ValidateStockDTO;
import com.fante.project.api.omsOrderProcess.service.IOmsConfirmOrderService;
import com.fante.project.api.setting.OrderStting;
import com.fante.project.api.utils.OrderRedis;
import com.fante.project.business.omsCartItem.service.IOmsCartItemService;
import com.fante.project.business.omsOrder.domain.OmsOrder;
import com.fante.project.business.omsOrder.service.IOmsOrderService;
import com.fante.project.business.omsOrderItem.domain.OmsOrderItem;
import com.fante.project.business.omsOrderItem.service.IOmsOrderItemService;
import com.fante.project.business.omsPayOrder.domain.OmsPayOrder;
import com.fante.project.business.omsPayOrder.service.IOmsPayOrderService;
import com.fante.project.business.pmsFeightTemplate.service.IPmsFeightTemplateService;
import com.fante.project.business.pmsProduct.domain.PmsProduct;
import com.fante.project.business.pmsProduct.service.IPmsProductService;
import com.fante.project.business.pmsSkuStock.service.IPmsSkuStockService;
import com.fante.project.business.smsCoupon.domain.SmsCouponDTO;
import com.fante.project.business.smsCoupon.service.ISmsCouponService;
import com.fante.project.business.smsCouponHistory.domain.SmsCouponHistory;
import com.fante.project.business.smsCouponHistory.service.ISmsCouponHistoryService;
import com.fante.project.business.smsFlashPromotion.service.ISmsFlashPromotionService;
import com.fante.project.business.smsFlashPromotionProduct.service.ISmsFlashPromotionProductService;
import com.fante.project.business.smsFlashPromotionSession.service.ISmsFlashPromotionSessionService;
import com.fante.project.business.smsFlashPromotionSku.domain.SmsFlashPromotionSku;
import com.fante.project.business.smsFlashPromotionSku.service.ISmsFlashPromotionSkuService;
import com.fante.project.business.smsGroupGameRecord.service.ISmsGroupGameRecordService;
import com.fante.project.business.smsGroupGameSku.service.ISmsGroupGameSkuService;
import com.fante.project.business.umsMemberReceiveAddress.domain.UmsMemberReceiveAddress;
import com.fante.project.business.umsMemberReceiveAddress.service.IUmsMemberReceiveAddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 各种下单的服务
 *
 * @author wz
 */
@Service
public class OmsConfirmOrderServiceImpl implements IOmsConfirmOrderService {
    private static Logger log = LoggerFactory.getLogger(OmsConfirmOrderServiceImpl.class);

    @Autowired
    private OrderRedis orderRedis;
    /**
     * 支付订单服务
     */
    @Autowired
    private IOmsPayOrderService iOmsPayOrderService;
    /**
     * 取消订单队列
     */
    @Autowired
    private CancelOrderSender cancelOrderSender;
    /**
     * 订单队列
     */
    @Autowired
    private ConfirmOrderSender confirmOrderSender;
    /**
     * 订单
     */
    @Autowired
    private IOmsOrderService iOmsOrderService;
    /**
     * 订单详情
     */
    @Autowired
    private IOmsOrderItemService iOmsOrderItemService;
    /**
     * 订单设置
     */
    @Autowired
    private OrderStting orderStting;
    /**
     * 购物车
     */
    @Autowired
    private IOmsCartItemService iOmsCartItemService;
    /**
     * 收货地址
     */
    @Autowired
    private IUmsMemberReceiveAddressService iUmsMemberReceiveAddressService;
    /**
     * 运费
     */
    @Autowired
    private IPmsFeightTemplateService iPmsFeightTemplateService;
    /**
     * 商品
     */
    @Autowired
    private IPmsProductService iPmsProductService;
    /**
     * 商品sku
     */
    @Autowired
    private IPmsSkuStockService iPmsSkuStockService;
    /**
     * 处理优惠券
     */
    @Autowired
    private ISmsCouponService iSmsCouponService;
    /**
     * 历史优惠券
     */
    @Autowired
    private ISmsCouponHistoryService iSmsCouponHistoryService;
    /**
     * 团购查询
     */
    @Autowired
    private ISmsGroupGameSkuService iSmsGroupGameSkuService;
    /**
     * 团购记录查询
     */
    @Autowired
    private ISmsGroupGameRecordService iSmsGroupGameRecordService;
    /**
     * 秒杀sku关系表查询
     */
    @Autowired
    private ISmsFlashPromotionSkuService iSmsFlashPromotionSkuService;
    /**
     * 秒杀商品关系表查询
     */
    @Autowired
    private ISmsFlashPromotionProductService iSmsFlashPromotionProductService;
    /**
     * 秒杀活动
     */
    @Autowired
    private ISmsFlashPromotionService iSmsFlashPromotionService;
    /**
     * 秒杀时间段
     */
    @Autowired
    private ISmsFlashPromotionSessionService iSmsFlashPromotionSessionService;


    /**
     * #################从购物车中下单购买##########
     *
     * @param params
     * @return
     */
    @Override
    public int confirmByCart(List<ConfirmOrderParam> params, Long memberId) {
        System.out.println("从购物车中下单购买="+params.get(0).getPaymentType());
        //验证当前用户是否有正在处理的订单
        existOrderHanding(memberId);
        OrderHandleOfCartParam handleOfCartParam = new OrderHandleOfCartParam();
        //设置订单标识：购物车订单
        handleOfCartParam.setCartType(OrderConst.CartType.CART.getType());
        handleOfCartParam.setPaymentType(params.get(0).getPaymentType());
        List<OrderHandleParam> OrderHandleList = new ArrayList<>();
        params.forEach(param -> {
            //查询各个商品的集合
            List<CartSkuDto> cartSkus = iPmsSkuStockService.selectPmsSkuStockByCartIds(String.valueOf(param.getCartIds()));
            //生成订单
            OrderHandleList.add(handleOrder(cartSkus, param, StringUtils.EMPTY,"1"));
        });
        handleOfCartParam.setOrderHandleList(OrderHandleList);

        OmsPayOrder payOrder = new OmsPayOrder();
        payOrder.setMemberId(memberId);
        payOrder.setPayOrderSn(String.valueOf(IdGenerator.nextId()));
        payOrder.setCreateTime(DateUtils.getNowDate());
        payOrder.setCreateBy(params.get(0).getMember().getNickname());
        handleOfCartParam.setPayOrder(payOrder);
        //核算所有分订单总支付价格
        handleOfCartParam.countPayTotalPrice();
        //设置订单处理中
        orderRedis.setOrderRedis(memberId, OrderHandleResult.handling());
        //加入队列
        confirmOrderSender.sendGeneralOrderByCart(handleOfCartParam);
        return 1;
    }

    /**
     * ##################立即购买##################
     *
     * @param param
     * @return
     */
    @Override
    public int confirmByNow(ConfirmOrderParam param) {
        System.out.println("立即购买购买方式："+param.getPaymentType());
        Long memberId = param.getMember().getId();
        //验证当前用户是否有正在处理的订单
        existOrderHanding(memberId);
        //查询各个商品的集合
        List<CartSkuDto> cartSkus = iPmsSkuStockService.selectPmsSkuStockByCartIds(String.valueOf(param.getCartIds()));
        //生成订单
        OrderHandleOfCartParam orderHandleOfCartParam = new OrderHandleOfCartParam();
        orderHandleOfCartParam.setPaymentType(param.getPaymentType());
        //设置订单标识：立即购买订单
        orderHandleOfCartParam.setCartType(OrderConst.CartType.BUY_NOW.getType());
        OrderHandleParam orderHandleParam = handleOrder(cartSkus, param, StringUtils.EMPTY,param.getPaymentType());
        //支付订单
        OmsPayOrder payOrder = new OmsPayOrder();
        payOrder.setMemberId(memberId);
        payOrder.setCreateTime(DateUtils.getNowDate());
        payOrder.setCreateBy(param.getMember().getNickname());
        payOrder.setPayOrderSn(String.valueOf(IdGenerator.nextId()));
        payOrder.setPayTotalPrice(orderHandleParam.getOrder().getPayAmount());
        orderHandleOfCartParam.setPayOrder(payOrder);
        orderHandleParam.setPayOrder(payOrder);
        orderHandleOfCartParam.setOrderHandleList(Arrays.asList(orderHandleParam));
        //设置订单处理中
        orderRedis.setOrderRedis(memberId, OrderHandleResult.handling());
        //加入队列
        confirmOrderSender.sendGeneralOrderByCart(orderHandleOfCartParam);
        return 1;
    }

    /**
     * ##################团购购买##################
     *
     * @param param
     * @return
     */
    @Override
    public int confirmByGroup(ConfirmOrderParam param) {
        Long memberId = param.getMember().getId();
        //验证当前用户是否有正在处理的订单
        existOrderHanding(memberId);
        //根据skuID查询CartSku需要的数据
        CartSkuDto cartSku = iSmsGroupGameSkuService.getCartSkuDtoBySkuId(Long.valueOf(param.getSkuIds()));
        //检查传来的活动skuid和通过商品skuId查到的id是否一致 ,不一致则抛出异常
        Checker.checkOp(Objects.equals(cartSku.getGameSkuId(), param.getPromotionId()), "该商品团购活动已结束.");
        //设置购买数量
        cartSku.setQuantity(param.getQuantity());
        //设置订单处理中
        OrderHandleParam handleParam = handleOrder(Arrays.asList(cartSku), param, OrderConst.CartType.GROUP.getType(),null);
        //支付订单
        OmsPayOrder payOrder = new OmsPayOrder();
        payOrder.setMemberId(memberId);
        //设置参团id
        payOrder.setGroupId(param.getInGroupId());
        payOrder.setCreateTime(DateUtils.getNowDate());
        payOrder.setCreateBy(param.getMember().getNickname());
        payOrder.setPayOrderSn(String.valueOf(IdGenerator.nextId()));
        payOrder.setPayTotalPrice(handleParam.getOrder().getPayAmount());

        handleParam.setPayOrder(payOrder);
        //设置订单处理中
        orderRedis.setOrderRedis(memberId, OrderHandleResult.handling());
        //加入队列 处理团购订单
        confirmOrderSender.sendGroupOrder(handleParam);
        return 0;
    }

    /**
     * ##################秒杀购买##################
     *
     * @param param
     * @return
     */
    @Override
    public int confirmBySeckill(ConfirmOrderParam param) {
        Long memberId = param.getMember().getId();
        //验证当前用户是否有正在处理的订单
        existOrderHanding(memberId);
        //通过商品sku查询商品id并判断是否可用返回可用商品id
        CartSkuDto cartSku = iPmsSkuStockService.getProductDetailBySkuId(Long.valueOf(param.getSkuIds()));
        //根据传入的秒杀sku关系表id计算价格和库存,在队列中 需通过活动skuid-->查询-->活动商品关系id-->查询-->活动表 和 时间段表 判断当前时间是否符合表时间
        SmsFlashPromotionSku gameSku = iSmsFlashPromotionSkuService.selectSmsFlashPromotionSkuById(param.getPromotionId());
        Checker.check(ObjectUtils.isEmpty(gameSku), "秒杀活动已过期！");
        //更新sku信息为秒杀信息
        cartSku.setQuantity(param.getQuantity());
        cartSku.setStock(gameSku.getFlashPromotionCount());
        cartSku.setPromotionPrice(gameSku.getFlashPromotionPrice());
        cartSku.setLockStock(gameSku.getLockStock());
        //生成订单
        OrderHandleParam handleParam = handleOrder(Arrays.asList(cartSku), param, OrderConst.CartType.FLASH.getType(),null);
        //支付订单
        OmsPayOrder payOrder = new OmsPayOrder();
        payOrder.setMemberId(memberId);
        payOrder.setCreateTime(DateUtils.getNowDate());
        payOrder.setCreateBy(param.getMember().getNickname());
        payOrder.setPayOrderSn(String.valueOf(IdGenerator.nextId()));
        payOrder.setPayTotalPrice(handleParam.getOrder().getPayAmount());

        handleParam.setPayOrder(payOrder);
        //设置订单处理中
        orderRedis.setOrderRedis(memberId, OrderHandleResult.handling());
        //加入队列
        confirmOrderSender.sendSeckillOrder(handleParam);
        return 0;
    }

    /**
     * 未支付订单重新支付<br/>
     * 按照订单类型：<br/>
     * 普通订单：如有支付订单号唯一直接调用支付订单支付，否则重新生成支付订单后调用支付<br/>
     * 团购订单、秒杀订单：直接调用支付订单支付<br/>
     * 积分订单：无须支付<br/>
     * @param orderId
     * @return
     */
    @Override
    public String confirmAgain(Long orderId) {//商品订单id
        Checker.check(ObjectUtils.isEmpty(orderId), "缺少订单标识");

        OmsOrder order = iOmsOrderService.selectOmsOrderById(orderId);
        Checker.check(ObjectUtils.isEmpty(order), "未找到该订单");

        OrderConst.OrderType orderType = OrderConst.OrderType.get(order.getOrderType());
        Checker.check(ObjectUtils.isEmpty(orderType), "订单类型异常");

        // 查询
        OmsOrder query = new OmsOrder();
        query.setPayOrderId(order.getPayOrderId());
        boolean anew = iOmsOrderService.countOmsOrder(query) > 1;

        String payOrderSn = "";
        switch (orderType) {
            case GENERAL:
                payOrderSn = doReOrder(order, true);
                break;
            case GROUP:
                payOrderSn = doReOrder(order, false);
                break;
            /*case FLASH:
                payOrderSn = doReOrder(order, false);
                break;
            case INTEGRAL:
                throw new BusinessException(AjaxResult.Type.ERROR.value(), "积分订单无须支付");*/
            default:
                break;
        }
        return payOrderSn;
    }

    /**
     * 重新下单<br/>
     * anew：true 重新生成支付订单，false 使用原支付订单
     * @param order
     * @param anew
     * @return
     */
    private String doReOrder(OmsOrder order, boolean anew) {
        OmsPayOrder payOrder = null;
        if (anew) {
            // 重新生成支付订单
            payOrder = new OmsPayOrder(order.getMemberId(), String.valueOf(IdGenerator.nextId()), order.getPayAmount(), DateUtils.getNowDate());
            iOmsPayOrderService.insertOmsPayOrder(payOrder);
            // 更新订单中支付订单ID
            order.setPayOrderId(payOrder.getId());
            iOmsOrderService.updateBaseOmsOrder(order);
        } else{
            payOrder = iOmsPayOrderService.selectOmsPayOrderById(order.getPayOrderId());
        }
        return payOrder.getPayOrderSn();
    }


    /**
     * ===============队列调用:普通订单-购物车===============
     * 1.插入payOrder支付订单
     * 2.设置每个子订单的payOrderId
     * 3.一次调用->普通商品订单生成,插入数据库数据
     *
     * @param params 需要插入的订单信息
     */
    @Override
    public String completeGeneralOrderOfCart(OrderHandleOfCartParam params) {
        OmsPayOrder payOrder = params.getPayOrder();
        String payOrderSn = payOrder.getPayOrderSn();
        log.info("普通订单-购物车-支付订单号:{},开始插入数据库", payOrderSn);
        //插入payOrder支付订单
        int p = iOmsPayOrderService.insertOmsPayOrder(params.getPayOrder());
        Checker.check(p != 1, "商品信息已过期,请刷新后重试.");
        //支付订单id
        Long payId = payOrder.getId();
        params.getOrderHandleList().stream().forEach(param -> {
            //遍历处理每个订单
            OmsOrder order = param.getOrder();
            log.info("普通订单-立即购买-分单单号:{},循环处理中.", order.getOrderSn());
            //设置payId
            order.setPayOrderId(payId);
            order.setPaymentType(params.getPaymentType());
            List<OmsOrderItem> orderItemList = param.getOrderItemList();
            List<ValidateStockDTO> stockList = param.getStockList();
            String cartIds = param.getCartIds();
            Long promotionId = param.getPromotionId();
            //普通商品订单生成
            completeGeneralOrder(order, orderItemList, stockList, cartIds, promotionId);
        });
        //发送延迟未支付取消订单
        cancelOrderSender.sendDelayMessageCancelGeneralOrder(payOrderSn);
        //返回支付订单的订单号
        return payOrderSn;
    }

    /**
     * ===============队列调用:普通订单-立即购买===============
     * 1.插入支付订单
     * 2.设置订单中的支付订单id信息
     * 3.调用普通商品订单生成生成插入数据库订单信息
     * 4.返回支付订单的订单号
     *
     * @param payOrder      支付订单信息
     * @param order         需要插入的订单信息
     * @param orderItemList 需要插入的订单详情信息
     * @param stockList     需要验证的库存信息
     * @param promotionId   优惠券id
     */
    @Override
    public String completeGeneralOrderOfNow(OmsPayOrder payOrder, OmsOrder order, List<OmsOrderItem> orderItemList, List<ValidateStockDTO> stockList, String cartIds, Long promotionId,String paymentType) {
        log.info("普通订单-立即购买-支付订单号:{},开始插入数据库", payOrder.getPayOrderSn());
        //插入payOrder支付订单

        int p = iOmsPayOrderService.insertOmsPayOrder(payOrder);
        Checker.check(p != 1, "商品信息已过期,请刷新后重试.");
        //设置payId
        order.setPayOrderId(payOrder.getId());
        order.setPaymentType(paymentType);
        //普通商品订单生成
        completeGeneralOrder(order, orderItemList, stockList, cartIds, promotionId);
        //发送延迟未支付取消订单
        cancelOrderSender.sendDelayMessageCancelGeneralOrder(payOrder.getPayOrderSn());
        return payOrder.getPayOrderSn();
    }

    /**
     * ===============队列调用:秒杀订单========================
     * 1. 需通过活动sku关系表-->查询-->活动商品关系表id-->查询-->活动表 和 时间段表 判断当前时间是否符合表时间
     * 2.根据当前商品关系表id和当前skuId查询 秒杀sku关系表
     * 3.根据秒杀sku关系表 和 stockList中的参数验证库存 并得到秒杀skuid
     * 4.设置gameSkuId
     * 5.插入payOrder.设置 order中的payOrderId 插入order
     * 6.设置orderItem中的orderId
     * 7.插入orderItem
     * 8.修改关系表中lockStock
     *
     * @param order         需要插入的订单信息
     * @param orderItemList 需要插入的订单详情信息
     * @param stockList     需要验证的库存信息
     * @param promotionId   需要验证的秒杀sku关系表id
     * @return OrderSn 返回订单号
     */
    @Override
    public String completeFlashOrder(OmsPayOrder payOrder, OmsOrder order, List<OmsOrderItem> orderItemList, List<ValidateStockDTO> stockList, Long promotionId) {
        log.info("秒杀订单-支付订单号:{},开始插入数据库", payOrder.getPayOrderSn());
        OmsOrderItem omsOrderItem = orderItemList.get(0);
        ValidateStockDTO validateStockDTO = stockList.get(0);
        //判断活动是否过期
        int i = iSmsFlashPromotionSkuService.validateGameTimeOut(promotionId, validateStockDTO);
        Checker.check(i != 1, "该商品活动结束");
        //插入payOrder
        int p = iOmsPayOrderService.insertOmsPayOrder(payOrder);
        //设置payId
        order.setPayOrderId(payOrder.getId());
        //插入order
        int n = iOmsOrderService.insertOmsOrder(order);
        //设置orderItem中的orderId
        omsOrderItem.setOrderId(order.getId());
        //插入orderItem
        int m = iOmsOrderItemService.insertOmsOrderItem(omsOrderItem);
        //修改关系表中lockStock
        int c = iSmsFlashPromotionSkuService.addLockStock(promotionId, validateStockDTO.getQuantity());
        Checker.checkOp(c != 0 && m != 0 && m == n, "该商品活动信息已过期！");
        //发送延迟未支付取消订单
        cancelOrderSender.sendDelayMessageCancelSeckillOrder(payOrder.getPayOrderSn());
        return payOrder.getPayOrderSn();
    }

    /**
     * ===============队列调用:团购订单=======================
     * 1.根据团购id查询团购活动对象
     * 2.根据团购活动 及 stockList中的参数验证库存是否充足 并得到团购skuid插入OrderItem中
     * 3.插入payOrder.设置 order中的payOrderId 插入order
     * 4.设置orderItem中的orderId
     * 5.插入orderItem
     * 说明:团购必须立即支付,否则直接取消订单
     *
     * @param order         需要插入的订单信息
     * @param orderItemList 需要插入的订单详情信息
     * @param stockList     需要验证的库存信息
     * @param promotionId   需要验证的团购sku关系表id
     */
    @Override
    public String completeGroupOrder(OmsPayOrder payOrder, OmsOrder order, List<OmsOrderItem> orderItemList, List<ValidateStockDTO> stockList, Long promotionId) {
        log.info("团购订单-支付订单号:{},开始插入数据库", payOrder.getPayOrderSn());
        OmsOrderItem omsOrderItem = orderItemList.get(0);
        ValidateStockDTO validateStockDTO = stockList.get(0);
        //验证团购商品数量是否充足 以及 团购活动是否到期
        int count = iSmsGroupGameSkuService.validateStock(validateStockDTO.getQuantity(), promotionId);
        Checker.check(count != 1, "该团购商品数量不足！");

        Long groupId = payOrder.getGroupId();
        //在该团中预定一个位置,若没有成功则说明该团满额了.
        if (!ObjectUtils.isEmpty(groupId)) {
            //预定名额:同时判断该团是否过时
            int i = iSmsGroupGameRecordService.reservationQuotas(groupId);
            Checker.check(i != 1, "该团拼团已结束！");
        }
        //插入payOrder
        int p = iOmsPayOrderService.insertOmsPayOrder(payOrder);
        //设置payId
        order.setPayOrderId(payOrder.getId());
        //插入order
        int n = iOmsOrderService.insertOmsOrder(order);
        //设置orderItem中的orderId
        omsOrderItem.setOrderId(order.getId());
        //插入orderItem
        int m = iOmsOrderItemService.insertOmsOrderItem(omsOrderItem);
        Checker.checkOp(m != 0 && m == n, "该商品活动结束");
        //发送延迟未支付取消订单
        cancelOrderSender.sendDelayMessageCancelGroupOrder(payOrder.getPayOrderSn());
        return payOrder.getPayOrderSn();
    }


    /**
     * ---------验证当前用户是否有正在处理的订单--------------
     */
    private void existOrderHanding(Long memberId) {
        OrderHandleResult orderRedis = this.orderRedis.getOrderStatusRedis(memberId);
        //不为空 并且 返回code = 202处理中... 就抛出异常
        Checker.check(orderRedis != null && orderRedis.getCode() == AjaxResult.Type.INFO.value(), "订单正在处理！");
    }

    /**
     * -------------------处理订单-------------------------
     *
     * @param cartSkus          处理过价格的商品规格集合
     * @param confirmOrderParam 入参
     * @param promotionType     活动类型:空->没活动 否则 团购或者秒杀
     */
    private OrderHandleParam handleOrder(List<CartSkuDto> cartSkus, ConfirmOrderParam confirmOrderParam, String promotionType,String judge ) {
        Checker.check(ObjectUtils.isEmpty(cartSkus), "订单信息已过期！");
        //***************订单详情******************
        List<OmsOrderItem> orderItemList = new ArrayList<>();
        //*****************订单*********************omsOrder
        OmsOrder order = new OmsOrder();
        //设置商品shopid
        order.setShopId(cartSkus.get(0).getShopId());
        //============设置优惠信息:优惠券id / 活动 id============
        order.setGameId(confirmOrderParam.getPromotionId());
        Long addressId = confirmOrderParam.getMemberReceiveAddressId();
        UmsMemberReceiveAddress memberAddr = iUmsMemberReceiveAddressService.selectUmsMemberReceiveAddressById(addressId);
        System.out.println(judge);
        if ("2".equals(judge)){
            Checker.check(ObjectUtils.isEmpty(memberAddr), "选择的地址不存在！");
        }
        //============设置物流地址============
        if (memberAddr != null) {
            String province = memberAddr.getProvince();
            order.setReceiverProvince(province);
            order.setReceiverCity(memberAddr.getCity());
            order.setReceiverRegion(memberAddr.getRegion());
            order.setReceiverDetailAddress(memberAddr.getDetailAddress());
            order.setReceiverName(memberAddr.getName());
            order.setReceiverPhone(memberAddr.getPhoneNumber());
            order.setReceiverPostCode(memberAddr.getPostCode());
        }else {
            order.setReceiverProvince("中国");
            order.setReceiverCity("驻马店");
            order.setReceiverRegion(" ");
            order.setReceiverDetailAddress(" ");
            order.setReceiverName(" ");
            order.setReceiverPhone(" ");
            order.setReceiverPostCode(" ");
        }

        //============设置发票信息============
        String billType = confirmOrderParam.getBillType();
        order.setBillType(StringUtils.isEmpty(billType) ? OrderConst.BillType.NEEDLESS.getType() : billType);
        if (!StringUtils.equals(OrderConst.BillType.NEEDLESS.getType(), billType)) {
            order.setBillHeader(confirmOrderParam.getBillHeader());
            order.setBillContent(confirmOrderParam.getBillContent());
            order.setBillReceiverPhone(confirmOrderParam.getBillReceiverPhone());
            order.setBillReceiverEmail(confirmOrderParam.getBillReceiverEmail());
        }
        //============订单备注/创建时间/用户id============
        order.setNote(confirmOrderParam.getNote());
        Date nowDate = DateUtils.getNowDate();
        order.setCreateTime(nowDate);
        order.setCreateBy(confirmOrderParam.getMember().getNickname());
        order.setMemberId(confirmOrderParam.getMember().getId());
        order.setAutoConfirmDay(orderStting.getAutoConfirmDay());
        //============初始化订单中的优惠券总金额============
        order.setCouponAmount(BigDecimal.ZERO);
        System.out.println("购物车内支付方式："+confirmOrderParam.getPaymentType());
        order.setPaymentType(confirmOrderParam.getPaymentType());
        //声明:优惠券可用商品list<Id>
        Set<Long> enableCouponProductIds = new HashSet<>();
        //声明:优惠券平均到各个商品的价格
        BigDecimal itemCouponPrice = BigDecimal.ZERO;

        //活动类型:空即是没有活动->处理优惠券
        if (StringUtils.isEmpty(promotionType)) {
            //没活动 就可能有优惠券:处理优惠券
            Long couponId = confirmOrderParam.getPromotionId();
            if (!ObjectUtils.isEmpty(couponId)) {
                //查询优惠券
                SmsCouponDTO smsCouponDTO = iSmsCouponService.selectSmsCouponAllById(couponId);
                Checker.check(ObjectUtils.isEmpty(smsCouponDTO), "选择的优惠券不可用！");
                //============设置活动信息============
                order.setGameInfo(smsCouponDTO.getName());
                //============设置优惠券类型============
                order.setCouponType(smsCouponDTO.getCouponType());//优惠券类型(0:满减券1:折扣券)
                Long enableQuantity = 0L;
                BigDecimal allCouponAmount=BigDecimal.ZERO;
                //优惠券金额
                BigDecimal allPrice=BigDecimal.ZERO;
                //1、满减券直接处理
                if("0".equals(smsCouponDTO.getCouponType())){
                    allCouponAmount = smsCouponDTO.getAmount();
                }

                //2、折扣券需要根据订单金额计算出优惠券金额
                if("1".equals(smsCouponDTO.getCouponType())){
                    //计算订单支付金额
                    allPrice= cartSkus.stream().map(x -> x.getPrice().multiply(new BigDecimal(x.getQuantity()))).reduce((A, B) -> A.add(B)).get();
                    System.out.println("商品金额="+allPrice);
                    //根据订单支付金额计算出总优惠金额
                    //折扣值
                    BigDecimal zkz=new BigDecimal("1").subtract( smsCouponDTO.getDiscount().divide(new BigDecimal(10)));
                    System.out.println("折扣值="+zkz);
                    allCouponAmount = allPrice.multiply(zkz);
                    System.out.println("折扣优惠金额="+allCouponAmount);
                }
                //============设置订单中的优惠券总金额============
                order.setCouponAmount(allCouponAmount);




                SmsCouponConst.useTypeEnum useTypeEnum = SmsCouponConst.useTypeEnum.get(smsCouponDTO.getUseType());
                Checker.check(ObjectUtils.isEmpty(useTypeEnum), "优惠券已失效！");
                switch (useTypeEnum) {
                    case ALLUSE:
                        //商品总数量
                        enableQuantity = cartSkus.stream().map(x -> x.getQuantity()).reduce((A, B) -> A + B).get();
                        enableCouponProductIds.addAll(cartSkus.stream().map(x -> x.getProductId()).collect(Collectors.toSet()));
                        break;
                    case CLASSIFYUSE:
                        //获取可用商品分类
                        Set<Long> enableCateIds = smsCouponDTO.getCateRelations().stream().map(x -> x.getProductCategoryId()).collect(Collectors.toSet());
                        enableQuantity = cartSkus.stream()
                                .filter(x -> enableCateIds.contains(x.getProductCategoryId()))
                                .map(x -> {
                                    enableCouponProductIds.add(x.getProductId());
                                    return x.getQuantity();
                                })
                                .reduce((A, B) -> A + B)
                                .get();
                        break;
                    case PRODUCTUSE:
                        //获取可用商品
                        Set<Long> enableProductIds = smsCouponDTO.getProductRelations().stream().map(x -> x.getProductId()).collect(Collectors.toSet());
                        enableQuantity = cartSkus.stream()
                                .filter(x -> enableProductIds.contains(x.getProductId()))
                                .map(x -> {
                                    enableCouponProductIds.add(x.getProductId());
                                    return x.getQuantity();
                                })
                                .reduce((A, B) -> A + B)
                                .get();
                        break;
                    default:
                        ;
                }


                //============设置优惠券DATA============
                order.setCouponData(JSON.toJSONString(enableCouponProductIds));
                Checker.check(enableQuantity == 0L, "优惠券不可用！");
                itemCouponPrice = Arith.div(allCouponAmount, new BigDecimal(enableQuantity));//平均分配优惠券金额
            } else {
                order.setGameInfo("无优惠");
            }
        }
        BigDecimal itemCouponAmount = itemCouponPrice;
        //模板id去重使用
        Set<Long> templateIds = new HashSet<>();
        List<ValidateStockDTO> stockList = new ArrayList<>();
        //============生成订单编号============
        String orderSN = String.valueOf(IdGenerator.nextId());
        order.setOrderSn(orderSN);
        //============初始化运费总价============
        order.setFreightAmount(BigDecimal.ZERO);
        cartSkus.forEach(cartSku -> {
            ValidateStockDTO validateStockDTO = new ValidateStockDTO();
            OmsOrderItem orderItem = new OmsOrderItem();
            /**判断库存的数据*/
            validateStockDTO.setSkuId(cartSku.getId());
            validateStockDTO.setProductName(cartSku.getProductName());
            validateStockDTO.setQuantity(cartSku.getQuantity());
            validateStockDTO.setSpData(cartSku.getSpData());
            stockList.add(validateStockDTO);
            //Checker.check(cartSku.getStock() - cartSku.getLockStock()<cartSku.getQuantity(),"商品"+cartSku.getProductName()+"已售罄！");
            /**判断配送地区 并计算订单 和详单的运费*/
            Long productId = cartSku.getProductId();
            PmsProduct product = iPmsProductService.selectPmsProductById(productId);
            Checker.check(ObjectUtils.isEmpty(product), "商品信息已过期！");
            Long templateId = product.getFeightTemplateId();
            //根据区域和模板id查询运费和
            /*BigDecimal price = iPmsFeightTemplateService.getFeightFeeByRegion(templateId, province);
            Checker.check(BigDecimal.ONE.negate().equals(price), "商品:" + product.getName() + ",无法运输至你所在的国家或地区！");
            //去重过滤返回不重复的模板价格 如果不需要去重则直接累加 price即可
            if (!templateIds.contains(templateId)) {
                templateIds.add(templateId);
                //============累加运费============
                order.addFreightAmount(price);
            }*/

            /**
             * 设置其他值
             */
            //设置优惠券id 或其他id
            orderItem.setGameSkuId(order.getGameId());
            //ordersn
            orderItem.setOrderSn(orderSN);
            //商品:id name 品牌 sn 分类id
            orderItem.setProductId(product.getId());
            orderItem.setProductName(product.getName());
            orderItem.setProductBrand(product.getBrandName());
            orderItem.setProductSn(product.getProductSn());
            orderItem.setProductCategoryId(product.getProductCategoryId());
            //sku:id / 规格 / code /主图 / 原始价格
            orderItem.setProductSkuId(cartSku.getId());
            orderItem.setProductSpData(cartSku.getSpData());
            orderItem.setProductSkuCode(cartSku.getSkuCode());
            orderItem.setProductPic(cartSku.getPic());
            orderItem.setInitPrice(cartSku.getPrice());
            //购买数量
            orderItem.setProductQuantity(cartSku.getQuantity());

            //============累加总订单价格============
            order.addProductTotalAmount(Arith.mul(cartSku.getPromotionPrice(), new BigDecimal(cartSku.getQuantity())));

            //orderItem.setFreightPrice(price);
            //设置优惠券 优惠信息
            if (enableCouponProductIds.contains(cartSku.getProductId())) {
                orderItem.setCouponPrice(itemCouponAmount);
            }else{
                orderItem.setCouponPrice(BigDecimal.ZERO);
            }
            //设置商品价格 = 促销价格 - 平均优惠金额
            orderItem.setProductPrice(Arith.sub(cartSku.getPromotionPrice(),orderItem.getCouponPrice()));
            //设置实际支付价格 = 商品价格*(数量) + 运费
            orderItem.countPayPrice();
            orderItemList.add(orderItem);
            //积分成长值:暂无此功能
            /*orderItem.setGiftIntegration();
            orderItem.setGiftGrowth();*/
        });
        //============计算实际支付价格============
        order.countPayAmount();
        //============设置订单类型============
        if (StringUtils.equals(promotionType, OrderConst.CartType.GROUP.getType())) {
            //活动类型:团购
            order.setOrderType(OrderConst.OrderType.GROUP.getType());
            order.setGameInfo("团购优惠活动");
            //发送队列
            return new OrderHandleParam(order, orderItemList, stockList, confirmOrderParam.getPromotionId());
        } else if (StringUtils.equals(promotionType, OrderConst.CartType.FLASH.getType())) {
            //活动类型:秒杀
            order.setOrderType(OrderConst.OrderType.FLASH.getType());
            order.setGameInfo("秒杀优惠活动");
            //发送队列
            return new OrderHandleParam(order, orderItemList, stockList, confirmOrderParam.getPromotionId());
        } else {
            //活动类型:普通
            order.setOrderType(OrderConst.OrderType.GENERAL.getType());
            //发送队列
            return new OrderHandleParam(order, orderItemList,stockList, confirmOrderParam.getCartIds(), confirmOrderParam.getPromotionId());
        }
    }

    /**
     * -------------------普通商品订单生成-------------------
     * 1.根据 stockList中的参数验证库存
     * 2.设置 order中的payOrderId 插入order
     * 4.插入orderItem
     * 5.删除购物车ids
     * 3.设置orderItem中的orderId
     *
     * @param order         需要插入的订单信息
     * @param orderItemList 需要插入的订单详情信息
     * @param stockList     需要验证的库存信息
     * @param promotionId   优惠券id
     */
    private void completeGeneralOrder(OmsOrder order, List<OmsOrderItem> orderItemList, List<ValidateStockDTO> stockList, String cartIds, Long promotionId) {
        StringBuilder msg = new StringBuilder();
        //验证优惠券
        if (!ObjectUtils.isEmpty(promotionId)) {
            //查询优惠券是否领取
            SmsCouponHistory couponHistory = new SmsCouponHistory();
            couponHistory.setUserId(order.getMemberId());
            couponHistory.setCouponId(promotionId);
            couponHistory.setOrderId(order.getId());
            couponHistory.setOrderSn(order.getOrderSn());
            //修改优惠券领取使用记录
            int count = iSmsCouponHistoryService.updateForUseCoupon(couponHistory);
            Checker.check(count != 1, "该优惠券不可用！");
        }
        //验证库存
        List<ValidateStockDTO> result = iPmsSkuStockService.validateStock(stockList);
        if (!ObjectUtils.isEmpty(result)) {
            msg.append("商品:");
            result.stream().forEach(x -> {
                msg.append("," + x.getProductName() + "[" + x.getSpData().replaceAll("\\\"", "") + "]");
            });
            msg.deleteCharAt(3);
            msg.append("库存不足！");
            Checker.check(true, msg.toString());
        }
        //插入order
        int i = iOmsOrderService.insertOmsOrder(order);
        Long orderId = order.getId();
        //设置orderItem中的orderId
        orderItemList.forEach(x -> x.setOrderId(orderId));
        //插入orderItem
        int m = iOmsOrderItemService.batchInsert(orderItemList);
        //修改sku表中lockStock
        int n = iPmsSkuStockService.batchAddLockStock(stockList);
        //统一检查订单是否插入成功
        Checker.checkOp(i == 1 && m != 0 && m == n, "商品信息已过期！");
        //删除购物车ids
        iOmsCartItemService.deleteOmsCartItemByIds(cartIds,order.getMemberId());
    }

}
