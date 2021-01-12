package com.fante.project.api.omsOrderProcess.domain;

import com.fante.framework.aspectj.lang.annotation.Excel;
import com.fante.framework.web.domain.BaseEntity;
import com.fante.project.business.umsMemberReceiveAddress.domain.UmsMemberReceiveAddress;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

/**
 * 会员收货地址对象 ums_member_receive_address
 *
 * @author fante
 * @date 2020-04-10
 */
public class UmsMemberReceiveAddressResult implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "运费")
    private BigDecimal freightPrice;

    @ApiModelProperty(value = "错误消息:key错误类型,value:商品id的集合对象")
    private Map<String, String> errMsg;

    @ApiModelProperty(value = "默认地址")
    private UmsMemberReceiveAddress defaultAddress;

    public Map<String, String> getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(Map<String, String> errMsg) {
        this.errMsg = errMsg;
    }

    public UmsMemberReceiveAddressResult(UmsMemberReceiveAddress defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    public UmsMemberReceiveAddressResult() {
    }

    public UmsMemberReceiveAddressResult(BigDecimal freightPrice, UmsMemberReceiveAddress defaultAddress) {
        this.freightPrice = freightPrice;
        this.defaultAddress = defaultAddress;
    }

    public BigDecimal getFreightPrice() {
        return freightPrice;
    }

    public void setFreightPrice(BigDecimal freightPrice) {
        this.freightPrice = freightPrice;
    }

    public UmsMemberReceiveAddress getDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(UmsMemberReceiveAddress defaultAddress) {
        this.defaultAddress = defaultAddress;
    }
}
