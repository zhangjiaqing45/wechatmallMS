package com.fante.common.utils;

import com.fante.common.exception.BusinessException;
import com.fante.framework.web.domain.AjaxResult;

/**
 * @program: Fante
 * @Date: 2019/12/20 10:30
 * @Author: Mr.Z
 * @Description: 业务校验
 */
public class Checker {

    public static void check(boolean expression, String message) {
        check(expression, message, AjaxResult.Type.ERROR.value());
    }

    public static void check(boolean expression, String message, Integer code) {
        if (expression) {
            throw new BusinessException(code, message);
        }
    }

    public static void checkOp(boolean expression, String message) {
        checkOp(expression, message, AjaxResult.Type.ERROR.value());
    }

    public static void checkOp(boolean expression, String message, Integer code) {
        if (!expression) {
            throw new BusinessException(code, message);
        }
    }
}
