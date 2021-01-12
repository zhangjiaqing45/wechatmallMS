package com.fante.project.api.mq.req;

import com.fante.project.api.omsOrderProcess.domain.ValidateStockDTO;
import com.fante.project.business.omsOrder.domain.OmsOrder;
import com.fante.project.business.omsOrderItem.domain.OmsOrderItem;
import com.fante.project.business.omsPayOrder.domain.OmsPayOrder;

import java.io.Serializable;
import java.util.List;

/**
 * @author ftnet
 * @Description OrderHandleDTO
 * @CreatTime 2020/4/16
 */
public class OrderHandleParam implements Serializable {
    private static final long serialVersionUID = 1L;

    private OmsPayOrder payOrder;
    private OmsOrder order;
    private List<OmsOrderItem> orderItemList;
    private List<ValidateStockDTO> stockList;
    private String cartIds;
    private Long promotionId;


    public OrderHandleParam() {
    }

    public OrderHandleParam(OmsOrder order, List<OmsOrderItem> orderItemList, List<ValidateStockDTO> stockList) {
    }

    public OrderHandleParam(OmsOrder order, List<OmsOrderItem> orderItemList, List<ValidateStockDTO> stockList, String cartIds, Long promotionId) {
        this.order = order;
        this.orderItemList = orderItemList;
        this.stockList = stockList;
        this.cartIds = cartIds;
        this.promotionId = promotionId;
    }

    public OrderHandleParam(OmsOrder order, List<OmsOrderItem> orderItemList, List<ValidateStockDTO> stockList, Long promotionId) {
        this.order = order;
        this.orderItemList = orderItemList;
        this.stockList = stockList;
        this.promotionId = promotionId;
    }

    public OmsPayOrder getPayOrder() {
        return payOrder;
    }

    public void setPayOrder(OmsPayOrder payOrder) {
        this.payOrder = payOrder;
    }

    public Long getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Long promotionId) {
        this.promotionId = promotionId;
    }

    public OmsOrder getOrder() {
        return order;
    }

    public void setOrder(OmsOrder order) {
        this.order = order;
    }

    public List<OmsOrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OmsOrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public List<ValidateStockDTO> getStockList() {
        return stockList;
    }

    public void setStockList(List<ValidateStockDTO> stockList) {
        this.stockList = stockList;
    }

    public String getCartIds() {
        return cartIds;
    }

    public void setCartIds(String cartIds) {
        this.cartIds = cartIds;
    }
}
