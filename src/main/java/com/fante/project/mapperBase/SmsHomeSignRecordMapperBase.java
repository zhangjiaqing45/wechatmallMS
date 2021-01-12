package com.fante.project.mapperBase;

import com.fante.project.business.smsHomeSignRecord.domain.SmsHomeSignRecord;
import java.util.List;

/**
 * 签到记录Mapper基础接口
 *
 * @author fante
 * @date 2020-03-12
 */
public interface SmsHomeSignRecordMapperBase {
    /**
     * 查询签到记录
     *
     * @param id 签到记录ID
     * @return 签到记录
     */
    public SmsHomeSignRecord selectSmsHomeSignRecordById(Long id);

    /**
     * 查询签到记录列表
     *
     * @param smsHomeSignRecord 签到记录
     * @return 签到记录集合
     */
    public List<SmsHomeSignRecord> selectSmsHomeSignRecordList(SmsHomeSignRecord smsHomeSignRecord);

    /**
     * 查询签到记录数量
     *
     * @param smsHomeSignRecord 签到记录
     * @return 结果
     */
    public int countSmsHomeSignRecord(SmsHomeSignRecord smsHomeSignRecord);

    /**
     * 新增签到记录
     *
     * @param smsHomeSignRecord 签到记录
     * @return 结果
     */
    public int insertSmsHomeSignRecord(SmsHomeSignRecord smsHomeSignRecord);

    /**
     * 修改签到记录
     *
     * @param smsHomeSignRecord 签到记录
     * @return 结果
     */
    public int updateSmsHomeSignRecord(SmsHomeSignRecord smsHomeSignRecord);

    /**
     * 删除签到记录
     *
     * @param id 签到记录ID
     * @return 结果
     */
    public int deleteSmsHomeSignRecordById(Long id);

    /**
     * 批量删除签到记录
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSmsHomeSignRecordByIds(String[] ids);
}
