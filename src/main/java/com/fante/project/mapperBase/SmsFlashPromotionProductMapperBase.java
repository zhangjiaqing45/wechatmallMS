package com.fante.project.mapperBase;

import com.fante.project.business.smsFlashPromotionProduct.domain.SmsFlashPromotionProduct;
import java.util.List;

/**
 * 秒杀活动产品Mapper基础接口
 *
 * @author fante
 * @date 2020-04-06
 */
public interface SmsFlashPromotionProductMapperBase {
    /**
     * 查询秒杀活动产品
     *
     * @param id 秒杀活动产品ID
     * @return 秒杀活动产品
     */
    public SmsFlashPromotionProduct selectSmsFlashPromotionProductById(Long id);

    /**
     * 查询秒杀活动产品列表
     *
     * @param smsFlashPromotionProduct 秒杀活动产品
     * @return 秒杀活动产品集合
     */
    public List<SmsFlashPromotionProduct> selectSmsFlashPromotionProductList(SmsFlashPromotionProduct smsFlashPromotionProduct);

    /**
     * 查询秒杀活动产品数量
     *
     * @param smsFlashPromotionProduct 秒杀活动产品
     * @return 结果
     */
    public int countSmsFlashPromotionProduct(SmsFlashPromotionProduct smsFlashPromotionProduct);

    /**
     * 唯一校验
     *
     * @param smsFlashPromotionProduct 秒杀活动产品
     * @return 结果
     */
    public int checkSmsFlashPromotionProductUnique(SmsFlashPromotionProduct smsFlashPromotionProduct);

    /**
     * 新增秒杀活动产品
     *
     * @param smsFlashPromotionProduct 秒杀活动产品
     * @return 结果
     */
    public int insertSmsFlashPromotionProduct(SmsFlashPromotionProduct smsFlashPromotionProduct);

    /**
     * 修改秒杀活动产品
     *
     * @param smsFlashPromotionProduct 秒杀活动产品
     * @return 结果
     */
    public int updateSmsFlashPromotionProduct(SmsFlashPromotionProduct smsFlashPromotionProduct);

    /**
     * 删除秒杀活动产品
     *
     * @param id 秒杀活动产品ID
     * @return 结果
     */
    public int deleteSmsFlashPromotionProductById(Long id);

    /**
     * 批量删除秒杀活动产品
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSmsFlashPromotionProductByIds(String[] ids);

}
