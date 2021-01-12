package com.fante.project.business.smsSetting.utils;

import com.fante.common.business.enums.CommonUse;
import com.fante.common.utils.StringUtils;
import com.fante.project.business.smsSetting.domain.SmsSetting;
import com.fante.project.business.smsSetting.service.ISmsSettingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Objects;

/**
 * @program: Fante
 * @Date: 2020/4/8 13:45
 * @Author: Mr.Z
 * @Description: 营销设置工具
 */
@Component
public class SmsSettingUtils {

    private static Logger log = LoggerFactory.getLogger(SmsSettingUtils.class);

    private static final SmsSetting EMPTY = new SmsSetting();

    @Autowired
    ISmsSettingService smsSettingService;

    /**
     * 优惠券是否启用
     * @return
     */
    public boolean couponEnable() {
        return checkEnable(getSetting().getCouponStatus());
    }

    /**
     * 团购活动是否启用
     * @return
     */
    public boolean groupEnable() {
        return checkEnable(getSetting().getGroupStatus());
    }

    /**
     * 签到活动是否启用
     * @return
     */
    public boolean signEnable() {
        return checkEnable(getSetting().getSignStatus());
    }

    /**
     * 限时秒杀是否启用
     * @return
     */
    public boolean flashEnable() {
        return checkEnable(getSetting().getFlashStatus());
    }

    /**
     * 积分商城是否启用
     * @return
     */
    public boolean integralEnable() {
        return checkEnable(getSetting().getIntegralStatus());
    }

    /**
     * 获取营销设置信息
     * @return
     */
    private SmsSetting getSetting() {
        SmsSetting smsSetting = smsSettingService.selectSmsSettingRecent();
        return ObjectUtils.isEmpty(smsSetting) ? EMPTY : smsSetting;
    }

    /**
     * 校验状态
     * @param status
     * @return
     */
    private boolean checkEnable(String status) {
        if (StringUtils.isBlank(status)) {
            return false;
        } else {
            return Objects.equals(CommonUse.Status.ENABLE, status);
        }
    }

}
