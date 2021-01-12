package com.fante.project.business.homeStatistics.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author ftnet
 * @Description HomeData
 * @CreatTime 2020/5/12
 */
public class SomeDaysOrderInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**店铺id*/
    private Long shopId;
    /**开始时间*/
    private String startTime;
    /**结束时间*/
    private String endTime;
    /**获取时间段内订单总量*/
    private int countDaysOfOrder;
    /**获取时间段内订单成交总额*/
    private BigDecimal sumDaysOfMoney;
    /**获取时间段内佣金总额*/
    private BigDecimal sumDaysOfMemberCash;
    /**获取时间段内佣金总额*/
    private int someDayCouponCount;

    public SomeDaysOrderInfo(int countDaysOfOrder, BigDecimal sumDaysOfMoney, BigDecimal sumDaysOfMemberCash,int someDayCouponCount) {
        this.countDaysOfOrder = countDaysOfOrder;
        this.sumDaysOfMoney = sumDaysOfMoney;
        this.sumDaysOfMemberCash = sumDaysOfMemberCash;
        this.someDayCouponCount = someDayCouponCount;
    }

    public SomeDaysOrderInfo() {
    }

    public int getSomeDayCouponCount() {
        return someDayCouponCount;
    }

    public void setSomeDayCouponCount(int someDayCouponCount) {
        this.someDayCouponCount = someDayCouponCount;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getCountDaysOfOrder() {
        return countDaysOfOrder;
    }

    public void setCountDaysOfOrder(int countDaysOfOrder) {
        this.countDaysOfOrder = countDaysOfOrder;
    }

    public BigDecimal getSumDaysOfMoney() {
        return sumDaysOfMoney;
    }

    public void setSumDaysOfMoney(BigDecimal sumDaysOfMoney) {
        this.sumDaysOfMoney = sumDaysOfMoney;
    }

    public BigDecimal getSumDaysOfMemberCash() {
        return sumDaysOfMemberCash;
    }

    public void setSumDaysOfMemberCash(BigDecimal sumDaysOfMemberCash) {
        this.sumDaysOfMemberCash = sumDaysOfMemberCash;
    }
}
