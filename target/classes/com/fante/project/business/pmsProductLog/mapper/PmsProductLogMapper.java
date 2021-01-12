package com.fante.project.business.pmsProductLog.mapper;

import com.fante.project.business.pmsProductLog.domain.PmsProductLog;
import com.fante.project.mapperBase.PmsProductLogMapperBase;

import java.util.List;

/**
 * 商品操作日志Mapper扩展接口
 *
 * @author fante
 * @date 2020-03-21
 */

public interface PmsProductLogMapper extends PmsProductLogMapperBase {
    /**
     * 查询商品操作日志列表
     *
     * @param pmsProductLog 商品操作日志
     * @return 商品操作日志集合
     */
    public List<PmsProductLog> getPmsProductLogList(PmsProductLog pmsProductLog);
}
