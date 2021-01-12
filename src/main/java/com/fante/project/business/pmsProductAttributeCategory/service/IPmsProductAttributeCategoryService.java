package com.fante.project.business.pmsProductAttributeCategory.service;

import com.fante.project.business.pmsProductAttributeCategory.domain.PmsProductAttributeCategory;
import java.util.List;

/**
 * 产品属性分类Service接口
 *
 * @author fante
 * @date 2020-03-09
 */
public interface IPmsProductAttributeCategoryService {
    /**
     * 查询产品属性分类
     *
     * @param id 产品属性分类ID
     * @return 产品属性分类
     */
    public PmsProductAttributeCategory selectPmsProductAttributeCategoryById(Long id);

    /**
     * 查询产品属性分类列表
     *
     * @param pmsProductAttributeCategory 产品属性分类
     * @return 产品属性分类集合
     */
    public List<PmsProductAttributeCategory> selectPmsProductAttributeCategoryList(PmsProductAttributeCategory pmsProductAttributeCategory);

    /**
     * 新增产品属性分类
     *
     * @param pmsProductAttributeCategory 产品属性分类
     * @return 新增产品属性分类数量
     */
    public int insertPmsProductAttributeCategory(PmsProductAttributeCategory pmsProductAttributeCategory);

    /**
     * 修改产品属性分类
     *
     * @param pmsProductAttributeCategory 产品属性分类
     * @return 修改产品属性分类数量
     */
    public int updatePmsProductAttributeCategory(PmsProductAttributeCategory pmsProductAttributeCategory);

    /**
     * 批量删除产品属性分类
     *
     * @param ids 需要删除的数据ID
     * @return 删除产品属性分类数量
     */
    public int deletePmsProductAttributeCategoryByIds(String ids);

    /**
     * 删除产品属性分类信息
     *
     * @param id 产品属性分类ID
     * @return 删除产品属性分类数量
     */
    public int deletePmsProductAttributeCategoryById(Long id);
    /**
     * 唯一校验
     *
     * @param pmsProductAttributeCategory 产品属性分类
     * @return 结果
     */
    public String checkPmsProductAttributeCategoryUnique(PmsProductAttributeCategory pmsProductAttributeCategory);

}
