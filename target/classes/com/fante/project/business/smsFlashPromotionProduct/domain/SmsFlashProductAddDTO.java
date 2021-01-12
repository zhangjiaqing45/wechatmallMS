package com.fante.project.business.smsFlashPromotionProduct.domain;

import com.fante.common.utils.Checker;
import com.fante.project.business.smsFlashPromotionSku.domain.SmsFlashProductSkuSetDTO;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.util.List;

/**
 * @program: Fante
 * @Date: 2020/4/2 17:57
 * @Author: Mr.Z
 * @Description: 添加秒杀商品参数 DTO
 */
public class SmsFlashProductAddDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long flashPromotionId;

    private Long flashPromotionSessionId;

    private Long productId;

    private Integer sort;

    private List<SmsFlashProductSkuSetDTO> skus;


    public void validate() {
        Checker.check(ObjectUtils.isEmpty(getFlashPromotionId()), "缺少秒杀活动标识");
        Checker.check(ObjectUtils.isEmpty(getFlashPromotionSessionId()), "缺少秒杀时间段标识");
        Checker.check(ObjectUtils.isEmpty(getProductId()), "缺少商品标识");
        Checker.check(ObjectUtils.isEmpty(getSort()), "缺少排序");

        Checker.check(ObjectUtils.isEmpty(getSkus()), "缺少秒杀商品库存信息");
        getSkus().forEach(skuSetDTO -> {
            skuSetDTO.validate();
        });
    }

    public Long getFlashPromotionId() {
        return flashPromotionId;
    }

    public void setFlashPromotionId(Long flashPromotionId) {
        this.flashPromotionId = flashPromotionId;
    }

    public Long getFlashPromotionSessionId() {
        return flashPromotionSessionId;
    }

    public void setFlashPromotionSessionId(Long flashPromotionSessionId) {
        this.flashPromotionSessionId = flashPromotionSessionId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public List<SmsFlashProductSkuSetDTO> getSkus() {
        return skus;
    }

    public void setSkus(List<SmsFlashProductSkuSetDTO> skus) {
        this.skus = skus;
    }
}
