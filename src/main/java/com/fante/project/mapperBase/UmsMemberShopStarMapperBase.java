package com.fante.project.mapperBase;

import com.fante.project.business.umsMemberShopStar.domain.UmsMemberShopStar;
import java.util.List;

/**
 * 店铺收藏Mapper基础接口
 *
 * @author fante
 * @date 2020-04-25
 */
public interface UmsMemberShopStarMapperBase {
    /**
     * 查询店铺收藏
     *
     * @param id 店铺收藏ID
     * @return 店铺收藏
     */
    public UmsMemberShopStar selectUmsMemberShopStarById(Long id);

    /**
     * 查询店铺收藏列表
     *
     * @param umsMemberShopStar 店铺收藏
     * @return 店铺收藏集合
     */
    public List<UmsMemberShopStar> selectUmsMemberShopStarList(UmsMemberShopStar umsMemberShopStar);

    /**
     * 查询店铺收藏数量
     *
     * @param umsMemberShopStar 店铺收藏
     * @return 结果
     */
    public int countUmsMemberShopStar(UmsMemberShopStar umsMemberShopStar);

    /**
     * 唯一校验
     *
     * @param umsMemberShopStar 店铺收藏
     * @return 结果
     */
    public int checkUmsMemberShopStarUnique(UmsMemberShopStar umsMemberShopStar);

    /**
     * 新增店铺收藏
     *
     * @param umsMemberShopStar 店铺收藏
     * @return 结果
     */
    public int insertUmsMemberShopStar(UmsMemberShopStar umsMemberShopStar);

    /**
     * 修改店铺收藏
     *
     * @param umsMemberShopStar 店铺收藏
     * @return 结果
     */
    public int updateUmsMemberShopStar(UmsMemberShopStar umsMemberShopStar);

    /**
     * 删除店铺收藏
     *
     * @param id 店铺收藏ID
     * @return 结果
     */
    public int deleteUmsMemberShopStarById(Long id);

    /**
     * 批量删除店铺收藏
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteUmsMemberShopStarByIds(String[] ids);
}
