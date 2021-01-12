package com.fante.project.business.homeStatistics.domain;

import java.io.Serializable;

/**
 * @author ftnet
 * @Description HomeData
 * @CreatTime 2020/5/12
 */
public class HomeData implements Serializable {

    private static final long serialVersionUID = 1L;
    /**获取当日订单总量*/
    private String countDayOfOrder;
    /**获取当月订单总量*/
    private String countMonthOfOrder;
    /**获取当日订单成交总额*/
    private String sumDayOfMoney;
    /**获取当月订单成交总额*/
    private String sumMonthOfMoney;
    /**当日，当月新增会员总数量*/
    private String countDayOfMember;
    /**当月，新增会员总数量*/
    private String countMonthOfMember;
    /** 当日，新增店铺总数*/
    private String countDayOfShop;
    /** 当月，新增店铺总数*/
    private String countMonthOfShop;
    /** 当日总提现金额*/
    private String sumDayOfShopCash;
    /** 当月总提现金额*/
    private String sumMonthOfShopCash;
    /**当日总佣金发放金额*/
    private String sumDayOfMemberCash;
    /**当月总佣金发放金额*/
    private String sumMonthOfMemberCash;
    /**优惠券今日使用量*/
    private String sumDayOfCoupon;
    /**优惠券本月使用量*/
    private String sumMonthOfCoupon;

    public String getSumDayOfCoupon() {
        return sumDayOfCoupon;
    }

    public void setSumDayOfCoupon(String sumDayOfCoupon) {
        this.sumDayOfCoupon = sumDayOfCoupon;
    }

    public String getSumMonthOfCoupon() {
        return sumMonthOfCoupon;
    }

    public void setSumMonthOfCoupon(String sumMonthOfCoupon) {
        this.sumMonthOfCoupon = sumMonthOfCoupon;
    }

    public String getCountDayOfOrder() {
        return countDayOfOrder;
    }

    public void setCountDayOfOrder(String countDayOfOrder) {
        this.countDayOfOrder = countDayOfOrder;
    }

    public String getCountMonthOfOrder() {
        return countMonthOfOrder;
    }

    public void setCountMonthOfOrder(String countMonthOfOrder) {
        this.countMonthOfOrder = countMonthOfOrder;
    }

    public String getSumDayOfMoney() {
        return sumDayOfMoney;
    }

    public void setSumDayOfMoney(String sumDayOfMoney) {
        this.sumDayOfMoney = sumDayOfMoney;
    }

    public String getSumMonthOfMoney() {
        return sumMonthOfMoney;
    }

    public void setSumMonthOfMoney(String sumMonthOfMoney) {
        this.sumMonthOfMoney = sumMonthOfMoney;
    }

    public String getCountDayOfMember() {
        return countDayOfMember;
    }

    public void setCountDayOfMember(String countDayOfMember) {
        this.countDayOfMember = countDayOfMember;
    }

    public String getCountMonthOfMember() {
        return countMonthOfMember;
    }

    public void setCountMonthOfMember(String countMonthOfMember) {
        this.countMonthOfMember = countMonthOfMember;
    }

    public String getCountDayOfShop() {
        return countDayOfShop;
    }

    public void setCountDayOfShop(String countDayOfShop) {
        this.countDayOfShop = countDayOfShop;
    }

    public String getCountMonthOfShop() {
        return countMonthOfShop;
    }

    public void setCountMonthOfShop(String countMonthOfShop) {
        this.countMonthOfShop = countMonthOfShop;
    }

    public String getSumDayOfShopCash() {
        return sumDayOfShopCash;
    }

    public void setSumDayOfShopCash(String sumDayOfShopCash) {
        this.sumDayOfShopCash = sumDayOfShopCash;
    }

    public String getSumMonthOfShopCash() {
        return sumMonthOfShopCash;
    }

    public void setSumMonthOfShopCash(String sumMonthOfShopCash) {
        this.sumMonthOfShopCash = sumMonthOfShopCash;
    }

    public String getSumDayOfMemberCash() {
        return sumDayOfMemberCash;
    }

    public void setSumDayOfMemberCash(String sumDayOfMemberCash) {
        this.sumDayOfMemberCash = sumDayOfMemberCash;
    }

    public String getSumMonthOfMemberCash() {
        return sumMonthOfMemberCash;
    }

    public void setSumMonthOfMemberCash(String sumMonthOfMemberCash) {
        this.sumMonthOfMemberCash = sumMonthOfMemberCash;
    }
}
