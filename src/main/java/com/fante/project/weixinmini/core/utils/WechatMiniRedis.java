package com.fante.project.weixinmini.core.utils;

import com.fante.framework.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 * @program: Fante
 * @Date:
 * @Author: Mr.Z
 * @Description: 微信小程序Redis工具类
 */
@Component
public class WechatMiniRedis {

    @Autowired
    RedisUtil redisUtil;


}
