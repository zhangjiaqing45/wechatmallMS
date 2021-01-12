package com.fante.project.mapperBase;

import com.fante.project.business.accCommissionRecord.domain.AccCommissionRecord;
import java.util.List;

/**
 * 用户佣金记录Mapper基础接口
 *
 * @author fante
 * @date 2020-05-09
 */
public interface AccCommissionRecordMapperBase {
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
    public int checkAccCommissionRecordUnique(AccCommissionRecord accCommissionRecord);

    /**
     * 新增用户佣金记录
     *
     * @param accCommissionRecord 用户佣金记录
     * @return 结果
     */
    public int insertAccCommissionRecord(AccCommissionRecord accCommissionRecord);

    /**
     * 修改用户佣金记录
     *
     * @param accCommissionRecord 用户佣金记录
     * @return 结果
     */
    public int updateAccCommissionRecord(AccCommissionRecord accCommissionRecord);

    /**
     * 删除用户佣金记录
     *
     * @param id 用户佣金记录ID
     * @return 结果
     */
    public int deleteAccCommissionRecordById(Long id);

    /**
     * 批量删除用户佣金记录
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAccCommissionRecordByIds(String[] ids);

}
