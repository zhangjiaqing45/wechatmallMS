package com.fante.project.mapperBase;

import com.fante.project.api.appView.domain.ShopAndCouponInfo;
import com.fante.project.business.bizShopInfo.domain.BizShopInfo;
import java.util.List;

/**
 * 店铺信息Mapper基础接口
 *
 * @author fante
 * @date 2020-03-11
 */
public interface BizShopInfoMapperBase {
    /**
     * 查询店铺信息
     *
     * @param id 店铺信息ID
     * @return 店铺信息
     */
    public BizShopInfo selectBizShopInfoById(Long id);

    /**
     * 查询店铺信息列表
     *
     * @param bizShopInfo 店铺信息
     * @return 店铺信息集合
     */
    public List<BizShopInfo> selectBizShopInfoList(BizShopInfo bizShopInfo);

    /**
     * 查询店铺信息数量
     *
     * @param bizShopInfo 店铺信息
     * @return 结果
     */
    public int countBizShopInfo(BizShopInfo bizShopInfo);

    /**
     * 新增店铺信息
     *
     * @param bizShopInfo 店铺信息
     * @return 结果
     */
    public int insertBizShopInfo(BizShopInfo bizShopInfo);

    /**
     * 修改店铺信息
     *
     * @param bizShopInfo 店铺信息
     * @return 结果
     */
    public int updateBizShopInfo(BizShopInfo bizShopInfo);

    /**
     * 删除店铺信息
     *
     * @param id 店铺信息ID
     * @return 结果
     */
    public int deleteBizShopInfoById(Long id);

    /**
     * 批量删除店铺信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizShopInfoByIds(String[] ids);
}
