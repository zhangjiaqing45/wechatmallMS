package com.fante.project.business.pmsShopCategoryRelation.service;

import com.fante.project.business.pmsProductCategory.domain.PmsProductCategory;
import com.fante.project.business.pmsShopCategoryRelation.domain.PmsShopCategoryRelation;
import com.fante.project.business.pmsShopCategoryRelation.domain.PmsShopCategoryRelationParam;
import com.fante.project.business.pmsShopCategoryRelation.domain.PmsShopCategoryRelationResult;

import java.util.List;

/**
 * 店铺从平台选择的分类Service接口
 *
 * @author fante
 * @date 2020-03-10
 */
public interface IPmsShopCategoryRelationService {
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
     * 新增店铺从平台选择的分类
     *
     * @param pmsShopCategoryRelation 店铺从平台选择的分类
     * @return 新增店铺从平台选择的分类数量
     */
    public int insertPmsShopCategoryRelation(PmsShopCategoryRelation pmsShopCategoryRelation);

    /**
     * 修改店铺从平台选择的分类
     *
     * @param pmsShopCategoryRelation 店铺从平台选择的分类
     * @return 修改店铺从平台选择的分类数量
     */
    public int updatePmsShopCategoryRelation(PmsShopCategoryRelation pmsShopCategoryRelation);

    /**
     * 批量删除店铺从平台选择的分类
     *
     * @param ids 需要删除的数据ID
     * @return 删除店铺从平台选择的分类数量
     */
    public int deletePmsShopCategoryRelationByIds(String ids);

    /**
     * 删除店铺从平台选择的分类信息
     *
     * @param id 店铺从平台选择的分类ID
     * @return 删除店铺从平台选择的分类数量
     */
    public int deletePmsShopCategoryRelationById(String id);
    /**
     * 查询店铺从平台选择的分类列表
     *
     * @return 店铺从平台选择的分类集合
     */
    public List<PmsShopCategoryRelationResult> selectPmsShopCategoryList(PmsShopCategoryRelationParam pmsProductCategoryParam);

    /**
     * 查询状态为展示的商品分类
     * 条件
     * 1.平台设置展示
     * 2.店铺设置展示
     */
    public List<PmsShopCategoryRelationResult> selectPmsCategoryListForAddPms();
    /**
     * 批量插入店铺选择的商品分类
     * @param ids
     * @return
     */
    public int insertPmsShopCategoryRelationList(String ids);

    /**
     * 通过关系表获取对应店铺的商品分类
     * @param id 店铺与商品分类关系表id
     * @return
     */
    public PmsProductCategory selectPmsShopCategoryByRelationId(Long id);
    /**
     * 唯一校验
     *
     * @param pmsShopCategoryRelation 店铺从平台选择的分类
     * @return 结果
     */
    public String checkPmsShopCategoryRelationUnique(PmsShopCategoryRelation pmsShopCategoryRelation);
}
