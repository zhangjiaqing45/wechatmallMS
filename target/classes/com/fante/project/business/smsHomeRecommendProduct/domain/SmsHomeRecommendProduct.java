package com.fante.project.business.smsHomeRecommendProduct.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fante.framework.aspectj.lang.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import com.fante.framework.web.domain.BaseEntity;

import java.util.Date;

/**
 * 推荐商品对象 sms_home_recommend_product
 * 
 * @author fante
 * @date 2020-03-10
 */
public class SmsHomeRecommendProduct extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "$column.columnComment")
    private Long id;

    @ApiModelProperty(value = "商品主键ID")
    @Excel(name = "商品主键ID")
    private Long productId;

    @ApiModelProperty(value = "商品名称")
    @Excel(name = "商品名称")
    private String productName;

    @ApiModelProperty(value = "排序")
    @Excel(name = "排序")
    private Long sort;

    @ApiModelProperty(value = "状态")
    @Excel(name = "状态", readConverterExp = "0=启用,1=停用")
    private String status;

    @ApiModelProperty(value = "推荐类型")
    @Excel(name = "推荐类型", readConverterExp = "01=热门商品,02=精品推荐,03=新品推荐")
    private String type;

    @ApiModelProperty(value = "删除标记")
    private String delFlag;

    public SmsHomeRecommendProduct() {
    }

    public SmsHomeRecommendProduct(Long productId, String status, String type, String createBy, Date createTime) {
        this.productId = productId;
        this.status = status;
        this.type = type;
        super.setCreateBy(createBy);
        super.setCreateTime(createTime);
    }

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

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }
    public void setSort(Long sort) {
        this.sort = sort;
    }

    public Long getSort() {
        return sort;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
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
            .append("productName", getProductName())
            .append("sort", getSort())
            .append("status", getStatus())
            .append("type", getType())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
