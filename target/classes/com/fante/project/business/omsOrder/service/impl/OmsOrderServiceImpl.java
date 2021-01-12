package com.fante.project.business.omsOrder.service.impl;

import com.fante.common.business.enums.AccRecordConst;
import com.fante.common.business.enums.OrderConst;
import com.fante.common.business.enums.SmsGroupGameConst;
import com.fante.common.constant.Constants;
import com.fante.common.utils.Checker;
import com.fante.common.utils.DateUtils;
import com.fante.common.utils.StringUtils;
import com.fante.common.utils.idgen.IdGenerator;
import com.fante.common.utils.security.ShiroUtils;
import com.fante.common.utils.text.Convert;
import com.fante.project.api.appView.service.BizShopService;
import com.fante.project.api.appView.service.UmsAddressService;
import com.fante.project.api.omsIntegral.domain.OmsIntegralOrder;
import com.fante.project.api.omsOrderProcess.domain.OmsMemberOrderDetail;
import com.fante.project.api.setting.OrderStting;
import com.fante.project.api.umsProcess.service.UmsMemberProcessService;
import com.fante.project.business.accAccountRecord.domain.AccAccountRecord;
import com.fante.project.business.accAccountRecord.service.IAccAccountRecordService;
import com.fante.project.business.accCommissionRecord.domain.AccCommissionRecord;
import com.fante.project.business.accCommissionRecord.service.IAccCommissionRecordService;
import com.fante.project.business.bizShopInfo.service.IBizShopInfoService;
import com.fante.project.business.omsOrder.domain.OmsOrder;
import com.fante.project.business.omsOrder.domain.OmsOrderDetail;
import com.fante.project.business.omsOrder.domain.RemarkParam;
import com.fante.project.business.omsOrder.domain.SendOrderParam;
import com.fante.project.business.omsOrder.mapper.OmsOrderMapper;
import com.fante.project.business.omsOrder.service.IOmsOrderService;
import com.fante.project.business.omsOrderItem.domain.OmsOrderItem;
import com.fante.project.business.omsOrderOperateHistory.domain.OmsOrderOperateHistory;
import com.fante.project.business.omsOrderOperateHistory.domain.OmsOrderOperateHistoryParam;
import com.fante.project.business.omsOrderOperateHistory.service.IOmsOrderOperateHistoryService;
import com.fante.project.business.pmsSkuStock.service.IPmsSkuStockService;
import com.fante.project.business.smsFlashPromotionSku.service.ISmsFlashPromotionSkuService;
import com.fante.project.business.umsMember.domain.UmsMember;
import com.fante.project.business.umsMember.service.IUmsMemberService;
import com.fante.project.weixin.business.service.WechatOrderService;
import com.fante.project.weixin.core.domain.OrderRefundRsp;
import com.fante.project.weixin.core.utils.WechatRedis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 订单Service业务层处理
 *
 * @author fante
 * @date 2020-04-01
 */
@Service
public class OmsOrderServiceImpl implements IOmsOrderService {

    private static Logger log = LoggerFactory.getLogger(OmsOrderServiceImpl.class);

    @Autowired
    private OmsOrderMapper omsOrderMapper;
    @Autowired
    private WechatOrderService wechatOrderService;
    /**
     * 账户出入账记录Service接口
     */
    @Autowired
    private IAccAccountRecordService iAccAccountRecordService;
    /**
     * 店铺
     */
    @Autowired
    private IBizShopInfoService iBizShopInfoService;
    /**
     * 用户佣金记录Service接口
     */
    @Autowired
    private IAccCommissionRecordService iAccCommissionRecordService;
    /**
     * 会员Service接口
     */
    @Autowired
    private IUmsMemberService iUmsMemberService;
    /**
     *会员相关处理服务
     */
    @Autowired
    private WechatRedis wechatRedis;
    /**
     * 订单设置
     */
    @Autowired
    private OrderStting orderStting;
    /**
     * 秒杀活动和sku关系表
     */
    @Autowired
    private ISmsFlashPromotionSkuService iSmsFlashPromotionSkuService;
    /**
     * 商品sku库存管理
     */
    @Autowired
    private IPmsSkuStockService iPmsSkuStockService;
    /**
     * 商品sku库存管理
     */
    @Autowired
    private OmsCancelOrderService omsCancelOrderService;

    @Autowired
    private IOmsOrderOperateHistoryService iOmsOrderOperateHistoryService;

