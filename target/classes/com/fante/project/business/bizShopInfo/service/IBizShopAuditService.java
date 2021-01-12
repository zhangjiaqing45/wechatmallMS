package com.fante.project.business.bizShopInfo.service;

import com.fante.project.business.bizShopInfo.domain.BizShopInfo;

/**
 * @program: Fante
 * @Date: 2020/3/12 17:12
 * @Author: Mr.Z
 * @Description: 店铺审核
 */
public interface IBizShopAuditService {

    /**
     * 审核通过
     * @param bizShopInfo
     */
    public void auditPass(BizShopInfo bizShopInfo);

    /**
     * 审核拒绝
     * @param bizShopInfo
     */
    public void auditRefuse(BizShopInfo bizShopInfo);
}
