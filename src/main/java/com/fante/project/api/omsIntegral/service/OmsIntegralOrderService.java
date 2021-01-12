package com.fante.project.api.omsIntegral.service;

import com.fante.common.business.enums.OrderConst;
import com.fante.common.business.enums.UmsAddressConst;
import com.fante.common.utils.Checker;
import com.fante.common.utils.DateUtils;
import com.fante.common.utils.StringUtils;
import com.fante.common.utils.idgen.IdGenerator;
import com.fante.project.api.appView.service.UmsAddressService;
import com.fante.project.api.omsIntegral.domain.IntegralExchangeParam;
import com.fante.project.api.omsIntegral.domain.IntegralOrderInfo;
import com.fante.project.api.omsIntegral.domain.OmsIntegralOrder;
import com.fante.project.api.setting.OrderStting;
import com.fante.project.business.omsOrder.domain.OmsOrder;
import com.fante.project.business.omsOrder.service.IOmsOrderService;
import com.fante.project.business.omsOrderItem.domain.OmsOrderItem;
import com.fante.project.business.omsOrderItem.service.IOmsOrderItemService;
import com.fante.project.business.pmsIntegralFeightTemplate.service.IPmsIntegralFeightTemplateService;
import com.fante.project.business.pmsIntegralProduct.domain.PmsIntegralProduct;
import com.fante.project.business.pmsIntegralProduct.service.IPmsIntegralProductService;
import com.fante.project.business.umsMember.domain.UmsMember;
import com.fante.project.business.umsMember.service.IUmsMemberService;
import com.fante.project.business.umsMemberReceiveAddress.domain.UmsMemberReceiveAddress;
import com.fante.project.business.umsMemberReceiveAddress.service.IUmsMemberReceiveAddressService;
import org.apache.poi.ss.formula.functions.Now;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author wz
 * @Description OmsIntergralOrderService
 * @CreatTime 2020/4/22
 */
@Service
public class OmsIntegralOrderService {
    /**
     * 积分商品
     */
    @Autowired
    private IPmsIntegralProductService iPmsIntegralProductService;
    /**
     * 收货地址
     */
    @Autowired
    private IUmsMemberReceiveAddressService iUmsMemberReceiveAddressService;
    /**
     * 前端收货地址
     */
    @Autowired
    private UmsAddressService umsAddressService;
    /**
     * 积分运费模板
     */
    @Autowired
    private IPmsIntegralFeightTemplateService iPmsIntegralFeightTemplateService;
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
     * 会员表
     */
    @Autowired
    private IUmsMemberService iUmsMemberService;
    /**
     * 订单设置
     */
    @Autowired
    private OrderStting orderStting;

    /**
     * [结算]订单:积分兑换
     *
     * @param param
     * @return
     */
    public IntegralOrderInfo createPreviewOfIntergral(IntegralExchangeParam param) {
        Long addressId = param.getAddressId();
        UmsMemberReceiveAddress address = null;
        if(!ObjectUtils.isEmpty(addressId)){
            address = iUmsMemberReceiveAddressService.selectUmsMemberReceiveAddressById(addressId);
            Checker.check(ObjectUtils.isEmpty(address), "选择的收货地址异常！");
        }else{
            //如果地址为空则获取默认
            address = umsAddressService.getDefAddr(param.getMember().getId());
        }
        return generationIntegralOrder(param, address);
    }

