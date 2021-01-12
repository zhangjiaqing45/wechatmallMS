package com.fante;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 *
 * @author fante
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class FanteApplication {
    public static void main(String[] args) {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(FanteApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  生活圈后台启动成功   ლ(´ڡ`ლ)ﾞ  \n");
    }
}