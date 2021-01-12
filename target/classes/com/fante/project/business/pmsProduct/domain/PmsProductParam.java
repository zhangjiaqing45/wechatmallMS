package com.fante.project.business.pmsProduct.domain;

import com.fante.common.utils.Checker;
import com.fante.common.utils.StringUtils;
import com.fante.project.business.pmsBrand.domain.PmsBrand;
import com.fante.project.business.pmsFeightTemplate.domain.PmsFeightTemplate;
import com.fante.project.business.pmsProductAttributeCategory.domain.PmsProductAttributeCategory;
import com.fante.project.business.pmsProductCategory.domain.PmsProductCategory;
import com.fante.project.business.pmsSkuStock.domain.PmsSkuStock;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @author ftnet
 * @Description PmsProductParam
 * @CreatTime 2020/3/14
 */
public class PmsProductParam extends PmsProduct {

    @ApiModelProperty(value = "推荐类型逗号拼接")
    private String recommendProductType;

    @ApiModelProperty(value = "sku集合")
    private List<PmsSkuStock> skuList;

    public void validate() {
        Checker.check(ObjectUtils.isEmpty(getProductCategoryId()), "缺少商品分类信息！");
        Checker.check(ObjectUtils.isEmpty(getBrandId()), "缺少品牌信息！");
        //Checker.check(ObjectUtils.isEmpty(getFeightTemplateId()), "缺少运费模板信息！");
        Checker.check(ObjectUtils.isEmpty(getProductAttributeCategoryId()), "缺少属性类型信息！");
        Checker.check(StringUtils.isEmpty(getName()), "缺少商品名称信息！");
        Checker.check(ObjectUtils.isEmpty(getPrice()), "缺少商品价格信息！");
        Checker.check(ObjectUtils.isEmpty(getOriginalPrice()), "缺少商品市场价格信息！");
        Checker.check(ObjectUtils.isEmpty(getSkuList()), "缺少商品规格信息！");
        Checker.check(StringUtils.isEmpty(getPic()), "缺少商品主图信息！");
        Checker.check(StringUtils.isEmpty(getAlbumPics()), "缺少商品画册信息！");
        Checker.check(ObjectUtils.isEmpty(getDetailHtml()), "缺少商品详情网页信息！");

        //sku验证
        skuList.forEach(sku->{
            Checker.check(ObjectUtils.isEmpty(sku.getPrice()), "缺少商品sku价格信息！");
            Checker.check(ObjectUtils.isEmpty(sku.getStock()), "缺少商品sku库存信息！");
            Checker.check(ObjectUtils.isEmpty(sku.getLowStock()), "缺少商品sku库存预警信息！");
            Checker.check(ObjectUtils.isEmpty(sku.getPic()), "缺少商品sku图片信息！");
        });
    }

    public String getRecommendProductType() {
        return recommendProductType;
    }

    public void setRecommendProductType(String recommendProductType) {
        this.recommendProductType = recommendProductType;
    }

    public List<PmsSkuStock> getSkuList() {
        return skuList;
    }

    public void setSkuList(List<PmsSkuStock> skuList) {
        this.skuList = skuList;
    }
}
