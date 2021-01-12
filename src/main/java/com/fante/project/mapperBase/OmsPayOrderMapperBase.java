package com.fante.project.mapperBase;

import com.fante.project.business.omsPayOrder.domain.OmsPayOrder;
import java.util.List;

/**
 * 支付订单Mapper基础接口
 *
 * @author fante
 * @date 2020-04-18
 */
public interface OmsPayOrderMapperBase {
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
    public int checkOmsPayOrderUnique(OmsPayOrder omsPayOrder);

    /**
     * 新增支付订单
     *
     * @param omsPayOrder 支付订单
     * @return 结果
     */
    public int insertOmsPayOrder(OmsPayOrder omsPayOrder);

    /**
     * 修改支付订单
     *
     * @param omsPayOrder 支付订单
     * @return 结果
     */
    public int updateOmsPayOrder(OmsPayOrder omsPayOrder);

    /**
     * 删除支付订单
     *
     * @param id 支付订单ID
     * @return 结果
     */
    public int deleteOmsPayOrderById(Long id);

    /**
     * 批量删除支付订单
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteOmsPayOrderByIds(String[] ids);

}
