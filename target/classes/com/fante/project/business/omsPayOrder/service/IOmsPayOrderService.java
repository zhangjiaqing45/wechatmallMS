package com.fante.project.business.omsPayOrder.service;

import com.fante.project.business.omsPayOrder.domain.OmsPayOrder;
import java.util.List;

/**
 * 支付订单Service接口
 *
 * @author fante
 * @date 2020-04-18
 */
public interface IOmsPayOrderService {
    /**
     * 查询支付订单
     *
     * @param id 支付订单ID
     * @return 支付订单
     */
    public OmsPayOrder selectOmsPayOrderById(Long id);

    /**
     * 查询支付订单列表
     *
     * @param omsPayOrder 支付订单
     * @return 支付订单集合
     */
    public List<OmsPayOrder> selectOmsPayOrderList(OmsPayOrder omsPayOrder);

    /**
     * 查询支付订单数量
     *
     * @param omsPayOrder 支付订单
     * @return 结果
     */
    public int countOmsPayOrder(OmsPayOrder omsPayOrder);

    /**
     * 唯一校验
     *
     * @param omsPayOrder 支付订单
     * @return 结果
     */
    public String checkOmsPayOrderUnique(OmsPayOrder omsPayOrder);

    /**
     * 新增支付订单
     *
     * @param omsPayOrder 支付订单
     * @return 新增支付订单数量
     */
    public int insertOmsPayOrder(OmsPayOrder omsPayOrder);

    /**
     * 修改支付订单
     *
     * @param omsPayOrder 支付订单
     * @return 修改支付订单数量
     */
    public int updateOmsPayOrder(OmsPayOrder omsPayOrder);

    /**
     * 批量删除支付订单
     *
     * @param ids 需要删除的数据ID
     * @return 删除支付订单数量
     */
    public int deleteOmsPayOrderByIds(String ids);

    /**
     * 删除支付订单信息
     *
     * @param id 支付订单ID
     * @return 删除支付订单数量
     */
    public int deleteOmsPayOrderById(Long id);

    /**
     * 修改支付订单支付状态为已经支付
     * @param payOrderSn
     * @return
     */
    public int paySuccess(String payOrderSn);

    /**
     * 查询支付订单
     * @param payOrderSn
     * @return
     */
    public OmsPayOrder selectOmsPayOrderByOrderSn(String payOrderSn);

    /**
     * 查询支付订单
     * @param payOrderSn
     * @param status
     * @return
     */
    public OmsPayOrder selectOmsPayOrderByOrderSn(String payOrderSn, String status);
}
