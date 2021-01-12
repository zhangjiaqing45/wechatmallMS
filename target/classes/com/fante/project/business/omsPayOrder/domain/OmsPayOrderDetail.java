package com.fante.project.business.omsPayOrder.domain;

import com.fante.framework.aspectj.lang.annotation.Excel;
import com.fante.framework.web.domain.BaseEntity;
import com.fante.project.business.omsOrder.domain.OmsOrderDetail;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.List;

/**
 * 支付订单对象 oms_pay_order
 * 
 * @author fante
 * @date 2020-04-18
 */
public class OmsPayOrderDetail extends OmsPayOrder {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "子订单详单集合")
    private List<OmsOrderDetail> orderDetailList;

    public OmsPayOrderDetail(List<OmsOrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }
}
