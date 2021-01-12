package com.fante.project.api.omsOrderProcess.service;

import com.fante.project.api.omsOrderProcess.domain.CartCouponDTO;
import com.fante.project.api.omsOrderProcess.domain.CartSkuDto;
import com.fante.project.api.omsOrderProcess.domain.SmsCouponHistoryDetail;
import com.fante.project.business.omsOrder.domain.OmsOrder;
import com.fante.project.business.smsCoupon.domain.SmsCoupon;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ISmsMemberCouponService {


    /**
     * 根据订单状态查询会员订单
     *
     * @return
     */
    public List<SmsCouponHistoryDetail> getMemberCoupon(Long memberId);

    /**
     * 根据购物车获取可用券 组合
     * 前提：一张优惠券只能对应一个商品 或者 分类
     *
     * @param cartSkuDtos 存放sku和购买数量
     * @param totalAmount 订单总额
     * @return
     */
    public List<SmsCoupon> getJoinCoupon(List<CartSkuDto> cartSkuDtos, BigDecimal totalAmount, Long memberId);
}
