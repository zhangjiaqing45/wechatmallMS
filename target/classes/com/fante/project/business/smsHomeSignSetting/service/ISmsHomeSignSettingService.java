package com.fante.project.business.smsHomeSignSetting.service;

import com.fante.project.business.smsHomeSignSetting.domain.SmsHomeSignSetting;
import java.util.List;

/**
 * 签到奖励设置Service接口
 *
 * @author fante
 * @date 2020-03-11
 */
public interface ISmsHomeSignSettingService {
    /**
     * 查询签到奖励设置
     *
     * @param id 签到奖励设置ID
     * @return 签到奖励设置
     */
    public SmsHomeSignSetting selectSmsHomeSignSettingById(Long id);

    /**
     * 查询签到奖励设置列表
     *
     * @param smsHomeSignSetting 签到奖励设置
     * @return 签到奖励设置集合
     */
    public List<SmsHomeSignSetting> selectSmsHomeSignSettingList(SmsHomeSignSetting smsHomeSignSetting);

    /**
     * 通过签到次数和类型查询
     *
     * @param setting
     * @return 通过签到次数和类型查询
     */
    SmsHomeSignSetting selectByTypeAndTimes(SmsHomeSignSetting setting);

    /**
     * 查询签到奖励设置数量
     *
     * @param smsHomeSignSetting 签到奖励设置
     * @return 结果
     */
    public int countSmsHomeSignSetting(SmsHomeSignSetting smsHomeSignSetting);

    /**
     * 新增签到奖励设置
     *
     * @param smsHomeSignSetting 签到奖励设置
     * @return 新增签到奖励设置数量
     */
    public int insertSmsHomeSignSetting(SmsHomeSignSetting smsHomeSignSetting);

    /**
     * 修改签到奖励设置
     *
     * @param smsHomeSignSetting 签到奖励设置
     * @return 修改签到奖励设置数量
     */
    public int updateSmsHomeSignSetting(SmsHomeSignSetting smsHomeSignSetting);

    /**
     * 批量删除签到奖励设置
     *
     * @param ids 需要删除的数据ID
     * @return 删除签到奖励设置数量
     */
    public int deleteSmsHomeSignSettingByIds(String ids);

    /**
     * 删除签到奖励设置信息
     *
     * @param id 签到奖励设置ID
     * @return 删除签到奖励设置数量
     */
    public int deleteSmsHomeSignSettingById(Long id);

    /**
     * 校验字段唯一
     *
     * @param smsHomeSignSetting
     * @return 校验字段唯一性
     */
    String checkSignSettingUnique(SmsHomeSignSetting smsHomeSignSetting);
}
