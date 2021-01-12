package com.fante.project.business.umsMemberProductStar.mapper;

import com.fante.project.api.appView.domain.PmsProductDetailPageInfo;
import com.fante.project.business.pmsProduct.domain.PmsProduct;
import com.fante.project.mapperBase.UmsMemberProductStarMapperBase;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品收藏表Mapper扩展接口
 *
 * @author fante
 * @date 2020-04-24
 */

public interface UmsMemberProductStarMapper extends UmsMemberProductStarMapperBase {
    /**
     * 获取会员收藏到所有商品
     */
    List<PmsProductDetailPageInfo> getMemberStarProduct(Long memberId);

    /**
     * 会员取消关注
     * @param id
     * @param memberId
     * @return
     */
    int memberDelProductStar(@Param("id") Long id, @Param("memberId")Long memberId);
}
