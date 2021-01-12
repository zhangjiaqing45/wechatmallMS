package com.fante.project.api.omsOrderProcess.domain;

import com.fante.project.business.omsCartItem.domain.OmsCartItem;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 购物车详情
 *
 * @author fante
 * @date 2020-04-04
 */
public class CartDetail extends OmsCartItem {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "优惠券类型")
    private int realStock;

    public int getRealStock() {
        return realStock;
    }

    public void setRealStock(int realStock) {
        this.realStock = realStock;
    }
}
