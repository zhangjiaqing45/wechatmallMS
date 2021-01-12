package com.fante.project.business.smsGroupGameRecord.service.impl;

import java.util.Collections;
import java.util.List;

import com.fante.common.business.enums.SmsGroupGameConst;
import com.fante.common.utils.Checker;
import com.fante.common.utils.DateUtils;
import com.fante.common.constant.Constants;
import com.fante.common.utils.security.ShiroUtils;
import com.fante.project.business.smsGroupGame.domain.SmsGroupGame;
import com.fante.project.business.smsGroupGame.mapper.SmsGroupGameMapper;
import com.fante.project.business.smsGroupGameRecord.domain.SmsGroupGameRecordDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fante.project.business.smsGroupGameRecord.mapper.SmsGroupGameRecordMapper;
import com.fante.project.business.smsGroupGameRecord.domain.SmsGroupGameRecord;
import com.fante.project.business.smsGroupGameRecord.service.ISmsGroupGameRecordService;
import com.fante.common.utils.text.Convert;
import org.springframework.util.ObjectUtils;

/**
 * 团购记录Service业务层处理
 *
 * @author fante
 * @date 2020-03-30
 */
@Service
public class SmsGroupGameRecordServiceImpl implements ISmsGroupGameRecordService {

    private static Logger log = LoggerFactory.getLogger(SmsGroupGameRecordServiceImpl.class);

    @Autowired
    private SmsGroupGameRecordMapper smsGroupGameRecordMapper;
    @Autowired
    private SmsGroupGameMapper smsGroupGameMapper;

    /**
     * 查询团购记录
     *
     * @param id 团购记录ID
     * @return 团购记录
     */
    @Override
    public SmsGroupGameRecord selectSmsGroupGameRecordById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        return smsGroupGameRecordMapper.selectSmsGroupGameRecordById(id);
    }

    /**
     * 查询团购记录列表
     *
     * @param smsGroupGameRecord 团购记录
     * @return 团购记录集合
     */
    @Override
    public List<SmsGroupGameRecord> selectSmsGroupGameRecordList(SmsGroupGameRecord smsGroupGameRecord) {
        return smsGroupGameRecordMapper.getSmsGroupGameRecordList(smsGroupGameRecord);
    }

    /**
     * 查询团购记录数量
     *
     * @param smsGroupGameRecord 团购记录
     * @return 结果
     */
    @Override
    public int countSmsGroupGameRecord(SmsGroupGameRecord smsGroupGameRecord){
        return smsGroupGameRecordMapper.countSmsGroupGameRecord(smsGroupGameRecord);
    }

    /**
     * 唯一校验
     *
     * @param smsGroupGameRecord 团购记录
     * @return 结果
     */
    @Override
    public String checkSmsGroupGameRecordUnique(SmsGroupGameRecord smsGroupGameRecord) {
        return smsGroupGameRecordMapper.checkSmsGroupGameRecordUnique(smsGroupGameRecord) > 0 ? Constants.NOT_UNIQUE : Constants.UNIQUE;
    }

    /**
     * 新增团购记录
     *
     * @param smsGroupGameRecord 团购记录
     * @return 新增团购记录数量
     */
    @Override
    public int insertSmsGroupGameRecord(SmsGroupGameRecord smsGroupGameRecord) {
        smsGroupGameRecord.setCreateTime(DateUtils.getNowDate());
        return smsGroupGameRecordMapper.insertSmsGroupGameRecord(smsGroupGameRecord);
    }

    /**
     * 修改团购记录
     *
     * @param smsGroupGameRecord 团购记录
     * @return 修改团购记录数量
     */
    @Override
    public int updateSmsGroupGameRecord(SmsGroupGameRecord smsGroupGameRecord) {
        smsGroupGameRecord.setUpdateTime(DateUtils.getNowDate());
        return smsGroupGameRecordMapper.updateSmsGroupGameRecord(smsGroupGameRecord);
    }

    /**
     * 删除团购记录对象
     *
     * @param ids 需要删除的数据ID
     * @return 删除团购记录数量
     */
    @Override
    public int deleteSmsGroupGameRecordByIds(String ids) {
        return smsGroupGameRecordMapper.deleteSmsGroupGameRecordByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除团购记录信息
     *
     * @param id 团购记录ID
     * @return 删除团购记录数量
     */
    @Override
    public int deleteSmsGroupGameRecordById(Long id) {
        return smsGroupGameRecordMapper.deleteSmsGroupGameRecordById(id);
    }

    /**
     * 团购记录详情信息
     * @param id
     * @return
     */
    @Override
    public SmsGroupGameRecordDetail detail(Long id) {
        return smsGroupGameRecordMapper.getSmsGroupGameRecordDetailById(id);
    }
    /**
     * 预定名额
     * @param groupId
     * @return
     */
    @Override
    public int reservationQuotas(Long groupId) {
        return smsGroupGameRecordMapper.reservationQuotas(groupId);
    }

    /**
     * 释放名额
     * @param groupId
     * @return
     */
    @Override
    public int recoverQuotas(Long groupId) {
        return smsGroupGameRecordMapper.recoverQuotas(groupId);
    }
    /**
     * 支付成功正式加入此团
     * @param groupId
     * @return
     */
    @Override
    public int paySuccess(Long groupId) {
        return smsGroupGameRecordMapper.paySuccess(groupId);
    }
    /**
     * 判断次团购的时效是否过期
     */
    @Override
    public int validateAging(Long id) {
        return smsGroupGameRecordMapper.validateAging(id);
    }
    /**
     * 设置团购状态为成功
     * @param recordId
     */
    @Override
    public int groupSuccess(Long recordId) {
        return smsGroupGameRecordMapper.groupSuccess(recordId);
    }
    /**
     * 设置团购状态为失败
     * @param recordId
     */
    @Override
    public int groupFailure(Long recordId) {
        return smsGroupGameRecordMapper.groupFailure(recordId);
    }
    /**
     * (app)获取当前商品团购记录
     * 排除自己所在到团
     * @param ids
     * @return
     */
    @Override
    public List<SmsGroupGameRecordDetail> getGroupRecordList(List<Long> ids,int aging) {
        if (ObjectUtils.isEmpty(ids)) {
            return Collections.emptyList();
        }
        return smsGroupGameRecordMapper.getGroupRecordList(ids,aging);
    }
    /**
     * 获取用户团购记录详情
     * @param ids
     * @return
     */
    @Override
    public List<SmsGroupGameRecordDetail> getMemberGroupRecord(List<Long> ids){
        if (ObjectUtils.isEmpty(ids)) {
            return Collections.emptyList();
        }
        return smsGroupGameRecordMapper.getMemberGroupRecord(ids);
    }
    /**
     * (app)获取当前商品团购记录
     * 排除自己所在到团
     * @param groupGameId
     * @param memberId
     * @return
     */
    @Override
    public List<Long> getGroupRecordListBase(Long groupGameId, Long memberId) {
        SmsGroupGame game = smsGroupGameMapper.selectSmsGroupGameById(groupGameId);
        Checker.check(ObjectUtils.isEmpty(game),"活动已结束");
        return smsGroupGameRecordMapper.getGroupRecordListBase(
                groupGameId,
                memberId,
                SmsGroupGameConst.RecordStatusEnum.PROCESSING.getType());
    }
    /**
     * 获取用户团购记录详情
     * @param memberId
     * @return
     */
    @Override
    public List<Long> getMemberGroupRecordBase(Long memberId){
        return smsGroupGameRecordMapper.getMemberGroupRecordBase( memberId,
                SmsGroupGameConst.RecordStatusEnum.PROCESSING.getType());
    }
}
