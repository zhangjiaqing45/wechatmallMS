package com.fante.project.business.pmsProduct.domain;

import com.fante.common.utils.bean.BeanUtils;
import com.fante.framework.aspectj.lang.annotation.Excel;
import com.fante.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.List;

/**
 * 商品信息对象 pms_product
 * 
 * @author fante
 * @date 2020-03-21
 */
public class PmsProductBtn extends PmsProduct {
    private static final long serialVersionUID = 1L;

    private List<String> btnList;

    public PmsProductBtn(PmsProduct product,List<String> btnList) {
        BeanUtils.copyProperties(product,this);
        this.btnList = btnList;
    }

    public PmsProductBtn() {
    }

    public List<String> getBtnList() {
        return btnList;
    }

    public void setBtnList(List<String> btnList) {
        this.btnList = btnList;
    }
}
