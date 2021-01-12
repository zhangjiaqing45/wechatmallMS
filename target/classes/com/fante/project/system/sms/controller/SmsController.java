package com.fante.project.system.sms.controller;

import com.fante.common.exception.BusinessException;
import com.fante.framework.config.SmsConfig;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.project.system.sms.service.SmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: Fante
 * @Date: 2020/1/8 16:05
 * @Author: Mr.Z
 * @Description:
 */
@Controller
@RequestMapping("/sms")
public class SmsController {

    private static Logger log = LoggerFactory.getLogger(SmsController.class);

    @Autowired
    SmsService smsService;
    @Autowired
    private SmsConfig smsConfig;

    @PostMapping("/verifyCode")
    @ResponseBody
    public AjaxResult verifyCode(String phone) {
        try {
            String code = smsService.sendSmsCode(phone);
            if (smsConfig.isEnabled()) {
                // 正式环境，隐藏验证码
                code = "";
            }
            return AjaxResult.success("验证码发送成功").put("code", code);
        } catch (NumberFormatException ne) {
            log.error(ne.getMessage());
            return AjaxResult.error("系统设置异常");
        } catch (BusinessException be) {
            log.error(be.getMessage());
            return AjaxResult.warn(be.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage());
            return AjaxResult.error("验证码发送失败");
        }
    }


}
