package com.fante.project.business.umsMemberReceiveAddress.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fante.framework.aspectj.lang.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import com.fante.framework.web.domain.BaseEntity;

/**
 * 会员收货地址对象 ums_member_receive_address
 * 
 * @author fante
 * @date 2020-04-10
 */
public class UmsMemberReceiveAddress extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "$column.columnComment")
    private Long id;

    @ApiModelProperty(value = "会员id")
    @Excel(name = "会员id")
    private Long memberId;

    @ApiModelProperty(value = "收货人名称")
    @Excel(name = "收货人名称")
    private String name;

    @ApiModelProperty(value = "收货电话")
    @Excel(name = "收货电话")
    private String phoneNumber;

    @ApiModelProperty(value = "是否为默认")
    @Excel(name = "是否为默认")
    private String status;

    @ApiModelProperty(value = "邮政编码")
    @Excel(name = "邮政编码")
    private String postCode;

    @ApiModelProperty(value = "省份/直辖市")
    @Excel(name = "省份/直辖市")
    private String province;

    @ApiModelProperty(value = "城市")
    @Excel(name = "城市")
    private String city;

    @ApiModelProperty(value = "区")
    @Excel(name = "区")
    private String region;

    @ApiModelProperty(value = "详细地址(街道)")
    @Excel(name = "详细地址(街道)")
    private String detailAddress;

    @ApiModelProperty(value = "删除标记")
    private String delFlag;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getMemberId() {
        return memberId;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPostCode() {
        return postCode;
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
            .append("memberId", getMemberId())
            .append("name", getName())
            .append("phoneNumber", getPhoneNumber())
            .append("status", getStatus())
            .append("postCode", getPostCode())
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
