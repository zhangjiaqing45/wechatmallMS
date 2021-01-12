package com.fante.project.business.bizMainCategory.service;

import com.fante.project.business.bizMainCategory.domain.BizMainCategory;
import java.util.List;

/**
 * 店铺主营类目Service接口
 *
 * @author fante
 * @date 2020-03-10
 */
public interface IBizMainCategoryService {
    /**
     * 查询店铺主营类目
     *
     * @param id 店铺主营类目ID
     * @return 店铺主营类目
     */
    public BizMainCategory selectBizMainCategoryById(Long id);

    /**
     * 查询店铺主营类目列表
     *
     * @param bizMainCategory 店铺主营类目
     * @return 店铺主营类目集合
     */
    public List<BizMainCategory> selectBizMainCategoryList(BizMainCategory bizMainCategory);

    /**
     * 查询店铺主营类目数量
     *
     * @param bizMainCategory 店铺主营类目
     * @return 结果
     */
    public int countBizMainCategory(BizMainCategory bizMainCategory);

    /**
     * 新增店铺主营类目
     *
     * @param bizMainCategory 店铺主营类目
     * @return 新增店铺主营类目数量
     */
    public int insertBizMainCategory(BizMainCategory bizMainCategory);

    /**
     * 修改店铺主营类目
     *
     * @param bizMainCategory 店铺主营类目
     * @return 修改店铺主营类目数量
     */
    public int updateBizMainCategory(BizMainCategory bizMainCategory);

    /**
     * 批量删除店铺主营类目
     *
     * @param ids 需要删除的数据ID
     * @return 删除店铺主营类目数量
     */
    public int deleteBizMainCategoryByIds(String ids);

    /**
     * 删除店铺主营类目信息
     *
     * @param id 店铺主营类目ID
     * @return 删除店铺主营类目数量
     */
    public int deleteBizMainCategoryById(Long id);

    /**
     * 校验主营类目名称是否唯一
     * @param bizMainCategory
     * @return
     */
    public String checkCategoryUnique(BizMainCategory bizMainCategory);
}
