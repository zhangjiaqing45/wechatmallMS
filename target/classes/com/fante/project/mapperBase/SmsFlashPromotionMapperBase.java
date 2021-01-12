package com.fante.project.mapperBase;

import com.fante.project.business.smsFlashPromotion.domain.SmsFlashPromotion;
import java.util.List;

/**
 * 秒杀活动Mapper基础接口
 *
 * @author fante
 * @date 2020-03-21
 */
public interface SmsFlashPromotionMapperBase {
    /**
     * 查询秒杀活动
     *
     * @param id 秒杀活动ID
     * @return 秒杀活动
     */
    public SmsFlashPromotion selectSmsFlashPromotionById(Long id);

    /**
     * 查询秒杀活动列表
     *
     * @param smsFlashPromotion 秒杀活动
     * @return 秒杀活动集合
     */
    public List<SmsFlashPromotion> selectSmsFlashPromotionList(SmsFlashPromotion smsFlashPromotion);

    /**
     * 查询秒杀活动数量
     *
     * @param smsFlashPromotion 秒杀活动
     * @return 结果
     */
    public int countSmsFlashPromotion(SmsFlashPromotion smsFlashPromotion);

    /**
     * 唯一校验
     *
     * @param smsFlashPromotion 秒杀活动
     * @return 结果
     */
    public int checkSmsFlashPromotionUnique(SmsFlashPromotion smsFlashPromotion);

    /**
     * 新增秒杀活动
     *
     * @param smsFlashPromotion 秒杀活动
     * @return 结果
     */
    public int insertSmsFlashPromotion(SmsFlashPromotion smsFlashPromotion);

    /**
     * 修改秒杀活动
     *
     * @param smsFlashPromotion 秒杀活动
     * @return 结果
     */
    public int updateSmsFlashPromotion(SmsFlashPromotion smsFlashPromotion);

    /**
     * 删除秒杀活动
     *
     * @param id 秒杀活动ID
     * @return 结果
     */
    public int deleteSmsFlashPromotionById(Long id);

    /**
     * 批量删除秒杀活动
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSmsFlashPromotionByIds(String[] ids);

}
