package com.fante.project.api.omsOrderProcess.service;

import com.fante.project.api.omsOrderProcess.domain.OmsMemberOrderDetail;
import com.fante.project.business.omsOrder.domain.OmsOrder;

import java.util.List;
import java.util.Map;

/**
 * 订单Service接口
 *
 * @author fante
 * @date 2020-04-01
 */
public interface IOmsOrderOperationService {


    /**
     * 根据订单状态查询会员订单
     *
     * @param status
     * @return
     */
    List<Long>  selectOmsOrderListByStatusBase(String status, Long memberId);
    /**
     * 根据订单状态查询会员订单
     *
     * @param ids
     * @return
     */
    public List<OmsMemberOrderDetail> selectOmsOrderListByStatus(List<Long> ids);
    /**
     * (app)根据订单id删除会员订单
     *
     * @param id
     * @return
     */
    int deleteOmsOrderById(Long id,Long memberId);

    /**
     *  (app)根据订单id取消订单
     *
     * @param id
     * @return
     */
    int memberCancleOmsOrderById(Long id,Long memberId);

    /**
     * 查询订单详情
     * @param id
     * @param memberId
     * @return
     */
    OmsMemberOrderDetail getOrderDetailPageInfo(Long id, Long memberId);
}
