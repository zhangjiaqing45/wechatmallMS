package com.fante.project.business.pmsProductLog.service;

import com.fante.project.business.pmsProductLog.domain.PmsProductLog;
import com.fante.project.business.pmsProductLog.domain.PmsProductLogParam;

import java.util.List;

/**
 * 商品操作日志Service接口
 *
 * @author fante
 * @date 2020-03-21
 */
public interface IPmsProductLogService {
    /**
     * 查询商品操作日志
     *
     * @param id 商品操作日志ID
     * @return 商品操作日志
     */
    public PmsProductLog selectPmsProductLogById(Long id);

    /**
     * 查询商品操作日志列表
     *
     * @param pmsProductLog 商品操作日志
     * @return 商品操作日志集合
     */
    public List<PmsProductLog> selectPmsProductLogList(PmsProductLog pmsProductLog);

    /**
     * 查询商品操作日志数量
     *
     * @param pmsProductLog 商品操作日志
     * @return 结果
     */
    public int countPmsProductLog(PmsProductLog pmsProductLog);

    /**
     * 唯一校验
     *
     * @param pmsProductLog 商品操作日志
     * @return 结果
     */
    public String checkPmsProductLogUnique(PmsProductLog pmsProductLog);

    /**
     * 新增商品操作日志
     *
     * @param pmsProductLog 商品操作日志
     * @return 新增商品操作日志数量
     */
    public int insertPmsProductLog(PmsProductLog pmsProductLog);
    /**
     * 新增商品操作日志
     *
     * @param productLogParam 商品操作日志生成
     * @return 新增商品操作日志数量
     */
    public int insertPmsProductLog(PmsProductLogParam productLogParam);
    /**
     * 修改商品操作日志
     *
     * @param pmsProductLog 商品操作日志
     * @return 修改商品操作日志数量
     */
    public int updatePmsProductLog(PmsProductLog pmsProductLog);

    /**
     * 批量删除商品操作日志
     *
     * @param ids 需要删除的数据ID
     * @return 删除商品操作日志数量
     */
    public int deletePmsProductLogByIds(String ids);

    /**
     * 删除商品操作日志信息
     *
     * @param id 商品操作日志ID
     * @return 删除商品操作日志数量
     */
    public int deletePmsProductLogById(Long id);
}
