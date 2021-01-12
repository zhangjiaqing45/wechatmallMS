package com.fante.project.business.pmsProductCategory.domain;

import com.fante.framework.aspectj.lang.annotation.Excel;
import com.fante.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 产品分类对象 pms_product_category
 * 
 * @author fante
 * @date 2020-03-09
 */
public class PmsShopProductCategory extends PmsProductCategory {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "关系表id")
    private Long relationId;

    public Long getRelationId() {
        return relationId;
    }

    public void setRelationId(Long relationId) {
        this.relationId = relationId;
    }
}
