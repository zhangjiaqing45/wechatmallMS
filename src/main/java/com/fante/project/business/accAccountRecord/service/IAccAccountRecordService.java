package com.fante.project.business.accAccountRecord.service;

import com.fante.project.business.accAccountRecord.domain.AccAccountRecord;
import com.fante.project.business.accAccountRecord.domain.AccAccountRecordDTO;

import java.math.BigDecimal;
import java.util.List;

/**
 * 账户出入账记录Service接口
 *
 * @author fante
 * @date 2020-05-07
 */
public interface IAccAccountRecordService {
    /**
     * 查询账户出入账记录
     *
     * @param id 账户出入账记录ID
     * @return 账户出入账记录
     */
    public AccAccountRecord selectAccAccountRecordById(Long id);

    /**
     * 查询账户出入账记录列表
     *
     * @param accAccountRecord 账户出入账记录
     * @return 账户出入账记录集合
     */
    public List<AccAccountRecord> selectAccAccountRecordList(AccAccountRecord accAccountRecord);

    /**
     * 查询账户出入账记录数量
     *
     * @param accAccountRecord 账户出入账记录
     * @return 结果
     */
    public int countAccAccountRecord(AccAccountRecord accAccountRecord);

    /**
     * 唯一校验
     *
     * @param accAccountRecord 账户出入账记录
     * @return 结果
     */
    public String checkAccAccountRecordUnique(AccAccountRecord accAccountRecord);

    /**
     * 新增账户出入账记录
     *
     * @param accAccountRecord 账户出入账记录
     * @return 新增账户出入账记录数量
     */
    public int insertAccAccountRecord(AccAccountRecord accAccountRecord);

    /**
     * 修改账户出入账记录
     *
     * @param accAccountRecord 账户出入账记录
     * @return 修改账户出入账记录数量
     */
    public int updateAccAccountRecord(AccAccountRecord accAccountRecord);

    /**
     * 批量删除账户出入账记录
     *
     * @param ids 需要删除的数据ID
     * @return 删除账户出入账记录数量
     */
    public int deleteAccAccountRecordByIds(String ids);

    /**
     * 删除账户出入账记录信息
     *
     * @param id 账户出入账记录ID
     * @return 删除账户出入账记录数量
     */
    public int deleteAccAccountRecordById(Long id);

    /**
     * 操作:
     * 0佣金收入
     */
    public int addAccountRecordOfCommission(AccAccountRecord accAccountRecord);
    /**
     * 操作:
     *  1订单金额入账
     */
    public int addAccountRecordOfProduct(AccAccountRecord accAccountRecord);
    /**
     * 操作:
     * 2商品退货支出
     */
    public int subAccountRecordOfProduct(AccAccountRecord accAccountRecord);
    /**
     * 操作:
     * 3退货佣金出账
     */
    public int subAccountRecordOfCommission(AccAccountRecord accAccountRecord);
    /**
     * 操作:
     * 4店铺提现出账
     */
    public int subAccountRecordOfCash(AccAccountRecord accAccountRecord);
    /**
     * 操作:
     * 5用户提现出账
     */
    public int subOfMemberCashApply(AccAccountRecord accAccountRecord);
    /**
     * 核算平台欠我多少钱
     */
    public BigDecimal sumCashAccountMoney(Long shopId);

    /**
     * 核算我欠合伙人等多少钱
     */
    public BigDecimal sumCommissionAccountMoney(Long shopId);

    /**
     * 根据订单 id 和 操作状态 获取唯一记录
     * @param orderId
     * @return
     */
    AccAccountRecord getRecordByOrderIdAndOperation(Long orderId,String operation);

    /**
     * 批量插入入账记录
     * @param accList
     * @return
     */
    int batchInsertAcc(List<AccAccountRecord> accList);

    /**
     * 账户记录与店铺信息关联查询
     * @param dto
     * @return
     */
    List<AccAccountRecordDTO> selectJoinList(AccAccountRecordDTO dto);

    /**
     *付款未发货取消订单
     * @param id
     * @return
     */
    int delRecordByOrderId(Long id);
}
