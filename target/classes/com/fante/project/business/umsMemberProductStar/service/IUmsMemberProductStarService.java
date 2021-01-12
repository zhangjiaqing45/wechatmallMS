package com.fante.project.business.umsMemberProductStar.service;

import com.fante.project.api.appView.domain.PmsProductDetailPageInfo;
import com.fante.project.business.umsMemberProductStar.domain.UmsMemberProductStar;
import java.util.List;

/**
 * 商品收藏表Service接口
 *
 * @author fante
 * @date 2020-04-24
 */
public interface IUmsMemberProductStarService {
    /**
     * 查询商品收藏表
     *
     * @param id 商品收藏表ID
     * @return 商品收藏表
     */
    public UmsMemberProductStar selectUmsMemberProductStarById(Long id);

    /**
     * 查询商品收藏表列表
     *
     * @param umsMemberProductStar 商品收藏表
     * @return 商品收藏表集合
     */
    public List<UmsMemberProductStar> selectUmsMemberProductStarList(UmsMemberProductStar umsMemberProductStar);

    /**
     * 查询商品收藏表数量
     *
     * @param umsMemberProductStar 商品收藏表
     * @return 结果
     */
    public int countUmsMemberProductStar(UmsMemberProductStar umsMemberProductStar);

    /**
     * 唯一校验
     *
     * @param umsMemberProductStar 商品收藏表
     * @return 结果
     */
    public String checkUmsMemberProductStarUnique(UmsMemberProductStar umsMemberProductStar);

    /**
     * 新增商品收藏表
     *
     * @param umsMemberProductStar 商品收藏表
     * @return 新增商品收藏表数量
     */
    public int insertUmsMemberProductStar(UmsMemberProductStar umsMemberProductStar);

    /**
     * 修改商品收藏表
     *
     * @param umsMemberProductStar 商品收藏表
     * @return 修改商品收藏表数量
     */
    public int updateUmsMemberProductStar(UmsMemberProductStar umsMemberProductStar);

    /**
     * 批量删除商品收藏表
     *
     * @param ids 需要删除的数据ID
     * @return 删除商品收藏表数量
     */
    public int deleteUmsMemberProductStarByIds(String ids);

    /**
     * 删除商品收藏表信息
     *
     * @param id 商品收藏表ID
     * @return 删除商品收藏表数量
     */
    public int deleteUmsMemberProductStarById(Long id,Long memberId);
    /**
     * 获取会员收藏到所有商品
     */
    List<PmsProductDetailPageInfo> getMemberStarProduct(Long memberId);
}

