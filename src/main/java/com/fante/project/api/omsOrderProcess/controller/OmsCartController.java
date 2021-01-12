package com.fante.project.api.omsOrderProcess.controller;

import com.fante.common.business.enums.OrderConst;
import com.fante.framework.aspectj.lang.annotation.Log;
import com.fante.framework.aspectj.lang.enums.BusinessType;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.framework.web.page.TableDataInfo;
import com.fante.project.api.omsOrderProcess.service.ICartService;
import com.fante.project.business.omsCartItem.domain.OmsCartItemParam;
import com.fante.project.business.omsCartItem.domain.OmsCartItemResult;
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
@Api(tags = "OmsCartController", description = "购物车")
@Controller
@RequestMapping("/api/cart")
public class OmsCartController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(OmsCartController.class);

    @Autowired
    private ICartService iCartService;

    @ApiOperation("查询用户所有购物车")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list() {
        startPage();
        List<OmsCartItemResult> list = iCartService.getMemberCartItems(getTokenUserId());
        return getDataTable(list);
    }

    @ApiOperation("删除购物车")
    @Log(title = "购物车", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(iCartService.delMemberCarts(ids, getTokenUserId()));
    }

    @ApiOperation("普通购买:添加购物车")
    @Log(title = "购物车", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult add(OmsCartItemParam cartParam) {
        cartParam.setCartType(OrderConst.CartType.CART.getType());
        cartParam.setMemberId(getTokenUserId());
        return toAjax(iCartService.add(cartParam));
    }

    @ApiOperation("立即购买:添加购物车")
    @Log(title = "购物车", businessType = BusinessType.INSERT)
    @PostMapping("/addBuyNow")
    @ResponseBody
    public AjaxResult addBuyNow(OmsCartItemParam cartParam) {
        cartParam.setMemberId(getTokenUserId());
        //设置状态立即购买
        cartParam.setCartType(OrderConst.CartType.BUY_NOW.getType());
        //1.添加购物车：返回购物车Id
        return toAjax(iCartService.add(cartParam)).put("cartId", cartParam.getCartId());
    }


    @ApiOperation("修改购物车数量")
    @Log(title = "购物车", businessType = BusinessType.UPDATE)
    @PostMapping("/quantity")
    @ResponseBody
    public AjaxResult changeQuantity(OmsCartItemParam cartParam) {
        cartParam.setMemberId(getTokenUserId());
        return toAjax(iCartService.changeQuantity(cartParam));
    }

}
