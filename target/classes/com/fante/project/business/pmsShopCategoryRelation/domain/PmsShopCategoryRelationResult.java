package com.fante.project.business.pmsShopCategoryRelation.domain;

import com.fante.framework.aspectj.lang.annotation.Excel;
import com.fante.framework.web.domain.BaseEntity;
import com.fante.project.business.pmsProductCategory.domain.PmsProductCategory;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 店铺从平台选择的分类对象 pms_shop_category_relation
 *
 * @author fante
 * @date 2020-03-11
 */
public class PmsShopCategoryRelationResult extends PmsProductCategory {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "店铺分类关系表id")
    private Long pscrId;
    @ApiModelProperty(value = "店铺导航状态")
    private String shopStatus;

    public String getShopStatus() {
        return shopStatus;
    }

    public void setShopStatus(String shopStatus) {
        this.shopStatus = shopStatus;
    }

    public Long getPscrId() {
        return pscrId;
    }

    public void setPscrId(Long pscrId) {
        this.pscrId = pscrId;
    }
}
