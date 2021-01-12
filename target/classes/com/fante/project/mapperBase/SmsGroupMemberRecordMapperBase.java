package com.fante.project.mapperBase;

import com.fante.project.business.smsGroupMemberRecord.domain.SmsGroupMemberRecord;
import java.util.List;

/**
 * 团购记录人员Mapper基础接口
 *
 * @author fante
 * @date 2020-05-09
 */
public interface SmsGroupMemberRecordMapperBase {
    /**
     * 查询团购记录人员
     *
     * @param id 团购记录人员ID
     * @return 团购记录人员
     */
    public SmsGroupMemberRecord selectSmsGroupMemberRecordById(Long id);

    /**
     * 查询团购记录人员列表
     *
     * @param smsGroupMemberRecord 团购记录人员
     * @return 团购记录人员集合
     */
    public List<SmsGroupMemberRecord> selectSmsGroupMemberRecordList(SmsGroupMemberRecord smsGroupMemberRecord);

    /**
     * 查询团购记录人员数量
     *
     * @param smsGroupMemberRecord 团购记录人员
     * @return 结果
     */
    public int countSmsGroupMemberRecord(SmsGroupMemberRecord smsGroupMemberRecord);

    /**
     * 唯一校验
     *
     * @param smsGroupMemberRecord 团购记录人员
     * @return 结果
     */
    public int checkSmsGroupMemberRecordUnique(SmsGroupMemberRecord smsGroupMemberRecord);

    /**
     * 新增团购记录人员
     *
     * @param smsGroupMemberRecord 团购记录人员
     * @return 结果
     */
    public int insertSmsGroupMemberRecord(SmsGroupMemberRecord smsGroupMemberRecord);

    /**
     * 修改团购记录人员
     *
     * @param smsGroupMemberRecord 团购记录人员
     * @return 结果
     */
    public int updateSmsGroupMemberRecord(SmsGroupMemberRecord smsGroupMemberRecord);

    /**
     * 删除团购记录人员
     *
     * @param id 团购记录人员ID
     * @return 结果
     */
    public int deleteSmsGroupMemberRecordById(Long id);

    /**
     * 批量删除团购记录人员
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSmsGroupMemberRecordByIds(String[] ids);

}
