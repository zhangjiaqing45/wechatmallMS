package com.fante.common.business.enums;

import com.fante.common.business.constant.BizConstants;
import com.fante.common.utils.MapDataUtil;
import com.fante.common.utils.StringUtils;
import com.google.common.collect.Lists;
import com.sun.net.httpserver.Authenticator;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.*;

/**
 * @program: Fante
 * @Date: 2020/2/21 17:22
 * @Author: Mr.Z
 * @Description: 微信支付日志类型
 */
public class OrderConst {

    /**redis订单的key的前缀*/
    public static final String REDIS_ORDER_PREFIX = "order:create:";
    /**redis加入组团的key的前缀*/
    public static final String REDIS_JOIN_GROUP_PREFIX = "join:group:";
    /**redis存活时间:30min*/
    public static final int REDIS_ORDER_LIVE_TIME = 30*60;


    private OrderConst(){}
    /**购物车类型*/
    public enum CartType{
        /**购物车商品*/
        CART("0","购物车商品"),
        /**立即购买商品*/
        BUY_NOW("1","立即购买商品"),
        /**立即团购商品*/
        GROUP("2","立即团购商品"),
        /**立即秒杀商品*/
        FLASH("3","立即秒杀商品"),
        /**积分兑换商品*/
        INTEGRAL("4","积分兑换商品");

        private String type;
        private String describe;

        CartType(String type, String describe) {
            this.type = type;
            this.describe = describe;
        }

        public String getType() {
            return type;
        }

        public String getDescribe() {
            return describe;
        }

        private static final Map<String, Object> SELF_MAP = new LinkedHashMap<>();

        public static Map<String, Object> toMap() {
            for (CartType value : CartType.values()) {
                SELF_MAP.put(value.getType(), value.getDescribe());
            }
            return SELF_MAP;
        }

        public static CartType get(String type){
            for (CartType value : CartType.values()) {
                if(Objects.equals(value.getType(),type)){
                    return value;
                }
            }
            return null;
        }
    }
    /**订单类型*/
    public enum OrderType{
        /**普通订单*/
        //GENERAL("1","普通订单"),
        /**团购订单*/
        //GROUP("2","团购订单"),
        /**核销订单*/
        GENERAL("1","核销订单"),
        /*预约订单*/
        GROUP("2","预约订单"),
        /**秒杀订单*/
        FLASH("3","秒杀订单"),
        /**积分订单*/
        INTEGRAL("4","积分订单");



        private String type;
        private String describe;

        OrderType(String type, String describe) {
            this.type = type;
            this.describe = describe;
        }

        public String getType() {
            return type;
        }

        public String getDescribe() {
            return describe;
        }

        private static final Map<String, Object> SELF_MAP = new LinkedHashMap<>();

        public static Map<String, Object> toMap() {
            for (OrderType value : OrderType.values()) {
                SELF_MAP.put(value.getType(), value.getDescribe());
            }
            return SELF_MAP;
        }

        public static OrderType get(String type){
            for (OrderType value : OrderType.values()) {
                if(Objects.equals(value.getType(),type)){
                    return value;
                }
            }
            return null;
        }
    }
    /**备注标志*/
    public enum RemarkFlag{
        /**重要等级1*/
        FLAG_1("1","标记1",StringUtils.EMPTY),
        FLAG_2("2","标记2",BizConstants.style.INFO),
        FLAG_3("3","标记3",BizConstants.style.SUCCESS),
        FLAG_4("4","标记4",BizConstants.style.PRIMARY),
        FLAG_5("5","标记5",BizConstants.style.WARNING),
        FLAG_6("6","标记6",BizConstants.style.DANGER),
        ;

        private String type;
        private String describe;
        private String style;
        RemarkFlag(String type, String describe,String style) {
            this.type = type;
            this.describe = describe;
            this.style = style;
        }

        public String getType() {
            return type;
        }

        public String getStyle() {
            return style;
        }
        public String getDescribe() {
            return describe;
        }

        private static final Map<String, Object> SELF_MAP = new LinkedHashMap<>();
        private static final Map<String, Object> MAP_PLUS = new LinkedHashMap<>();

