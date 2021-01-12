package com.fante.project.business.pmsProductLog.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fante.framework.aspectj.lang.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import com.fante.framework.web.domain.BaseEntity;

/**
 * 商品操作日志对象 pms_product_log
 * 
 * @author fante
 * @date 2020-03-21
 */
public class PmsProductLog extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "店铺id")
    private Long shopId;

    @ApiModelProperty(value = "店铺名称")
    @Excel(name = "店铺名称")
    private String shopName;

    @ApiModelProperty(value = "商品id")
    private Long productId;

    @ApiModelProperty(value = "商品名称")
    @Excel(name = "商品名称")
    private String productName;

    @ApiModelProperty(value = "skuId")
    private Long skuId;

    @ApiModelProperty(value = "sku规格属性")
    @Excel(name = "sku规格属性")
    private String skuSpData;

    @ApiModelProperty(value = "0:普通商品 1:积分商品")
    private String productType;

    @ApiModelProperty(value = "动作")
    @Excel(name = "动作")
    private String action;

    @ApiModelProperty(value = "修改前的值")
    @Excel(name = "修改前的值")
    private String oldValue;

    @ApiModelProperty(value = "修改后的值")
    @Excel(name = "修改后的值")
    private String newValue;

    @ApiModelProperty(value = "描述")
    @Excel(name = "描述")
    private String description;

    @ApiModelProperty(value = "删除标记")
    private String delFlag;

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
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopName() {
        return shopName;
    }
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
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
    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductType() {
        return productType;
    }
    public void setAction(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }
    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getOldValue() {
        return oldValue;
    }
    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public String getNewValue() {
        return newValue;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
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
            .append("shopId", getShopId())
            .append("shopName", getShopName())
            .append("productId", getProductId())
            .append("productName", getProductName())
            .append("skuId", getSkuId())
            .append("skuSpData", getSkuSpData())
            .append("productType", getProductType())
            .append("action", getAction())
            .append("oldValue", getOldValue())
            .append("newValue", getNewValue())
            .append("description", getDescription())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
