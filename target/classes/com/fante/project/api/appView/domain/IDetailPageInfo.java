package com.fante.project.api.appView.domain;

import com.fante.project.business.bizDescription.domain.BizDescription;

public interface IDetailPageInfo {

    /**
     * 设置参数
     */
    public void setPageInfoData(int shopStarCount,int shopProductCount,boolean shopStar,boolean productStar);
    /**
     * 获取此详情页店铺id
     */
    public Long getPageInfoShopId();
    /**
     * 获取此详情页商品id
     */
    public Long getPageInfoProductId();
}
