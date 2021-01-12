package com.fante.project.business.omsOrder.domain;

import com.fante.common.utils.bean.BeanUtils;
import com.fante.framework.aspectj.lang.annotation.Excel;
import com.fante.framework.web.domain.BaseEntity;
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
public class OmsOrderBtnResult extends OmsOrder {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "可执行的操作")
    private List<String> btns;

    public OmsOrderBtnResult(OmsOrder order,List<String> btns) {
        BeanUtils.copyProperties(order,this);
        this.btns = btns;
    }

    public OmsOrderBtnResult() {
    }


    public List<String> getBtns() {
        return btns;
    }

    public void setBtns(List<String> btns) {
        this.btns = btns;
    }
}
