package com.fante.framework.config;

import com.fante.project.business.smsCouponHistory.service.ISmsCouponHistoryService;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 程序注解配置
 *
 * @author fante
 */
@Configuration
// 表示通过aop框架暴露该代理对象,AopContext能够访问
@EnableAspectJAutoProxy(exposeProxy = true)
// 指定要扫描的Mapper类的包的路径
@MapperScan("com.fante.project.**.mapper")
@EnableScheduling
@EnableTransactionManagement
public class ApplicationConfig {

    private Logger logger = LoggerFactory.getLogger(ApplicationConfig.class);
    @Autowired
    private ISmsCouponHistoryService smsCouponHistoryService;

    @Scheduled(cron = "0 0 23 * * ? ")//每天23点执行
    private void updateState() {
        logger.info("自动扫描优惠券失效开始");
        Integer result = smsCouponHistoryService.updateUserState();
        logger.info("自动扫描优惠券失效结束", result);
    }

}
