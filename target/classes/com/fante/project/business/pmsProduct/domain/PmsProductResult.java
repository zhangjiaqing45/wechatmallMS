package com.fante.project.business.pmsProduct.domain;

import com.fante.common.utils.Checker;
import com.fante.common.utils.StringUtils;
import com.fante.project.business.pmsFeightTemplate.domain.PmsFeightTemplate;
import com.fante.project.business.pmsSkuStock.domain.PmsSkuStock;
import com.fante.project.business.smsHomeRecommendProduct.domain.SmsHomeRecommendProduct;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @author ftnet
 * @Description PmsProductParam
 * @CreatTime 2020/3/14
 */
public class PmsProductResult extends PmsProduct {

    @ApiModelProperty(value = "店铺名称")
    private String shopName;

    @ApiModelProperty(value = "sku集合")
    private List<PmsSkuStock> skuList;

    @ApiModelProperty(value = "模板对象")
    private PmsFeightTemplate pmsFeightTemplate;

    @ApiModelProperty(value = "推荐类型集合")
    private List<SmsHomeRecommendProduct> smsHomeRecommendProductList;

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
    public String getShopName() {
        return shopName;
    }
    public List<PmsSkuStock> getSkuList() {
        return skuList;
    }
    public void setSkuList(List<PmsSkuStock> skuList) {
        this.skuList = skuList;
    }

    public List<SmsHomeRecommendProduct> getSmsHomeRecommendProductList() {
        return smsHomeRecommendProductList;
    }

    public void setSmsHomeRecommendProductList(List<SmsHomeRecommendProduct> smsHomeRecommendProductList) {
        this.smsHomeRecommendProductList = smsHomeRecommendProductList;
    }

    public PmsFeightTemplate getPmsFeightTemplate() {
        return pmsFeightTemplate;
    }

    public void setPmsFeightTemplate(PmsFeightTemplate pmsFeightTemplate) {
        this.pmsFeightTemplate = pmsFeightTemplate;
    }
}
