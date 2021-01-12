package com.fante.project.business.accBalanceRecord.service;

import com.fante.project.business.accBalanceRecord.domain.AccBalanceRecord;
import com.fante.project.business.accBalanceRecord.domain.AccBalanceRecordDTO;

import java.util.List;

/**
 * 用户余额记录Service接口
 *
 * @author fante
 * @date 2020-05-07
 */
public interface IAccBalanceRecordService {
    /**
     * 查询用户余额记录
     *
     * @param id 用户余额记录ID
     * @return 用户余额记录
     */
    public AccBalanceRecord selectAccBalanceRecordById(Long id);

    /**
     * 查询用户余额记录列表
     *
     * @param accBalanceRecord 用户余额记录
     * @return 用户余额记录集合
     */
    public List<AccBalanceRecord> selectAccBalanceRecordList(AccBalanceRecord accBalanceRecord);

    /**
     * 查询用户余额记录数量
     *
     * @param accBalanceRecord 用户余额记录
     * @return 结果
     */
    public int countAccBalanceRecord(AccBalanceRecord accBalanceRecord);

    /**
     * 唯一校验
     *
     * @param accBalanceRecord 用户余额记录
     * @return 结果
     */
    public String checkAccBalanceRecordUnique(AccBalanceRecord accBalanceRecord);

    /**
     * 新增用户余额记录
     *
     * @param accBalanceRecord 用户余额记录
     * @return 新增用户余额记录数量
     */
    public int insertAccBalanceRecord(AccBalanceRecord accBalanceRecord);

    /**
     * 修改用户余额记录
     *
     * @param accBalanceRecord 用户余额记录
     * @return 修改用户余额记录数量
     */
    public int updateAccBalanceRecord(AccBalanceRecord accBalanceRecord);

    /**
     * 批量删除用户余额记录
     *
     * @param ids 需要删除的数据ID
     * @return 删除用户余额记录数量
     */
    public int deleteAccBalanceRecordByIds(String ids);

    /**
     * 删除用户余额记录信息
     *
     * @param id 用户余额记录ID
     * @return 删除用户余额记录数量
     */
    public int deleteAccBalanceRecordById(Long id);


    /**
     * 同意提现调用
     *
     * @param  accBalanceRecord
     * @return  同意提现调用
     */
    public int insertAccBalanceRecordOfCash(AccBalanceRecord accBalanceRecord);

    /**
     * 佣金转入调用
     *
     * @param  accBalanceRecord
     * @return  佣金转入调用
     */
    public int insertAccBalanceRecordOfCommission(AccBalanceRecord accBalanceRecord);


    /**
     * 现金明细与用户信息关联查询
     * @param dto
     * @return
     */
    List<AccBalanceRecordDTO> selectJoinList(AccBalanceRecordDTO dto);

}
