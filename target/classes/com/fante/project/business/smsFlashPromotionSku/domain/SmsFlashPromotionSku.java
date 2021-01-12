package com.fante.project.business.smsFlashPromotionSku.domain;

import com.fante.framework.aspectj.lang.annotation.Excel;
import com.fante.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 秒杀活动产品SKU对象 sms_flash_promotion_sku
 * 
 * @author fante
 * @date 2020-04-06
 */
public class SmsFlashPromotionSku extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "活动和商品关系表ID")
    @Excel(name = "活动和商品关系表ID")
    private Long promotionPriductId;

    @ApiModelProperty(value = "skuID")
    @Excel(name = "skuID")
    private Long skuId;

    @ApiModelProperty(value = "商品规格")
    @Excel(name = "商品规格")
    private String skuSpData;

    @ApiModelProperty(value = "sku图片")
    @Excel(name = "sku图片")
    private String skuPic;

    @ApiModelProperty(value = "销售价格")
    @Excel(name = "销售价格")
    private BigDecimal skuPrice;

    @ApiModelProperty(value = "限时购价格")
    @Excel(name = "限时购价格")
    private BigDecimal flashPromotionPrice;

    @ApiModelProperty(value = "限时购数量")
    @Excel(name = "限时购数量")
    private Long flashPromotionCount;

    @ApiModelProperty(value = "每人限购数量")
    @Excel(name = "每人限购数量")
    private Long flashPromotionLimit;

    @ApiModelProperty(value = "锁定库存")
    @Excel(name = "锁定库存")
    private Long lockStock;

    @ApiModelProperty(value = "排序")
    @Excel(name = "排序")
    private Integer sort;

    @ApiModelProperty(value = "销量")
    @Excel(name = "销量")
    private Long sales;

    public SmsFlashPromotionSku() {
    }

    public SmsFlashPromotionSku(Long promotionPriductId, Long skuId, String skuSpData, String skuPic, BigDecimal skuPrice, BigDecimal flashPromotionPrice, Long flashPromotionCount, Long flashPromotionLimit, Integer sort) {
        this.promotionPriductId = promotionPriductId;
        this.skuId = skuId;
        this.skuSpData = skuSpData;
        this.skuPic = skuPic;
        this.skuPrice = skuPrice;
        this.flashPromotionPrice = flashPromotionPrice;
        this.flashPromotionCount = flashPromotionCount;
        this.flashPromotionLimit = flashPromotionLimit;
        this.sort = sort;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public void setPromotionPriductId(Long promotionPriductId) {
        this.promotionPriductId = promotionPriductId;
    }

    public Long getPromotionPriductId() {
        return promotionPriductId;
    }
    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Long getSkuId() {
        return skuId;
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
    public void setSkuPrice(BigDecimal skuPrice) {
        this.skuPrice = skuPrice;
    }

    public BigDecimal getSkuPrice() {
        return skuPrice;
    }
    public void setFlashPromotionPrice(BigDecimal flashPromotionPrice) {
        this.flashPromotionPrice = flashPromotionPrice;
    }

    public BigDecimal getFlashPromotionPrice() {
        return flashPromotionPrice;
    }
    public void setFlashPromotionCount(Long flashPromotionCount) {
        this.flashPromotionCount = flashPromotionCount;
    }

    public Long getFlashPromotionCount() {
        return flashPromotionCount;
    }
    public void setFlashPromotionLimit(Long flashPromotionLimit) {
        this.flashPromotionLimit = flashPromotionLimit;
    }

    public Long getFlashPromotionLimit() {
        return flashPromotionLimit;
    }

    public Long getLockStock() {
        return lockStock;
    }

    public void setLockStock(Long lockStock) {
        this.lockStock = lockStock;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getSort() {
        return sort;
    }
    public void setSales(Long sales) {
        this.sales = sales;
    }

    public Long getSales() {
        return sales;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("promotionPriductId", getPromotionPriductId())
                .append("skuId", getSkuId())
                .append("skuSpData", getSkuSpData())
                .append("skuPic", getSkuPic())
                .append("skuPrice", getSkuPrice())
                .append("flashPromotionPrice", getFlashPromotionPrice())
                .append("flashPromotionCount", getFlashPromotionCount())
                .append("flashPromotionLimit", getFlashPromotionLimit())
                .append("lockStock", getLockStock())
                .append("sort", getSort())
                .append("sales", getSales())
                .toString();
    }
}
