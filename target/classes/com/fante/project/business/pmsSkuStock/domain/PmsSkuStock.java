package com.fante.project.business.pmsSkuStock.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fante.framework.aspectj.lang.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import com.fante.framework.web.domain.BaseEntity;
import java.math.BigDecimal;

/**
 * sku的库存对象 pms_sku_stock
 * 
 * @author fante
 * @date 2020-04-06
 */
public class PmsSkuStock extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "null")
    private Long id;

    @ApiModelProperty(value = "商品id")
    @Excel(name = "商品id")
    private Long productId;

    @ApiModelProperty(value = "店铺id")
    @Excel(name = "店铺id")
    private Long shopId;

    @ApiModelProperty(value = "sku编码")
    @Excel(name = "sku编码")
    private String skuCode;

    @ApiModelProperty(value = "价格")
    @Excel(name = "价格")
    private BigDecimal price;

    @ApiModelProperty(value = "库存")
    @Excel(name = "库存")
    private Long stock;

    @ApiModelProperty(value = "预警库存")
    @Excel(name = "预警库存")
    private Long lowStock;

    @ApiModelProperty(value = "展示图片")
    @Excel(name = "展示图片")
    private String pic;

    @ApiModelProperty(value = "销量")
    @Excel(name = "销量")
    private Long sale;

    @ApiModelProperty(value = "单品促销价格")
    @Excel(name = "单品促销价格")
    private BigDecimal promotionPrice;

    @ApiModelProperty(value = "锁定库存")
    @Excel(name = "锁定库存")
    private Long lockStock;

    @ApiModelProperty(value = "商品销售属性，json格式")
    @Excel(name = "商品销售属性，json格式")
    private String spData;

    @ApiModelProperty(value = "状态")
    @Excel(name = "状态")
    private String status;

    @ApiModelProperty(value = "删除标记")
    private String delFlag;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getShopId() {
        return shopId;
    }
    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getSkuCode() {
        return skuCode;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }
    public void setStock(Long stock) {
        this.stock = stock;
    }

    public Long getStock() {
        return stock;
    }
    public void setLowStock(Long lowStock) {
        this.lowStock = lowStock;
    }

    public Long getLowStock() {
        return lowStock;
    }
    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPic() {
        return pic;
    }
    public void setSale(Long sale) {
        this.sale = sale;
    }

    public Long getSale() {
        return sale;
    }
    public void setPromotionPrice(BigDecimal promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public BigDecimal getPromotionPrice() {
        return promotionPrice;
    }
    public void setLockStock(Long lockStock) {
        this.lockStock = lockStock;
    }

    public Long getLockStock() {
        return lockStock;
    }
    public void setSpData(String spData) {
        this.spData = spData;
    }

    public String getSpData() {
        return spData;
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
            .append("productId", getProductId())
            .append("shopId", getShopId())
            .append("skuCode", getSkuCode())
            .append("price", getPrice())
            .append("stock", getStock())
            .append("lowStock", getLowStock())
            .append("pic", getPic())
            .append("sale", getSale())
            .append("promotionPrice", getPromotionPrice())
            .append("lockStock", getLockStock())
            .append("spData", getSpData())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
