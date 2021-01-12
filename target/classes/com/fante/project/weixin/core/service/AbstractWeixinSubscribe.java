package com.fante.project.weixin.core.service;

import com.fante.project.business.umsMember.domain.UmsMember;
import com.fante.project.weixin.core.domain.RedirectReq;
import org.springframework.util.ObjectUtils;
import org.weixin4j.WeixinException;

/**
 * @program: Fante
 * @Date:
 * @Author: Mr.Z
 * @Description: 微信关注/取消关注抽象操作
 */
public abstract class AbstractWeixinSubscribe<WxUser, Member, RedirectParams> {

    /**
     * 获取微信用户信息 <br/>
     * @return WxUser 微信用户 <br/>
     * @throws WeixinException
     */
    protected abstract WxUser getWxUser(String openid) throws WeixinException;

    /**
     * 获取系统会员信息 <br/>
     * @param wxUser 微信用户 <br/>
     * @return Member 系统会员 <br/>
     */
    protected abstract Member getMember(WxUser wxUser);

    /**
     * 初次关注 <br/>
     * @param wxUser 微信用户 <br/>
     * @param params 重定向参数 <br/>
     */
    protected abstract void subscribeFirstTime(WxUser wxUser, RedirectParams params);

    /**
     * 第一次进来更新粉丝邀请人
     * @param openid
     * @param params
     */
    protected abstract void subscribeFirstTimeAndUpdate(String openid, RedirectParams params) throws WeixinException;

    /**
     * 已关注或再次关注 <br/>
     * @param wxUser 微信用户 <br/>
     * @param member 系统会员 <br/>
     */
    protected abstract void subscribeAgain(WxUser wxUser, Member member);

    /**
     * 取消关注 <br/>
     * @param wxUser 微信用户 <br/>
     * @param member 系统会员 <br/>
     */
    protected abstract void unsubscribe(WxUser wxUser, Member member);

    /**
     * 更新缓存 <br/>
     * @param member 系统会员 <br/>
     */
    protected abstract void updateCache(String openid, Member member);

    /**
     * 关注处理过程 <br/>
     * @throws WeixinException <br/>
     */
    public final void subscribeProcess(String openid, RedirectParams params) throws WeixinException {
        //WxUser wxUser = getWxUser(openid);
        //Member member = getMember(wxUser);
        Member member = getMemberByOpenid(openid);
        if (ObjectUtils.isEmpty(member)) {
            /*WxUser wxUser = getWxUser(openid);
            subscribeFirstTime(wxUser, params);*/
             //将市行公众号粉丝信息插入会员信息表中
            subscribeFirstTimeAndUpdate(openid,params);
        }else{
            //更新信息
            updateCache(openid, member);
        }


        //updateCache(openid, member);0
    }

    /**
     * 取消关注处理过程 <br/>
     * @throws WeixinException <br/>
     */
    public final void unsubscribeProcess(String openid) throws WeixinException {
        WxUser wxUser = getWxUser(openid);
        Member member = getMember(wxUser);
        if (!ObjectUtils.isEmpty(member)) {
            updateCache(openid, member);
            unsubscribe(wxUser, member);
        }
    }

    /**
     * 通过openid获取会员信息
     * @param openid
     * @return
     */
    protected abstract Member getMemberByOpenid(String openid);

}
