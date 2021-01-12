package com.fante.project.business.smsFlashPromotionSku.service;

import com.fante.project.api.omsOrderProcess.domain.CartSkuDto;
import com.fante.project.api.omsOrderProcess.domain.ValidateStockDTO;
import com.fante.project.business.omsOrder.domain.OmsOrderDetail;
import com.fante.project.business.omsOrderItem.domain.OmsOrderItem;
import com.fante.project.business.smsFlashPromotionSku.domain.SmsFlashPromotionSku;

import java.util.List;

/**
 * 秒杀活动产品SKUService接口
 *
 * @author fante
 * @date 2020-03-24
 */
public interface ISmsFlashPromotionSkuService {
    /**
     * 查询秒杀活动产品SKU
     *
     * @param id 秒杀活动产品SKUID
     * @return 秒杀活动产品SKU
     */
    public SmsFlashPromotionSku selectSmsFlashPromotionSkuById(Long id);

    /**
     * 查询秒杀活动产品SKU列表
     *
     * @param smsFlashPromotionSku 秒杀活动产品SKU
     * @return 秒杀活动产品SKU集合
     */
    public List<SmsFlashPromotionSku> selectSmsFlashPromotionSkuList(SmsFlashPromotionSku smsFlashPromotionSku);

    /**
     * 查询秒杀活动产品SKU数量
     *
     * @param smsFlashPromotionSku 秒杀活动产品SKU
     * @return 结果
     */
    public int countSmsFlashPromotionSku(SmsFlashPromotionSku smsFlashPromotionSku);

    /**
     * 唯一校验
     *
     * @param smsFlashPromotionSku 秒杀活动产品SKU
     * @return 结果
     */
    public String checkSmsFlashPromotionSkuUnique(SmsFlashPromotionSku smsFlashPromotionSku);

    /**
     * 新增秒杀活动产品SKU
     *
     * @param smsFlashPromotionSku 秒杀活动产品SKU
     * @return 新增秒杀活动产品SKU数量
     */
    public int insertSmsFlashPromotionSku(SmsFlashPromotionSku smsFlashPromotionSku);

    /**
     * 修改秒杀活动产品SKU
     *
     * @param smsFlashPromotionSku 秒杀活动产品SKU
     * @return 修改秒杀活动产品SKU数量
     */
    public int updateSmsFlashPromotionSku(SmsFlashPromotionSku smsFlashPromotionSku);

    /**
     * 批量删除秒杀活动产品SKU
     *
     * @param ids 需要删除的数据ID
     * @return 删除秒杀活动产品SKU数量
     */
    public int deleteSmsFlashPromotionSkuByIds(String ids);

    /**
     * 删除秒杀活动产品SKU信息
     *
     * @param id 秒杀活动产品SKUID
     * @return 删除秒杀活动产品SKU数量
     */
    public int deleteSmsFlashPromotionSkuById(Long id);

    /**
     * 批量添加
     * @param skus
     * @return
     */
    int batchinsertSmsFlashPromotionSku(List<SmsFlashPromotionSku> skus);

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
    List<SmsFlashPromotionSku> selectSmsFlashPromotionSkuByPromotionPriductId(Long promotionPriductId);

    /**
     * 根据秒杀活动商品获取库存信息（设置中）
     * @param promotionProductId
     * @return
     */
    public List<SmsFlashPromotionSku> selectProductSkuInSet(Long promotionProductId);

    /**
     * 根据购买的skuId查询确认订单所需的相关数据
     * @param productSkuId
     * @return
     */
    public CartSkuDto getCartSkuDtoBySkuId(Long productSkuId);

    /**
     * 秒杀订单 减锁定库存
     * @param order
     */
    public int recoverFlashOrderStock(OmsOrderDetail order);

    /**
     *判断活动是否过期
     * @param promotionId
     */
    public int validateGameTimeOut(Long promotionId,ValidateStockDTO validateStockDTO);

    /**
     * 添加锁定库存
     * @param promotionId
     * @param quantity
     * @return
     */
    public int addLockStock(Long promotionId, Long quantity);

    /**
     * 支付成功:扣减库存 和 锁定库存
     * @param item
     */
    public int subtractStock(OmsOrderItem item);
}
