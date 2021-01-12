package com.fante.project.mapperBase;

import com.fante.project.business.omsOrderReturnReason.domain.OmsOrderReturnReason;
import java.util.List;

/**
 * 退货原因设置Mapper基础接口
 *
 * @author fante
 * @date 2020-05-04
 */
public interface OmsOrderReturnReasonMapperBase {
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
    public int checkOmsOrderReturnReasonUnique(OmsOrderReturnReason omsOrderReturnReason);

    /**
     * 新增退货原因设置
     *
     * @param omsOrderReturnReason 退货原因设置
     * @return 结果
     */
    public int insertOmsOrderReturnReason(OmsOrderReturnReason omsOrderReturnReason);

    /**
     * 修改退货原因设置
     *
     * @param omsOrderReturnReason 退货原因设置
     * @return 结果
     */
    public int updateOmsOrderReturnReason(OmsOrderReturnReason omsOrderReturnReason);

    /**
     * 删除退货原因设置
     *
     * @param id 退货原因设置ID
     * @return 结果
     */
    public int deleteOmsOrderReturnReasonById(Long id);

    /**
     * 批量删除退货原因设置
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteOmsOrderReturnReasonByIds(String[] ids);

}
