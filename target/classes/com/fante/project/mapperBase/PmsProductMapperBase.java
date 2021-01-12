package com.fante.project.mapperBase;

import com.fante.project.business.pmsProduct.domain.PmsProduct;
import java.util.List;

/**
 * 商品信息Mapper基础接口
 *
 * @author fante
 * @date 2020-03-21
 */
public interface PmsProductMapperBase {
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
    public int checkPmsProductUnique(PmsProduct pmsProduct);

    /**
     * 新增商品信息
     *
     * @param pmsProduct 商品信息
     * @return 结果
     */
    public int insertPmsProduct(PmsProduct pmsProduct);

    /**
     * 修改商品信息
     *
     * @param pmsProduct 商品信息
     * @return 结果
     */
    public int updatePmsProduct(PmsProduct pmsProduct);

    /**
     * 删除商品信息
     *
     * @param id 商品信息ID
     * @return 结果
     */
    public int deletePmsProductById(Long id);

    /**
     * 批量删除商品信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePmsProductByIds(String[] ids);

}
