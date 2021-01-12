package com.fante.project.business.omsOrder.service.impl;

import com.fante.common.business.enums.OrderConst;
import com.fante.common.constant.Constants;
import com.fante.common.utils.Checker;
import com.fante.common.utils.DateUtils;
import com.fante.common.utils.StringUtils;
import com.fante.common.utils.security.ShiroUtils;
import com.fante.common.utils.text.Convert;
import com.fante.project.api.setting.OrderStting;
import com.fante.project.business.omsOrder.domain.OmsOrder;
import com.fante.project.business.omsOrder.domain.OmsOrderDetail;
import com.fante.project.business.omsOrder.domain.RemarkParam;
import com.fante.project.business.omsOrder.domain.SendOrderParam;
import com.fante.project.business.omsOrder.mapper.OmsOrderMapper;
import com.fante.project.business.omsOrder.service.IOmsOrderService;
import com.fante.project.business.omsOrderOperateHistory.domain.OmsOrderOperateHistory;
import com.fante.project.business.omsOrderOperateHistory.domain.OmsOrderOperateHistoryParam;
import com.fante.project.business.omsOrderOperateHistory.service.IOmsOrderOperateHistoryService;
import com.fante.project.business.omsPayOrder.domain.OmsPayOrder;
import com.fante.project.business.pmsSkuStock.service.IPmsSkuStockService;
import com.fante.project.business.smsCouponHistory.domain.SmsCouponHistory;
import com.fante.project.business.smsCouponHistory.mapper.SmsCouponHistoryMapper;
import com.fante.project.business.smsFlashPromotionSku.service.ISmsFlashPromotionSkuService;
import com.fante.project.business.smsGroupGameRecord.service.ISmsGroupGameRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 订单Service业务层处理
 *
 * @author fante
 * @date 2020-04-01
 */
@Service
public class OmsCancelOrderService {

    private static Logger log = LoggerFactory.getLogger(OmsCancelOrderService.class);

    @Autowired
    private OmsOrderMapper omsOrderMapper;

    @Autowired
    private SmsCouponHistoryMapper smsCouponHistoryMapper;

    /**
     * 秒杀活动和sku关系表
     */
    @Autowired
    private ISmsFlashPromotionSkuService iSmsFlashPromotionSkuService;
    /**
     * 团购记录
     */
    @Autowired
    private ISmsGroupGameRecordService iSmsGroupGameRecordService;
    /**
     * 商品sku库存管理
     */
    @Autowired
    private IPmsSkuStockService iPmsSkuStockService;

    /**
     * 统一取消订单
     * @param order
     */
    @Transactional(rollbackFor = Exception.class)
    public void cancelOrder(OmsOrderDetail order, OmsPayOrder payOrder){
        //更改订单状态
        int count = omsOrderMapper.cancleOrderById(order.getId());
        Checker.check(count!=1 ,StringUtils.format("订单号:{},订单状态修改失败！",order.getId()));
        //根据订单类型 分别处理 普通订单库存  和秒杀订单库存
        String type = order.getOrderType();
        OrderConst.OrderType orderType = OrderConst.OrderType.get(type);
        switch (orderType){
            case FLASH:
                //秒杀订单 减锁定库存
                iSmsFlashPromotionSkuService.recoverFlashOrderStock(order);
                break;
            case GROUP:
                Long groupId = payOrder.getGroupId();
                if(!ObjectUtils.isEmpty(groupId)){
                    //团购订单:取消占用的位置
                    int i = iSmsGroupGameRecordService.recoverQuotas(groupId);
                    Checker.check(i!=1,"团购释放名额失败！团购记录id:"+groupId);
                }
                break;
            case GENERAL:
                //普通订单 减锁定库存
                int subLockStock = iPmsSkuStockService.recoverOrderStock(order);
                Checker.checkOp(subLockStock!=0 && order.getOrderItemList().size()==subLockStock,"锁定库存释放失败！");

                //优惠券变成未使用
                order.getMemberId();//用户id
                order.getGameId();//优惠券id

                //根据用户id和优惠券id修改用户优惠券使用状态为“未使用”
                SmsCouponHistory history=new SmsCouponHistory();
                history.setUserId(order.getMemberId());
                history.setCouponId(order.getGameId());
                List<SmsCouponHistory> list= smsCouponHistoryMapper.selectSmsCouponHistoryList(history);
                if(list.size()>0){
                    //如果优惠券已过期就不再修改优惠券状态
                    SmsCouponHistory history2=list.get(0);
                    if("1".equals(history2.getUseStatus())){
                        history2.setUseStatus("0");
                        smsCouponHistoryMapper.updateSmsCouponHistory(history2);
                    }
                }


                break;
            default:
                Checker.check(true,StringUtils.format("订单号:{},类型异常！类型码:{}",String.valueOf(order.getId()),type));
        }
    }

}
