package com.fante.project.business.smsFlashPromotion.service;

import com.fante.common.business.enums.SmsFlashConst;
import com.fante.project.business.smsFlashPromotion.domain.SmsFlashPromotion;
import com.fante.project.business.smsFlashPromotionProduct.domain.SmsFlashProductAddDTO;
import com.fante.project.business.smsFlashPromotionProduct.domain.SmsFlashProductEditDTO;

import java.util.List;

/**
 * 秒杀活动Service接口
 *
 * @author fante
 * @date 2020-03-21
 */
public interface ISmsFlashPromotionService {
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
    public String checkSmsFlashPromotionUnique(SmsFlashPromotion smsFlashPromotion);

    /**
     * 新增秒杀活动
     *
     * @param smsFlashPromotion 秒杀活动
     * @return 新增秒杀活动数量
     */
    public int insertSmsFlashPromotion(SmsFlashPromotion smsFlashPromotion);

    /**
     * 修改秒杀活动
     *
     * @param smsFlashPromotion 秒杀活动
     * @return 修改秒杀活动数量
     */
    public int updateSmsFlashPromotion(SmsFlashPromotion smsFlashPromotion);

    /**
     * 批量删除秒杀活动
     *
     * @param ids 需要删除的数据ID
     * @return 删除秒杀活动数量
     */
    public int deleteSmsFlashPromotionByIds(String ids);

    /**
     * 删除秒杀活动信息
     *
     * @param id 秒杀活动ID
     * @return 删除秒杀活动数量
     */
    public int deleteSmsFlashPromotionById(Long id);

    /**
     * 活动上架
     *
     * @param promotionId
     * @return
     */
    int putawayPromotion(Long promotionId);

    /**
     * 活动下架
     *
     * @param promotionId
     * @return
     */
    int soldoutPromotion(Long promotionId);

    /**
     * 校验是否满足操作执行需要的状态
     * @param shopId
     * @param promotionId
     * @param btnEnum
     */
    public void operateValidate(Long shopId, Long promotionId, SmsFlashConst.operateBtnEnum btnEnum);

    /**
     * 校验是否满足操作执行需要的状态
     * @param shopId
     * @param promotion
     * @param btnEnum
     */
    public void operateValidate(Long shopId, SmsFlashPromotion promotion, SmsFlashConst.operateBtnEnum btnEnum);

    /**
     * 新增秒杀活动商品处理
     * @param product
     */
    public void insertProductProcess(SmsFlashProductAddDTO product);

    /**
     * 编辑秒杀活动商品处理
     * @param product
     */
    public void updateProductProcess(SmsFlashProductEditDTO product);

    /**
     * 删除秒杀活动商品处理
     * @param flashPromotionId
     * @param promotionProductId
     */
    public void deleteProductProcess(Long flashPromotionId, Long promotionProductId);


    }
