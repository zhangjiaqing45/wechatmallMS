package com.fante.project.business.smsGroupGameRecord.service;

import com.fante.project.business.smsGroupGameRecord.domain.SmsGroupGameRecord;
import com.fante.project.business.smsGroupGameRecord.domain.SmsGroupGameRecordDetail;

import java.util.List;

/**
 * 团购记录Service接口
 *
 * @author fante
 * @date 2020-03-30
 */
public interface ISmsGroupGameRecordService {
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
    public String checkSmsGroupGameRecordUnique(SmsGroupGameRecord smsGroupGameRecord);

    /**
     * 新增团购记录
     *
     * @param smsGroupGameRecord 团购记录
     * @return 新增团购记录数量
     */
    public int insertSmsGroupGameRecord(SmsGroupGameRecord smsGroupGameRecord);

    /**
     * 修改团购记录
     *
     * @param smsGroupGameRecord 团购记录
     * @return 修改团购记录数量
     */
    public int updateSmsGroupGameRecord(SmsGroupGameRecord smsGroupGameRecord);

    /**
     * 批量删除团购记录
     *
     * @param ids 需要删除的数据ID
     * @return 删除团购记录数量
     */
    public int deleteSmsGroupGameRecordByIds(String ids);

    /**
     * 删除团购记录信息
     *
     * @param id 团购记录ID
     * @return 删除团购记录数量
     */
    public int deleteSmsGroupGameRecordById(Long id);

    /**
     * 团购记录详情信息
     * @param id
     * @return
     */
    public SmsGroupGameRecordDetail detail(Long id);

    /**
     * 预定名额
     * @param groupId
     * @return
     */
    public int reservationQuotas(Long groupId);

    /**
     * 释放名额
     * @param groupId
     * @return
     */
    public int recoverQuotas(Long groupId);

    /**
     * 判断次团购的时效是否过期
     */
    public int validateAging(Long id);
    /**
     * 支付成功正式加入此团
     * @param groupId
     * @return
     */
    public int paySuccess(Long groupId);
    /**
     * 设置团购状态为成功
     * @param recordId
     */
    public int groupSuccess(Long recordId);
    /**
     * 设置团购状态为失败
     * @param recordId
     */
    public int groupFailure(Long recordId);

    /**
     * (app)获取当前商品团购记录
     * 排除自己所在到团
     * @param ids
     * @return
     */
    List<SmsGroupGameRecordDetail>  getGroupRecordList(List<Long> ids,int aging);

    /**
     * 获取用户团购记录详情
     * @param ids
     * @return
     */
    List<SmsGroupGameRecordDetail> getMemberGroupRecord(List<Long> ids);
    /**
     * (app)获取当前商品团购记录
     * 排除自己所在到团
     * @param groupGameId
     * @param memberId
     * @return
     */
    List<Long>  getGroupRecordListBase(Long groupGameId, Long memberId);

    /**
     * 获取用户团购记录详情
     * @param memberId
     * @return
     */
    List<Long> getMemberGroupRecordBase(Long memberId);
}
