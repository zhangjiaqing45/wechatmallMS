package com.fante.project.mapperBase;

import com.fante.project.business.smsCouponProductCateRelation.domain.SmsCouponProductCateRelation;
import java.util.List;

/**
 * 优惠券和产品分类关系Mapper基础接口
 *
 * @author fante
 * @date 2020-03-20
 */
public interface SmsCouponProductCateRelationMapperBase {
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
    public int checkSmsCouponProductCateRelationUnique(SmsCouponProductCateRelation smsCouponProductCateRelation);

    /**
     * 新增优惠券和产品分类关系
     *
     * @param smsCouponProductCateRelation 优惠券和产品分类关系
     * @return 结果
     */
    public int insertSmsCouponProductCateRelation(SmsCouponProductCateRelation smsCouponProductCateRelation);

    /**
     * 修改优惠券和产品分类关系
     *
     * @param smsCouponProductCateRelation 优惠券和产品分类关系
     * @return 结果
     */
    public int updateSmsCouponProductCateRelation(SmsCouponProductCateRelation smsCouponProductCateRelation);

    /**
     * 删除优惠券和产品分类关系
     *
     * @param id 优惠券和产品分类关系ID
     * @return 结果
     */
    public int deleteSmsCouponProductCateRelationById(Long id);

    /**
     * 批量删除优惠券和产品分类关系
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSmsCouponProductCateRelationByIds(String[] ids);

}
