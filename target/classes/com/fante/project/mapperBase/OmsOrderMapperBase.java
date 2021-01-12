package com.fante.project.mapperBase;

import com.fante.project.business.omsOrder.domain.OmsOrder;
import java.util.List;

/**
 * 订单Mapper基础接口
 *
 * @author fante
 * @date 2020-04-04
 */
public interface OmsOrderMapperBase {
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
    public int checkOmsOrderUnique(OmsOrder omsOrder);

    /**
     * 新增订单
     *
     * @param omsOrder 订单
     * @return 结果
     */
    public int insertOmsOrder(OmsOrder omsOrder);

    /**
     * 修改订单
     *
     * @param omsOrder 订单
     * @return 结果
     */
    public int updateOmsOrder(OmsOrder omsOrder);

    /**
     * 删除订单
     *
     * @param id 订单ID
     * @return 结果
     */
    public int deleteOmsOrderById(Long id);

    /**
     * 批量删除订单
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteOmsOrderByIds(String[] ids);

}
