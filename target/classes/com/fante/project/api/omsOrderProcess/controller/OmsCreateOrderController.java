package com.fante.project.api.omsOrderProcess.controller;

import com.alibaba.fastjson.JSONArray;
import com.fante.common.business.enums.SmsCouponConst;
import com.fante.common.business.enums.UmsAddressConst;
import com.fante.common.utils.Checker;
import com.fante.common.utils.StringUtils;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.project.api.mq.resp.OrderHandleResult;
import com.fante.project.api.omsOrderProcess.domain.AddressParam;
import com.fante.project.api.omsOrderProcess.domain.ConfirmOrderParam;
import com.fante.project.api.omsOrderProcess.domain.OmsOrderShow;
import com.fante.project.api.omsOrderProcess.service.IOmsConfirmOrderService;
import com.fante.project.api.omsOrderProcess.service.IOmsPreviewOrderService;
import com.fante.project.api.omsOrderProcess.service.OmsOrderCallBackService;
import com.fante.project.api.umsProcess.controller.WeixinUserController;
import com.fante.project.api.umsProcess.service.UmsMemberProcessService;
import com.fante.project.api.utils.OrderRedis;
import com.fante.project.business.omsCartItem.domain.OmsCartItemParam;
import com.fante.project.business.umsMember.domain.UmsMember;
import com.fante.project.business.umsMemberReceiveAddress.domain.UmsMemberReceiveAddress;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单Controller
 *
 * @author fante
 * @date 2020-04-01
 */
