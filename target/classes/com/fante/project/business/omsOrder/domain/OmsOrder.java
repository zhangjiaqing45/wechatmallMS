package com.fante.project.business.omsOrder.domain;

import com.fante.common.utils.Arith;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fante.framework.aspectj.lang.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import com.fante.framework.web.domain.BaseEntity;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单对象 oms_order
 * 
 * @author fante
 * @date 2020-04-04
 */
public class OmsOrder extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单id")
    private Long id;

    @ApiModelProperty(value = "店铺id")
    @Excel(name = "店铺id")
    private Long shopId;

    @ApiModelProperty(value = "支付订单编号")
    @Excel(name = "支付订单编号")
    private Long payOrderId;

    @ApiModelProperty(value = "订单编号")
    @Excel(name = "订单编号")
    private String orderSn;

    @ApiModelProperty(value = "用户id")
    @Excel(name = "用户id")
    private Long memberId;

    @ApiModelProperty(value = "应付金额（实际支付金额）")
    @Excel(name = "应付金额", readConverterExp = "实=际支付金额")
    private BigDecimal payAmount;

    @ApiModelProperty(value = "商品原始总金额(不包括运费增和券减)")
    @Excel(name = "商品原始总金额(不包括运费增和券减)")
    private BigDecimal productTotalAmount;

    @ApiModelProperty(value = "运费总金额")
    @Excel(name = "运费总金额")
    private BigDecimal freightAmount;

    @ApiModelProperty(value = "优惠券抵扣金额")
    @Excel(name = "优惠券抵扣金额")
    private BigDecimal couponAmount;

    @ApiModelProperty(value = "优惠券类型")
    @Excel(name = "优惠券类型")
    private String couponType;

    @ApiModelProperty(value = "优惠券详情json格式")
   // @Excel(name = "优惠券详情json格式")
    private String couponData;

    @ApiModelProperty(value = "订单类型：1购物车2普通商品3团购4秒杀5积分")
    //@Excel(name = "订单类型：1购物车2普通商品3团购4秒杀5积分")
    private String orderType;

    @ApiModelProperty(value = "活动id")
    //@Excel(name = "活动id")
    private Long gameId;

    @ApiModelProperty(value = "活动信息")
    @Excel(name = "活动信息")
    private String gameInfo;

    @ApiModelProperty(value = "纸质发票")
    //@Excel(name = "纸质发票")
    private String billType;

    @ApiModelProperty(value = "发票抬头")
   // @Excel(name = "发票抬头")
    private String billHeader;

    @ApiModelProperty(value = "发票内容")
    //@Excel(name = "发票内容")
    private String billContent;

    @ApiModelProperty(value = "收票人电话")
    //@Excel(name = "收票人电话")
    private String billReceiverPhone;

    @ApiModelProperty(value = "收票人邮箱")
    //@Excel(name = "收票人邮箱")
    private String billReceiverEmail;

    @ApiModelProperty(value = "收货人姓名")
    //@Excel(name = "收货人姓名")
    private String receiverName;

    @ApiModelProperty(value = "收货人电话")
    //@Excel(name = "收货人电话")
    private String receiverPhone;

    @ApiModelProperty(value = "收货人邮编")
    //@Excel(name = "收货人邮编")
    private String receiverPostCode;

    @ApiModelProperty(value = "省份/直辖市")
    //@Excel(name = "省份/直辖市")
    private String receiverProvince;

    @ApiModelProperty(value = "城市")
    //@Excel(name = "城市")
    private String receiverCity;

    @ApiModelProperty(value = "区")
    //@Excel(name = "区")
    private String receiverRegion;

    @ApiModelProperty(value = "详细地址")
    //@Excel(name = "详细地址")
    private String receiverDetailAddress;

    @ApiModelProperty(value = "订单备注")
    @Excel(name = "订单备注")
    private String note;

    @ApiModelProperty(value = "支付状态：0未支付1已支付2已退款")
    @Excel(name = "支付状态：0未支付1已支付2已退款")
    private String payStatus;

    @ApiModelProperty(value = "支付类型:1->线上付款；2->货到付款")
    @Excel(name = "支付类型")
    private String paymentType;

    @ApiModelProperty(value = "订单状态：0->待付款;1->待发货；2->已发货；3->已完成；4->已关闭；5->已退货6->无效订单7->等组团")
    @Excel(name = "订单状态：0->待付款;1->待发货；2->已发货；3->已完成；4->已关闭；5->已退货6->无效订单7->等组团")
    private String status;
    @ApiModelProperty(value = "核销状态:-1->核销码未生成；0->未核销；1->已核销")
    @Excel(name = "核销状态")
    private String writeOffStatus;


    @ApiModelProperty(value = "支付时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Excel(name = "支付时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date paymentTime;

    @ApiModelProperty(value = "发货时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
   // @Excel(name = "发货时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date deliveryTime;

    @ApiModelProperty(value = "确认收货时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    //@Excel(name = "确认收货时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date receiveTime;

    @ApiModelProperty(value = "自动确认时间（天）")
    //@Excel(name = "自动确认时间", readConverterExp = "天=")
    private Long autoConfirmDay;

    @ApiModelProperty(value = "评价时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Excel(name = "评价时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date commentTime;

    @ApiModelProperty(value = "删除标记")
    private String delFlag;

    @ApiModelProperty(value = "可以获得的积分")
    //@Excel(name = "可以获得的积分")
    private Long integration;

    @ApiModelProperty(value = "可以活动的成长值")
   // @Excel(name = "可以活动的成长值")
    private Long growth;

    @ApiModelProperty(value = "已确认")
    //@Excel(name = "已确认")
    private String confirmStatus;

    @ApiModelProperty(value = "物流公司(配送方式)")
    //@Excel(name = "物流公司(配送方式)")
    private String deliveryCompany;

    @ApiModelProperty(value = "物流单号")
    //@Excel(name = "物流单号")
    private String deliverySn;

    private String beginCreateTime;
    private String endCreateTime;
    private String payOrderSn;
    /**
     * 累加运费
     * @param freight
     */
    public void addFreightAmount(BigDecimal freight){
        if(freight == null){ return;}
        this.freightAmount = this.freightAmount==null?
            BigDecimal.ZERO.add(freight)
                    :this.freightAmount.add(freight);
    }

    /**
     * 计算应付价格
     * 商品总价格 + 运费 - 优惠券价格
     */
    public void countPayAmount(){
        if(this.productTotalAmount==null){
            this.payAmount = BigDecimal.ZERO;
            return;
        }
        if(this.productTotalAmount==null){
            this.freightAmount = BigDecimal.ZERO;
        }
        if(this.couponAmount==null){
            this.couponAmount = BigDecimal.ZERO;
        }
        this.payAmount = Arith.sub(Arith.add(this.productTotalAmount,this.freightAmount),this.couponAmount);
    }
    /**
     * 累加金额
     * @param promotionPrice
     */
    public void addProductTotalAmount(BigDecimal promotionPrice) {
        if(promotionPrice==null){return;}
        if(this.productTotalAmount==null){
            this.productTotalAmount=promotionPrice;
        }else{
            this.productTotalAmount=this.productTotalAmount.add(promotionPrice);
        }
    }

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
    public void setPayOrderId(Long payOrderId) {
        this.payOrderId = payOrderId;
    }

    public Long getPayOrderId() {
        return payOrderId;
    }
    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public String getOrderSn() {
        return orderSn;
    }
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getMemberId() {
        return memberId;
    }
    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }
    public void setProductTotalAmount(BigDecimal productTotalAmount) {
        this.productTotalAmount = productTotalAmount;
    }

    public BigDecimal getProductTotalAmount() {
        return productTotalAmount;
    }
    public void setFreightAmount(BigDecimal freightAmount) {
        this.freightAmount = freightAmount;
    }

    public BigDecimal getFreightAmount() {
        return freightAmount;
    }
    public void setCouponAmount(BigDecimal couponAmount) {
        this.couponAmount = couponAmount;
    }

    public BigDecimal getCouponAmount() {
        return couponAmount;
    }
    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

    public String getCouponType() {
        return couponType;
    }
    public void setCouponData(String couponData) {
        this.couponData = couponData;
    }

    public String getCouponData() {
        return couponData;
    }
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderType() {
        return orderType;
    }
    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public Long getGameId() {
        return gameId;
    }
    public void setGameInfo(String gameInfo) {
        this.gameInfo = gameInfo;
    }

    public String getGameInfo() {
        return gameInfo;
    }
    public void setBillType(String billType) {
        this.billType = billType;
    }

    public String getBillType() {
        return billType;
    }
    public void setBillHeader(String billHeader) {
        this.billHeader = billHeader;
    }

    public String getBillHeader() {
        return billHeader;
    }
    public void setBillContent(String billContent) {
        this.billContent = billContent;
    }

    public String getBillContent() {
        return billContent;
    }
    public void setBillReceiverPhone(String billReceiverPhone) {
        this.billReceiverPhone = billReceiverPhone;
    }

    public String getBillReceiverPhone() {
        return billReceiverPhone;
    }
    public void setBillReceiverEmail(String billReceiverEmail) {
        this.billReceiverEmail = billReceiverEmail;
    }

    public String getBillReceiverEmail() {
        return billReceiverEmail;
    }
    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverName() {
        return receiverName;
    }
    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }
    public void setReceiverPostCode(String receiverPostCode) {
        this.receiverPostCode = receiverPostCode;
    }

    public String getReceiverPostCode() {
        return receiverPostCode;
    }
    public void setReceiverProvince(String receiverProvince) {
        this.receiverProvince = receiverProvince;
    }

    public String getReceiverProvince() {
        return receiverProvince;
    }
    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity;
    }

    public String getReceiverCity() {
        return receiverCity;
    }
    public void setReceiverRegion(String receiverRegion) {
        this.receiverRegion = receiverRegion;
    }

    public String getReceiverRegion() {
        return receiverRegion;
    }
    public void setReceiverDetailAddress(String receiverDetailAddress) {
        this.receiverDetailAddress = receiverDetailAddress;
    }

    public String getReceiverDetailAddress() {
        return receiverDetailAddress;
    }
    public void setNote(String note) {
        this.note = note;
    }

    public String getNote() {
        return note;
    }
    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getPayStatus() {
        return payStatus;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }
    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }
    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }
    public void setAutoConfirmDay(Long autoConfirmDay) {
        this.autoConfirmDay = autoConfirmDay;
    }

    public Long getAutoConfirmDay() {
        return autoConfirmDay;
    }
    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public Date getCommentTime() {
        return commentTime;
    }
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }
    public void setIntegration(Long integration) {
        this.integration = integration;
    }

    public Long getIntegration() {
        return integration;
    }
    public void setGrowth(Long growth) {
        this.growth = growth;
    }

    public Long getGrowth() {
        return growth;
    }
    public void setConfirmStatus(String confirmStatus) {
        this.confirmStatus = confirmStatus;
    }

    public String getConfirmStatus() {
        return confirmStatus;
    }
    public void setDeliveryCompany(String deliveryCompany) {
        this.deliveryCompany = deliveryCompany;
    }

    public String getDeliveryCompany() {
        return deliveryCompany;
    }
    public void setDeliverySn(String deliverySn) {
        this.deliverySn = deliverySn;
    }

    public String getDeliverySn() {
        return deliverySn;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getWriteOffStatus() {
        return writeOffStatus;
    }

    public void setWriteOffStatus(String writeOffStatus) {
        this.writeOffStatus = writeOffStatus;
    }

    public String getBeginCreateTime() {
        return beginCreateTime;
    }

    public void setBeginCreateTime(String beginCreateTime) {
        this.beginCreateTime = beginCreateTime;
    }

    public String getEndCreateTime() {
        return endCreateTime;
    }

    public void setEndCreateTime(String endCreateTime) {
        this.endCreateTime = endCreateTime;
    }

    public String getPayOrderSn() {
        return payOrderSn;
    }

    public void setPayOrderSn(String payOrderSn) {
        this.payOrderSn = payOrderSn;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("shopId", getShopId())
            .append("payOrderId", getPayOrderId())
            .append("orderSn", getOrderSn())
            .append("memberId", getMemberId())
            .append("payAmount", getPayAmount())
            .append("productTotalAmount", getProductTotalAmount())
            .append("freightAmount", getFreightAmount())
            .append("couponAmount", getCouponAmount())
            .append("couponType", getCouponType())
            .append("couponData", getCouponData())
            .append("orderType", getOrderType())
            .append("gameId", getGameId())
            .append("gameInfo", getGameInfo())
            .append("billType", getBillType())
            .append("billHeader", getBillHeader())
            .append("billContent", getBillContent())
            .append("billReceiverPhone", getBillReceiverPhone())
            .append("billReceiverEmail", getBillReceiverEmail())
            .append("receiverName", getReceiverName())
            .append("receiverPhone", getReceiverPhone())
            .append("receiverPostCode", getReceiverPostCode())
            .append("receiverProvince", getReceiverProvince())
            .append("receiverCity", getReceiverCity())
            .append("receiverRegion", getReceiverRegion())
            .append("receiverDetailAddress", getReceiverDetailAddress())
            .append("note", getNote())
            .append("payStatus", getPayStatus())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("paymentTime", getPaymentTime())
            .append("deliveryTime", getDeliveryTime())
            .append("receiveTime", getReceiveTime())
            .append("autoConfirmDay", getAutoConfirmDay())
            .append("commentTime", getCommentTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .append("integration", getIntegration())
            .append("growth", getGrowth())
            .append("confirmStatus", getConfirmStatus())
            .append("deliveryCompany", getDeliveryCompany())
            .append("deliverySn", getDeliverySn())
            .toString();
    }
}
