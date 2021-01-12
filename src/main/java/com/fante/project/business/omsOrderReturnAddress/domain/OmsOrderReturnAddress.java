package com.fante.project.business.omsOrderReturnAddress.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fante.framework.aspectj.lang.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import com.fante.framework.web.domain.BaseEntity;

/**
 * 店铺收发货地址对象 oms_order_return_address
 * 
 * @author fante
 * @date 2020-04-01
 */
public class OmsOrderReturnAddress extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "null")
    private Long id;

    @ApiModelProperty(value = "店铺id")
    @Excel(name = "店铺id")
    private Long shopId;

    @ApiModelProperty(value = "地址名称")
    @Excel(name = "地址名称")
    private String name;

    @ApiModelProperty(value = "是")
    @Excel(name = "是")
    private String sendStatus;

    @ApiModelProperty(value = "是")
    @Excel(name = "是")
    private String receiveStatus;

    @ApiModelProperty(value = "收发货人姓名")
    @Excel(name = "收发货人姓名")
    private String persionName;

    @ApiModelProperty(value = "收货人电话")
    @Excel(name = "收货人电话")
    private String persionPhone;

    @ApiModelProperty(value = "省/直辖市")
    @Excel(name = "省/直辖市")
    private String province;

    @ApiModelProperty(value = "市")
    @Excel(name = "市")
    private String city;

    @ApiModelProperty(value = "区")
    @Excel(name = "区")
    private String region;

    @ApiModelProperty(value = "详细地址")
    @Excel(name = "详细地址")
    private String detailAddress;

    @ApiModelProperty(value = "删除标记")
    private String delFlag;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getShopId() {
        return shopId;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus;
    }

    public String getSendStatus() {
        return sendStatus;
    }
    public void setReceiveStatus(String receiveStatus) {
        this.receiveStatus = receiveStatus;
    }

    public String getReceiveStatus() {
        return receiveStatus;
    }
    public void setPersionName(String persionName) {
        this.persionName = persionName;
    }

    public String getPersionName() {
        return persionName;
    }
    public void setPersionPhone(String persionPhone) {
        this.persionPhone = persionPhone;
    }

    public String getPersionPhone() {
        return persionPhone;
    }
    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvince() {
        return province;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }
    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegion() {
        return region;
    }
    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getDetailAddress() {
        return detailAddress;
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
            .append("shopId", getShopId())
            .append("name", getName())
            .append("sendStatus", getSendStatus())
            .append("receiveStatus", getReceiveStatus())
            .append("persionName", getPersionName())
            .append("persionPhone", getPersionPhone())
            .append("province", getProvince())
            .append("city", getCity())
            .append("region", getRegion())
            .append("detailAddress", getDetailAddress())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
