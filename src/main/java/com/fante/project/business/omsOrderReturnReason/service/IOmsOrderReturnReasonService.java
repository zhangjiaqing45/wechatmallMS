package com.fante.project.business.omsOrderReturnReason.service;

import com.fante.project.business.omsOrderReturnReason.domain.OmsOrderReturnReason;
import java.util.List;

/**
 * 退货原因设置Service接口
 *
 * @author fante
 * @date 2020-03-27
 */
public interface IOmsOrderReturnReasonService {
    /**
     * 查询退货原因设置
     *
     * @param id 退货原因设置ID
     * @return 退货原因设置
     */
    public OmsOrderReturnReason selectOmsOrderReturnReasonById(Long id);

    /**
     * 查询退货原因设置列表
     *
     * @param omsOrderReturnReason 退货原因设置
     * @return 退货原因设置集合
     */
    public List<OmsOrderReturnReason> selectOmsOrderReturnReasonList(OmsOrderReturnReason omsOrderReturnReason);

    /**
     * 查询退货原因设置数量
     *
     * @param omsOrderReturnReason 退货原因设置
     * @return 结果
     */
    public int countOmsOrderReturnReason(OmsOrderReturnReason omsOrderReturnReason);

    /**
     * 唯一校验
     *
     * @param omsOrderReturnReason 退货原因设置
     * @return 结果
     */
    public String checkOmsOrderReturnReasonUnique(OmsOrderReturnReason omsOrderReturnReason);

    /**
     * 新增退货原因设置
     *
     * @param omsOrderReturnReason 退货原因设置
     * @return 新增退货原因设置数量
     */
    public int insertOmsOrderReturnReason(OmsOrderReturnReason omsOrderReturnReason);

    /**
     * 修改退货原因设置
     *
     * @param omsOrderReturnReason 退货原因设置
     * @return 修改退货原因设置数量
     */
    public int updateOmsOrderReturnReason(OmsOrderReturnReason omsOrderReturnReason);

    /**
     * 批量删除退货原因设置
     *
     * @param ids 需要删除的数据ID
     * @return 删除退货原因设置数量
     */
    public int deleteOmsOrderReturnReasonByIds(String ids);

    /**
     * 删除退货原因设置信息
     *
     * @param id 退货原因设置ID
     * @return 删除退货原因设置数量
     */
    public int deleteOmsOrderReturnReasonById(Long id);
    }
