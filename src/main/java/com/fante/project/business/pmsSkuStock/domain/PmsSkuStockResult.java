package com.fante.project.business.pmsSkuStock.domain;

import com.fante.framework.aspectj.lang.annotation.Excel;
import com.fante.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * sku的库存对象 pms_sku_stock
 * 
 * @author fante
 * @date 2020-03-18
 */
public class PmsSkuStockResult extends PmsSkuStock{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品名称")
    @Excel(name = "商品名称")
    private String productName;

    @ApiModelProperty(value = "上架状态")
    @Excel(name = "上架状态")
    private String publishStatus;

    public String getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(String publishStatus) {
        this.publishStatus = publishStatus;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
