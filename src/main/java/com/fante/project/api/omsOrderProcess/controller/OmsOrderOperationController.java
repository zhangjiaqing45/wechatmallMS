package com.fante.project.api.omsOrderProcess.controller;

import com.fante.common.business.enums.OrderConst;
import com.fante.common.utils.StringUtils;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.project.api.omsOrderProcess.domain.OmsMemberOrderDetail;
import com.fante.project.api.omsOrderProcess.service.IOmsOrderOperationService;
import com.fante.project.business.bizLogistics.service.IBizLogisticsService;
import com.fante.project.business.omsOrder.domain.OmsOrder;
import com.fante.project.business.omsOrder.service.IOmsOrderService;
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
 * 订单Controller
 *
 * @author fante
 * @date 2020-04-01
 */
@Api(tags = "OmsOrderProcessController", description = "订单")
@Controller
@RequestMapping("/api/order")
public class OmsOrderOperationController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(OmsOrderOperationController.class);

    @Autowired
    private IOmsOrderOperationService iOmsOrderProcessService;
    @Autowired
    private IOmsOrderService iOmsOrderService;
    /**
     * 快递服务
     */
    @Autowired
    private IBizLogisticsService iBizLogisticsService;

    @ApiOperation("根据订单状态查询会员订单列表")
    @PostMapping("/list")
    @ResponseBody
    public AjaxResult list(String status) {
        startPage();
        List<Long> ids = iOmsOrderProcessService.selectOmsOrderListByStatusBase(status,getTokenUserId());
        boolean canLoad = new PageInfo(ids).isHasNextPage();
        List<OmsMemberOrderDetail> details = iOmsOrderProcessService.selectOmsOrderListByStatus(ids);
        return AjaxResult.success().put("orderList",details).put("canLoad", canLoad)
                .put("orderTypeMap", OrderConst.OrderType.toMap());
    }

    @ApiOperation("删除订单")
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(Long id) {
        return toAjax(iOmsOrderProcessService.deleteOmsOrderById(id,getTokenUserId()));
    }

    @ApiOperation("取消订单")
    @PostMapping("/cancle")
    @ResponseBody
    public AjaxResult cancle(Long id) {
        return toAjax(iOmsOrderProcessService.memberCancleOmsOrderById(id,getTokenUserId()));
    }

    @ApiOperation("查询订单详情")
    @PostMapping("/detail")
    @ResponseBody
    public AjaxResult detail(Long id) {
        return AjaxResult.success().put("detail",iOmsOrderProcessService.getOrderDetailPageInfo(id, getTokenUserId()))
                .put("orderTypeMap", OrderConst.OrderType.toMap());
    }

    @ApiOperation("查询核销订单详情")
    @PostMapping("/detailhx")
    @ResponseBody
    public AjaxResult detailhx(String orderSn) {
        OmsOrder order=new OmsOrder();
        order.setOrderSn(orderSn);
        OmsOrder order2= iOmsOrderService.selectOmsOrderList(order).get(0);
        return AjaxResult.success().put("detail",iOmsOrderProcessService.getOrderDetailPageInfo(order2.getId(), null))
                .put("orderTypeMap", OrderConst.OrderType.toMap());
    }

    @ApiOperation("物流查询接口")
    @PostMapping("/progress")
    @ResponseBody
    public AjaxResult progress(String deliverySn)  throws Exception{
        return AjaxResult.success().put("progress",iBizLogisticsService.queryByNumberOrType(deliverySn, StringUtils.EMPTY));
    }
}
