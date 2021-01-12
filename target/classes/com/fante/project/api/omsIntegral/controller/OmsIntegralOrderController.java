package com.fante.project.api.omsIntegral.controller;

import com.fante.common.business.enums.PmsIntegralConst;
import com.fante.framework.aspectj.lang.annotation.Log;
import com.fante.framework.aspectj.lang.enums.BusinessType;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.project.api.omsIntegral.domain.IntegralExchangeParam;
import com.fante.project.api.omsIntegral.domain.OmsIntegralOrder;
import com.fante.project.api.omsIntegral.service.OmsIntegralOrderService;
import com.fante.project.api.omsOrderProcess.controller.OmsCartController;
import com.fante.project.api.umsProcess.service.UmsMemberProcessService;
import com.fante.project.business.omsOrderItem.domain.OmsOrderItem;
import com.fante.project.business.pmsIntegralLog.domain.PmsIntegralLog;
import com.fante.project.business.pmsIntegralLog.service.IPmsIntegralLogService;
import com.fante.project.business.umsMember.domain.UmsMember;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Objects;

/**
 * @author ftnet
 * @Description OmsIntergralOrder
 * @CreatTime 2020/4/22
 */
@Api(tags = "OmsIntergralOrderController", description = "积分订单")
@Controller
@RequestMapping("/api/intOrder")
public class OmsIntegralOrderController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(OmsCartController.class);

    @Autowired
    private OmsIntegralOrderService omsIntergralOrderService;
    @Autowired
    private IPmsIntegralLogService iPmsIntegralLogService;
    /**
     * 会员相关处理服务
     */
    @Autowired
    private UmsMemberProcessService umsMemberProcessService;


    @ApiOperation("[结算]订单:积分兑换")
    @Log(title = "订单", businessType = BusinessType.INSERT)
    @PostMapping("/createNowPreview")
    @ResponseBody
    public AjaxResult createNow(IntegralExchangeParam param) {
        UmsMember member = umsMemberProcessService.get(getTokenClientId());
        param.setMember(member);
        return AjaxResult.success(omsIntergralOrderService.createPreviewOfIntergral(param));
    }


    @ApiOperation("[确认]下单:积分兑换")
    @Log(title = "订单", businessType = BusinessType.INSERT)
    @PostMapping("/confirmOfNow")
    @ResponseBody
    public AjaxResult confirmOfNow(IntegralExchangeParam param) {
        UmsMember member = umsMemberProcessService.get(getTokenClientId());
        param.setMember(member);
        OmsOrderItem orderItem = omsIntergralOrderService.intergralOfExchange(param);
        //兑换成功后插入兑换记录
        PmsIntegralLog integralLog = new PmsIntegralLog();
        integralLog.setIntegral(orderItem.getPayPrice().negate());
        integralLog.setMemberId(member.getId());
        integralLog.setName(orderItem.getProductName()+",,,"+param.getQuantity());
        integralLog.setType(PmsIntegralConst.IntegralLogType.EXCHANGE.getType());
        iPmsIntegralLogService.insertPmsIntegralLog(integralLog);
        //更新缓存
        umsMemberProcessService.updateCache(member.getId());
        return AjaxResult.success("兑换成功");
    }

    @ApiOperation("查询:所有积分订单")
    @Log(title = "订单", businessType = BusinessType.INSERT)
    @PostMapping("/list")
    @ResponseBody
    public AjaxResult list(String status) {
        startPage();
        List<OmsIntegralOrder>  orderList = omsIntergralOrderService.list(status, getTokenUserId());
        boolean canLoad = new PageInfo(orderList).isHasNextPage();
        return AjaxResult.success().put("orderList", orderList).put("canLoad", canLoad);
    }

    @ApiOperation("查询:一个积分订单")
    @Log(title = "订单", businessType = BusinessType.INSERT)
    @PostMapping("/get")
    @ResponseBody
    public AjaxResult get(Long id) {
        return AjaxResult.success(omsIntergralOrderService.get(id, getTokenUserId()));
    }

}
