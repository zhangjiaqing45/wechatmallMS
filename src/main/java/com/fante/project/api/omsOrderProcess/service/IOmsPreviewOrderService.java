package com.fante.project.api.omsOrderProcess.service;

import com.fante.project.api.omsOrderProcess.domain.AddressParam;
import com.fante.project.api.omsOrderProcess.domain.OmsCreateOrderParam;
import com.fante.project.api.omsOrderProcess.domain.OmsOrderShow;
import com.fante.project.api.omsOrderProcess.domain.UmsMemberReceiveAddressResult;
import com.fante.project.business.omsCartItem.domain.OmsCartItemParam;

import java.util.HashMap;
import java.util.List;

/**
 * 各种下单的服务
 */
public interface IOmsPreviewOrderService {
    /**
     * 从购物车中下单
     *
     * @param ids 购物车ids
     * @return
     */
    public List<OmsOrderShow> createPreviewByCart(String ids, Long memberId, Long addressId);

    /**
     * 立即购买
     *
     * @return
     */
    public List<OmsOrderShow> createPreviewByNow(String id, Long tokenUserId, Long addressId);

    /**
     * 团购
     *
     * @param param
     * @return
     */
    public OmsOrderShow createPreviewByGroup(OmsCartItemParam param);

    /**
     * 秒杀
     *
     * @param param
     * @return
     */
    public OmsOrderShow createPreviewBySeckill(OmsCartItemParam param);

    /**
     * 修改地址
     *
     * @param param
     * @return shopId : result
     */
    public HashMap<String, Object> changeAddress(AddressParam param);

}
