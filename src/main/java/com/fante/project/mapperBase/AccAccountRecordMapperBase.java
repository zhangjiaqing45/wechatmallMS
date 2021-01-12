package com.fante.project.mapperBase;

import com.fante.project.business.accAccountRecord.domain.AccAccountRecord;
import java.util.List;

/**
 * 账户出入账记录Mapper基础接口
 *
 * @author fante
 * @date 2020-05-09
 */
public interface AccAccountRecordMapperBase {
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
    public int checkAccAccountRecordUnique(AccAccountRecord accAccountRecord);

    /**
     * 新增账户出入账记录
     *
     * @param accAccountRecord 账户出入账记录
     * @return 结果
     */
    public int insertAccAccountRecord(AccAccountRecord accAccountRecord);

    /**
     * 修改账户出入账记录
     *
     * @param accAccountRecord 账户出入账记录
     * @return 结果
     */
    public int updateAccAccountRecord(AccAccountRecord accAccountRecord);

    /**
     * 删除账户出入账记录
     *
     * @param id 账户出入账记录ID
     * @return 结果
     */
    public int deleteAccAccountRecordById(Long id);

    /**
     * 批量删除账户出入账记录
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAccAccountRecordByIds(String[] ids);

}
