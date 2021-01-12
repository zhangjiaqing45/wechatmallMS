package com.fante.project.api.umsProcess.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.fante.common.business.enums.UmsMemberConst;
import com.fante.common.utils.Checker;
import com.fante.common.utils.StringUtils;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.framework.web.service.ConfigService;
import com.fante.project.api.appView.domain.MWxFans;
import com.fante.project.api.umsProcess.service.UmsMemberProcessService;
import com.fante.project.business.omsOrderWriteOff.domain.OmsOrderWriteOff;
import com.fante.project.business.omsOrderWriteOff.service.IOmsOrderWriteOffService;
import com.fante.project.business.umsMember.domain.UmsMember;
import com.fante.project.business.wxSettings.domain.WxSettings;
import com.fante.project.business.wxSettings.service.IWxSettingsService;
import com.fante.project.system.dict.service.IDictDataService;
import com.fante.project.weixin.core.utils.WechatRedis;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fante.project.api.appView.domain.MemberDataRsp;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

/**
 * @program: Fante
 * @Date: 2020/4/15 16:45
 * @Author: Mr.Z
 * @Description: 公众号用户接口
 */
@Api(tags = "WeixinUserController", description = "公众号用户接口")
@Controller
@RequestMapping("/api/user")
public class WeixinUserController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(WeixinUserController.class);

    private final String url = "http://www.henangaiyin.com/sunshinecredit/RealNameCertification/getFansByOpenid";

    @Autowired
    UmsMemberProcessService umsMemberProcessService;
    @Autowired
    IWxSettingsService wxSettingsService;
    @Autowired
    private IDictDataService dictDataService;
    @Autowired
    private ConfigService configService;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    WechatRedis wechatRedis;
    @Autowired
    IOmsOrderWriteOffService writeOffService;

    @ApiOperation("公众号会员登录")
    @PostMapping("/login")
    @ResponseBody
    public AjaxResult login(String openid) {
        log.info("login:: OPENID: {}", openid);
        Checker.check(StringUtils.isBlank(openid), "缺少微信标识");
        //如果关注后无会员信息则初始化并添加会员信息
        UmsMember member =new UmsMember();
        // 会员信息
         member = umsMemberProcessService.get(openid);
        // TOKEN
        String token = "";
        // 公众号设置
        WxSettings wxSettings = null;
        // 关注标识
        //boolean subscribe = false;//正式放开
        boolean subscribe = true;//做测试用

        // 资源路径
        String resPath = "";
        try{
        //正式放开
        //从市行公众号接口中获取粉丝信息
        String url_1 = url +"?openid="+openid;
        MemberDataRsp rsp = restTemplate.getForObject(url_1, MemberDataRsp.class);
        log.info(rsp.getData().toString());

        //判断是否关注公众号
        if(rsp.getData()!=null){

            MWxFans mWxFans=JSON.parseObject(JSON.toJSONString(rsp.getData()), new TypeReference<MWxFans>(){});
            // JSONObject jsonobject= JSONObject.parseObject(rsp.getData().toString());
            if(mWxFans.getBlack().equals("2")){
                subscribe=false;
                member.setSubscribe("0");
                // 更新缓存
                wechatRedis.setUmsMember(openid, member);
            }else{
                subscribe=true;
                //关注时将市行公众号上的粉丝信息更新到商铺的会员信息中
                if (!ObjectUtils.isEmpty(member)) {
                    member.setNickname(mWxFans.getNickname());
                    member.setHeadimgurl(mWxFans.getHeadimgurl());
                    member.setCountry(mWxFans.getCountry());
                    member.setProvince(mWxFans.getProvince());
                    member.setCity(mWxFans.getCity());
                    member.setSubscribe(mWxFans.getBlack().equals("2") ? "0" : "1");
                    umsMemberProcessService.update(member);
                }else{
                    member =new UmsMember();
                    //如果关注后无会员信息则给实体赋值
                    member.setNickname(mWxFans.getNickname());
                    member.setHeadimgurl(mWxFans.getHeadimgurl());
                    member.setSex(mWxFans.getSex());
                    member.setCountry(mWxFans.getCountry());
                    member.setProvince(mWxFans.getProvince());
                    member.setCity(mWxFans.getCity());
                    member.setSubscribe(mWxFans.getBlack().equals("2") ? "0" : "1");
                    member.setOpenid(mWxFans.getOpenid());
                    member.setUnionid(mWxFans.getUnionid());
                    member.setRemark(mWxFans.getRemark());
                    umsMemberProcessService.insert(member);
                }
            }
        }else{
            subscribe=false;

        }

        // 更新缓存
        wechatRedis.setUmsMember(openid, member);

        if (!ObjectUtils.isEmpty(member)) {
            token = umsMemberProcessService.token(member);
            //subscribe = Objects.equals(member.getSubscribe(), UmsMemberConst.SubscribeEnum.YES.getType());
            wxSettings = wxSettingsService.selectWxSettingsRecent();
            resPath = configService.appResPath();
        }
        System.out.println("token="+token);
        return AjaxResult.success()
                .put("token", token)
                .put("member", member)
                .put("subscribe", subscribe)
                .put("wxSettings", wxSettings)
                .put("resPath", resPath);

    }catch (Exception e) {
        log.info(e.toString());
        return AjaxResult.errorlt();
    }
    }

    @ApiOperation("获取会员信息")
    @GetMapping("/get")
    @ResponseBody
    public AjaxResult get() {
        String openid = getTokenClientId();
        log.info("get:: OPENID: {}", openid);
        //如果关注后无会员信息则初始化并添加会员信息
        UmsMember member =new UmsMember();
        // 会员信息
         member = umsMemberProcessService.get(openid);
        // 公众号设置
        WxSettings wxSettings = null;
        // 关注标识
        boolean subscribe = false;

        // 资源路径
        String resPath = "";
        //测试开始
        wxSettings = wxSettingsService.selectWxSettingsRecent();
        resPath = configService.appResPath();
        return AjaxResult.success()
                .put("member", member)
                .put("subscribe", true)
                .put("wxSettings", wxSettings)
                .put("resPath", resPath);
        //测试结束


        /*try{
        //从市行公众号接口中获取粉丝信息
        String url_1 = url +"?openid="+openid;
        MemberDataRsp rsp = restTemplate.getForObject(url_1, MemberDataRsp.class);
        log.info(rsp.getData().toString());

        //判断是否关注公众号
        if(rsp.getData()!=null){

            MWxFans mWxFans=JSON.parseObject(JSON.toJSONString(rsp.getData()), new TypeReference<MWxFans>(){});
            // JSONObject jsonobject= JSONObject.parseObject(rsp.getData().toString());
            if(mWxFans.getBlack().equals("2")){
                subscribe=false;
                member.setSubscribe("0");
            }else{
                subscribe=true;
                //关注时将市行公众号上的粉丝信息更新到商铺的会员信息中
                if (!ObjectUtils.isEmpty(member)) {
                    member.setNickname(mWxFans.getNickname());
                    member.setHeadimgurl(mWxFans.getHeadimgurl());
                    member.setCountry(mWxFans.getCountry());
                    member.setProvince(mWxFans.getProvince());
                    member.setCity(mWxFans.getCity());
                    member.setSubscribe(mWxFans.getBlack().equals("2") ? "0" : "1");
                    umsMemberProcessService.update(member);
                }else{
                    //如果关注后无会员信息则给实体赋值
                    member.setNickname(mWxFans.getNickname());
                    member.setHeadimgurl(mWxFans.getHeadimgurl());
                    member.setSex(mWxFans.getSex());
                    member.setCountry(mWxFans.getCountry());
                    member.setProvince(mWxFans.getProvince());
                    member.setCity(mWxFans.getCity());
                    member.setSubscribe(mWxFans.getBlack().equals("2") ? "0" : "1");
                    member.setOpenid(mWxFans.getOpenid());
                    member.setUnionid(mWxFans.getUnionid());
                    member.setRemark(mWxFans.getRemark());
                    umsMemberProcessService.insert(member);
                }
            }
        }else{
            subscribe=false;
        }
        // 更新缓存
        wechatRedis.setUmsMember(openid, member);

        if (!ObjectUtils.isEmpty(member)) {
            //subscribe = Objects.equals(member.getSubscribe(), UmsMemberConst.SubscribeEnum.YES.getType());
            wxSettings = wxSettingsService.selectWxSettingsRecent();
            resPath = configService.appResPath();
        }
        return AjaxResult.success()
                .put("member", member)
                .put("subscribe", subscribe)
                .put("wxSettings", wxSettings)
                .put("resPath", resPath);
        }catch (Exception e) {
            log.info(e.toString());
            return AjaxResult.errorlt();
        }*/
    }

    @ApiOperation("获取账户类型")
    @GetMapping("/getAccountType")
    @ResponseBody
    public AjaxResult getAccountType() {
        return AjaxResult.success().put("accountType", dictDataService.selectDictDataByTypeToMap("biz_account_type"));
    }

    @ApiOperation("分销人员注册")
    @GetMapping("/partnerRegister")
    @ResponseBody
    public AjaxResult partnerRegister(String shopCode, String type, String phone, String verifyCode, String key) {
        umsMemberProcessService.partnerRegister(getTokenUserId(), shopCode, type, phone, verifyCode, key);
        return AjaxResult.success();
    }

    @ApiOperation("分销人员信息维护")
    @GetMapping("/partnerMaintain")
    @ResponseBody
    public AjaxResult partnerMaintain(String phone, String verifyCode, String accountType, String account) {
        umsMemberProcessService.partnerMaintain(getTokenUserId(), phone, verifyCode, accountType, account);
        return AjaxResult.success();
    }

    @ApiOperation("分销人员获取核销记录")
    @GetMapping("/partnerWriteOffS")
    @ResponseBody
    public AjaxResult partnerWriteOffS() {
        String openid = getTokenClientId();
        OmsOrderWriteOff writeOff=new OmsOrderWriteOff();
        writeOff.setHxOpenid(openid);
        startPage();
        List<OmsOrderWriteOff> list= writeOffService.selectOmsOrderWriteOffList(writeOff);
        System.out.println("list.size()="+list.size());
        return AjaxResult.success(list);
    }

    @ApiOperation("资源路径")
    @GetMapping("/resPath")
    @ResponseBody
    public AjaxResult resPath() {
        return AjaxResult.success().put("resPath", configService.appResPath());
    }

    @ApiOperation("获取token2222")
    @GetMapping("/getToken2")
    @ResponseBody
    public AjaxResult getToken2() {
        // 会员信息
        UmsMember member = umsMemberProcessService.get("ottGh1byVhMYeIdv2uCV63tYvocc");

        //UmsMember member = umsMemberProcessService.get("oM0wO0oRryCfuQlp89QG0K2Ic-2I");
        return AjaxResult.success().put("token", umsMemberProcessService.token(member));

    }

}


