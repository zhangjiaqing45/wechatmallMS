package com.fante.project.business.omsOrder.controller;

import com.fante.common.business.enums.CommonUse;
import com.fante.common.business.enums.OrderConst;
import com.fante.common.utils.StringUtils;
import com.fante.common.utils.poi.ExcelUtil;
import com.fante.framework.aspectj.lang.annotation.Log;
import com.fante.framework.aspectj.lang.enums.BusinessType;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.framework.web.page.TableDataInfo;
import com.fante.project.business.bizLogistics.domain.BizLogistics;
import com.fante.project.business.bizLogistics.service.IBizLogisticsService;
import com.fante.project.business.omsOrder.domain.*;
import com.fante.project.business.omsOrder.service.IOmsOrderService;
import com.fante.project.business.omsOrderSendCompany.domain.OmsOrderSendCompany;
import com.fante.project.business.omsOrderSendCompany.service.IOmsOrderSendCompanyService;
import com.fante.project.business.omsPayOrder.domain.OmsPayOrder;
import com.fante.project.business.omsPayOrder.service.IOmsPayOrderService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 订单Controller
 *
 * @author fante
 * @date 2020-04-01
 */
@Api(tags = "OmsOrderController", description = "订单")
@Controller
@RequestMapping("/business/omsOrder")
public class OmsOrderController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(OmsOrderController.class);

    private String prefix = "business/omsOrder";
    /**
     * 订单服务
     */
    @Autowired
    private IOmsOrderService omsOrderService;

    @Autowired
    private IOmsPayOrderService omsPayOrderService;
    /**
     * 快递服务
     */
    @Autowired
    private IBizLogisticsService iBizLogisticsService;
    /**
     * 物流公司查询服务
     */
    @Autowired
    private IOmsOrderSendCompanyService iOmsOrderSendCompanyService;

    @RequiresPermissions("business:omsOrder:view")
    @GetMapping()
    public String omsOrder(ModelMap map) {
        String prompt = "";
        if (getSysUser().isFranchisee()) {
            int n = omsOrderService.countSevenDayNotSend(getSysUser().getDeptId());
            if (n > 0) {
                prompt = StringUtils.format("七天内有{}单超过24小时未发货,请及时处理.以免影响您店铺的评价！", n);
            }
        }
        map.put("prompt", prompt);
        return prefix + "/omsOrder";
    }

    @ApiOperation("条件查询订单列表")
    @RequiresPermissions("business:omsOrder:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(OmsOrder omsOrder) {
        startPage();
        List<OmsOrder> list = omsOrderService.selectOmsOrderList(omsOrder);
        return getDataTable(list);
    }

    @ApiOperation("条件查询订单列表包括操作按钮")
    @RequiresPermissions("business:omsOrder:list")
    @PostMapping("/listBtns")
    @ResponseBody
    public TableDataInfo listBtns(OmsOrder omsOrder) {
        if (getSysUser().isFranchisee()) {
            omsOrder.setShopId(getSysUser().getDeptId());
        }
        startPage();


        if(!ObjectUtils.isEmpty(omsOrder.getBeginCreateTime())){
            omsOrder.setBeginCreateTime(omsOrder.getBeginCreateTime()+" 00:00:00");
        }

        if(!ObjectUtils.isEmpty(omsOrder.getEndCreateTime())){
            omsOrder.setEndCreateTime(omsOrder.getEndCreateTime()+" 59:59:59");
        }
        System.out.println("开始搜索时间："+omsOrder.getBeginCreateTime());
        System.out.println("结束搜索时间："+omsOrder.getEndCreateTime());

        if(!ObjectUtils.isEmpty(omsOrder.getOrderType())){
            System.out.println("omsOrder.getOrderType()="+omsOrder.getOrderType());

            if("1".equals(omsOrder.getOrderType())){
                System.out.println("1");
                omsOrder.setPaymentType("1");
                omsOrder.setOrderType(null);
            }else if("2".equals(omsOrder.getOrderType())){
                System.out.println("2");
                omsOrder.setPaymentType("2");
                omsOrder.setOrderType(null);
            }

        }

        List<OmsOrder> list = omsOrderService.selectOmsOrderList(omsOrder);

        if(list.size()>0){
            for(OmsOrder order:list){
                if(!ObjectUtils.isEmpty(order.getPayOrderId())){
                 OmsPayOrder payOrder= omsPayOrderService.selectOmsPayOrderById(order.getPayOrderId());
                    order.setPayOrderSn(payOrder.getPayOrderSn());
                }
            }
        }

        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(AjaxResult.Type.SUCCESS.value());
        rspData.setTotal(new PageInfo(list).getTotal());
        List<OmsOrderBtnResult> collect = list.stream()
                .map(item -> {
                    //获取按钮组合
                    List<String> btns = OrderConst.OrderActionEnum.getBtns(item.getStatus());
                    return new OmsOrderBtnResult(item, btns);
                }).collect(Collectors.toList());
        rspData.setRows(collect);
        return rspData;
    }

    @ApiOperation("导出订单列表")
    @RequiresPermissions("business:omsOrder:export")
    @Log(title = "订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OmsOrder omsOrder) {
        if(!ObjectUtils.isEmpty(omsOrder.getBeginCreateTime())){
            omsOrder.setBeginCreateTime(omsOrder.getBeginCreateTime()+" 00:00:00");
        }

        if(!ObjectUtils.isEmpty(omsOrder.getEndCreateTime())){
            omsOrder.setEndCreateTime(omsOrder.getEndCreateTime()+" 59:59:59");
        }
        System.out.println("开始搜索时间："+omsOrder.getBeginCreateTime());
        System.out.println("结束搜索时间："+omsOrder.getEndCreateTime());

        if(!ObjectUtils.isEmpty(omsOrder.getOrderType())){
            System.out.println("omsOrder.getOrderType()="+omsOrder.getOrderType());

            if("1".equals(omsOrder.getOrderType())){
                System.out.println("1");
                omsOrder.setPaymentType("1");
                omsOrder.setOrderType(null);
            }else if("2".equals(omsOrder.getOrderType())){
                System.out.println("2");
                omsOrder.setPaymentType("2");
                omsOrder.setOrderType(null);
            }

        }
        List<OmsOrder> list = omsOrderService.selectOmsOrderList(omsOrder);
        if(list.size()>0){
            for(OmsOrder order:list){
                if(!ObjectUtils.isEmpty(order.getPayOrderId())){
                    OmsPayOrder payOrder= omsPayOrderService.selectOmsPayOrderById(order.getPayOrderId());
                    order.setPayOrderSn(payOrder.getPayOrderSn());
                }
            }
        }

        ExcelUtil<OmsOrder> util = new ExcelUtil<OmsOrder>(OmsOrder.class);
        return util.exportExcel(list, "omsOrder");
    }

    @ApiOperation("[跳转] 到新增订单页面")
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    @ApiOperation("新增保存订单")
    @RequiresPermissions("business:omsOrder:add")
    @Log(title = "订单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(OmsOrder omsOrder) {
        return toAjax(omsOrderService.insertOmsOrder(omsOrder));
    }

    @ApiOperation("[跳转] 到订单编辑页面")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        OmsOrder omsOrder = omsOrderService.selectOmsOrderById(id);
        mmap.put("omsOrder", omsOrder);
        return prefix + "/edit";
    }

    @ApiOperation("[跳转] 到订单详情页面")
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, ModelMap mmap) {
        OmsOrderDetail detail = omsOrderService.selectOmsOrderDetailById(id);
        //获取按钮组合
        List<String> btns = OrderConst.OrderActionEnum.getBtns(detail.getStatus());
        detail.setBtns(btns);
        mmap.put("detail", detail);
        return prefix + "/detail";
    }

    @ApiOperation("[跳转] 到订单发货页面")
    @GetMapping("/send")
    public String send(String ids, ModelMap mmap) {
        List<OmsOrderDetail> detailList = omsOrderService.selectOrderSendDetail(ids);
        OmsOrderSendCompany select = new OmsOrderSendCompany();
        select.setStatus(CommonUse.Status.ENABLE.getType());
        List<OmsOrderSendCompany> omsOrderSendCompanies = iOmsOrderSendCompanyService.selectOmsOrderSendCompanyList(select);
        mmap.put("detailList", detailList);
        mmap.put("sendCompanys", omsOrderSendCompanies);
        return prefix + "/send";
    }

    @ApiOperation("[跳转] 到订单跟踪页面")
    @GetMapping("/progress/{deliverySn}")
    public String progress(@PathVariable("deliverySn") String deliverySn, ModelMap mmap) throws Exception{
        BizLogistics bizLogistics= iBizLogisticsService.queryByNumberOrType(deliverySn, "");
        mmap.put("progress", bizLogistics);
        return prefix + "/progress";
    }

    @ApiOperation("修改保存订单")
    @RequiresPermissions("business:omsOrder:edit")
    @Log(title = "订单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(OmsOrder omsOrder) {
        return toAjax(omsOrderService.updateOmsOrder(omsOrder));
    }

    @ApiOperation("批量删除订单")
    @RequiresPermissions("business:omsOrder:remove")
    @Log(title = "订单", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(Long id) {
        return toAjax(omsOrderService.deleteOmsOrderById(id));
    }

    @ApiOperation("取消订单")
    @RequiresPermissions("business:omsOrder:edit")
    @Log(title = "订单", businessType = BusinessType.UPDATE)
    @PostMapping("/cancle")
    @ResponseBody
    public AjaxResult cancle(Long id) {
        return toAjax(omsOrderService.cancleOmsOrderById(id));
    }

    @ApiOperation("批量备注订单")
    @RequiresPermissions("business:omsOrder:edit")
    @Log(title = "订单", businessType = BusinessType.UPDATE)
    @PostMapping("/batchRemarks")
    @ResponseBody
    public AjaxResult batchRemarks(RemarkParam param) {
        return toAjax(omsOrderService.batchRemarks(param));
    }

    @ApiOperation("订单唯一校验")
    @PostMapping("/checkUnique")
    @ResponseBody
    public String checkUnique(OmsOrder omsOrder) {
        return omsOrderService.checkOmsOrderUnique(omsOrder);
    }

    @ApiOperation("批量发货")
    @RequiresPermissions("business:omsOrder:edit")
    @Log(title = "订单", businessType = BusinessType.UPDATE)
    @PostMapping("/sendProduct")
    @ResponseBody
    public AjaxResult sendProduct(@RequestBody List<SendOrderParam> paramList) {
        return toAjax(omsOrderService.batchSendProducts(paramList));
    }

}
