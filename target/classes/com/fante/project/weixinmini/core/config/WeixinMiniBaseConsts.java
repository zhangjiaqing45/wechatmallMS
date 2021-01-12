package com.fante.project.weixinmini.core.config;

/**
 * @program: Fante
 * @Date:
 * @Author: Mr.Z
 * @Description: 微信小程序通用设置
 */
public class WeixinMiniBaseConsts {

    /**
     * Reids相关
     */
    public class Redis {
        /**
         * Redis存储的access_token键值
         */
        public static final String ACCESS_TOKEN_KEY = "weixin_mini_access_token::";
        /**
         * 提前过期时间（秒）
         */
        public static final long AHEAD_OF_EXPIRE = 600L;
    }
}
