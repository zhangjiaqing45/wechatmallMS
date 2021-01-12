package com.fante.project.business.smsFlashPromotionProduct.service;

import com.fante.project.api.appView.domain.FlashProductParam;
import com.fante.project.api.appView.domain.PmsFlashProductDetailPageInfo;
import com.fante.project.api.appView.domain.PmsFlashProductDetailPageInfo;
import com.fante.project.business.smsFlashPromotionProduct.domain.SmsFlashPromotionProduct;
import com.fante.project.business.smsFlashPromotionProduct.domain.SmsFlashPromotionProductDTO;
import com.fante.project.business.smsFlashPromotionProduct.domain.SmsFlashPromotionProductSelectDTO;

import java.util.List;

/**
 * 秒杀活动产品Service接口
 *
 * @author fante
 * @date 2020-03-23
 */
public interface ISmsFlashPromotionProductService {
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
    public String checkSmsFlashPromotionProductUnique(SmsFlashPromotionProduct smsFlashPromotionProduct);

    /**
     * 新增秒杀活动产品
     *
     * @param smsFlashPromotionProduct 秒杀活动产品
     * @return 新增秒杀活动产品数量
     */
    public int insertSmsFlashPromotionProduct(SmsFlashPromotionProduct smsFlashPromotionProduct);

    /**
     * 修改秒杀活动产品
     *
     * @param smsFlashPromotionProduct 秒杀活动产品
     * @return 修改秒杀活动产品数量
     */
    public int updateSmsFlashPromotionProduct(SmsFlashPromotionProduct smsFlashPromotionProduct);

    /**
     * 批量删除秒杀活动产品
     *
     * @param ids 需要删除的数据ID
     * @return 删除秒杀活动产品数量
     */
    public int deleteSmsFlashPromotionProductByIds(String ids);

    /**
     * 删除秒杀活动产品信息
     *
     * @param id 秒杀活动产品ID
     * @return 删除秒杀活动产品数量
     */
    public int deleteSmsFlashPromotionProductById(Long id);


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
     * 设置排序
     * @param id
     * @param sort
     * @return
     */
    public int changeSort(Long id, Integer sort);

    /**
     * 设置商品参与状态
     * @param id
     * @return
     */
    public int changeStatus(Long id, String status);
    /**
     * (app)根据秒杀时间段获取秒杀商品
     */
    public  List<SmsFlashPromotionProductDTO> getFlashProductList(FlashProductParam param);
    /**
     * (app)获取秒杀商品详情
     * @param productId
     * @return
     */
    PmsFlashProductDetailPageInfo getFlashProductDetailInfo(Long productId);
}
