package com.fante.project.weixin.core.loader;

import com.alibaba.fastjson.JSON;
import com.fante.project.weixin.core.config.WeixinBaseConsts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.weixin4j.loader.ITicketLoader;
import org.weixin4j.model.js.Ticket;
import org.weixin4j.model.js.TicketType;

import java.util.concurrent.TimeUnit;

@Component
public class RedisTicketLoader implements ITicketLoader {

    private static final Logger LOG = LoggerFactory.getLogger(RedisTicketLoader.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Ticket get(TicketType ticketType) {
        String key = "";
        if (null != ticketType) {
            key = getTicketKey(ticketType);
        }
        String ticket = stringRedisTemplate.opsForValue().get(key);
        LOG.info("wechat ticket:{}", ticket);
        return JSON.parseObject(ticket, Ticket.class);
    }

    @Override
    public void refresh(Ticket ticket) {
        String key = "";
        if (null != ticket.getTicketType()) {
            key = getTicketKey((ticket.getTicketType()));
        }
        LOG.info("refresh wechat ticket:{}", ticket.toString());
        String ticketValue = JSON.toJSONString(ticket);
        //ticket.getExpires_in() - 600L，是为了提前10分钟过期
        stringRedisTemplate.opsForValue().set(key, ticketValue,
                ticket.getExpires_in() - WeixinBaseConsts.Redis.AHEAD_OF_EXPIRE,
                TimeUnit.SECONDS);
    }

    /**
     * 根据Ticket类型获取Key
     * @param ticketType
     * @return
     */
    private String getTicketKey(TicketType ticketType) {
        switch (ticketType) {
            case JSAPI:
                return WeixinBaseConsts.Redis.JS_API_KEY;
            case WX_CARD:
                return WeixinBaseConsts.Redis.WX_CARD_KEY;
            default:
                return WeixinBaseConsts.Redis.DEF_TICKET_KEY;
        }
    }

}
