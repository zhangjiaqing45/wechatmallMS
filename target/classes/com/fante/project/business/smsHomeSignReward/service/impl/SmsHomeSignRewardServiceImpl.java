package com.fante.project.business.smsHomeSignReward.service.impl;

import java.util.List;
import com.fante.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fante.project.business.smsHomeSignReward.mapper.SmsHomeSignRewardMapper;
import com.fante.project.business.smsHomeSignReward.domain.SmsHomeSignReward;
import com.fante.project.business.smsHomeSignReward.service.ISmsHomeSignRewardService;
import com.fante.common.utils.text.Convert;
import org.springframework.util.ObjectUtils;

/**
 * 签到奖励记录Service业务层处理
 *
 * @author fante
 * @date 2020-03-12
 */
@Service
public class SmsHomeSignRewardServiceImpl implements ISmsHomeSignRewardService {

    private static Logger log = LoggerFactory.getLogger(SmsHomeSignRewardServiceImpl.class);

    @Autowired
    private SmsHomeSignRewardMapper smsHomeSignRewardMapper;

    /**
     * 查询签到奖励记录
     *
     * @param id 签到奖励记录ID
     * @return 签到奖励记录
     */
    @Override
    public SmsHomeSignReward selectSmsHomeSignRewardById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        return smsHomeSignRewardMapper.selectSmsHomeSignRewardById(id);
    }

    /**
     * 查询签到奖励记录列表
     *
     * @param smsHomeSignReward 签到奖励记录
     * @return 签到奖励记录集合
     */
    @Override
    public List<SmsHomeSignReward> selectSmsHomeSignRewardList(SmsHomeSignReward smsHomeSignReward) {
        return smsHomeSignRewardMapper.selectSmsHomeSignRewardList(smsHomeSignReward);
    }

    /**
     * 查询签到奖励记录数量
     *
     * @param smsHomeSignReward 签到奖励记录
     * @return 结果
     */
    @Override
    public int countSmsHomeSignReward(SmsHomeSignReward smsHomeSignReward){
        return smsHomeSignRewardMapper.countSmsHomeSignReward(smsHomeSignReward);
    }

    /**
     * 新增签到奖励记录
     *
     * @param smsHomeSignReward 签到奖励记录
     * @return 新增签到奖励记录数量
     */
    @Override
    public int insertSmsHomeSignReward(SmsHomeSignReward smsHomeSignReward) {
        smsHomeSignReward.setCreateTime(DateUtils.getNowDate());
        return smsHomeSignRewardMapper.insertSmsHomeSignReward(smsHomeSignReward);
    }

    /**
     * 修改签到奖励记录
     *
     * @param smsHomeSignReward 签到奖励记录
     * @return 修改签到奖励记录数量
     */
    @Override
    public int updateSmsHomeSignReward(SmsHomeSignReward smsHomeSignReward) {
        return smsHomeSignRewardMapper.updateSmsHomeSignReward(smsHomeSignReward);
    }

    /**
     * 删除签到奖励记录对象
     *
     * @param ids 需要删除的数据ID
     * @return 删除签到奖励记录数量
     */
    @Override
    public int deleteSmsHomeSignRewardByIds(String ids) {
        return smsHomeSignRewardMapper.deleteSmsHomeSignRewardByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除签到奖励记录信息
     *
     * @param id 签到奖励记录ID
     * @return 删除签到奖励记录数量
     */
    @Override
    public int deleteSmsHomeSignRewardById(Long id) {
        return smsHomeSignRewardMapper.deleteSmsHomeSignRewardById(id);
    }

    /**
     * 批量添加
     *
     * @param rewards 签到奖励记录
     * @return void
     */
    @Override
    public void insertBatch(List<SmsHomeSignReward> rewards) {
        smsHomeSignRewardMapper.insertBatch(rewards);
    }
}
