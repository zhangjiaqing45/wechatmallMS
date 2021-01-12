package com.fante.project.business.omsOrderOperateHistory.domain;

import com.fante.common.business.enums.OrderConst;
import com.fante.common.business.enums.ProductAttributeCategoryConst;
import com.fante.framework.aspectj.lang.annotation.Excel;
import com.fante.framework.web.domain.BaseEntity;
import com.fante.project.business.omsOrder.domain.OmsOrder;
import com.fante.project.business.omsOrderItem.domain.OmsOrderItem;
import com.fante.project.business.pmsSkuStock.domain.PmsSkuStock;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * 订单操作历史记录对象 oms_order_operate_history
 * 
 * @author fante
 * @date 2020-04-02
 */
public class OmsOrderOperateHistoryParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "订单")
    private OmsOrder order;

    @ApiModelProperty(value = "动作")
    private OrderConst.OrderActionEnum action;


    public OrderConst.OrderActionEnum getAction() {
        return action;
    }

    public void setAction(OrderConst.OrderActionEnum action) {
        this.action = action;
    }

    public OmsOrder getOrder() {
        return order;
    }

    public void setOrder(OmsOrder order) {
        this.order = order;
    }

}
