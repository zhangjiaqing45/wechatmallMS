package com.fante.project.business.smsGroupMemberRecord.service;

import com.fante.project.business.smsGroupMemberRecord.domain.SmsGroupMemberRecord;
import java.util.List;

/**
 * 团购记录人员Service接口
 *
 * @author fante
 * @date 2020-03-30
 */
public interface ISmsGroupMemberRecordService {
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
    public String checkSmsGroupMemberRecordUnique(SmsGroupMemberRecord smsGroupMemberRecord);

    /**
     * 新增团购记录人员
     *
     * @param smsGroupMemberRecord 团购记录人员
     * @return 新增团购记录人员数量
     */
    public int insertSmsGroupMemberRecord(SmsGroupMemberRecord smsGroupMemberRecord);

    /**
     * 修改团购记录人员
     *
     * @param smsGroupMemberRecord 团购记录人员
     * @return 修改团购记录人员数量
     */
    public int updateSmsGroupMemberRecord(SmsGroupMemberRecord smsGroupMemberRecord);

    /**
     * 批量删除团购记录人员
     *
     * @param ids 需要删除的数据ID
     * @return 删除团购记录人员数量
     */
    public int deleteSmsGroupMemberRecordByIds(String ids);

    /**
     * 删除团购记录人员信息
     *
     * @param id 团购记录人员ID
     * @return 删除团购记录人员数量
     */
    public int deleteSmsGroupMemberRecordById(Long id);

    /**
     * 获取所有团购失败未取消订单
     * @return
     */
    List<Long> selectTimeOutGroupRecord();
}