    /**
     * 查询订单
     *
     * @param id 订单ID
     * @return 订单
     */
    @Override
    public OmsOrder selectOmsOrderById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        return omsOrderMapper.selectOmsOrderById(id);
    }

    /**
     * 查询订单列表
     *
     * @param omsOrder 订单
     * @return 订单集合
     */
    @Override
    public List<OmsOrder> selectOmsOrderList(OmsOrder omsOrder) {
        return omsOrderMapper.selectOmsOrderList(omsOrder);
    }
    /**
     * (app)查询订单列表
     *
     * @param ids 订单
     * @return 订单集合
     */
    @Override
    public List<OmsMemberOrderDetail> selectOmsOrderListByStatus(List<Long> ids ) {
        if (ObjectUtils.isEmpty(ids)) {
            return Collections.emptyList();
        }
        return omsOrderMapper.selectOmsOrderListByStatus(ids);
    }
    /**
     * (app)查询订单列表
     *
     * @param omsOrder 订单
     * @return 订单集合
     */
    @Override
    public OmsMemberOrderDetail getOmsOrderListByStatus(OmsOrder omsOrder) {
        return omsOrderMapper.getOmsOrderListByStatus(omsOrder);
    }
    /**
     * (app)查询订单列表 base
     *
     * @param omsOrder 订单
     * @return 订单集合
     */
    @Override
    public List<Long> selectOmsOrderListByStatusBase(OmsOrder omsOrder) {
        return omsOrderMapper.selectOmsOrderListByStatusBase(omsOrder);
    }

    /**
     * 查询订单数量
     *
     * @param omsOrder 订单
     * @return 结果
     */
    @Override
    public int countOmsOrder(OmsOrder omsOrder){
        return omsOrderMapper.countOmsOrder(omsOrder);
    }

    /**
     * 唯一校验
     *
     * @param omsOrder 订单
     * @return 结果
     */
    @Override
    public String checkOmsOrderUnique(OmsOrder omsOrder) {
        return omsOrderMapper.checkOmsOrderUnique(omsOrder) > 0 ? Constants.NOT_UNIQUE : Constants.UNIQUE;
    }

    /**
     * 新增订单
     *
     * @param omsOrder 订单
     * @return 新增订单数量
     */
    @Override
    public int insertOmsOrder(OmsOrder omsOrder) {
        omsOrder.setCreateTime(DateUtils.getNowDate());
        System.out.println("omsOrder.getPaymentType()="+omsOrder.getPaymentType());
        if("2".equals(omsOrder.getPaymentType())){
            omsOrder.setStatus("3");//如果是预约订单则已完成状态
        }else if("1".equals(omsOrder.getPaymentType())){
            omsOrder.setWriteOffStatus("-1");//核销码未生成
        }
        return omsOrderMapper.insertOmsOrder(omsOrder);
    }

    /**
     * 修改订单
     *
     * @param omsOrder 订单
     * @return 修改订单数量
     */
    @Override
    public int updateOmsOrder(OmsOrder omsOrder) {
        //验证
        Checker.check(ObjectUtils.isEmpty(omsOrder.getId()),"该订单不存在！");
        //validateAction(omsOrder.getId(),OrderConst.OrderActionEnum.EDIT,null);
        //设置更新人
        //omsOrder.setUpdateBy(ShiroUtils.getLoginName());
        //omsOrder.setUpdateTime(DateUtils.getNowDate());
        return updateBaseOmsOrder(omsOrder);
    }

    @Override
    public int updateBaseOmsOrder(OmsOrder omsOrder) {
        return omsOrderMapper.updateOmsOrder(omsOrder);
    }

    /**
     * 删除订单对象
     *
     * @param ids 需要删除的数据ID
     * @return 删除订单数量
     */
    @Override
    public int deleteOmsOrderByIds(String ids) {
        return omsOrderMapper.deleteOmsOrderByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除订单信息
     *
     * @param id 订单ID
     * @return 删除订单数量
     */
    @Override
    public int deleteOmsOrderById(Long id) {
        //假删除
        validateAction(id,OrderConst.OrderActionEnum.DEL,null);
        return omsOrderMapper.deleteOmsOrderByIds(Convert.toStrArray(String.valueOf(id)));
    }
    /**
     * (app)会员删除订单信息
     *
     * @param id 订单ID
     * @return 删除订单数量
     */
    @Override
    public int memberDeleteOmsOrderById(Long id,Long memberId) {
        //假删除
        validateAction(id,OrderConst.OrderActionEnum.DEL,memberId);
        return omsOrderMapper.deleteOmsOrderByIds(Convert.toStrArray(String.valueOf(id)));
    }

    /**
     * 验证操作的合法性
     */
    private OmsOrder validateAction(Long id, OrderConst.OrderActionEnum btn,Long memberId){
        OmsOrder order = omsOrderMapper.selectOmsOrderById(id);
        Checker.check(ObjectUtils.isEmpty(order),"该订单不存在！");
        System.out.println("memberId="+memberId);
        if(ObjectUtils.isEmpty(memberId)){
            //防止同一浏览器依次登录不同店铺账号,操作缓存数据情况.
            Checker.checkOp(Objects.equals(order.getShopId(), ShiroUtils.getSysUser().getDeptId()),"非本店铺订单,不可操作！");
        }else{
            //检查会员是否操作的是自己的订单
            Checker.checkOp(Objects.equals(order.getMemberId(),memberId),"只能操作自己的订单！");
        }

        String status = order.getStatus();
        //验证商品状态
        boolean exist = OrderConst.OrderActionEnum.getBtns(status).stream().anyMatch(
                item-> StringUtils.equals(item,btn.getType())
        );
        Checker.checkOp(exist,StringUtils.format("订单状态为:{},不可执行{}操作",OrderConst.OrderStatus.get(status).getDescribe(),btn.getDescribe()));
        /*插入log*/
        if(ObjectUtils.isEmpty(memberId)) {
            //memberId 为空 说明是后台操作 需要 记录log
            OmsOrderOperateHistoryParam historyParam = new OmsOrderOperateHistoryParam();
            historyParam.setAction(btn);
            historyParam.setOrder(order);
            iOmsOrderOperateHistoryService.insertOmsOrderOperateHistory(historyParam);
        }
        return order;
    }

    /**
     * 获取订单详情
     * @param id
     * @return
     */
    @Override
    public OmsOrderDetail selectOmsOrderDetailById(Long id) {
        return omsOrderMapper.selectOmsOrderDetailById(id);
    }

    /**
     * 获取订单详情
     * @param id
     * @return
     */
    @Override
    public OmsOrderDetail getOmsOrderDetailById(Long id) {
        return omsOrderMapper.getOmsOrderDetailById(id);
    }

    /**
     * 批量备注
     * @param param
     * @return
     */
    @Override
    public int batchRemarks(RemarkParam param) {
        param.validate();
        return omsOrderMapper.batchRemarks(Convert.toStrArray(param.getIds()),param.getFlag()+"@"+param.getRemark());
    }

    /**
     * 发货订单需要的信息
     * @return
     */
    @Override
    public List<OmsOrderDetail>  selectOrderSendDetail(String ids) {
        //查询待发货的订单
        return omsOrderMapper.selectOrderSendDetail(Convert.toStrArray(ids), OrderConst.OrderStatus.WAIT_SEND.getType());
    }

    /**
     * 批量发货
     * @param paramList
     * @return
     */
    @Override
    public int batchSendProducts(List<SendOrderParam> paramList) {
        //批量发货
        int count = omsOrderMapper.batchSendProducts(paramList);
        List<String> ids = paramList.stream().map(item -> String.valueOf(item.getId())).collect(Collectors.toList());
        List<Long> failureIds = new ArrayList<>();
        if(count!=paramList.size()){
            //检查发货失败的订单
            failureIds = omsOrderMapper.selectOrderSendFailure(ids.toArray(new String[0]));
        }
        //添加操作记录
        List<Long> finalFailureIds = failureIds;
        List<OmsOrderOperateHistory> operateHistoryList = paramList.stream()
                .filter(item -> !finalFailureIds.contains(item.getId()))
                .map(item -> {
                    OmsOrderOperateHistory history = new OmsOrderOperateHistory();
                    history.setOrderId(item.getId());
                    history.setCreateTime(new Date());
                    history.setCreateBy(ShiroUtils.getLoginName());
                   // history.setOrderStatus(OrderConst.OrderStatus.SENDED.getType());
                    history.setPayStatus(OrderConst.PayStatus.PAY.getType());
                    history.setSendStatus(OrderConst.SendStatus.YES.getType());
                    history.setRemark(OrderConst.OrderActionEnum.TRANSPORT.getDescribe());
                    return history;
                }).collect(Collectors.toList());
        iOmsOrderOperateHistoryService.batchInsertOmsOrderOperateHistory(operateHistoryList);
        return count;
    }

    /**
     * 取消订单(后台)
     * 说明:后台待发货订单(已经付款)取消订单,不用回归库存
     * 退全款
     * @param id
     * @return
     */
    @Override
    public int cancleOmsOrderById(Long id) {
        //验证并返回订单信息
        OmsOrder omsOrder = validateAction(id, OrderConst.OrderActionEnum.CANCLE, null);
        //如果时积分订单 返还积分 删除缓存 结束
        if( OrderConst.OrderType.INTEGRAL.getType().equals(omsOrder.getOrderType())){
            long returnSn = IdGenerator.nextId();
            int i = omsOrderMapper.cancleOmsOrderById(id,"付款未发货时取消订单.退货单号:"+returnSn);
            Checker.check(i!=1,"取消订单失败");
            i+= iUmsMemberService.addMemberIntegral(omsOrder.getMemberId(), omsOrder.getPayAmount());
            Checker.check(i!=2,"返还用户积分时错误");
            //删除缓存
            UmsMember member = iUmsMemberService.selectUmsMemberById(omsOrder.getMemberId());
            // 更新缓存
            wechatRedis.delUmsMember(member.getOpenid());
            return i;
        }
        /*//若是商品订单
        // 1.订单状态未付款 结束
        if( OrderConst.OrderStatus.WAIT_PAY.getType().equals(omsOrder.getStatus())){
            return i;
        }
        // 2.订单状态付款 则 处理佣金 处理退款 结束
        //店铺佣金记录和店铺营业额记录改为无效 用户佣金记录
        AccAccountRecord search = new  AccAccountRecord();
        search.setOrderId(id);
        List<AccAccountRecord> list = iAccAccountRecordService.selectAccAccountRecordList(search);
        Checker.check(ObjectUtils.isEmpty(list),"店铺账户记录错误取消订单失败！");
        list.forEach(x->{
            //0佣金收入 1订单金额入账
            String operationstr = x.getOperation();
            AccRecordConst.AccountOperation operation = AccRecordConst.AccountOperation.get(operationstr);
            switch (operation){
                //佣金入账
                case COMMISSION_IN:
                    //返回佣金金额
                    iBizShopInfoService.subCommissionToAccount( x.getShopId(), x.getMoney());
                    break;
                //订单入账
                case ORDER_IN:
                    //返回佣金金额
                    iBizShopInfoService.subCashToAccount( x.getShopId(), x.getMoney());
                    break;
                default:
                    Checker.check(true,"账单中记录订单类型异常");
            }
        });
        int j = iAccAccountRecordService.delRecordByOrderId(id);
        Checker.check(j!=list.size(),"取消营业额记录失败");
        //查询佣金记录
        AccCommissionRecord search1 = new  AccCommissionRecord();
        search1.setOrderId(id);
        List<AccCommissionRecord> commissionRecords = iAccCommissionRecordService.selectAccCommissionRecordList(search1);
        //如果佣金记录不为空
        if (!ObjectUtils.isEmpty(commissionRecords)){
            //店铺的佣金/营业额用户佣金
            commissionRecords.forEach(x->{
            });
            i+=iAccCommissionRecordService.delRecourdByOrderId(id);
            Checker.check(i!=3,"取消佣金记录失败");
        }
        //退全款
        OrderRefundRsp rsp = wechatOrderService.wxRefundOrder(id, String.valueOf(returnSn));
        Checker.checkOp(rsp.getStatus(),rsp.getMsg());*/
        return 0;
    }

    /**
     * 7天内n单超过24小时未发货
     * @return
     */
    @Override
    public int countSevenDayNotSend(Long shopId) {
        return omsOrderMapper.countSevenDayNotSend(shopId);
    }

    /**
     * 根据订单支付单号查询订单详情列表
     * @param payOrderSn
     * @return
     */
    @Override
    public List<OmsOrderDetail> selectOrderDetailByPayOrderSn(String payOrderSn) {
        return omsOrderMapper.selectOrderDetailByPayOrderSn(payOrderSn);
    }

    /**
     * 修改状态:支付订单成功
     * @param id
     * @return
     */
    @Override
    public int paySuccess(Long id) {
        return omsOrderMapper.paySuccess(id);
    }
    /**
     * 团购订单改为待发货
     * @param recordId
     * @return
     */
    @Override
    public int setStatusOfgroupSuccess(Long recordId) {
        return omsOrderMapper.setStatusOfgroupSuccess(recordId);
    }
    /**
     * 团购订单改为 : INVALID("6","无效订单"),
     * @param recordId
     * @return
     */
    @Override
    public int setStatusOfgroupFailure(Long recordId) {
        return omsOrderMapper.setStatusOfgroupFailure(recordId);
    }
    /**
     * 通过recordId查询所有到订单详情信息
     * @param recordId
     * @return
     */
    @Override
    public List<OmsOrderDetail> selectDetailForAccountRecord(Long recordId) {
        return omsOrderMapper.selectDetailForAccountRecord(recordId);
    }

    /**
     * 查询积分订单
     * @param orderStatus
     * @param tokenUserId
     * @return
     */
    @Override
    public List<OmsIntegralOrder> selectIntegralOrderList(String orderStatus, Long tokenUserId) {
        return omsOrderMapper.selectIntegralOrderList(null,orderStatus,tokenUserId);
    }
    /**
     * 查询积分订单通过id
     * @param id
     * @param tokenUserId
     * @return
     */
    @Override
    public OmsIntegralOrder selectIntegralOrderById(Long id, Long tokenUserId) {
        List<OmsIntegralOrder> omsIntegralOrders = omsOrderMapper.selectIntegralOrderList(id, null, tokenUserId);
        if(ObjectUtils.isEmpty(omsIntegralOrders)){
            return null;
        }
        return omsIntegralOrders.get(0);
    }
    /**
     * 更新评论时间
     * 参数 订单详情id
     * 说明: 设置订单评价的时间 = 订单详情第一次评价时的时间
     * @param orderItemId
     * @return
     */
    @Override
    public int updateOmsOrderCommentTime(Long orderItemId) {
        return omsOrderMapper.updateOmsOrderCommentTime(orderItemId);
    }
    /**
     * 查询所有可用自动关闭的订单
     * @param autoCloseOrderTime
     * @return
     */
    @Override
    public List<OmsOrder> searchAllCanCloseOrders(Long autoCloseOrderTime) {
        return omsOrderMapper.searchAllCanCloseOrders(autoCloseOrderTime);
    }

    /**
     * 批量关闭订单(不用)
     * @param order
     */
    @Override
    public void closeOrder(OmsOrder order) {
        Long orderId = order.getId();
        int i = omsOrderMapper.closeOrder(orderId);
        Checker.check(i!=1,"关闭订单时发生错误");
        //根据订单查询佣金记录
        List<AccCommissionRecord> accCommissionRecords = iAccCommissionRecordService.selectAccCommissionRecordListByOrderIds(String.valueOf(orderId));
        if(!ObjectUtils.isEmpty(accCommissionRecords)){
            AccCommissionRecord acc =  accCommissionRecords.get(0);
            //处理佣金 -->待入佣金改为佣金
            i += iAccCommissionRecordService.updateCommissionStatus(orderId);
            Checker.check(i!=2,"修改佣金记录时发生错误");
            //批量修改用户账户信息--待入佣金 转入 可提现佣金
            i += iUmsMemberService.updateCommission(acc.getMemberId(),acc.getMoney());
            Checker.check(i!=3,"用户待入佣金转入可提现佣金时发生错误");
        }
    }

    /**
     * 用户确认收货
     * @param orderId
     * @param memberId
     * @return
     */
    @Override
    public int memberConfirmReceiveOrder(Long orderId, Long memberId) {
        OmsOrder update = new OmsOrder();
        update.setMemberId(memberId);
        update.setId(orderId);
        //修改订单状态 为收货 status = 3
        update.setStatus(OrderConst.OrderStatus.RECEIVED.getType());
        return  omsOrderMapper.memberConfirmReceiveOrder(update);
    }
    /**
     * 自动确认收货
     * @param ids
     * @return
     */
    @Override
    public int autoConfirmReceiveOrder(String ids) {
        return  omsOrderMapper.autoConfirmReceiveOrder(Convert.toStrArray(ids));
    }

    /**
     * 查询所有可自动确认订单的订单
     * @param receiveTime
     * @return
     */
    @Override
    public List<OmsOrder> searchAllCanReceiveOrders(Long receiveTime){
        return  omsOrderMapper.searchAllCanReceiveOrders(receiveTime);
    }


    /**
     * 根据订单编号查询
     * @param orderSn
     * @return
     */
    @Override
    public OmsOrder selectOmsOrderByOrderSn(String orderSn) {
        if (StringUtils.isBlank(orderSn)) {
            return null;
        }
        return omsOrderMapper.selectOmsOrderByOrderSn(orderSn);
    }
    /**
     * 修改订单状态:等待团购组团
     * @param id
     * @return
     */
    @Override
    public int paySuccessForGroup(Long id) {
        return omsOrderMapper.paySuccessForGroup(id);
    }
}
