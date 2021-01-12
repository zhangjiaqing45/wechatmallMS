package com.fante.project.weixin.core.utils;

import com.fante.common.utils.StringUtils;
import com.fante.common.utils.idgen.IdGenerator;
import com.fante.framework.redis.RedisUtil;
import com.fante.project.business.umsMember.domain.UmsMember;
import com.fante.project.weixin.core.config.WeixinBaseConsts;
import com.fante.project.weixin.core.domain.RedirectReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * @program: Fante
 * @Date:
 * @Author: Mr.Z
 * @Description: 微信公众号Redis工具类
 */
@Component
public class WechatRedis {

    @Autowired
    RedisUtil redisUtil;

    /**************************************** 网页授权重定向参数 -- 开始 *****************************************/

    /**
     * 设置网页授权重定向参数
     * @param req
     * @return
     */
    public String setRedirectParams(RedirectReq req) {
        String key = getRedirectParamsKey();
        boolean result = redisUtil.set(key, req, WeixinBaseConsts.Redis.WX_REDIRECT_PARAMS_EXPIRE);
        return key;
    }

    /**
     * 获取网页授权重定向参数
     * @param key
     * @return
     */
    public RedirectReq getRedirectParams(String key) {
        Object obj = redisUtil.get(key);
        return ObjectUtils.isEmpty(obj) ? null : (RedirectReq) obj;
    }

    /**
     * 获取网页授权重定向参数KEY
     * @return
     */
    private String getRedirectParamsKey() {
        return StringUtils.format(WeixinBaseConsts.Redis.WX_REDIRECT_PARAMS_PREFIX, IdGenerator.nextId());
    }

    /**************************************** 网页授权重定向参数 -- 结束 *****************************************/



    /**************************************** 微信用户 -- 开始 *****************************************/

    /**
     * 设置微信用户
     * @param member
     * @return
     */
    public boolean setUmsMember(String openid, UmsMember member) {
        long time = ObjectUtils.isEmpty(member) ? WeixinBaseConsts.Redis.WX_NO_USER_EXPIRE : WeixinBaseConsts.Redis.WX_USER_EXPIRE;
        return redisUtil.set(getUmsMemberKey(openid), member, time);
    }

    /**
     * 获取微信用户
     * @param openid
     * @return
     */
    public UmsMember getUmsMember(String openid) {
        Object obj = redisUtil.get(getUmsMemberKey(openid));
        return ObjectUtils.isEmpty(obj) ? null : (UmsMember) obj;
    }

    /**
     * 清除微信用户
     * @param openid
     */
    public void delUmsMember(String openid) {
        redisUtil.del(getUmsMemberKey(openid));
    }

    /**
     * 获取微信用户Key
     * @param openid
     * @return
     */
    private String getUmsMemberKey(String openid) {
        return StringUtils.format(WeixinBaseConsts.Redis.WX_USER_KEY_PREFIX, openid);
    }

    /**************************************** 微信用户 -- 结束 *****************************************/
}
