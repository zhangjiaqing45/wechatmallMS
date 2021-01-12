package com.fante.project.business.smsCouponProductRelation.service;

import com.fante.project.business.smsCouponProductRelation.domain.SmsCouponProductRelation;
import com.fante.project.business.smsCouponProductRelation.domain.SmsCouponProductRelationDTO;

import java.util.List;

/**
 * 优惠券和产品的关系Service接口
 *
 * @author fante
 * @date 2020-03-20
 */
public interface ISmsCouponProductRelationService {
    /**
     * 查询优惠券和产品的关系
     *
     * @param id 优惠券和产品的关系ID
     * @return 优惠券和产品的关系
     */
    public SmsCouponProductRelation selectSmsCouponProductRelationById(Long id);

    /**
     * 查询优惠券和产品的关系列表
     *
     * @param smsCouponProductRelation 优惠券和产品的关系
     * @return 优惠券和产品的关系集合
     */
    public List<SmsCouponProductRelation> selectSmsCouponProductRelationList(SmsCouponProductRelation smsCouponProductRelation);

    /**
     * 优惠券指定商品
     * @param relationDTO
     * @return
     */
    public List<SmsCouponProductRelationDTO> selectJoinList(SmsCouponProductRelationDTO relationDTO);

    /**
     * 查询优惠券和产品的关系数量
     *
     * @param smsCouponProductRelation 优惠券和产品的关系
     * @return 结果
     */
    public int countSmsCouponProductRelation(SmsCouponProductRelation smsCouponProductRelation);

    /**
     * 唯一校验
     *
     * @param smsCouponProductRelation 优惠券和产品的关系
     * @return 结果
     */
    public String checkSmsCouponProductRelationUnique(SmsCouponProductRelation smsCouponProductRelation);

    /**
     * 新增优惠券和产品的关系
     *
     * @param smsCouponProductRelation 优惠券和产品的关系
     * @return 新增优惠券和产品的关系数量
     */
    public int insertSmsCouponProductRelation(SmsCouponProductRelation smsCouponProductRelation);

    /**
     * 修改优惠券和产品的关系
     *
     * @param smsCouponProductRelation 优惠券和产品的关系
     * @return 修改优惠券和产品的关系数量
     */
    public int updateSmsCouponProductRelation(SmsCouponProductRelation smsCouponProductRelation);

    /**
     * 批量删除优惠券和产品的关系
     *
     * @param ids 需要删除的数据ID
     * @return 删除优惠券和产品的关系数量
     */
    public int deleteSmsCouponProductRelationByIds(String ids);

    /**
     * 删除优惠券和产品的关系信息
     *
     * @param id 优惠券和产品的关系ID
     * @return 删除优惠券和产品的关系数量
     */
    public int deleteSmsCouponProductRelationById(Long id);


    /**
     * 查询可选择的商品
     * @param relationDTO
     * @return
     */
    public List<SmsCouponProductRelationDTO> selectProductForCoupon(SmsCouponProductRelationDTO relationDTO);

    /**
     * 根据优惠券删除商品关联
     * @param couponId
     * @return
     */
    public int deleteByCouponId(Long couponId);

    /**
     * 批量新增优惠券商品关联
     * @param batchList
     * @return
     */
    public int batchInsertProductForCoupon(List<SmsCouponProductRelation> batchList);

    public int batchInsertProductForCoupon(Long couponId, String productIds);
}
