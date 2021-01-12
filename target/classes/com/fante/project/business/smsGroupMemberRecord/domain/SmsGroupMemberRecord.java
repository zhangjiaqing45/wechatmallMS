package com.fante.project.business.smsGroupMemberRecord.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fante.framework.aspectj.lang.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import com.fante.framework.web.domain.BaseEntity;
import java.math.BigDecimal;

/**
 * 团购记录人员对象 sms_group_member_record
 * 
 * @author fante
 * @date 2020-05-09
 */
public class SmsGroupMemberRecord extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "退款申请服务号")
    @Excel(name = "退款申请服务号")
    private String returnApplySn;

    @ApiModelProperty(value = "订单号")
    @Excel(name = "订单号")
    private String orderSn;

    @ApiModelProperty(value = "团购活动ID")
    @Excel(name = "团购活动ID")
    private Long groupJoinRecordId;

    @ApiModelProperty(value = "商品sku团购活动id")
    @Excel(name = "商品sku团购活动id")
    private Long groupGameSkuId;

    @ApiModelProperty(value = "退款标记")
    @Excel(name = "退款标记")
    private String returnFlag;

    @ApiModelProperty(value = "参团用户ID")
    @Excel(name = "参团用户ID")
    private Long memberId;

    @ApiModelProperty(value = "用户昵称")
    @Excel(name = "用户昵称")
    private String memberNick;

    @ApiModelProperty(value = "用户头像")
    @Excel(name = "用户头像")
    private String memberIcon;

    @ApiModelProperty(value = "购买数量")
    @Excel(name = "购买数量")
    private Integer quantity;

    @ApiModelProperty(value = "团购时商品名称")
    @Excel(name = "团购时商品名称")
    private String productName;

    @ApiModelProperty(value = "团购时商品规格")
    @Excel(name = "团购时商品规格")
    private String skuSpData;

    @ApiModelProperty(value = "团购时sku图片")
    @Excel(name = "团购时sku图片")
    private String skuPic;

    @ApiModelProperty(value = "团购时价格")
    @Excel(name = "团购时价格")
    private BigDecimal groupPrice;

    @ApiModelProperty(value = "查询团购记录的状态(这不需要设置值)")
    @Excel(name = "查询团购记录的状态(这不需要设置值)")
    private String status;

    @ApiModelProperty(value = "删除标记")
    private String delFlag;

    @ApiModelProperty(value = "团购时商品规格编码")
    @Excel(name = "团购时商品规格编码")
    private String skuCode;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public void setReturnApplySn(String returnApplySn) {
        this.returnApplySn = returnApplySn;
    }

    public String getReturnApplySn() {
        return returnApplySn;
    }
    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public String getOrderSn() {
        return orderSn;
    }
    public void setGroupJoinRecordId(Long groupJoinRecordId) {
        this.groupJoinRecordId = groupJoinRecordId;
    }

    public Long getGroupJoinRecordId() {
        return groupJoinRecordId;
    }
    public void setGroupGameSkuId(Long groupGameSkuId) {
        this.groupGameSkuId = groupGameSkuId;
    }

    public Long getGroupGameSkuId() {
        return groupGameSkuId;
    }
    public void setReturnFlag(String returnFlag) {
        this.returnFlag = returnFlag;
    }

    public String getReturnFlag() {
        return returnFlag;
    }
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getMemberId() {
        return memberId;
    }
    public void setMemberNick(String memberNick) {
        this.memberNick = memberNick;
    }

    public String getMemberNick() {
        return memberNick;
    }
    public void setMemberIcon(String memberIcon) {
        this.memberIcon = memberIcon;
    }

    public String getMemberIcon() {
        return memberIcon;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }
    public void setSkuSpData(String skuSpData) {
        this.skuSpData = skuSpData;
    }

    public String getSkuSpData() {
        return skuSpData;
    }
    public void setSkuPic(String skuPic) {
        this.skuPic = skuPic;
    }

    public String getSkuPic() {
        return skuPic;
    }
    public void setGroupPrice(BigDecimal groupPrice) {
        this.groupPrice = groupPrice;
    }

    public BigDecimal getGroupPrice() {
        return groupPrice;
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
    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getSkuCode() {
        return skuCode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("returnApplySn", getReturnApplySn())
            .append("orderSn", getOrderSn())
            .append("groupJoinRecordId", getGroupJoinRecordId())
            .append("groupGameSkuId", getGroupGameSkuId())
            .append("returnFlag", getReturnFlag())
            .append("memberId", getMemberId())
            .append("memberNick", getMemberNick())
            .append("memberIcon", getMemberIcon())
            .append("quantity", getQuantity())
            .append("productName", getProductName())
            .append("skuSpData", getSkuSpData())
            .append("skuPic", getSkuPic())
            .append("groupPrice", getGroupPrice())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .append("skuCode", getSkuCode())
            .toString();
    }
}
