package com.fante.project.business.omsOrderOperateHistory.service;

import com.fante.project.business.omsOrderOperateHistory.domain.OmsOrderOperateHistory;
import com.fante.project.business.omsOrderOperateHistory.domain.OmsOrderOperateHistoryParam;

import java.util.List;

/**
 * 订单操作历史记录Service接口
 *
 * @author fante
 * @date 2020-04-02
 */
public interface IOmsOrderOperateHistoryService {
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
    public String checkOmsOrderOperateHistoryUnique(OmsOrderOperateHistory omsOrderOperateHistory);

    /**
     * 新增订单操作历史记录
     *
     * @param omsOrderOperateHistory 订单操作历史记录
     * @return 新增订单操作历史记录数量
     */
    public int insertOmsOrderOperateHistory(OmsOrderOperateHistory omsOrderOperateHistory);

    /**
     * 新增订单操作历史记录通过订单及操作
     *
     * @param param 订单操作历史记录
     * @return 新增订单操作历史记录数量
     */
    public int insertOmsOrderOperateHistory(OmsOrderOperateHistoryParam param);
    /**
     * 修改订单操作历史记录
     *
     * @param omsOrderOperateHistory 订单操作历史记录
     * @return 修改订单操作历史记录数量
     */
    public int updateOmsOrderOperateHistory(OmsOrderOperateHistory omsOrderOperateHistory);

    /**
     * 批量删除订单操作历史记录
     *
     * @param ids 需要删除的数据ID
     * @return 删除订单操作历史记录数量
     */
    public int deleteOmsOrderOperateHistoryByIds(String ids);

    /**
     * 删除订单操作历史记录信息
     *
     * @param id 订单操作历史记录ID
     * @return 删除订单操作历史记录数量
     */
    public int deleteOmsOrderOperateHistoryById(Long id);

    /**
     * 批量插入
     * @param operateHistoryList
     * @return
     */
    public int batchInsertOmsOrderOperateHistory(List<OmsOrderOperateHistory> operateHistoryList);
}
