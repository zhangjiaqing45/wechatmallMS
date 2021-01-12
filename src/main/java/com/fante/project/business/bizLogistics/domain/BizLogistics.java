package com.fante.project.business.bizLogistics.domain;

import com.fante.framework.aspectj.lang.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 物流信息对象 biz_logistics
 *
 * @author fante
 * @date 2020-02-06
 */
public class BizLogistics {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 物流单号
     */
    @Excel(name = "物流单号")
    private String number;

    /**
     * 快递公司代码(选填)
     */
    @Excel(name = "快递公司代码(选填)")
    private String type;

    /**
     * 返回状态
     */
    @Excel(name = "返回状态")
    private String status;

    /**
     * 返回标识
     */
    @Excel(name = "返回标识")
    private String msg;

    /**
     * 返回详细信息集合
     */
    @Excel(name = "返回详细信息集合")
    private String resuleList;

    /**
     * 快件状态（0：快递收件(揽件)1.在途中 2.正在派件 3.已签收 4.派送失败 5.疑难件 6.退件签收 ）
     */
    @Excel(name = "快件状态", readConverterExp = "0=：快递收件(揽件)1.在途中,2=.正在派件,3=.已签收,4=.派送失败,5=.疑难件,6=.退件签收")
    private String deliverystatus;

    /**
     * 是否签收
     */
    @Excel(name = "是否签收")
    private String issign;

    /**
     * 快递公共名称
     */
    @Excel(name = "快递公共名称")
    private String expName;

    /**
     * 快递公司官网
     */
    @Excel(name = "快递公司官网")
    private String expSite;

    /**
     * 快递公司电话
     */
    @Excel(name = "快递公司电话")
    private String expPhone;

    /**
     * 快递员
     */
    @Excel(name = "快递员")
    private String courier;

    /**
     * 快递员电话
     */
    @Excel(name = "快递员电话")
    private String courierPhone;

    /**
     * 快递轨迹信息最新更新时间
     */
    @Excel(name = "快递轨迹信息最新更新时间")
    private String updateTime;

    /**
     * 发货到收获消耗时常
     */
    @Excel(name = "发货到收获消耗时常")
    private String takeTime;

    /**
     * 入库更新时间
     */
    @Excel(name = "入库更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Long time;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setResuleList(String resuleList) {
        this.resuleList = resuleList;
    }

    public String getResuleList() {
        return resuleList;
    }

    public void setDeliverystatus(String deliverystatus) {
        this.deliverystatus = deliverystatus;
    }

    public String getDeliverystatus() {
        return deliverystatus;
    }

    public void setIssign(String issign) {
        this.issign = issign;
    }

    public String getIssign() {
        return issign;
    }

    public void setCourier(String courier) {
        this.courier = courier;
    }

    public String getCourier() {
        return courier;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getExpName() {
        return expName;
    }

    public void setExpName(String expName) {
        this.expName = expName;
    }

    public String getExpSite() {
        return expSite;
    }

    public void setExpSite(String expSite) {
        this.expSite = expSite;
    }

    public String getExpPhone() {
        return expPhone;
    }

    public void setExpPhone(String expPhone) {
        this.expPhone = expPhone;
    }

    public String getCourierPhone() {
        return courierPhone;
    }

    public void setCourierPhone(String courierPhone) {
        this.courierPhone = courierPhone;
    }

    public String getTakeTime() {
        return takeTime;
    }

    public void setTakeTime(String takeTime) {
        this.takeTime = takeTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("number", getNumber())
                .append("type", getType())
                .append("status", getStatus())
                .append("msg", getMsg())
                .append("resuleList", getResuleList())
                .append("deliverystatus", getDeliverystatus())
                .append("issign", getIssign())
                .append("expName", getExpName())
                .append("expSite", getExpSite())
                .append("expPhone", getExpPhone())
                .append("courier", getCourier())
                .append("courierPhone", getCourierPhone())
                .append("updateTime", getUpdateTime())
                .append("takeTime", getTakeTime())
                .append("time", getTime())
                .toString();
    }
}
