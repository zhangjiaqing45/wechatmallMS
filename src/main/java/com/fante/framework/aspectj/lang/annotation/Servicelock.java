package com.fante.framework.aspectj.lang.annotation;

import java.lang.annotation.*;

/**
 * @program: sunshinecredit
 * @Date: 2019/11/4 14:29
 * @Author: Mr.Z
 * @Description:
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Servicelock {
    String description()  default "";
}
