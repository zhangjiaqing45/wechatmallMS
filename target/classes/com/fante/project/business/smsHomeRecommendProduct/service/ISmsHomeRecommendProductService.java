package com.fante.project.business.smsHomeRecommendProduct.service;

import com.fante.project.api.appView.domain.CmsRecommendReq;
import com.fante.project.business.smsHomeRecommendProduct.domain.SmsHomeRecommendProduct;
import com.fante.project.business.smsHomeRecommendProduct.domain.SmsHomeRecommendProductDTO;
import com.fante.project.business.smsHomeRecommendProduct.domain.SmsHomeRecommendSelectDTO;

import java.util.List;

/**
 * 推荐商品Service接口
 *
 * @author fante
 * @date 2020-03-10
 */
public interface ISmsHomeRecommendProductService {
    /**
     * 查询推荐商品
     *
     * @param id 推荐商品ID
     * @return 推荐商品
     */
    public SmsHomeRecommendProduct selectSmsHomeRecommendProductById(Long id);

    /**
     * 查询推荐商品列表
     *
     * @param smsHomeRecommendProduct 推荐商品
     * @return 推荐商品集合
     */
    public List<SmsHomeRecommendProduct> selectSmsHomeRecommendProductList(SmsHomeRecommendProduct smsHomeRecommendProduct);

    /**
     * 推荐商品信息
     * @param productDTO
     * @return
     */
    List<SmsHomeRecommendProductDTO> selectJoinList(SmsHomeRecommendProductDTO productDTO);

    /**
     * 新增推荐商品
     *
     * @param smsHomeRecommendProduct 推荐商品
     * @return 新增推荐商品数量
     */
    public int insertSmsHomeRecommendProduct(SmsHomeRecommendProduct smsHomeRecommendProduct);

    /**
     * 批量新增--指定推荐类型添加多个商品
     * @param productIds
     * @param type
     * @param createBy
     * @return
     */
    public int batchInsertSmsHomeRecommendProducts(String productIds, String type, String createBy);

    /**
     * 批量新增--单个商品添加多种推荐类型
     * @param productId
     * @param types
     * @param createBy
     * @return
     */
    public void batchInsertSmsHomeRecommendProductByTypes(Long productId, String types, String createBy);

    /**
     * 根据商品信息删除推荐信息
     * @param productId
     * @return
     */
    public int deleteSmsHomeRecommendProductByProductId(Long productId);


    /**
     * 检查当前类型中是否有重复商品信息
     * @param ids
     * @param type
     * @return
     */
    int checkProductUnique(Long[] ids, String type);

    /**
     * 修改推荐商品
     *
     * @param smsHomeRecommendProduct 推荐商品
     * @return 修改推荐商品数量
     */
    public int updateSmsHomeRecommendProduct(SmsHomeRecommendProduct smsHomeRecommendProduct);

    /**
     * 批量删除推荐商品
     *
     * @param ids 需要删除的数据ID
     * @return 删除推荐商品数量
     */
    public int deleteSmsHomeRecommendProductByIds(String ids);

    /**
     * 删除推荐商品信息
     *
     * @param id 推荐商品ID
     * @return 删除推荐商品数量
     */
    public int deleteSmsHomeRecommendProductById(Long id);

    /**
     * 推荐设置
     * @param id
     * @param status
     * @return
     */
    public int recommendSetting(Long id, String status);

    /**
     * 设置排序
     * @param id
     * @param sort
     * @return
     */
    public int changeSort(Long id, Long sort);


    /**
     * 选择商品
     * @param selectDTO
     * @return
     */
    List<SmsHomeRecommendSelectDTO> recommendSelect(SmsHomeRecommendSelectDTO selectDTO);

    /**
     * 按照推荐类型分类查询数据
     * @param type
     * @param showNum
     * @return
     */
    List<SmsHomeRecommendProductDTO> selectRecommendProductWithType(String type, int showNum);

    /**
     * 推荐商品信息(前端显示)
     * @param recommendReq
     * @return
     */
    List<SmsHomeRecommendProductDTO> selectAppJoinList(CmsRecommendReq recommendReq);

    }
