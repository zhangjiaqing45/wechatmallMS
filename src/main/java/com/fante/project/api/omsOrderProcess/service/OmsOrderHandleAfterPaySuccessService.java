package com.fante.project.api.omsOrderProcess.service;

import com.alibaba.fastjson.JSON;
import com.fante.common.business.enums.OrderConst;
import com.fante.common.business.enums.UmsMemberConst;
import com.fante.common.utils.Arith;
import com.fante.common.utils.Checker;
import com.fante.common.utils.DateUtils;
import com.fante.common.utils.StringUtils;
import com.fante.project.api.mq.req.JoinGroupHandleParam;
import com.fante.project.api.mq.sender.CancelGroupSender;
import com.fante.project.api.mq.sender.ConfirmOrderSender;
import com.fante.project.api.omsOrderProcess.domain.OmsOrderCommissionDTO;
import com.fante.project.business.accAccountRecord.domain.AccAccountRecord;
import com.fante.project.business.accAccountRecord.service.IAccAccountRecordService;
import com.fante.project.business.accCommissionRecord.domain.AccCommissionRecord;
import com.fante.project.business.accCommissionRecord.service.IAccCommissionRecordService;
import com.fante.project.business.bizShopInfo.service.IBizShopInfoService;
import com.fante.project.business.omsOrder.domain.OmsOrderDetail;
import com.fante.project.business.omsOrder.service.IOmsOrderService;
import com.fante.project.business.omsOrderItem.domain.OmsOrderItem;
import com.fante.project.business.omsPayOrder.domain.OmsPayOrder;
import com.fante.project.business.omsPayOrder.service.IOmsPayOrderService;
import com.fante.project.business.pmsProduct.service.IPmsProductService;
import com.fante.project.business.pmsSkuStock.service.IPmsSkuStockService;
import com.fante.project.business.smsFlashPromotionSku.service.ISmsFlashPromotionSkuService;
import com.fante.project.business.smsGroupGame.domain.SmsGroupGame;
import com.fante.project.business.smsGroupGame.service.ISmsGroupGameService;
import com.fante.project.business.smsGroupGameRecord.domain.SmsGroupGameRecord;
import com.fante.project.business.smsGroupGameRecord.service.ISmsGroupGameRecordService;
import com.fante.project.business.smsGroupGameSku.service.ISmsGroupGameSkuService;
import com.fante.project.business.smsGroupMemberRecord.domain.SmsGroupMemberRecord;
import com.fante.project.business.smsGroupMemberRecord.service.ISmsGroupMemberRecordService;
import com.fante.project.business.umsDistribution.service.IUmsDistributionService;
import com.fante.project.business.umsMember.domain.UmsMember;
import com.fante.project.business.umsMember.service.IUmsMemberService;
import com.fante.project.weixin.business.service.WechatOrderService;
import com.fante.project.weixin.core.service.impl.WeixinPayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 订单支付成功后操作订单信息
 *
 * @author Administrator
 */
@Service
public class OmsOrderHandleAfterPaySuccessService {
    private static Logger log = LoggerFactory.getLogger(OmsOrderHandleAfterPaySuccessService.class);
    @Autowired
    private IOmsOrderService iOmsOrderService;
    /**
     * 微信支付-JSAPI
     */
    @Autowired
    private WechatOrderService wechatOrderService;
    /**
     * 支付订单
     */
    @Autowired
    private IOmsPayOrderService iOmsPayOrderService;
    /**
     * 商品sku
     */
    @Autowired
    private IPmsSkuStockService iPmsSkuStockService;
    /**
     * 商品
     */
    @Autowired
    private IPmsProductService iPmsProductService;
    /**
     * 分销比例商品角色关系Service接口
     */
    @Autowired
    private IUmsDistributionService iUmsDistributionService;
    /**
     * 会员Service接口
     */
    @Autowired
    private IUmsMemberService iUmsMemberService;
    /**
     * 账户出入账记录Service接口
     */
    @Autowired
    private IAccAccountRecordService iAccAccountRecordService;
    /**
     * 店铺信息Service接口
     */
    @Autowired
    private IBizShopInfoService iBizShopInfoService;
    /**
     * 用户佣金记录Service接口
     */
    @Autowired
    private IAccCommissionRecordService iAccCommissionRecordService;
    /**
     * 秒杀活动和sku关系表
     */
    @Autowired
    private ISmsFlashPromotionSkuService iSmsFlashPromotionSkuService;
    /**
     * 团购活动
     */
    @Autowired
    private ISmsGroupGameService iSmsGroupGameService;
    /**
     * 团购SKU活动
     */
    @Autowired
    private ISmsGroupGameSkuService iSmsGroupGameSkuService;
    /**
     * 团购活动记录
     */
    @Autowired
    private ISmsGroupGameRecordService iSmsGroupGameRecordService;
    /**
     * 确认订单队列消息发送者
     */
    @Autowired
    private ConfirmOrderSender confirmOrderSender;
    /**
     * 团购会员记录
     */
    @Autowired
    private ISmsGroupMemberRecordService iSmsGroupMemberRecordService;

