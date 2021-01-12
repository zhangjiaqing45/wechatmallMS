package com.fante.project.mapperBase;

import com.fante.project.business.smsHomeSignReward.domain.SmsHomeSignReward;
import java.util.List;

/**
 * 签到奖励记录Mapper基础接口
 *
 * @author fante
 * @date 2020-03-12
 */
public interface SmsHomeSignRewardMapperBase {
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
     * @return 结果
     */
    public int insertSmsHomeSignReward(SmsHomeSignReward smsHomeSignReward);

    /**
     * 修改签到奖励记录
     *
     * @param smsHomeSignReward 签到奖励记录
     * @return 结果
     */
    public int updateSmsHomeSignReward(SmsHomeSignReward smsHomeSignReward);

    /**
     * 删除签到奖励记录
     *
     * @param id 签到奖励记录ID
     * @return 结果
     */
    public int deleteSmsHomeSignRewardById(Long id);

    /**
     * 批量删除签到奖励记录
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSmsHomeSignRewardByIds(String[] ids);
}
