package com.fante.project.business.umsDistribution.domain;

import com.fante.framework.aspectj.lang.annotation.Excel;
import com.fante.framework.web.domain.BaseEntity;
import com.fante.project.business.pmsSkuStock.domain.PmsSkuStock;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.List;

/**
 * 分销比例商品角色关系对象 ums_distribution
 * 
 * @author wz
 * @date 2020-04-30
 */
public class UmsDistributionResult extends UmsDistribution {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "商品价格")
    private List<PmsSkuStock> skuList;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public List<PmsSkuStock> getSkuList() {
        return skuList;
    }

    public void setSkuList(List<PmsSkuStock> skuList) {
        this.skuList = skuList;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
