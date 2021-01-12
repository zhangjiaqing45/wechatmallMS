package com.fante.project.business.bizShopWriteExport.domain;

import com.fante.framework.aspectj.lang.annotation.Excel;
import com.fante.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

/**
 * @program: Fante
 * @Date: 2020年9月2日10:03:44
 * @Author: Mr.Jin
 * @Description: 商城数据汇总DTO
 */
public class BizShopWriteDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "管理店铺")
    @Excel(name = "管理店铺")
    private String deptName;

    @ApiModelProperty(value = "账户账号")
    @Excel(name = "账户账号")
    private String loginName;

    @ApiModelProperty(value = "账户名称")
    @Excel(name = "账户名称")
    private String userName;

    @ApiModelProperty(value = "手机号码")
    @Excel(name = "手机号码")
    private String phonenumber;

    @ApiModelProperty(value = "区域")
    @Excel(name = "区域")
    private String region;

    @ApiModelProperty(value = "商品数量")
    @Excel(name = "商品数量")
    private String pmscount;

    @ApiModelProperty(value = "优惠卷核销数量")
    @Excel(name = "优惠卷核销数量")
    private String smscount;

    @ApiModelProperty(value = "开始日期查询")
    private String beginTime;

    @ApiModelProperty(value = "结束日期查询")
    private String endTime;


    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getPmscount() {
        return pmscount;
    }

    public void setPmscount(String pmscount) {
        this.pmscount = pmscount;
    }

    public String getSmscount() {
        return smscount;
    }

    public void setSmscount(String smscount) {
        this.smscount = smscount;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }



    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }
}