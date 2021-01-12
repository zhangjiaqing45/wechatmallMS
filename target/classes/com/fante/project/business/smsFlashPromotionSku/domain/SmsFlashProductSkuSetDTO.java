package com.fante.project.business.smsFlashPromotionSku.domain;

import com.fante.common.utils.Checker;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @program: Fante
 * @Date: 2020/4/2 18:01
 * @Author: Mr.Z
 * @Description: 设置秒杀商品SKU DTO
 */
public class SmsFlashProductSkuSetDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long skuId;

    private BigDecimal flashPromotionPrice;

    private Long flashPromotionCount;

    private Long flashPromotionLimit;

    private Integer sort;


    public void validate() {
        Checker.check(ObjectUtils.isEmpty(getSkuId()), "缺少SKU标识");
        Checker.check(ObjectUtils.isEmpty(getFlashPromotionPrice()), "缺少秒杀价格");
        Checker.check(ObjectUtils.isEmpty(getFlashPromotionCount()), "缺少秒杀数量");
        Checker.check(ObjectUtils.isEmpty(getFlashPromotionLimit()), "缺少限购数量");
        Checker.check(ObjectUtils.isEmpty(getSort()), "缺少排序");
    }


    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public BigDecimal getFlashPromotionPrice() {
        return flashPromotionPrice;
    }

    public void setFlashPromotionPrice(BigDecimal flashPromotionPrice) {
        this.flashPromotionPrice = flashPromotionPrice;
    }

    public Long getFlashPromotionCount() {
        return flashPromotionCount;
    }

    public void setFlashPromotionCount(Long flashPromotionCount) {
        this.flashPromotionCount = flashPromotionCount;
    }

    public Long getFlashPromotionLimit() {
        return flashPromotionLimit;
    }

    public void setFlashPromotionLimit(Long flashPromotionLimit) {
        this.flashPromotionLimit = flashPromotionLimit;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
