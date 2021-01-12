package com.fante.project.business.pmsIntegralFeightTemplate.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fante.framework.aspectj.lang.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import com.fante.framework.web.domain.BaseEntity;
import java.math.BigDecimal;

/**
 * 积分商品运费设置对象 pms_integral_feight_template
 * 
 * @author fante
 * @date 2020-04-13
 */
public class PmsIntegralFeightTemplate extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "$column.columnComment")
    private Long id;

    @ApiModelProperty(value = "模板名称")
    @Excel(name = "模板名称")
    private String name;

    @ApiModelProperty(value = "运费（元）")
    @Excel(name = "运费", readConverterExp = "元=")
    private BigDecimal feightFee;

    @ApiModelProperty(value = "包邮区域")
    @Excel(name = "包邮区域")
    private String freeDest;

    @ApiModelProperty(value = "不包邮地区")
    @Excel(name = "不包邮地区")
    private String tollDest;

    @ApiModelProperty(value = "不配送地区")
    @Excel(name = "不配送地区")
    private String excludeDest;

    @ApiModelProperty(value = "$column.columnComment")
    @Excel(name = "不配送地区")
    private Long shopId;

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
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setFeightFee(BigDecimal feightFee) {
        this.feightFee = feightFee;
    }

    public BigDecimal getFeightFee() {
        return feightFee;
    }
    public void setFreeDest(String freeDest) {
        this.freeDest = freeDest;
    }

    public String getFreeDest() {
        return freeDest;
    }
    public void setTollDest(String tollDest) {
        this.tollDest = tollDest;
    }

    public String getTollDest() {
        return tollDest;
    }
    public void setExcludeDest(String excludeDest) {
        this.excludeDest = excludeDest;
    }

    public String getExcludeDest() {
        return excludeDest;
    }
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getShopId() {
        return shopId;
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
            .append("name", getName())
            .append("feightFee", getFeightFee())
            .append("freeDest", getFreeDest())
            .append("tollDest", getTollDest())
            .append("excludeDest", getExcludeDest())
            .append("shopId", getShopId())
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
