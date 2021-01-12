package com.fante.project.business.smsGroupGameSku.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fante.framework.aspectj.lang.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import com.fante.framework.web.domain.BaseEntity;
import java.math.BigDecimal;

/**
 * 团购商品sku对象 sms_group_game_sku
 * 
 * @author fante
 * @date 2020-04-08
 */
public class SmsGroupGameSku extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "活动ID")
    @Excel(name = "活动ID")
    private Long groupGameId;

    @ApiModelProperty(value = "商品ID")
    @Excel(name = "商品ID")
    private Long productId;

    @ApiModelProperty(value = "sku编码")
    @Excel(name = "sku编码")
    private String skuCode;

    @ApiModelProperty(value = "SKUID")
    @Excel(name = "SKUID")
    private Long skuId;

    @ApiModelProperty(value = "sku原价")
    @Excel(name = "sku原价")
    private BigDecimal skuPrice;

    @ApiModelProperty(value = "团购时商品规格")
    @Excel(name = "团购时商品规格")
    private String skuSpData;

    @ApiModelProperty(value = "团购时sku图片")
    @Excel(name = "团购时sku图片")
    private String skuPic;

    @ApiModelProperty(value = "团购价")
    @Excel(name = "团购价")
    private BigDecimal groupPrice;

    @ApiModelProperty(value = "团购库存")
    @Excel(name = "团购库存")
    private Integer groupStock;

    @ApiModelProperty(value = "销量")
    @Excel(name = "销量")
    private Long sales;

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
    public void setGroupGameId(Long groupGameId) {
        this.groupGameId = groupGameId;
    }

    public Long getGroupGameId() {
        return groupGameId;
    }
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }
    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getSkuCode() {
        return skuCode;
    }
    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Long getSkuId() {
        return skuId;
    }
    public void setSkuPrice(BigDecimal skuPrice) {
        this.skuPrice = skuPrice;
    }

    public BigDecimal getSkuPrice() {
        return skuPrice;
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
    public void setGroupStock(Integer groupStock) {
        this.groupStock = groupStock;
    }

    public Integer getGroupStock() {
        return groupStock;
    }
    public void setSales(Long sales) {
        this.sales = sales;
    }

    public Long getSales() {
        return sales;
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
            .append("groupGameId", getGroupGameId())
            .append("productId", getProductId())
            .append("skuCode", getSkuCode())
            .append("skuId", getSkuId())
            .append("skuPrice", getSkuPrice())
            .append("skuSpData", getSkuSpData())
            .append("skuPic", getSkuPic())
            .append("groupPrice", getGroupPrice())
            .append("groupStock", getGroupStock())
            .append("sales", getSales())
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
