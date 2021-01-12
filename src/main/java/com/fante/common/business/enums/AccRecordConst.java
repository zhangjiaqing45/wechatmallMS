package com.fante.common.business.enums;

import com.fante.common.business.constant.BizConstants;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Date: 2020/2/21 17:22
 * @Author: WZ
 * @Description: 财务记录
 */
public class AccRecordConst {
    private AccRecordConst(){}

    /**
     * @Description 出入账描述
     * {}:商品购买,转入金额
     * {}:商品退货,资金返还
     * {}:申请提现,资金转出
     */
    public interface AccountDescribe{
        /**
         * 用户商品购买,转入佣金
         */
        public static final String DESCRIBE_COMMITION_ADD = "用户商品购买,转入佣金账户";
        /**
         * 用户商品购买,转入营业额
         */
        public static final String DESCRIBE_TURNOVER_ADD = "用户商品购买,转入营业额账户";
        /**
         * 用户商品退货,佣金扣减
         */
        public static final String DESCRIBE_COMMITION_RETURN = "用户商品退货,佣金账户扣减";
        /**
         * 用户商品退货,营业额扣减
         */
        public static final String DESCRIBE_TURNOVER_RETURN = "用户商品退货,营业额账户扣减";
        /**
         * 商户提现成功,营业额转出
         */
        public static final String DESCRIBE_SHOP_CASH = "商户提现成功,营业额账户转出";
        /**
         * 用户提现成功,佣金账户转出
         */
        public static final String DESCRIBE_MEMBER_CASH = "用户提现成功,佣金账户转出";
    }
    /**
     *  @Description 操作类型
     *  操作说明:0佣金收入 1商品收入 2商品退货支出 3提现支出
     */
    public enum  AccountOperation{
        /**
         * 佣金入账
         */
        COMMISSION_IN("0","订单佣金入账",BizConstants.style.INFO),
        /**
         * 订单入账
         */
        ORDER_IN("1","订单金额入账",BizConstants.style.INFO),
        /**
         * 退货订单出账
         */
        RETURN_OUT("2","退货订单出账",BizConstants.style.DANGER),
        /**
         * 退货佣金出账
         */
        RETURN_COMMISSION_OUT("3","退货佣金出账",BizConstants.style.DANGER),
        /**
         * 店铺提现出账
         */
        SHOP_CASH_OUT("4","店铺提现出账",BizConstants.style.DANGER),
        /**
         * 用户提现出账
         */
        MEMBER_CASH_OUT("5","用户提现出账",BizConstants.style.DANGER),
        ;

        private String type;
        private String describe;
        private String style;
        AccountOperation(String type, String describe,String style) {
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
            for (AccountOperation value : AccountOperation.values()) {
                SELF_MAP.put(value.getType(), value.getDescribe());
            }
            return SELF_MAP;
        }
        public static Map<String, Object> toMapPlus() {
            Map<String, Object> inner = null;
            for (AccountOperation value : AccountOperation.values()) {
                inner = new HashMap<>();
                inner.put("style", value.getStyle());
                inner.put("desc", value.getDescribe());
                MAP_PLUS.put(value.getType(), inner);
            }
            return MAP_PLUS;
        }
        public static AccountOperation get(String type){
            for (AccountOperation value : AccountOperation.values()) {
                if(Objects.equals(value.getType(),type)){
                    return value;
                }
            }
            return null;
        }
    }
    /**
     *  @Description 金额类型
     *  金额类型->0:佣金1:商品2提现
     */
    public enum AccountMoneyType{
        /**
         * 分佣金额
         */
        COMMISSION("0","分佣金额",BizConstants.style.INFO),
        /**
         * 营业金额
         */
        BASE("1","营业金额",BizConstants.style.SUCCESS),

        ;

        private String type;
        private String describe;
        private String style;
        AccountMoneyType(String type, String describe,String style) {
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
            for (AccountMoneyType value : AccountMoneyType.values()) {
                SELF_MAP.put(value.getType(), value.getDescribe());
            }
            return SELF_MAP;
        }
        public static Map<String, Object> toMapPlus() {
            Map<String, Object> inner = null;
            for (AccountMoneyType value : AccountMoneyType.values()) {
                inner = new HashMap<>();
                inner.put("style", value.getStyle());
                inner.put("desc", value.getDescribe());
                MAP_PLUS.put(value.getType(), inner);
            }
            return MAP_PLUS;
        }
        public static AccountMoneyType get(String type){
            for (AccountMoneyType value : AccountMoneyType.values()) {
                if(Objects.equals(value.getType(),type)){
                    return value;
                }
            }
            return null;
        }
    }

    /**
     *  @Description 佣金状态
     *  0待入佣金
     *  1可提现佣金
     */
    public enum CommissionStatus{
        /**
         * 待入佣金
         */
        COMMISSION("0","待入佣金 ",BizConstants.style.INFO),
        /**
         * 可提现佣金
         */
        CASH("1","可提现佣金",BizConstants.style.SUCCESS),
        ;

