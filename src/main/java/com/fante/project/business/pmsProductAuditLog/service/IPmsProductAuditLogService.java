package com.fante.project.business.pmsProductAuditLog.service;

import com.fante.project.business.pmsProduct.domain.PmsProduct;
import com.fante.project.business.pmsProductAuditLog.domain.PmsProductAuditLog;
import java.util.List;

/**
 * 商品审核日志Service接口
 *
 * @author fante
 * @date 2020-03-19
 */
public interface IPmsProductAuditLogService {
    /**
     * 查询商品审核日志
     *
     * @param id 商品审核日志ID
     * @return 商品审核日志
     */
    public PmsProductAuditLog selectPmsProductAuditLogById(Long id);

    /**
     * 查询商品审核日志列表
     *
     * @param pmsProductAuditLog 商品审核日志
     * @return 商品审核日志集合
     */
    public List<PmsProductAuditLog> selectPmsProductAuditLogList(PmsProductAuditLog pmsProductAuditLog);

    /**
     * 查询商品审核日志数量
     *
     * @param pmsProductAuditLog 商品审核日志
     * @return 结果
     */
    public int countPmsProductAuditLog(PmsProductAuditLog pmsProductAuditLog);

    /**
     * 唯一校验
     *
     * @param pmsProductAuditLog 商品审核日志
     * @return 结果
     */
    public String checkPmsProductAuditLogUnique(PmsProductAuditLog pmsProductAuditLog);

    /**
     * 新增商品审核日志
     *
     * @param pmsProductAuditLog 商品审核日志
     * @return 新增商品审核日志数量
     */
    public int insertPmsProductAuditLog(PmsProductAuditLog pmsProductAuditLog);
    /**
     * 新增商品审核日志
     *
     * @param pmsProduct 商品审核日志
     * @return 新增商品审核日志数量
     */
    public int addPmsProductAuditLog(PmsProduct pmsProduct);
    /**
     * 修改商品审核日志
     *
     * @param pmsProduct 商品审核日志
     * @return 修改商品审核日志数量
     */
    public int productAuditLog(PmsProduct pmsProduct);
    /**
     * 修改商品审核日志
     *
     * @param pmsProductAuditLog 商品审核日志
     * @return 修改商品审核日志数量
     */
    public int updatePmsProductAuditLog(PmsProductAuditLog pmsProductAuditLog);

    /**
     * 批量删除商品审核日志
     *
     * @param ids 需要删除的数据ID
     * @return 删除商品审核日志数量
     */
    public int deletePmsProductAuditLogByIds(String ids);

    /**
     * 删除商品审核日志信息
     *
     * @param id 商品审核日志ID
     * @return 删除商品审核日志数量
     */
    public int deletePmsProductAuditLogById(Long id);
    }
