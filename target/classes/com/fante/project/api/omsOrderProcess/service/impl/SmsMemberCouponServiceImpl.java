package com.fante.project.api.omsOrderProcess.service.impl;

import com.fante.common.business.enums.SmsCouponConst;
import com.fante.common.constant.Constants;
import com.fante.project.api.omsOrderProcess.domain.CartCouponDTO;
import com.fante.project.api.omsOrderProcess.domain.CartSkuDto;
import com.fante.project.api.omsOrderProcess.domain.SmsCouponHistoryDetail;
import com.fante.project.api.omsOrderProcess.service.ISmsMemberCouponService;
import com.fante.project.business.omsOrderItem.domain.OmsOrderItem;
import com.fante.project.business.pmsSkuStock.service.IPmsSkuStockService;
import com.fante.project.business.smsCoupon.domain.SmsCoupon;
import com.fante.project.business.smsCoupon.service.ISmsCouponService;
import com.fante.project.business.smsCouponHistory.service.ISmsCouponHistoryService;
import com.fante.project.business.smsCouponProductCateRelation.service.ISmsCouponProductCateRelationService;
import com.fante.project.business.smsCouponProductRelation.service.ISmsCouponProductRelationService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author ftnet
 * @Description SmsMemberCouponServiceImpl
 * @CreatTime 2020/4/11
 */
@Service
public class SmsMemberCouponServiceImpl implements ISmsMemberCouponService {
    /**
     * 优惠券 用户领取历史表
     */
    @Autowired
    private ISmsCouponHistoryService iSmsCouponHistoryService;
    /**
     * 优惠券 表
     */
    @Autowired
    private ISmsCouponService iSmsCouponService;
    /**
     * 优惠券 指定分类关系表
     */
    @Autowired
    private ISmsCouponProductCateRelationService iSmsCouponProductCateRelationService;
    /**
     * 优惠券 指定商品关系表
     */
    @Autowired
    private ISmsCouponProductRelationService iSmsCouponProductRelationService;
    /**
     * sku表
     */
    @Autowired
    private IPmsSkuStockService iPmsSkuStockService;

    /**
     * 获取用户 可用 优惠券
     * 1.获取用户优惠券领取历史
     * 2.根据历史联合查询优惠券中未使用的,并且 优惠券的过期时间未到 和 优惠券上架的状态
     *
     * @return
     */
    @Override
    public List<SmsCouponHistoryDetail> getMemberCoupon(Long memberId) {
        return iSmsCouponHistoryService.getMemberCouponUsable(memberId,null);
    }


    /**
     * 计算购物车中商品价格综合
     *
     * @param cartSkuDtos 存放sku和购买数量
     * @return
     */
    public BigDecimal calcTotalAmount(List<CartSkuDto> cartSkuDtos) {
        BigDecimal total = BigDecimal.ZERO;
        for (CartSkuDto item : cartSkuDtos) {
            //直接使用促销价格即可
            BigDecimal realPrice = item.getPromotionPrice();
            //求和
            total = total.add(realPrice.multiply(new BigDecimal(item.getQuantity())));
        }
        return total;
    }

    /**
     * 计算购物车中商品的分类id包含在productCategoryIds数组中的所有商品的价格和
     *
     * @param cartSkuDtos
     * @return
     */
    private BigDecimal calcTotalAmountByproductCategoryId(List<CartSkuDto> cartSkuDtos, List<Long> categoryIds) {
        return cartSkuDtos.stream()
                .map(skuDto -> {
                    if (categoryIds.contains(skuDto.getProductCategoryId())) {
                        return skuDto.getPromotionPrice().multiply(new BigDecimal(skuDto.getQuantity()));
                    } else {
                        return BigDecimal.ZERO;
                    }
                }).reduce((A, B) -> A.add(B)).get();
    }

