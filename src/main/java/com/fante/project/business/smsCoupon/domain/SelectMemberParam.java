package com.fante.project.business.smsCoupon.domain;

import com.fante.common.utils.Checker;
import com.fante.framework.web.domain.BaseEntity;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;

/**
 * @author ftnet
 * @Description SelectMemberParam
 * @CreatTime 2020/6/29
 */
public class SelectMemberParam implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 电话
     */
    private String phone;
    /**
     * 订阅标识
     */
    private String subscribe;
    /**
     * 优惠券id
     */
    private Long couponId;

    /**
     * 存款分组
     */
    private String depositgroup;
    /**
     * 姓名
     */
    private String surname;
    /**
     * 身份证号
     */
    private String idcard;
    /**
     * 验证入参
     */
    public void validateParam(){
        Checker.check(ObjectUtils.isEmpty(getCouponId()),"优惠券参数缺失");
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(String subscribe) {
        this.subscribe = subscribe;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public String getDepositgroup() {
        return depositgroup;
    }

    public void setDepositgroup(String depositgroup) {
        this.depositgroup = depositgroup;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }
}
