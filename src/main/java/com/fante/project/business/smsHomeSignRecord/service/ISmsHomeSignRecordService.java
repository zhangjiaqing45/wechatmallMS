package com.fante.project.business.smsHomeSignRecord.service;

import com.fante.project.business.smsHomeSignRecord.domain.SmsHomeSignRecord;
import java.util.List;

/**
 * 签到记录Service接口
 *
 * @author fante
 * @date 2020-03-12
 */
public interface ISmsHomeSignRecordService {
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
     * 查询用户签到记录（当月）
     *
     * @param smsHomeSignRecord 签到记录
     * @return 签到记录集合
     */
    public List<SmsHomeSignRecord> selectUserSignRecordList(SmsHomeSignRecord smsHomeSignRecord);

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
     * @return 新增签到记录数量
     */
    public int insertSmsHomeSignRecord(SmsHomeSignRecord smsHomeSignRecord);

    /**
     * 修改签到记录
     *
     * @param smsHomeSignRecord 签到记录
     * @return 修改签到记录数量
     */
    public int updateSmsHomeSignRecord(SmsHomeSignRecord smsHomeSignRecord);

    /**
     * 批量删除签到记录
     *
     * @param ids 需要删除的数据ID
     * @return 删除签到记录数量
     */
    public int deleteSmsHomeSignRecordByIds(String ids);

    /**
     * 删除签到记录信息
     *
     * @param id 签到记录ID
     * @return 删除签到记录数量
     */
    public int deleteSmsHomeSignRecordById(Long id);
    }
