package com.fante.project.api.omsOrderProcess.service;

import com.fante.project.business.omsCartItem.domain.OmsCartItem;
import com.fante.project.business.omsCartItem.domain.OmsCartItemParam;
import com.fante.project.business.omsCartItem.domain.OmsCartItemResult;

import java.util.List;

public interface ICartService {
    /**
     * 查询用户购物车信心
     *
     * @return
     */
    public List<OmsCartItemResult> getMemberCartItems(Long memberId);

    /**
     * 删除购物车
     *
     * @return
     */
    public int delMemberCarts(String ids, Long memberId);

    /**
     * 添加购物车
     *
     * @return
     */
    public int add(OmsCartItemParam cartParam);

    /**
     * 修改购物车数量
     *
     * @return
     */
    public int changeQuantity(OmsCartItemParam cartParam);
}
