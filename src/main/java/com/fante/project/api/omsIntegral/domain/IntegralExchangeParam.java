package com.fante.project.api.omsIntegral.domain;

import com.fante.common.utils.Checker;
import com.fante.project.business.umsMember.domain.UmsMember;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.util.ObjectUtils;

/**
 * @author wz
 * @Description IntergralExchangeParam
 * @CreatTime 2020/4/22
 */
public class IntegralExchangeParam {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "积分商品id")
    private Long id;

    @ApiModelProperty(value = "用户")
    private UmsMember member;

    @ApiModelProperty(value = "积分商品兑换数量")
    private Long quantity;

    @ApiModelProperty(value = "选择地址Id")
    private Long addressId;

    @ApiModelProperty(value = "用户留言")
    private String note;

    public void validate() {
        Checker.check(ObjectUtils.isEmpty(getId()), "缺少积分商品标识");
        Checker.check(ObjectUtils.isEmpty(getQuantity()), "缺少兑换数量");
        Checker.checkOp(getQuantity() > 0, "至少选择一件商品");
    }

    public UmsMember getMember() {
        return member;
    }

    public void setMember(UmsMember member) {
        this.member = member;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
