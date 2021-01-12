package com.fante.project.weixin.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.fante.common.business.enums.UmsMemberConst;
import com.fante.common.utils.bean.BeanUtils;
import com.fante.common.utils.spring.SpringUtils;
import com.fante.project.api.appView.domain.MWxFans;
import com.fante.project.api.appView.domain.MemberDataRsp;
import com.fante.project.api.umsProcess.service.UmsMemberProcessService;
import com.fante.project.business.bizShopInfo.utils.ShopRedis;
import com.fante.project.business.smsCoupon.service.ISmsCouponService;
import com.fante.project.business.umsMember.domain.UmsMember;
import com.fante.project.weixin.core.domain.RedirectReq;
import com.fante.project.weixin.core.service.AbstractWeixinSubscribe;
import com.fante.project.weixin.core.utils.WechatRedis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;
import org.weixin4j.WeixinException;
import org.weixin4j.component.UserComponent;
import org.weixin4j.model.user.User;
import org.weixin4j.spring.WeixinTemplate;

/**
 * @program: Fante
 * @Date: 2020/4/16 14:12
 * @Author: Mr.Z
 * @Description: 微信公众号关注/取消关注具体实现
 */
@Component
public class WeixinSubscribeImpl extends AbstractWeixinSubscribe<User, UmsMember, RedirectReq> {

    private static Logger log = LoggerFactory.getLogger(WeixinSubscribeImpl.class);

    private final String url = "http://www.henangaiyin.com/sunshinecredit/RealNameCertification/getFansByOpenid";
    /**
     * 微信公众号Redis工具类
     */
    @Autowired
    WechatRedis wechatRedis;

    /**
     * 会员相关处理服务
     */
    @Autowired
    UmsMemberProcessService memberService;
    /**
     * 优惠券Service接口
     */
    @Autowired
    ISmsCouponService iSmsCouponService;

