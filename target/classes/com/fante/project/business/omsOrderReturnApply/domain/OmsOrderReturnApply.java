package com.fante.project.business.omsOrderReturnApply.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fante.framework.aspectj.lang.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import com.fante.framework.web.domain.BaseEntity;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单退货申请对象 oms_order_return_apply
 * 
 * @author fante
 * @date 2020-05-09
 */
public class OmsOrderReturnApply extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "$column.columnComment")
    private Long id;

    @ApiModelProperty(value = "店铺id")
    @Excel(name = "店铺id")
    private Long shopId;

    @ApiModelProperty(value = "退货服务单号")
    @Excel(name = "退货服务单号")
    private String returnApplySn;

    @ApiModelProperty(value = "订单id")
    @Excel(name = "订单id")
    private Long orderId;

    @ApiModelProperty(value = "商品规格id")
    @Excel(name = "商品规格id")
    private Long skuId;

    @ApiModelProperty(value = "订单详情id")
    @Excel(name = "订单详情id")
    private Long orderItemId;

    @ApiModelProperty(value = "用户id")
    @Excel(name = "用户id")
    private Long memberId;

    @ApiModelProperty(value = "退货商品id")
    @Excel(name = "退货商品id")
    private Long productId;

    @ApiModelProperty(value = "商品图片")
    @Excel(name = "商品图片")
    private String productPic;

    @ApiModelProperty(value = "商品货号")
    @Excel(name = "商品货号")
    private String productSn;

    @ApiModelProperty(value = "商品名称")
    @Excel(name = "商品名称")
    private String productName;

    @ApiModelProperty(value = "商品品牌")
    @Excel(name = "商品品牌")
    private String productBrand;

    @ApiModelProperty(value = "商品规格")
    @Excel(name = "商品规格")
    private String spData;

    @ApiModelProperty(value = "退货数量")
    @Excel(name = "退货数量")
    private Integer productCount;

    @ApiModelProperty(value = "商品单价")
    @Excel(name = "商品单价")
    private BigDecimal productPrice;

    @ApiModelProperty(value = "商品实际支付单价")
    @Excel(name = "商品实际支付单价")
    private BigDecimal productRealPrice;

    @ApiModelProperty(value = "申请状态：0->待处理；1->退货中；2->已完成；3->已拒绝")
    @Excel(name = "申请状态：0->待处理；1->退货中；2->已完成；3->已拒绝")
    private String status;

    @ApiModelProperty(value = "订单编号")
    @Excel(name = "订单编号")
    private String orderSn;

    @ApiModelProperty(value = "用户昵称")
    @Excel(name = "用户昵称")
    private String memberNickName;

    @ApiModelProperty(value = "退货人姓名")
    @Excel(name = "退货人姓名")
    private String returnName;

    @ApiModelProperty(value = "退货人电话")
    @Excel(name = "退货人电话")
    private String returnPhone;

    @ApiModelProperty(value = "原因")
    @Excel(name = "原因")
    private String reason;

    @ApiModelProperty(value = "描述")
    @Excel(name = "描述")
    private String description;

    @ApiModelProperty(value = "凭证图片，以逗号隔开")
    @Excel(name = "凭证图片，以逗号隔开")
    private String proofPics;

    @ApiModelProperty(value = "退款金额")
    @Excel(name = "退款金额")
    private BigDecimal returnAmount;

    @ApiModelProperty(value = "退货地址表id")
    @Excel(name = "退货地址表id")
    private Long companyAddressId;

    @ApiModelProperty(value = "退货接受人")
    @Excel(name = "退货接受人")
    private String receiveName;

    @ApiModelProperty(value = "退货接受电话")
    @Excel(name = "退货接受电话")
    private String receivePhone;

    @ApiModelProperty(value = "退货接受区域")
    @Excel(name = "退货接受区域")
    private String receiveArea;

    @ApiModelProperty(value = "退货接受地址")
    @Excel(name = "退货接受地址")
    private String receiveAddr;

    @ApiModelProperty(value = "处理备注")
    @Excel(name = "处理备注")
    private String handleNote;

    @ApiModelProperty(value = "处理时间")
    @Excel(name = "处理时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date handleTime;

    @ApiModelProperty(value = "处理人员")
    @Excel(name = "处理人员")
    private String handleMan;

    @ApiModelProperty(value = "收货操作人")
    @Excel(name = "收货操作人")
    private String receiveMan;

    @ApiModelProperty(value = "收货时间")
    @Excel(name = "收货时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date receiveTime;

    @ApiModelProperty(value = "收货备注")
    @Excel(name = "收货备注")
    private String receiveNote;

    @ApiModelProperty(value = "删除标记")
    private String delFlag;

    @ApiModelProperty(value = "物流公司(配送方式)")
    @Excel(name = "物流公司(配送方式)")
    private String deliveryCompany;

    @ApiModelProperty(value = "物流单号")
    @Excel(name = "物流单号")
    private String deliverySn;

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
    public void setReturnApplySn(String returnApplySn) {
        this.returnApplySn = returnApplySn;
    }

    public String getReturnApplySn() {
        return returnApplySn;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }
    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Long getSkuId() {
        return skuId;
    }
    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Long getOrderItemId() {
        return orderItemId;
    }
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getMemberId() {
        return memberId;
    }
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }
    public void setProductPic(String productPic) {
        this.productPic = productPic;
    }

    public String getProductPic() {
        return productPic;
    }
    public void setProductSn(String productSn) {
        this.productSn = productSn;
    }

    public String getProductSn() {
        return productSn;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }
    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getProductBrand() {
        return productBrand;
    }
    public void setSpData(String spData) {
        this.spData = spData;
    }

    public String getSpData() {
        return spData;
    }
    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public Integer getProductCount() {
        return productCount;
    }
    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }
    public void setProductRealPrice(BigDecimal productRealPrice) {
        this.productRealPrice = productRealPrice;
    }

    public BigDecimal getProductRealPrice() {
        return productRealPrice;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public String getOrderSn() {
        return orderSn;
    }
    public void setMemberNickName(String memberNickName) {
        this.memberNickName = memberNickName;
    }

    public String getMemberNickName() {
        return memberNickName;
    }
    public void setReturnName(String returnName) {
        this.returnName = returnName;
    }

    public String getReturnName() {
        return returnName;
    }
    public void setReturnPhone(String returnPhone) {
        this.returnPhone = returnPhone;
    }

    public String getReturnPhone() {
        return returnPhone;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
    public void setProofPics(String proofPics) {
        this.proofPics = proofPics;
    }

    public String getProofPics() {
        return proofPics;
    }
    public void setReturnAmount(BigDecimal returnAmount) {
        this.returnAmount = returnAmount;
    }

    public BigDecimal getReturnAmount() {
        return returnAmount;
    }
    public void setCompanyAddressId(Long companyAddressId) {
        this.companyAddressId = companyAddressId;
    }

    public Long getCompanyAddressId() {
        return companyAddressId;
    }
    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getReceiveName() {
        return receiveName;
    }
    public void setReceivePhone(String receivePhone) {
        this.receivePhone = receivePhone;
    }

    public String getReceivePhone() {
        return receivePhone;
    }
    public void setReceiveArea(String receiveArea) {
        this.receiveArea = receiveArea;
    }

    public String getReceiveArea() {
        return receiveArea;
    }
    public void setReceiveAddr(String receiveAddr) {
        this.receiveAddr = receiveAddr;
    }

    public String getReceiveAddr() {
        return receiveAddr;
    }
    public void setHandleNote(String handleNote) {
        this.handleNote = handleNote;
    }

    public String getHandleNote() {
        return handleNote;
    }
    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }

    public Date getHandleTime() {
        return handleTime;
    }
    public void setHandleMan(String handleMan) {
        this.handleMan = handleMan;
    }

    public String getHandleMan() {
        return handleMan;
    }
    public void setReceiveMan(String receiveMan) {
        this.receiveMan = receiveMan;
    }

    public String getReceiveMan() {
        return receiveMan;
    }
    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Date getReceiveTime() {
        return receiveTime;
    }
    public void setReceiveNote(String receiveNote) {
        this.receiveNote = receiveNote;
    }

    public String getReceiveNote() {
        return receiveNote;
    }
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlag() {
        return delFlag;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("shopId", getShopId())
            .append("returnApplySn", getReturnApplySn())
            .append("orderId", getOrderId())
            .append("skuId", getSkuId())
            .append("orderItemId", getOrderItemId())
            .append("memberId", getMemberId())
            .append("productId", getProductId())
            .append("productPic", getProductPic())
            .append("productSn", getProductSn())
            .append("productName", getProductName())
            .append("productBrand", getProductBrand())
            .append("spData", getSpData())
            .append("productCount", getProductCount())
            .append("productPrice", getProductPrice())
            .append("productRealPrice", getProductRealPrice())
            .append("status", getStatus())
            .append("orderSn", getOrderSn())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("memberNickName", getMemberNickName())
            .append("returnName", getReturnName())
            .append("returnPhone", getReturnPhone())
            .append("reason", getReason())
            .append("description", getDescription())
            .append("proofPics", getProofPics())
            .append("returnAmount", getReturnAmount())
            .append("companyAddressId", getCompanyAddressId())
            .append("receiveName", getReceiveName())
            .append("receivePhone", getReceivePhone())
            .append("receiveArea", getReceiveArea())
            .append("receiveAddr", getReceiveAddr())
            .append("handleNote", getHandleNote())
            .append("handleTime", getHandleTime())
            .append("handleMan", getHandleMan())
            .append("receiveMan", getReceiveMan())
            .append("receiveTime", getReceiveTime())
            .append("receiveNote", getReceiveNote())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .append("deliveryCompany", getDeliveryCompany())
            .append("deliverySn", getDeliverySn())
            .toString();
    }
}
