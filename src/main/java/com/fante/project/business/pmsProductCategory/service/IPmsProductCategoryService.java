package com.fante.project.business.pmsProductCategory.service;

import com.fante.common.business.enums.CommonUse;
import com.fante.common.utils.Checker;
import com.fante.project.business.pmsProductCategory.domain.PmsProductCategory;
import com.fante.project.business.pmsProductCategory.domain.PmsProductCategoryDetail;
import com.fante.project.business.pmsProductCategory.domain.PmsShopProductCategory;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;

/**
 * 产品分类Service接口
 *
 * @author fante
 * @date 2020-03-09
 */
public interface IPmsProductCategoryService {
    /**
     * 查询产品分类
     *
     * @param id 产品分类ID
     * @return 产品分类
     */
    public PmsProductCategory selectPmsProductCategoryById(Long id);

    /**
     * 查询产品分类列表
     *
     * @param pmsProductCategory 产品分类
     * @return 产品分类集合
     */
    public List<PmsProductCategory> selectPmsProductCategoryList(PmsProductCategory pmsProductCategory);
    
    /**
     * 查询产品分类列表
     *
     * @param param 产品分类
     * @return 产品分类集合
     */
    public List<PmsProductCategoryDetail> getPmsProductCategoryList(Map<String, Object> param);

    /**
     * 新增产品分类
     *
     * @param pmsProductCategory 产品分类
     * @return 新增产品分类数量
     */
    public int insertPmsProductCategory(PmsProductCategory pmsProductCategory);

    /**
     * 修改产品分类
     *
     * @param pmsProductCategory 产品分类
     * @return 修改产品分类数量
     */
    public int updatePmsProductCategory(PmsProductCategory pmsProductCategory);

    /**
     * 批量删除产品分类
     *
     * @param ids 需要删除的数据ID
     * @return 删除产品分类数量
     */
    public int deletePmsProductCategoryByIds(String ids);

    /**
     * 删除产品分类信息
     *
     * @param id 产品分类ID
     * @return 删除产品分类数量
     */
    public int deletePmsProductCategoryById(Long id);
    /**
     * 查询当前店铺未选商品分类
     *
     * @return 当前店铺未选商品分类列表
     */
    public List<PmsProductCategory> selectableCategory(String name);

    /**
     * 唯一校验
     *
     * @param pmsProductCategory 产品分类
     * @return 结果
     */
    public String checkPmsProductCategoryUnique(PmsProductCategory pmsProductCategory);
    /**
     * 通过id校验这个分类是否可用
     * @param id 产品分类id
     * @return 结果
     */
    public PmsProductCategory validatePmsProductCategoryById(Long id);

    /**
     * 获取分类app店铺展示
     * @param shopId shopId
     * @return 结果
     */
    public List<PmsShopProductCategory> getCategoryForNavInShop(Long shopId, Long level) ;
    /**
     * 获取所有分类app展示
     * @return 结果
     */
    public List<PmsProductCategory> getCategoryForNavInPlatform(Long level);
}
