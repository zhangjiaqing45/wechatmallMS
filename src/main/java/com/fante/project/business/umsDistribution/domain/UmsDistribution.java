package com.fante.project.business.umsDistribution.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fante.framework.aspectj.lang.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import com.fante.framework.web.domain.BaseEntity;
import java.math.BigDecimal;

/**
 * 分销比例商品角色关系对象 ums_distribution
 * 
 * @author fante
 * @date 2020-04-30
 */
public class UmsDistribution extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "商品id")
    @Excel(name = "商品id")
    private Long productId;

    @ApiModelProperty(value = "分销角色 2:设计师 3:自由合伙人 4:导购")
    @Excel(name = "分销角色 2:设计师 3:自由合伙人 4:导购")
    private String roleType;

    @ApiModelProperty(value = "分销比例")
    @Excel(name = "分销比例")
    private BigDecimal ratio;

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
    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getRoleType() {
        return roleType;
    }
    public void setRatio(BigDecimal ratio) {
        this.ratio = ratio;
    }

    public BigDecimal getRatio() {
        return ratio;
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
            .append("roleType", getRoleType())
            .append("ratio", getRatio())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
