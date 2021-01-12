package com.fante.project.api.appView.service;

import com.fante.common.business.constant.BizConstants;
import com.fante.common.business.enums.ProductAttributeCategoryConst;
import com.fante.common.business.enums.SmsRecommendConst;
import com.fante.common.constant.Constants;
import com.fante.common.utils.Checker;
import com.fante.common.utils.StringUtils;
import com.fante.common.utils.sql.SqlUtil;
import com.fante.framework.aspectj.lang.annotation.Excel;
import com.fante.framework.config.TxMapConfig;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.framework.web.page.PageDomain;
import com.fante.framework.web.page.TableSupport;
import com.fante.project.api.appView.domain.ShopAndCouponInfo;
import com.fante.project.business.bizShopInfo.domain.BizShopInfo;
import com.fante.project.business.bizShopInfo.service.IBizShopInfoService;
import com.fante.project.business.bizShopInfo.utils.ShopRedis;
import com.fante.project.business.cmsAppNav.domain.CmsAppNav;
import com.fante.project.business.cmsAppNav.service.ICmsAppNavService;
import com.fante.project.business.cmsTopic.domain.CmsTopicDTO;
import com.fante.project.business.cmsTopic.service.ICmsTopicService;
import com.fante.project.business.pmsProduct.domain.PmsProduct;
import com.fante.project.business.pmsProduct.service.IPmsProductService;
import com.fante.project.business.smsCoupon.domain.SmsCoupon;
import com.fante.project.business.smsHomeAdvertise.domain.SmsHomeAdvertisePositionDTO;
import com.fante.project.business.smsHomeAdvertise.service.ISmsHomeAdvertiseService;
import com.fante.project.business.smsHomeRecommendProduct.service.ISmsHomeRecommendProductService;
import com.fante.project.system.config.service.IConfigService;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Maps;
import io.swagger.annotations.ApiModelProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.naming.Name;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: Fante
 * @Date: 2020/4/18 15:54
 * @Author: Mr.Z
 * @Description: 前端首页相关处理服务
 */
@Service
public class CmsHomeService {

    private static Logger log = LoggerFactory.getLogger(CmsHomeService.class);
    /**
     * 参数配置 服务层
     */
    @Autowired
    private IConfigService configService;
    /**
     * 广告管理Service接口
     */
    @Autowired
    private ISmsHomeAdvertiseService smsHomeAdvertiseService;
    /**
     * 推荐商品Service接口
     */
    @Autowired
    private ISmsHomeRecommendProductService smsHomeRecommendProductService;
    /**
     * 话题Service接口
     */
    @Autowired
    private ICmsTopicService cmsTopicService;
    /**
     * 前端导航管理Service接口
     */
    @Autowired
    private ICmsAppNavService cmsAppNavService;
    /**
     * 店铺信息服务
     */
    @Autowired
    BizShopService bizShopService;
    /**
     * 店铺信息服务
     */
    @Autowired
    private IBizShopInfoService iBizShopInfoService;
    /**
     * 收藏 关注接口
     */
    @Autowired
    CmsStarService cmsStarService;
    /**
     * 商品
     */
    @Autowired
    private IPmsProductService iPmsProductService;
    /**
     * 店铺Redis工具类
     */
    @Autowired
    ShopRedis shopRedis;
    /**
     * 腾讯地图
     */
    @Autowired
    TxMapConfig txMapConfig;

