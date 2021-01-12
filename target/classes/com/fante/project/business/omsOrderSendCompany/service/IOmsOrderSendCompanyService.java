package com.fante.project.business.omsOrderSendCompany.service;

import com.fante.project.business.omsOrderSendCompany.domain.OmsOrderSendCompany;
import java.util.List;

/**
 * 物流公司Service接口
 *
 * @author fante
 * @date 2020-04-02
 */
public interface IOmsOrderSendCompanyService {
    /**
     * 查询物流公司
     *
     * @param id 物流公司ID
     * @return 物流公司
     */
    public OmsOrderSendCompany selectOmsOrderSendCompanyById(Long id);

    /**
     * 查询物流公司列表
     *
     * @param omsOrderSendCompany 物流公司
     * @return 物流公司集合
     */
    public List<OmsOrderSendCompany> selectOmsOrderSendCompanyList(OmsOrderSendCompany omsOrderSendCompany);

    /**
     * 查询物流公司数量
     *
     * @param omsOrderSendCompany 物流公司
     * @return 结果
     */
    public int countOmsOrderSendCompany(OmsOrderSendCompany omsOrderSendCompany);

    /**
     * 唯一校验
     *
     * @param omsOrderSendCompany 物流公司
     * @return 结果
     */
    public String checkOmsOrderSendCompanyUnique(OmsOrderSendCompany omsOrderSendCompany);

    /**
     * 新增物流公司
     *
     * @param omsOrderSendCompany 物流公司
     * @return 新增物流公司数量
     */
    public int insertOmsOrderSendCompany(OmsOrderSendCompany omsOrderSendCompany);

    /**
     * 修改物流公司
     *
     * @param omsOrderSendCompany 物流公司
     * @return 修改物流公司数量
     */
    public int updateOmsOrderSendCompany(OmsOrderSendCompany omsOrderSendCompany);

    /**
     * 批量删除物流公司
     *
     * @param ids 需要删除的数据ID
     * @return 删除物流公司数量
     */
    public int deleteOmsOrderSendCompanyByIds(String ids);

    /**
     * 删除物流公司信息
     *
     * @param id 物流公司ID
     * @return 删除物流公司数量
     */
    public int deleteOmsOrderSendCompanyById(Long id);
    }
