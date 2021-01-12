package com.fante.project.business.pmsProduct.mapper;

import com.fante.project.api.appView.domain.PmsProductDetailPageInfo;
import com.fante.project.business.omsOrderItem.domain.OmsOrderItem;
import com.fante.project.business.pmsProduct.domain.PmsProduct;
import com.fante.project.business.pmsProduct.domain.PmsProductResult;
import com.fante.project.mapperBase.PmsProductMapperBase;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品信息Mapper扩展接口
 *
 * @author fante
 * @date 2020-03-09
 */

public interface PmsProductMapper extends PmsProductMapperBase {
    /**
     * 查询商品信息,包括商铺名称
     * @param pmsProduct
     * @return
     */
    public List<PmsProductResult> selectPmsProductShowList(PmsProduct pmsProduct);

    /**
     * 查询商品信息,包括商铺名称
     * 日期可以单方向查询
     * @param pmsProduct
     * @return
     */
    public List<PmsProduct> getPmsProductList(PmsProduct pmsProduct);

    /**
     * 查询商品信息,包括 sku集合 和 推荐类型集合
     * @param id
     * @return
     */
    public PmsProductResult selectPmsProductAuditById(Long id);

    /**
     * 统计店铺下的指定商品
     * @param shopId
     * @param pids
     * @return
     */
    public int countProductsWithInShop(@Param("shopId") Long shopId, @Param("pids") Long[] pids);

    /**
     * 通过商品ids查询商品
     * @param ids
     * @return
     */
    List<PmsProduct> selectPmsProductByIds(@Param("ids") Long[] ids);
    /**
     * (app)根据商品分类,商品名称获取商品
     * @param publishStatus
     * @param categoryId
     * @param name
     * @param shopId
     * @return
     */
    List<PmsProductDetailPageInfo> getProductList(@Param("publishStatus")String publishStatus,
                                                  @Param("categoryId")Long categoryId,
                                                  @Param("name")String name,
                                                  @Param("shopId")Long shopId
    );
    /**
     * (app)根据id查询商品详情
     * @param id
     * @return
     */
    PmsProductDetailPageInfo getDetailById(@Param("id") Long id, @Param("publishStatus") String publishStatus);
    /**
     *  商品销量增加
     * @param list
     * @return
     */
    int addSale(@Param("list") List<OmsOrderItem> list);
}
