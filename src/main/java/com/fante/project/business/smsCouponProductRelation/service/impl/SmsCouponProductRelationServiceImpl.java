package com.fante.project.business.smsCouponProductRelation.service.impl;

import com.fante.common.constant.Constants;
import com.fante.common.utils.Checker;
import com.fante.common.utils.StringUtils;
import com.fante.common.utils.text.Convert;
import com.fante.project.business.smsCouponProductRelation.domain.SmsCouponProductRelation;
import com.fante.project.business.smsCouponProductRelation.domain.SmsCouponProductRelationDTO;
import com.fante.project.business.smsCouponProductRelation.mapper.SmsCouponProductRelationMapper;
import com.fante.project.business.smsCouponProductRelation.service.ISmsCouponProductRelationService;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Stream;

/**
 * 优惠券和产品的关系Service业务层处理
 *
 * @author fante
 * @date 2020-03-20
 */
@Service
public class SmsCouponProductRelationServiceImpl implements ISmsCouponProductRelationService {

    private static Logger log = LoggerFactory.getLogger(SmsCouponProductRelationServiceImpl.class);

    @Autowired
    private SmsCouponProductRelationMapper smsCouponProductRelationMapper;

    /**
     * 查询优惠券和产品的关系
     *
     * @param id 优惠券和产品的关系ID
     * @return 优惠券和产品的关系
     */
    @Override
    public SmsCouponProductRelation selectSmsCouponProductRelationById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        return smsCouponProductRelationMapper.selectSmsCouponProductRelationById(id);
    }

    /**
     * 查询优惠券和产品的关系列表
     *
     * @param smsCouponProductRelation 优惠券和产品的关系
     * @return 优惠券和产品的关系集合
     */
    @Override
    public List<SmsCouponProductRelation> selectSmsCouponProductRelationList(SmsCouponProductRelation smsCouponProductRelation) {
        return smsCouponProductRelationMapper.selectSmsCouponProductRelationList(smsCouponProductRelation);
    }

    /**
     * 优惠券指定商品
     * @param relationDTO
     * @return
     */
    @Override
    public List<SmsCouponProductRelationDTO> selectJoinList(SmsCouponProductRelationDTO relationDTO) {
        return smsCouponProductRelationMapper.selectJoinList(relationDTO);
    }

    /**
     * 查询优惠券和产品的关系数量
     *
     * @param smsCouponProductRelation 优惠券和产品的关系
     * @return 结果
     */
    @Override
    public int countSmsCouponProductRelation(SmsCouponProductRelation smsCouponProductRelation){
        return smsCouponProductRelationMapper.countSmsCouponProductRelation(smsCouponProductRelation);
    }

    /**
     * 唯一校验
     *
     * @param smsCouponProductRelation 优惠券和产品的关系
     * @return 结果
     */
    @Override
    public String checkSmsCouponProductRelationUnique(SmsCouponProductRelation smsCouponProductRelation) {
        return smsCouponProductRelationMapper.checkSmsCouponProductRelationUnique(smsCouponProductRelation) > 0 ? Constants.NOT_UNIQUE : Constants.UNIQUE;
    }

    /**
     * 新增优惠券和产品的关系
     *
     * @param smsCouponProductRelation 优惠券和产品的关系
     * @return 新增优惠券和产品的关系数量
     */
    @Override
    public int insertSmsCouponProductRelation(SmsCouponProductRelation smsCouponProductRelation) {
        return smsCouponProductRelationMapper.insertSmsCouponProductRelation(smsCouponProductRelation);
    }

    /**
     * 修改优惠券和产品的关系
     *
     * @param smsCouponProductRelation 优惠券和产品的关系
     * @return 修改优惠券和产品的关系数量
     */
    @Override
    public int updateSmsCouponProductRelation(SmsCouponProductRelation smsCouponProductRelation) {
        return smsCouponProductRelationMapper.updateSmsCouponProductRelation(smsCouponProductRelation);
    }

    /**
     * 删除优惠券和产品的关系对象
     *
     * @param ids 需要删除的数据ID
     * @return 删除优惠券和产品的关系数量
     */
    @Override
    public int deleteSmsCouponProductRelationByIds(String ids) {
        return smsCouponProductRelationMapper.deleteSmsCouponProductRelationByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除优惠券和产品的关系信息
     *
     * @param id 优惠券和产品的关系ID
     * @return 删除优惠券和产品的关系数量
     */
    @Override
    public int deleteSmsCouponProductRelationById(Long id) {
        return smsCouponProductRelationMapper.deleteSmsCouponProductRelationById(id);
    }

    /**
     * 查询可选择的商品
     * @param relationDTO
     * @return
     */
    @Override
    public List<SmsCouponProductRelationDTO> selectProductForCoupon(SmsCouponProductRelationDTO relationDTO) {
        return smsCouponProductRelationMapper.selectProductForCoupon(relationDTO);
    }

    /**
     * 根据优惠券删除商品关联
     * @param couponId
     * @return
     */
    @Override
    public int deleteByCouponId(Long couponId) {
        return smsCouponProductRelationMapper.deleteByCouponId(couponId);
    }

    /**
     * 批量新增优惠券商品关联
     * @param batchList
     * @return
     */
    @Override
    public int batchInsertProductForCoupon(List<SmsCouponProductRelation> batchList) {
        return smsCouponProductRelationMapper.batchInsertProductForCoupon(batchList);
    }

    @Override
    public int batchInsertProductForCoupon(Long couponId, String productIds) {
        Checker.check(ObjectUtils.isEmpty(couponId), "缺少优惠券标识");
        Checker.check(StringUtils.isBlank(productIds), "缺少指定商品");
        List<SmsCouponProductRelation> batchList = Lists.newArrayList();
        Stream.of(Convert.toLongArray(productIds))
                .distinct()
                .forEach(productId -> {
                    batchList.add(new SmsCouponProductRelation(couponId, productId));
                });
        return batchInsertProductForCoupon(batchList);
    }
}