    @Autowired
    RestTemplate restTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    protected User getWxUser(String openid) throws WeixinException {
        WeixinTemplate weixinTemplate = SpringUtils.getBean(WeixinTemplate.class);
        UserComponent userComponent = weixinTemplate.user();
        User wxUser = userComponent.info(openid);
        log.info("微信用户信息: {}", BeanUtils.beanToString(wxUser));
        return wxUser;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    protected UmsMember getMember(User wxUser) {
        UmsMember member = memberService.selectByOpenid(wxUser.getOpenid());
        log.info("会员用户信息: {}", ObjectUtils.isEmpty(member) ? "未记录数据库" : member.toString());
        return member;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    protected UmsMember getMemberByOpenid(String openid) {
        UmsMember member = memberService.selectByOpenid(openid);
        log.info("会员用户信息: {}", ObjectUtils.isEmpty(member) ? "未记录数据库" : member.toString());
        return member;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    protected void subscribeFirstTime(User wxUser, RedirectReq req) {
        log.info("首次关注");
        UmsMember member = new UmsMember();
        convert(wxUser, member);
        UmsMember inviter = getInviter(req);
        if (!ObjectUtils.isEmpty(inviter)) {
            member.setPid(inviter.getId());
        }
        int r = memberService.insert(member);
        log.info("首次关注: 新增用户{}", r > 0 ? "成功" : "失败");
        log.info("会员用户信息: {}", member.toString());
        followFirstTime(member, inviter);
    }

    /**
     * 已关注并从市行公众号上获取用户信息后，第一次进来更新邀请人信息
     * @param openid
     * @param req
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    protected void subscribeFirstTimeAndUpdate(String openid, RedirectReq req) throws WeixinException{

        //从市行公众号接口中获取粉丝信息
        String url_1 = url +"?openid="+openid;
        MemberDataRsp rsp = restTemplate.getForObject(url_1, MemberDataRsp.class);
        log.info(rsp.getData().toString());

        //判断是否关注公众号
        if(rsp.getData()!=null){
            MWxFans mWxFans=JSON.parseObject(JSON.toJSONString(rsp.getData()), new TypeReference<MWxFans>(){});
            //如果关注后无会员信息则初始化并添加会员信息
            UmsMember umsMember =new UmsMember();
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

            log.info("首次关注");
            //UmsMember member = memberService.selectByOpenid(openid);
            //convert(wxUser, member);
            UmsMember inviter = getInviter(req);
            if (!ObjectUtils.isEmpty(inviter)) {
                umsMember.setPid(inviter.getId());
            }
            int r = memberService.insert(umsMember);
            log.info("首次关注: 新增用户{}", r > 0 ? "成功" : "失败");
            log.info("会员用户信息: {}", umsMember.toString());
            followFirstTime(umsMember, inviter);
        }else{
            WeixinTemplate weixinTemplate = SpringUtils.getBean(WeixinTemplate.class);
            UserComponent userComponent = weixinTemplate.user();
            User wxUser = userComponent.info(openid);
            log.info("首次关注");
            UmsMember member = new UmsMember();
            convert(wxUser, member);
            UmsMember inviter = getInviter(req);
            if (!ObjectUtils.isEmpty(inviter)) {
                member.setPid(inviter.getId());
            }
            int r = memberService.insert(member);
            log.info("首次关注: 新增用户{}", r > 0 ? "成功" : "失败");
            log.info("会员用户信息: {}", member.toString());
            followFirstTime(member, inviter);
        }


    }

    /**
     * 初次关注后续操作
     * @param member
     */
    private void followFirstTime(UmsMember member, UmsMember inviter) {
        log.info("首次关注--后续处理");
        if(ObjectUtils.isEmpty(inviter)){
            return;
        }
        //直接发放到历史记录中
        iSmsCouponService.offerCoupons(member.getId(), inviter.getShopId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    protected void subscribeAgain(User wxUser, UmsMember member) {
        log.info("再次关注");
        convert(wxUser, member);
        int r = memberService.update(member);
        log.info("再次关注: 更新用户{}", r > 0 ? "成功" : "失败");
        log.info("会员用户信息: {}", member.toString());
        followAgain(member);
    }

    /**
     * 已关注或再次关注后续操作 <br/>
     * @param member <br/>
     */
    private void followAgain(UmsMember member) {
        log.info("已关注或再次关注--后续处理");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    protected void unsubscribe(User wxUser, UmsMember member) {
        log.info("取消关注");
        member.setSubscribe(UmsMemberConst.SubscribeEnum.NOT.getType());
        int r = memberService.update(member);
        log.info("取消关注: 更新用户{}", r > 0 ? "成功" : "失败");
    }

    @Override
    protected void updateCache(String openid, UmsMember member) {
        wechatRedis.setUmsMember(openid, member);
        log.info("更新微信用户缓存信息");
    }

    /**
     * 自定义转换: 微信用户 -> 系统会员
     *
     * @param wxUser
     * @param member
     */
    private void convert(User wxUser, UmsMember member) {
        member.setOpenid(wxUser.getOpenid());
        member.setSubscribe(wxUser.getSubscribe());
        member.setNickname(wxUser.getNickname());
        member.setSex(String.valueOf(wxUser.getSex()));
        member.setCity(wxUser.getCity());
        member.setCountry(wxUser.getCountry());
        member.setProvince(wxUser.getProvince());
        member.setHeadimgurl(wxUser.getHeadimgurl());
        member.setUnionid(wxUser.getUnionid());
        member.setRemark(wxUser.getRemark());
        log.info("自定义转换: 微信用户 -> 系统会员");
    }

    ///**
    // * 自定义转换：重定向参数 -> 系统会员
    // * @param req
    // * @param member
    // */
    //private void convert(RedirectReq req, UmsMember member) {
    //    if (ObjectUtils.isEmpty(req)) {
    //        return ;
    //    }
    //    if (StringUtils.isNotBlank(req.getInviterId())) {
    //        UmsMember inviter = memberService.selectByOpenid(req.getInviterId());
    //        if (!ObjectUtils.isEmpty(inviter) && !ObjectUtils.isEmpty(inviter.getId())) {
    //            member.setPid(inviter.getId());
    //        }
    //        log.info("自定义转换：重定向参数");
    //    }
    //}

    private UmsMember getInviter(RedirectReq req) {
        if (!ObjectUtils.isEmpty(req) && req.effective()) {
            return memberService.selectByOpenid(req.getInviterId());
        }
        return null;
    }
}
