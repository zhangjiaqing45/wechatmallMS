package com.fante.project.business.smsCoupon.mapper;

import com.fante.common.business.enums.SmsCouponConst;
import com.fante.project.api.appView.domain.SmsMemberCouponDetail;
import com.fante.project.business.smsCoupon.domain.SmsCoupon;
import com.fante.project.business.smsCoupon.domain.SmsCouponListDTO;
import com.fante.project.mapperBase.SmsCouponMapperBase;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 优惠券Mapper扩展接口
 *
 * @author fante
 * @date 2020-03-19
 */

public interface SmsCouponMapper extends SmsCouponMapperBase {
    /**
     * (app)根据id查询商品到可用优惠券
     *
     * @return
     */
    List<SmsCoupon> getUsableCouponsByProductId(@Param("productId") Long productId,
                                                @Param("shopId") Long shopId,
                                                @Param("memberId") Long memberId
                                               );

    /**
     * (app)根据店铺id查询商品的可用优惠券
     *
     * @return
     */
    List<SmsCoupon> getUsableCouponsByShopId(@Param("shopId") Long shopId,
                                             @Param("memberId") Long memberId
                                            );
    /**
     * (app)获取优惠券并验证是否可用
     * @return
     */
    SmsCoupon getUsableCouponsById(@Param("couponId") Long couponId, @Param("memberId") Long memberId);

    /**
     * 领取优惠券 减库存 加领取数量
     * @param couponId
     * @return
     */
    int memeberGetCoupon(Long couponId);
    /**
     * 获取用户可用优惠券
     * @param memberId
     * @return
     */
    List<SmsMemberCouponDetail> getMemberEnableCoupon(Long memberId,String useStatus);

    /**
     * 查询上级所在店铺主动方法的优惠券
     * @param shopId
     * @param type
     * @return
     */
    List<SmsCoupon> getOfferCouponsByShopId(Long shopId, String type);

    /**
     * 查询优惠券包括店铺名称
     * @param smsCoupon
     * @return
     */
    List<SmsMemberCouponDetail> selectSmsCouponDetailList(SmsCoupon smsCoupon);

    /**
     * 查询优惠券包括店铺名称
     * @param smsCoupon
     * @return
     */
    List<SmsCouponListDTO> selectSmsCouponListDTOList(SmsCoupon smsCoupon);
    /**
     * 添加使用量
     * @param couponId
     * @return
     */
    int memeberUseCoupon(Long couponId);
}
