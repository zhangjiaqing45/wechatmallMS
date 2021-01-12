package com.fante.project.api.omsOrderProcess.service;

import com.fante.common.business.enums.AccRecordConst;
import com.fante.common.utils.Arith;
import com.fante.common.utils.Checker;
import com.fante.project.api.mq.req.JoinGroupHandleParam;
import com.fante.project.api.mq.sender.CancelGroupSender;
import com.fante.project.business.accAccountRecord.domain.AccAccountRecord;
import com.fante.project.business.accAccountRecord.service.IAccAccountRecordService;
import com.fante.project.business.bizShopInfo.service.IBizShopInfoService;
import com.fante.project.business.omsOrder.service.IOmsOrderService;
import com.fante.project.business.smsGroupGame.service.ISmsGroupGameService;
import com.fante.project.business.smsGroupGameRecord.domain.SmsGroupGameRecord;
import com.fante.project.business.smsGroupGameRecord.service.ISmsGroupGameRecordService;
import com.fante.project.business.smsGroupGameSku.service.ISmsGroupGameSkuService;
import com.fante.project.business.smsGroupMemberRecord.domain.SmsGroupMemberRecord;
import com.fante.project.business.smsGroupMemberRecord.service.ISmsGroupMemberRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * 订单支付成功回掉
 *
 * @author Administrator
 */
@Service
public class OmsOrderCallBackService {
    private static Logger log = LoggerFactory.getLogger(OmsOrderCallBackService.class);
    @Autowired
    private IOmsOrderService iOmsOrderService;
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
     * 团购活动
     */
    @Autowired
    private ISmsGroupGameService iSmsGroupGameService;
    /**
     * 订单支付成功后操作订单信息
     */
    @Autowired
    private OmsOrderHandleAfterPaySuccessService omsOrderHandleAfterPaySuccessService;
    /**
     * 团购活动记录
     */
    @Autowired
    private ISmsGroupGameRecordService iSmsGroupGameRecordService;
    /**
     * 团购会员记录
     */
    @Autowired
    private ISmsGroupMemberRecordService iSmsGroupMemberRecordService;
    /**
     * 组团失效时间发送者
     */
    @Autowired
    private CancelGroupSender cancelGroupSender;
    /**
     * 团购SKU活动
     */
    @Autowired
    private ISmsGroupGameSkuService iSmsGroupGameSkuService;

    /**
     * 订单支付成功回掉
     *
     * @param payOrderSn
     * @return
     */
    public int paySuccessCallBack(String payOrderSn) {
        try {
            omsOrderHandleAfterPaySuccessService.orderHandleAfterPaySuccess(payOrderSn);
            return 1;
        } catch (Exception e) {
            log.info("支付单号:{},订单支付成功回掉异常:{}", payOrderSn, e.toString());
            return 0;
        }
    }

