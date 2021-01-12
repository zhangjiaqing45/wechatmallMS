package com.fante.project.api.omsOrderProcess.service.impl;

import com.fante.common.business.enums.OrderConst;
import com.fante.common.utils.Checker;
import com.fante.common.utils.StringUtils;
import com.fante.project.api.omsOrderProcess.domain.OmsMemberOrderDetail;
import com.fante.project.api.omsOrderProcess.service.IOmsOrderOperationService;
import com.fante.project.business.omsOrder.domain.OmsOrder;
import com.fante.project.business.omsOrder.service.IOmsOrderService;
import com.fante.project.business.omsOrder.service.impl.OmsCancelOrderOperationService;
import com.fante.project.business.omsOrder.service.impl.OmsCancelOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;

/**
 * 订单Service业务层处理
 *
 * @author fante
 * @date 2020-04-01
 */
@Service
public class OmsOrderOperationServiceImpl implements IOmsOrderOperationService {

    private static Logger log = LoggerFactory.getLogger(OmsOrderOperationServiceImpl.class);

    @Autowired
    private IOmsOrderService iOmsOrderService;
    @Autowired
    private OmsCancelOrderOperationService cancelOrderService;

    /**
     * 根据订单状态查询会员订单
     *
     * @param status
     * @return
     */
    @Override
    public List<Long> selectOmsOrderListByStatusBase(String status, Long memberId){
        OmsOrder param = new OmsOrder();
        //状态为空则查询所有,不为空则先验证状态是否正确,然后在查询
        if (!StringUtils.isEmpty(status)) {
            OrderConst.OrderStatus orderStatus = OrderConst.OrderStatus.get(status);
            Checker.check(ObjectUtils.isEmpty(orderStatus), "选择订单状态异常！");
        }
        param.setStatus(status);
        param.setMemberId(memberId);
        return  iOmsOrderService.selectOmsOrderListByStatusBase(param);
    }
    /**
     * 根据订单状态查询会员订单
     *
     * @param ids
     * @return
     */
    @Override
    public List<OmsMemberOrderDetail> selectOmsOrderListByStatus(List<Long> ids){
        return  iOmsOrderService.selectOmsOrderListByStatus(ids);
    }

    /**
     * (app)根据订单id删除会员订单
     *
     * @param id
     * @return
     */
    @Override
    public int deleteOmsOrderById(Long id,Long memberId) {
        return iOmsOrderService.memberDeleteOmsOrderById(id,memberId);
    }

    /**
     *  (app)根据订单id取消订单
     *
     * @param id
     * @return
     */
    @Override
    public int memberCancleOmsOrderById(Long id,Long memberId) {
        return cancelOrderService.memberCancleOmsOrderById(id,memberId);
    }
    /**
     * 查询订单详情
     * 1.订单和订单详情
     * 2.地址详情
     * @param id
     * @param memberId
     * @return
     */
    @Override
    public OmsMemberOrderDetail getOrderDetailPageInfo(Long id,Long memberId) {
        OmsOrder param = new OmsOrder();
        Checker.check(ObjectUtils.isEmpty(id),"订单参数缺失");
        param.setMemberId(memberId);
        param.setId(id);
        OmsMemberOrderDetail details = iOmsOrderService.getOmsOrderListByStatus(param);
        Checker.check(ObjectUtils.isEmpty(details),"订单已删除或不存在");
        return  details;
    }
}
