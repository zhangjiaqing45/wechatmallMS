package com.fante.common.business.enums;

import com.fante.common.business.constant.BizConstants;
import com.fante.common.utils.StringUtils;

import java.util.*;

/**
 * @program: Fante
 * @Date: 2020/2/21 17:22
 * @Author: Mr.Z
 * @Description: 商品相关设置
 */
public class ProductAttributeCategoryConst {
    private ProductAttributeCategoryConst(){}
    /**选择方式*/
    public enum CheckMethodEnum{
        /**
         * 单选
         */
        SELECT_ONE("1", "单选"),
        /**
         * 复选
         */
        SELECT_MORE("2", "复选"),
        ;

        private String type;
        private String describe;

        CheckMethodEnum(String type, String describe) {
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
            for (CheckMethodEnum value : CheckMethodEnum.values()) {
                SELF_MAP.put(value.getType(), value.getDescribe());
            }
            return SELF_MAP;
        }
    }

    /**录入方式*/
    public enum EntryMethodEnum{
        /**
         * 从列表中选取
         */
        SELECT_ENTRY("1", "从列表中选取"),

        /**
         * 手动录入
         */
        MANUAL_ENTRY("0", "手动录入"),
        ;
        private String type;
        private String describe;

        EntryMethodEnum(String type, String describe) {
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
            for (EntryMethodEnum value : EntryMethodEnum.values()) {
                SELF_MAP.put(value.getType(), value.getDescribe());
            }
            return SELF_MAP;
        }
    }

    /**是否支持手动新增*/
    public enum SupportManualEnum{
        /**
         * 支持
         */
        SUPPORT_YES("0", "支持"),
        /**
         * 不支持
         */
        SUPPORT_NO("1", "不支持"),
        ;

        private String type;
        private String describe;

        SupportManualEnum(String type, String describe) {
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
            for (SupportManualEnum value : SupportManualEnum.values()) {
                SELF_MAP.put(value.getType(), value.getDescribe());
            }
            return SELF_MAP;
        }
    }

    /**是否支持手动新增*/
    public enum ProductAttrType{
        /**
         * 参数列表
         */
        PARAMETER("0", "参数列表"),
        /**
         * 属性列表
         */
        ATTR("1", "属性列表"),
        ;

        private String type;
        private String describe;

        ProductAttrType(String type, String describe) {
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
            for (ProductAttrType value : ProductAttrType.values()) {
                SELF_MAP.put(value.getType(), value.getDescribe());
            }
            return SELF_MAP;
        }

        /**
         *  包含则返回,否则返回""
         * @param code
         * @return
         */
        public static ProductAttrType get(String code){
            for (ProductAttrType value : ProductAttrType.values()) {
                if(StringUtils.equals(value.getType(),code)){
                    return value;
                }
            }
            return null;
        }
    }

    /**
     * 商品上/下架状态
     */
    public enum publicStatusEnum {

        /**
         * 商品下架
         */
        SOLDOUT("0", "下架", BizConstants.style.WARNING),
        /**
         * 商品上架
         */
        PUTAWAY("1", "上架", BizConstants.style.PRIMARY),
        ;


        private String type;
        private String describe;
        private String style;

        publicStatusEnum(String type, String describe, String style) {
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

        private static final Map<String, Object> ALL_MAP = new LinkedHashMap<>();

        public static Map<String, Object> toAllMap() {
            for (publicStatusEnum value : publicStatusEnum.values()) {
                ALL_MAP.put(value.getType(), value.getDescribe());
            }
            return ALL_MAP;
        }

        private static final Map<String, Object> ALL_MAP_PLUS = new LinkedHashMap<>();

        public static Map<String, Object> toAllMapPlus() {
            Map<String, Object> inner = null;
            for (publicStatusEnum value : publicStatusEnum.values()) {
                inner = new HashMap<>();
                inner.put("style", value.getStyle());
                inner.put("desc", value.getDescribe());
                ALL_MAP_PLUS.put(value.getType(), inner);
            }
            return ALL_MAP_PLUS;
        }
    }

    /**
     * 积分商品操作
     */
    public enum IntegralProductBtnEnum {

        /**
         * 编辑按钮
         */
        BTN_EIDT("btn-eidt", "编辑", new String[] {publicStatusEnum.SOLDOUT.getType()} ),
        /**
         * 上架按钮
         */
        BTN_PUTAWAY("btn-putaway", "上架", new String[] {publicStatusEnum.SOLDOUT.getType()}),
        /**
         * 下架按钮
         */
        BTN_SOLDOUT("btn-soldout", "下架", new String[] {publicStatusEnum.PUTAWAY.getType()}),
        /**
         * 删除按钮
         */
        BTN_DELETE("btn-delete", "删除", new String[] {publicStatusEnum.SOLDOUT.getType()}),
        ;
        ;
        private String type;
        private String describe;
        private String[] publishStatus;

