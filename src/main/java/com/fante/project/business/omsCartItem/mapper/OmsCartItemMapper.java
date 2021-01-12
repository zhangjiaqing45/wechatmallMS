package com.fante.project.business.omsCartItem.mapper;

import com.fante.project.api.appView.domain.ShopOfProductDatas;
import com.fante.project.business.omsCartItem.domain.OmsCartItem;
import com.fante.project.business.omsCartItem.domain.OmsCartItemResult;
import com.fante.project.mapperBase.OmsCartItemMapperBase;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * 购物车Mapper扩展接口
 *
 * @author fante
 * @date 2020-03-28
 */

public interface OmsCartItemMapper extends OmsCartItemMapperBase {
    /**
     * 查询当前登录用户的购物车
     * @param param
     * @return
     */
    List<OmsCartItemResult> selectOmsCartItemListOfMember(OmsCartItem param);
    /**
     * 根据ids查询下单所需数据
     * @param param
     * @return
     */
    List<OmsCartItemResult> selectOmsCartItemListForOrder(OmsCartItem param);

    /**
     * 通过productSkuId查询出购物车中需要的信息
     * @param productSkuId
     * @return
     */
    OmsCartItem selectCartMsgBySkuId(Long productSkuId);

    /**
     * 删除购物车
     * @param ids
     * @param memberId
     * @return
     */
    int deleteMemberCartByIds(@Param("ids") String[] ids, @Param("memberId") Long memberId);
    /**
     * 修改购物车数量
     * @return
     */
    int changeQuantity(OmsCartItem omsCartItem);

    /**
     * 根据购物车获取相应到店铺对应商品的数据
     * @param ids
     * @return
     */
    List<ShopOfProductDatas> getShopOfProudctsData(@Param("ids") String[] ids, @Param("memberId") Long memberId);
}
