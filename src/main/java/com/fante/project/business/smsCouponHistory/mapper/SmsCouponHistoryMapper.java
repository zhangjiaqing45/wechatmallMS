package com.fante.project.business.smsCouponHistory.mapper;

import com.fante.project.api.appView.domain.CouponHistoryDetail;
import com.fante.project.api.omsOrderProcess.domain.SmsCouponHistoryDetail;
import com.fante.project.business.smsCoupon.domain.SmsCoupon;
import com.fante.project.business.smsCouponHistory.domain.SmsCouponHistory;
import com.fante.project.business.smsCouponHistory.domain.SmsCouponHistoryDTO;
import com.fante.project.mapperBase.SmsCouponHistoryMapperBase;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 优惠券使用、领取历史Mapper扩展接口
 *
 * @author fante
 * @date 2020-03-20
 */

public interface SmsCouponHistoryMapper extends SmsCouponHistoryMapperBase {

    /**
     * 查询优惠券使用、领取历史列表DTO
     *
     * @param smsCouponHistory 优惠券使用、领取历史
     * @return 优惠券使用、领取历史集合
     */
    List<SmsCouponHistoryDTO> selectSmsCouponHistoryListDTO(SmsCouponHistoryDTO smsCouponHistory);
    /**
     * 获取用户 可用 优惠券
     * 1.获取用户优惠券领取历史
     * 2.根据历史联合查询优惠券中未使用的,并且 优惠券的过期时间未到 和 优惠券上架的状态
     * @param memberId
     * @return
     */
    List<SmsCouponHistoryDetail> getMemberCouponUsable(@Param("memberId") Long memberId, @Param("shopId")Long shopId);
    /**
     * 使用优惠券 修改使用状态
     * @param couponHistory
     * @return
     */
    int updateForUseCoupon(SmsCouponHistory couponHistory);
    /**
     * 商家批量发放优惠券到历史记录中
     * @param coupons
     * @param memberId
     * @return
     */
    int batchInsertCouponHistory(@Param("list") List<SmsCoupon> coupons, @Param("memberId") Long memberId);
    /**
     * 根据code查询优惠券领取历史信息
     * @param code
     * @return
     */
    CouponHistoryDetail selectSmsCouponHistoryByCode(String code);
    
    /**
     * 根据code查询优惠券使用状态
     * @param code
     * @return
     */
    String queryCouponUseStatusByCode(String code);
}
