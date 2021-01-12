package com.fante.project.api.appView.controller;

import com.fante.common.business.enums.SmsCouponConst;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.project.api.appView.domain.PmsProductDetailPageInfo;
import com.fante.project.api.appView.service.CmsProductDisplayService;
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
 * @Description: 商品查询
 */
@Api(tags = "CmsProductDisplayController", description = "商品查询")
@Controller
@RequestMapping("/api/product")
public class CmsProductDisplayController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(CmsNewsController.class);

    @Autowired
    private CmsProductDisplayService cmsProductDisplayService;

    @ApiOperation("平台商品分类")
    @PostMapping("/category")
    @ResponseBody
    public AjaxResult getCategoryForNavInPlatform() {
        return AjaxResult.success(cmsProductDisplayService.getCategoryForNavInPlatform(null));
    }

    @ApiOperation("分类app:店铺展示")
    @PostMapping("/shopCategory")
    @ResponseBody
    public AjaxResult getCategoryForNavInShop(Long shopId) {
        return AjaxResult.success(cmsProductDisplayService.getCategoryForNavInShop(shopId, null));
    }
    
    @ApiOperation("平台商品分类")
    @PostMapping("/categoryRemake")
    @ResponseBody
    public AjaxResult getCategoryForNavInPlatform(Long level) {
        return AjaxResult.success(cmsProductDisplayService.getCategoryForNavInPlatform(level));
    }
    
    @ApiOperation("根据id查询平台商品分类信息")
    @PostMapping("/getCategoryInfoById")
    @ResponseBody
    public AjaxResult getPmsProductCategoryById(Long id){
        return AjaxResult.success(cmsProductDisplayService.getPmsProductCategoryById(id));
    }
    
    @ApiOperation("分类app:店铺展示")
    @PostMapping("/shopCategoryRemake")
    @ResponseBody
    public AjaxResult getCategoryForNavInShop(Long shopId, Long level) {
        return AjaxResult.success(cmsProductDisplayService.getCategoryForNavInShop(shopId, level));
    }

    @ApiOperation("查询商品:分类 / 名称 / 店铺")
    @PostMapping("/list")
    @ResponseBody
    public AjaxResult list(Long categoryId, String name, Long shopId) {
        startPage();
        List<PmsProductDetailPageInfo> list = cmsProductDisplayService.getProductList(categoryId, name, shopId);
        boolean canLoad = new PageInfo(list).isHasNextPage();
        return AjaxResult.success()
                .put("productList", list)
                .put("canLoad", canLoad);
    }

    @ApiOperation("根据id查询商品详情")
    @PostMapping("/detail")
    @ResponseBody
    public AjaxResult detail(Long id) {
        return AjaxResult.success(cmsProductDisplayService.detail(id, getTokenUserId()));
    }

}