        public static Map<String, Object> toMap() {
            for (RemarkFlag value : RemarkFlag.values()) {
                SELF_MAP.put(value.getType(), value.getDescribe());
            }
            return SELF_MAP;
        }
        public static Map<String, Object> toMapPlus() {
            Map<String, Object> inner = null;
            for (RemarkFlag value : RemarkFlag.values()) {
                inner = new HashMap<>();
                inner.put("style", value.getStyle());
                inner.put("desc", value.getDescribe());
                MAP_PLUS.put(value.getType(), inner);
            }
            return MAP_PLUS;
        }
        public static RemarkFlag get(String type){
            for (RemarkFlag value : RemarkFlag.values()) {
                if(Objects.equals(value.getType(),type)){
                    return value;
                }
            }
            return null;
        }
    }
    /**订单状态*/
    public enum OrderStatus{
        /**待支付*/
        WAIT_PAY("0","待支付"),
        /**待支付*/
       // WAIT_GROUP("7","待组团"),
        /**待发货*/
        //WAIT_SEND("1","待发货"),
        WAIT_SEND("1","待使用"),
        /**已发货*/
        //SENDED("2","已发货"),
        /**确认收货(*/
        //RECEIVED("3","已收货"),
        RECEIVED("3","已完成"),
        /**已关闭：收货后超过一定时间订单关闭，不允许任何操作*/
        CLOSED("4","已关闭"),
        /**已退货*/
        //RETURN("5","已退货"),
        /**无效订单:过期自动取消 or 手动取消后的订单*/
        INVALID("6","无效订单"),
        ;

        private String type;
        private String describe;

        OrderStatus(String type, String describe) {
            this.type = type;
            this.describe = describe;
        }

        public String getType() {
            return type;
        }

        public String getDescribe() {
            return describe;
        }

        private static final Map<String, Object> SELF_MAP = new LinkedHashMap<>();

        public static Map<String, Object> toMap() {
            for (OrderStatus value : OrderStatus.values()) {
                SELF_MAP.put(value.getType(), value.getDescribe());
            }
            return SELF_MAP;
        }

        public static OrderStatus get(String type){
            for (OrderStatus value : OrderStatus.values()) {
                if(Objects.equals(value.getType(),type)){
                    return value;
                }
            }
            return null;
        }
    }
    /**评价状态*/
    public enum CommentStatus{
        /**未评价*/
        NO("0","未评价"),
        /**已评价*/
        YES("1","已评价");

        private String type;
        private String describe;

        CommentStatus(String type, String describe) {
            this.type = type;
            this.describe = describe;
        }

        public String getType() {
            return type;
        }

        public String getDescribe() {
            return describe;
        }

        private static final Map<String, Object> SELF_MAP = new LinkedHashMap<>();

        public static Map<String, Object> toMap() {
            for (CommentStatus value : CommentStatus.values()) {
                SELF_MAP.put(value.getType(), value.getDescribe());
            }
            return SELF_MAP;
        }
        public static CommentStatus get(String type){
            for (CommentStatus value : CommentStatus.values()) {
                if(Objects.equals(value.getType(),type)){
                    return value;
                }
            }
            return null;
        }
    }
    /**退货状态*/
    public enum ReturnStatus{
        /**未退货*/
        NO("0","未退货"),
        /**已退货*/
        YES("1","已退货");

        private String type;
        private String describe;

        ReturnStatus(String type, String describe) {
            this.type = type;
            this.describe = describe;
        }

        public String getType() {
            return type;
        }

        public String getDescribe() {
            return describe;
        }
    }
    /**支付状态*/
    public enum PayStatus{
        /**未支付*/
        NO_PAY("0","未支付"),
        /**未支付*/
        RETURN("2","已退款"),
        /**已支付*/
        PAY("1","已支付");

        private String type;
        private String describe;

        PayStatus(String type, String describe) {
            this.type = type;
            this.describe = describe;
        }

        public String getType() {
            return type;
        }

        public String getDescribe() {
            return describe;
        }

        private static final Map<String, Object> SELF_MAP = new LinkedHashMap<>();

        public static Map<String, Object> toMap() {
            for (PayStatus value : PayStatus.values()) {
                SELF_MAP.put(value.getType(), value.getDescribe());
            }
            return SELF_MAP;
        }
        public static PayStatus get(String type){
            for (PayStatus value : PayStatus.values()) {
                if(Objects.equals(value.getType(),type)){
                    return value;
                }
            }
            return null;
        }
    }
    /**发货状态*/
    public enum SendStatus{
        /**未发货*/
        NO("0","未发货"),
        /**未发货*/
        RETURN("2","已退货"),
        /**已支付*/
        YES("1","已发货");

        private String type;
        private String describe;

        SendStatus(String type, String describe) {
            this.type = type;
            this.describe = describe;
        }

        public String getType() {
            return type;
        }

        public String getDescribe() {
            return describe;
        }

        private static final Map<String, Object> SELF_MAP = new LinkedHashMap<>();

        public static Map<String, Object> toMap() {
            for (SendStatus value : SendStatus.values()) {
                SELF_MAP.put(value.getType(), value.getDescribe());
            }
            return SELF_MAP;
        }
        public static SendStatus get(String type){
            for (SendStatus value : SendStatus.values()) {
                if(Objects.equals(value.getType(),type)){
                    return value;
                }
            }
            return null;
        }
        private static final List<OrderStatus> WAIT_MAP = Arrays.asList(
                //OrderStatus.WAIT_PAY,OrderStatus.WAIT_SEND,OrderStatus.INVALID
        );

