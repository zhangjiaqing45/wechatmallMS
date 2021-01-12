package com.fante.project.business.pmsProductAuditLog.domain;

import com.fante.framework.aspectj.lang.annotation.Excel;
import com.fante.framework.web.domain.BaseEntity;
import com.fante.project.business.pmsProduct.domain.PmsProduct;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 商品审核日志对象 pms_product_audit_log
 * 
 * @author fante
 * @date 2020-03-19
 */
public class PmsProductAuditLogParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "审核的商品对象")
    private PmsProduct product;

}
