package com.fante.project.api.appView.domain;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName SignRsp
 * @Description 用户签到信息
 * @Author LiuQingyu
 * @Date 2020-03-12 10:25
 * @Version 1.0
 **/
public class SignInfoRsp implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 连续签到天数 */
    private int continuous;
    /** 累计签到天数 */
    private int cumulative;
    /**当月已经签到的日子*/
    private List<String> beforeSignDates;
    /**该用户目前所拥有的积分*/
    private BigDecimal integral;
    /**该用户今天是否签到*/
    private boolean  signToday;

    public SignInfoRsp() {
    }

    public SignInfoRsp(int continuous, int cumulative, List<String> beforeSignDates, BigDecimal integral,boolean signToday) {
        this.continuous = continuous;
        this.cumulative = cumulative;
        this.beforeSignDates = beforeSignDates;
        this.integral = integral;
        this.signToday = signToday;
    }

    public boolean isSignToday() {
        return signToday;
    }

    public void setSignToday(boolean signToday) {
        this.signToday = signToday;
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

    public List<String> getBeforeSignDates() {
        return beforeSignDates;
    }

    public void setBeforeSignDates(List<String> beforeSignDates) {
        this.beforeSignDates = beforeSignDates;
    }

    public BigDecimal getIntegral() {
        return integral;
    }

    public void setIntegral(BigDecimal integral) {
        this.integral = integral;
    }
}
