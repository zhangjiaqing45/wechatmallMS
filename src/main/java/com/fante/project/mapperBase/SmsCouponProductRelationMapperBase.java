package com.fante.project.mapperBase;

import com.fante.project.business.smsCouponProductRelation.domain.SmsCouponProductRelation;
import java.util.List;

/**
 * 优惠券和产品的关系Mapper基础接口
 *
 * @author fante
 * @date 2020-03-20
 */
public interface SmsCouponProductRelationMapperBase {
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
    public int checkSmsCouponProductRelationUnique(SmsCouponProductRelation smsCouponProductRelation);

    /**
     * 新增优惠券和产品的关系
     *
     * @param smsCouponProductRelation 优惠券和产品的关系
     * @return 结果
     */
    public int insertSmsCouponProductRelation(SmsCouponProductRelation smsCouponProductRelation);

    /**
     * 修改优惠券和产品的关系
     *
     * @param smsCouponProductRelation 优惠券和产品的关系
     * @return 结果
     */
    public int updateSmsCouponProductRelation(SmsCouponProductRelation smsCouponProductRelation);

    /**
     * 删除优惠券和产品的关系
     *
     * @param id 优惠券和产品的关系ID
     * @return 结果
     */
    public int deleteSmsCouponProductRelationById(Long id);

    /**
     * 批量删除优惠券和产品的关系
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSmsCouponProductRelationByIds(String[] ids);

}
