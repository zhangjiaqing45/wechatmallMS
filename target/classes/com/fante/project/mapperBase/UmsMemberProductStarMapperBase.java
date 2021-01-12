package com.fante.project.mapperBase;

import com.fante.project.business.umsMemberProductStar.domain.UmsMemberProductStar;
import java.util.List;

/**
 * 商品收藏Mapper基础接口
 *
 * @author fante
 * @date 2020-04-25
 */
public interface UmsMemberProductStarMapperBase {
    /**
     * 查询商品收藏
     *
     * @param id 商品收藏ID
     * @return 商品收藏
     */
    public UmsMemberProductStar selectUmsMemberProductStarById(Long id);

    /**
     * 查询商品收藏列表
     *
     * @param umsMemberProductStar 商品收藏
     * @return 商品收藏集合
     */
    public List<UmsMemberProductStar> selectUmsMemberProductStarList(UmsMemberProductStar umsMemberProductStar);

    /**
     * 查询商品收藏数量
     *
     * @param umsMemberProductStar 商品收藏
     * @return 结果
     */
    public int countUmsMemberProductStar(UmsMemberProductStar umsMemberProductStar);

    /**
     * 唯一校验
     *
     * @param umsMemberProductStar 商品收藏
     * @return 结果
     */
    public int checkUmsMemberProductStarUnique(UmsMemberProductStar umsMemberProductStar);

    /**
     * 新增商品收藏
     *
     * @param umsMemberProductStar 商品收藏
     * @return 结果
     */
    public int insertUmsMemberProductStar(UmsMemberProductStar umsMemberProductStar);

    /**
     * 修改商品收藏
     *
     * @param umsMemberProductStar 商品收藏
     * @return 结果
     */
    public int updateUmsMemberProductStar(UmsMemberProductStar umsMemberProductStar);

    /**
     * 删除商品收藏
     *
     * @param id 商品收藏ID
     * @return 结果
     */
    public int deleteUmsMemberProductStarById(Long id);

    /**
     * 批量删除商品收藏
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteUmsMemberProductStarByIds(String[] ids);

}