@Api(tags = "OmsCreateOrderController", description = "创建订单")
@Controller
@RequestMapping("/api/buy")
public class OmsCreateOrderController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(OmsCreateOrderController.class);
    /**
     * 预览订单信息服务
     */
    @Autowired
    private IOmsPreviewOrderService iOmsPreviewOrderService;
    /**
     * 下单服务
     */
    @Autowired
    private IOmsConfirmOrderService iOmsConfirmOrderService;
    /**
     * 公众号用户接口
     */
    @Autowired
    private UmsMemberProcessService umsMemberProcessService;
    /**
     * redis工具
     */
    @Autowired
    private OrderRedis orderRedis;
    /**
     * redis工具
     */
    @Autowired
    private OmsOrderCallBackService omsOrderCallBackService;

    /*********************************生产预览订单信息******************************/
    @ApiOperation("[结算]订单:购物车")
    @PostMapping("/createCartPreview")
    @ResponseBody
    public AjaxResult createByCart(String ids, Long addressId) {
        List<OmsOrderShow> preview = iOmsPreviewOrderService.createPreviewByCart(ids, getTokenUserId(), addressId);
        UmsMemberReceiveAddress defaultAddress = preview.get(0).getDefaultAddress();
        String noSendProductIds = preview.stream().map(x -> x.getErrMsg().get(UmsAddressConst.NO_SEND))
                .filter(StringUtils::isNotEmpty)
                .reduce((result, item) -> result + "," + item).orElse(StringUtils.EMPTY);

        return AjaxResult.success().put("previewList", preview)
                .put("address", defaultAddress)
                .put(UmsAddressConst.NO_SEND,noSendProductIds)
                .put("couponUseTypes", SmsCouponConst.useTypeEnum.toMap());
    }

    @ApiOperation("[结算]订单:立即购买")
    @PostMapping("/createNowPreview")
    @ResponseBody
    public AjaxResult createNow(String ids, Long addressId) {
        List<OmsOrderShow> preview = iOmsPreviewOrderService.createPreviewByNow(ids, getTokenUserId(), addressId);
        OmsOrderShow show = preview.get(0);
        return AjaxResult.success().put("previewList", preview)
                .put("address", show.getDefaultAddress())
                .put(UmsAddressConst.NO_SEND,show.getErrMsg())
                .put("couponUseTypes", SmsCouponConst.useTypeEnum.toMap());
    }

    @ApiOperation("[结算]订单:团购")
    @PostMapping("/createGroupPreview")
    @ResponseBody
    public AjaxResult createByGroup(OmsCartItemParam param) {
        param.setMemberId(getTokenUserId());
        return AjaxResult.success( iOmsPreviewOrderService.createPreviewByGroup(param));
    }

    @ApiOperation("[结算]订单:秒杀")
    @PostMapping("/createSeckillPreview")
    @ResponseBody
    public AjaxResult createBySeckill(OmsCartItemParam param) {
        param.setMemberId(getTokenUserId());
        return AjaxResult.success(iOmsPreviewOrderService.createPreviewBySeckill(param));
    }

    /*********************************生产订单******************************************/
    @ApiOperation("[确认]下单:购物车")
    @PostMapping("/confirmOfCart")
    @ResponseBody
    public AjaxResult confirmByCart(String paramListStr/*List<ConfirmOrderParam> params*/) {
        System.out.println("paramListStr............="+paramListStr);
        JSONArray jsonArray = JSONArray.parseArray(paramListStr);
        List<ConfirmOrderParam> params = jsonArray.toJavaList(ConfirmOrderParam.class);
        UmsMember member = umsMemberProcessService.get(getTokenClientId());
        params.forEach(param->
                param.setMember(member)
                );
        return AjaxResult.success(iOmsConfirmOrderService.confirmByCart(params, member.getId()));
    }

    @ApiOperation("[确认]下单:立即购买")
    @PostMapping("/confirmOfNow")
    @ResponseBody
    public AjaxResult confirmOfNow(String paramListStr) {
        System.out.println("paramListStr="+paramListStr);
        JSONArray jsonArray = JSONArray.parseArray(paramListStr);
        List<ConfirmOrderParam> params = jsonArray.toJavaList(ConfirmOrderParam.class);
        ConfirmOrderParam orderParam = params.get(0);
        orderParam.setMember(umsMemberProcessService.get(getTokenClientId()));
        return AjaxResult.success(iOmsConfirmOrderService.confirmByNow(orderParam));
    }

    @ApiOperation("[确认]下单:创建团购")
    @PostMapping("/confirmOfCreateGroup")
    @ResponseBody
    public AjaxResult confirmOfCreateGroup(ConfirmOrderParam param) {
        param.setInGroupId(null);
        param.setMember(umsMemberProcessService.get(getTokenClientId()));
        return AjaxResult.success(iOmsConfirmOrderService.confirmByGroup(param));
    }

    @ApiOperation("[确认]下单:加入团购")
    @PostMapping("/confirmOfJoinGroup")
    @ResponseBody
    public AjaxResult confirmOfJoinGroup(ConfirmOrderParam param) {
        Checker.check(ObjectUtils.isEmpty(param.getInGroupId()), "未选择要加入的团购");
        param.setMember(umsMemberProcessService.get(getTokenClientId()));
        return AjaxResult.success(iOmsConfirmOrderService.confirmByGroup(param));
    }

    @ApiOperation("[确认]下单:秒杀")
    @PostMapping("/confirmOfseckill")
    @ResponseBody
    public AjaxResult confirmOfseckill(ConfirmOrderParam param) {
        param.setMember(umsMemberProcessService.get(getTokenClientId()));
        return AjaxResult.success(iOmsConfirmOrderService.confirmBySeckill(param));
    }


    @ApiOperation("未支付订单确认下单")
    @PostMapping("/confirmAgain")
    @ResponseBody
    public AjaxResult confirmAgain(Long orderId) {
        return AjaxResult.success().put("payOrderSn", iOmsConfirmOrderService.confirmAgain(orderId));
    }

    /*********************************轮询订单******************************************/
    @ApiOperation("[确认]下单:轮询订单")
    @PostMapping("/rollPollOrder")
    @ResponseBody
    public OrderHandleResult rollPollOrder() {
        return orderRedis.getOrderStatusRedis(getTokenUserId());
    }

    /*********************************更换配送地址******************************************/
    @ApiOperation("下单页面:更换配送地址")
    @PostMapping("/changeAddress")
    @ResponseBody
    public AjaxResult changeAddress(AddressParam param) {
        param.setMemberId(getTokenUserId());
        return AjaxResult.success(iOmsPreviewOrderService.changeAddress(param));
    }

}