    /**
     * 订单支付成功后操作订单信息
     *
     * @param payOrderSn
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public void orderHandleAfterPaySuccess(String payOrderSn) {
        //支付成功,修改支付单状态
        int i = iOmsPayOrderService.paySuccess(payOrderSn);
        Checker.check(i != 1,
                StringUtils.format("支付回掉:支付订单处理异常！支付单号:{}", payOrderSn));
        //根据订单单号查询各个订单详情
        List<OmsOrderDetail> orderDetailList = iOmsOrderService.selectOrderDetailByPayOrderSn(payOrderSn);
        Checker.check(ObjectUtils.isEmpty(orderDetailList),
                StringUtils.format("支付回掉:根据支付单号获取订单失败！支付单号:{}", payOrderSn));

        orderDetailList.forEach(orderDetail -> {
            Checker.check(ObjectUtils.isEmpty(orderDetail.getOrderItemList()),
                    StringUtils.format("支付回掉:根据支付单号获取详细订单失败！支付单号:{}", payOrderSn));

            //获取订单类型
            String orderType = orderDetail.getOrderType();
            OrderConst.OrderType type = OrderConst.OrderType.get(orderType);
            if(!StringUtils.equals(orderType,OrderConst.OrderType.GROUP.getType())){
                //修改订单状态:支付成功
                int j = iOmsOrderService.paySuccess(orderDetail.getId());
                Checker.check(j != 1,
                        StringUtils.format("支付回掉:设置订单支付状态失败！支付单号:{},子订单号:{}", payOrderSn, orderDetail.getOrderSn()));
            }
            switch (type) {
                case GENERAL:
                    //处理一般订单
                    generalOrderHandle(orderDetail);
                    //佣金业务处理
                    ordercommissionHandle(orderDetail);
                    break;
                case GROUP:
                    //修改订单状态:等待团购组团
                    int j = iOmsOrderService.paySuccessForGroup(orderDetail.getId());
                    Checker.check(j != 1,
                            StringUtils.format("支付回掉:设置订单支付状态失败！支付单号:{},子订单号:{}", payOrderSn, orderDetail.getOrderSn()));
                    //处理团购订单
                    OmsPayOrder payOrder = iOmsPayOrderService.selectOmsPayOrderByOrderSn(payOrderSn);
                    groupOrderHandle(orderDetail, payOrder);
                    break;
                case FLASH:
                    //处理秒杀订单
                    seckillOrderHandle(orderDetail);
                    break;
                default:
                    Checker.check(true,
                            StringUtils.format("支付回掉:订单状态异常！支付单号:{},子订单号:{},订单状态:{}", payOrderSn, orderDetail.getOrderSn(), orderType));
            }
        });
    }

    /**
     * ##########一般订单处理_1##########
     * 1.商品sku的 : 锁定库存,实际库存 减
     */
    private void generalOrderHandle(OmsOrderDetail detail) {
        List<OmsOrderItem> orderItemList = detail.getOrderItemList();
        //锁定库存,实际库存 减
        int i = iPmsSkuStockService.subtractStock(orderItemList);
        Checker.check(i != orderItemList.size(), "普通订单:库存扣减失败！");
        //商品销量增加
        int j = iPmsProductService.addSale(orderItemList);
        Checker.check(j != orderItemList.size(), "普通订单:库存扣减失败！");
    }

