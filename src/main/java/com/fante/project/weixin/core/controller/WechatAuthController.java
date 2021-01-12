package com.fante.project.weixin.core.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.fante.common.utils.StringUtils;
import com.fante.common.utils.bean.BeanUtils;
import com.fante.common.utils.spring.SpringUtils;
import com.fante.framework.config.WechatConfig;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.project.api.appView.domain.MWxFans;
import com.fante.project.business.umsMember.domain.UmsMember;
import com.fante.project.business.wxSettings.service.IWxSettingsService;
import com.fante.project.weixin.core.config.WeixinBaseConsts;
import com.fante.project.weixin.core.domain.RedirectReq;
import com.fante.project.weixin.core.service.impl.WeixinSubscribeImpl;
import com.fante.project.weixin.core.utils.WechatRedis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.weixin4j.WeixinException;
import org.weixin4j.model.sns.SnsAccessToken;
import org.weixin4j.spring.WeixinTemplate;
import com.fante.project.api.appView.domain.MemberDataRsp;
import org.springframework.web.client.RestTemplate;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: Fante
 * @Date:
 * @Author: Mr.Z
 * @Description: 微信授权服务
 */
@Controller
@RequestMapping("/wechat/auth")
public class WechatAuthController {

    private static Logger log = LoggerFactory.getLogger(WechatAuthController.class);

    @Autowired
    WechatRedis wechatRedis;
    @Autowired
    WechatConfig wechatConfig;
    @Autowired
    IWxSettingsService wxSettingsService;
    @Autowired
    WeixinSubscribeImpl weixinSubscribe;
    @Autowired
    RestTemplate restTemplate;

    private final String url = "http://www.henangaiyin.com/sunshinecredit/RealNameCertification/getFansByOpenid";

    /**
     * 微信授权URL
     * @param req 重定向参数
     * @return
     */
    @GetMapping("/oAuth2CodeUrl")
    @ResponseBody
    public AjaxResult oAuth2CodeUrl(RedirectReq req) {
        log.info("重定向参数: {}", BeanUtils.beanToString(req));

        WeixinTemplate weixinTemplate = SpringUtils.getBean(WeixinTemplate.class);

        String oauthUrl = weixinTemplate.getWeixinConfig().getOauthUrl();
        String accessTokenUrl = wechatConfig.getAccessTokenUrl();
        log.info("网页授权URL: {}, 网页授权链接: {}", oauthUrl, accessTokenUrl);

        String redirectUrl = oauthUrl + accessTokenUrl;
        log.info("构建重定向URL: {}", redirectUrl);

        // 重定向参数处理：保存内容至Redis缓存中并返回KEY，利用state参数，传递KEY
        String state = req.effective() ? wechatRedis.setRedirectParams(req) : StringUtils.EMPTY;

        String oAuth2CodeUrl = weixinTemplate.sns().getOAuth2CodeUrl(redirectUrl, WeixinBaseConsts.Authorize.SCOPE_SNSAPI_BASE, state);
        log.info("微信授权URL: {}", oAuth2CodeUrl);
        return AjaxResult.success().put("oAuth2CodeUrl", oAuth2CodeUrl);
    }


