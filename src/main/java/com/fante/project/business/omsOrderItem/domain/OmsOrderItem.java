package com.fante.project.business.omsOrderItem.domain;

import com.fante.common.utils.Arith;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fante.framework.aspectj.lang.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import com.fante.framework.web.domain.BaseEntity;
import java.math.BigDecimal;

/**
 * 订单中所包含的商品对象 oms_order_item
 * 
 * @author fante
 * @date 2020-05-05
 */
public class OmsOrderItem extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "null")
    private Long id;

    @ApiModelProperty(value = "订单id")
    @Excel(name = "订单id")
    private Long orderId;

    @ApiModelProperty(value = "订单号")
    @Excel(name = "订单号")
    private String orderSn;

    @ApiModelProperty(value = "商品id")
    @Excel(name = "商品id")
    private Long productId;

    @ApiModelProperty(value = "优惠券id/团购skuid/秒杀skuid")
    @Excel(name = "优惠券id/团购skuid/秒杀skuid")
    private Long gameSkuId;

    @ApiModelProperty(value = "商品主图")
    @Excel(name = "商品主图")
    private String productPic;

    @ApiModelProperty(value = "商品名称")
    @Excel(name = "商品名称")
    private String productName;

    @ApiModelProperty(value = "规格属性")
    @Excel(name = "规格属性")
    private String productSpData;

    @ApiModelProperty(value = "商品品牌")
    @Excel(name = "商品品牌")
    private String productBrand;

    @ApiModelProperty(value = "商品货号")
    @Excel(name = "商品货号")
    private String productSn;

    @ApiModelProperty(value = "支付价格")
    @Excel(name = "支付价格")
    private BigDecimal payPrice;

    @ApiModelProperty(value = "销售价格")
    @Excel(name = "销售价格")
    private BigDecimal productPrice;

    @ApiModelProperty(value = "运费总金额")
    @Excel(name = "运费总金额")
    private BigDecimal freightPrice;

    @ApiModelProperty(value = "优惠价格")
    @Excel(name = "优惠价格")
    private BigDecimal couponPrice;

    @ApiModelProperty(value = "购买数量")
    @Excel(name = "购买数量")
    private Long productQuantity;

    @ApiModelProperty(value = "商品sku编号")
    @Excel(name = "商品sku编号")
    private Long productSkuId;

    @ApiModelProperty(value = "商品sku条码")
    @Excel(name = "商品sku条码")
    private String productSkuCode;

    @ApiModelProperty(value = "商品分类id")
    @Excel(name = "商品分类id")
    private Long productCategoryId;

    @ApiModelProperty(value = "商品促销信息")
    @Excel(name = "商品促销信息")
    private String promotionInfo;

    @ApiModelProperty(value = "原始价格(团购前/秒杀前)价格")
    @Excel(name = "原始价格(团购前/秒杀前)价格")
    private BigDecimal initPrice;

    @ApiModelProperty(value = "商品赠送积分")
    @Excel(name = "商品赠送积分")
    private Long giftIntegration;

    @ApiModelProperty(value = "商品赠送成长值")
    @Excel(name = "商品赠送成长值")
    private Long giftGrowth;

    @ApiModelProperty(value = "退货状态1:已退货")
    @Excel(name = "退货状态1:已退货")
    private String returnStatus;

    @ApiModelProperty(value = "评论状态")
    @Excel(name = "评论状态")
    private String status;

    @ApiModelProperty(value = "删除标记")
    private String delFlag;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }
    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public String getOrderSn() {
        return orderSn;
    }
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }
    public void setGameSkuId(Long gameSkuId) {
        this.gameSkuId = gameSkuId;
    }

    public Long getGameSkuId() {
        return gameSkuId;
    }
    public void setProductPic(String productPic) {
        this.productPic = productPic;
    }

    public String getProductPic() {
        return productPic;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }
    public void setProductSpData(String productSpData) {
        this.productSpData = productSpData;
    }

    public String getProductSpData() {
        return productSpData;
    }
    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getProductBrand() {
        return productBrand;
    }
    public void setProductSn(String productSn) {
        this.productSn = productSn;
    }

    public String getProductSn() {
        return productSn;
    }
    public void setPayPrice(BigDecimal payPrice) {
        this.payPrice = payPrice;
    }

    public BigDecimal getPayPrice() {
        return payPrice;
    }
    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }
    public void setFreightPrice(BigDecimal freightPrice) {
        this.freightPrice = freightPrice;
    }

    public BigDecimal getFreightPrice() {
        return freightPrice;
    }
    public void setCouponPrice(BigDecimal couponPrice) {
        this.couponPrice = couponPrice;
    }

    public BigDecimal getCouponPrice() {
        return couponPrice;
    }
    public void setProductQuantity(Long productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Long getProductQuantity() {
        return productQuantity;
    }
    public void setProductSkuId(Long productSkuId) {
        this.productSkuId = productSkuId;
    }

    public Long getProductSkuId() {
        return productSkuId;
    }
    public void setProductSkuCode(String productSkuCode) {
        this.productSkuCode = productSkuCode;
    }

    public String getProductSkuCode() {
        return productSkuCode;
    }
    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public Long getProductCategoryId() {
        return productCategoryId;
    }
    public void setPromotionInfo(String promotionInfo) {
        this.promotionInfo = promotionInfo;
    }

    public String getPromotionInfo() {
        return promotionInfo;
    }
    public void setInitPrice(BigDecimal initPrice) {
        this.initPrice = initPrice;
    }

    public BigDecimal getInitPrice() {
        return initPrice;
    }
    public void setGiftIntegration(Long giftIntegration) {
        this.giftIntegration = giftIntegration;
    }

    public Long getGiftIntegration() {
        return giftIntegration;
    }
    public void setGiftGrowth(Long giftGrowth) {
        this.giftGrowth = giftGrowth;
    }

    public Long getGiftGrowth() {
        return giftGrowth;
    }
    public void setReturnStatus(String returnStatus) {
        this.returnStatus = returnStatus;
    }

    public String getReturnStatus() {
        return returnStatus;
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
            .append("orderId", getOrderId())
            .append("orderSn", getOrderSn())
            .append("productId", getProductId())
            .append("gameSkuId", getGameSkuId())
            .append("productPic", getProductPic())
            .append("productName", getProductName())
            .append("productSpData", getProductSpData())
            .append("productBrand", getProductBrand())
            .append("productSn", getProductSn())
            .append("payPrice", getPayPrice())
            .append("productPrice", getProductPrice())
            .append("freightPrice", getFreightPrice())
            .append("couponPrice", getCouponPrice())
            .append("productQuantity", getProductQuantity())
            .append("productSkuId", getProductSkuId())
            .append("productSkuCode", getProductSkuCode())
            .append("productCategoryId", getProductCategoryId())
            .append("promotionInfo", getPromotionInfo())
            .append("initPrice", getInitPrice())
            .append("giftIntegration", getGiftIntegration())
            .append("giftGrowth", getGiftGrowth())
            .append("returnStatus", getReturnStatus())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .toString();
    }

    /**
     * 合计实际支付价格
     * 实际支付价格 = 商品价格*(数量) + 运费
     */
    public void countPayPrice() {
        if(this.productPrice==null||this.productQuantity==null){
            this.payPrice = BigDecimal.ZERO;
            return;
        }
        if(this.freightPrice==null){
            this.freightPrice = BigDecimal.ZERO;
        }
        if(this.couponPrice==null){
            this.couponPrice = BigDecimal.ZERO;
        }
        this.payPrice =Arith.add(Arith.mul(this.productPrice,new BigDecimal(this.productQuantity)),this.freightPrice);
    }
}
