package com.fante.project.mapperBase;

import com.fante.project.business.omsOrderOperateHistory.domain.OmsOrderOperateHistory;
import java.util.List;

/**
 * 订单操作历史记录Mapper基础接口
 *
 * @author fante
 * @date 2020-04-02
 */
public interface OmsOrderOperateHistoryMapperBase {
    /**
     * 查询订单操作历史记录
     *
     * @param id 订单操作历史记录ID
     * @return 订单操作历史记录
     */
    public OmsOrderOperateHistory selectOmsOrderOperateHistoryById(Long id);

    /**
     * 查询订单操作历史记录列表
     *
     * @param omsOrderOperateHistory 订单操作历史记录
     * @return 订单操作历史记录集合
     */
    public List<OmsOrderOperateHistory> selectOmsOrderOperateHistoryList(OmsOrderOperateHistory omsOrderOperateHistory);

    /**
     * 查询订单操作历史记录数量
     *
     * @param omsOrderOperateHistory 订单操作历史记录
     * @return 结果
     */
    public int countOmsOrderOperateHistory(OmsOrderOperateHistory omsOrderOperateHistory);

    /**
     * 唯一校验
     *
     * @param omsOrderOperateHistory 订单操作历史记录
     * @return 结果
     */
    public int checkOmsOrderOperateHistoryUnique(OmsOrderOperateHistory omsOrderOperateHistory);

    /**
     * 新增订单操作历史记录
     *
     * @param omsOrderOperateHistory 订单操作历史记录
     * @return 结果
     */
    public int insertOmsOrderOperateHistory(OmsOrderOperateHistory omsOrderOperateHistory);

    /**
     * 修改订单操作历史记录
     *
     * @param omsOrderOperateHistory 订单操作历史记录
     * @return 结果
     */
    public int updateOmsOrderOperateHistory(OmsOrderOperateHistory omsOrderOperateHistory);

    /**
     * 删除订单操作历史记录
     *
     * @param id 订单操作历史记录ID
     * @return 结果
     */
    public int deleteOmsOrderOperateHistoryById(Long id);

    /**
     * 批量删除订单操作历史记录
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteOmsOrderOperateHistoryByIds(String[] ids);

}
