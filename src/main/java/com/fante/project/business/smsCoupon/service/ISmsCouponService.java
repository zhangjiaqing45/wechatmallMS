package com.fante.project.business.smsCoupon.service;

import com.fante.common.business.enums.SmsCouponConst;
import com.fante.project.api.appView.domain.SmsMemberCouponDetail;
import com.fante.project.business.smsCoupon.domain.SmsCoupon;
import com.fante.project.business.smsCoupon.domain.SmsCouponDTO;
import com.fante.project.business.smsCoupon.domain.SmsCouponListDTO;

import java.util.List;

/**
 * 优惠券Service接口
 *
 * @author fante
 * @date 2020-03-19
 */
public interface ISmsCouponService {
    /**
     * 查询优惠券
     *
     * @param id 优惠券ID
     * @return 优惠券
     */
    public SmsCoupon selectSmsCouponById(Long id);

    /**
     * 查询优惠券信息
     * @param id
     * @return
     */
    public SmsCouponDTO selectSmsCouponAllById(Long id);

    /**
     * 查询优惠券列表
     *
     * @param smsCoupon 优惠券
     * @return 优惠券集合
     */
    public List<SmsMemberCouponDetail> selectSmsCouponList(SmsCoupon smsCoupon);

    /**
     * 查询优惠券列表
     *
     * @param smsCoupon 优惠券
     * @return 优惠券集合
     */
    public List<SmsCouponListDTO> selectSmsCouponListDTOList(SmsCoupon smsCoupon);

    /**
     * 查询优惠券数量
     *
     * @param smsCoupon 优惠券
     * @return 结果
     */
    public int countSmsCoupon(SmsCoupon smsCoupon);

    /**
     * 唯一校验
     *
     * @param smsCoupon 优惠券
     * @return 结果
     */
    public String checkSmsCouponUnique(SmsCoupon smsCoupon);

    /**
     * 新增优惠券
     *
     * @param smsCoupon 优惠券
     * @return 新增优惠券数量
     */
    public int insertSmsCoupon(SmsCoupon smsCoupon);

    /**
     * 优惠券新增处理
     * @param smsCoupon
     * @param selectIds
     */
    public void insertProcess(SmsCoupon smsCoupon, String selectIds);

    /**
     * 修改优惠券
     *
     * @param smsCoupon 优惠券
     * @return 修改优惠券数量
     */
    public int updateSmsCoupon(SmsCoupon smsCoupon);

    /**
     * 优惠券修改处理
     * @param smsCoupon
     * @param selectIds
     */
    public void updateProcess(SmsCoupon smsCoupon, String selectIds);

    /**
     * 批量删除优惠券
     *
     * @param ids 需要删除的数据ID
     * @return 删除优惠券数量
     */
    public int deleteSmsCouponByIds(String ids);

    /**
     * 删除优惠券信息
     *
     * @param id 优惠券ID
     * @return 删除优惠券数量
     */
    public int deleteSmsCouponById(Long id);

    /**
     * 优惠券上架
     * @param id
     * @return
     */
    public int putawayCoupon(Long id);

    /**
     * 优惠券下架
     * @param id
     * @return
     */
    public int soldoutCoupon(Long id);


    /**
     * 校验是否满足操作执行需要的状态
     * @param couponId
     * @param btnEnum
     */
    public void operateValidate(Long couponId, SmsCouponConst.operateBtnEnum btnEnum);


    public void operateValidate(SmsCoupon smsCoupon, SmsCouponConst.operateBtnEnum btnEnum);
    /**
     * (app)根据id查询商品的可用优惠券
     * @param id
     * @return
     */
    List<SmsCoupon> getUsableCouponsByProductId(Long id,Long memberId);
    /**
     * (app)根据店铺id查询商品的可用优惠券
     * @return
     */
    public List<SmsCoupon> getUsableCouponsByShopId(Long shopId,Long memberId);
    /**
     * (app)获取优惠券并验证是否可用
     * @return
     */
    public SmsCoupon getUsableCouponsById(Long couponId,Long memberId);

    /**
     * 减库存
     * @param couponId
     * @return
     */
    int  memeberGetCoupon(Long couponId);
    /**
     * 添加使用量
     * @param couponId
     * @return
     */
    int  memeberUseCoupon(Long couponId);

    /**
     * 获取用户可用优惠券
     * @param tokenUserId
     * @return
     */
    List<SmsMemberCouponDetail> getMemberEnableCoupon(Long tokenUserId,String useStatus);

    /**
     * 主动发放优惠券
     * @param memberId 用户id
     * @param shopId 上级用户的店铺id
     * @return 插入数量
     */
    int offerCoupons(Long memberId, Long shopId);

    /**
     * 批量发放优惠券
     * @param memberIds
     * @param couponId
     * @return
     */
    String beachGiveCoupon(String memberIds, Long couponId);
}