    /**
     * ##########[队列调用]团购订单处理##########
     * 1.获取要加入的团的记录id
     * 2.如果没有则创建新的团然后加入:此时 加入的是团长
     * 3.如果获取了团,则加入团,同时预定的位置释放,真实团购数量减1
     * 4.判断这个团是否成功,如果成功则直接改变订单状态待发货.
     * 5.如果这个团超时 或 库存不足 则 直接返回库存不足拼团失败,同时通知其他同类商品的团,拼团失败
     * 6.如果团购活动 正常进行,但是此团未满 则继续等待.
     */
    @Transactional(rollbackFor = Exception.class)
    public void paySuccessOfGroup(JoinGroupHandleParam param) {
        log.info("团购订单处理:开始");
        SmsGroupGameRecord gameRecord = param.getGameRecord();
        Long recordId = gameRecord.getId();
        SmsGroupMemberRecord groupMember = param.getGroupMember();

        //根据活动id判断活动是否过期和 判断库存是否不足 同时获取时效时长 -1:获取失败说明活动不可用 其他则为时效值 (这里不扣减库存)
        int aging = iSmsGroupGameService.validateGame(gameRecord.getGroupGameId(), groupMember.getGroupGameSkuId(), groupMember.getQuantity());
        if(aging == -1){
            log.info("库存不足");
        }
        Checker.check(aging == -1, "团购活动已结束");

        //如果没有则创建新的团然后加入:此时 加入的是团长
        //组团成功标识
        boolean groupSuccess = false;
        //创建团的标识
        boolean isCreateGroup = false;
        if (ObjectUtils.isEmpty(recordId)) {
            log.info("团购订单处理:创建新的组团.");
            isCreateGroup = true;
            //设置人数为1人
            gameRecord.setGroupMemberCount(1);
            int i = iSmsGroupGameRecordService.insertSmsGroupGameRecord(gameRecord);
            Checker.check(i != 1, "建团时发生未知错误！");
            //重新赋值
            recordId = gameRecord.getId();
        } else {
            log.info("团购订单处理:加入组团.");
            //若有要添加的组团的id 则 修改数量
            int num = iSmsGroupGameRecordService.validateAging(recordId);
            if (num == 1) {
                //加上"我"正好团购达成
                groupSuccess = true;
            }
            //预定的位置释放,真实团购人数减1
            int i = iSmsGroupGameRecordService.paySuccess(recordId);
            Checker.check(i != 1, "参团人数已满！入团失败！");
        }
        //设置组团id
        groupMember.setGroupJoinRecordId(recordId);
        //插入团购会员表
        int i = iSmsGroupMemberRecordService.insertSmsGroupMemberRecord(groupMember);
        Checker.check(i != 1, "未知错误！入团失败！");
        //创建的新的团需要加入延时过期团购
        if (isCreateGroup) {
            // 延时取消团购订单
            // 注意:这个延时取消需要比实际时间 多30s
            //      防止当支付成功但未插入数据时,团购过时会漏掉这个订单的情况
            cancelGroupSender.sendDelayMessageCancelGroup(recordId, (long) aging * 60 * 60 * 1000 + 30 * 1000);
        }
        //拼团成功
        if (groupSuccess) {
            /**
             * 团购成功:
             * 1.团购状态改为成功
             * 2.更改订单为发货
             * 3.扣减库存
             * 4.增加 成团数量
             */
            notifyGroupSuccess(recordId);
            //iSmsGroupGameSkuService.notifyGroupSuccess(recordId);
        }
    }

    /**
     * 团购成功:已经放入存储过程
     * 1.团购状态改为成功
     * 2.更改订单为发货
     * 3.扣减库存
     * 4.增加 成团数量
     */
    private void notifyGroupSuccess(Long recordId) {
        //扣减该团的所有sku库存
        //查询库存和团购活动sku 的对应关系
        SmsGroupMemberRecord memberRecord = new SmsGroupMemberRecord();
        memberRecord.setGroupJoinRecordId(recordId);
        List<SmsGroupMemberRecord> records = iSmsGroupMemberRecordService.selectSmsGroupMemberRecordList(memberRecord);
        iSmsGroupGameSkuService.subStockOfgroupSuccess(records);
        log.info("修改所有订单状态");
        //1.团购状态改为成功
        iSmsGroupGameRecordService.groupSuccess(recordId);
        //2.更改订单为待发货
        iOmsOrderService.setStatusOfgroupSuccess(recordId);
        AtomicReference<BigDecimal> payAmount = new AtomicReference<>(BigDecimal.ZERO);
        //查询所有orderDetail
        List<AccAccountRecord> accList = iOmsOrderService.selectDetailForAccountRecord(recordId).stream().map(detail -> {
            payAmount.set(Arith.add(detail.getPayAmount(),payAmount.get()));
            //插入全金额到店铺账户记录
            AccAccountRecord insertOfOrder = new AccAccountRecord();
            insertOfOrder.setOrderId(detail.getId());
            insertOfOrder.setMoney(detail.getPayAmount());
            insertOfOrder.setShopId(detail.getShopId());
            insertOfOrder.setCreateBy(String.valueOf(detail.getMemberId()));
            insertOfOrder.setCreateTime(new Date());
            //操作 订单入账
            insertOfOrder.setOperation(AccRecordConst.AccountOperation.ORDER_IN.getType());
            //金额类型 营业金额
            insertOfOrder.setType(AccRecordConst.AccountMoneyType.BASE.getType());
            //描述 用户商品购买,转入营业额
            insertOfOrder.setDescription(AccRecordConst.AccountDescribe.DESCRIBE_TURNOVER_ADD);
            return insertOfOrder;
        }).collect(Collectors.toList());
        //批量插入入账记录
        int i = iAccAccountRecordService.batchInsertAcc(accList);
        //插入到账户
        iBizShopInfoService.addCashToAccount(accList.get(0).getShopId(),payAmount.get());
        //3.增加成团数量
        iSmsGroupGameService.groupSuccess(recordId);
    }

}
