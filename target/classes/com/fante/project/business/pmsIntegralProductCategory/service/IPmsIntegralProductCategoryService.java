package com.fante.project.business.pmsIntegralProductCategory.service;

import com.fante.project.business.pmsIntegralProductCategory.domain.PmsIntegralProductCategory;
import java.util.List;

/**
 * 积分商品分类Service接口
 *
 * @author fante
 * @date 2020-03-18
 */
public interface IPmsIntegralProductCategoryService {
    /**
     * 查询积分商品分类
     *
     * @param id 积分商品分类ID
     * @return 积分商品分类
     */
    public PmsIntegralProductCategory selectPmsIntegralProductCategoryById(Long id);

    /**
     * 查询积分商品分类列表
     *
     * @param pmsIntegralProductCategory 积分商品分类
     * @return 积分商品分类集合
     */
    public List<PmsIntegralProductCategory> selectPmsIntegralProductCategoryList(PmsIntegralProductCategory pmsIntegralProductCategory);

    public List<PmsIntegralProductCategory> selectJoinList(PmsIntegralProductCategory pmsIntegralProductCategory);

    /**
     * 查询积分商品分类数量
     *
     * @param pmsIntegralProductCategory 积分商品分类
     * @return 结果
     */
    public int countPmsIntegralProductCategory(PmsIntegralProductCategory pmsIntegralProductCategory);

    /**
     * 唯一校验
     *
     * @param pmsIntegralProductCategory 积分商品分类
     * @return 结果
     */
    public String checkPmsIntegralProductCategoryUnique(PmsIntegralProductCategory pmsIntegralProductCategory);

    /**
     * 新增积分商品分类
     *
     * @param pmsIntegralProductCategory 积分商品分类
     * @return 新增积分商品分类数量
     */
    public int insertPmsIntegralProductCategory(PmsIntegralProductCategory pmsIntegralProductCategory);

    /**
     * 修改积分商品分类
     *
     * @param pmsIntegralProductCategory 积分商品分类
     * @return 修改积分商品分类数量
     */
    public int updatePmsIntegralProductCategory(PmsIntegralProductCategory pmsIntegralProductCategory);

    /**
     * 批量删除积分商品分类
     *
     * @param ids 需要删除的数据ID
     * @return 删除积分商品分类数量
     */
    public int deletePmsIntegralProductCategoryByIds(String ids);

    /**
     * 删除积分商品分类信息
     *
     * @param id 积分商品分类ID
     * @return 删除积分商品分类数量
     */
    public int deletePmsIntegralProductCategoryById(Long id);

    /**
     * (app)积分商品分类查询
     * @return
     */
    List<PmsIntegralProductCategory> getPmsIntegralProductCategoryForDisplay();
}
