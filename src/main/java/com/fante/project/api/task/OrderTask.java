package com.fante.project.api.task;

import com.fante.common.utils.DateUtils;
import com.fante.project.business.omsOrder.service.IOmsOrderService;
import com.fante.project.business.omsOrder.service.impl.OmsCancelOrderOperationService;
import com.fante.project.business.omsOrder.service.impl.OmsCancelOrderService;
import com.fante.project.business.omsOrder.service.impl.OmsOrderOtherOperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ftnet
 * @Description OrderTask
 * @CreatTime 2020/4/17
 */
@Component("OrderTask")
public class OrderTask {
    private static Logger log = LoggerFactory.getLogger(OrderTask.class);
    /**
     * 自动取消订单
     */
    @Autowired
    private OmsCancelOrderOperationService cancelOrderService;
    /**
     * 自动关闭订单
     */
    @Autowired
    private OmsOrderOtherOperationService omsOrderOtherOperationService;

    /**
     * 自动取消未支付过期订单
     * 每10分钟
     */
    public void scannerTimeOutCancelOrder() {
        log.info("------------------------------------------------------");
        log.info("|---------------自动取消未支付过期订单-----------------|");
        log.info("|---------------"+DateUtils.getTime()+"------------------|");
        log.info("------------------------------------------------------");
        cancelOrderService.autoCancelTimeOutOrder();
    }
    /**
     * 每天扫描一次
     * 凌晨1点
     * 定时扫描订单超过规定时间 关闭订单
     * 1.订单表 关闭订单
     * 2.插入佣金记录表状态 由 0 -> 1
     * 3.账户信息表 待入佣金 ->转入佣金
     */
    public void scannerTimeOutOrder() {
        log.info("------------------------------------------------------");
        log.info("|-----------------超时自动关闭订单---------------------|");
        log.info("|---------------"+DateUtils.getTime()+"------------------|");
        log.info("------------------------------------------------------");
        omsOrderOtherOperationService.autoCloseTimeOutOrder();
    }
    /**
     * 每天扫描一次
     * 凌晨2点
     * 扫描超过时间自动确认收货
     * 1.订单表 关闭订单
     * 2.插入佣金记录表状态 由 0 -> 1
     * 3.账户信息表 待入佣金 ->转入佣金
     */
    public void scannerTimeOutConfirmReceiveOrder() {
        log.info("------------------------------------------------------");
        log.info("|-----------------超时自动确认收货---------------------|");
        log.info("|---------------"+DateUtils.getTime()+"------------------|");
        log.info("------------------------------------------------------");
        omsOrderOtherOperationService.autoConfirmReceiveTimeOutOrder();
    }

    /**
     * 每天扫描一次
     * 凌晨3点
     * 扫描团购订单过期取消返回资金
     * 1.查询过期但未自动取消的团
     * 2.取消
     */
    public void scannerTimeOutGroupOrder() {
        log.info("------------------------------------------------------");
        log.info("|-----------------超时自动团购失败---------------------|");
        log.info("|---------------"+DateUtils.getTime()+"------------------|");
        log.info("------------------------------------------------------");
        omsOrderOtherOperationService.autoCancelTimeOutGroupOrderOrder();
    }
}
