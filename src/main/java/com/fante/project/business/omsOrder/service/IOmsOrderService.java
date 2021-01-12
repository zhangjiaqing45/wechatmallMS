package com.fante.project.business.omsOrder.service;

import com.fante.project.api.omsIntegral.domain.OmsIntegralOrder;
import com.fante.project.api.omsOrderProcess.domain.OmsMemberOrderDetail;
import com.fante.project.business.omsOrder.domain.OmsOrder;
import com.fante.project.business.omsOrder.domain.OmsOrderDetail;
import com.fante.project.business.omsOrder.domain.RemarkParam;
import com.fante.project.business.omsOrder.domain.SendOrderParam;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 订单Service接口
 *
 * @author fante
 * @date 2020-04-01
 */
public interface IOmsOrderService {
    /**
     * 查询订单
     *
     * @param id 订单ID
     * @return 订单
     */
    public OmsOrder selectOmsOrderById(Long id);

    /**
     * 查询订单列表
     *
     * @param omsOrder 订单
     * @return 订单集合
     */
    public List<OmsOrder> selectOmsOrderList(OmsOrder omsOrder);
    /**
     * (app)查询订单列表
     *
     * @param ids 订单ids
     * @return 订单集合
     */
    public List<OmsMemberOrderDetail> selectOmsOrderListByStatus(List<Long> ids);
    /**
     * (app)查询订单列表 base
     *
     * @param omsOrder 订单
     * @return 订单集合
     */
    public List<Long> selectOmsOrderListByStatusBase(OmsOrder omsOrder);
    /**
     * (app)查询订单列表
     *
     * @param omsOrder 订单
     * @return 订单集合
     */
    public OmsMemberOrderDetail getOmsOrderListByStatus(OmsOrder omsOrder);
    /**
     * 查询订单数量
     *
     * @param omsOrder 订单
     * @return 结果
     */
    public int countOmsOrder(OmsOrder omsOrder);

    /**
     * 唯一校验
     *
     * @param omsOrder 订单
     * @return 结果
     */
    public String checkOmsOrderUnique(OmsOrder omsOrder);

    /**
     * 新增订单
     *
     * @param omsOrder 订单
     * @return 新增订单数量
     */
    public int insertOmsOrder(OmsOrder omsOrder);

    /**
     * 修改订单
     *
     * @param omsOrder 订单
     * @return 修改订单数量
     */
    public int updateOmsOrder(OmsOrder omsOrder);

    /**
     * 基础修改
     * @param omsOrder
     * @return
     */
    public int updateBaseOmsOrder(OmsOrder omsOrder);

    /**
     * 批量删除订单
     *
     * @param ids 需要删除的数据ID
     * @return 删除订单数量
     */
    public int deleteOmsOrderByIds(String ids);

    /**
     * 删除订单信息
     *
     * @param id 订单ID
     * @return 删除订单数量
     */
    public int deleteOmsOrderById(Long id);
    /**
     * (app)会员删除订单信息
     *
     * @param id 订单ID
     * @return 删除订单数量
     */
    public int memberDeleteOmsOrderById(Long id,Long memberId);
    /**
     * 获取订单详情
     * @param id
     * @return
     */
    public OmsOrderDetail selectOmsOrderDetailById(Long id);
    /**
     * 获取订单详情
     * @param id
     * @return
     */
    public OmsOrderDetail getOmsOrderDetailById(Long id);
    /**
     * 批量备注
     */
    public int batchRemarks(RemarkParam param);

    /**
     * 发送订单需要的信息
     * @param ids
     * @return
     */
    public List<OmsOrderDetail> selectOrderSendDetail(String ids);

    /**
     * 批量发货
     * @param paramList
     * @return
     */
    public int batchSendProducts( List<SendOrderParam> paramList);

    /**
     * 取消订单
     * @param id
     * @return
     */
    public int cancleOmsOrderById(Long id);

    /**
     * 7天内n单超过24小时未发货
     * @return
     */
    public int countSevenDayNotSend(Long shopId);

    /**
     * 根据订单支付单号查询订单详情列表
     * @param payOrderSn
     * @return
     */
    public List<OmsOrderDetail> selectOrderDetailByPayOrderSn(String payOrderSn);

    /**
     * 修改状态:支付订单成功
     * @param id
     * @return
     */
    public int paySuccess(Long id);

    /**
     * 团购订单改为待发货
     * @param recordId
     * @return
     */
    public int setStatusOfgroupSuccess(Long recordId);
    /**
     * 团购订单改为 : INVALID("6","无效订单"),
     * @param recordId
     * @return
     */
    public int setStatusOfgroupFailure(Long recordId);
    /**
     * 通过recordId查询所有到订单详情信息
     * @param recordId
     * @return
     */
    public List<OmsOrderDetail> selectDetailForAccountRecord(Long recordId);

    /**
     * 查询积分订单
     * @param orderStatus
     * @param tokenUserId
     * @return
     */
    List<OmsIntegralOrder> selectIntegralOrderList(String orderStatus, Long tokenUserId);
    /**
     * 查询积分订单通过id
     * @param id
     * @param tokenUserId
     * @return
     */
    OmsIntegralOrder selectIntegralOrderById(Long id, Long tokenUserId);

    /**
     * 更新评论时间
     * 参数 订单详情id
     * 说明: 设置订单评价的时间 = 订单详情第一次评价时的时间
     * @param orderItemId
     * @return
     */
    int updateOmsOrderCommentTime(Long orderItemId);

    /**
     * 查询所有可用自动关闭的订单
     * @param autoCloseOrderTime
     * @return
     */
    List<OmsOrder> searchAllCanCloseOrders(Long autoCloseOrderTime);

    /**
     * 关闭订单
     * @param order
     */
    @Transactional(rollbackFor = Exception.class)
    void closeOrder(OmsOrder order);

    /**
     * 用户确认收货
     * @param orderId
     * @param memberId
     * @return
     */
    int memberConfirmReceiveOrder(Long orderId, Long memberId);
    /**
     * 自动确认收货
     * @param ids
     * @return
     */
    public int autoConfirmReceiveOrder(String ids);

    /**
     * 查询所有可自动确认订单的订单
     * @param receiveTime
     * @return
     */
    public List<OmsOrder> searchAllCanReceiveOrders(Long receiveTime);


    /**
     * 根据订单编号查询
     * @param orderSn
     * @return
     */
    OmsOrder selectOmsOrderByOrderSn(String orderSn);

    /**
     * 修改订单状态:等待团购组团
     * @param id
     * @return
     */
    int paySuccessForGroup(Long id);
}
