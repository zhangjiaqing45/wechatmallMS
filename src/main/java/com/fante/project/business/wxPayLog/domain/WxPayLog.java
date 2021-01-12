package com.fante.project.business.wxPayLog.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fante.framework.aspectj.lang.annotation.Excel;
import com.fante.framework.web.domain.BaseEntity;

/**
 * 微信支付日志对象 wx_pay_log
 * 
 * @author fante
 * @date 2020-02-21
 */
public class WxPayLog extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 日志状态 */
    @Excel(name = "日志状态")
    private String status;

    /** 日志类型 */
    @Excel(name = "日志类型")
    private String type;

    /** 用户标识 */
    @Excel(name = "用户标识")
    private String openid;

    /** 商户订单号 */
    @Excel(name = "商户订单号")
    private String outTradeNo;

    /** 标价金额 */
    @Excel(name = "标价金额")
    private String totalFee;

    /** 提示信息 */
    @Excel(name = "提示信息")
    private String msg;

    /** 调用信息 */
    @Excel(name = "调用信息")
    private String content;

    /** 删除标记 */
    private String delFlag;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getOpenid() {
        return openid;
    }
    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }
    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    public String getTotalFee() {
        return totalFee;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("status", getStatus())
            .append("type", getType())
            .append("openid", getOpenid())
            .append("outTradeNo", getOutTradeNo())
            .append("totalFee", getTotalFee())
            .append("msg", getMsg())
            .append("content", getContent())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
