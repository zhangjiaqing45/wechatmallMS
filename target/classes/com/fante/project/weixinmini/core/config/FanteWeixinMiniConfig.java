package com.fante.project.weixinmini.core.config;

import com.fante.project.weixinmini.core.loader.RedisMiniTokenLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.weixin4j.miniprogram.WeixinMiniprogram;
import org.weixin4j.miniprogram.WeixinMiniprogramBuilder;

/**
 * @program: Fante
 * @Date:
 * @Author: Mr.Z
 * @Description: 微信小程序配置
 */
@Configuration
public class FanteWeixinMiniConfig {

    /**
     * 微信小程序模板
     * @param tokenLoader
     * @return
     */
    @Bean
    WeixinMiniprogram weixinMiniprogram(RedisMiniTokenLoader tokenLoader) {
        return WeixinMiniprogramBuilder.newInstance()
                .setTokenLoader(tokenLoader)
                .build();
    }

}
