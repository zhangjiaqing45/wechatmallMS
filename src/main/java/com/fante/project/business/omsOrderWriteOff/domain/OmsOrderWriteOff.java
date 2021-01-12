package com.fante.project.business.omsOrderWriteOff.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fante.framework.aspectj.lang.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import com.fante.framework.web.domain.BaseEntity;

/**
 * 订单核销记录对象 oms_order_write_off
 * 
 * @author fante
 * @date 2020-11-14
 */
public class OmsOrderWriteOff extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "商户订单号")
    @Excel(name = "商户订单号")
    private String outTradeNo;

    @ApiModelProperty(value = "核销人员openID")
    @Excel(name = "核销人员openID")
    private String hxOpenid;

    @ApiModelProperty(value = "核销人员昵称")
    @Excel(name = "核销人员昵称")
    private String hxNickname;

    @ApiModelProperty(value = "消费人员openID")
    @Excel(name = "消费人员openID")
    private String openid;

    @ApiModelProperty(value = "消费人员昵称")
    @Excel(name = "消费人员昵称")
    private String nickname;

    @ApiModelProperty(value = "核销状态：0->核销失败；1->核销成功")
    @Excel(name = "核销状态：0->核销失败；1->核销成功")
    private String status;

    @ApiModelProperty(value = "删除标记")
    private String delFlag;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }
    public void setHxOpenid(String hxOpenid) {
        this.hxOpenid = hxOpenid;
    }

    public String getHxOpenid() {
        return hxOpenid;
    }
    public void setHxNickname(String hxNickname) {
        this.hxNickname = hxNickname;
    }

    public String getHxNickname() {
        return hxNickname;
    }
    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getOpenid() {
        return openid;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
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
            .append("outTradeNo", getOutTradeNo())
            .append("hxOpenid", getHxOpenid())
            .append("hxNickname", getHxNickname())
            .append("openid", getOpenid())
            .append("nickname", getNickname())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