    /**
     * 计算购物车中的商品id属于productIds中的所有商品价格综合
     *
     * @param cartSkuDtos
     * @return
     */
    private BigDecimal calcTotalAmountByProductId(List<CartSkuDto> cartSkuDtos, List<Long> productIds) {
        return cartSkuDtos.stream()
                .map(skuDto -> {
                    if (productIds.contains(skuDto.getProductId())) {
                        return skuDto.getPromotionPrice().multiply(new BigDecimal(skuDto.getQuantity()));
                    } else {
                        return BigDecimal.ZERO;
                    }
                }).reduce((A, B) -> A.add(B)).get();
    }

    /**
     * 根据购物车获取可用券 组合
     * 前提：一张优惠券只能对应一个商品 或者 分类
     *
     * @param cartSkuDtos 存放sku和购买数量
     * @return
     */
    @Override
    public List<SmsCoupon> getJoinCoupon(List<CartSkuDto> cartSkuDtos, BigDecimal totalAmount, Long memberId) {
        //获取店铺id
        Long shopId = cartSkuDtos.get(0).getShopId();
        //获取该用户所有优惠券详情:优惠券表 / 优惠券和商品关系表 / 优惠券和分类关系表
        List<SmsCouponHistoryDetail> allList = iSmsCouponHistoryService.getMemberCouponUsable(memberId,shopId);
        //根据优惠券使用类型来判断优惠券是否可用
        //声明可用的优惠券
        List<SmsCoupon> allEnableCoupon = new ArrayList<>();

        for (SmsCouponHistoryDetail couponHistoryDetail : allList) {
            //使用门槛；0表示无门槛
            BigDecimal minPoint = new BigDecimal(couponHistoryDetail.getCoupon().getMinPoint());
            //详情中的优惠券
            SmsCoupon coupon = couponHistoryDetail.getCoupon();
            //优惠券使用类型0->全场通用；1->指定分类；2->指定商品
            String useType = coupon.getUseType();
            //优惠金额
            BigDecimal amount = coupon.getAmount();
            SmsCouponConst.useTypeEnum useTypeEnum = SmsCouponConst.useTypeEnum.get(useType);
            if (!ObjectUtils.isEmpty(useTypeEnum)) {
                // 2.对可用优惠券分类:全场类  分类可用  指定商品
                switch (useTypeEnum) {
                    //全场通用
                    case ALLUSE:
                        //0->全场通用
                        //判断是否满足优惠起点
                        if (totalAmount.compareTo(minPoint) >= 0) {
                            allEnableCoupon.add(coupon);
                        }
                        break;
                    //分类
                    case CLASSIFYUSE:
                        //1->指定分类
                        //计算指定分类商品的总价
                        //获取优惠券中的规定:商品分类id
                        List<Long> categoryIds = couponHistoryDetail.getCategoryRelationList().stream()
                                .map(x -> x.getProductCategoryId())
                                .collect(Collectors.toList());
                        BigDecimal cateAmount = calcTotalAmountByproductCategoryId(cartSkuDtos, categoryIds);
                        //判断最低消费额
                        if (cateAmount.intValue() > 0 && cateAmount.compareTo(minPoint) >= 0) {
                            //要所有
                            allEnableCoupon.add(coupon);
                        }
                        break;
                    //指定商品
                    case PRODUCTUSE:
                        //2->指定商品
                        //计算指定商品的总价
                        //获取优惠券中的规定:商品id
                        List<Long> productIds = couponHistoryDetail.getProductRelationList().stream()
                                .map(x -> x.getProductId())
                                .collect(Collectors.toList());
                        BigDecimal prodAmount = calcTotalAmountByProductId(cartSkuDtos, productIds);
                        //判断最低消费额
                        if (prodAmount.intValue() > 0 && prodAmount.compareTo(minPoint) >= 0) {
                            //要所有
                            allEnableCoupon.add(coupon);
                        }
                        break;
                    default:
                }
            }
        }
        return allEnableCoupon;
    }
}
