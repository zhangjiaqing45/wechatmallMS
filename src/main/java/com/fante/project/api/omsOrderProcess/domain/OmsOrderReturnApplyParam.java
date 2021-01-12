package com.fante.project.api.omsOrderProcess.domain;

import com.fante.common.utils.Checker;
import com.fante.common.utils.StringUtils;
import com.fante.project.business.umsMember.domain.UmsMember;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;

/**
 * @author ftnet
 * @Description OmsOrderReturnApplyParam
 * @CreatTime 2020/5/4
 */
public class OmsOrderReturnApplyParam implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户")
    private UmsMember member;
    /***第一次提交参数***/
    @ApiModelProperty(value = "退货人姓名")
    private String returnName;

    @ApiModelProperty(value = "退货人电话")
    private String returnPhone;

    @ApiModelProperty(value = "订单详情id")
    private Long orderItemId;

    @ApiModelProperty(value = "退货数量")
    private Integer productCount;

    @ApiModelProperty(value = "原因")
    private String reason;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "凭证图片，以逗号隔开")
    private String proofPics;
    /***第二次更新参数***/

    @ApiModelProperty(value = "退货服务单号")
    private Long returnApplyId;

    @ApiModelProperty(value = "邮寄公司")
    private String deliveryCompany;

    @ApiModelProperty(value = "邮寄快递号")
    private String deliverySn;


    /**
     * 验证申请时传入到必要参数
     */
    public void validateApplayParam(){
        Checker.check(StringUtils.isEmpty(getReturnName()),"退货人参数缺失");
        Checker.check(StringUtils.isEmpty(getReturnPhone()),"退货电话参数缺失");
        Checker.check(ObjectUtils.isEmpty(getOrderItemId()),"订单详情参数缺失");
        Checker.check(StringUtils.isEmpty(getReason()),"退货原因参数缺失");
        Checker.check(ObjectUtils.isEmpty(getProductCount()) || getProductCount()<1,"商品数量参数缺失");
    }

    /**
     * 验证更新时传入到必要参数
     */
    public void validateUpdateParam(){
        Checker.check(ObjectUtils.isEmpty(getReturnApplyId()),"服务单参数缺失");
        Checker.check(StringUtils.isEmpty(getDeliverySn()),"退货单号参数缺失");
        Checker.check(StringUtils.isEmpty(getDeliveryCompany()),"退货公司参数缺失");

    }

    public String getReturnName() {
        return returnName;
    }

    public void setReturnName(String returnName) {
        this.returnName = returnName;
    }

    public String getReturnPhone() {
        return returnPhone;
    }

    public void setReturnPhone(String returnPhone) {
        this.returnPhone = returnPhone;
    }

    public Long getReturnApplyId() {
        return returnApplyId;
    }

    public void setReturnApplyId(Long returnApplyId) {
        this.returnApplyId = returnApplyId;
    }

    public String getDeliveryCompany() {
        return deliveryCompany;
    }

    public void setDeliveryCompany(String deliveryCompany) {
        this.deliveryCompany = deliveryCompany;
    }

    public String getDeliverySn() {
        return deliverySn;
    }

    public void setDeliverySn(String deliverySn) {
        this.deliverySn = deliverySn;
    }

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public UmsMember getMember() {
        return member;
    }

    public void setMember(UmsMember member) {
        this.member = member;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProofPics() {
        return proofPics;
    }

    public void setProofPics(String proofPics) {
        this.proofPics = proofPics;
    }
}
