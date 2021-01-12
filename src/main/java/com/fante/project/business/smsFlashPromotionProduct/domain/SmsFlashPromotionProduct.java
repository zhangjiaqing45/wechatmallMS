package com.fante.project.business.smsFlashPromotionProduct.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fante.framework.aspectj.lang.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import com.fante.framework.web.domain.BaseEntity;
import java.math.BigDecimal;

/**
 * 秒杀活动产品对象 sms_flash_promotion_product
 * 
 * @author fante
 * @date 2020-04-06
 */
public class SmsFlashPromotionProduct extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "秒杀活动ID")
    @Excel(name = "秒杀活动ID")
    private Long flashPromotionId;

    @ApiModelProperty(value = "时间段表ID")
    @Excel(name = "时间段表ID")
    private Long flashPromotionSessionId;

    @ApiModelProperty(value = "商品ID")
    @Excel(name = "商品ID")
    private Long productId;

    @ApiModelProperty(value = "商品货号")
    @Excel(name = "商品货号")
    private String productSn;

    @ApiModelProperty(value = "商品名称")
    @Excel(name = "商品名称")
    private String productName;

    @ApiModelProperty(value = "商品主图")
    @Excel(name = "商品主图")
    private String productPic;

    @ApiModelProperty(value = "展示价格")
    @Excel(name = "展示价格")
    private BigDecimal minPrice;

    @ApiModelProperty(value = "销量")
    @Excel(name = "销量")
    private Long sales;

    @ApiModelProperty(value = "排序")
    @Excel(name = "排序")
    private Integer sort;

    @ApiModelProperty(value = "状态")
    @Excel(name = "状态")
    private String status;

    public SmsFlashPromotionProduct() {
    }

    public SmsFlashPromotionProduct(Long flashPromotionId, Long flashPromotionSessionId, Long productId) {
        this.flashPromotionId = flashPromotionId;
        this.flashPromotionSessionId = flashPromotionSessionId;
        this.productId = productId;
    }

    public SmsFlashPromotionProduct(Long flashPromotionId, Long flashPromotionSessionId, Long productId, String productSn, String productName, String productPic, BigDecimal minPrice, Integer sort, String status) {
        this.flashPromotionId = flashPromotionId;
        this.flashPromotionSessionId = flashPromotionSessionId;
        this.productId = productId;
        this.productSn = productSn;
        this.productName = productName;
        this.productPic = productPic;
        this.minPrice = minPrice;
        this.sort = sort;
        this.status = status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public void setFlashPromotionId(Long flashPromotionId) {
        this.flashPromotionId = flashPromotionId;
    }

    public Long getFlashPromotionId() {
        return flashPromotionId;
    }
    public void setFlashPromotionSessionId(Long flashPromotionSessionId) {
        this.flashPromotionSessionId = flashPromotionSessionId;
    }

    public Long getFlashPromotionSessionId() {
        return flashPromotionSessionId;
    }
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
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
    public void setProductPic(String productPic) {
        this.productPic = productPic;
    }

    public String getProductPic() {
        return productPic;
    }
    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }
    public void setSales(Long sales) {
        this.sales = sales;
    }

    public Long getSales() {
        return sales;
    }
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getSort() {
        return sort;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("flashPromotionId", getFlashPromotionId())
            .append("flashPromotionSessionId", getFlashPromotionSessionId())
            .append("productId", getProductId())
            .append("productSn", getProductSn())
            .append("productName", getProductName())
            .append("productPic", getProductPic())
            .append("minPrice", getMinPrice())
            .append("sales", getSales())
            .append("sort", getSort())
            .append("status", getStatus())
            .toString();
    }
}
