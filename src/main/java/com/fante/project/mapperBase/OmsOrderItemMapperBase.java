package com.fante.project.mapperBase;

import com.fante.project.business.omsOrderItem.domain.OmsOrderItem;
import java.util.List;

/**
 * 订单中所包含的商品Mapper基础接口
 *
 * @author fante
 * @date 2020-05-05
 */
public interface OmsOrderItemMapperBase {
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
    public int checkOmsOrderItemUnique(OmsOrderItem omsOrderItem);

    /**
     * 新增订单中所包含的商品
     *
     * @param omsOrderItem 订单中所包含的商品
     * @return 结果
     */
    public int insertOmsOrderItem(OmsOrderItem omsOrderItem);

    /**
     * 修改订单中所包含的商品
     *
     * @param omsOrderItem 订单中所包含的商品
     * @return 结果
     */
    public int updateOmsOrderItem(OmsOrderItem omsOrderItem);

    /**
     * 删除订单中所包含的商品
     *
     * @param id 订单中所包含的商品ID
     * @return 结果
     */
    public int deleteOmsOrderItemById(Long id);

    /**
     * 批量删除订单中所包含的商品
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteOmsOrderItemByIds(String[] ids);

}
