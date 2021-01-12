package com.fante.project.business.smsCoupon.domain;

import com.fante.common.utils.bean.BeanUtils;
import com.fante.project.api.appView.domain.SmsMemberCouponDetail;

import java.util.List;

/**
 * @program: Fante
 * @Date: 2020/3/26 18:36
 * @Author: Mr.Z
 * @Description: 优惠券DTO，增加列表按钮显示
 */
public class SmsCouponListDTO extends SmsMemberCouponDetail {

    private static final long serialVersionUID = 1L;

    private List<String> btns;

    public SmsCouponListDTO() {
    }

    public SmsCouponListDTO(SmsMemberCouponDetail coupon, List<String> btns) {
        BeanUtils.copyBeanProp(this, coupon);
        this.btns = btns;
    }

    public List<String> getBtns() {
        return btns;
    }

    public void setBtns(List<String> btns) {
        this.btns = btns;
    }
}
