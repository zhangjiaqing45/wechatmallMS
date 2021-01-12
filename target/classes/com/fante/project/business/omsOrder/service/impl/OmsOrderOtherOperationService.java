package com.fante.project.business.omsOrder.service.impl;

import com.fante.common.utils.StringUtils;
import com.fante.project.api.omsOrderProcess.service.OmsOrderHandleAfterPaySuccessService;
import com.fante.project.api.setting.OrderStting;
import com.fante.project.business.accCommissionRecord.service.IAccCommissionRecordService;
import com.fante.project.business.omsOrder.domain.OmsOrder;
import com.fante.project.business.omsOrder.service.IOmsOrderService;
import com.fante.project.business.smsGroupMemberRecord.domain.SmsGroupMemberRecord;
import com.fante.project.business.smsGroupMemberRecord.service.ISmsGroupMemberRecordService;
import com.fante.project.business.umsMember.service.IUmsMemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * 订单Service业务层处理
 *
 * @author fante
 * @date 2020-04-01
 */
@Service
public class OmsOrderOtherOperationService {

    private static Logger log = LoggerFactory.getLogger(OmsOrderOtherOperationService.class);

    /**
     * 订单
     */
    @Autowired
    private IOmsOrderService iOmsOrderService;
    /**
     * 佣金记录表
     */
    @Autowired
    private IAccCommissionRecordService iAccCommissionRecordService;
    /**
     * 会员Service接口
     */
    @Autowired
    private IUmsMemberService iUmsMemberService;
    /**
     * 订单设置
     */
    @Autowired
    private OrderStting orderStting;
    /**
     * 订单支付成功后操作订单信息
     */
    @Autowired
    private OmsOrderHandleAfterPaySuccessService omsOrderHandleAfterPaySuccessService;
    /**
     * 团购记录人员Service接口
     */
    @Autowired
    private ISmsGroupMemberRecordService iSmsGroupMemberRecordService;

    /**
     * 查询并关闭过期订单
     */
    public void autoCloseTimeOutOrder(){
        log.info("自动扫描过期未确认收货订单...");
        Long autoCloseOrderTime = orderStting.getAutoCloseOrderTime();
        //查询所有可用自动关闭的订单
        List<OmsOrder> orderList =  iOmsOrderService.searchAllCanCloseOrders(autoCloseOrderTime);
        if(ObjectUtils.isEmpty(orderList)){
            log.info("暂无过期需要自动关闭的订单");
            return;
        }
        //批量关闭订单
        log.info("开始自动关闭订单操作流程...");
        orderList.forEach(order->{
            try{
                iOmsOrderService.closeOrder(order);
                log.info("处理订单成功,ID:{}",order.getId());
            }catch(Exception e){
                log.info("处理订单失败,ID:{}.错误信息:{}",order.getId(),e.getMessage());
            }
        });
    }
    /**
     * 扫描超过时间自动确认收货
     */
    public void autoConfirmReceiveTimeOutOrder() {
        log.info("自动扫描过期未确认收货订单...");
        //获取自动收货时间
        Long receiveTime = orderStting.getAutoConfirmDay();
        //查询所有可用自动关闭的订单
        List<OmsOrder> orderList =  iOmsOrderService.searchAllCanReceiveOrders(receiveTime);
        if(ObjectUtils.isEmpty(orderList)){
            log.info("暂无自动确认收货的订单");
            return;
        }
        //批量确认收货订单
        String orderIds = orderList.stream()
                .map(x -> String.valueOf(x.getId()))
                .reduce((a, b) -> a + "," + b)
                .orElse(StringUtils.EMPTY);
        log.info("扫描到可以执行自动确认收货的订单ID:[{}]",orderIds);
        log.info("开始自动确认收货操作...");
        int i = iOmsOrderService.autoConfirmReceiveOrder(orderIds);
        log.info("共操作:{}个订单",i);
        log.info("自动确认收货订单流程处理结束");
    }

    /**
     * 扫描团购订单过期取消返回资金
     */
    public void autoCancelTimeOutGroupOrderOrder() {
        //获取所有团购失败未取消订单
        List<Long> recordIds = iSmsGroupMemberRecordService.selectTimeOutGroupRecord();
        if(ObjectUtils.isEmpty(recordIds)){
            log.info("暂无过期未取消团购订单");
            return;
        }
        log.info("开始自动取消过期未取消团购订单...");
        recordIds.forEach(id->{
            //回收失败的组团
            omsOrderHandleAfterPaySuccessService.cancleGroupFailure(id);
        });
        log.info("自动取消过期未取消团购订单流程处理结束.");
    }
}
