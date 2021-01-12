package com.fante.project.business.smsGroupGameSku.mapper;

import com.fante.project.api.omsOrderProcess.domain.CartSkuDto;
import com.fante.project.api.omsOrderProcess.domain.ValidateStockDTO;
import com.fante.project.business.smsGroupGameSku.domain.SmsGroupGameSku;
import com.fante.project.business.smsGroupMemberRecord.domain.SmsGroupMemberRecord;
import com.fante.project.mapperBase.SmsGroupGameSkuMapperBase;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 团购商品skuMapper扩展接口
 *
 * @author fante
 * @date 2020-03-30
 */

public interface SmsGroupGameSkuMapper extends SmsGroupGameSkuMapperBase {
    /**
     * 批量插入团购商品sku信息
     *
     * @param skus
     * @return 删除团购商品sku数量
     */
    int batchInsertGroupGameSku(@Param("skus") List<SmsGroupGameSku> skus);
    /**
     * 批量更新团购商品sku信息
     *
     * @param skus
     * @return 批量更新团购商品sku信息数量
     */
    int batchUpdateGameSku(@Param("skus") List<SmsGroupGameSku> skus);
    /**
     * 批量删除团购商品sku信息
     *
     * @param ids 活动ids
     * @return 批量删除团购商品sku信息数量
     */
    int realDelSmsGroupGameSkuByGGid(@Param("ids")String[] ids);
    /**
     * 假批量删除团购商品sku信息
     *
     * @param ids 活动ids
     * @return 批量删除团购商品sku信息数量
     */
    int deleteSmsGroupGameSkuByGGid(@Param("ids")String[] ids);
    /**
     * 根据skuID查询CartSku需要的数据
     * @param productSkuId
     * @return
     */
    CartSkuDto getCartSkuDtoBySkuId(Long productSkuId);
    /**
     * 验证库存是否充足
     * @param quantity
     * @param id
     * @return
     */
    int validateStock(@Param("quantity") Long quantity,@Param("id") Long id);
    /**
     * 扣减库存
     * @param records
     * @return
     */
    int subStockOfgroupSuccess(@Param("records")List<SmsGroupMemberRecord> records);
    /**
     * 1.团购中所有sku扣减库存
     * 2.团购记录中:团购状态改为成功
     * 3.更改团中的所有成员的订单为待发货
     * 4.团购活动的成团数量 + 1
     * @param recordId
     * @return
     */
    void notifyGroupSuccess(Long recordId);
}
