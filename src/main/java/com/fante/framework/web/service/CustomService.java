package com.fante.framework.web.service;

import com.fante.common.business.enums.*;
import com.fante.common.constant.Constants;
import com.fante.common.utils.security.ShiroUtils;
import com.fante.project.business.cmsTopicCategory.domain.CmsTopicCategory;
import com.fante.project.business.cmsTopicCategory.service.ICmsTopicCategoryService;
import com.fante.project.business.pmsBrand.domain.PmsBrand;
import com.fante.project.business.pmsBrand.service.IPmsBrandService;
import com.fante.project.business.pmsFeightTemplate.domain.PmsFeightTemplate;
import com.fante.project.business.pmsFeightTemplate.service.IPmsFeightTemplateService;
import com.fante.project.business.pmsIntegralProductCategory.domain.PmsIntegralProductCategory;
import com.fante.project.business.pmsIntegralProductCategory.service.IPmsIntegralProductCategoryService;
import com.fante.project.business.pmsProductAttributeCategory.domain.PmsProductAttributeCategory;
import com.fante.project.business.pmsProductAttributeCategory.service.IPmsProductAttributeCategoryService;
import com.fante.project.business.pmsProductCategory.domain.PmsProductCategory;
import com.fante.project.business.pmsProductCategory.domain.PmsProductCategoryDetail;
import com.fante.project.business.pmsProductCategory.service.IPmsProductCategoryService;
import com.fante.project.business.pmsShopCategoryRelation.domain.PmsShopCategoryRelationResult;
import com.fante.project.business.pmsShopCategoryRelation.service.IPmsShopCategoryRelationService;
import com.fante.project.weixin.core.config.enums.SubscribeSceneEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @program: Fante
 * @Date:
 * @Author: Mr.Z
 * @Description: 自定义设置读取
 */
@Service("custom")
public class CustomService {
    /**
     * 商品品牌
     */
    @Autowired
    private IPmsBrandService iPmsBrandService;
    /**
     * 店铺商品分类
     */
    @Autowired
    private IPmsShopCategoryRelationService iPmsShopCategoryRelationService;
    /**
     * 平台商品分类
     */
    @Autowired
    private IPmsProductCategoryService iPmsProductCategoryService;
    /**
     * 商品属性类型
     */
    @Autowired
    private IPmsProductAttributeCategoryService iPmsProductAttributeCategoryService;

    /**
     * 话题分类
     */
    @Autowired
    private ICmsTopicCategoryService cmsTopicCategoryService;

    /**
     * 积分商品分类
     */
    @Autowired
    private IPmsIntegralProductCategoryService pmsIntegralProductCategoryService;

    /**
     * 运费模板
     */
    @Autowired
    private IPmsFeightTemplateService pmsFeightTemplateService;


    /**
     * 常用设置-通用状态
     * @return
     */
    public Map<String, Object> commonUseStatus() {
        return CommonUse.Status.toMap();
    }

    /**
     * 常用设置-通用状态（包含样式）
     * @return
     */
    public Map<String, Object> commonUseStatusAll() {
        return CommonUse.Status.toAllMap();
    }


    /**
     * 支付日志类型
     *
     * @return
     */
    public Map<String, Object> wxPayLogTypes() {
        return WxPayLogTypeEnum.toMap();
    }


    /**
     * 用户类型设置
     *
     * @return
     * @see UserTypeEnum
     */
    public Map<String, Object> userTypes() {
        return UserTypeEnum.toMap();
    }

    /**
     * 用户类型设置-更多
     *
     * @return
     * @see UserTypeEnum
     */
    public Map<String, Object> userTypesMore() {
        return UserTypeEnum.toMapMore();
    }


    /**
     * 审核状态配置
     *
     * @return
     * @see AuditTypeEnum
     */
    public Map<String, Object> auditTypes() {
        return AuditTypeEnum.toMap();
    }

