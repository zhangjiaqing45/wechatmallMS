package com.fante.project.api.appView.controller;

import com.fante.common.business.constant.BizConstants;
import com.fante.common.business.enums.PmsIntegralConst;
import com.fante.common.utils.Checker;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.project.api.appView.service.CmsIntegralDisplayService;
import com.fante.project.business.cmsDocument.service.ICmsDocumentService;
import com.fante.project.business.pmsIntegralLog.domain.PmsIntegralLog;
import com.fante.project.business.pmsIntegralProduct.domain.PmsIntegralProduct;
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
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @program: Fante
 * @Date: 2020/4/22 10:54
 * @Author: wz
 * @Description: 商品查询
 */
@Api(tags = "CmsProductDisplayController", description = "积分商品查询")
@Controller
@RequestMapping("/api/integral")
public class CmsIntegralDisplayController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(CmsNewsController.class);

    @Autowired
    private CmsIntegralDisplayService cmsIntegralDisplayService;
    @Autowired
    private ICmsDocumentService cmsDocumentService;

    @ApiOperation("(app)积分商品分类查询")
    @PostMapping("/category")
    @ResponseBody
    public AjaxResult getCategoryForNavInPlatform() {
        return AjaxResult.success().put("categoryList", cmsIntegralDisplayService.getCategoryForNavInPlatform());
    }


    @ApiOperation("查询商品:分类 / 名称 ")
    @PostMapping("/list")
    @ResponseBody
    public AjaxResult list(Long categoryId, String name) {
        startPage();
        List<PmsIntegralProduct> list = cmsIntegralDisplayService.getIntProductList(categoryId, name);
        boolean canLoad = new PageInfo(list).isHasNextPage();
        return AjaxResult.success()
                .put("productList", list)
                .put("canLoad", canLoad);
    }

    @ApiOperation("根据id查询积分商品详情")
    @PostMapping("/detail")
    @ResponseBody
    public AjaxResult detail(Long id) {
        Checker.check(ObjectUtils.isEmpty(id), "积分商品参数缺失");
        return AjaxResult.success().put("detail", cmsIntegralDisplayService.detail(id));
    }

    @ApiOperation("积分兑换规则")
    @PostMapping("/rules")
    @ResponseBody
    public AjaxResult rules() {
        return AjaxResult.success()
                .put("doc", cmsDocumentService.selectCmsDocumentByDocKey(BizConstants.smsIntegral.BIZ_INTEGRAL_EXCHAGE_RULES_DOC_KEY));
    }

    @ApiOperation("积分兑换记录")
    @PostMapping("/record")
    @ResponseBody
    public AjaxResult record(PmsIntegralLog param) {
        param.setMemberId(getTokenUserId());
        startPage();
        List<PmsIntegralLog> list = cmsIntegralDisplayService.record(param);
        boolean canLoad = new PageInfo(list).isHasNextPage();
        return AjaxResult.success()
                .put("record", list)
                .put("canLoad", canLoad)
                .put("typeMap", PmsIntegralConst.IntegralLogType.toMap());
    }
}
