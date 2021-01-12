package com.fante.project.api.appView.controller;

import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.project.api.appView.domain.SignRsp;
import com.fante.project.api.appView.service.SignProcessService;
import com.fante.project.api.umsProcess.service.UmsMemberProcessService;
import com.fante.project.weixin.core.utils.WechatRedis;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: Fante
 * @Date: 2020/4/22 10:54
 * @Author: wz
 * @Description: 店铺/商品 收藏查询
 */
@Api(tags = "CmsStarService", description = "店铺/商品 收藏查询")
@Controller
@RequestMapping("/api/sign")
public class UmsSignInfoController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(CmsNewsController.class);
    /**
     * 签到流程服务
     */
    @Autowired
    SignProcessService signProcessService;
    /**
     * 用户缓存
     */
    @Autowired
    private WechatRedis wechatRedis;
    /**
     * 会员Service接口
     */
    @Autowired
    private UmsMemberProcessService umsMemberProcessService;

    @ApiOperation("获取用户签到详情")
    @PostMapping("/info")
    @ResponseBody
    public AjaxResult shopList() {
        return AjaxResult.success().put("info", signProcessService.signInfo(getTokenClientId()));
    }

    @ApiOperation("签到")
    @PostMapping("/sign")
    @ResponseBody
    public AjaxResult productList() {
        SignRsp sign = signProcessService.sign( getTokenClientId());
        // 更新缓存
        umsMemberProcessService.updateCache(getTokenUserId());
        return AjaxResult.success().put("sign",sign);
    }

}
