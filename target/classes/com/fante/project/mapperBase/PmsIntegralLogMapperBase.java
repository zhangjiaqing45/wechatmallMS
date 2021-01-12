package com.fante.project.mapperBase;

import com.fante.project.business.pmsIntegralLog.domain.PmsIntegralLog;
import java.util.List;

/**
 * 积分兑换记录Mapper基础接口
 *
 * @author fante
 * @date 2020-05-01
 */
public interface PmsIntegralLogMapperBase {
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
    public int checkPmsIntegralLogUnique(PmsIntegralLog pmsIntegralLog);

    /**
     * 新增积分兑换记录
     *
     * @param pmsIntegralLog 积分兑换记录
     * @return 结果
     */
    public int insertPmsIntegralLog(PmsIntegralLog pmsIntegralLog);

    /**
     * 修改积分兑换记录
     *
     * @param pmsIntegralLog 积分兑换记录
     * @return 结果
     */
    public int updatePmsIntegralLog(PmsIntegralLog pmsIntegralLog);

    /**
     * 删除积分兑换记录
     *
     * @param id 积分兑换记录ID
     * @return 结果
     */
    public int deletePmsIntegralLogById(Long id);

    /**
     * 批量删除积分兑换记录
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePmsIntegralLogByIds(String[] ids);

}
