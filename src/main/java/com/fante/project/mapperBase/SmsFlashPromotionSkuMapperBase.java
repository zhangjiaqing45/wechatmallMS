package com.fante.project.mapperBase;

import com.fante.project.business.smsFlashPromotionSku.domain.SmsFlashPromotionSku;
import java.util.List;

/**
 * 秒杀活动产品SKUMapper基础接口
 *
 * @author fante
 * @date 2020-04-06
 */
public interface SmsFlashPromotionSkuMapperBase {
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
    public int checkSmsFlashPromotionSkuUnique(SmsFlashPromotionSku smsFlashPromotionSku);

    /**
     * 新增秒杀活动产品SKU
     *
     * @param smsFlashPromotionSku 秒杀活动产品SKU
     * @return 结果
     */
    public int insertSmsFlashPromotionSku(SmsFlashPromotionSku smsFlashPromotionSku);

    /**
     * 修改秒杀活动产品SKU
     *
     * @param smsFlashPromotionSku 秒杀活动产品SKU
     * @return 结果
     */
    public int updateSmsFlashPromotionSku(SmsFlashPromotionSku smsFlashPromotionSku);

    /**
     * 删除秒杀活动产品SKU
     *
     * @param id 秒杀活动产品SKUID
     * @return 结果
     */
    public int deleteSmsFlashPromotionSkuById(Long id);

    /**
     * 批量删除秒杀活动产品SKU
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSmsFlashPromotionSkuByIds(String[] ids);

}
