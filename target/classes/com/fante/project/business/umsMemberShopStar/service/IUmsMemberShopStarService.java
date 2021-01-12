package com.fante.project.business.umsMemberShopStar.service;

import com.fante.project.business.bizShopInfo.domain.BizShopInfo;
import com.fante.project.business.umsMemberShopStar.domain.UmsMemberShopStar;
import java.util.List;

/**
 * 店铺收藏Service接口
 *
 * @author fante
 * @date 2020-04-24
 */
public interface IUmsMemberShopStarService {
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
    public String checkUmsMemberShopStarUnique(UmsMemberShopStar umsMemberShopStar);

    /**
     * 新增店铺收藏
     *
     * @param umsMemberShopStar 店铺收藏
     * @return 新增店铺收藏数量
     */
    public int insertUmsMemberShopStar(UmsMemberShopStar umsMemberShopStar);

    /**
     * 修改店铺收藏
     *
     * @param umsMemberShopStar 店铺收藏
     * @return 修改店铺收藏数量
     */
    public int updateUmsMemberShopStar(UmsMemberShopStar umsMemberShopStar);

    /**
     * 批量删除店铺收藏
     *
     * @param ids 需要删除的数据ID
     * @return 删除店铺收藏数量
     */
    public int deleteUmsMemberShopStarByIds(String ids);

    /**
     * 删除店铺收藏信息
     *
     * @param id 店铺收藏ID
     * @return 删除店铺收藏数量
     */
    public int deleteUmsMemberShopStarById(Long id,Long memberId);
    /**
     * 获取会员所有收藏到店铺
     */
    List<BizShopInfo> getMemberShopStar(Long memberId);
}
