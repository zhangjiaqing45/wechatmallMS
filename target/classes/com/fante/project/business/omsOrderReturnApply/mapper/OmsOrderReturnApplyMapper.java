package com.fante.project.business.omsOrderReturnApply.mapper;

import com.fante.project.business.omsOrderReturnApply.domain.OmsOrderReturnApply;
import com.fante.project.mapperBase.OmsOrderReturnApplyMapperBase;

import java.util.List;

/**
 * 订单退货申请Mapper扩展接口
 *
 * @author fante
 * @date 2020-04-09
 */

public interface OmsOrderReturnApplyMapper extends OmsOrderReturnApplyMapperBase {
    /**
     * 查询列表
     * @param omsOrderReturnApply
     * @return
     */
    List<OmsOrderReturnApply> getOmsOrderReturnApplyList(OmsOrderReturnApply omsOrderReturnApply);

    /**
     * 删除拒绝的服务单号
     * @param ids
     * @return
     */
    int realDelOmsOrderReturnApplyByIds(String[] ids);
}
