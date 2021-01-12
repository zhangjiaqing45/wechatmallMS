package com.fante.project.business.smsCouponProductCateRelation.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Stream;

import com.fante.common.constant.Constants;
import com.fante.common.utils.Checker;
import com.fante.common.utils.StringUtils;
import com.fante.project.business.smsCouponProductCateRelation.domain.SmsCouponProductCateRelationDTO;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fante.project.business.smsCouponProductCateRelation.mapper.SmsCouponProductCateRelationMapper;
import com.fante.project.business.smsCouponProductCateRelation.domain.SmsCouponProductCateRelation;
import com.fante.project.business.smsCouponProductCateRelation.service.ISmsCouponProductCateRelationService;
import com.fante.common.utils.text.Convert;
import org.springframework.util.ObjectUtils;

/**
 * 优惠券和产品分类关系Service业务层处理
 *
 * @author fante
 * @date 2020-03-20
 */
@Service
public class SmsCouponProductCateRelationServiceImpl implements ISmsCouponProductCateRelationService {

    private static Logger log = LoggerFactory.getLogger(SmsCouponProductCateRelationServiceImpl.class);

    @Autowired
    private SmsCouponProductCateRelationMapper smsCouponProductCateRelationMapper;

    /**
     * 查询优惠券和产品分类关系
     *
     * @param id 优惠券和产品分类关系ID
     * @return 优惠券和产品分类关系
     */
    @Override
    public SmsCouponProductCateRelation selectSmsCouponProductCateRelationById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        return smsCouponProductCateRelationMapper.selectSmsCouponProductCateRelationById(id);
    }

    /**
     * 查询优惠券和产品分类关系列表
     *
     * @param smsCouponProductCateRelation 优惠券和产品分类关系
     * @return 优惠券和产品分类关系集合
     */
    @Override
    public List<SmsCouponProductCateRelation> selectSmsCouponProductCateRelationList(SmsCouponProductCateRelation smsCouponProductCateRelation) {
        return smsCouponProductCateRelationMapper.selectSmsCouponProductCateRelationList(smsCouponProductCateRelation);
    }

    /**
     * 查询优惠券指定分类
     * @param relationDTO
     * @return
     */
    @Override
    public List<SmsCouponProductCateRelationDTO> selectJoinList(SmsCouponProductCateRelationDTO relationDTO) {
        return smsCouponProductCateRelationMapper.selectJoinList(relationDTO);
    }

    /**
     * 查询优惠券和产品分类关系数量
     *
     * @param smsCouponProductCateRelation 优惠券和产品分类关系
     * @return 结果
     */
    @Override
    public int countSmsCouponProductCateRelation(SmsCouponProductCateRelation smsCouponProductCateRelation){
        return smsCouponProductCateRelationMapper.countSmsCouponProductCateRelation(smsCouponProductCateRelation);
    }

    /**
     * 唯一校验
     *
     * @param smsCouponProductCateRelation 优惠券和产品分类关系
     * @return 结果
     */
    @Override
    public String checkSmsCouponProductCateRelationUnique(SmsCouponProductCateRelation smsCouponProductCateRelation) {
        return smsCouponProductCateRelationMapper.checkSmsCouponProductCateRelationUnique(smsCouponProductCateRelation) > 0 ? Constants.NOT_UNIQUE : Constants.UNIQUE;
    }

    /**
     * 新增优惠券和产品分类关系
     *
     * @param smsCouponProductCateRelation 优惠券和产品分类关系
     * @return 新增优惠券和产品分类关系数量
     */
    @Override
    public int insertSmsCouponProductCateRelation(SmsCouponProductCateRelation smsCouponProductCateRelation) {
        return smsCouponProductCateRelationMapper.insertSmsCouponProductCateRelation(smsCouponProductCateRelation);
    }

    /**
     * 修改优惠券和产品分类关系
     *
     * @param smsCouponProductCateRelation 优惠券和产品分类关系
     * @return 修改优惠券和产品分类关系数量
     */
    @Override
    public int updateSmsCouponProductCateRelation(SmsCouponProductCateRelation smsCouponProductCateRelation) {
        return smsCouponProductCateRelationMapper.updateSmsCouponProductCateRelation(smsCouponProductCateRelation);
    }

    /**
     * 删除优惠券和产品分类关系对象
     *
     * @param ids 需要删除的数据ID
     * @return 删除优惠券和产品分类关系数量
     */
    @Override
    public int deleteSmsCouponProductCateRelationByIds(String ids) {
        return smsCouponProductCateRelationMapper.deleteSmsCouponProductCateRelationByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除优惠券和产品分类关系信息
     *
     * @param id 优惠券和产品分类关系ID
     * @return 删除优惠券和产品分类关系数量
     */
    @Override
    public int deleteSmsCouponProductCateRelationById(Long id) {
        return smsCouponProductCateRelationMapper.deleteSmsCouponProductCateRelationById(id);
    }

    /**
     * 查询可以选择的商品类型
     * @param relationDTO
     * @return
     */
    @Override
    public List<SmsCouponProductCateRelationDTO> selectCateForCoupon(SmsCouponProductCateRelationDTO relationDTO) {
        return smsCouponProductCateRelationMapper.selectCateForCoupon(relationDTO);
    }

    /**
     * 根据优惠券删除分类关联
     * @param couponId
     * @return
     */
    @Override
    public int deleteByCouponId(Long couponId) {
        return smsCouponProductCateRelationMapper.deleteByCouponId(couponId);
    }

    /**
     * 批量新增优惠券分类关联
     * @param batchList
     * @return
     */
    @Override
    public int batchInsertCateForCoupon(List<SmsCouponProductCateRelation> batchList) {
        return smsCouponProductCateRelationMapper.batchInsertCateForCoupon(batchList);
    }

    @Override
    public int batchInsertCateForCoupon(Long couponId, String cateIds) {
        Checker.check(ObjectUtils.isEmpty(couponId), "缺少优惠券标识");
        Checker.check(StringUtils.isBlank(cateIds), "缺少指定分类");
        List<SmsCouponProductCateRelation> batchList = Lists.newArrayList();
        Stream.of(Convert.toLongArray(cateIds))
                .distinct()
                .forEach(cateId -> {
                    batchList.add(new SmsCouponProductCateRelation(couponId, cateId));
                });
        return batchInsertCateForCoupon(batchList);
    }
}
