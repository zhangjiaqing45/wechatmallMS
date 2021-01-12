package com.fante.project.business.smsGroupGame.domain;

import com.fante.common.utils.bean.BeanUtils;
import com.fante.framework.aspectj.lang.annotation.Excel;
import com.fante.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;

/**
 * 团购商品对象 sms_group_game
 * 
 * @author fante
 * @date 2020-03-30
 */
public class SmsGroupGameBtnsResult extends SmsGroupGame {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "可操作按钮组")
    private List<String> btns;

    @ApiModelProperty(value = "商品上架状态")
    private String productPublishStatus;

    @ApiModelProperty(value = "商品删除状态")
    private String productDelFlag;

    public String getProductPublishStatus() {
        return productPublishStatus;
    }

    public void setProductPublishStatus(String productPublishStatus) {
        this.productPublishStatus = productPublishStatus;
    }

    public String getProductDelFlag() {
        return productDelFlag;
    }

    public void setProductDelFlag(String productDelFlag) {
        this.productDelFlag = productDelFlag;
    }

    public SmsGroupGameBtnsResult(SmsGroupGame smsGroupGame, List<String> btns) {
        BeanUtils.copyProperties(smsGroupGame,this);
        this.btns = btns;
    }

    public SmsGroupGameBtnsResult() {
    }

    public List<String> getBtns() {
        return btns;
    }

    public void setBtns(List<String> btns) {
        this.btns = btns;
    }
}
