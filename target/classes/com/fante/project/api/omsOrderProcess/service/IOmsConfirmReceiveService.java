package com.fante.project.api.omsOrderProcess.service;

import com.fante.common.business.enums.OrderConst;
import com.fante.project.business.omsOrder.domain.OmsOrder;
import com.fante.project.business.omsOrder.service.IOmsOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author ftnet
 * @Description IOmsConfirmReceiveService
 * @CreatTime 2020/5/9
 */
@Service
public class IOmsConfirmReceiveService {
    @Autowired
    IOmsOrderService iOmsOrderService;
    /**
     * 确认收货
     * @param orderId 订单id
     * @param memberId 用户id
     * @return
     */
    public int confirmReceiveOrder(Long orderId, Long memberId) {

        return iOmsOrderService.memberConfirmReceiveOrder(orderId,memberId);
    }
}
