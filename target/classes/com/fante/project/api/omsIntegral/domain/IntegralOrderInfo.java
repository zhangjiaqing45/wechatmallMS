package com.fante.project.api.omsIntegral.domain;

import com.fante.project.business.pmsIntegralProduct.domain.PmsIntegralProduct;
import com.fante.project.business.umsMemberReceiveAddress.domain.UmsMemberReceiveAddress;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author wz
 * @Description IntergralOrderInfo
 * @CreatTime 2020/4/22
 */
public class IntegralOrderInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "邮寄地址")
    private UmsMemberReceiveAddress address;

    @ApiModelProperty(value = "积分商品")
    private PmsIntegralProduct product;

    @ApiModelProperty(value = "积分总价")
    private Long totalPrice;

    @ApiModelProperty(value = "积分商品兑换数量")
    private Long quantity;

    @ApiModelProperty(value = "用户留言")
    private String note;

    private boolean free;

    public IntegralOrderInfo(PmsIntegralProduct pmsIntegralProduct,Long quantity) {
        this.product = pmsIntegralProduct;
        this.quantity = quantity;
    }

    public PmsIntegralProduct getProduct() {
        return product;
    }

    public void setProduct(PmsIntegralProduct product) {
        this.product = product;
    }

    public IntegralOrderInfo() {
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public UmsMemberReceiveAddress getAddress() {
        return address;
    }

    public void setAddress(UmsMemberReceiveAddress address) {
        this.address = address;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public boolean getFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }
}
