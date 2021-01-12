package com.fante.project.business.pmsIntegralProduct.domain;

import com.fante.common.utils.bean.BeanUtils;

import java.util.List;

/**
 * @program: Fante
 * @Date: 2020/3/27 18:05
 * @Author: Mr.Z
 * @Description: 积分商品DTO，增加列表按钮显示
 */
public class PmsIntegralProductListDTO extends PmsIntegralProduct {

    private static final long serialVersionUID = 1L;

    private List<String> btns;


    public PmsIntegralProductListDTO() {
    }

    public PmsIntegralProductListDTO(PmsIntegralProduct product, List<String> btns) {
        BeanUtils.copyBeanProp(this, product);
        this.btns = btns;
    }

    public List<String> getBtns() {
        return btns;
    }

    public void setBtns(List<String> btns) {
        this.btns = btns;
    }
}
