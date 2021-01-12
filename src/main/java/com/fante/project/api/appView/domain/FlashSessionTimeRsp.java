package com.fante.project.api.appView.domain;

import com.fante.common.utils.bean.BeanUtils;
import com.fante.project.business.smsFlashPromotionSession.domain.SmsFlashPromotionSession;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by ftnet on 2020/5/1
 */
public class FlashSessionTimeRsp implements Serializable  {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "购买标识")
    private String buyPermissions;

    @ApiModelProperty(value = "时间段对象")
    private SmsFlashPromotionSession sessionTime;

    public FlashSessionTimeRsp(SmsFlashPromotionSession sessionTime, String type) {

        this.sessionTime = sessionTime;
        this.buyPermissions = type;
    }

    public FlashSessionTimeRsp() {
        super();
    }

    public SmsFlashPromotionSession getSessionTime() {
        return sessionTime;
    }

    public void setSessionTime(SmsFlashPromotionSession sessionTime) {
        this.sessionTime = sessionTime;
    }

    public String getBuyPermissions() {
        return buyPermissions;
    }

    public void setBuyPermissions(String buyPermissions) {
        this.buyPermissions = buyPermissions;
    }
}
