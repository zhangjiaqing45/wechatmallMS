package com.fante.project.business.accMemberCashApply.mapper;

import com.fante.project.business.accMemberCashApply.domain.AccMemberCashApplyDTO;
import com.fante.project.mapperBase.AccMemberCashApplyMapperBase;

import java.util.List;

/**
 * 用户提现记录Mapper扩展接口
 *
 * @author fante
 * @date 2020-05-07
 */

public interface AccMemberCashApplyMapper extends AccMemberCashApplyMapperBase {

    /**
     * 提现记录与用户信息关联查询
     * @param dto
     * @return
     */
    List<AccMemberCashApplyDTO> selectJoinList(AccMemberCashApplyDTO dto);
}
