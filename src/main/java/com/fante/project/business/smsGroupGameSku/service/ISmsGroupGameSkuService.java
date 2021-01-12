package com.fante.project.business.smsGroupGameSku.service;

import com.fante.project.api.omsOrderProcess.domain.CartSkuDto;
import com.fante.project.business.smsGroupGameSku.domain.SmsGroupGameSku;
import com.fante.project.business.smsGroupMemberRecord.domain.SmsGroupMemberRecord;

import java.util.List;

/**
 * 团购商品skuService接口
 *
 * @author fante
 * @date 2020-03-30
 */
public interface ISmsGroupGameSkuService {
    /**
     * 查询团购商品sku
     *
     * @param id 团购商品skuID
     * @return 团购商品sku
     */
    public SmsGroupGameSku selectSmsGroupGameSkuById(Long id);

    /**
     * 查询团购商品sku列表
     *
     * @param smsGroupGameSku 团购商品sku
     * @return 团购商品sku集合
     */
    public List<SmsGroupGameSku> selectSmsGroupGameSkuList(SmsGroupGameSku smsGroupGameSku);

    /**
     * 查询团购商品sku数量
     *
     * @param smsGroupGameSku 团购商品sku
     * @return 结果
     */
    public int countSmsGroupGameSku(SmsGroupGameSku smsGroupGameSku);

    /**
     * 唯一校验
     *
     * @param smsGroupGameSku 团购商品sku
     * @return 结果
     */
    public String checkSmsGroupGameSkuUnique(SmsGroupGameSku smsGroupGameSku);

    /**
     * 新增团购商品sku
     *
     * @param smsGroupGameSku 团购商品sku
     * @return 新增团购商品sku数量
     */
    public int insertSmsGroupGameSku(SmsGroupGameSku smsGroupGameSku);

    /**
     * 修改团购商品sku
     *
     * @param smsGroupGameSku 团购商品sku
     * @return 修改团购商品sku数量
     */
    public int updateSmsGroupGameSku(SmsGroupGameSku smsGroupGameSku);

    /**
     * 批量删除团购商品sku
     *
     * @param ids 需要删除的数据ID
     * @return 删除团购商品sku数量
     */
    public int deleteSmsGroupGameSkuByIds(String ids);
    /**
     * 批量删除团购商品sku
     *
     * @param ids 需要删除的数据ID
     * @return 删除团购商品sku数量
     */
    public int deleteSmsGroupGameSkuByIds(String[] ids);
    /**
     * 删除团购商品sku信息
     *
     * @param id 团购商品skuID
     * @return 删除团购商品sku数量
     */
    public int deleteSmsGroupGameSkuById(Long id);
    /**
     * 批量插入团购商品sku信息
     *
     * @param skus
     * @return 删除团购商品sku数量
     */
    public int batchInsertGroupGameSku(List<SmsGroupGameSku> skus);
    /**
     * 批量更新团购商品sku信息
     *
     * @param skus
     * @return 批量更新团购商品sku信息数量
     */
    public int batchUpdateGameSku(List<SmsGroupGameSku> skus);
    /**
     * 真批量删除团购商品sku信息
     *
     * @param ids 活动ids
     * @return 批量删除团购商品sku信息数量
     */
    public int realDelSmsGroupGameSkuByGGid(String[] ids);
    /**
     * 假批量删除团购商品sku信息
     *
     * @param ids 活动ids
     * @return 批量删除团购商品sku信息数量
     */
    public int deleteSmsGroupGameSkuByGGid(String[] ids);

    /**
     * 根据skuID查询CartSku需要的数据
     * @param productSkuId
     * @return
     */
    public CartSkuDto getCartSkuDtoBySkuId(Long productSkuId);

    /**
     * 验证库存是否充足
     * @param quantity
     * @param promotionId
     * @return
     */
    public int validateStock(Long quantity, Long promotionId);

    /**
     * 扣减库存
     * @param records
     * @return
     */
    public int subStockOfgroupSuccess(List<SmsGroupMemberRecord> records);

    /**
     * 1.团购中所有sku扣减库存
     * 2.团购记录中:团购状态改为成功
     * 3.更改团中的所有成员的订单为待发货
     * 4.团购活动的成团数量 + 1
     * @param recordId
     * @return
     */
    public void  notifyGroupSuccess(Long recordId);
}
