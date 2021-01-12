package com.fante.project.business.accAccountRecord.mapper;

import com.fante.project.business.accAccountRecord.domain.AccAccountRecord;
import com.fante.project.business.accAccountRecord.domain.AccAccountRecordDTO;
import com.fante.project.mapperBase.AccAccountRecordMapperBase;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * 账户出入账记录Mapper扩展接口
 *
 * @author fante
 * @date 2020-05-07
 */

public interface AccAccountRecordMapper extends AccAccountRecordMapperBase {
    /**
     *核算平台欠我多少钱type = 1 或者 核算我欠合伙人等多少钱 type=0
     */
    BigDecimal sumAccountMoney(@Param("shopId") Long shopId,@Param("type")String type);
    /**
     * 根据订单 id 和 操作状态 获取唯一记录
     * @param orderId
     * @param operation
     * @return
     */
    AccAccountRecord getRecordByOrderIdAndOperation(@Param("orderId") Long orderId, @Param("operation")String operation);

    /**
     * 批量插入入账记录
     * @param accList
     * @return
     */
    int batchInsertAcc(@Param("accList")List<AccAccountRecord> accList);

    /**
     * 账户记录与店铺信息关联查询
     * @param dto
     * @return
     */
    List<AccAccountRecordDTO> selectJoinList(AccAccountRecordDTO dto);
    /**
     *付款未发货取消订单
     * @param orderId
     * @return
     */
    int delRecordByOrderId(Long orderId);
}
