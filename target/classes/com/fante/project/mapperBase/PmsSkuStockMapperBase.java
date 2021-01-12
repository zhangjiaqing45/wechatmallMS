package com.fante.project.mapperBase;

import com.fante.project.business.pmsSkuStock.domain.PmsSkuStock;
import java.util.List;

/**
 * sku的库存Mapper基础接口
 *
 * @author fante
 * @date 2020-04-06
 */
public interface PmsSkuStockMapperBase {
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
    public int checkPmsSkuStockUnique(PmsSkuStock pmsSkuStock);

    /**
     * 新增sku的库存
     *
     * @param pmsSkuStock sku的库存
     * @return 结果
     */
    public int insertPmsSkuStock(PmsSkuStock pmsSkuStock);

    /**
     * 修改sku的库存
     *
     * @param pmsSkuStock sku的库存
     * @return 结果
     */
    public int updatePmsSkuStock(PmsSkuStock pmsSkuStock);

    /**
     * 删除sku的库存
     *
     * @param id sku的库存ID
     * @return 结果
     */
    public int deletePmsSkuStockById(Long id);

    /**
     * 批量删除sku的库存
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePmsSkuStockByIds(String[] ids);

}
