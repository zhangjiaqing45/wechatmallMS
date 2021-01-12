package com.fante.project.business.omsOrderItem.service;

import com.fante.project.api.omsOrderProcess.domain.OmsMemberOrderDetail;
import com.fante.project.business.omsOrderItem.domain.OmsOrderItem;
import java.util.List;

/**
 * 订单中所包含的商品Service接口
 *
 * @author fante
 * @date 2020-04-01
 */
public interface IOmsOrderItemService {
    /**
     * 查询订单中所包含的商品
     *
     * @param id 订单中所包含的商品ID
     * @return 订单中所包含的商品
     */
    public OmsOrderItem selectOmsOrderItemById(Long id);

    /**
     * 查询订单中所包含的商品列表
     *
     * @param omsOrderItem 订单中所包含的商品
     * @return 订单中所包含的商品集合
     */
    public List<OmsOrderItem> selectOmsOrderItemList(OmsOrderItem omsOrderItem);

    /**
     * 查询订单中所包含的商品数量
     *
     * @param omsOrderItem 订单中所包含的商品
     * @return 结果
     */
    public int countOmsOrderItem(OmsOrderItem omsOrderItem);

    /**
     * 唯一校验
     *
     * @param omsOrderItem 订单中所包含的商品
     * @return 结果
     */
    public String checkOmsOrderItemUnique(OmsOrderItem omsOrderItem);

    /**
     * 新增订单中所包含的商品
     *
     * @param omsOrderItem 订单中所包含的商品
     * @return 新增订单中所包含的商品数量
     */
    public int insertOmsOrderItem(OmsOrderItem omsOrderItem);

    /**
     * 修改订单中所包含的商品
     *
     * @param omsOrderItem 订单中所包含的商品
     * @return 修改订单中所包含的商品数量
     */
    public int updateOmsOrderItem(OmsOrderItem omsOrderItem);

    /**
     * 批量删除订单中所包含的商品
     *
     * @param ids 需要删除的数据ID
     * @return 删除订单中所包含的商品数量
     */
    public int deleteOmsOrderItemByIds(String ids);

    /**
     * 删除订单中所包含的商品信息
     *
     * @param id 订单中所包含的商品ID
     * @return 删除订单中所包含的商品数量
     */
    public int deleteOmsOrderItemById(Long id);

    /**
     * 批量插入
     * @param orderItemList
     * @return
     */
    public int batchInsert(List<OmsOrderItem> orderItemList);

    /**
     * (app) 查询订单详情 和 订单信息
     * @param orderItemId
     * @param memberId
     * @return
     */
    OmsMemberOrderDetail getOrderDetailForReturnByOrderItemId(Long orderItemId,Long memberId);
}
