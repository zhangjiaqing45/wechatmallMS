package com.fante.project.business.omsOrder.mapper;

import com.fante.project.api.omsIntegral.domain.OmsIntegralOrder;
import com.fante.project.api.omsOrderProcess.domain.OmsMemberOrderDetail;
import com.fante.project.business.omsOrder.domain.OmsOrder;
import com.fante.project.business.omsOrder.domain.OmsOrderDetail;
import com.fante.project.business.omsOrder.domain.SendOrderParam;
import com.fante.project.mapperBase.OmsOrderMapperBase;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单Mapper扩展接口
 *
 * @author fante
 * @date 2020-04-01
 */

public interface OmsOrderMapper extends OmsOrderMapperBase {
    /**
     * 获取订单详情 包含操作历史
     * @param id
     * @return
     */
    OmsOrderDetail selectOmsOrderDetailById(Long id);
    /**
     * 获取订单详情
     * @param id
     * @return
     */
    OmsOrderDetail getOmsOrderDetailById(Long id);

    /**
     * 批量备注
     * @param ids
     * @param remark
     * @return
     */
    int batchRemarks(@Param("ids") String[] ids, @Param("remark")String remark);

    /**
     * 批量发货
     * @param paramList
     * @return
     */
    int batchSendProducts(@Param("list") List<SendOrderParam> paramList);

    /**
     * 根据ids 和 发货状态查询订单集合
     * @param ids
     * @return
     */
    List<OmsOrderDetail> selectOrderSendDetail(@Param("ids")String[] ids,@Param("status")String status);
    /**
     * 根据ids 和 发货状态查询订单数量
     * @param ids
     * @return
     */
    List<Long> selectOrderSendFailure(@Param("ids")String[] ids);
    /**
     * 取消订单
     * @param id
     * @return
     */
    int cancleOmsOrderById(@Param("id") Long id,@Param("remark") String remark);
    /**
     * 7天内n单超过24小时未发货
     * @return
     */
    int countSevenDayNotSend(Long shopId);

    /**
     * 查询超时订单
     * @param generalTimes  普通超时时间
     * @param groupTimes  团购超时时间
     * @param seckillTimes  秒杀超时时间
     * @return
     */
    List<OmsOrderDetail> getTimeOutOrders(Long generalTimes,Long groupTimes,Long seckillTimes);
    /**
     * 前台手动取消或超时订单自动取消
     * @param id
     * @return
     */
    int cancleOrderById(Long id);
    /**
     * 根据订单支付单号查询订单详情列表
     * @param payOrderSn
     * @return
     */
    List<OmsOrderDetail> selectOrderDetailByPayOrderSn(String payOrderSn);
    /**
     * 修改状态:支付订单成功
     * @param id
     * @return
     */
    int paySuccess(Long id);
    /**
     * 团购订单改为待发货
     * @param recordId
     * @return
     */
    int setStatusOfgroupSuccess(Long recordId);
    /**
     * 团购订单改为 : INVALID("6","无效订单"),
     * @param recordId
     * @return
     */
    int setStatusOfgroupFailure(Long recordId);

    /**
     * 查询积分订单
     * @param id 订单id
     * @param orderStatus 订单状态
     * @param memberId 会员id
     * @return
     */
    List<OmsIntegralOrder> selectIntegralOrderList(@Param("id") Long id,@Param("orderStatus")String orderStatus, @Param("memberId")Long memberId);
    /**
     * (app)查询订单列表
     *
     * @param ids 订单ids
     * @return 订单集合
     */
    List<OmsMemberOrderDetail> selectOmsOrderListByStatus(@Param("ids") List<Long> ids);
    /**
     * (app)查询订单列表
     *
     * @param omsOrder 订单omsOrder
     * @return 订单集合
     */
    OmsMemberOrderDetail getOmsOrderListByStatus(OmsOrder omsOrder);

    /**
     * (app)查询订单列表
     *
     * @param omsOrder 订单
     * @return 订单集合
     */
    List<Long> selectOmsOrderListByStatusBase(OmsOrder omsOrder);
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
     * 批量关闭订单
     * @param orderList
     */
    int batchCloseOrder(@Param("orderList") List<OmsOrder> orderList);

    /**
     * 根据订单编号查询
     * @param orderSn
     * @return
     */
    OmsOrder selectOmsOrderByOrderSn(String orderSn);
    /**
     * 通过recordId查询所有到订单详情信息
     * @param recordId
     * @return
     */
    public List<OmsOrderDetail> selectDetailForAccountRecord(Long recordId);
    /**
     * 用户确认收货
     * @param order
     * @return
     */
    int memberConfirmReceiveOrder(OmsOrder order);
    /**
     * 自动批量确认收货
     * @param ids
     * @return
     */
    int autoConfirmReceiveOrder(@Param("ids")String[] ids);
    /**
     * 查询所有可自动确认订单的订单
     * @param receiveTime
     * @return
     */
    List<OmsOrder> searchAllCanReceiveOrders(Long receiveTime);

    /**
     * 关闭订单
     * @param id
     * @return
     */
    int closeOrder(Long id);
    /**
     * 修改订单状态:等待团购组团
     * @param id
     * @return
     */
    int paySuccessForGroup(Long id);
}