    /**
     * 所有审核状态显示
     *
     * @return
     * @see AuditTypeEnum
     */
    public Map<String, Object> allAuditTypes() {
        return AuditTypeEnum.toAllMap();
    }

    /**
     * 所有审核状态（包括样式）
     *
     * @return
     */
    public Map<String, Object> allAuditTypesPlus() {
        return AuditTypeEnum.toAllMapPlus();
    }

    /**
     * 公众号是否关注
     *
     * @return
     */
    public Map<String, Object> memberSubscribe() {
        return UmsMemberConst.SubscribeEnum.toMap();
    }

    /**
     * 公众号是否关注（包括样式）
     *
     * @return
     */
    public Map<String, Object> memberSubscribeAll() {
        return UmsMemberConst.SubscribeEnum.toAllMap();
    }

    /**
     * 用户来源
     *
     * @return
     */
    public Map<String, Object> memberSource() {
        return UmsMemberConst.SourceEnum.toMap();
    }

    /**
     * 用户来源（包括样式）
     *
     * @return
     */
    public Map<String, Object> memberSourceAll() {
        return UmsMemberConst.SourceEnum.toAllMap();
    }

    /**
     * 公众号用户关注的渠道来源
     *
     * @return
     */
    public Map<String, Object> wxSubscribeScene() {
        return SubscribeSceneEnum.toMap();
    }

    /**
     * 签到类型
     *
     * @return
     */
    public Map<String, Object> signType() {
        return SmsSignConst.SignType.toMap();
    }

    /**
     * 签到类型(包含样式)
     *
     * @return
     */
    public Map<String, Object> signTypeAll() {
        return SmsSignConst.SignType.toAllMap();
    }

    /**
     * 团购活动状态
     *
     * @return
     */
    public Map<String, Object> groupGameStatus() {
        return SmsGroupGameConst.GameStatusEnum.toMap();
    }
    /**
     * 团购活动状态(可选项)
     *
     * @return
     */
    public Map<String, Object> groupGameStatusForCheck() {
        return SmsGroupGameConst.GameStatusEnum.toMapOfCheck();
    }
    /**
     * 团购活动状态(包含样式)
     *
     * @return
     */
    public Map<String, Object> groupGameStatusPlus() {
        return SmsGroupGameConst.GameStatusEnum.toMapPlus();
    }

    /**
     * 团购活动记录状态
     *
     * @return
     */
    public Map<String, Object> groupRecordStatus() {
        return SmsGroupGameConst.RecordStatusEnum.toMap();
    }
    /**
     * 团购活动记录状态包含样式
     *
     * @return
     */
    public Map<String, Object> groupRecordStatusPlus() {
        return SmsGroupGameConst.RecordStatusEnum.toMapPlus();
    }

    /**
     * 秒杀活动状态
     * @return
     */
    public Map<String, Object> smsFlashStatus() {
        return SmsFlashConst.StatusEnum.toMap();
    }

    /**
     * 秒杀活动状态(包含样式)
     * @return
     */
    public Map<String, Object> smsFlashStatusPlus() {
        return SmsFlashConst.StatusEnum.toAllMap();
    }

    /**
     * 推荐商品类型
     *
     * @return
     */
    public Map<String, Object> recommendProductType() {
        return SmsRecommendConst.ProductType.toMap();
    }

    /**
     * 优惠券类型
     * 0.满减 1 折扣
     *
     * @return
     */
    public Map<String, Object> couponType() {
        return SmsCouponConst.CouponTypeEnum.toMap();
    }

    /**
     * 优惠券类型（包括样式）
     * 0.满减 1 折扣
     * @return
     */
    public Map<String, Object> couponTypePlus() {
        return SmsCouponConst.CouponTypeEnum.toAllMap();
    }
    /**
     * 会员等级
     *
     * @return
     */
    public Map<String, Object> memberLevel() {
        return SmsCouponConst.MemberLevelEnum.toMap();
    }

