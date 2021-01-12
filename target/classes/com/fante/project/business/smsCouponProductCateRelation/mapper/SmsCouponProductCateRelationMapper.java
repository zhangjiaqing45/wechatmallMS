package com.fante.project.business.smsCouponProductCateRelation.mapper;

import com.fante.project.business.smsCouponProductCateRelation.domain.SmsCouponProductCateRelation;
import com.fante.project.business.smsCouponProductCateRelation.domain.SmsCouponProductCateRelationDTO;
import com.fante.project.mapperBase.SmsCouponProductCateRelationMapperBase;

import java.util.List;

/**
 * 优惠券和产品分类关系Mapper扩展接口
 *
 * @author fante
 * @date 2020-03-20
 */

public interface SmsCouponProductCateRelationMapper extends SmsCouponProductCateRelationMapperBase {

    /**
     * 查询优惠券指定分类
     * @param relationDTO
     * @return
     */
    public List<SmsCouponProductCateRelationDTO> selectJoinList(SmsCouponProductCateRelationDTO relationDTO);

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
}
