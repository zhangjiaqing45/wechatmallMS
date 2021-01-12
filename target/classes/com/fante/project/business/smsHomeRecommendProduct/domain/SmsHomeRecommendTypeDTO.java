package com.fante.project.business.smsHomeRecommendProduct.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @program: Fante
 * @Date: 2020/4/20 10:56
 * @Author: Mr.Z
 * @Description: 按照推荐类型分类组装数据
 */
public class SmsHomeRecommendTypeDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String type;

    private List<SmsHomeRecommendProductDTO> products;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<SmsHomeRecommendProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<SmsHomeRecommendProductDTO> products) {
        this.products = products;
    }
}
