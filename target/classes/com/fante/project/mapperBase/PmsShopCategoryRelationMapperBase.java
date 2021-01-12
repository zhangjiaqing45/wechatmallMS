package com.fante.project.mapperBase;

import com.fante.project.business.pmsShopCategoryRelation.domain.PmsShopCategoryRelation;
import java.util.List;

/**
 * 店铺从平台选择的分类Mapper基础接口
 *
 * @author fante
 * @date 2020-03-12
 */
public interface PmsShopCategoryRelationMapperBase {
    /**
     * 查询店铺从平台选择的分类
     *
     * @param id 店铺从平台选择的分类ID
     * @return 店铺从平台选择的分类
     */
    public PmsShopCategoryRelation selectPmsShopCategoryRelationById(Long id);

    /**
     * 查询店铺从平台选择的分类列表
     *
     * @param pmsShopCategoryRelation 店铺从平台选择的分类
     * @return 店铺从平台选择的分类集合
     */
    public List<PmsShopCategoryRelation> selectPmsShopCategoryRelationList(PmsShopCategoryRelation pmsShopCategoryRelation);

    /**
     * 查询店铺从平台选择的分类数量
     *
     * @param pmsShopCategoryRelation 店铺从平台选择的分类
     * @return 结果
     */
    public int countPmsShopCategoryRelation(PmsShopCategoryRelation pmsShopCategoryRelation);

    /**
     * 唯一校验
     *
     * @param pmsShopCategoryRelation 店铺从平台选择的分类
     * @return 结果
     */
    public int checkPmsShopCategoryRelationUnique(PmsShopCategoryRelation pmsShopCategoryRelation);

    /**
     * 新增店铺从平台选择的分类
     *
     * @param pmsShopCategoryRelation 店铺从平台选择的分类
     * @return 结果
     */
    public int insertPmsShopCategoryRelation(PmsShopCategoryRelation pmsShopCategoryRelation);

    /**
     * 修改店铺从平台选择的分类
     *
     * @param pmsShopCategoryRelation 店铺从平台选择的分类
     * @return 结果
     */
    public int updatePmsShopCategoryRelation(PmsShopCategoryRelation pmsShopCategoryRelation);

    /**
     * 删除店铺从平台选择的分类
     *
     * @param id 店铺从平台选择的分类ID
     * @return 结果
     */
    public int deletePmsShopCategoryRelationById(Long id);

    /**
     * 批量删除店铺从平台选择的分类
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePmsShopCategoryRelationByIds(String[] ids);

}
