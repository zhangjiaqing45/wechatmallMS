package com.fante.project.mapperBase;

import com.fante.project.business.smsHomeRecommendProduct.domain.SmsHomeRecommendProduct;
import java.util.List;

/**
 * 推荐商品Mapper基础接口
 *
 * @author fante
 * @date 2020-03-10
 */
public interface SmsHomeRecommendProductMapperBase {
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
     * 新增推荐商品
     *
     * @param smsHomeRecommendProduct 推荐商品
     * @return 结果
     */
    public int insertSmsHomeRecommendProduct(SmsHomeRecommendProduct smsHomeRecommendProduct);

    /**
     * 修改推荐商品
     *
     * @param smsHomeRecommendProduct 推荐商品
     * @return 结果
     */
    public int updateSmsHomeRecommendProduct(SmsHomeRecommendProduct smsHomeRecommendProduct);

    /**
     * 删除推荐商品
     *
     * @param id 推荐商品ID
     * @return 结果
     */
    public int deleteSmsHomeRecommendProductById(Long id);

    /**
     * 批量删除推荐商品
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSmsHomeRecommendProductByIds(String[] ids);
}
