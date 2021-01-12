package com.fante.project.weixin.core.service.impl;

import com.fante.common.utils.spring.SpringUtils;
import com.fante.project.weixin.core.config.WeixinBaseConsts;
import com.fante.project.weixin.core.loader.RedisTicketLoader;
import com.fante.project.weixin.core.loader.RedisTokenLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.weixin4j.WeixinBuilder;
import org.weixin4j.WeixinConfig;
import org.weixin4j.WeixinPayConfig;
import org.weixin4j.factory.WeixinFactory;
import org.weixin4j.spring.WeixinTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * @program: Fante
 * @Date: 2020/4/9 13:53
 * @Author: Mr.Z
 * @Description: 微信配置（公众号、支付）
 */
@Service
public class WeixinConfigService  {

    private static Logger log = LoggerFactory.getLogger(WeixinConfigService.class);

    @Autowired
    RedisTokenLoader redisTokenLoader;
    @Autowired
    RedisTicketLoader redisTicketLoader;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 重载微信公众号配置
     * @param weixinConfig
     */
    public void reloadWeixinConfig(WeixinConfig weixinConfig) {
        clearCache();
        log.info("清除微信公众号token和ticket缓存");
        reload(weixinConfig, null);
        log.info("重载微信公众号配置");
    }

    /**
     * 重载微信支付配置
     * @param weixinPayConfig
     */
    public void reloadWeixinPayConfig(WeixinPayConfig weixinPayConfig) {
        reload(null, weixinPayConfig);
        log.info("重载微信支付配置");
    }

    /**
     * 重载配置
     * @param weixinConfig
     * @param weixinPayConfig
     */
    private void reload(WeixinConfig weixinConfig, WeixinPayConfig weixinPayConfig){

        WeixinTemplate weixinTemplate = SpringUtils.getBean(WeixinTemplate.class);
        if (ObjectUtils.isEmpty(weixinConfig)) {
            weixinConfig = weixinTemplate.getWeixinConfig();
        }
        if (ObjectUtils.isEmpty(weixinPayConfig)) {
            weixinPayConfig = weixinTemplate.getWeixinPayConfig();
        }
        WeixinFactory factory = WeixinBuilder.newInstance(weixinConfig, weixinPayConfig)
                .setTicketLoader(redisTicketLoader)
                .setTokenLoader(redisTokenLoader)
                .buildWeixinFactory();

        refresh(factory);
    }

    /**
     * spring刷新bean
     * @param weixinFactory
     */
    private void refresh(WeixinFactory weixinFactory){
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(WeixinTemplate.class);

        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();

        ConstructorArgumentValues argValues = new ConstructorArgumentValues();
        argValues.addIndexedArgumentValue(0, weixinFactory);
        beanDefinition.setConstructorArgumentValues(argValues);

        SpringUtils.beanFactory.registerBeanDefinition("weixinTemplate", beanDefinitionBuilder.getBeanDefinition());
    }

    /**
     * 清除缓存
     */
    private void clearCache() {
        try {
            List<String> keys = Arrays.asList(WeixinBaseConsts.Redis.ACCESS_TOKEN_KEY, WeixinBaseConsts.Redis.JS_API_KEY, WeixinBaseConsts.Redis.WX_CARD_KEY, WeixinBaseConsts.Redis.DEF_TICKET_KEY);
            stringRedisTemplate.delete(keys);
        } catch (Exception e) {
            log.error("Redis异常: {}", e.getMessage());
        }
    }

}
