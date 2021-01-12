package com.fante.project.api.omsOrderProcess.domain;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ftnet
 * @Description ConfirmOrderParam
 * @CreatTime 2020/4/14
 */
public class AddressParam implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "用户id")
    private Long memberId;
    @ApiModelProperty(value = "收货地址id")
    private Long memberReceiveAddressId;
    @ApiModelProperty(value = "购物车ids")
    private String cartIds;

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getMemberReceiveAddressId() {
        return memberReceiveAddressId;
    }

    public void setMemberReceiveAddressId(Long memberReceiveAddressId) {
        this.memberReceiveAddressId = memberReceiveAddressId;
    }

    public String getCartIds() {
        return cartIds;
    }

    public void setCartIds(String cartIds) {
        this.cartIds = cartIds;
    }
}
