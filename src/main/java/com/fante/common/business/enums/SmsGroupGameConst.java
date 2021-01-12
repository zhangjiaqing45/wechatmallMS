package com.fante.common.business.enums;

import com.fante.common.business.constant.BizConstants;
import com.fante.common.utils.StringUtils;

import java.util.*;

/**
 * @program: Fante
 * @Date: 2020/3/26 16:02
 * @Author: Mr.Z
 * @Description:
 */
public class SmsGroupGameConst {

    private SmsGroupGameConst() {
    }

    /**
     * @Author LiuQingyu
     * @Description 拼团活动记录状态（拼团中/拼团成功/拼团失败）
     * @Date 9:09 2020-03-16
     **/
    public enum RecordStatusEnum {

        /**
         * 拼团中
         */
        PROCESSING("0","拼团中",BizConstants.style.INFO),
        /**
         * 拼团成功
         */
        SUCCESS("1","拼团成功",BizConstants.style.SUCCESS),
        /**
         * 拼团失败
         */
        FAILURE("2","拼团失败",BizConstants.style.DANGER),
        ;
        private String type;
        private String describe;
        private String style;

        RecordStatusEnum(String type, String describe, String style) {
            this.type = type;
            this.describe = describe;
            this.style = style;
        }

        public String getType() {
            return type;
        }

        public String getDescribe() {
            return describe;
        }

        public String getStyle() {
            return style;
        }

        private static final Map<String, Object> SELF_MAP = new LinkedHashMap<>();
        private static final Map<String, Object> MAP_PLUS = new LinkedHashMap<>();

        public static Map<String, Object> toMap() {
            for (RecordStatusEnum value : RecordStatusEnum.values()) {
                SELF_MAP.put(value.getType(), value.getDescribe());
            }
            return SELF_MAP;
        }
        public static Map<String, Object> toMapPlus() {
            Map<String, Object> inner = null;
            for (RecordStatusEnum value : RecordStatusEnum.values()) {
                inner = new HashMap<>();
                inner.put("style", value.getStyle());
                inner.put("desc", value.getDescribe());
                MAP_PLUS.put(value.getType(), inner);
            }
            return MAP_PLUS;
        }
    }
    /**
     * @Description 拼团活动操作枚举
     **/
    public enum GroupActionEnum {
        /**
         * 开始活动
         */
        START("start","开启活动",new String[] {
                GameStatusEnum.TOBELISTED.getType()
        }),
        /**
         * 开始活动
         */
        EDIT("edit","编辑活动",new String[] {
                GameStatusEnum.TOBELISTED.getType(),
        }),
        /**
         * 结束活动
         */
        STOP("stop","结束活动",new String[] {
                GameStatusEnum.LISTED.getType()
        }),
        /**
         * 查看详情
         */
        DETAIL("detail","查看详情",new String[] {
                GameStatusEnum.TOBELISTED.getType(),
                GameStatusEnum.LISTED.getType(),
                GameStatusEnum.CANCELLISTED.getType()
        }),
        /**
         * 删除活动
         */
        DEL("del","删除活动",new String[] {
                GameStatusEnum.TOBELISTED.getType()
        }),
        ;

        private String type;
        private String describe;
        private String[] gameStatus;

        GroupActionEnum(String type, String describe, String[] gameStatus) {
            this.type = type;
            this.describe = describe;
            this.gameStatus = gameStatus;
        }

        public String getType() {
            return type;
        }

        public String getDescribe() {
            return describe;
        }

        public String[] getGameStatus() {
            return gameStatus;
        }

        private static final Map<String, Object> ACTIVE_MAP = new LinkedHashMap<>();

