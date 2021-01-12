package com.fante.project.business.smsGroupGame.domain;

import com.fante.framework.aspectj.lang.annotation.Excel;
import com.fante.framework.web.domain.BaseEntity;
import com.fante.project.business.pmsSkuStock.domain.PmsSkuStock;
import com.fante.project.business.smsGroupGameSku.domain.SmsGroupGameSku;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 团购商品对象 sms_group_game
 * 
 * @author fante
 * @date 2020-03-30
 */
public class SmsGroupGameParam extends SmsGroupGame {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "团购商品sku对象集合")
    private List<SmsGroupGameSku> skus;

    public List<SmsGroupGameSku> getSkus() {
        return skus;
    }

    public void setSkus(List<SmsGroupGameSku> skus) {
        this.skus = skus;
    }
}