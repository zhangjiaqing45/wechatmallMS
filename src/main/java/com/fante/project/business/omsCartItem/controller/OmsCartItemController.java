package com.fante.project.business.omsCartItem.controller;

import java.util.List;

import com.fante.project.business.omsCartItem.domain.OmsCartItemParam;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fante.framework.aspectj.lang.annotation.Log;
import com.fante.framework.aspectj.lang.enums.BusinessType;
import io.swagger.annotations.*;
import com.fante.project.business.omsCartItem.domain.OmsCartItem;
import com.fante.project.business.omsCartItem.service.IOmsCartItemService;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.common.utils.poi.ExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fante.framework.web.page.TableDataInfo;

/**
 * 购物车Controller
 *
 * @author fante
 * @date 2020-03-28
 */
@Api(tags = "OmsCartItemController", description = "购物车")
@Controller
@RequestMapping("/business/omsCartItem")
public class OmsCartItemController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(OmsCartItemController.class);

    private String prefix = "business/omsCartItem";

    @Autowired
    private IOmsCartItemService omsCartItemService;

    @RequiresPermissions("business:omsCartItem:view")
    @GetMapping()
    public String omsCartItem() {
        return prefix + "/omsCartItem";
    }

    @ApiOperation("条件查询购物车列表")
    @RequiresPermissions("business:omsCartItem:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(OmsCartItem omsCartItem) {
        startPage();
        List<OmsCartItem> list = omsCartItemService.selectOmsCartItemList(omsCartItem);
        return getDataTable(list);
    }

    @ApiOperation("新增保存购物车")
    @RequiresPermissions("business:omsCartItem:add")
    @Log(title = "购物车", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(OmsCartItemParam param) {
        param.setMemberId(getTokenUserId());
        return toAjax(omsCartItemService.add(param));
    }

    @ApiOperation("修改保存购物车")
    @RequiresPermissions("business:omsCartItem:edit")
    @Log(title = "购物车", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(OmsCartItem omsCartItem) {
        return toAjax(omsCartItemService.updateOmsCartItem(omsCartItem));
    }

  /*  @ApiOperation("批量删除购物车")
    @RequiresPermissions("business:omsCartItem:remove")
    @Log(title = "购物车", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(omsCartItemService.deleteOmsCartItemByIds(ids));
    }*/

}
