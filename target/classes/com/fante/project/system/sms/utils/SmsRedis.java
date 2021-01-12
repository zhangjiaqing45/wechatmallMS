package com.fante.project.system.sms.utils;

import com.fante.framework.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * @program: Fante
 * @Date: 2020/1/8 15:20
 * @Author: Mr.Z
 * @Description:
 */
@Component
public class SmsRedis {

    @Autowired
    RedisUtil redisUtil;

    /**
     * 默认短信有效时间（秒）
     */
    public static final long DEF_EXPIRE = 300;
    /**
     * Redis中存储的键名
     */
    public static final String KEY_PREFIX = "fante:sms::";

    /**
     * 删除保存在Redis中的验证码
     * @param phone
     */
    public void del(String phone) {
        redisUtil.del(smsKey(phone));
    }

    /**
     * 获取保存在Redis中的验证码
     * @param phone
     * @return
     */
    public String get(String phone){
        Object verifyCode = redisUtil.get(smsKey(phone));
        return ObjectUtils.isEmpty(verifyCode) ? "" : String.valueOf(verifyCode);
    }


    /**
     * Redis保存该手机号对应的验证码
     * @param phone
     * @param verifyCode
     * @param expire
     * @return
     */
    public boolean set(String phone, String verifyCode, Long expire) {
        return redisUtil.set(smsKey(phone), verifyCode, timeValid(expire));
    }

    /**
     * 检查是否以为该手机号发送短信
     * @param phone
     * @return
     */
    public boolean hasKey(String phone){
        return redisUtil.hasKey(smsKey(phone));
    }


    /**
     * 生成短信键名
     * @param phone
     * @return
     */
    private String smsKey(String phone) {
        return KEY_PREFIX + phone;
    }

    /**
     * 获取正确的有效时间
     * @param expire
     * @return
     */
    private long timeValid(Long expire) {
        if (!ObjectUtils.isEmpty(expire) && expire > 0) {
            return expire;
        }
        return DEF_EXPIRE;
    }

}
