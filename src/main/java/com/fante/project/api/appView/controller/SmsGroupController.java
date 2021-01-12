package com.fante.project.api.appView.controller;

import com.fante.common.utils.Checker;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.project.api.appView.domain.PmsGroupProductDetailPageInfo;
import com.fante.project.api.appView.service.CmsGroupService;
import com.fante.project.business.smsGroupGame.domain.SmsGroupGame;
import com.fante.project.business.smsGroupGame.service.ISmsGroupGameService;
import com.fante.project.business.smsGroupGameRecord.domain.SmsGroupGameRecordDetail;
import com.fante.project.business.smsGroupGameRecord.service.ISmsGroupGameRecordService;
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
 * @Description: 团购活动
 */
@Api(tags = "SmsGroupController", description = "团购活动")
@Controller
@RequestMapping("/api/group")
public class SmsGroupController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(SmsGroupController.class);
    /**
     * 组团服务
     */
    @Autowired
    private ISmsGroupGameService iSmsGroupGameService;
    @Autowired
    private CmsGroupService cmsGroupService;

    @ApiOperation("获取团购商品列表")
    @PostMapping("/list")
    @ResponseBody
    public AjaxResult getEnableGroupProduct (SmsGroupGame game) {//必要参数:productName shopId
        //获取所有时间段
        startPage();
        List<SmsGroupGame> enableGroupProduct = cmsGroupService.getEnableGroupProduct(game);
        boolean canLoad = new PageInfo(enableGroupProduct).isHasNextPage();
        return AjaxResult.success()
                .put( "groupProductList",enableGroupProduct )
                .put("canLoad", canLoad);
    }


    @ApiOperation("获取团购商品详情")
    @PostMapping("/detail")
    @ResponseBody
    public AjaxResult detail(Long id) {
        long memberId = getTokenUserId();
        //获取商品详情
        PmsGroupProductDetailPageInfo detail = cmsGroupService.detail(id,memberId );
        return AjaxResult.success().put("detail",detail);
    }

    @ApiOperation("获取团购商品的团购记录")
    @PostMapping("/record")
    @ResponseBody
    public AjaxResult record(Long id) {
        long memberId = getTokenUserId();
        //获取当前商品团购记录
        startPage();
        SmsGroupGame game = iSmsGroupGameService.selectSmsGroupGameById(id);
        Checker.check(ObjectUtils.isEmpty(game),"活动已结束");
        List<Long> ids = cmsGroupService.getGroupRecordListBase( id, memberId );
        PageInfo pageInfo = new PageInfo(ids);
        return AjaxResult.success().put("record",  cmsGroupService.getGroupRecordList(ids,game.getAging()))
                                 .put("canLoad", pageInfo.isHasNextPage())
                                 .put("total", pageInfo.getTotal());
    }
    @ApiOperation("获取我的团购记录")
    @PostMapping("/member")
    @ResponseBody
    public AjaxResult memberRecord() {
        long memberId = getTokenUserId();
        //获取当前商品团购记录
        startPage();
        List<Long> ids = cmsGroupService.getMemberGroupRecordBase( memberId );
        PageInfo pageInfo = new PageInfo(ids);
        return AjaxResult.success().put("record", cmsGroupService.getMemberGroupRecord(ids))
                                 .put("canLoad", pageInfo.isHasNextPage())
                                 .put("total", pageInfo.getTotal());
    }
}
