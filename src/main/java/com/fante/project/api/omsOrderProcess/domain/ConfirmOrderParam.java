package com.fante.project.api.omsOrderProcess.domain;

import com.fante.project.business.umsMember.domain.UmsMember;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author ftnet
 * @Description ConfirmOrderParam
 * @CreatTime 2020/4/14
 */
public class ConfirmOrderParam implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "用户")
    private UmsMember member;
    @ApiModelProperty(value = "收货地址id")
    private Long memberReceiveAddressId;
    @ApiModelProperty(value = "支付方式")
    private Integer payType;
    @ApiModelProperty(value = "购买方式")
    private String paymentType;
    @ApiModelProperty(value = "优惠券id/团购skuid/秒杀sku关系表id")
    private Long promotionId;
    @ApiModelProperty(value = "参团的id")
    private Long inGroupId;
    @ApiModelProperty(value = "留言")
    private String note;
    @ApiModelProperty(value = "商品规格ids")
    private String skuIds;
    @ApiModelProperty(value = "购买数量")
    private Long quantity;
    @ApiModelProperty(value = "购物车ids")
    private String cartIds;
    @ApiModelProperty(value = "发票类型")
    private String billType;
    @ApiModelProperty(value = "发票抬头")
    private String billHeader;
    @ApiModelProperty(value = "发票内容")
    private String BillContent;
    @ApiModelProperty(value = "收票人电话")
    private String billReceiverPhone;
    @ApiModelProperty(value = "收票人邮箱")
    private String billReceiverEmail;

    public Long getInGroupId() {
        return inGroupId;
    }

    public void setInGroupId(Long inGroupId) {
        this.inGroupId = inGroupId;
    }

    public UmsMember getMember() {
        return member;
    }

    public void setMember(UmsMember member) {
        this.member = member;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public String getBillHeader() {
        return billHeader;
    }

    public void setBillHeader(String billHeader) {
        this.billHeader = billHeader;
    }

    public String getBillContent() {
        return BillContent;
    }

    public void setBillContent(String billContent) {
        BillContent = billContent;
    }

    public String getBillReceiverPhone() {
        return billReceiverPhone;
    }

    public void setBillReceiverPhone(String billReceiverPhone) {
        this.billReceiverPhone = billReceiverPhone;
    }

    public String getBillReceiverEmail() {
        return billReceiverEmail;
    }

    public void setBillReceiverEmail(String billReceiverEmail) {
        this.billReceiverEmail = billReceiverEmail;
    }

    public Long getMemberReceiveAddressId() {
        return memberReceiveAddressId;
    }

    public void setMemberReceiveAddressId(Long memberReceiveAddressId) {
        this.memberReceiveAddressId = memberReceiveAddressId;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Long getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Long promotionId) {
        this.promotionId = promotionId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getSkuIds() {
        return skuIds;
    }

    public void setSkuIds(String skuIds) {
        this.skuIds = skuIds;
    }

    public String getCartIds() {
        return cartIds;
    }

    public void setCartIds(String cartIds) {
        this.cartIds = cartIds;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
}
