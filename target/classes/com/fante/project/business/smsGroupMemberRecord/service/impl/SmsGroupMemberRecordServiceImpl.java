package com.fante.project.business.smsGroupMemberRecord.service.impl;

import java.util.List;

import com.fante.common.business.enums.SmsGroupGameConst;
import com.fante.common.utils.DateUtils;
import com.fante.common.constant.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fante.project.business.smsGroupMemberRecord.mapper.SmsGroupMemberRecordMapper;
import com.fante.project.business.smsGroupMemberRecord.domain.SmsGroupMemberRecord;
import com.fante.project.business.smsGroupMemberRecord.service.ISmsGroupMemberRecordService;
import com.fante.common.utils.text.Convert;
import org.springframework.util.ObjectUtils;

/**
 * 团购记录人员Service业务层处理
 *
 * @author fante
 * @date 2020-03-30
 */
@Service
public class SmsGroupMemberRecordServiceImpl implements ISmsGroupMemberRecordService {

    private static Logger log = LoggerFactory.getLogger(SmsGroupMemberRecordServiceImpl.class);

    @Autowired
    private SmsGroupMemberRecordMapper smsGroupMemberRecordMapper;

    /**
     * 查询团购记录人员
     *
     * @param id 团购记录人员ID
     * @return 团购记录人员
     */
    @Override
    public SmsGroupMemberRecord selectSmsGroupMemberRecordById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        return smsGroupMemberRecordMapper.selectSmsGroupMemberRecordById(id);
    }

    /**
     * 查询团购记录人员列表
     *
     * @param smsGroupMemberRecord 团购记录人员
     * @return 团购记录人员集合
     */
    @Override
    public List<SmsGroupMemberRecord> selectSmsGroupMemberRecordList(SmsGroupMemberRecord smsGroupMemberRecord) {
        return smsGroupMemberRecordMapper.selectSmsGroupMemberRecordList(smsGroupMemberRecord);
    }

    /**
     * 查询团购记录人员数量
     *
     * @param smsGroupMemberRecord 团购记录人员
     * @return 结果
     */
    @Override
    public int countSmsGroupMemberRecord(SmsGroupMemberRecord smsGroupMemberRecord){
        return smsGroupMemberRecordMapper.countSmsGroupMemberRecord(smsGroupMemberRecord);
    }

    /**
     * 唯一校验
     *
     * @param smsGroupMemberRecord 团购记录人员
     * @return 结果
     */
    @Override
    public String checkSmsGroupMemberRecordUnique(SmsGroupMemberRecord smsGroupMemberRecord) {
        return smsGroupMemberRecordMapper.checkSmsGroupMemberRecordUnique(smsGroupMemberRecord) > 0 ? Constants.NOT_UNIQUE : Constants.UNIQUE;
    }

    /**
     * 新增团购记录人员
     *
     * @param smsGroupMemberRecord 团购记录人员
     * @return 新增团购记录人员数量
     */
    @Override
    public int insertSmsGroupMemberRecord(SmsGroupMemberRecord smsGroupMemberRecord) {
        smsGroupMemberRecord.setCreateTime(DateUtils.getNowDate());
        return smsGroupMemberRecordMapper.insertSmsGroupMemberRecord(smsGroupMemberRecord);
    }

    /**
     * 修改团购记录人员
     *
     * @param smsGroupMemberRecord 团购记录人员
     * @return 修改团购记录人员数量
     */
    @Override
    public int updateSmsGroupMemberRecord(SmsGroupMemberRecord smsGroupMemberRecord) {
        smsGroupMemberRecord.setUpdateTime(DateUtils.getNowDate());
        return smsGroupMemberRecordMapper.updateSmsGroupMemberRecord(smsGroupMemberRecord);
    }

    /**
     * 删除团购记录人员对象
     *
     * @param ids 需要删除的数据ID
     * @return 删除团购记录人员数量
     */
    @Override
    public int deleteSmsGroupMemberRecordByIds(String ids) {
        return smsGroupMemberRecordMapper.deleteSmsGroupMemberRecordByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除团购记录人员信息
     *
     * @param id 团购记录人员ID
     * @return 删除团购记录人员数量
     */
    @Override
    public int deleteSmsGroupMemberRecordById(Long id) {
        return smsGroupMemberRecordMapper.deleteSmsGroupMemberRecordById(id);
    }

    /**
     * 获取所有团购失败未取消订单
     * @return
     */
    @Override
    public List<Long> selectTimeOutGroupRecord() {
        return smsGroupMemberRecordMapper.selectTimeOutGroupRecord(SmsGroupGameConst.RecordStatusEnum.PROCESSING.getType());
    }
}
