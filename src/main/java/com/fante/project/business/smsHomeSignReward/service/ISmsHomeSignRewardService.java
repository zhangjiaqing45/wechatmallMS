package com.fante.project.business.smsHomeSignReward.service;

import com.fante.project.business.smsHomeSignReward.domain.SmsHomeSignReward;
import java.util.List;

/**
 * 签到奖励记录Service接口
 *
 * @author fante
 * @date 2020-03-12
 */
public interface ISmsHomeSignRewardService {
    /**
     * 查询签到奖励记录
     *
     * @param id 签到奖励记录ID
     * @return 签到奖励记录
     */
    public SmsHomeSignReward selectSmsHomeSignRewardById(Long id);

    /**
     * 查询签到奖励记录列表
     *
     * @param smsHomeSignReward 签到奖励记录
     * @return 签到奖励记录集合
     */
    public List<SmsHomeSignReward> selectSmsHomeSignRewardList(SmsHomeSignReward smsHomeSignReward);

    /**
     * 查询签到奖励记录数量
     *
     * @param smsHomeSignReward 签到奖励记录
     * @return 结果
     */
    public int countSmsHomeSignReward(SmsHomeSignReward smsHomeSignReward);

    /**
     * 新增签到奖励记录
     *
     * @param smsHomeSignReward 签到奖励记录
     * @return 新增签到奖励记录数量
     */
    public int insertSmsHomeSignReward(SmsHomeSignReward smsHomeSignReward);

    /**
     * 修改签到奖励记录
     *
     * @param smsHomeSignReward 签到奖励记录
     * @return 修改签到奖励记录数量
     */
    public int updateSmsHomeSignReward(SmsHomeSignReward smsHomeSignReward);

    /**
     * 批量删除签到奖励记录
     *
     * @param ids 需要删除的数据ID
     * @return 删除签到奖励记录数量
     */
    public int deleteSmsHomeSignRewardByIds(String ids);

    /**
     * 删除签到奖励记录信息
     *
     * @param id 签到奖励记录ID
     * @return 删除签到奖励记录数量
     */
    public int deleteSmsHomeSignRewardById(Long id);

    /**
     * 批量添加
     *
     * @param rewards 签到奖励记录
     * @return void
     */
    void insertBatch(List<SmsHomeSignReward> rewards);
}
