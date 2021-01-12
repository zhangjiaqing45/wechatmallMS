package com.fante.project.api.mq.req;

import com.fante.common.utils.Checker;
import com.fante.project.api.omsOrderProcess.domain.ValidateStockDTO;
import com.fante.project.business.omsOrder.domain.OmsOrder;
import com.fante.project.business.omsOrderItem.domain.OmsOrderItem;
import com.fante.project.business.omsPayOrder.domain.OmsPayOrder;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author ftnet
 * @Description OrderHandleDTO
 * @CreatTime 2020/4/16
 */
public class OrderHandleOfCartParam implements Serializable {
    private static final long serialVersionUID = 1L;

    private String cartType;

    private String paymentType;

    private OmsPayOrder payOrder;

    private List<OrderHandleParam> OrderHandleList;

    public OmsPayOrder getPayOrder() {
        return payOrder;
    }

    public void setPayOrder(OmsPayOrder payOrder) {
        this.payOrder = payOrder;
    }

    public List<OrderHandleParam> getOrderHandleList() {
        return OrderHandleList;
    }

    public void setOrderHandleList(List<OrderHandleParam> orderHandleList) {
        OrderHandleList = orderHandleList;
    }

    public String getCartType() {
        return cartType;
    }

    public void setCartType(String cartType) {
        this.cartType = cartType;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * 计算支付价格
     */
    public void countPayTotalPrice() {
        Checker.check(ObjectUtils.isEmpty(this.OrderHandleList), "还没有订单,无法合计价格！");
        if (ObjectUtils.isEmpty(this.payOrder)) {
            this.payOrder = new OmsPayOrder();
        }
        BigDecimal totalPrice = this.OrderHandleList.stream().map(x -> x.getOrder().getPayAmount()).reduce((A, B) -> A.add(B)).orElse(BigDecimal.ZERO);
        this.payOrder.setPayTotalPrice(totalPrice);
    }
}
