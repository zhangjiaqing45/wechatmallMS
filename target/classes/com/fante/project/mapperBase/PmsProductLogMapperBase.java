package com.fante.project.mapperBase;

import com.fante.project.business.pmsProductLog.domain.PmsProductLog;
import java.util.List;

/**
 * 商品操作日志Mapper基础接口
 *
 * @author fante
 * @date 2020-03-21
 */
public interface PmsProductLogMapperBase {
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
    public int checkPmsProductLogUnique(PmsProductLog pmsProductLog);

    /**
     * 新增商品操作日志
     *
     * @param pmsProductLog 商品操作日志
     * @return 结果
     */
    public int insertPmsProductLog(PmsProductLog pmsProductLog);

    /**
     * 修改商品操作日志
     *
     * @param pmsProductLog 商品操作日志
     * @return 结果
     */
    public int updatePmsProductLog(PmsProductLog pmsProductLog);

    /**
     * 删除商品操作日志
     *
     * @param id 商品操作日志ID
     * @return 结果
     */
    public int deletePmsProductLogById(Long id);

    /**
     * 批量删除商品操作日志
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePmsProductLogByIds(String[] ids);

}
