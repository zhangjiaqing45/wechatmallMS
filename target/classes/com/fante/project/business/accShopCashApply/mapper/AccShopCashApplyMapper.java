package com.fante.project.business.accShopCashApply.mapper;

import com.fante.project.business.accShopCashApply.domain.AccShopCashApplyDTO;
import com.fante.project.mapperBase.AccShopCashApplyMapperBase;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 店铺提现记录Mapper扩展接口
 *
 * @author fante
 * @date 2020-05-07
 */

public interface AccShopCashApplyMapper extends AccShopCashApplyMapperBase {
    /**
     * 申请审核
     */
    public int applyAudit(@Param("id") Long id, @Param("status") String status, @Param("remark") String remark);

    /**
     * 提现记录与店铺信息关联查询
     * @param dto
     * @return
     */
    public List<AccShopCashApplyDTO> selectJoinList(AccShopCashApplyDTO dto);

}
