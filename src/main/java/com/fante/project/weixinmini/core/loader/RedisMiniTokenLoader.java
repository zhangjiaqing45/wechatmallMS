package com.fante.project.weixinmini.core.loader;

import com.alibaba.fastjson.JSON;
import com.fante.project.weixinmini.core.config.WeixinMiniBaseConsts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.weixin4j.miniprogram.loader.ITokenLoader;
import org.weixin4j.miniprogram.model.auth.Token;

import java.util.concurrent.TimeUnit;

/**
 * @program: Fante
 * @Date:
 * @Author: Mr.Z
 * @Description: 实现ITokenLoader，Redis缓存MiniAccessToken
 */
@Component
public class RedisMiniTokenLoader implements ITokenLoader {

    private static Logger LOG = LoggerFactory.getLogger(RedisMiniTokenLoader.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Token get() {
        String accessToken = stringRedisTemplate.opsForValue().get(WeixinMiniBaseConsts.Redis.ACCESS_TOKEN_KEY);
        LOG.info("weixin_mini access_token:{}", accessToken);
        return JSON.parseObject(accessToken, Token.class);
    }

    @Override
    public void refresh(Token token) {
        LOG.info("refresh weixin_mini access_token:{}", token.toString());
        String accessToken = JSON.toJSONString(token);
        //ticket.getExpires_in() - 600L，是为了提前10分钟过期
        stringRedisTemplate.opsForValue().set(WeixinMiniBaseConsts.Redis.ACCESS_TOKEN_KEY, accessToken,
                token.getExpires_in() - WeixinMiniBaseConsts.Redis.AHEAD_OF_EXPIRE,
                TimeUnit.SECONDS);
    }
}
