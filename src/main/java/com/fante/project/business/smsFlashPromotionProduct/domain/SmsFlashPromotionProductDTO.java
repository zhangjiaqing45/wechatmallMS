package com.fante.project.business.smsFlashPromotionProduct.domain;

/**
 * @program: Fante
 * @Date: 2020/4/1 16:05
 * @Author: Mr.Z
 * @Description: 秒杀活动设置列表商品显示DTO
 */
public class SmsFlashPromotionProductDTO extends SmsFlashPromotionProduct {

    private static final long serialVersionUID = 1L;

    private String publishStatus;

    private String verifyStatus;

    private Long shopId;

    public String getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(String publishStatus) {
        this.publishStatus = publishStatus;
    }

    public String getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(String verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
}
