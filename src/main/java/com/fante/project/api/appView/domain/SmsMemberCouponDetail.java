package com.fante.project.api.appView.domain;

import com.fante.framework.aspectj.lang.annotation.Excel;
import com.fante.project.business.smsCoupon.domain.SmsCoupon;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author ftnet
 * @Description SmsMemberCouponDetail
 * @CreatTime 2020/6/20
 */
public class SmsMemberCouponDetail extends SmsCoupon {
    /**
     * 核销码
     */
    private String vCode;
    /**
     * 店铺名称
     */
    private String shopName;

    @ApiModelProperty(value = "使用状态：0->未使用；1->已使用；2->已过期")
    private String useStatus;

    public String getvCode() {
        return vCode;
    }

    public void setvCode(String vCode) {
        this.vCode = vCode;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(String useStatus) {
        this.useStatus = useStatus;
    }
}