    /**
     * 会员等级
     *
     * @return
     */
    public Map<String, Object> memberLevelPlus() {
        return SmsCouponConst.MemberLevelEnum.toAllMap();
    }
    /**
     * 优惠券获取类型
     *1.主动领取 2商家赠送
     * @return
     */
    public Map<String, Object> couponGetType() {
        return SmsCouponConst.getTypeEnum.toMap();
    }

    /**
     * 优惠券类型（包括样式）
     *1.主动领取 2商家赠送
     * @return
     */
    public Map<String, Object> couponGetTypePlus() {
        return SmsCouponConst.getTypeEnum.toAllMap();
    }

    /**
     * 优惠券状态
     *
     * @return
     */
    public Map<String, Object> couponStatus() {
        return SmsCouponConst.StatusEnum.toMap();
    }

    /**
     * 优惠券状态（包括样式）
     *
     * @return
     */
    public Map<String, Object> couponStatusPlus() {
        return SmsCouponConst.StatusEnum.toAllMap();
    }

    /**
     * 优惠券使用范围
     *
     * @return
     */
    public Map<String, Object> couponUseType() {
        return SmsCouponConst.useTypeEnum.toMap();
    }

    /**
     * 优惠券使用范围（包括样式）
     *
     * @return
     */
    public Map<String, Object> couponUseTypeAll() {
        return SmsCouponConst.useTypeEnum.toAllMap();
    }

    /**
     * 优惠券使用状态
     *
     * @return
     */
    public Map<String, Object> couponUseStatus() {
        return SmsCouponConst.useStatusEnum.toMap();
    }

    /**
     * 优惠券使用状态（包括样式）
     *
     * @return
     */
    public Map<String, Object> couponUseStatusPlus() {
        return SmsCouponConst.useStatusEnum.toAllMap();
    }

    /**
     * 选择方式
     *
     * @return
     */
    public Map<String, Object> checkMethod() {
        return ProductAttributeCategoryConst.CheckMethodEnum.toMap();
    }

    /**
     * 录入方式
     *
     * @return
     */
    public Map<String, Object> entryMethod() {
        return ProductAttributeCategoryConst.EntryMethodEnum.toMap();
    }

    /**
     * 是否支持手动新增
     *
     * @return
     */
    public Map<String, Object> supportManual() {
        return ProductAttributeCategoryConst.SupportManualEnum.toMap();
    }

    /**
     * 品牌查询
     */
    public List<PmsBrand> getPmsBrand() {
        PmsBrand pmsBrand = new PmsBrand();
        //过滤平台禁用的
        pmsBrand.setShowStatus(0);
        return iPmsBrandService.selectPmsBrandList(pmsBrand);
    }

    /**
     * 查询状态为展示的商品分类
     * 条件
     * 1.平台设置展示
     * 2.店铺设置展示
     *
     * @return
     */
    public List<PmsShopCategoryRelationResult> getPmsCategory() {
        return iPmsShopCategoryRelationService.selectPmsCategoryListForAddPms();
    }

    /**
     * 获取所有的商品分类
     * 条件
     * @return
     */
    public List<PmsProductCategory> getAllPmsCategory() {
        return iPmsProductCategoryService.selectPmsProductCategoryList(new PmsProductCategory());
    }
    
    /**
     * 获取所有的商品分类
     * 条件
     * @return
     */
    public List<PmsProductCategoryDetail> getFristPmsCategory() {
        Map<String, Object> param = new HashMap<>();
        param.put("level", "0");
        return iPmsProductCategoryService.getPmsProductCategoryList(param);
    }

    /**
     * 商品属性类型
     *
     * @return
     */
    public List<PmsProductAttributeCategory> getPmsAttrCategory() {
        PmsProductAttributeCategory category = new PmsProductAttributeCategory();
        category.setShopId(ShiroUtils.getSysUser().getDeptId());
        return iPmsProductAttributeCategoryService.selectPmsProductAttributeCategoryList(category);
    }

