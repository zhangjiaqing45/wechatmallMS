package com.fante.project.business.pmsIntegralLog.service;

import com.fante.project.business.pmsIntegralLog.domain.PmsIntegralLog;
import com.fante.project.business.pmsIntegralLog.domain.PmsIntegralLogDTO;

import java.util.List;

/**
 * 积分兑换记录Service接口
 *
 * @author fante
 * @date 2020-05-01
 */
public interface IPmsIntegralLogService {
    /**
     * 查询积分兑换记录
     *
     * @param id 积分兑换记录ID
     * @return 积分兑换记录
     */
    public PmsIntegralLog selectPmsIntegralLogById(Long id);

    /**
     * 查询积分兑换记录列表
     *
     * @param pmsIntegralLog 积分兑换记录
     * @return 积分兑换记录集合
     */
    public List<PmsIntegralLog> selectPmsIntegralLogList(PmsIntegralLog pmsIntegralLog);

    /**
     * 查询积分兑换记录数量
     *
     * @param pmsIntegralLog 积分兑换记录
     * @return 结果
     */
    public int countPmsIntegralLog(PmsIntegralLog pmsIntegralLog);

    /**
     * 唯一校验
     *
     * @param pmsIntegralLog 积分兑换记录
     * @return 结果
     */
    public String checkPmsIntegralLogUnique(PmsIntegralLog pmsIntegralLog);

    /**
     * 新增积分兑换记录
     *
     * @param pmsIntegralLog 积分兑换记录
     * @return 新增积分兑换记录数量
     */
    public int insertPmsIntegralLog(PmsIntegralLog pmsIntegralLog);

    /**
     * 修改积分兑换记录
     *
     * @param pmsIntegralLog 积分兑换记录
     * @return 修改积分兑换记录数量
     */
    public int updatePmsIntegralLog(PmsIntegralLog pmsIntegralLog);

    /**
     * 批量删除积分兑换记录
     *
     * @param ids 需要删除的数据ID
     * @return 删除积分兑换记录数量
     */
    public int deletePmsIntegralLogByIds(String ids);

    /**
     * 删除积分兑换记录信息
     *
     * @param id 积分兑换记录ID
     * @return 删除积分兑换记录数量
     */
    public int deletePmsIntegralLogById(Long id);

    /**
     * (app) 获取会员积分记录
     *
     * @param log
     * @return
     */
    List<PmsIntegralLog> getMemberList(PmsIntegralLog log);

    /**
     * 积分记录与用户信息关联查询
     * @param dto
     * @return
     */
    List<PmsIntegralLogDTO> selectJoinList(PmsIntegralLogDTO dto);
}
