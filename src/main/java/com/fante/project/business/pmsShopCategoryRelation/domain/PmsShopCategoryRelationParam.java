package com.fante.project.business.pmsShopCategoryRelation.domain;

import com.fante.framework.aspectj.lang.annotation.Excel;
import com.fante.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 店铺从平台选择的分类对象 pms_shop_category_relation
 *
 * @author fante
 * @date 2020-03-11
 */
public class PmsShopCategoryRelationParam implements Serializable {
    private static final long serialVersionUID = 1L;
    /**分类名称*/
    private String name;
    /**店铺显示状态*/
    private String shopStatus;
    /**平台显示状态*/
    private String showStatus;
    /**店铺id*/
    private long shopId;
    /**平台删除标记*/
    private String shopDelFlag;

    public String getShopDelFlag() {
        return shopDelFlag;
    }

    public void setShopDelFlag(String shopDelFlag) {
        this.shopDelFlag = shopDelFlag;
    }

    public long getShopId() {
        return shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShopStatus() {
        return shopStatus;
    }

    public void setShopStatus(String shopStatus) {
        this.shopStatus = shopStatus;
    }

    public String getShowStatus() {
        return showStatus;
    }

    public void setShowStatus(String showStatus) {
        this.showStatus = showStatus;
    }


}
