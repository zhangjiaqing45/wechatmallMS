package com.fante.project.api.appView.controller;

import com.fante.common.utils.StringUtils;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.project.api.appView.service.CmsHomeService;
import com.fante.project.business.bizShopInfo.utils.ShopRedis;
import com.fante.project.business.txmap.domain.GeocoderOpAddressComponent;
import com.fante.project.business.txmap.service.TxMapService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.stream.Collectors;

/**
 * @program: Fante
 * @Date: 2020/4/18 15:41
 * @Author: Mr.Z
 * @Description: 前端首页接口
 */
@Api(tags = "CmsHomeController", description = "前端首页")
@Controller
@RequestMapping("/api/home")
public class CmsHomeController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(CmsHomeController.class);

    @Autowired
    CmsHomeService cmsHomeService;
    @Autowired
    private TxMapService txMapService;
    /**
     * 店铺Redis工具类
     */
    @Autowired
    ShopRedis shopRedis;

    @ApiOperation("平台-前端首页数据")
    @PostMapping("/pageData")
    @ResponseBody
    public AjaxResult pageData() {
        return AjaxResult.success()
                .put("advertises", cmsHomeService.advertises())
                .put("recommendProducts", cmsHomeService.recommendProducts())
                .put("topics", cmsHomeService.topics())
                .put("offerCoupons", cmsHomeService.getOfferCoupons(getTokenUserId()))
                //去掉优惠券
                .put("nav", cmsHomeService.indexNav().stream().filter(item->item.getId()!=15).collect(Collectors.toList()));
    }

    @ApiOperation("定位服务")
    @PostMapping("/location")
    @ResponseBody
    public AjaxResult loaction(String lat, String lng) {
        GeocoderOpAddressComponent addrComp = null;
        if (StringUtils.isNoneBlank(lat, lng)) {
            addrComp = txMapService.coordToAddr(lat, lng);
        }
        return AjaxResult.success().put("addrComp", addrComp);
    }

    @ApiOperation("店铺-前端店铺首页数据")
    @PostMapping("/shopPageData")
    @ResponseBody
    public AjaxResult shopPageData(Long shopId) {
        return cmsHomeService.getShopHandInfo(shopId,getTokenUserId());
    }


    @ApiOperation("通知用户商家赠送优惠券后删除redis中key")
    @PostMapping("/iGetIt")
    @ResponseBody
    public AjaxResult iGetIt() {
        shopRedis.delCouponHintFlag(String.valueOf(getTokenUserId()));
        return AjaxResult.success();
    }

}
