package com.fante.project.business.smsCoupon.domain;

import com.fante.common.utils.bean.BeanUtils;
import com.fante.framework.aspectj.lang.annotation.Excel;
import com.fante.project.business.smsCouponHistory.domain.SmsCouponHistoryDTO;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ftnet
 * @Description 导出使用的表
 * @CreatTime 2020/6/24
 */
public class SmsCouponExport implements Serializable {

    private static final long serialVersionUID = 1L;

    @Excel(name = "店铺名称", width = 30)
    private String shopName;

    @Excel(name = "优惠券名称", width = 30)
    private String couponName;

    @Excel(name = "优惠券码", width = 40)
    private String code;

    @Excel(name = "优惠券类型",readConverterExp = "0=满减券,1=折扣券")
    private String couponType;
/**************************************上面是优惠券信息****************************************/
/**************************************下面是优惠券领取历史信息****************************************/
    @Excel(name = "领取人")
    private String userName;

    @Excel(name = "领取时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createTime;

    @Excel(name = "核销码", width = 40)
    private String couponCode;

    @Excel(name = "使用状态",readConverterExp = "0=未使用,1=已使用")
    private Integer useStatus;

    @Excel(name = "核销员")
    private String updateBy;

    @Excel(name = "使用时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date useTime;

    public SmsCouponExport(String shopName, String couponName, String code, String couponType, SmsCouponHistoryDTO historyDTO) {
        this.shopName = shopName;
        this.couponName = couponName;
        this.code = code;
        this.couponType = couponType;
        BeanUtils.copyProperties(historyDTO,this);
    }

    public SmsCouponExport() {
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public Integer getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(Integer useStatus) {
        this.useStatus = useStatus;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUseTime() {
        return useTime;
    }

    public void setUseTime(Date useTime) {
        this.useTime = useTime;
    }
}