        IntegralProductBtnEnum(String type, String describe, String[] publishStatus) {
            this.type = type;
            this.describe = describe;
            this.publishStatus = publishStatus;
        }

        public String getType() {
            return type;
        }

        public String getDescribe() {
            return describe;
        }

        public String[] getPublishStatus() {
            return publishStatus;
        }

        /**
         * 根据上架状态获取操作按钮
         * @param publishStatus
         * @return
         */
        public static List<String> getBtns(String publishStatus) {
            if (StringUtils.isBlank(publishStatus)) {
                return Collections.emptyList();
            }
            List<String> result = new ArrayList<>();
            for (IntegralProductBtnEnum value : IntegralProductBtnEnum.values()) {
                if (Arrays.asList(value.getPublishStatus()).contains(publishStatus)) {
                    result.add(value.getType());
                }
            }
            return result;
        }
    }


    /**
     * 操作
     */
    public enum ActivityEnum {
        // 按钮 待创建    待审核    通过           拒绝    -->  0 1 2 3
        // 审核   O         X        O             X
        // 上架   X         X        O             X
        // 下架   X         X        O             X
        // 详情   O         O        O             O
        // 编辑   O         X        O->待创建     O
        // 删除   O         X        O             O
        //DEL  AUDIT PUTAWAY SOLDOUT EDIT DETAIL
        //待创建
        //1.只要编辑 商品后 审核状态都改为 待创建
        //2.通过后 编辑 提示需要重新申请 -->待创建
        //3.审核失败的商品必须 重新编辑后 才可 再次提交审核
        //4.上架 不显示下架 下架不显示上架
        SORT("sort","修改排序",new String[]{
                AuditTypeEnum.CREATE.getType(),
                AuditTypeEnum.SUCCESS.getType(),
                AuditTypeEnum.WAIT.getType(),
                AuditTypeEnum.FAIL.getType(),
        },new String[]{
                publicStatusEnum.PUTAWAY.getType(),
                publicStatusEnum.SOLDOUT.getType(),
        }),
        LOWSTOCK("lowStock","修改预警库存",new String[]{
                AuditTypeEnum.CREATE.getType(),
                AuditTypeEnum.WAIT.getType(),
                AuditTypeEnum.SUCCESS.getType(),
                AuditTypeEnum.FAIL.getType(),
        },new String[]{
                publicStatusEnum.PUTAWAY.getType(),
                publicStatusEnum.SOLDOUT.getType(),
        }),
        SKUPRICE("skuPrice","修改sku价格",new String[]{
                AuditTypeEnum.CREATE.getType(),
                AuditTypeEnum.SUCCESS.getType(),
                AuditTypeEnum.FAIL.getType(),
                AuditTypeEnum.WAIT.getType(),
        },new String[]{
                publicStatusEnum.SOLDOUT.getType(),
                publicStatusEnum.PUTAWAY.getType(),
        }),
        PRICE("price","修改主页价格",new String[]{
                AuditTypeEnum.CREATE.getType(),
                AuditTypeEnum.SUCCESS.getType(),
                AuditTypeEnum.FAIL.getType(),
        },new String[]{
                publicStatusEnum.SOLDOUT.getType(),
                publicStatusEnum.PUTAWAY.getType(),
        }),
        ORIGINALPRICE("originalPrice","修改主页市场价",new String[]{
                AuditTypeEnum.CREATE.getType(),
                AuditTypeEnum.SUCCESS.getType(),
                AuditTypeEnum.FAIL.getType(),
        },new String[]{
                publicStatusEnum.PUTAWAY.getType(),
                publicStatusEnum.SOLDOUT.getType(),
        }),
        EXPORT("export","商品导出",new String[]{
                AuditTypeEnum.CREATE.getType(),
                AuditTypeEnum.WAIT.getType(),
                AuditTypeEnum.SUCCESS.getType(),
                AuditTypeEnum.FAIL.getType(),
        },new String[]{
                publicStatusEnum.PUTAWAY.getType(),
                publicStatusEnum.SOLDOUT.getType(),
        }),
        COMMISSION("commission","修改佣金",new String[]{
                AuditTypeEnum.CREATE.getType(),
                AuditTypeEnum.WAIT.getType(),
                AuditTypeEnum.SUCCESS.getType(),
                AuditTypeEnum.FAIL.getType(),
        },new String[]{
                publicStatusEnum.PUTAWAY.getType(),
                publicStatusEnum.SOLDOUT.getType(),
        }),
        ADD("add","添加商品",new String[]{
                AuditTypeEnum.CREATE.getType(),
                AuditTypeEnum.WAIT.getType(),
                AuditTypeEnum.SUCCESS.getType(),
                AuditTypeEnum.FAIL.getType(),
        },new String[]{
                publicStatusEnum.PUTAWAY.getType(),
                publicStatusEnum.SOLDOUT.getType(),
        }),
        STOCK("stock","修改库存",new String[]{
                AuditTypeEnum.CREATE.getType(),
                AuditTypeEnum.SUCCESS.getType(),
                AuditTypeEnum.FAIL.getType(),
                AuditTypeEnum.WAIT.getType(),
        },new String[]{
                publicStatusEnum.SOLDOUT.getType(),
                publicStatusEnum.PUTAWAY.getType(),
        }),
        DEL("del","删除商品", new String[]{
                AuditTypeEnum.CREATE.getType(),
                AuditTypeEnum.SUCCESS.getType(),
                AuditTypeEnum.FAIL.getType()
        },new String[]{
                publicStatusEnum.SOLDOUT.getType(),
        }),
        //审核按钮
        AUDIT("audit","提交审核", new String[]{
                AuditTypeEnum.CREATE.getType(),
        },new String[]{
                publicStatusEnum.SOLDOUT.getType(),
        }),
        //上架按钮
        PUTAWAY("putAway","商品上架", new String[]{
                AuditTypeEnum.SUCCESS.getType()
        },new String[]{
                publicStatusEnum.SOLDOUT.getType(),
        }),
        //下架按钮
        SOLDOUT("soldOut","商品下架", new String[]{
                AuditTypeEnum.SUCCESS.getType()
        },new String[]{
                publicStatusEnum.PUTAWAY.getType(),
        }),
        //编辑按钮
        EDIT("edit", "商品编辑",new String[]{
                AuditTypeEnum.CREATE.getType(),
                AuditTypeEnum.SUCCESS.getType(),
                AuditTypeEnum.FAIL.getType(),
        },new String[]{
                publicStatusEnum.SOLDOUT.getType(),
        }),
        //详情按钮
        DETAIL("detail","查看详情", new String[]{
                AuditTypeEnum.CREATE.getType(),
                AuditTypeEnum.WAIT.getType(),
                AuditTypeEnum.SUCCESS.getType(),
                AuditTypeEnum.FAIL.getType(),
        },new String[]{
                publicStatusEnum.PUTAWAY.getType(),
                publicStatusEnum.SOLDOUT.getType(),
        })
        ;