    /**
     * 话题分类
     *
     * @return
     */
    public List<CmsTopicCategory> cmsTopicCategories() {
        List<CmsTopicCategory> list =  cmsTopicCategoryService.selectCmsTopicCategoryList(null);
        list.forEach(category -> {
            if (Objects.equals(CommonUse.Status.DISABLE.getType(), category.getStatus())) {
                category.setName(category.getName() + "(" + CommonUse.Status.DISABLE.getDescribe() + ")");
            }
        });
        return list;
    }

    /**
     * 积分商品分类
     *
     * @return
     */
    public List<PmsIntegralProductCategory> pmsIntegralProductCategories() {
        List<PmsIntegralProductCategory> list = pmsIntegralProductCategoryService.selectPmsIntegralProductCategoryList(null);
        list.forEach(pmsIntegralProductCategory -> {
            if (Objects.equals(CommonUse.Status.DISABLE.getType(), pmsIntegralProductCategory.getShowStatus())) {
                pmsIntegralProductCategory.setName(pmsIntegralProductCategory.getName() + "(" + CommonUse.Status.DISABLE.getDescribe() + ")");
            }
        });
        return list;
    }


    /**
     * 商品上/下架状态
     *
     * @return
     * @see ProductAttributeCategoryConst.publicStatusEnum
     */
    public Map<String, Object> allPublicStatus() {
        return ProductAttributeCategoryConst.publicStatusEnum.toAllMap();
    }

    /**
     * 商品上/下架状态（包括样式）
     *
     * @return
     * @see ProductAttributeCategoryConst.publicStatusEnum
     */
    public Map<String, Object> allPublicStatusPlus() {
        return ProductAttributeCategoryConst.publicStatusEnum.toAllMapPlus();
    }

    /**
     * 退货状态
     *
     * @return
     */
    public Map<String, Object> returnStatus() {
        return ProductReturnConst.ReturnStatus.toMap();
    }

    /**
     * 退货状态（包括样式）
     *
     * @return
     */
    public Map<String, Object> returnStatusPlus() {
        return ProductReturnConst.ReturnStatus.toMapPlus();
    }
    /**
     * 商品操作日志的所有动作
     *
     * @return
     * @see ProductAttributeCategoryConst.ActivityEnum
     */
    public Map<String, Object> getProductHandleLog() {
        return ProductAttributeCategoryConst.ActivityEnum.toMap();
    }

    /**
     * 运费模板
     *
     * @return
     */
    public List<PmsFeightTemplate> feightTemplates() {
        return pmsFeightTemplateService.selectAvailableList();
    }

    /**
     * 订单类型
     *
     * @return
     */
    public Map<String, Object> getOrderType() {
        return OrderConst.OrderType.toMap();
    }
    /**
     * 订单状态
     *
     * @return
     */
    public Map<String, Object> getOrderStatus() {
        return OrderConst.OrderStatus.toMap();
    }
    /**
     * 发票类型
     *
     * @return
     */
    public Map<String, Object> getBillType() {
        return OrderConst.BillType.toMap();
    }
    /**
     * 发票类型(不包含 不开发票选项)
     *
     * @return
     */
    public Map<String, Object> getBillTypeToCheck() {
        return OrderConst.BillType.toMapForCheck();
    }
    /**
     * 备注标记
     *
     * @return
     */
    public Map<String, Object> getRemarkFlagPlus() {
        return OrderConst.RemarkFlag.toMapPlus();
    }
    /**
     * 付款
     *
     * @return
     */
    public Map<String, Object> getPayStatus() {
        return OrderConst.PayStatus.toMap();
    }
    /**
     * 发货状态
     *
     * @return
     */
    public Map<String, Object> getSendStatus() {
        return OrderConst.SendStatus.toMap();
    }

    /**
     * 广告位置
     * @return
     */
    public Map<String, Object> advertisePosition() {
        return SmsAdvertiseConst.Position.toMap();
    }

    /**
     * 广告位置（包括样式）
     * @return
     */
    public Map<String, Object> advertisePositionAll() {
        return SmsAdvertiseConst.Position.toAllMap();
    }

