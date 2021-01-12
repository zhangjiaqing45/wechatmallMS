package com.fante.project.business.pmsSkuStock.mapper;

import com.fante.project.api.omsOrderProcess.domain.CartSkuDto;
import com.fante.project.api.omsOrderProcess.domain.ValidateStockDTO;
import com.fante.project.business.omsOrder.domain.OmsOrderDetail;
import com.fante.project.business.omsOrderItem.domain.OmsOrderItem;
import com.fante.project.business.pmsProductComment.domain.PmsProductComment;
import com.fante.project.business.pmsSkuStock.domain.PmsSkuStock;
import com.fante.project.business.pmsSkuStock.domain.PmsSkuStockResult;
import com.fante.project.business.umsDistribution.domain.UmsDistributionResult;
import com.fante.project.mapperBase.PmsSkuStockMapperBase;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * sku的库存Mapper扩展接口
 *
 * @author fante
 * @date 2020-03-14
 */

public interface PmsSkuStockMapper extends PmsSkuStockMapperBase {
    /**
     * 批量插入sku
     * @param list sku集合
     */
     int beachInsert(List<PmsSkuStock> list);
    /**
     * 通过条件删除
     * @param sku
     */
     int deleteByExample(PmsSkuStock sku);

    /**
     * 条件查询缺货的sku的库存列表
     * 只要lowStock不为空,查询的都是缺货商品sku
     * @param pmsSkuStock
     * @return
     */
     List<PmsSkuStock> stockoutList(PmsSkuStockResult pmsSkuStock);
    /**
     * 查询skus
     * @param skuIds
     * @return
     */
     List<PmsSkuStock> selectPmsSkuStockByIds(String[] skuIds);

    /**
     * 根据购物车ids查询sku集合和数量
     * @param cartIds
     * @return
     */
     List<CartSkuDto> selectPmsSkuStockByCartIds(@Param("cartIds") String[] cartIds);
    /**
     * 通过skuid查询cartSku需要的源数据数据
     * 源数据:未修改价格和库存等信息的原始商品信息
     * @return
     */
     CartSkuDto getProductDetailBySkuId(Long skuId);
    /**
     * 取消 普通订单 减锁定库存
     * @param list
     */
     int recoverOrderStock(@Param("list") List<OmsOrderItem> list);
    /**
     * 验证库存
     * @param list
     * @return
     */
    List<ValidateStockDTO> validateStock(@Param("list")List<ValidateStockDTO> list);
    /**
     * 批量修改添加锁定库存
     * @param list
     * @return
     */
    int batchAddLockStock(@Param("list") List<ValidateStockDTO> list);
    /**
     * 支付成功:扣减库存
     * @param list
     * @return
     */
    int subtractStock(@Param("list")List<OmsOrderItem> list);

    /**
     * 修改sku库存/预警/价格
     * @param updateSku
     * @return
     */
    int updateStockOrLowStockOrPrice(PmsSkuStock updateSku);

}
