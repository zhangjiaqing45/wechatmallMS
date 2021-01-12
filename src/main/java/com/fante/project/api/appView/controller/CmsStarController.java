package com.fante.project.api.appView.controller;

import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.project.api.appView.domain.CmsRecommendReq;
import com.fante.project.api.appView.domain.PmsProductDetailPageInfo;
import com.fante.project.api.appView.service.CmsStarService;
import com.fante.project.business.bizShopInfo.domain.BizShopInfo;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @program: Fante
 * @Date: 2020/4/22 10:54
 * @Author: wz
 * @Description: 店铺/商品 收藏查询
 */
@Api(tags = "CmsStarService", description = "店铺/商品 收藏查询")
@Controller
@RequestMapping("/api/star")
public class CmsStarController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(CmsNewsController.class);

    @Autowired
    CmsStarService cmsStarService;

    @ApiOperation("获取收藏店铺")
    @PostMapping("/shop")
    @ResponseBody
    public AjaxResult shopList() {
        startPage();
        List<BizShopInfo> list = cmsStarService.shopList(getTokenUserId());
        boolean canLoad = new PageInfo(list).isHasNextPage();
        return AjaxResult.success()
                .put("shopList", list)
                .put("canLoad", canLoad);

    }

    @ApiOperation("获取收藏商品")
    @PostMapping("/product")
    @ResponseBody
    public AjaxResult productList(CmsRecommendReq req) {
        startPage();
        List<PmsProductDetailPageInfo> list = cmsStarService.productList(getTokenUserId());
        boolean canLoad = new PageInfo(list).isHasNextPage();
        return AjaxResult.success()
                .put("productList", list)
                .put("canLoad", canLoad);
    }


    @ApiOperation("添加收藏店铺")
    @PostMapping("/addShop")
    @ResponseBody
    public AjaxResult addShop(Long shopId) {
        return toAjax(cmsStarService.addShop(shopId, getTokenUserId()));

    }

    @ApiOperation("添加收藏商品")
    @PostMapping("/addProduct")
    @ResponseBody
    public AjaxResult addProduct(Long productId) {
        return toAjax(cmsStarService.addProduct(productId, getTokenUserId()));
    }

    @ApiOperation("取消收藏店铺")
    @PostMapping("/delShop")
    @ResponseBody
    public AjaxResult delShop(Long shopId) {
        return toAjax(cmsStarService.delShop(shopId, getTokenUserId()));
    }

    @ApiOperation("取消收藏商品")
    @PostMapping("/delProduct")
    @ResponseBody
    public AjaxResult delProduct(Long productId) {
        return toAjax(cmsStarService.delProduct(productId, getTokenUserId()));
    }


}
