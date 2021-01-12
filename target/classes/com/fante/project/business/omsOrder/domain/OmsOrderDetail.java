package com.fante.project.business.omsOrder.domain;

import com.fante.framework.aspectj.lang.annotation.Excel;
import com.fante.framework.web.domain.BaseEntity;
import com.fante.project.business.omsOrderItem.domain.OmsOrderItem;
import com.fante.project.business.omsOrderOperateHistory.domain.OmsOrderOperateHistory;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单对象 oms_order
 * 
 * @author fante
 * @date 2020-04-01
 */
public class OmsOrderDetail extends OmsOrder {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "订单详单集合")
    private List<OmsOrderItem> orderItemList;

    @ApiModelProperty(value = "订单操作集合")
    private List<OmsOrderOperateHistory> orderHistoryList;

    @ApiModelProperty(value = "操作按钮")
    private List<String> btns;

    public List<String> getBtns() {
        return btns;
    }

    public void setBtns(List<String> btns) {
        this.btns = btns;
    }

    public List<OmsOrderOperateHistory> getOrderHistoryList() {
        return orderHistoryList;
    }

    public void setOrderHistoryList(List<OmsOrderOperateHistory> orderHistoryList) {
        this.orderHistoryList = orderHistoryList;
    }

    public List<OmsOrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OmsOrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }
}
