package com.fante.project.business.homeStatistics.controller;

import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.framework.web.page.TableDataInfo;
import com.fante.project.business.cmsTopicCategory.controller.CmsTopicCategoryController;
import com.fante.project.business.cmsTopicCategory.domain.CmsTopicCategory;
import com.fante.project.business.cmsTopicCategory.service.ICmsTopicCategoryService;
import com.fante.project.business.homeStatistics.domain.ChartDto;
import com.fante.project.business.homeStatistics.domain.HomeData;
import com.fante.project.business.homeStatistics.domain.SomeDaysOrderInfo;
import com.fante.project.business.homeStatistics.service.IhomeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by ftnet on 2020/5/11
 */
@Api(tags = "homeController", description = "首页显示统计")
@Controller
@RequestMapping("/business/home")
public class homeController extends BaseController {
    private static Logger log = LoggerFactory.getLogger( homeController.class);

    @Autowired
    private IhomeService ihomeService;

    @ApiOperation("当日、当月订单总数量及成交总额；")
    @RequiresPermissions("business:omsOrder:view")
    @GetMapping("/getHomeData")
    @ResponseBody
    public AjaxResult homeData() {
        Long shopId = null;
        if (getSysUser().isFranchisee()){
            shopId = getSysUser().getDeptId();
        }
        //获取首页数据
        HomeData homeData = ihomeService.getHomeData(shopId);
        return AjaxResult.success().put( "homeData",homeData )
                .put( "shopId",shopId );
    }

    @ApiOperation("当月每日订单数量及成交额曲线图统计；")
    @RequiresPermissions("business:omsOrder:view")
    @PostMapping("/getEchartData")
    @ResponseBody
    public AjaxResult getEchartData(SomeDaysOrderInfo param) {
        if (getSysUser().isFranchisee()){
            param.setShopId(getSysUser().getDeptId());
        }
        //当月每日订单数量及成交额曲线图统计；
        ChartDto chartDto = ihomeService.getEchartData(param);
        //获取时间段内的订单数量 成交总额 发放佣金
        SomeDaysOrderInfo someDaysOrderInfo = ihomeService.getSomeDayOrderInfo(param);
        return AjaxResult.success().put("homeChartInfo",chartDto)
                .put("someDaysOrderInfo",someDaysOrderInfo);
    }
}
