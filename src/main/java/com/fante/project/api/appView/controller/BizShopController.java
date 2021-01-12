package com.fante.project.api.appView.controller;

import com.fante.common.business.enums.AuditTypeEnum;
import com.fante.common.constant.Constants;
import com.fante.common.utils.StringUtils;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.project.api.appView.domain.ShopAndCouponInfo;
import com.fante.project.api.appView.service.BizShopService;
import com.fante.project.business.bizShopInfo.domain.BizShopInfo;
import com.fante.project.business.bizShopInfo.domain.ShopInfoVo;
import com.fante.project.business.bizShopInfo.service.IBizShopInfoService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: Fante
 * @Date: 2020/4/22 10:54
 * @Author: wz
 * @Description: 店铺
 */
@Api(tags = "BizShopController", description = "店铺")
@Controller
@RequestMapping("/api/shop")
public class BizShopController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(BizShopController.class);

    @Autowired
    BizShopService bizShopService;

    @Autowired
    IBizShopInfoService iBizShopInfoService;


    @ApiOperation("查询店铺信息")
    @PostMapping("/list")
    @ResponseBody
    public AjaxResult list(ShopInfoVo shopInfoVo) {
        startPage();
//        List<BizShopInfo> list = bizShopService.list(StringUtils.EMPTY,name);
        
//        //添加优惠券信息
//        List<Long> ids = list.stream()
//                .map(BizShopInfo::getId)
//                .collect(Collectors.toList());
        //List<ShopAndCouponInfo> shopCouponInfos = iBizShopInfoService.selectJoinCouponList(ids);
//        List<ShopAndCouponInfo> shopCouponInfos = Collections.emptyList();
//        if(!ObjectUtils.isEmpty(ids)){
//            shopCouponInfos = iBizShopInfoService.selectJoinCouponList(ids);
//        }
        shopInfoVo.setAudit(AuditTypeEnum.SUCCESS.getType());
        List<ShopAndCouponInfo> shopCouponInfos = iBizShopInfoService.selectJoinCouponListRemake(shopInfoVo);
        PageInfo page = new PageInfo(shopCouponInfos);
        boolean canLoad = page.isHasNextPage();
        long total = page.getTotal();
        return AjaxResult.success()
                .put("list", shopCouponInfos)
                .put("total",total)
                .put("canLoad", canLoad);
    }

    @ApiOperation("查询店铺详情")
    @PostMapping("/detail")
    @ResponseBody
    public AjaxResult detail(Long shopId) {
        return AjaxResult.success()
                .put("shopInfo", bizShopService.get(shopId));
    }

}
