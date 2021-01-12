package com.fante.common.utils;

import java.util.UUID;

/**
 * @program: Fante
 * @Date:
 * @Author: Mr.Z
 * @Description:g
 */
public class UUIDUtils {

    /**
     * 生成32为UUID
     * @return
     */
    public static String general(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
