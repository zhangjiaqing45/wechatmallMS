package com.fante.project.weixin.core.controller;

import com.fante.common.utils.spring.SpringUtils;
import com.fante.framework.web.domain.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.weixin4j.WeixinException;
import org.weixin4j.model.js.WxConfig;
import org.weixin4j.spring.WeixinTemplate;

/**
 * @program: Fante
 * @Date:
 * @Author: Mr.Z
 * @Description: 微信JS-SDK接口注入权限验证配置
 */
@Controller
@RequestMapping("/wechat/jssdk")
public class WechatJsSdkController {

    private static Logger log = LoggerFactory.getLogger(WechatJsSdkController.class);

    @GetMapping("/config")
    @ResponseBody
    public AjaxResult wxConfig(String url) throws WeixinException {
        WeixinTemplate weixinTemplate = SpringUtils.getBean(WeixinTemplate.class);
        log.info("使用JS-SDK的页面: {}", url);
        WxConfig config = weixinTemplate.js().getWxConfig(url);
        log.info("权限验证配置: {}", config.toString());
        return AjaxResult.success().put("wxConfig", config);
    }

}
