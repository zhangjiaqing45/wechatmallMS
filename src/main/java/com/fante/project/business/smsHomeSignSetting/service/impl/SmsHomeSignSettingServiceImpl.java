package com.fante.project.business.smsHomeSignSetting.service.impl;

import com.fante.common.business.enums.SmsSignConst;
import com.fante.common.constant.Constants;
import com.fante.common.utils.DateUtils;
import com.fante.common.utils.StringUtils;
import com.fante.common.utils.text.Convert;
import com.fante.project.business.smsHomeSignSetting.domain.SmsHomeSignSetting;
import com.fante.project.business.smsHomeSignSetting.mapper.SmsHomeSignSettingMapper;
import com.fante.project.business.smsHomeSignSetting.service.ISmsHomeSignSettingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * 签到奖励设置Service业务层处理
 *
 * @author fante
 * @date 2020-03-11
 */
@Service
public class SmsHomeSignSettingServiceImpl implements ISmsHomeSignSettingService {

    private static Logger log = LoggerFactory.getLogger(SmsHomeSignSettingServiceImpl.class);

    @Autowired
    private SmsHomeSignSettingMapper smsHomeSignSettingMapper;

    /**
     * 查询签到奖励设置
     *
     * @param id 签到奖励设置ID
     * @return 签到奖励设置
     */
    @Override
    public SmsHomeSignSetting selectSmsHomeSignSettingById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        return smsHomeSignSettingMapper.selectSmsHomeSignSettingById(id);
    }

    /**
     * 查询签到奖励设置列表
     *
     * @param smsHomeSignSetting 签到奖励设置
     * @return 签到奖励设置集合
     */
    @Override
    public List<SmsHomeSignSetting> selectSmsHomeSignSettingList(SmsHomeSignSetting smsHomeSignSetting) {
        return smsHomeSignSettingMapper.selectSmsHomeSignSettingList(smsHomeSignSetting);
    }

    /**
     * 通过签到次数和类型查询
     *
     * @param setting
     * @return 通过签到次数和类型查询
     */
    @Override
    public SmsHomeSignSetting selectByTypeAndTimes(SmsHomeSignSetting setting) {
        return smsHomeSignSettingMapper.selectByTypeAndTimes(setting);
    }

    /**
     * 查询签到奖励设置数量
     *
     * @param smsHomeSignSetting 签到奖励设置
     * @return 结果
     */
    @Override
    public int countSmsHomeSignSetting(SmsHomeSignSetting smsHomeSignSetting) {
        return smsHomeSignSettingMapper.countSmsHomeSignSetting(smsHomeSignSetting);
    }

    /**
     * 新增签到奖励设置
     *
     * @param smsHomeSignSetting 签到奖励设置
     * @return 新增签到奖励设置数量
     */
    @Override
    public int insertSmsHomeSignSetting(SmsHomeSignSetting smsHomeSignSetting) {
        smsHomeSignSetting.setCreateTime(DateUtils.getNowDate());
        return smsHomeSignSettingMapper.insertSmsHomeSignSetting(smsHomeSignSetting);
    }

    /**
     * 修改签到奖励设置
     *
     * @param smsHomeSignSetting 签到奖励设置
     * @return 修改签到奖励设置数量
     */
    @Override
    public int updateSmsHomeSignSetting(SmsHomeSignSetting smsHomeSignSetting) {
        smsHomeSignSetting.setUpdateTime(DateUtils.getNowDate());
        return smsHomeSignSettingMapper.updateSmsHomeSignSetting(smsHomeSignSetting);
    }

    /**
     * 删除签到奖励设置对象
     *
     * @param ids 需要删除的数据ID
     * @return 删除签到奖励设置数量
     */
    @Override
    public int deleteSmsHomeSignSettingByIds(String ids) {
        return smsHomeSignSettingMapper.deleteSmsHomeSignSettingByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除签到奖励设置信息
     *
     * @param id 签到奖励设置ID
     * @return 删除签到奖励设置数量
     */
    @Override
    public int deleteSmsHomeSignSettingById(Long id) {
        return smsHomeSignSettingMapper.deleteSmsHomeSignSettingById(id);
    }

    /**
     * 校验字段唯一性
     *
     * @param smsHomeSignSetting
     * @return 校验字段唯一性
     */
    @Override
    public String checkSignSettingUnique(SmsHomeSignSetting smsHomeSignSetting) {
        if (StringUtils.equals(smsHomeSignSetting.getSignType(), SmsSignConst.SignType.DAILY.getType())
                || (!ObjectUtils.isEmpty(smsHomeSignSetting.getSignTimes()) && smsHomeSignSetting.getSignTimes() > 0)) {
            if (smsHomeSignSettingMapper.checkSignSettingUnique(smsHomeSignSetting) > 0) {
                return Constants.NOT_UNIQUE;
            }
        }
        return Constants.UNIQUE;
    }
}
