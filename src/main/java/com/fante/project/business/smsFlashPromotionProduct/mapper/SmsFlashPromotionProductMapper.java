package com.fante.project.business.smsFlashPromotionProduct.mapper;

import com.fante.project.api.appView.domain.FlashProductParam;
import com.fante.project.api.appView.domain.PmsFlashProductDetailPageInfo;
import com.fante.project.business.smsFlashPromotionProduct.domain.SmsFlashPromotionProductDTO;
import com.fante.project.business.smsFlashPromotionProduct.domain.SmsFlashPromotionProductSelectDTO;
import com.fante.project.mapperBase.SmsFlashPromotionProductMapperBase;

import java.util.List;

/**
 * 秒杀活动产品Mapper扩展接口
 *
 * @author fante
 * @date 2020-03-23
 */

public interface SmsFlashPromotionProductMapper extends SmsFlashPromotionProductMapperBase {


    /**
     * 秒杀活动设置商品查询
     * @param productDTO
     * @return
     */
    public List<SmsFlashPromotionProductDTO> selectJoinList(SmsFlashPromotionProductDTO productDTO);

    /**
     * 秒杀活动选择商品查询
     * @param selectDTO
     * @return
     */
    public List<SmsFlashPromotionProductSelectDTO> selectProductSelectList(SmsFlashPromotionProductSelectDTO selectDTO);

    /**
     * 根据秒杀时间段获取秒杀商品
     */
    public List<SmsFlashPromotionProductDTO> getFlashProductList(FlashProductParam param);
    /**
     * (app)获取秒杀商品详情
     * @param productId
     * @return
     */
    PmsFlashProductDetailPageInfo getFlashProductDetailInfo(Long productId);
}
