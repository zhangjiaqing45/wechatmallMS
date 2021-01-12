package com.fante.project.api.appView.domain;

import com.fante.common.utils.Checker;
import com.fante.common.utils.StringUtils;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @program: Fante
 * @Date: 2020/4/22 10:57
 * @Author: Mr.Z
 * @Description: 前端推荐商品接口参数
 */
public class CmsRecommendReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    @ApiModelProperty(value = "推荐类型")
    private String type;

    @ApiModelProperty(hidden = true)
    private String publishStatus;

    @ApiModelProperty(hidden = true)
    private String status;

    @ApiModelProperty(hidden = true)
    private int showNum;

    @ApiModelProperty(value = "店铺id")
    private Long shopId;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public void validate() {
        Checker.check(StringUtils.isBlank(getType()), "缺少推荐类型");
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(String publishStatus) {
        this.publishStatus = publishStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getShowNum() {
        return showNum;
    }

    public void setShowNum(int showNum) {
        this.showNum = showNum;
    }
}
