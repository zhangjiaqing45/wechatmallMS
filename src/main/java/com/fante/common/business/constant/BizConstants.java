package com.fante.common.business.constant;

/**
 * @program: Fante
 * @Date: 2020/3/11 9:49
 * @Author: Mr.Z
 * @Description: 业务常量
 */
public class BizConstants {
    public class regexp {
        /**
         *  "{\"颜色\":\"蓝色\",\"内存\":\"256\"}" .replaceAll("","")
         *  结果:
         *  颜色:蓝色,内存:256
         */
        public static final String REGEXP_SP_DATA_TRIM = "\\{|\\}|\\\"";
    }

    public class base {
        /**
         * 店铺邀请入口
         */
        public static final String BIZ_SHOP_INVITE_ENTRANCE = "biz.shop.invite.entrance";
        /**
         * 自营店ID
         */
        public static final long SELF_SHOP_ID = 1L;
        /**
         * 短信验证码有效时间（秒）
         */
        public static final String SYS_SMS_CODE_EXPIRE = "sys.sms.code.expire";

    }

    /**
     * 前端相关
     */
    public class app {
        /**
         * 首页广告位置显示广告的数量
         */
        public static final String APP_ADVERTISE_SHOW_NUM = "app.advertise.show.num";
        /**
         * 首页广告位置显示广告的数量默认值
         */
        public static final String APP_ADVERTISE_SHOW_NUM_DEF = "3";
        /**
         * 首页推荐商品显示数量
         */
        public static final String APP_RECOMMEND_SHOW_NUM = "app.recommend.show.num";
        /**
         * 首页推荐商品显示数量默认值
         */
        public static final String APP_RECOMMEND_SHOW_NUM_DEF = "2";
        /**
         * 首页热门商品显示数量
         */
        public static final String APP_ISHOT_SHOW_NUM = "app.ishot.show.num";
        /**
         * 首页热门商品显示数量默认值
         */
        public static final String APP_ISHOT_SHOW_NUM_DEF = "2";
        /**
         * 首页新闻咨询显示数量
         */
        public static final String APP_TOPIC_SHOW_NUM = "app.topic.show.num";
        /**
         * 首页新闻咨询显示数量默认值
         */
        public static final String APP_TOPIC_SHOW_NUM_DEF = "3";
    }

    /**
     * 秒杀活动相关
     */
    public class smsFlash {
        /**
         * 秒杀时间段管理中可选的开始时间段
         */
        public static final String BIZ_FLASH_SESSION_BEGIN = "biz.flash.session.begin";
        /**
         * 可选的开始时间段默认值
         */
        public static final String BIZ_FLASH_SESSION_BEGIN_DEF = "8";

        /**
         * 秒杀时间段管理中可选的截止时间段
         */
        public static final String BIZ_FLASH_SESSION_END = "biz.flash.session.end";
        /**
         * 可选的开始时间段默认值
         */
        public static final String BIZ_FLASH_SESSION_END_DEF = "20";

    }

    /**
     * 积分商品相关
     */
    public class smsIntegral {
        /**
         * 积分商品相册图片最大数量
         */
        public static final String BIZ_INTEGRAL_PRODUCT_ALBUM = "biz.integral.product.album";
        /**
         * 积分商品相册图片最大数量默认值
         */
        public static final String BIZ_INTEGRAL_PRODUCT_ALBUM_DEF = "5";
        /**
         * 积分兑换规则文档键值
         */
        public static final String BIZ_INTEGRAL_EXCHAGE_RULES_DOC_KEY = "doc.integral.exchange.rules";

    }

    /**
     * 广告相关
     */
    public class smsAdvertise {
        /**
         * 跳转店铺链接前缀
         */
        public static final String AD_URL_PREFIX_SHOP = "biz.ad.url.prefix.shop";
        /**
         * 跳转商品链接前缀
         */
        public static final String AD_URL_PREFIX_PRODUCT = "biz.ad.url.prefix.product";
    }

    /**
     * 店铺相关
     */
    public class shop {
        /**
         * 店铺入驻用户注册中绑定的角色键值
         */
        public static final String FRANCHISEE_REGISTER_ROLE = "biz.franchisee.register.role";

        /**
         * 店铺入驻完善信息路径
         */
        public static final String SHOP_INFO_ACCOMPLISH_URL = "biz.shop.info.accomplish.url";

        /**
         * 店铺入驻须知文档键值
         */
        public static final String SHOP_ENTRY_NOTICE_DOC_KEY = "doc.shop.entry.notice";

        /**
         * 店铺主营类目所需资料最大数目
         */
        public static final String BIZ_MAIN_CATEGORY_INFO_MAX = "biz.main.category.info.max";
        /**
         * 店铺主营类目所需资料最大数目默认值
         */
        public static final String BIZ_MAIN_CATEGORY_INFO_MAX_DEF = "9";
    }


    /**
     * 短信内容模板
     */
    public class shortMsg {
        /**
         * 短信验证码信息
         */
        public static final String VERIFY_CODE_MSG = "【{}】您的验证码为{}，{}秒内有效，若非本人操作请忽略此消息。";

        /**
         * 店铺注册审核未通过短信提示信息
         */
        public static final String SHOP_AUDIT_REFUSE_MSG = "您的账号[{}]申请入驻的店铺[{}], 审核未通过；原因：{}. 请登录账号完善店铺信息";

        /**
         * 店铺注册审核通过短信提示信息
         */
        public static final String SHOP_AUDIT_PASS_MSG = "您的账号[{}]申请入驻的店铺[{}], 审核通过； ";

    }

    /**
     * Reids相关
     */
    public class redis {
        /**
         * 第一次订阅公众号提示发放优惠券时效验证KEY
         */
        public static final String FIRST_SUBSCRIBE_ENTRANCE_KEY = "first:subscribe::";
        /**
         * 第一次订阅公众号提示发放优惠券过期时间
         */
        public static final long FIRST_SUBSCRIBE_ENTRANCE_EXPIRE = 86400L;
        /**
         * 店铺邀请链接时效验证KEY
         */
        public static final String SHOP_INVITE_ENTRANCE_KEY = "shop:invite::";
        /**
         * 店铺邀请链接时效验证有效期（秒）
         */
        public static final String SHOP_INVITE_ENTRANCE_EXPIRE = "biz.shop.invite.entrance.expire";
    }


    /**
     * 显示样式
     */
    public class style {
        public static final String PRIMARY = "primary";
        public static final String SUCCESS = "success";
        public static final String INFO = "info";
        public static final String WARNING = "warning";
        public static final String DANGER = "danger";
        public static final String DEFAULT = "default";
    }

    public class path {
        /**
         * 下载路径
         */
        public static final String DOWNLOAD = "download";
        /**
         * 头像上传路径
         */
        public static final String AVATAR = "avatar";
        /**
         * 上传路径
         */
        public static final String UPLOAD = "upload";
        /**
         * 上传路径(重要信息)
         */
        public static final String ORG_UPLOAD = "orgUpload";
        /**
         * 富文本图片上传路径
         */
        public static final String RICH_TEXT_UPLOAD = "richTextUpload";
        /**
         * 微信公众号上传路径
         */
        public static final String WECHAT_UPLOAD = "wechatUpload";
    }
}
