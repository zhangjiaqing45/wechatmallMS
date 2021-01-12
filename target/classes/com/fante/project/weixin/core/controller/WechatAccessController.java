package com.fante.project.weixin.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.weixin4j.spring.web.WeixinJieruController;

/**
 * @program: Fante
 * @Date:
 * @Author: Mr.Z
 * @Description: 微信开发者接入 直接继承即可
 */
@Controller
@RequestMapping("/wechat/access")
public class WechatAccessController extends WeixinJieruController {

}