    /**
     * 广告链接类型
     * @return
     */
    public Map<String, Object> advertiseUrlType() {
        return SmsAdvertiseConst.UrlType.toMap();
    }

    /**
     * 广告链接类型（包括样式）
     * @return
     */
    public Map<String, Object> advertiseUrlTypeAll() {
        return SmsAdvertiseConst.UrlType.toAllMap();
    }

    /**
     * 前端导航链接类型
     * @return
     */
    public Map<String, Object> navLinkType() {
        return CmsAppNavConst.NavLinkType.toMap();
    }

    /**
     * 前端导航链接类型（包括样式）
     * @return
     */
    public Map<String, Object> navLinkTypeAll() {
        return CmsAppNavConst.NavLinkType.toAllMap();
    }

    /**
     * 前端导航显示范围
     * @return
     */
    public Map<String, Object> navShowType() {
        return CmsAppNavConst.NavShowType.toMap();
    }

    /**
     * 前端导航显示范围（包括样式）
     * @return
     */
    public Map<String, Object> navShowTypeAll() {
        return CmsAppNavConst.NavShowType.toAllMap();
    }

    /**
     * 用户现金余额操作类型
     * @return
     */
    public Map<String, Object> balanceOperation() {
        return AccRecordConst.BalanceOperation.toMap();
    }

    /**
     * 用户现金余额操作类型（包括样式）
     * @return
     */
    public Map<String, Object> balanceOperationAll() {
        return AccRecordConst.BalanceOperation.toMapPlus();
    }

    /**
     * 用户佣金状态
     * @return
     */
    public Map<String, Object> commissionStatus() {
        return AccRecordConst.CommissionStatus.toMap();
    }

    /**
     * 用户佣金状态（包括样式）
     * @return
     */
    public Map<String, Object> commissionStatusAll() {
        return AccRecordConst.CommissionStatus.toMapPlus();
    }

    /**
     * 用户佣金操作类型
     * @return
     */
    public Map<String, Object> commissionOperation() {
        return AccRecordConst.CommissionOperation.toMap();
    }

    /**
     * 用户佣金操作类型（包括样式）
     * @return
     */
    public Map<String, Object> commissionOperationAll() {
        return AccRecordConst.CommissionOperation.toMapPlus();
    }

    /**
     * 店铺账户金额类型
     * @return
     */
    public Map<String, Object> accountMoneyType() {
        return AccRecordConst.AccountMoneyType.toMap();
    }

    /**
     * 店铺账户金额类型（包括样式）
     * @return
     */
    public Map<String, Object> accountMoneyTypeAll() {
        return AccRecordConst.AccountMoneyType.toMapPlus();
    }

    /**
     * 店铺账户操作类型
     * @return
     */
    public Map<String, Object> accountOperation() {
        return AccRecordConst.AccountOperation.toMap();
    }

    /**
     * 店铺账户操作类型（包括样式）
     * @return
     */
    public Map<String, Object> accountOperationAll() {
        return AccRecordConst.AccountOperation.toMapPlus();
    }

    /**
     * 积分记录类型
     * @return
     */
    public Map<String, Object> integralLogType() {
        return PmsIntegralConst.IntegralLogType.toMap();
    }

    /**
     * 积分记录类型（包括样式）
     * @return
     */
    public Map<String, Object> integralLogTypeAll() {
        return PmsIntegralConst.IntegralLogType.toMapPlus();
    }

    /**
     * 财务记录审核状态
     * @return
     */
    public Map<String, Object> cashApplyAuditType() {
        return AccCashApplyConst.AuditType.toMap();
    }

    /**
     * 财务记录审核状态（包括样式）
     * @return
     */
    public Map<String, Object> cashApplyAuditTypeAll() {
        return AccCashApplyConst.AuditType.toAllMap();
    }

    /**
     * 订单核销状态
     *
     * @return
     */
    public Map<String, Object> WriteOffConst() {
        return WriteOffConst.writeOffType.toAllMap();
    }

}
