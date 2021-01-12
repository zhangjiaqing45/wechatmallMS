package com.fante.common.business.enums;

import com.fante.common.business.constant.BizConstants;
import com.fante.common.utils.StringUtils;
import com.google.common.collect.Lists;
import org.springframework.util.ObjectUtils;

import java.util.*;

/**
 * @program: Fante
 * @Date: 2020/2/21 17:22
 * @Author: Mr.Z
 * @Description: 退货状态
 */
public class ProductReturnConst {
    private ProductReturnConst(){}

    /**退货状态*/
    public enum ReturnStatus{
        PENDING("0","待处理",BizConstants.style.WARNING),
        RETURNING("1","退货中",BizConstants.style.INFO),
        COMPLETE("2","已完成",BizConstants.style.SUCCESS),
        REFUSE("3","已拒绝",BizConstants.style.DANGER)
        ;

        private String type;
        private String describe;
        private String style;
        ReturnStatus(String type, String describe,String style) {
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
            for (ReturnStatus value : ReturnStatus.values()) {
                SELF_MAP.put(value.getType(), value.getDescribe());
            }
            return SELF_MAP;
        }
        public static Map<String, Object> toMapPlus() {
            Map<String, Object> inner = null;
            for (ReturnStatus value : ReturnStatus.values()) {
                inner = new HashMap<>();
                inner.put("style", value.getStyle());
                inner.put("desc", value.getDescribe());
                MAP_PLUS.put(value.getType(), inner);
            }
            return MAP_PLUS;
        }
        public static ReturnStatus get(String type){
            for (ReturnStatus value : ReturnStatus.values()) {
                if(Objects.equals(value.getType(),type)){
                    return value;
                }
            }
            return null;
        }
    }
    /**退货状态*/
    public enum ReturnAction {

        PASS("pass", "确认退货",new String[]{
            ReturnStatus.PENDING.getType()
        }),
        REFUSE("refuse", "拒绝退货",new String[]{
            ReturnStatus.PENDING.getType()
        }),
        RECEIVE("receive", "确认收货",new String[]{
            ReturnStatus.RETURNING.getType()
        })
        ;
        private String type;
        private String describe;
        private String[] status;

        ReturnAction(String type, String describe,String[] status) {
            this.type = type;
            this.describe = describe;
            this.status = status;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }

        public String[] getStatus() {
            return status;
        }

        public void setStatus(String[] status) {
            this.status = status;
        }

        /**
         * 根据秒杀活动状态显示活动列表按钮
         * @param status
         * @return
         */
        public static List<String> getBtns(String status){
            if (StringUtils.isBlank(status)) {
                return Collections.emptyList();
            }
            List<String> btns = Lists.newArrayList();
            for (ReturnAction value : ReturnAction.values()) {
                if (Arrays.asList(value.getStatus()).contains(status)) {
                    btns.add(value.getType());
                }
            }
            return btns;
        }
    }
}