        public static Map<String, Object> toMap() {
            for (GroupActionEnum value : GroupActionEnum.values()) {
                ACTIVE_MAP.put(value.getType(), value.getDescribe());
            }
            return ACTIVE_MAP;
        }
        /**
         * 根据上架状态获取操作按钮
         * @param status
         * @return
         */
        public static List<String> getBtns(String status) {
            if (StringUtils.isBlank(status)) {
                return Collections.emptyList();
            }
            List<String> result = new ArrayList<>();
            for (GroupActionEnum value : GroupActionEnum.values()) {
                if (Arrays.asList(value.getGameStatus()).contains(status)) {
                    result.add(value.getType());
                }
            }
            return result;
        }
    }
    /**
     * @Description 团购活动状态（待上架/已上架/已下架）
     * @Date 10:15 2020-03-14
     **/
    public enum GameStatusEnum {

        /**
         * 未开始
         */
        TOBELISTED("0","未开始", BizConstants.style.WARNING),
        /**
         * 进行中
         */
        LISTED("1","进行中",BizConstants.style.SUCCESS),
        /**
         * 已结束
         */
        CANCELLISTED("2","已结束",BizConstants.style.DANGER)
        ;

        private String type;
        private String describe;
        private String style;

        GameStatusEnum(String type, String describe, String style) {
            this.type = type;
            this.describe = describe;
            this.style = style;
        }

        public String getType() {
            return type;
        }

        public String getDescribe() {
            return describe;
        }

        public String getStyle() {
            return style;
        }

        private static final Map<String, Object> MAP_PLUS = new LinkedHashMap<>();
        private static final Map<String, Object> MAP = new LinkedHashMap<>();
        private static final Map<String, Object> MAP_CHECK = new LinkedHashMap<>();

        private static final List<GameStatusEnum> EDITABLE = Arrays.asList(CANCELLISTED);

        /**
         * log 中显示的按钮 不包括 detail详情查看
         * @return
         */
        public static GameStatusEnum getEnumByType(String type) {
            for (GameStatusEnum value : GameStatusEnum.values()) {
                if(StringUtils.equals(type,value.getType())){
                   return value;
                }
            }
            return null;
        }

        /**
         * log 中显示的按钮 不包括 detail详情查看
         * @return
         */
        public static Map<String, Object> toMapOfCheck() {
            for (GameStatusEnum value : GameStatusEnum.values()) {
                if(!EDITABLE.contains(value)){
                    MAP_CHECK.put(value.getType(), value.getDescribe());
                }
            }
            return MAP_CHECK;
        }

        public static Map<String, Object> toMap() {
            for (GameStatusEnum value : GameStatusEnum.values()) {
                MAP.put(value.getType(), value.getDescribe());
            }
            return MAP;
        }

        public static Map<String, Object> toMapPlus() {
            Map<String, Object> inner = null;
            for (GameStatusEnum value : GameStatusEnum.values()) {
                inner = new HashMap<>();
                inner.put("style", value.getStyle());
                inner.put("desc", value.getDescribe());
                MAP_PLUS.put(value.getType(), inner);
            }
            return MAP_PLUS;
        }

    }

    /**拼团人数*/
    public enum GroupPersonCountLimit{
        /**最小值*/
        MIN(2,"最小值"),
        /**最大值*/
        MAX(5,"最大值");

        private int type;
        private String describe;

        GroupPersonCountLimit(int type, String describe) {
            this.type = type;
            this.describe = describe;
        }

        public int getType() {
            return type;
        }

        public String getDescribe() {
            return describe;
        }

        private static final Map<Integer, Object> SELF_MAP = new LinkedHashMap<>();

        public static Map<Integer, Object> toMap() {
            for (GroupPersonCountLimit value : GroupPersonCountLimit.values()) {
                SELF_MAP.put(value.getType(), value.getDescribe());
            }
            return SELF_MAP;
        }
        public static GroupPersonCountLimit get(Integer type){
            for (GroupPersonCountLimit value : GroupPersonCountLimit.values()) {
                if(Objects.equals(value.getType(),type)){
                    return value;
                }
            }
            return null;
        }
    }
}
