package com.fante.project.api.appView.controller;

import com.fante.common.business.enums.SmsRecommendConst;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.project.api.appView.domain.CmsRecommendReq;
import com.fante.project.api.appView.service.CmsRecommendService;
import com.fante.project.business.smsHomeRecommendProduct.domain.SmsHomeRecommendProductDTO;
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
 * @Author: Mr.Z
 * @Description: 推荐商品接口
 */
@Api(tags = "CmsRecommendController", description = "前端推荐商品")
@Controller
@RequestMapping("/api/recommend")
public class CmsRecommendController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(CmsNewsController.class);

    @Autowired
    CmsRecommendService cmsRecommendService;

    @ApiOperation("商品推荐类型")
    @PostMapping("/types")
    @ResponseBody
    public AjaxResult types() {
        return AjaxResult.success().put("types", SmsRecommendConst.ProductType.toMap());
    }


    @ApiOperation("推荐商品列表")
    @PostMapping("/list")
    @ResponseBody
    public AjaxResult list(CmsRecommendReq req) {
        req.validate();
        startPage();
        List<SmsHomeRecommendProductDTO> list = cmsRecommendService.recommendProducts(req);
        boolean canLoad = new PageInfo(list).isHasNextPage();
        return AjaxResult.success()
                .put("recommendList", list)
                .put("canLoad", canLoad);
    }

}
