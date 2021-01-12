package com.fante.project.weixin.core.loader;

import com.alibaba.fastjson.JSON;
import com.fante.project.weixin.core.config.WeixinBaseConsts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.weixin4j.loader.ITokenLoader;
import org.weixin4j.model.base.Token;

import java.util.concurrent.TimeUnit;


@Component
public class RedisTokenLoader implements ITokenLoader {

    private static final Logger LOG = LoggerFactory.getLogger(RedisTokenLoader.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Token get() {
        String accessToken = stringRedisTemplate.opsForValue().get(WeixinBaseConsts.Redis.ACCESS_TOKEN_KEY);
        LOG.info("wechat access_token:{}", accessToken);
        return JSON.parseObject(accessToken, Token.class);
    }

    @Override
    public void refresh(Token token) {
        LOG.info("refresh wechat access_token:{}", token.toString());
        String accessToken = JSON.toJSONString(token);
        //ticket.getExpires_in() - 600L，是为了提前10分钟过期
        stringRedisTemplate.opsForValue().set(WeixinBaseConsts.Redis.ACCESS_TOKEN_KEY, accessToken,
                token.getExpires_in() - WeixinBaseConsts.Redis.AHEAD_OF_EXPIRE,
                TimeUnit.SECONDS);
    }
}