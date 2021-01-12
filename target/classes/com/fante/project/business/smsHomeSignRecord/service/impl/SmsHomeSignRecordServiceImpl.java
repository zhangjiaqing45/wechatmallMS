package com.fante.project.business.smsHomeSignRecord.service.impl;

import java.util.List;
import com.fante.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fante.project.business.smsHomeSignRecord.mapper.SmsHomeSignRecordMapper;
import com.fante.project.business.smsHomeSignRecord.domain.SmsHomeSignRecord;
import com.fante.project.business.smsHomeSignRecord.service.ISmsHomeSignRecordService;
import com.fante.common.utils.text.Convert;
import org.springframework.util.ObjectUtils;

/**
 * 签到记录Service业务层处理
 *
 * @author fante
 * @date 2020-03-12
 */
@Service
public class SmsHomeSignRecordServiceImpl implements ISmsHomeSignRecordService {

    private static Logger log = LoggerFactory.getLogger(SmsHomeSignRecordServiceImpl.class);

    @Autowired
    private SmsHomeSignRecordMapper smsHomeSignRecordMapper;

    /**
     * 查询签到记录
     *
     * @param id 签到记录ID
     * @return 签到记录
     */
    @Override
    public SmsHomeSignRecord selectSmsHomeSignRecordById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        return smsHomeSignRecordMapper.selectSmsHomeSignRecordById(id);
    }

    /**
     * 查询签到记录列表
     *
     * @param smsHomeSignRecord 签到记录
     * @return 签到记录集合
     */
    @Override
    public List<SmsHomeSignRecord> selectSmsHomeSignRecordList(SmsHomeSignRecord smsHomeSignRecord) {
        return smsHomeSignRecordMapper.selectSmsHomeSignRecordList(smsHomeSignRecord);
    }

    /**
     * 查询用户签到记录（当月）
     *
     * @param smsHomeSignRecord 签到记录
     * @return 签到记录集合
     */
    @Override
    public List<SmsHomeSignRecord> selectUserSignRecordList(SmsHomeSignRecord smsHomeSignRecord) {
        return smsHomeSignRecordMapper.selectUserSignRecordList(smsHomeSignRecord);
    }

    /**
     * 查询签到记录数量
     *
     * @param smsHomeSignRecord 签到记录
     * @return 结果
     */
    @Override
    public int countSmsHomeSignRecord(SmsHomeSignRecord smsHomeSignRecord){
        return smsHomeSignRecordMapper.countSmsHomeSignRecord(smsHomeSignRecord);
    }

    /**
     * 新增签到记录
     *
     * @param smsHomeSignRecord 签到记录
     * @return 新增签到记录数量
     */
    @Override
    public int insertSmsHomeSignRecord(SmsHomeSignRecord smsHomeSignRecord) {
        smsHomeSignRecord.setCreateTime(DateUtils.getNowDate());
        return smsHomeSignRecordMapper.insertSmsHomeSignRecord(smsHomeSignRecord);
    }

    /**
     * 修改签到记录
     *
     * @param smsHomeSignRecord 签到记录
     * @return 修改签到记录数量
     */
    @Override
    public int updateSmsHomeSignRecord(SmsHomeSignRecord smsHomeSignRecord) {
        return smsHomeSignRecordMapper.updateSmsHomeSignRecord(smsHomeSignRecord);
    }

    /**
     * 删除签到记录对象
     *
     * @param ids 需要删除的数据ID
     * @return 删除签到记录数量
     */
    @Override
    public int deleteSmsHomeSignRecordByIds(String ids) {
        return smsHomeSignRecordMapper.deleteSmsHomeSignRecordByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除签到记录信息
     *
     * @param id 签到记录ID
     * @return 删除签到记录数量
     */
    @Override
    public int deleteSmsHomeSignRecordById(Long id) {
        return smsHomeSignRecordMapper.deleteSmsHomeSignRecordById(id);
    }
}
