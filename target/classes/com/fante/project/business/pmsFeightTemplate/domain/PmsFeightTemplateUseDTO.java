package com.fante.project.business.pmsFeightTemplate.domain;

import java.io.Serializable;

/**
 * @program: Fante
 * @Date: 2020/3/28 14:08
 * @Author: Mr.Z
 * @Description: 模板使用情况
 */
public class PmsFeightTemplateUseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long productId;

    private String productSn;

    private Long shopId;

    private Long feightTemplateId;

    private String productName;

    private String publishStatus;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductSn() {
        return productSn;
    }

    public void setProductSn(String productSn) {
        this.productSn = productSn;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getFeightTemplateId() {
        return feightTemplateId;
    }

    public void setFeightTemplateId(Long feightTemplateId) {
        this.feightTemplateId = feightTemplateId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(String publishStatus) {
        this.publishStatus = publishStatus;
    }
}