        private String type;
        private String describe;

        private String[] auditStatus;
        private String[] publishStatus;

        ActivityEnum(String type, String describe, String[] auditStatus, String[] publishStatus) {
            this.type = type;
            this.auditStatus = auditStatus;
            this.publishStatus = publishStatus;
            this.describe = describe;
        }

        public String getType() {
            return type;
        }

        public String getDescribe() {
            return describe;
        }

        public String[] getAuditStatus() {
            return auditStatus;
        }

        public String[] getPublishStatus() {
            return publishStatus;
        }


        /**
         * 根据 商品的审核状态 和 上架状态 获取 可操作按钮集合
         * @param auditStatus
         * @param publishStatus
         * @return
         */
        public static List<String> getBtns(String auditStatus,String publishStatus) {

            if(StringUtils.isAnyBlank(auditStatus,publishStatus)){
                return  Collections.emptyList();
            }
            List<String>  result = new ArrayList<>();
            for (ActivityEnum value : ActivityEnum.values()) {

                if(Arrays.asList(value.getAuditStatus()).contains(auditStatus) && Arrays.asList(value.getPublishStatus()).contains(publishStatus)) {
                    result.add(value.getType());
                }
            }
            return result;
        }
        /**
         *log页面 中显示的按钮 不包括 detail详情查看
         */
        private static final List<ActivityEnum> EDITABLE = Arrays.asList(DETAIL);

        private static final Map<String, Object> SELF_MAP = new LinkedHashMap<>();
        /**
         * log 中显示的按钮 不包括 detail详情查看
         * @return
         */
        public static Map<String, Object> toMap() {
            for (ActivityEnum value : ActivityEnum.values()) {
                if(!EDITABLE.contains(value)){
                    SELF_MAP.put(value.getType(), value.getDescribe());
                }
            }
            return SELF_MAP;
        }
    }

}
