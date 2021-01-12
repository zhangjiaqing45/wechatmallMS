package com.fante.project.mapperBase;

import com.fante.project.business.smsGroupGameRecord.domain.SmsGroupGameRecord;
import java.util.List;

/**
 * 团购记录Mapper基础接口
 *
 * @author fante
 * @date 2020-04-20
 */
public interface SmsGroupGameRecordMapperBase {
    /**
     * 查询团购记录
     *
     * @param id 团购记录ID
     * @return 团购记录
     */
    public SmsGroupGameRecord selectSmsGroupGameRecordById(Long id);

    /**
     * 查询团购记录列表
     *
     * @param smsGroupGameRecord 团购记录
     * @return 团购记录集合
     */
    public List<SmsGroupGameRecord> selectSmsGroupGameRecordList(SmsGroupGameRecord smsGroupGameRecord);

    /**
     * 查询团购记录数量
     *
     * @param smsGroupGameRecord 团购记录
     * @return 结果
     */
    public int countSmsGroupGameRecord(SmsGroupGameRecord smsGroupGameRecord);

    /**
     * 唯一校验
     *
     * @param smsGroupGameRecord 团购记录
     * @return 结果
     */
    public int checkSmsGroupGameRecordUnique(SmsGroupGameRecord smsGroupGameRecord);

    /**
     * 新增团购记录
     *
     * @param smsGroupGameRecord 团购记录
     * @return 结果
     */
    public int insertSmsGroupGameRecord(SmsGroupGameRecord smsGroupGameRecord);

    /**
     * 修改团购记录
     *
     * @param smsGroupGameRecord 团购记录
     * @return 结果
     */
    public int updateSmsGroupGameRecord(SmsGroupGameRecord smsGroupGameRecord);

    /**
     * 删除团购记录
     *
     * @param id 团购记录ID
     * @return 结果
     */
    public int deleteSmsGroupGameRecordById(Long id);

    /**
     * 批量删除团购记录
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSmsGroupGameRecordByIds(String[] ids);

}
