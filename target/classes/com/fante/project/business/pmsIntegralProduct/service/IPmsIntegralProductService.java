package com.fante.project.business.pmsIntegralProduct.service;

import com.fante.common.business.enums.ProductAttributeCategoryConst;
import com.fante.project.business.pmsIntegralProduct.domain.PmsIntegralProduct;

import java.util.List;

/**
 * 积分商品Service接口
 *
 * @author fante
 * @date 2020-03-19
 */
public interface IPmsIntegralProductService {
    /**
     * 查询积分商品
     *
     * @param id 积分商品ID
     * @return 积分商品
     */
    public PmsIntegralProduct selectPmsIntegralProductById(Long id);

    /**
     * 查询积分商品列表
     *
     * @param pmsIntegralProduct 积分商品
     * @return 积分商品集合
     */
    public List<PmsIntegralProduct> selectPmsIntegralProductList(PmsIntegralProduct pmsIntegralProduct);

    /**
     * 查询积分商品列表
     *
     * @param pmsIntegralProduct 积分商品
     * @return 积分商品集合
     */
    public List<PmsIntegralProduct> selectJoinList(PmsIntegralProduct pmsIntegralProduct);

    /**
     * 查询积分商品数量
     *
     * @param pmsIntegralProduct 积分商品
     * @return 结果
     */
    public int countPmsIntegralProduct(PmsIntegralProduct pmsIntegralProduct);

    /**
     * 唯一校验
     *
     * @param pmsIntegralProduct 积分商品
     * @return 结果
     */
    public String checkPmsIntegralProductUnique(PmsIntegralProduct pmsIntegralProduct);

    /**
     * 新增积分商品
     *
     * @param pmsIntegralProduct 积分商品
     * @return 新增积分商品数量
     */
    public int insertPmsIntegralProduct(PmsIntegralProduct pmsIntegralProduct);

    /**
     * 修改积分商品
     *
     * @param pmsIntegralProduct 积分商品
     * @return 修改积分商品数量
     */
    public int updatePmsIntegralProduct(PmsIntegralProduct pmsIntegralProduct);

    /**
     * 批量删除积分商品
     *
     * @param ids 需要删除的数据ID
     * @return 删除积分商品数量
     */
    public int deletePmsIntegralProductByIds(String ids);

    /**
     * 删除积分商品信息
     *
     * @param id 积分商品ID
     * @return 删除积分商品数量
     */
    public int deletePmsIntegralProductById(Long id);

    /**
     * 商品上架
     * @param id
     * @return
     */
    public int putawayPmsIntegralProduct(Long id);

    /**
     * 商品下架
     * @param id
     * @return
     */
    public int soldoutPmsIntegralProduct(Long id);

    /**
     * 更改排序
     * @param id
     * @param sort
     * @return
     */
    public int changeSort(Long id, Long sort);

    /**
     * 校验是否满足操作执行需要的状态
     * @param productId
     * @param btnEnum
     */
    public void operateValidate(Long productId, ProductAttributeCategoryConst.IntegralProductBtnEnum btnEnum);

    public void operateValidate(PmsIntegralProduct product, ProductAttributeCategoryConst.IntegralProductBtnEnum btnEnum);

    /**
     * 兑换成功>减库存
     * @param quantity
     * @param id
     * @return
     */
    int subStock(Long quantity, Long id);
    /**
     * （app）获取积分商品列表
     * @param categoryId
     * @param name
     * @return
     */
    List<PmsIntegralProduct> getPmsIntegralProductListForDisplay(Long categoryId, String name);

    /**
     * （app）获取积分商品详情
     * @param id
     * @return
     */
    PmsIntegralProduct getPmsIntegralProductDetailById(Long id);
}
