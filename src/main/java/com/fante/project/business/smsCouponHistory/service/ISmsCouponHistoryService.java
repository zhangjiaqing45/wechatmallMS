package com.fante.project.business.smsCouponHistory.service;

import com.fante.project.api.appView.domain.CouponHistoryDetail;
import com.fante.project.api.omsOrderProcess.domain.SmsCouponHistoryDetail;
import com.fante.project.business.smsCoupon.domain.SmsCoupon;
import com.fante.project.business.smsCouponHistory.domain.SmsCouponHistory;
import com.fante.project.business.smsCouponHistory.domain.SmsCouponHistoryDTO;

import java.util.List;

/**
 * 优惠券使用、领取历史Service接口
 *
 * @author fante
 * @date 2020-03-20
 */
public interface ISmsCouponHistoryService {
    /**
     * 查询优惠券使用、领取历史
     *
     * @param id 优惠券使用、领取历史ID
     * @return 优惠券使用、领取历史
     */
    public SmsCouponHistory selectSmsCouponHistoryById(Long id);

    /**
     * 查询优惠券使用、领取历史列表
     *
     * @param smsCouponHistory 优惠券使用、领取历史
     * @return 优惠券使用、领取历史集合
     */
    public List<SmsCouponHistory> selectSmsCouponHistoryList(SmsCouponHistory smsCouponHistory);

    /**
     * 查询优惠券使用、领取历史列表DTO
     *
     * @param smsCouponHistory 优惠券使用、领取历史
     * @return 优惠券使用、领取历史集合
     */
    public List<SmsCouponHistoryDTO> selectSmsCouponHistoryListDTO(SmsCouponHistoryDTO smsCouponHistory);

    /**
     * 查询优惠券使用、领取历史数量
     *
     * @param smsCouponHistory 优惠券使用、领取历史
     * @return 结果
     */
    public int countSmsCouponHistory(SmsCouponHistory smsCouponHistory);

    /**
     * 唯一校验
     *
     * @param smsCouponHistory 优惠券使用、领取历史
     * @return 结果
     */
    public String checkSmsCouponHistoryUnique(SmsCouponHistory smsCouponHistory);

    /**
     * 新增优惠券使用、领取历史
     *
     * @param smsCouponHistory 优惠券使用、领取历史
     * @return 新增优惠券使用、领取历史数量
     */
    public int insertSmsCouponHistory(SmsCouponHistory smsCouponHistory);

    /**
     * 修改优惠券使用、领取历史
     *
     * @param smsCouponHistory 优惠券使用、领取历史
     * @return 修改优惠券使用、领取历史数量
     */
    public int updateSmsCouponHistory(SmsCouponHistory smsCouponHistory);

    /**
     * 批量删除优惠券使用、领取历史
     *
     * @param ids 需要删除的数据ID
     * @return 删除优惠券使用、领取历史数量
     */
    public int deleteSmsCouponHistoryByIds(String ids);

    /**
     * 删除优惠券使用、领取历史信息
     *
     * @param id 优惠券使用、领取历史ID
     * @return 删除优惠券使用、领取历史数量
     */
    public int deleteSmsCouponHistoryById(Long id);
    /**
     * 获取用户 可用 优惠券
     * 1.获取用户优惠券领取历史
     * 2.根据历史联合查询优惠券中未使用的,并且 优惠券的过期时间未到 和 优惠券上架的状态
     * @param memberId
     * @return
     */
    public List<SmsCouponHistoryDetail> getMemberCouponUsable(Long memberId,Long shopId);

    /**
     * 使用优惠券 修改使用状态
     * @param couponHistory
     * @return
     */
    public int updateForUseCoupon(SmsCouponHistory couponHistory);

    /**
     * 商家批量发放优惠券到历史记录中
     * @param coupons
     * @param id
     * @return
     */
    int batchInsertCouponHistory(List<SmsCoupon> coupons, Long id);

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

    Integer updateUserState();//修改失效状态
}
