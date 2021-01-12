package com.fante.common.constant;

/**
 * 通用常量信息
 *
 * @author fante
 */
public class Constants {

    /**
     * ID不存在时默认值
     */
    public static final long INVALID_ID = -1L;
    /**
     * 推荐
     */
    public static final String REMMOEND = "1";
    /**
     * 不推荐
     */
    public static final String NOT_REMMOEND = "0";
    /**
     * 热门
     */
    public static final String ISHOT = "1";
    /**
     * 非热门
     */
    public static final String NOT_ISHOT = "0";
    /**
     * 支付
     */
    public static final String PAYMENTDISPLAY = "1";
    /**
     * 不支付
     */
    public static final String NOT_PAYMENTDISPLAY = "0";

    /**
     * 字符串0
     */
    public static final String ZERO_STR = "0";

    /**
     * UTF-8 字符集
     */
    public static final String UTF8 = "UTF-8" ;

    /**
     * 通用成功标识
     */
    public static final String SUCCESS = "0" ;

    /**
     * 通用失败标识
     */
    public static final String FAIL = "1" ;

    /**
     * 通用启用标识
     */
    public static final String ENABLE = "0" ;

    /**
     * 通用停用标识
     */
    public static final String DISABLE = "1" ;

    /**
     * 通用删除标识
     */
    public static final String DELETED = "1";

    /**
     * 通用唯一校验成功标识
     */
    public final static String UNIQUE = "0" ;

    /**
     * 通用唯一校验失败标识
     */
    public final static String NOT_UNIQUE = "1" ;

    /**
     * 登录成功
     */
    public static final String LOGIN_SUCCESS = "Success" ;

    /**
     * 注销
     */
    public static final String LOGOUT = "Logout" ;

    /**
     * 登录失败
     */
    public static final String LOGIN_FAIL = "Error" ;

    /**
     * 当前记录起始索引
     */
    public static final String PAGE_NUM = "pageNum" ;

    /**
     * 每页显示记录数
     */
    public static final String PAGE_SIZE = "pageSize" ;

    /**
     * 排序列
     */
    public static final String ORDER_BY_COLUMN = "orderByColumn" ;

    /**
     * 排序的方向 "desc" 或者 "asc".
     */
    public static final String IS_ASC = "isAsc" ;

    /**
     * 参数管理 cache name
     */
    public static final String SYS_CONFIG_CACHE = "sys-config";

    /**
     * 参数管理 cache key
     */
    public static final String SYS_CONFIG_KEY = "sys_config:";

    /**
     * 资源映射路径 前缀
     */
    //public static final String RESOURCE_PREFIX = "profile" ;
}
