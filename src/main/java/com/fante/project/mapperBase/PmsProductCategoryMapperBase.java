package com.fante.project.mapperBase;

import com.fante.project.business.pmsProductCategory.domain.PmsProductCategory;
import java.util.List;

/**
 * 产品分类Mapper基础接口
 *
 * @author fante
 * @date 2020-03-12
 */
public interface PmsProductCategoryMapperBase {
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
     * 查询产品分类数量
     *
     * @param pmsProductCategory 产品分类
     * @return 结果
     */
    public int countPmsProductCategory(PmsProductCategory pmsProductCategory);

    /**
     * 唯一校验
     *
     * @param pmsProductCategory 产品分类
     * @return 结果
     */
    public int checkPmsProductCategoryUnique(PmsProductCategory pmsProductCategory);

    /**
     * 新增产品分类
     *
     * @param pmsProductCategory 产品分类
     * @return 结果
     */
    public int insertPmsProductCategory(PmsProductCategory pmsProductCategory);

    /**
     * 修改产品分类
     *
     * @param pmsProductCategory 产品分类
     * @return 结果
     */
    public int updatePmsProductCategory(PmsProductCategory pmsProductCategory);

    /**
     * 删除产品分类
     *
     * @param id 产品分类ID
     * @return 结果
     */
    public int deletePmsProductCategoryById(Long id);

    /**
     * 批量删除产品分类
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePmsProductCategoryByIds(String[] ids);

}
