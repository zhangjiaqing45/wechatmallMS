package com.fante.project.api.appView.controller;

import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.project.api.appView.domain.CmsRecommendReq;
import com.fante.project.api.appView.domain.PmsProductCommentParam;
import com.fante.project.api.appView.service.CmsStarService;
import com.fante.project.api.appView.service.PmsCommentService;
import com.fante.project.business.bizShopInfo.domain.BizShopInfo;
import com.fante.project.business.pmsProductComment.domain.PmsProductComment;
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
 * @Description: 评论
 */
@Api(tags = "CmsStarService", description = "评论")
@Controller
@RequestMapping("/api/comment")
public class PmsCommentController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(CmsNewsController.class);

    @Autowired
    PmsCommentService pmsCommentService;


    @ApiOperation("根据id查询商品评论信息")
    @PostMapping("/product")
    @ResponseBody
    public AjaxResult product(Long id) {
        startPage();
        List<PmsProductComment> list = pmsCommentService.product(id);
        PageInfo page = new PageInfo(list);
        boolean canLoad = page.isHasNextPage();
        long total = page.getTotal();
        return AjaxResult.success()
                .put("commentList", list)
                .put("total",total)
                .put("canLoad", canLoad);
    }

    @ApiOperation("添加评论")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult add(PmsProductCommentParam param) {
        return toAjax(pmsCommentService.add(param,getTokenClientId()));
    }



}
