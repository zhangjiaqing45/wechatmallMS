package com.fante.project.business.smsCouponProductRelation.mapper;

import com.fante.project.business.smsCouponProductRelation.domain.SmsCouponProductRelation;
import com.fante.project.business.smsCouponProductRelation.domain.SmsCouponProductRelationDTO;
import com.fante.project.mapperBase.SmsCouponProductRelationMapperBase;

import java.util.List;

/**
 * 优惠券和产品的关系Mapper扩展接口
 *
 * @author fante
 * @date 2020-03-20
 */

public interface SmsCouponProductRelationMapper extends SmsCouponProductRelationMapperBase {


    /**
     * 优惠券指定商品
     * @param relationDTO
     * @return
     */
    public List<SmsCouponProductRelationDTO> selectJoinList(SmsCouponProductRelationDTO relationDTO);

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

}
