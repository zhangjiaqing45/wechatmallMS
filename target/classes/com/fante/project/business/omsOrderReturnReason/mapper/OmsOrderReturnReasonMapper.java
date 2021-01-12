package com.fante.project.business.omsOrderReturnReason.mapper;

import com.fante.project.mapperBase.OmsOrderReturnReasonMapperBase;

/**
 * 退货原因设置Mapper扩展接口
 *
 * @author fante
 * @date 2020-03-27
 */

public interface OmsOrderReturnReasonMapper extends OmsOrderReturnReasonMapperBase {
    /**
     * 批量删除退货原因设置
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int realDelOmsOrderReturnReasonByIds(String[] ids);
}
