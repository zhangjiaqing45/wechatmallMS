package com.fante.project.business.smsHomeRecommendProduct.mapper;

import com.fante.project.api.appView.domain.CmsRecommendReq;
import com.fante.project.business.smsHomeRecommendProduct.domain.SmsHomeRecommendProduct;
import com.fante.project.business.smsHomeRecommendProduct.domain.SmsHomeRecommendProductDTO;
import com.fante.project.business.smsHomeRecommendProduct.domain.SmsHomeRecommendSelectDTO;
import com.fante.project.mapperBase.SmsHomeRecommendProductMapperBase;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 推荐商品Mapper扩展接口
 *
 * @author fante
 * @date 2020-03-10
 */

public interface SmsHomeRecommendProductMapper extends SmsHomeRecommendProductMapperBase {

    /**
     * 检查当前类型中是否有重复商品信息
     * @param ids
     * @return
     */
    int checkProductUnique(@Param("ids") Long[] ids, @Param("type") String type);

    /**
     * 批量新增
     * @param batchData 商品集合
     * @return 新增推荐商品数量
     */
    int batchInsertSmsHomeRecommendProduct(List<SmsHomeRecommendProduct> batchData);

    /**
     * 删除推荐商品通过商品id
     *
     * @param id 推荐商品ID
     * @return 结果
     */
    public int deleteSmsHomeRecommendProductByProductId(Long id);

    /**
     * 推荐商品信息
     * @param productDTO
     * @return
     */
    List<SmsHomeRecommendProductDTO> selectJoinList(SmsHomeRecommendProductDTO productDTO);

    /**
     * 选择商品
     * @param selectDTO
     * @return
     */
    List<SmsHomeRecommendSelectDTO> recommendSelect(SmsHomeRecommendSelectDTO selectDTO);

    /**
     * 按照推荐类型分类查询数据
     * @param recommendReq
     * @return
     */
    List<SmsHomeRecommendProductDTO> selectRecommendProductWithType(CmsRecommendReq recommendReq);

    /**
     * 推荐商品信息(前端显示)
     * @param recommendReq
     * @return
     */
    List<SmsHomeRecommendProductDTO> selectAppJoinList(CmsRecommendReq recommendReq);
}