    /**
     * 首页广告（按照广告位置分类展示）
     * @return
     */
    public Map<String, Object> advertises() {
        String showNum = StringUtils.defaultString(configService.selectConfigByKey(BizConstants.app.APP_ADVERTISE_SHOW_NUM), BizConstants.app.APP_ADVERTISE_SHOW_NUM_DEF);
        List<SmsHomeAdvertisePositionDTO> list = smsHomeAdvertiseService.selectAdvertiseWithPosition(Integer.valueOf(showNum));
        if (ObjectUtils.isEmpty(list)) {
            return null;
        }
        Map<String, Object> result = Maps.newHashMap();
        list.forEach(advertise -> {
            result.put(advertise.getPosition(), advertise.getAdvertises());
        });
        return result;
    }
    /**
     * 首页推荐商品（按照推荐商品分类展示）
     * @return
     */
    public List<SmsCoupon> getOfferCoupons(Long memberId) {
        //获取商家主动赠送的优惠券
        List<SmsCoupon> offerCoupons = shopRedis.getOfferCoupons(String.valueOf(memberId));
        if(!ObjectUtils.isEmpty(offerCoupons)){
            return offerCoupons;
        }
        return null;
    }
    /**
     * 首页推荐商品（按照推荐商品分类展示）
     * @return
     */
    public Map<String, Object>  recommendProducts() {
        //设置推荐商品和店铺
        String showNum = StringUtils.defaultString(configService.selectConfigByKey(BizConstants.app.APP_RECOMMEND_SHOW_NUM), BizConstants.app.APP_RECOMMEND_SHOW_NUM_DEF);
        Map<String, Object> result = Maps.newHashMap();
        for (String type : SmsRecommendConst.ProductType.toMap().keySet()) {
            result.put(type, smsHomeRecommendProductService.selectRecommendProductWithType(type, Integer.valueOf(showNum)));
        }
        /**
         * 分页
         */
        PageHelper.startPage(1, Integer.valueOf(showNum),  StringUtils.EMPTY);
        List<Long> list = bizShopService.list(Constants.REMMOEND, StringUtils.EMPTY)
                .stream()
                .map(BizShopInfo::getId)
                .collect(Collectors.toList());
        List<ShopAndCouponInfo> couponInfos = new ArrayList<>();
        if(!ObjectUtils.isEmpty(list)){
            couponInfos = iBizShopInfoService.selectJoinCouponList(list);
        }
        result.put("shopList",couponInfos);
        //设置推荐店铺
        String showNumforishot = StringUtils.defaultString(configService.selectConfigByKey(BizConstants.app.APP_ISHOT_SHOW_NUM), BizConstants.app.APP_ISHOT_SHOW_NUM_DEF);
        /**
         * 分页
         */
        PageHelper.startPage(1, Integer.valueOf(showNumforishot),  StringUtils.EMPTY);
        List<Long> listforishot = bizShopService.listByIshot(Constants.ISHOT)
                .stream()
                .map(BizShopInfo::getId)
                .collect(Collectors.toList());
        List<ShopAndCouponInfo> couponInfosforishot = new ArrayList<>();
        if(!ObjectUtils.isEmpty(listforishot)){
            couponInfosforishot = iBizShopInfoService.selectJoinCouponList(listforishot);
        }
        result.put("shopListforishot",couponInfosforishot);
        return result;
    }

    /**
     * 首页滚动新闻咨询
     * @return
     */
    public List<CmsTopicDTO> topics() {
        String showNum = StringUtils.defaultString(configService.selectConfigByKey(BizConstants.app.APP_TOPIC_SHOW_NUM), BizConstants.app.APP_TOPIC_SHOW_NUM_DEF);
        PageHelper.startPage(1, Integer.valueOf(showNum));
        return cmsTopicService.selectShowList();
    }

    /**
     * 首页导航
     * @return
     */
    public List<CmsAppNav> indexNav() {
        return cmsAppNavService.selectAvailableNavForAppIndex();
    }

    /**
     * 店铺首页导航
     */
    public List<CmsAppNav> shopIndexNav() {
        return cmsAppNavService.selectAvailableNavForShopIndex();
    }

    /**
     * 店铺首页顶部的信息
     */
    public AjaxResult getShopHandInfo(Long shopId, Long memberId) {
        BizShopInfo shop = bizShopService.get(shopId);
        Checker.check(ObjectUtils.isEmpty(shop),"该店铺不存在");
        List<CmsAppNav> cmsAppNavs = shopIndexNav();
        //查询所有关注店铺的人数
        int shopStarCount = cmsStarService.countStarOfShop(shopId);
        //查询当前用户是否关注店铺
        boolean shopStar = cmsStarService.isStarOfShop(shopId, memberId);
        //查询店铺下的商品数量
        PmsProduct product = new PmsProduct();
        product.setShopId(shopId);
        product.setPublishStatus(ProductAttributeCategoryConst.publicStatusEnum.PUTAWAY.getType());
        int shopProductCount = iPmsProductService.countPmsProduct(product);
        String key = txMapConfig.getKey();
        //组装返回结果
        return AjaxResult.success().put("shopName",shop.getCompanyName())
                .put("longitude",shop.getLongitude())
                .put("mapKey",key)
                .put("latitude",shop.getLatitude())
                .put("address",shop.getAddress())
                .put("nav",cmsAppNavs)
                .put("serviceTel",shop.getServiceTel())
                .put("starCount",shopStarCount)
                .put("isStar",shopStar)
                .put("productCount",shopProductCount);
    }

}
