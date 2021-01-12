package com.fante.project.business.pmsProductAttribute.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fante.framework.aspectj.lang.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import com.fante.framework.web.domain.BaseEntity;

/**
 * 商品属性＆参数对象 pms_product_attribute
 * 
 * @author fante
 * @date 2020-03-09
 */
public class PmsProductAttribute extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "null")
    private Long id;

    @ApiModelProperty(value = "店铺id")
    private Long shopId;

    @ApiModelProperty(value = "商品类型id")
    @Excel(name = "商品类型id")
    private Long productAttributeCategoryId;

    @ApiModelProperty(value = "名称")
    @Excel(name = "名称")
    private String name;

    @ApiModelProperty(value = "属性选择类型")
    @Excel(name = "属性选择类型")
    private Integer selectType;

    @ApiModelProperty(value = "属性录入方式")
    @Excel(name = "属性录入方式")
    private Integer inputType;

    @ApiModelProperty(value = "可选值列表")
    @Excel(name = "可选值列表")
    private String inputList;

    @ApiModelProperty(value = "排序")
    @Excel(name = "排序")
    private Long sort;

    @ApiModelProperty(value = "是否支持手动新增")
    @Excel(name = "是否支持手动新增")
    private Integer handAddStatus;

    @ApiModelProperty(value = "属性的类型")
    @Excel(name = "属性的类型")
    private Integer type;

    @ApiModelProperty(value = "状态:可选/不可选")
    @Excel(name = "状态:可选/不可选")
    private String status;

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
    public void setProductAttributeCategoryId(Long productAttributeCategoryId) {
        this.productAttributeCategoryId = productAttributeCategoryId;
    }

    public Long getProductAttributeCategoryId() {
        return productAttributeCategoryId;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setSelectType(Integer selectType) {
        this.selectType = selectType;
    }

    public Integer getSelectType() {
        return selectType;
    }
    public void setInputType(Integer inputType) {
        this.inputType = inputType;
    }

    public Integer getInputType() {
        return inputType;
    }
    public void setInputList(String inputList) {
        this.inputList = inputList;
    }

    public String getInputList() {
        return inputList;
    }
    public void setSort(Long sort) {
        this.sort = sort;
    }

    public Long getSort() {
        return sort;
    }
    public void setHandAddStatus(Integer handAddStatus) {
        this.handAddStatus = handAddStatus;
    }

    public Integer getHandAddStatus() {
        return handAddStatus;
    }
    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
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
            .append("shopId", getShopId())
            .append("productAttributeCategoryId", getProductAttributeCategoryId())
            .append("name", getName())
            .append("selectType", getSelectType())
            .append("inputType", getInputType())
            .append("inputList", getInputList())
            .append("sort", getSort())
            .append("handAddStatus", getHandAddStatus())
            .append("type", getType())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
