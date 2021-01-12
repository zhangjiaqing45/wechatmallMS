package com.fante.project.api.appView.controller;

import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.project.api.appView.domain.FlashProductParam;
import com.fante.project.api.appView.domain.FlashSessionTimeRsp;
import com.fante.project.api.appView.service.CmsFlashService;
import com.fante.project.business.smsFlashPromotionProduct.domain.SmsFlashPromotionProductDTO;
import com.fante.project.business.smsFlashPromotionSession.domain.SmsFlashPromotionSession;
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
 * @Description: 秒杀活动
 */
@Api(tags = "SmsFlashController", description = "秒杀活动")
@Controller
@RequestMapping("/api/flash")
public class SmsFlashController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(SmsFlashController.class);

    @Autowired
    private CmsFlashService cmsFlashService;

    @ApiOperation("获取当前秒杀事件")
    @PostMapping("/init")
    @ResponseBody
    public AjaxResult getNowFlashPromotion (Long shopId) {
        //获取所有时间段
        List<SmsFlashPromotionSession> enableFlashSession = cmsFlashService.getEnableFlashSession();
        //获取 离当前时间最近的 秒杀时间段
        FlashSessionTimeRsp flashSessionTimeNow = cmsFlashService.getFlashSessionTimeNow( enableFlashSession );
        FlashProductParam param = new FlashProductParam();
        param.setShopId(shopId);
        SmsFlashPromotionSession sessionTime = flashSessionTimeNow.getSessionTime();
        param.setFlashPromotionSessionId(sessionTime.getId());
        startPage();
        List<SmsFlashPromotionProductDTO> flashProductList = cmsFlashService.getFlashProductList( param );
        boolean canLoad = new PageInfo(flashProductList).isHasNextPage();
        return AjaxResult.success()
                .put( "enableFlashSession",enableFlashSession )
                .put( "nowSessionTime", sessionTime )
                .put( "buyPermissions", flashSessionTimeNow.getBuyPermissions() )
                .put( "flashProductList",flashProductList)
                .put("canLoad", canLoad);
    }

    @ApiOperation("获取选择的秒杀商品列表")
    @PostMapping("/change")
    @ResponseBody
    public AjaxResult getFlashPromotionProduct(FlashProductParam param) {
        //获取此时间段状态
        String sessionTimeStatus = cmsFlashService.getSessionTimeStatus( param.getFlashPromotionSessionId() );
        startPage();
        List<SmsFlashPromotionProductDTO> flashProductList = cmsFlashService.getFlashProductList( param );
        boolean canLoad = new PageInfo(flashProductList).isHasNextPage();
        return AjaxResult.success()
                .put("flashProductList", flashProductList)
                .put("buyPermissions", sessionTimeStatus)
                .put("canLoad", canLoad);
    }

    @ApiOperation("上滑加载更多")
    @PostMapping("/upglide")
    @ResponseBody
    public AjaxResult upglide(FlashProductParam param) {
        startPage();
        List<SmsFlashPromotionProductDTO> flashProductList = cmsFlashService.getFlashProductList( param );
        boolean canLoad = new PageInfo(flashProductList).isHasNextPage();
        return AjaxResult.success()
                .put("flashProductList", flashProductList)
                .put("canLoad", canLoad);
    }

    @ApiOperation("获取秒杀商品详情")
    @PostMapping("/detail")
    @ResponseBody
    public AjaxResult detail(Long id) {
        return AjaxResult.success().put("detail",  cmsFlashService.detail( id,getTokenUserId() ));
    }
}
