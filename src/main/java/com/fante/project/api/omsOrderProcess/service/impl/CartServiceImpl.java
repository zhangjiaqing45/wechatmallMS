package com.fante.project.api.omsOrderProcess.service.impl;

import com.fante.project.api.omsOrderProcess.service.ICartService;
import com.fante.project.business.omsCartItem.domain.OmsCartItemParam;
import com.fante.project.business.omsCartItem.domain.OmsCartItemResult;
import com.fante.project.business.omsCartItem.service.IOmsCartItemService;
import com.fante.project.business.omsOrderOperateHistory.service.IOmsOrderOperateHistoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 购物车
 */
@Service
public class CartServiceImpl implements ICartService {
    private static Logger log = LoggerFactory.getLogger(OmsOrderOperationServiceImpl.class);

    @Autowired
    private IOmsCartItemService iOmsCartItemService;

    @Autowired
    private IOmsOrderOperateHistoryService iOmsOrderOperateHistoryService;

    /**
     * 查询用户购物车信息
     *
     * @return
     */
    @Override
    public List<OmsCartItemResult> getMemberCartItems(Long memberId) {
        return iOmsCartItemService.selectOmsCartItemListOfMember(memberId);
    }

    /**
     * 删除购物车
     *
     * @return
     */
    @Override
    public int delMemberCarts(String ids, Long memberId) {
        return iOmsCartItemService.deleteOmsCartItemByIds(ids, memberId);
    }

    /**
     * 添加购物车
     *
     * @return
     */
    @Override
    public int add(OmsCartItemParam cartParam) {
        return iOmsCartItemService.add(cartParam);
    }

    /**
     * 修改购物车数量
     *
     * @param cartParam
     * @return
     */
    @Override
    public int changeQuantity(OmsCartItemParam cartParam) {
        return iOmsCartItemService.changeQuantity(cartParam);
    }
}