        private String type;
        private String describe;
        private String style;
        CommissionStatus(String type, String describe,String style) {
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
            for (CommissionStatus value : CommissionStatus.values()) {
                SELF_MAP.put(value.getType(), value.getDescribe());
            }
            return SELF_MAP;
        }
        public static Map<String, Object> toMapPlus() {
            Map<String, Object> inner = null;
            for (CommissionStatus value : CommissionStatus.values()) {
                inner = new HashMap<>();
                inner.put("style", value.getStyle());
                inner.put("desc", value.getDescribe());
                MAP_PLUS.put(value.getType(), inner);
            }
            return MAP_PLUS;
        }
        public static CommissionStatus get(String type){
            for (CommissionStatus value : CommissionStatus.values()) {
                if(Objects.equals(value.getType(),type)){
                    return value;
                }
            }
            return null;
        }
    }
    /**
     *  @Description 佣金操作类型
     *  0:获得佣金 1:退货 2:转入余额
     */
    public enum CommissionOperation{
        /**
         * 获得佣金
         */
        GET("0","获得佣金 ",BizConstants.style.SUCCESS),
        /**
         * 退货佣金扣除
         */
        DEDUCT("1","退货佣金扣除",BizConstants.style.DANGER),
        /**
         * 转入余额
         */
        EXCHANGE("2","转入余额",BizConstants.style.INFO),
        ;

        private String type;
        private String describe;
        private String style;
        CommissionOperation(String type, String describe,String style) {
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
            for (CommissionOperation value : CommissionOperation.values()) {
                SELF_MAP.put(value.getType(), value.getDescribe());
            }
            return SELF_MAP;
        }
        public static Map<String, Object> toMapPlus() {
            Map<String, Object> inner = null;
            for (CommissionOperation value : CommissionOperation.values()) {
                inner = new HashMap<>();
                inner.put("style", value.getStyle());
                inner.put("desc", value.getDescribe());
                MAP_PLUS.put(value.getType(), inner);
            }
            return MAP_PLUS;
        }
        public static CommissionOperation get(String type){
            for (CommissionOperation value : CommissionOperation.values()) {
                if(Objects.equals(value.getType(),type)){
                    return value;
                }
            }
            return null;
        }
    }
    /**
     *  @Description 佣金操作类型
     *  描述:
     *  {}购买商品获得佣金
     *  {}退货清除佣金
     *  {}转入余额清除佣金
     */
    public interface CommissionDescribe{
        /**
         * 购买商品获得佣金
         */
        String GET_COMMISSION = "购买商品获得佣金 ";
        /**
         * 退货清除佣金
         */
        String RETURN_COMMISSION = "退货扣减佣金 ";
        /**
         * 转入余额清除佣金
         */
        String EXCHANGE_COMMISSION = "转入余额扣减佣金 ";
    }


    /**
     *  @Description 余额类型
     *  0佣金转入
     */
    public enum BalanceType{
        /**
         * 佣金转入
         */
        COMMISSION("0","佣金转入 ",BizConstants.style.INFO),
        ;

        private String type;
        private String describe;
        private String style;
        BalanceType(String type, String describe,String style) {
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
            for (BalanceType value : BalanceType.values()) {
                SELF_MAP.put(value.getType(), value.getDescribe());
            }
            return SELF_MAP;
        }
        public static Map<String, Object> toMapPlus() {
            Map<String, Object> inner = null;
            for (BalanceType value : BalanceType.values()) {
                inner = new HashMap<>();
                inner.put("style", value.getStyle());
                inner.put("desc", value.getDescribe());
                MAP_PLUS.put(value.getType(), inner);
            }
            return MAP_PLUS;
        }
        public static BalanceType get(String type){
            for (BalanceType value : BalanceType.values()) {
                if(Objects.equals(value.getType(),type)){
                    return value;
                }
            }
            return null;
        }
    }
    /**
     *  @Description 余额操作类型
     *  0:佣金转入 1:提现转出
     */
    public enum BalanceOperation{
        /**
         * 佣金转入
         */
        COMMISSION("0","佣金转入 ",BizConstants.style.SUCCESS),
        /**
         *  提现转出
         */
        CASH("1","提现转出",BizConstants.style.WARNING),
        ;

        private String type;
        private String describe;
        private String style;
        BalanceOperation(String type, String describe,String style) {
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
            for (BalanceOperation value : BalanceOperation.values()) {
                SELF_MAP.put(value.getType(), value.getDescribe());
            }
            return SELF_MAP;
        }
        public static Map<String, Object> toMapPlus() {
            Map<String, Object> inner = null;
            for (BalanceOperation value : BalanceOperation.values()) {
                inner = new HashMap<>();
                inner.put("style", value.getStyle());
                inner.put("desc", value.getDescribe());
                MAP_PLUS.put(value.getType(), inner);
            }
            return MAP_PLUS;
        }
        public static BalanceOperation get(String type){
            for (BalanceOperation value : BalanceOperation.values()) {
                if(Objects.equals(value.getType(),type)){
                    return value;
                }
            }
            return null;
        }
    }
    /**
     *  @Description 余额描述:，余额转出
     *  描述:
     *  佣金转入余额
     *  提现申请成功,余额转出
     */
    public interface BalanceDescribe{
        /**
         * 佣金转入余额
         */
        String COMMISSION_TO_BALANCE = "佣金转入余额";
        /**
         * 提现申请成功,余额转出
         */
        String BALANCE_TO_CASH = "提现申请成功,余额转出";
    }
}