    /**
     * 微信网页授权
     * @param code
     * @return
     * @throws WeixinException
     */
    @GetMapping("/accessToken")
    @ResponseBody
    public AjaxResult accessToken(String code, String state)  {
        WeixinTemplate weixinTemplate = SpringUtils.getBean(WeixinTemplate.class);

        log.info("微信网页授权:: code: {}, state: {}", code,  state);
        try{
        RedirectReq req = StringUtils.isBlank(state) ? null : wechatRedis.getRedirectParams(state);
        log.info("获取重定向参数: {}", BeanUtils.beanToString(req));

        SnsAccessToken snsAccessToken = weixinTemplate.sns().getSnsOAuth2AccessToken(code);

        String accessToken = snsAccessToken.getAccess_token();
        String openid = snsAccessToken.getOpenid();
        log.info("获取授权信息:: access_token: {}, openid: {}", accessToken, openid);

        System.out.println("wechatConfig.getWxServConfig()="+wechatConfig.getWxServConfig());
        if (!wechatConfig.getWxServConfig()) {
            System.out.println("1111111");
            UmsMember umsMember=wechatRedis.getUmsMember(openid);
            if (umsMember != null) {//正式放开
                 //从市行公众号接口中获取粉丝信息
                String url_1 = url +"?openid="+openid;
                MemberDataRsp rsp = restTemplate.getForObject(url_1, MemberDataRsp.class);
                System.out.println("rsp="+rsp.toString());
                System.out.println("rsp.getState()="+rsp.getState());
                System.out.println("rsp.getMsg()="+rsp.getMsg());
                System.out.println("rsp.getData()="+rsp.getData());
                System.out.println("rsp.getData1()="+rsp.getData1());
                //判断是否关注公众号
                if(rsp.getData()!=null){
                    MWxFans mWxFans=JSON.parseObject(JSON.toJSONString(rsp.getData()), new TypeReference<MWxFans>(){});

                    // JSONObject jsonobject= JSONObject.parseObject(rsp.getData().toString());
                    //如果关注后无会员信息则给实体赋值
                    umsMember.setNickname(mWxFans.getNickname());
                    umsMember.setHeadimgurl(mWxFans.getHeadimgurl());
                    umsMember.setSex(mWxFans.getSex());
                    umsMember.setCountry(mWxFans.getCountry());
                    umsMember.setProvince(mWxFans.getProvince());
                    umsMember.setCity(mWxFans.getCity());
                    umsMember.setSubscribe(mWxFans.getBlack().equals("2") ? "0" : "1");
                    umsMember.setOpenid(mWxFans.getOpenid());
                    umsMember.setUnionid(mWxFans.getUnionid());
                    umsMember.setRemark(mWxFans.getRemark());

                    wechatRedis.setUmsMember(openid,umsMember);
                }

            }
            // 未开启服务器配置，维护用户信息
            weixinSubscribe.subscribeProcess(openid, req);
        }
        return AjaxResult.success().put("openid", openid);
        }catch (Exception e) {
            log.info(e.toString());
            return AjaxResult.errorlt();
        }
    }



    /**
     *测试接口使用，暂无使用
     * @param openid 参数
     * @return
     */
    @GetMapping("/getFansByOpenid")
    @ResponseBody
    public AjaxResult getFansByOpenid(String openid) {

        String url_1 = url +"?openid="+openid;
        try{
        MemberDataRsp rsp = restTemplate.getForObject(url_1, MemberDataRsp.class);
        return AjaxResult.success().put("rows", rsp.getData()).put("wxSettings", wxSettingsService.selectWxSettingsRecent());
        }catch (Exception e) {
            log.info(e.toString());
            return AjaxResult.errorlt();
        }
    }


    ///**
    // * 微信网页授权 <br/>
    // * 流程分为四步： <br/>
    // * 1、引导用户进入授权页面同意授权，获取code <br/>
    // * 2、通过code换取网页授权access_token（与基础支持中的access_token不同） <br/>
    // * 3、如果需要，开发者可以刷新网页授权access_token，避免过期 <br/>
    // * 4、通过网页授权access_token和openid获取用户基本信息（支持UnionID机制） <br/>
    // * @param code 用户同意授权后,获取到的code
    // * @param state 重定向状态参数
    // * @param response
    // * @param request
    // * @throws Exception
    // */
    //@GetMapping(value = "/accessToken")
    //public void accessToken(
    //        @RequestParam(name = "code", required = false) String code,
    //        @RequestParam(name = "state") String state,
    //        HttpServletResponse response, HttpServletRequest request) throws WeixinException, IOException {
    //
    //    log.info("微信网页授权:: code: {}, state: {}", code, state);
    //
    //    WeixinTemplate weixinTemplate = SpringUtils.getBean(WeixinTemplate.class);
    //
    //    SnsAccessToken snsAccessToken = weixinTemplate.sns().getSnsOAuth2AccessToken(code);
    //
    //    log.info("获取授权信息:: access_token: {}, openid: {}", snsAccessToken.getAccess_token(), snsAccessToken.getOpenid());
    //
    //    // 重定向目标路径（默认为首页），如微信授权URL中参数state存在，则在此设定为目标路径；
    //    String redirectUrl = StringUtils.isBlank(state) ? weixinTemplate.getWeixinConfig().getOauthUrl() + wechatConfig.getIndexPage() : state;
    //
    //    // 生成重定向路径
    //    if (redirectUrl.contains("?")) {
    //        // 如目标路径中携带参数
    //        redirectUrl += "&openid=" + snsAccessToken.getOpenid();
    //    } else {
    //        redirectUrl += "?openid=" + snsAccessToken.getOpenid();
    //    }
    //
    //    log.info("重定向目标路径: {}", redirectUrl);
    //    // 重定向页面
    //    response.sendRedirect(redirectUrl);
    //}
}