    /**
     * ##########一般订单处理_2##########
     * 2.佣金处理
     * 2-1.获取 有效上级id(超过一个月上级id不分佣,视为无效上级id)
     * 2-2 判断 普通用户不操作佣金 (或者roleType为空) --> i.插入全金额到店铺账户  (记录 和 账户)
     * 2-3 获取orderitemidList -->获取总分销金额 -->如果佣金为空则不插入佣金记录,同2-2操作
     * 2-4 导购 分享其他店铺 东西 不产生佣金 -->同2-2操作
     * 2-5 高级分销人员 分享其他店铺 --->
     *      i.去掉佣金入账到订单店铺  ( 记录 和 账户)
     *      ii.佣金入账到平台账户账户 (店铺的 记录 和 账户)
     *      iii.插入佣金记录 到用户 (记录 和 账户)
     * 2-6  分销人员 分享 本店商品 --->
     *      i.插入全金额到本店铺账户 ( 记录 和 账户)
     *      ii.佣金入账到本店账户 ( 记录 和 账户)
     *      iii.插入佣金记录 到用户 (记录 和 账户)
     */
    private void ordercommissionHandle(OmsOrderDetail detail) {
        List<OmsOrderItem> orderItemList = detail.getOrderItemList();
        Long memberId = detail.getMemberId();
        //获取 有效上级id(超过一个月上级id不分佣,视为无效上级id)
        UmsMember parent = iUmsMemberService.selectUmsMemberParentById(memberId);
        if(ObjectUtils.isEmpty(parent)){
            //插入全金额到店铺账户  (记录 和 账户)
            insertAllOrderMoneyToShopAccount(detail);
            return;
        }
        Long pid = parent.getId();
        String roleType = parent.getRoleType();
        UmsMemberConst.RoleType type = UmsMemberConst.RoleType.get(roleType);
        if (ObjectUtils.isEmpty(type)) {
            log.error("用户{},ID{},角色类型错误.角色类型:{}",parent.getNickname(),parent.getId(),roleType);
            type = UmsMemberConst.RoleType.GENERAL;
        }
        //上级所属店铺
        Long parentShopId = parent.getShopId();
        //订单所属店铺
        Long orderShopId = detail.getShopId();
        //判断 普通用户不操作佣金 或者为空
        if (Objects.equals(type, UmsMemberConst.RoleType.GENERAL)) {
            //插入全金额到店铺账户  (记录 和 账户)
            insertAllOrderMoneyToShopAccount(detail);
            return;
        }
        //获取orderitemidList
        List<Long> itemIds = orderItemList.stream().map(OmsOrderItem::getId).collect(Collectors.toList());
        //根据上获取总分销金额
        List<OmsOrderCommissionDTO>  commissions = iUmsDistributionService.getCommissionByOrderItemIds(itemIds,pid);
        BigDecimal commission = commissions.stream()
                .map(x->Arith.mul(x.getMoney(),new BigDecimal(x.getQuantity())))
                .reduce(Arith::add)
                .orElse(BigDecimal.ZERO);
        //如果佣金为空则不插入佣金记录
        if(BigDecimal.ZERO.compareTo(commission)==0){
            //插入全金额到店铺账户  (记录 和 账户)
            insertAllOrderMoneyToShopAccount(detail);
            return;
        }
        //导购 卖其他店铺 东西 不产生佣金
        if (Objects.equals(type, UmsMemberConst.RoleType.SALESMAN) &&
                !Objects.equals(orderShopId,parentShopId) ) {
            //插入全金额到店铺账户  (记录 和 账户)
            insertAllOrderMoneyToShopAccount(detail);
            return;
        }
        //高级分销人员 分享其他店铺
        if (UmsMemberConst.RoleType.isHighLevel(roleType) &&
                !Objects.equals(orderShopId,parentShopId)) {
            //除去佣金后到订单金额
            BigDecimal sub = Arith.sub(detail.getPayAmount(), commission);
            AccAccountRecord insertOfOrder = new AccAccountRecord();
            //去掉佣金入账到订单店铺
            insertOfOrder.setOrderId(detail.getId());
            insertOfOrder.setMoney(sub);
            insertOfOrder.setShopId(orderShopId);
            iAccAccountRecordService.addAccountRecordOfProduct(insertOfOrder);
            //插入现金 到平台现金账户
            iBizShopInfoService.addCashToAccount(orderShopId,sub);
        }else{
            //分销人员-->本店商品
            insertAllOrderMoneyToShopAccount(detail);
        }
        //佣金入账到平台账户记录
        AccAccountRecord insertOfCommission = new AccAccountRecord();
        insertOfCommission.setOrderId(detail.getId());
        insertOfCommission.setMoney(commission);
        insertOfCommission.setShopId(parentShopId);
        iAccAccountRecordService.addAccountRecordOfProduct(insertOfCommission);
        //插入佣金 到平台佣金账户
        iBizShopInfoService.addCommissionToAccount(parentShopId,commission);
        Map<String, BigDecimal> map = new HashMap<>();
        commissions.stream().forEach(x->{
            // orderItemId : 佣金单价
            map.put(String.valueOf(x.getOrderItemId()),x.getMoney());
        });
        //插入佣金记录 到用户
        AccCommissionRecord insert= new AccCommissionRecord();
        insert.setMemberId(parent.getId());
        insert.setOrderId(detail.getId());
        insert.setMoney(commission);
        //remark中设置详单与佣金的关系
        insert.setRemark(JSON.toJSONString(map));
        iAccCommissionRecordService.addBrokerage(insert);
        //修改用户待入佣金
        iUmsMemberService.addWaitCommission(parent.getId(),commission);
    }

