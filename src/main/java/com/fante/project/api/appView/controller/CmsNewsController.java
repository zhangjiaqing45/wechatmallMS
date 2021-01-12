package com.fante.project.api.appView.controller;

import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.project.api.appView.service.CmsNewsService;
import com.fante.project.business.cmsTopic.domain.CmsTopicDTO;
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
 * @Date: 2020/4/21 17:19
 * @Author: Mr.Z
 * @Description: 前端新闻资讯接口
 */
@Api(tags = "CmsNewsController", description = "前端新闻资讯")
@Controller
@RequestMapping("/api/news")
public class CmsNewsController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(CmsNewsController.class);

    @Autowired
    CmsNewsService cmsNewsService;

    @ApiOperation("新闻资讯列表")
    @PostMapping("/list")
    @ResponseBody
    public AjaxResult list() {
        startPage();
        List<CmsTopicDTO> list = cmsNewsService.topics();
        boolean canLoad = new PageInfo(list).isHasNextPage();
        return AjaxResult.success()
                .put("newsList", list)
                .put("canLoad", canLoad);
    }

    @ApiOperation("新闻资讯详情")
    @PostMapping("/detail")
    @ResponseBody
    public AjaxResult detail(Long newsId) {
        return AjaxResult.success().put("newsDetail", cmsNewsService.topicDetail(newsId));
    }

}
