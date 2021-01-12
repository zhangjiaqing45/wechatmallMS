package com.fante.project.business.smsFlashPromotionSku.service.impl;

import com.fante.common.constant.Constants;
import com.fante.common.utils.DateUtils;
import com.fante.common.utils.text.Convert;
import com.fante.project.api.omsOrderProcess.domain.CartSkuDto;
import com.fante.project.api.omsOrderProcess.domain.ValidateStockDTO;
import com.fante.project.business.omsOrder.domain.OmsOrderDetail;
import com.fante.project.business.omsOrderItem.domain.OmsOrderItem;
import com.fante.project.business.smsFlashPromotionSku.domain.SmsFlashPromotionSku;
import com.fante.project.business.smsFlashPromotionSku.mapper.SmsFlashPromotionSkuMapper;
import com.fante.project.business.smsFlashPromotionSku.service.ISmsFlashPromotionSkuService;
import com.rabbitmq.client.Return;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;

/**
 * 秒杀活动产品SKUService业务层处理
 *
 * @author fante
 * @date 2020-03-24
 */
@Service
public class SmsFlashPromotionSkuServiceImpl implements ISmsFlashPromotionSkuService {

    private static Logger log = LoggerFactory.getLogger(SmsFlashPromotionSkuServiceImpl.class);

    @Autowired
    private SmsFlashPromotionSkuMapper smsFlashPromotionSkuMapper;

