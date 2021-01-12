package com.fante.project.business.pmsProduct.service;

import com.fante.project.api.appView.domain.PmsProductDetailPageInfo;
import com.fante.project.business.omsOrderItem.domain.OmsOrderItem;
import com.fante.project.business.pmsProduct.domain.*;
import com.fante.project.business.pmsProductCategory.domain.PmsShopProductCategory;
import com.fante.project.business.umsDistribution.domain.UmsDistributionResult;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * 商品信息Service接口
 *
 * @author fante
 * @date 2020-03-12
 */
public interface IPmsProductService {
    /**
     * 查询商品信息
     *
     * @param id 商品信息ID
     * @return 商品信息
     */
    public PmsProduct selectPmsProductById(Long id);

    /**
     * 查询商品信息列表
     *
     * @param pmsProduct 商品信息
     * @return 商品信息集合
     */
    public List<PmsProduct> selectPmsProductList(PmsProduct pmsProduct);
    /**
     * 查询商品信息列表 包括按钮组
     *
     * @param pmsProduct 商品信息
     * @return 商品信息集合
     */
    public List<PmsProduct> getPmsProductList(PmsProduct pmsProduct);
    /**
     * 查询商品信息数量
     *
     * @param pmsProduct 商品信息
     * @return 结果
     */
    public int countPmsProduct(PmsProduct pmsProduct);

    /**
     * 唯一校验
     *
     * @param pmsProduct 商品信息
     * @return 结果
     */
    public String checkPmsProductUnique(PmsProduct pmsProduct);

    /**
     * 新增商品信息
     *
     * @param productParam 商品信息
     * @return 新增商品信息数量
     */
    @Transactional
    public long insertPmsProduct(PmsProductParam productParam);

    /**
     * 行内修改商品序号信息(序号修改)
     *
     * @param pmsProduct 商品信息
     * @return 修改商品信息数量
     */
    public int editable(PmsProduct pmsProduct);

    /**
     * 修改商品信息
     *
     * @param productParam 商品信息
     * @return 修改商品信息数量
     */
    @Transactional
    public long updatePmsProduct(PmsProductParam productParam);

    /**
     * 批量删除商品信息
     *
     * @param ids 需要删除的数据ID
     * @return 删除商品信息数量
     */
    @Transactional
    public int deletePmsProductByIds(String ids);

    /**
     * 删除商品信息信息
     *
     * @param id 商品信息ID
     * @return 删除商品信息数量
     */
    @Transactional
    public int deletePmsProductById(Long id);

    /**
     * 提交审核
     * @param id
     * @return
     */
    @Transactional
    public int updatePmsProductVerify(Long id);

    /**
     * 条件查询商品信息列表包括店铺名称
     * @param pmsProduct
     * @return
     */
    public List<PmsProductResult> selectPmsProductShowList(PmsProduct pmsProduct);

    /**
     * 获取商品的审核详情
     * @param id
     * @return
     */
    public PmsProductResult selectPmsProductAuditDetail(Long id);

    /**
     * 商品审核通过
     * @param pmsProduct
     * @return
     */
    @Transactional
    public int pass(PmsProduct pmsProduct);

    /**
     * 商品审核拒绝
     * @param pmsProduct
     * @return
     */
    @Transactional
    public int refuse(PmsProduct pmsProduct);
    /**
     * 商品上架
     * @param id
     * @return
     */
    @Transactional
    public int putAway(Long id);
    /**
     * 商品下架
     * @param id
     * @return
     */
    @Transactional
    public int soldOut(Long id);

    /**
     * 统计店铺下的指定商品
     * @param shopId
     * @param pIds
     * @return
     */
    public int countProductsWithInShop(Long shopId, Long[] pIds);

    /**
     * 通过商品ids查询商品
     * @param productIds
     * @return
     */
    public List<PmsProduct> selectPmsProductByIds(Long[] productIds);

    /**
     * (app)根据商品分类,商品名称获取商品
     * @param categoryId
     * @param name
     * @return
     */
    List<PmsProductDetailPageInfo> getProductList(Long categoryId, String name, Long shopId);
    /**
     * (app)根据id查询商品详情
     * @param id
     * @return
     */
    PmsProductDetailPageInfo getDetailById(Long id);

    /**
     *  商品销量增加
     * @param orderItemList
     * @return
     */
    int addSale(List<OmsOrderItem> orderItemList);
}
