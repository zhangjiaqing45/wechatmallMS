package com.fante.project.business.pmsSkuStock.service;

import com.fante.project.api.omsOrderProcess.domain.CartSkuDto;
import com.fante.project.api.omsOrderProcess.domain.ValidateStockDTO;
import com.fante.project.business.omsOrder.domain.OmsOrderDetail;
import com.fante.project.business.omsOrderItem.domain.OmsOrderItem;
import com.fante.project.business.pmsProductComment.domain.PmsProductComment;
import com.fante.project.business.pmsSkuStock.domain.PmsSkuStock;
import com.fante.project.business.pmsSkuStock.domain.PmsSkuStockResult;
import com.fante.project.business.umsDistribution.domain.UmsDistributionResult;

import java.util.List;

/**
 * sku的库存Service接口
 *
 * @author fante
 * @date 2020-03-14
 */
public interface IPmsSkuStockService {
    /**
     * 查询sku的库存
     *
     * @param id sku的库存ID
     * @return sku的库存
     */
    public PmsSkuStock selectPmsSkuStockById(Long id);

    /**
     * 查询sku的库存列表
     *
     * @param pmsSkuStock sku的库存
     * @return sku的库存集合
     */
    public List<PmsSkuStock> selectPmsSkuStockList(PmsSkuStock pmsSkuStock);

    /**
     * 查询sku的库存数量
     *
     * @param pmsSkuStock sku的库存
     * @return 结果
     */
    public int countPmsSkuStock(PmsSkuStock pmsSkuStock);

    /**
     * 唯一校验
     *
     * @param pmsSkuStock sku的库存
     * @return 结果
     */
    public String checkPmsSkuStockUnique(PmsSkuStock pmsSkuStock);

    /**
     * 新增sku的库存
     *
     * @param pmsSkuStock sku的库存
     * @return 新增sku的库存数量
     */
    public int insertPmsSkuStock(PmsSkuStock pmsSkuStock);

    /**
     * 修改sku的库存
     *
     * @param pmsSkuStock sku的库存
     * @return 修改sku的库存数量
     */
    public int updatePmsSkuStock(PmsSkuStock pmsSkuStock);

    /**
     * 批量删除sku的库存
     *
     * @param ids 需要删除的数据ID
     * @return 删除sku的库存数量
     */
    public int deletePmsSkuStockByIds(String ids);

    /**
     * 删除sku的库存信息
     *
     * @param id sku的库存ID
     * @return 删除sku的库存数量
     */
    public int deletePmsSkuStockById(Long id);

    /**
     * 条件查询缺货的sku的库存列表
     * 只要lowStock不为空,查询的都是缺货商品sku
     * @param pmsSkuStock
     * @return
     */
    public List<PmsSkuStock> stockoutList(PmsSkuStockResult pmsSkuStock);

    /**
     * 修改保存sku的库存
     * @param pmsSkuStock
     * @param field
     * @return
     */
    public int updateEditField(PmsSkuStock pmsSkuStock,String field);

    /**
     * 查询skus
     * @param skuIds
     * @return
     */
    public List<PmsSkuStock> selectPmsSkuStockByIds(String[] skuIds);

    /**
     * 根据购物车ids查询sku集合和数量
     * @param cartIds
     * @return
     */
    List<CartSkuDto> selectPmsSkuStockByCartIds(String cartIds);

    /**
     * 通过skuid查询cartSku需要的源数据数据
     * 源数据:未修改价格和库存等信息的原始商品信息
     * @return
     */
    CartSkuDto getProductDetailBySkuId(Long skuId);

    /**
     * 取消 普通订单 减锁定库存
     * @param order
     */
    int recoverOrderStock(OmsOrderDetail order);

    /**
     * 验证库存
     * @param stockList
     * @return
     */
    List<ValidateStockDTO> validateStock(List<ValidateStockDTO> stockList);
    /**
     * 批量修改添加锁定库存
     * @param stockList
     * @return
     */
    public int batchAddLockStock(List<ValidateStockDTO> stockList);

    /**
     * 支付成功:扣减库存
     * @param orderItemList
     * @return
     */
    public int subtractStock(List<OmsOrderItem> orderItemList);
}
