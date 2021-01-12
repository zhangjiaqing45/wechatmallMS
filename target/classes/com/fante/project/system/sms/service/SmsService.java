package com.fante.project.system.sms.service;

import com.fante.common.business.constant.BizConstants;
import com.fante.common.utils.Checker;
import com.fante.common.utils.StringUtils;
import com.fante.framework.config.SmsConfig;
import com.fante.project.system.config.service.IConfigService;
import com.fante.project.system.sms.utils.SmsRedis;
import com.fante.project.system.sms.utils.SmsSend;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Random;

/**
 * @program: Fante
 * @Date: 2020/1/8 15:19
 * @Author: Mr.Z
 * @Description: 短信服务
 */
@Component
public class SmsService {

    private static Logger log = LoggerFactory.getLogger(SmsService.class);

    @Autowired
    private SmsConfig smsConfig;
    @Autowired
    SmsRedis smsRedis;
    @Autowired
    SmsSend smsSend;
    @Autowired
    private IConfigService configService;

    private static final Random RANDOM = new Random();

    private static final Integer END = 10;

    private static final Integer CAPTCHA_LENGTH = 6;

    /**
     * 发送短信消息
     *
     * @param phone
     * @param msg
     */
    public void sendSmsMsg(String phone, String msg) {
        log.info("向手机[{}]发送短信信息: {}", phone, msg);
        if (smsConfig.isEnabled()) {
            // 正式环境，执行短信发送
            smsSend.sendMsg(phone, msg);
        }
    }

    /**
     * 校验短信验证码
     *
     * @param phone
     * @param code
     * @return
     */
    public boolean verifySmsCode(String phone, String code) {
        String redisCode = smsRedis.get(phone);
        if (StringUtils.isBlank(redisCode)) {
            log.info("未存有手机号[{}]的验证码", phone);
            return false;
        }
        return Objects.equals(redisCode, code);
    }

    /**
     * 发送验证码
     *
     * @param phone
     */
    public String sendSmsCode(String phone) {
        // 检验是否已向改手机号发送短信
        Checker.check(smsRedis.hasKey(phone), StringUtils.format("已向手机[{}]发送短信验证码", phone));

        Long expire = Long.valueOf(configService.selectConfigByKey(BizConstants.base.SYS_SMS_CODE_EXPIRE));

        String code = createVerifyCode();
        log.info("生成短信验证码: {}", code);
        if (smsConfig.isEnabled()) {
            // 正式环境，执行短信发送
            String msg = StringUtils.format(BizConstants.shortMsg.VERIFY_CODE_MSG, smsConfig.getCoSign(), code, expire);
            smsSend.sendMsg(phone, msg);
        }
        smsRedis.set(phone, code, expire);
        return code;
    }


    /**
     * 生成短信验证码
     *
     * @return
     */
    public String createVerifyCode() {
        Double pross = RANDOM.nextDouble() * Math.pow(END, CAPTCHA_LENGTH);
        return String.format("%06.0f", pross);
    }
}