        public static SendStatus getByOrderStatus(String orderStatus) {
            OrderStatus status = OrderStatus.get(orderStatus);
            if(ObjectUtils.isEmpty(status)){return null;}
            switch (status){
            /**已发货*/
               // case SENDED:
                case RECEIVED:
                case CLOSED:
                    return SendStatus.YES;
                /**已退货*/
                //case RETURN:
                    //return SendStatus.RETURN;
                default:
                    return SendStatus.NO;
            }
        }
    }
    /**发票类型*/
    public enum BillType{
        /**不开发票*/
        NEEDLESS("0","不开发票"),
        /**电子发票*/
        WEB("1","电子发票"),
        /**纸质发票*/
        PAPER("2","纸质发票");

        private String type;
        private String describe;

        BillType(String type, String describe) {
            this.type = type;
            this.describe = describe;
        }

        public String getType() {
            return type;
        }

        public String getDescribe() {
            return describe;
        }

        private static final Map<String, Object> SELF_MAP = new LinkedHashMap<>();

        public static Map<String, Object> toMap() {
            for (BillType value : BillType.values()) {
                SELF_MAP.put(value.getType(), value.getDescribe());
            }
            return SELF_MAP;
        }
        private static final Map<String, Object> CHECK_MAP = new LinkedHashMap<>();
        public static Map<String, Object> toMapForCheck() {
            for (BillType value : BillType.values()) {
                if(Objects.equals(NEEDLESS,value)){
                    continue;
                }
                CHECK_MAP.put(value.getType(), value.getDescribe());
            }
            return CHECK_MAP;
        }

        public static BillType get(String type){
            for (BillType value : BillType.values()) {
                if(Objects.equals(value.getType(),type)){
                    return value;
                }
            }
            return null;
        }
    }

    public enum OrderActionEnum {
        //1订单发货/2查看详情/3订单跟踪/4删除订单/修改订单
        /**
         * 修改订单:待发货订单可以修改订单
         */
        EDIT("edit", "修改订单",new String[]{
                OrderStatus.WAIT_SEND.getType()
        }),
        /**
         * 取消订单:待发货订单可以取消订单
         */
        CANCLE("cancle", "取消订单",new String[]{
                OrderStatus.WAIT_SEND.getType()
        }),
        /**
         * 订单发货:待发货可以发货
         */
        TRANSPORT("transport", "订单发货",new String[]{
                OrderStatus.WAIT_SEND.getType()
        }),
        /**
         * 查看详情:详情都可以查看
         */
        DETAIL("detail", "查看详情",new String[]{
               // OrderStatus.WAIT_PAY.getType(),
                OrderStatus.WAIT_SEND.getType(),
                //OrderStatus.SENDED.getType(),
                OrderStatus.RECEIVED.getType(),
                OrderStatus.CLOSED.getType(),
               // OrderStatus.RETURN.getType(),
                OrderStatus.INVALID.getType()
        }),
        /**
         * 订单跟踪:已发货 和 已接受的可以查看物流
         */
        PROGRESS("progress", "订单跟踪",new String[]{
                //OrderStatus.SENDED.getType(),
                OrderStatus.RECEIVED.getType()
        }),
        /**
         * 删除订单:无效订单 /已完成 / 已关闭 订单 可以删除
         */
        DEL("del", "删除订单",new String[]{
                OrderStatus.INVALID.getType(),
                OrderStatus.RECEIVED.getType(),
                OrderStatus.CLOSED.getType(),
        }),
       ;


        private String type;
        private String describe;
        private String[] states;

        OrderActionEnum(String type, String describe, String[] states) {
            this.type = type;
            this.describe = describe;
            this.states = states;
        }

        public String getType() {
            return type;
        }

        public String getDescribe() {
            return describe;
        }

        public String[] getStates() {
            return states;
        }

        /**
         * 根据优惠券状态显示按钮
         * @param status
         * @return
         */
        public static List<String> getBtns(String status){
            if (StringUtils.isBlank(status)) {
                return Collections.emptyList();
            }
            List<String> btns = Lists.newArrayList();
            for (OrderActionEnum value : OrderActionEnum.values()) {
                if (Arrays.asList(value.getStates()).contains(status)) {
                    btns.add(value.getType());
                }
            }
            return btns;
        }

        private static final Map<String, Object> SELF_MAP = new LinkedHashMap<>();

        public static Map<String, Object> toMap() {
            for (OrderActionEnum value : OrderActionEnum.values()) {
                SELF_MAP.put(value.getType(), value.getDescribe());
            }
            return SELF_MAP;
        }
    }

}
