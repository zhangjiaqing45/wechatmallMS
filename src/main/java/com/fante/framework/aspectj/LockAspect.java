package com.fante.framework.aspectj;

import com.fante.common.exception.BusinessException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: sunshinecredit
 * @Date: 2019/11/4 14:30
 * @Author: Mr.Z
 * @Description:
 */
@Component
@Scope
@Aspect
@Order(1)
public class LockAspect {

    private final static Logger logger = LoggerFactory.getLogger(LockAspect.class);

    /**
     * 互斥锁 (公平锁)
     */
    private static Lock lock = new ReentrantLock(true);


    /**
     * Service层切点
     */
    @Pointcut("@annotation(com.fante.framework.aspectj.lang.annotation.Servicelock)")
    public void lockAspect() {

    }

    @Around("lockAspect()")
    public Object around(ProceedingJoinPoint joinPoint) {
        logger.info("进入切面");
        lock.lock();
        Object obj = null;
        try {
            obj = joinPoint.proceed();
        } catch (Throwable e) {
            throw new BusinessException(e.getMessage());
            //e.printStackTrace();
            //throw new RuntimeException();
        } finally {
            lock.unlock();
            logger.info("切面操作完成");
        }
        return obj;
    }

}
