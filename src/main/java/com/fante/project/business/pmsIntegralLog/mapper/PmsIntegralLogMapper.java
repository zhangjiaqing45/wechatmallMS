package com.fante.project.business.pmsIntegralLog.mapper;

import com.fante.project.business.pmsIntegralLog.domain.PmsIntegralLog;
import com.fante.project.business.pmsIntegralLog.domain.PmsIntegralLogDTO;
import com.fante.project.mapperBase.PmsIntegralLogMapperBase;

import java.util.List;

/**
 * 积分兑换记录Mapper扩展接口
 *
 * @author fante
 * @date 2020-05-01
 */

public interface PmsIntegralLogMapper extends PmsIntegralLogMapperBase {
    /**
     * 查询积分兑换记录
     */
    public List<PmsIntegralLog> getList(PmsIntegralLog log);
    /**
     * (app) 获取会员积分记录
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
