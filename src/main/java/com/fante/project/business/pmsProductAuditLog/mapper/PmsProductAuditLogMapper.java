package com.fante.project.business.pmsProductAuditLog.mapper;

import com.fante.project.business.pmsProductAuditLog.domain.PmsProductAuditLog;
import com.fante.project.mapperBase.PmsProductAuditLogMapperBase;

import java.util.List;

/**
 * 商品审核日志Mapper扩展接口
 *
 * @author fante
 * @date 2020-03-19
 */

public interface PmsProductAuditLogMapper extends PmsProductAuditLogMapperBase {

    /**
     * 查询商品审核日志列表
     * 时间可以单方向查询
     * @param pmsProductAuditLog 商品审核日志
     * @return 商品审核日志集合
     */
    public List<PmsProductAuditLog> getProductAuditLogList(PmsProductAuditLog pmsProductAuditLog);

}
