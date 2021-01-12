package com.fante.project.api.appView.domain;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by ftnet on 2020/5/1
 */
public class FlashProductParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "店铺id")
    private Long shopId;

    @ApiModelProperty(value = "时间段id")
    private Long flashPromotionSessionId;

    @ApiModelProperty(value = "活动启用状态")
    private String promotionStatus;

    @ApiModelProperty(value = "时间段状态")
    private String sessionStatus;

    @ApiModelProperty(value = "商品上架状态")
    private String publishStatus;

    @ApiModelProperty(value = "商品名称")
    private String productName;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getFlashPromotionSessionId() {
        return flashPromotionSessionId;
    }

    public void setFlashPromotionSessionId(Long flashPromotionSessionId) {
        this.flashPromotionSessionId = flashPromotionSessionId;
    }

    public String getPromotionStatus() {
        return promotionStatus;
    }

    public void setPromotionStatus(String promotionStatus) {
        this.promotionStatus = promotionStatus;
    }

    public String getSessionStatus() {
        return sessionStatus;
    }

    public void setSessionStatus(String sessionStatus) {
        this.sessionStatus = sessionStatus;
    }

    public String getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(String publishStatus) {
        this.publishStatus = publishStatus;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