    /**
     * 插入全金额到店铺账户记录
     * @param detail 订单详情
     */
    public void insertAllOrderMoneyToShopAccount(OmsOrderDetail detail){
        //插入全金额到店铺账户记录
        AccAccountRecord insertOfOrder = new AccAccountRecord();
        insertOfOrder.setOrderId(detail.getId());
        insertOfOrder.setMoney(detail.getPayAmount());
        insertOfOrder.setShopId(detail.getShopId());
        iAccAccountRecordService.addAccountRecordOfProduct(insertOfOrder);
        //插入到账户
        iBizShopInfoService.addCashToAccount(detail.getShopId(),detail.getPayAmount());
    }


    /**
     * ##########秒杀订单处理##########
     * 1.秒杀的 : 锁定库存,实际库存 减
     */
    private void seckillOrderHandle(OmsOrderDetail detail) {
        List<OmsOrderItem> orderItemList = detail.getOrderItemList();
        //锁定库存,实际库存 减
        int i = iSmsFlashPromotionSkuService.subtractStock(orderItemList.get(0));
        Checker.check(i != 1, "秒杀订单:库存扣减失败！");
        //插入入账记录
        insertAllOrderMoneyToShopAccount(detail);
    }

    /**
     * ##########团购订单处理##########
     * 1.获取要加入的团的记录id
     * 2.如果没有则创建新的团然后加入:此时 加入的是团长
     * 3.如果获取了团,则加入团,同时预定的位置释放,真实团购数量减1
     * 4.判断这个团是否成功,如果成功则直接改变订单状态待发货.
     * 5.如果这个团超时 或 库存不足 则 直接返回库存不足拼团失败,同时通知其他同类商品的团,拼团失败
     * 6.如果团购活动 正常进行,但是此团未满 则继续等待.
     */
    private void groupOrderHandle(OmsOrderDetail detail, OmsPayOrder payOrder) {
        Date now = DateUtils.getNowDate();
        //1.获取要加入的团的id
        Long groupId = payOrder.getGroupId();
        OmsOrderItem orderItem = detail.getOrderItemList().get(0);
        //组团表数据
        SmsGroupGameRecord gameRecord = null;
        //团员表数据
        SmsGroupMemberRecord groupMember = new SmsGroupMemberRecord();

        //2.若 (团购id 为 空) 则 创建新的团并把本会员设置为团长
        if (ObjectUtils.isEmpty(groupId)) {
            //获取要加入的团购活动的id
            Long gameId = detail.getGameId();
            SmsGroupGame groupGame = iSmsGroupGameService.selectSmsGroupGameBySkuId(gameId);
            Checker.check(ObjectUtils.isEmpty(groupGame), StringUtils.format("商品:{} 的团购活动已结束！", orderItem.getProductName()));
            //=================创建新的团购活动记录======================
            gameRecord = new SmsGroupGameRecord();
            gameRecord.setGroupGameId(groupGame.getId());
            gameRecord.setProductName(orderItem.getProductName());
            gameRecord.setProductPic(orderItem.getProductPic());
            gameRecord.setProductSn(orderItem.getProductSn());

            gameRecord.setShopId(detail.getShopId());
            gameRecord.setTargetMemberCount(groupGame.getTargetMemberCount());
            gameRecord.setCreateBy(payOrder.getCreateBy());
            gameRecord.setCreateTime(now);
        } else {
            //3.获取此团
            gameRecord = iSmsGroupGameRecordService.selectSmsGroupGameRecordById(groupId);
            Checker.check(ObjectUtils.isEmpty(gameRecord), "参加的组团已结束.");
            //不为空的话设置团购记录id
            groupMember.setGroupJoinRecordId(groupId);
        }
        UmsMember member = iUmsMemberService.selectUmsMemberById(payOrder.getMemberId());
        //=================会员团购记录======================
        groupMember.setGroupGameSkuId(orderItem.getGameSkuId());
        groupMember.setGroupPrice(orderItem.getProductPrice());
        groupMember.setMemberId(payOrder.getMemberId());
        groupMember.setMemberIcon(member.getHeadimgurl());
        groupMember.setOrderSn(detail.getOrderSn());
        groupMember.setProductName(orderItem.getProductName());
        groupMember.setQuantity(orderItem.getProductQuantity().intValue());
        groupMember.setSkuCode(orderItem.getProductSkuCode());
        groupMember.setSkuPic(orderItem.getProductPic());
        groupMember.setSkuSpData(orderItem.getProductSpData());
        groupMember.setCreateTime(now);
        groupMember.setCreateBy(payOrder.getCreateBy());
        groupMember.setMemberNick(member.getNickname());
        //队列处理:插入记录.判断:团购是否成功,防止多个团队同时成功时,超卖情况.
        JoinGroupHandleParam param = new JoinGroupHandleParam();
        param.setGameRecord(gameRecord);
        param.setGroupMember(groupMember);
        param.setDetail(detail);
        //发送队列: 支付成功后处理团购订单
        confirmOrderSender.sendPaySuccessOfGroup(param);
    }

    /**
     * 回收失败的组团:
     * 1.修改组团状态:拼团失败
     * 2.根据`团员表`查询`订单表` 修改状态 和 查询订单金额---退款
     * 3.修改订单状态:已作废 6 ,支付状态:已退款
     *
     * @param recordId
     */
    public void cancleGroupFailure(Long recordId) {
        log.info("组团编号:{},已超时,拼团失败！",recordId);
        //修改组团状态:拼团失败
        iSmsGroupGameRecordService.groupFailure(recordId);
        //修改订单状态:已作废 6 ,支付状态:已退款
        iOmsOrderService.setStatusOfgroupFailure(recordId);
        //根据recordId查询orderSn
        SmsGroupMemberRecord select = new SmsGroupMemberRecord();
        select.setGroupJoinRecordId(recordId);
        //查询
        List<SmsGroupMemberRecord> memberRecords = iSmsGroupMemberRecordService.selectSmsGroupMemberRecordList(select);
        //退款
        wechatOrderService.wxRefundOrder(memberRecords);
    }
}
