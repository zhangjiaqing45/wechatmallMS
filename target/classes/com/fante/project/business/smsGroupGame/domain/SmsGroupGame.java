package com.fante.project.business.smsGroupGame.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fante.framework.aspectj.lang.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import com.fante.framework.web.domain.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 团购商品对象 sms_group_game
 * 
 * @author fante
 * @date 2020-04-08
 */
public class SmsGroupGame extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "店铺ID")
    @Excel(name = "店铺ID")
    private Long shopId;

    @ApiModelProperty(value = "店铺名称")
    @Excel(name = "店铺名称")
    private String shopName;

    @ApiModelProperty(value = "商品ID")
    @Excel(name = "商品ID")
    private Long productId;

    @ApiModelProperty(value = "团购时商品名称")
    @Excel(name = "团购时商品名称")
    private String productName;

    @ApiModelProperty(value = "团购时商品图片")
    @Excel(name = "团购时商品图片")
    private String productPic;

    @ApiModelProperty(value = "目标团购人数")
    @Excel(name = "目标团购人数")
    private Integer targetMemberCount;

    @ApiModelProperty(value = "团购截至时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "团购截至时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    @ApiModelProperty(value = "开团时效")
    @Excel(name = "开团时效")
    private Integer aging;

    @ApiModelProperty(value = "虚拟基础团购数量(显示数量=this+实际数量)")
    @Excel(name = "虚拟基础团购数量(显示数量=this+实际数量)")
    private Integer virtualCount;

    @ApiModelProperty(value = "实际拼团数量（处理流程的时候维护）")
    @Excel(name = "实际拼团数量", readConverterExp = "处=理流程的时候维护")
    private Integer successCount;

    @ApiModelProperty(value = "前端页面展示的价格（设置的sku中最低价格）上架时维护进去")
    @Excel(name = "前端页面展示的价格", readConverterExp = "设=置的sku中最低价格")
    private BigDecimal minPrice;

    @ApiModelProperty(value = "活动状态(待上架，已上架，已下架)")
    @Excel(name = "活动状态(待上架，已上架，已下架)")
    private String status;

    @ApiModelProperty(value = "删除标记")
    private String delFlag;

    @ApiModelProperty(value = "排序")
    @Excel(name = "排序")
    private Integer sort;

    @ApiModelProperty(value = "商品货号")
    @Excel(name = "商品货号")
    private String productSn;

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
    public void setProductPic(String productPic) {
        this.productPic = productPic;
    }

    public String getProductPic() {
        return productPic;
    }
    public void setTargetMemberCount(Integer targetMemberCount) {
        this.targetMemberCount = targetMemberCount;
    }

    public Integer getTargetMemberCount() {
        return targetMemberCount;
    }
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getEndTime() {
        return endTime;
    }
    public void setAging(Integer aging) {
        this.aging = aging;
    }

    public Integer getAging() {
        return aging;
    }
    public void setVirtualCount(Integer virtualCount) {
        this.virtualCount = virtualCount;
    }

    public Integer getVirtualCount() {
        return virtualCount;
    }
    public void setSuccessCount(Integer successCount) {
        this.successCount = successCount;
    }

    public Integer getSuccessCount() {
        return successCount;
    }
    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
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
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getSort() {
        return sort;
    }
    public void setProductSn(String productSn) {
        this.productSn = productSn;
    }

    public String getProductSn() {
        return productSn;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("shopId", getShopId())
            .append("shopName", getShopName())
            .append("productId", getProductId())
            .append("productName", getProductName())
            .append("productPic", getProductPic())
            .append("targetMemberCount", getTargetMemberCount())
            .append("endTime", getEndTime())
            .append("aging", getAging())
            .append("virtualCount", getVirtualCount())
            .append("successCount", getSuccessCount())
            .append("minPrice", getMinPrice())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .append("sort", getSort())
            .append("productSn", getProductSn())
            .toString();
    }
}
