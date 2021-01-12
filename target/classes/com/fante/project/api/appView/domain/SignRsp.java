package com.fante.project.api.appView.domain;

import com.fante.framework.web.domain.AjaxResult;
import com.fante.project.business.smsHomeSignReward.domain.SmsHomeSignReward;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName SignRsp
 * @Description 签到操作返回信息
 * @Author LiuQingyu
 * @Date 2020-03-12 10:25
 * @Version 1.0
 **/
public class SignRsp implements Serializable {

    private static final long serialVersionUID = 1L;

    private AjaxResult success;

    /** 连续签到天数 */
    private int continuous;
    /** 累计签到天数 */
    private int cumulative;
    /** 获得的奖励 */
    private List<SmsHomeSignReward> rewards;


    public SignRsp() {
    }

    public SignRsp(AjaxResult success, int continuous, int cumulative, List<SmsHomeSignReward> rewards) {
        this.success = success;
        this.continuous = continuous;
        this.cumulative = cumulative;
        this.rewards = rewards;
    }

    public AjaxResult getSuccess() {
        return success;
    }

    public void setSuccess(AjaxResult success) {
        this.success = success;
    }

    public int getContinuous() {
        return continuous;
    }

    public void setContinuous(int continuous) {
        this.continuous = continuous;
    }

    public int getCumulative() {
        return cumulative;
    }

    public void setCumulative(int cumulative) {
        this.cumulative = cumulative;
    }

    public List<SmsHomeSignReward> getRewards() {
        return rewards;
    }

    public void setRewards(List<SmsHomeSignReward> rewards) {
        this.rewards = rewards;
    }
}
