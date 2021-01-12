package com.fante.project.business.smsCouponProductCateRelation.service;

import com.fante.project.business.smsCouponProductCateRelation.domain.SmsCouponProductCateRelation;
import com.fante.project.business.smsCouponProductCateRelation.domain.SmsCouponProductCateRelationDTO;

import java.util.List;

/**
 * 优惠券和产品分类关系Service接口
 *
 * @author fante
 * @date 2020-03-20
 */
public interface ISmsCouponProductCateRelationService {
    /**
     * 查询优惠券和产品分类关系
     *
     * @param id 优惠券和产品分类关系ID
     * @return 优惠券和产品分类关系
     */
    public SmsCouponProductCateRelation selectSmsCouponProductCateRelationById(Long id);

    /**
     * 查询优惠券和产品分类关系列表
     *
     * @param smsCouponProductCateRelation 优惠券和产品分类关系
     * @return 优惠券和产品分类关系集合
     */
    public List<SmsCouponProductCateRelation> selectSmsCouponProductCateRelationList(SmsCouponProductCateRelation smsCouponProductCateRelation);

    /**
     * 查询优惠券指定分类
     * @param relationDTO
     * @return
     */
    public List<SmsCouponProductCateRelationDTO> selectJoinList(SmsCouponProductCateRelationDTO relationDTO);

    /**
     * 查询优惠券和产品分类关系数量
     *
     * @param smsCouponProductCateRelation 优惠券和产品分类关系
     * @return 结果
     */
    public int countSmsCouponProductCateRelation(SmsCouponProductCateRelation smsCouponProductCateRelation);

    /**
     * 唯一校验
     *
     * @param smsCouponProductCateRelation 优惠券和产品分类关系
     * @return 结果
     */
    public String checkSmsCouponProductCateRelationUnique(SmsCouponProductCateRelation smsCouponProductCateRelation);

    /**
     * 新增优惠券和产品分类关系
     *
     * @param smsCouponProductCateRelation 优惠券和产品分类关系
     * @return 新增优惠券和产品分类关系数量
     */
    public int insertSmsCouponProductCateRelation(SmsCouponProductCateRelation smsCouponProductCateRelation);

    /**
     * 修改优惠券和产品分类关系
     *
     * @param smsCouponProductCateRelation 优惠券和产品分类关系
     * @return 修改优惠券和产品分类关系数量
     */
    public int updateSmsCouponProductCateRelation(SmsCouponProductCateRelation smsCouponProductCateRelation);

    /**
     * 批量删除优惠券和产品分类关系
     *
     * @param ids 需要删除的数据ID
     * @return 删除优惠券和产品分类关系数量
     */
    public int deleteSmsCouponProductCateRelationByIds(String ids);

    /**
     * 删除优惠券和产品分类关系信息
     *
     * @param id 优惠券和产品分类关系ID
     * @return 删除优惠券和产品分类关系数量
     */
    public int deleteSmsCouponProductCateRelationById(Long id);

    /**
     * 查询可以选择的商品类型
     * @param relationDTO
     * @return
     */
    public List<SmsCouponProductCateRelationDTO> selectCateForCoupon(SmsCouponProductCateRelationDTO relationDTO);

    /**
     * 根据优惠券删除分类关联
     * @param couponId
     * @return
     */
    public int deleteByCouponId(Long couponId);

    /**
     * 批量新增优惠券分类关联
     * @param batchList
     * @return
     */
    public int batchInsertCateForCoupon(List<SmsCouponProductCateRelation> batchList);

    public int batchInsertCateForCoupon(Long couponId, String cateIds);
}
