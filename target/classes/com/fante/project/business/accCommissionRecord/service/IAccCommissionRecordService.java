package com.fante.project.business.accCommissionRecord.service;

import com.fante.project.business.accCommissionRecord.domain.AccCommissionRecord;
import com.fante.project.business.accCommissionRecord.domain.AccCommissionRecordDTO;
import com.fante.project.business.omsOrder.domain.OmsOrder;

import java.math.BigDecimal;
import java.util.List;

/**
 * 用户佣金记录Service接口
 *
 * @author fante
 * @date 2020-05-07
 */
public interface IAccCommissionRecordService {
    /**
     * 查询用户佣金记录
     *
     * @param id 用户佣金记录ID
     * @return 用户佣金记录
     */
    public AccCommissionRecord selectAccCommissionRecordById(Long id);

    /**
     * 查询用户佣金记录列表
     *
     * @param accCommissionRecord 用户佣金记录
     * @return 用户佣金记录集合
     */
    public List<AccCommissionRecord> selectAccCommissionRecordList(AccCommissionRecord accCommissionRecord);

    /**
     * 查询用户佣金记录数量
     *
     * @param accCommissionRecord 用户佣金记录
     * @return 结果
     */
    public int countAccCommissionRecord(AccCommissionRecord accCommissionRecord);

    /**
     * 唯一校验
     *
     * @param accCommissionRecord 用户佣金记录
     * @return 结果
     */
    public String checkAccCommissionRecordUnique(AccCommissionRecord accCommissionRecord);

    /**
     * 新增用户佣金记录
     *
     * @param accCommissionRecord 用户佣金记录
     * @return 新增用户佣金记录数量
     */
    public int insertAccCommissionRecord(AccCommissionRecord accCommissionRecord);

    /**
     * 修改用户佣金记录
     *
     * @param accCommissionRecord 用户佣金记录
     * @return 修改用户佣金记录数量
     */
    public int updateAccCommissionRecord(AccCommissionRecord accCommissionRecord);

    /**
     * 批量删除用户佣金记录
     *
     * @param ids 需要删除的数据ID
     * @return 删除用户佣金记录数量
     */
    public int deleteAccCommissionRecordByIds(String ids);

    /**
     * 删除用户佣金记录信息
     *
     * @param id 用户佣金记录ID
     * @return 删除用户佣金记录数量
     */
    public int deleteAccCommissionRecordById(Long id);

    /**
     * 修改用户待入佣金
     * 以后购买后产生佣金-->产生佣金
     */
    public int addBrokerage(AccCommissionRecord accCommissionRecord);
    /**
     * 修改用户待入佣金
     * 退货-->扣减佣金
     */
    public int subtrackBrokerage(AccCommissionRecord accCommissionRecord);
    /**
     * 修改用户待入佣金
     * 转入余额-->扣减佣金
     */
    public int exchangeBrokerageToBalance(AccCommissionRecord accCommissionRecord);

    /**
     * 核算用户待入佣金
     */
    public BigDecimal sumWaitBrokerage(Long memberId);
    /**
     * 核算用户可提现佣金
     */
    public BigDecimal sumCashBrokerage(Long memberId);

    /**
     * 查询佣金记录 通过订单和
     * @param orderId
     * @param operation 0:获得佣金 1:退货 2:转入余额
     * @return
     */
    AccCommissionRecord getCommissionRecordByMemberIdAndOperation(Long orderId, String operation);
    /**
     * 修改用户待入佣金
     * 待入佣金-->可提现佣金
     */
    public int exhcangeBrokerage(Long orderId);
    /**
     * 批量处理佣金 -->待入佣金改为佣金
     * @param orderList
     * @return
     */
    int batchUpdateCommissionStatus(List<OmsOrder> orderList);
    /**
     * 处理佣金 -->待入佣金改为佣金
     * @param order
     * @return
     */
    int updateCommissionStatus(Long order);

    /**
     * 根据订单查询佣金记录
     * @param ids 订单ids
     * @return
     */
    List<AccCommissionRecord> selectAccCommissionRecordListByOrderIds(String ids);

    /**
     * 佣金明细与用户信息关联查询
     * @param dto
     * @return
     */
    List<AccCommissionRecordDTO> selectJoinList(AccCommissionRecordDTO dto);
    /**
     *付款未发货取消订单
     * @param id
     * @return
     */
    int delRecourdByOrderId(Long id);
}
