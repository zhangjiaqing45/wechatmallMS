package com.fante.project.mapperBase;

import com.fante.project.business.smsHomeSignSetting.domain.SmsHomeSignSetting;
import java.util.List;

/**
 * 签到奖励设置Mapper基础接口
 *
 * @author fante
 * @date 2020-03-11
 */
public interface SmsHomeSignSettingMapperBase {
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
     * @return 结果
     */
    public int insertSmsHomeSignSetting(SmsHomeSignSetting smsHomeSignSetting);

    /**
     * 修改签到奖励设置
     *
     * @param smsHomeSignSetting 签到奖励设置
     * @return 结果
     */
    public int updateSmsHomeSignSetting(SmsHomeSignSetting smsHomeSignSetting);

    /**
     * 删除签到奖励设置
     *
     * @param id 签到奖励设置ID
     * @return 结果
     */
    public int deleteSmsHomeSignSettingById(Long id);

    /**
     * 批量删除签到奖励设置
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSmsHomeSignSettingByIds(String[] ids);
}