    /**
     * 查询秒杀活动产品SKU
     *
     * @param id 秒杀活动产品SKUID
     * @return 秒杀活动产品SKU
     */
    @Override
    public SmsFlashPromotionSku selectSmsFlashPromotionSkuById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        return smsFlashPromotionSkuMapper.selectSmsFlashPromotionSkuById(id);
    }

    /**
     * 查询秒杀活动产品SKU列表
     *
     * @param smsFlashPromotionSku 秒杀活动产品SKU
     * @return 秒杀活动产品SKU集合
     */
    @Override
    public List<SmsFlashPromotionSku> selectSmsFlashPromotionSkuList(SmsFlashPromotionSku smsFlashPromotionSku) {
        return smsFlashPromotionSkuMapper.selectSmsFlashPromotionSkuList(smsFlashPromotionSku);
    }

    /**
     * 查询秒杀活动产品SKU数量
     *
     * @param smsFlashPromotionSku 秒杀活动产品SKU
     * @return 结果
     */
    @Override
    public int countSmsFlashPromotionSku(SmsFlashPromotionSku smsFlashPromotionSku){
        return smsFlashPromotionSkuMapper.countSmsFlashPromotionSku(smsFlashPromotionSku);
    }

    /**
     * 唯一校验
     *
     * @param smsFlashPromotionSku 秒杀活动产品SKU
     * @return 结果
     */
    @Override
    public String checkSmsFlashPromotionSkuUnique(SmsFlashPromotionSku smsFlashPromotionSku) {
        return smsFlashPromotionSkuMapper.checkSmsFlashPromotionSkuUnique(smsFlashPromotionSku) > 0 ? Constants.NOT_UNIQUE : Constants.UNIQUE;
    }

    /**
     * 新增秒杀活动产品SKU
     *
     * @param smsFlashPromotionSku 秒杀活动产品SKU
     * @return 新增秒杀活动产品SKU数量
     */
    @Override
    public int insertSmsFlashPromotionSku(SmsFlashPromotionSku smsFlashPromotionSku) {
        return smsFlashPromotionSkuMapper.insertSmsFlashPromotionSku(smsFlashPromotionSku);
    }

    /**
     * 修改秒杀活动产品SKU
     *
     * @param smsFlashPromotionSku 秒杀活动产品SKU
     * @return 修改秒杀活动产品SKU数量
     */
    @Override
    public int updateSmsFlashPromotionSku(SmsFlashPromotionSku smsFlashPromotionSku) {
        return smsFlashPromotionSkuMapper.updateSmsFlashPromotionSku(smsFlashPromotionSku);
    }

    /**
     * 删除秒杀活动产品SKU对象
     *
     * @param ids 需要删除的数据ID
     * @return 删除秒杀活动产品SKU数量
     */
    @Override
    public int deleteSmsFlashPromotionSkuByIds(String ids) {
        return smsFlashPromotionSkuMapper.deleteSmsFlashPromotionSkuByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除秒杀活动产品SKU信息
     *
     * @param id 秒杀活动产品SKUID
     * @return 删除秒杀活动产品SKU数量
     */
    @Override
    public int deleteSmsFlashPromotionSkuById(Long id) {
        return smsFlashPromotionSkuMapper.deleteSmsFlashPromotionSkuById(id);
    }

    /**
     * 批量添加
     * @param skus
     * @return
     */
    @Override
    public int batchinsertSmsFlashPromotionSku(List<SmsFlashPromotionSku> skus) {
        return smsFlashPromotionSkuMapper.batchinsertSmsFlashPromotionSku(skus);
    }

    /**
     * 根据秒杀活动商品删除库存信息
     * @param promotionPriductId
     * @return
     */
    @Override
    public int deleteSmsFlashPromotionSkuByPromotionPriductId(Long promotionPriductId) {
        return smsFlashPromotionSkuMapper.deleteSmsFlashPromotionSkuByPromotionPriductId(promotionPriductId);
    }

    /**
     * 根据秒杀活动商品获取库存信息
     * @param promotionPriductId
     * @return
     */
    @Override
    public List<SmsFlashPromotionSku> selectSmsFlashPromotionSkuByPromotionPriductId(Long promotionPriductId) {
        return smsFlashPromotionSkuMapper.selectSmsFlashPromotionSkuByPromotionPriductId(promotionPriductId);
    }

    /**
     * 根据秒杀活动商品获取库存信息（设置中）
     * @param promotionProductId
     * @return
     */
    @Override
    public List<SmsFlashPromotionSku> selectProductSkuInSet(Long promotionProductId) {
        if (ObjectUtils.isEmpty(promotionProductId)) {
            return Collections.emptyList();
        }
        return smsFlashPromotionSkuMapper.selectProductSkuInSet(promotionProductId);
    }

    /**
     * 根据购买的skuId查询确认订单所需的相关数据
     * @param productSkuId
     * @return
     */
    @Override
    public CartSkuDto getCartSkuDtoBySkuId(Long productSkuId) {
        return smsFlashPromotionSkuMapper.getCartSkuDtoBySkuId(productSkuId);
    }

    /**
     * 秒杀订单 减锁定库存
     * @param order
     */
    @Override
    public int recoverFlashOrderStock(OmsOrderDetail order) {
        return smsFlashPromotionSkuMapper.recoverFlashOrderStock(order.getOrderItemList());
    }
    /**
     *判断活动是否过期
     * @param promotionId
     */
    @Override
    public int validateGameTimeOut(Long promotionId, ValidateStockDTO validateStockDTO) {
        String[] dateTimeArr = DateUtils.getTime().split(" ");
        return smsFlashPromotionSkuMapper.validateGameTimeOut(promotionId
                ,dateTimeArr[0],dateTimeArr[1]
                ,validateStockDTO.getQuantity()
                ,validateStockDTO.getSkuId());
    }
    /**
     * 添加锁定库存
     * @param promotionId
     * @param quantity
     * @return
     */
    @Override
    public int addLockStock(Long promotionId, Long quantity) {
        return smsFlashPromotionSkuMapper.addLockStock(promotionId,quantity);
    }
    /**
     * 支付成功:扣减库存 和 锁定库存
     * @param item
     */
    @Override
    public int subtractStock(OmsOrderItem item) {
        return smsFlashPromotionSkuMapper.subtractStock(item);
    }
}
