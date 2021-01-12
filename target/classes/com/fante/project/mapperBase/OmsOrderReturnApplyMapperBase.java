package com.fante.project.mapperBase;

import com.fante.project.business.omsOrderReturnApply.domain.OmsOrderReturnApply;
import java.util.List;

/**
 * 订单退货申请Mapper基础接口
 *
 * @author fante
 * @date 2020-05-09
 */
public interface OmsOrderReturnApplyMapperBase {
    /**
     * 查询订单退货申请
     *
     * @param id 订单退货申请ID
     * @return 订单退货申请
     */
    public OmsOrderReturnApply selectOmsOrderReturnApplyById(Long id);

    /**
     * 查询订单退货申请列表
     *
     * @param omsOrderReturnApply 订单退货申请
     * @return 订单退货申请集合
     */
    public List<OmsOrderReturnApply> selectOmsOrderReturnApplyList(OmsOrderReturnApply omsOrderReturnApply);

    /**
     * 查询订单退货申请数量
     *
     * @param omsOrderReturnApply 订单退货申请
     * @return 结果
     */
    public int countOmsOrderReturnApply(OmsOrderReturnApply omsOrderReturnApply);

    /**
     * 唯一校验
     *
     * @param omsOrderReturnApply 订单退货申请
     * @return 结果
     */
    public int checkOmsOrderReturnApplyUnique(OmsOrderReturnApply omsOrderReturnApply);

    /**
     * 新增订单退货申请
     *
     * @param omsOrderReturnApply 订单退货申请
     * @return 结果
     */
    public int insertOmsOrderReturnApply(OmsOrderReturnApply omsOrderReturnApply);

    /**
     * 修改订单退货申请
     *
     * @param omsOrderReturnApply 订单退货申请
     * @return 结果
     */
    public int updateOmsOrderReturnApply(OmsOrderReturnApply omsOrderReturnApply);

    /**
     * 删除订单退货申请
     *
     * @param id 订单退货申请ID
     * @return 结果
     */
    public int deleteOmsOrderReturnApplyById(Long id);

    /**
     * 批量删除订单退货申请
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteOmsOrderReturnApplyByIds(String[] ids);

}
