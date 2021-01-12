package com.fante.project.mapperBase;

import com.fante.project.business.pmsIntegralProduct.domain.PmsIntegralProduct;
import java.util.List;

/**
 * 积分商品Mapper基础接口
 *
 * @author fante
 * @date 2020-03-19
 */
public interface PmsIntegralProductMapperBase {
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
    public int checkPmsIntegralProductUnique(PmsIntegralProduct pmsIntegralProduct);

    /**
     * 新增积分商品
     *
     * @param pmsIntegralProduct 积分商品
     * @return 结果
     */
    public int insertPmsIntegralProduct(PmsIntegralProduct pmsIntegralProduct);

    /**
     * 修改积分商品
     *
     * @param pmsIntegralProduct 积分商品
     * @return 结果
     */
    public int updatePmsIntegralProduct(PmsIntegralProduct pmsIntegralProduct);

    /**
     * 删除积分商品
     *
     * @param id 积分商品ID
     * @return 结果
     */
    public int deletePmsIntegralProductById(Long id);

    /**
     * 批量删除积分商品
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePmsIntegralProductByIds(String[] ids);

}
