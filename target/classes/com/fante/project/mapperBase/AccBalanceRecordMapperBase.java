package com.fante.project.mapperBase;

import com.fante.project.business.accBalanceRecord.domain.AccBalanceRecord;
import java.util.List;

/**
 * 用户余额记录Mapper基础接口
 *
 * @author fante
 * @date 2020-05-07
 */
public interface AccBalanceRecordMapperBase {
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
    public int checkAccBalanceRecordUnique(AccBalanceRecord accBalanceRecord);

    /**
     * 新增用户余额记录
     *
     * @param accBalanceRecord 用户余额记录
     * @return 结果
     */
    public int insertAccBalanceRecord(AccBalanceRecord accBalanceRecord);

    /**
     * 修改用户余额记录
     *
     * @param accBalanceRecord 用户余额记录
     * @return 结果
     */
    public int updateAccBalanceRecord(AccBalanceRecord accBalanceRecord);

    /**
     * 删除用户余额记录
     *
     * @param id 用户余额记录ID
     * @return 结果
     */
    public int deleteAccBalanceRecordById(Long id);

    /**
     * 批量删除用户余额记录
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAccBalanceRecordByIds(String[] ids);

}
