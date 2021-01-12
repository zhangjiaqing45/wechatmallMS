package com.fante.project.business.smsFlashPromotionSku.mapper;

import com.fante.project.api.omsOrderProcess.domain.CartSkuDto;
import com.fante.project.business.omsOrderItem.domain.OmsOrderItem;
import com.fante.project.business.smsFlashPromotionSku.domain.SmsFlashPromotionSku;
import com.fante.project.mapperBase.SmsFlashPromotionSkuMapperBase;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 秒杀活动产品SKUMapper扩展接口
 *
 * @author fante
 * @date 2020-03-24
 */

public interface SmsFlashPromotionSkuMapper extends SmsFlashPromotionSkuMapperBase {

    /**
     * 批量添加
     * @param skus
     * @return
     */
    int batchinsertSmsFlashPromotionSku(@Param("skus") List<SmsFlashPromotionSku> skus);

    /**
     * 根据秒杀活动商品删除库存信息
     * @param promotionPriductId
     * @return
     */
    int deleteSmsFlashPromotionSkuByPromotionPriductId(Long promotionPriductId);

    /**
     * 根据秒杀活动商品获取库存信息
     * @param promotionPriductId
     * @return
     */
    List<SmsFlashPromotionSku> selectSmsFlashPromotionSkuByPromotionPriductId(@Param("promotionProductId") Long promotionPriductId);

    /**
     * 根据秒杀活动商品获取库存信息（设置中）
     * @param promotionProductId
     * @return
     */
    public List<SmsFlashPromotionSku> selectProductSkuInSet(@Param("promotionProductId") Long promotionProductId);

    /**
     * 根据购买的skuId查询确认订单所需的相关数据
     * @param productSkuId
     * @return
     */
    CartSkuDto getCartSkuDtoBySkuId(Long productSkuId);

    /**
     * 秒杀订单 减锁定库存
     * @param orderItemList 订单详情列表
     */
    int recoverFlashOrderStock(@Param("list")List<OmsOrderItem> orderItemList);

    /**
     * 判断活动是否过期
     * @param promotionId 活动sku关系表id
     * @param nowDate 现在 年月日
     * @param nowTime 现在 时分秒
     * @param quantity 购买商品数量
     * @param skuId 商品skuid
     * @return
     */
    int validateGameTimeOut(@Param("promotionId") Long promotionId,
                            @Param("nowDate")String nowDate,
                            @Param("nowTime")String nowTime,
                            @Param("quantity")Long quantity,
                            @Param("skuId") Long skuId);

    /**
     * 添加锁定库存
     * @param promotionId
     * @param quantity
     * @return
     */
    int addLockStock(@Param("promotionId") Long promotionId, @Param("quantity")Long quantity);
    /**
     * 支付成功:扣减库存 和 锁定库存
     * @param item
     */
    int subtractStock(OmsOrderItem item);
}
