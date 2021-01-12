package com.fante.project.business.accCommissionRecord.mapper;

import com.fante.project.business.accCommissionRecord.domain.AccCommissionRecord;
import com.fante.project.business.accCommissionRecord.domain.AccCommissionRecordDTO;
import com.fante.project.business.omsOrder.domain.OmsOrder;
import com.fante.project.mapperBase.AccCommissionRecordMapperBase;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * 用户佣金记录Mapper扩展接口
 *
 * @author fante
 * @date 2020-05-07
 */

public interface AccCommissionRecordMapper extends AccCommissionRecordMapperBase {
    /**
     * 待入佣金--转--可提现佣金
     * @param orderId
     * @param status
     * @return
     */
    int exchangeCommissionRecordStatus(@Param("orderId") Long orderId, @Param("status")String status);
    /**
     * 核算用户待入佣金
     * @param memberId
     * @param status
     */
    BigDecimal sumBrokerage(@Param("memberId") Long memberId, @Param("status")String status);

    /**
     * 查询佣金记录 通过订单和  操作类型
     * @param orderId
     * @param operation 0:获得佣金 1:退货 2:转入余额
     * @return
     */
    AccCommissionRecord getCommissionRecordByMemberIdAndOperation(@Param("orderId")Long orderId, @Param("operation")String operation);
    /**
     * 批量处理佣金 -->待入佣金改为佣金
     * @param orderList
     * @return
     */
    int batchUpdateCommissionStatus(@Param("orderList")List<OmsOrder> orderList);
    /**
     * 根据订单查询佣金记录
     * @param ids 订单ids
     * @return
     */
    List<AccCommissionRecord> selectAccCommissionRecordListByOrderIds(@Param("ids")String[] ids);
    /**
     * 处理佣金 -->待入佣金改为佣金
     * @param id
     * @return
     */
    int updateCommissionStatus(Long id);

    /**
     * 佣金明细与用户信息关联查询
     * @param dto
     * @return
     */
    List<AccCommissionRecordDTO> selectJoinList(AccCommissionRecordDTO dto);
    /**
     *付款未发货取消订单
     * @param orderId
     * @return
     */
    int delRecourdByOrderId(Long orderId);
}