    /**
     * [确认]下单:积分兑换
     *
     * @param param
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public OmsOrderItem intergralOfExchange(IntegralExchangeParam param) {
        Long addressId = param.getAddressId();
        UmsMemberReceiveAddress memberAddr = iUmsMemberReceiveAddressService.selectUmsMemberReceiveAddressById(addressId);
        Checker.check(ObjectUtils.isEmpty(memberAddr), "选择的收货地址异常！");
        UmsMember member = param.getMember();
        IntegralOrderInfo info = generationIntegralOrder(param, memberAddr);
        //商品信息
        PmsIntegralProduct product = info.getProduct();
        //总共需要的积分
        BigDecimal amount = new BigDecimal(info.getTotalPrice());
        //*****************订单*********************
        OmsOrder order = new OmsOrder();
        //设置商品shopid
        order.setShopId(product.getShopId());
        //============设置物流地址============
        order.setReceiverProvince(memberAddr.getProvince());
        order.setReceiverCity(memberAddr.getCity());
        order.setReceiverRegion(memberAddr.getRegion());
        order.setReceiverDetailAddress(memberAddr.getDetailAddress());
        order.setReceiverName(memberAddr.getName());
        order.setReceiverPhone(memberAddr.getPhoneNumber());
        order.setReceiverPostCode(memberAddr.getPostCode());
        //============订单备注/创建时间/用户id/优惠信息============
        order.setCreateTime(DateUtils.getNowDate());
        order.setCreateBy(member.getNickname());
        order.setMemberId(member.getId());
        order.setAutoConfirmDay(orderStting.getAutoConfirmDay());
        order.setNote(param.getNote());
        order.setGameInfo("积分兑换");
        //============生成订单编号============
        String orderSN = String.valueOf(IdGenerator.nextId());
        order.setOrderSn(orderSN);
        //============累加总订单积分 = 实际支付积分 ============
        order.setPayAmount(amount);
        order.setProductTotalAmount(amount);
        //============设置订单类型 和 状态:待发货============
        order.setOrderType(OrderConst.OrderType.INTEGRAL.getType());
        order.setStatus(OrderConst.OrderStatus.WAIT_SEND.getType());
        order.setBillType("0");
        OmsOrderItem orderItem = new OmsOrderItem();
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
        //sku:id / 规格 / code /主图 / 原始积分
        orderItem.setProductSkuId(product.getId());
        orderItem.setProductSpData(StringUtils.EMPTY);
        orderItem.setProductSkuCode(product.getProductSn());
        orderItem.setProductPic(product.getPic());
        orderItem.setInitPrice(new BigDecimal(product.getOriginalPrice()));
        //购买数量
        orderItem.setProductQuantity(info.getQuantity());
        //设置商品单价积分
        orderItem.setProductPrice(new BigDecimal(product.getPrice()));
        //设置支付积分
        orderItem.setPayPrice(amount);
        //插入订单获取订单id
        int insert_order = iOmsOrderService.insertOmsOrder(order);
        orderItem.setOrderId(order.getId());
        //插入订单详情
        int insert_item = iOmsOrderItemService.insertOmsOrderItem(orderItem);
        //修改积分商品的库存 同时 增加销量
        int update_stock = iPmsIntegralProductService.subStock(info.getQuantity(), product.getId());
        //修改兑换人的积分
        UmsMember update = new UmsMember();
        update.setId(member.getId());
        //使用会员的积分支付
        int update_member = iUmsMemberService.payForIntegralProduct(member.getId(), info.getTotalPrice());
        Checker.checkOp(insert_order + insert_item + update_stock + update_member == 4, "兑换失败！");
        return orderItem;
    }

    /**
     * 根据参数生成积分订单需要的参数信息
     *
     * @param param
     * @return
     */
    private IntegralOrderInfo generationIntegralOrder(IntegralExchangeParam param, UmsMemberReceiveAddress checkAddress) {
        //验证参数是否合法
        param.validate();
        PmsIntegralProduct pmsIntegralProduct = iPmsIntegralProductService.selectPmsIntegralProductById(param.getId());
        Checker.check(ObjectUtils.isEmpty(pmsIntegralProduct), "该商品已下架！");
        IntegralOrderInfo info = new IntegralOrderInfo(pmsIntegralProduct,param.getQuantity());
        //设置总价
        info.setTotalPrice(pmsIntegralProduct.getPrice() * info.getQuantity());

        boolean isFree = false;
        if (!ObjectUtils.isEmpty(checkAddress)) {
            //获取运费 true 配送 false 不配送
            isFree = iPmsIntegralFeightTemplateService.getFeightFeeByRegion(checkAddress.getProvince());
        }
        info.setFree(isFree);
        //设置地址
        info.setAddress(checkAddress);
        return info;
    }


    /**
     * 查询:所有积分订单(会员操作)
     *
     * @param tokenUserId
     * @return
     */
    public List<OmsIntegralOrder> list(String orderStatus, Long tokenUserId) {
        //判断订单状态,如果空则查询所有,否则查询相应的状态
        if (!StringUtils.isEmpty(orderStatus)) {
            OrderConst.OrderStatus status = OrderConst.OrderStatus.get(orderStatus);
            Checker.check(ObjectUtils.isEmpty(status), "订单状态异常");
        }
        return iOmsOrderService.selectIntegralOrderList(orderStatus, tokenUserId);
    }

    /**
     * 查询:一个积分订单(会员操作)
     *
     * @param id
     * @param tokenUserId
     * @return
     */
    public OmsIntegralOrder get(Long id, Long tokenUserId) {
        return iOmsOrderService.selectIntegralOrderById(id, tokenUserId);
    }
}
