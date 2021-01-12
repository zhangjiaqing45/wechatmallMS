package com.fante.project.business.accBalanceRecord.mapper;

import com.fante.project.business.accBalanceRecord.domain.AccBalanceRecordDTO;
import com.fante.project.mapperBase.AccBalanceRecordMapperBase;

import java.util.List;

/**
 * 用户余额记录Mapper扩展接口
 *
 * @author fante
 * @date 2020-05-07
 */

public interface AccBalanceRecordMapper extends AccBalanceRecordMapperBase {

    /**
     * 现金明细与用户信息关联查询
     * @param dto
     * @return
     */
    List<AccBalanceRecordDTO> selectJoinList(AccBalanceRecordDTO dto);

}
