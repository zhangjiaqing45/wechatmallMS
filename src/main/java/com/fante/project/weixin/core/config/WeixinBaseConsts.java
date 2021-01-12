package com.fante.project.weixin.core.config;

/**
 * @program: Fante
 * @Date:
 * @Author: Mr.Z
 * @Description: 微信公众号通用设置
 */
public class WeixinBaseConsts {

    /**
     * 通用设置
     */
    public class Common {
    }


    /**
     * 模板消息
     */
    public class TplMsg {
        /**
         * 售后服务处理进度提醒-模板ID
         */
        public static final String AFTER_SALES_SERVICE = "zU7FEeGLeBxQ1tYbBZ7cTaz7p628NeTldBKs0iiMjus";
        /**
         * 售后服务处理进度提醒-模板{{first.DATA}}
         */
        public static final String AFTER_SALES_SERVICE_TITLE = "尊敬的{}先生/女士，您的工单[{}]有新的处理进度：";
        /**
         * 售后服务处理进度提醒-模板{{remark.DATA}}
         */
        public static final String AFTER_SALES_SERVICE_REMARK = "点击“详情”查看工单详细情况";
    }


    /**
     * 网页授权
     */
    public class Authorize {
        /**
         * 应用授权作用域：不弹出授权页面，直接跳转，只能获取用户openid
         */
        public static final String SCOPE_SNSAPI_BASE = "snsapi_base";
        /**
         * 应用授权作用域：弹出授权页面，可通过openid拿到昵称、性别、所在地。并且， 即使在未关注的情况下，只要用户授权，也能获取其信息
         */
        public static final String SCOPE_SNSAPI_USERINFO = "snsapi_userinfo";

    }

    /**
     * Reids相关
     */
    public class Redis {
        /**
         * Redis存储的微信用户键值
         */
        public static final String WX_USER_KEY_PREFIX = "weixin-user:openid-{}::";
        /**
         * Redis存储的网页授权重定向参数
         */
        public static final String WX_REDIRECT_PARAMS_PREFIX = "weixin-redirect:key-{}";
        /**
         * Redis存储的微信用户过期时间（秒）
         */
        public static final long WX_USER_EXPIRE = 7200;
        /**
         * Redis存储的网页授权重定向参数过期时间（秒）
         */
        public static final long WX_REDIRECT_PARAMS_EXPIRE = 120;
        /**
         * Redis存储的空用户过期时间（秒）
         */
        public static final long WX_NO_USER_EXPIRE = 30;
        /**
         * Redis存储的access_token键值
         */
        public static final String ACCESS_TOKEN_KEY = "weixin_access_token::";
        /**
         * Redis存储的正常获取jsapi_ticket键值
         */
        public static final String JS_API_KEY = "wechat_ticket_jsapi::";
        /**
         * Redis存储的微信卡券获取jsapi_ticket键值
         */
        public static final String WX_CARD_KEY = "wechat_ticket_wxcard::";
        /**
         * Redis存储的默认的jsapi_ticket键值
         */
        public static final String DEF_TICKET_KEY = "wechat_def_ticket::";
        /**
         * 提前过期时间（秒）
         */
        public static final long AHEAD_OF_EXPIRE = 600L;
    }
}
