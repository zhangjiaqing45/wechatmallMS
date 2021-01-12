package com.fante.project.business.umsMemberShopStar.mapper;

import com.fante.project.business.bizShopInfo.domain.BizShopInfo;
import com.fante.project.mapperBase.UmsMemberShopStarMapperBase;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 店铺收藏表Mapper扩展接口
 *
 * @author fante
 * @date 2020-04-24
 */

public interface UmsMemberShopStarMapper extends UmsMemberShopStarMapperBase {
    /**
     * 获取会员所有收藏到店铺
     */
    List<BizShopInfo> getMemberShopStar(Long memberId);



    /**
     * 批量删除店铺收藏
     *
     * @param id
     * @param memberId
     * @return 结果
     */
    public int memberDelShopStar(@Param("id") Long id, @Param("memberId")Long memberId);
}
