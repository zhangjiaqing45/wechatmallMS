package com.fante.project.business.omsOrderSendCompany.controller;

import java.util.List;

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
import com.fante.project.business.omsOrderSendCompany.domain.OmsOrderSendCompany;
import com.fante.project.business.omsOrderSendCompany.service.IOmsOrderSendCompanyService;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.common.utils.poi.ExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fante.framework.web.page.TableDataInfo;

/**
 * 物流公司Controller
 *
 * @author fante
 * @date 2020-04-02
 */
@Api(tags = "OmsOrderSendCompanyController", description = "物流公司")
@Controller
@RequestMapping("/business/omsOrderSendCompany")
public class OmsOrderSendCompanyController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(OmsOrderSendCompanyController.class);

    private String prefix = "business/omsOrderSendCompany";

    @Autowired
    private IOmsOrderSendCompanyService omsOrderSendCompanyService;

    @RequiresPermissions("business:omsOrderSendCompany:view")
    @GetMapping()
    public String omsOrderSendCompany() {
        return prefix + "/omsOrderSendCompany";
    }

    @ApiOperation("条件查询物流公司列表")
    @RequiresPermissions("business:omsOrderSendCompany:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(OmsOrderSendCompany omsOrderSendCompany) {
        startPage();
        List<OmsOrderSendCompany> list = omsOrderSendCompanyService.selectOmsOrderSendCompanyList(omsOrderSendCompany);
        return getDataTable(list);
    }

    @ApiOperation("导出物流公司列表")
    @RequiresPermissions("business:omsOrderSendCompany:export")
    @Log(title = "物流公司", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OmsOrderSendCompany omsOrderSendCompany) {
        List<OmsOrderSendCompany> list = omsOrderSendCompanyService.selectOmsOrderSendCompanyList(omsOrderSendCompany);
        ExcelUtil<OmsOrderSendCompany> util = new ExcelUtil<OmsOrderSendCompany>(OmsOrderSendCompany. class);
        return util.exportExcel(list, "omsOrderSendCompany");
    }

    @ApiOperation("[跳转] 到新增物流公司页面")
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    @ApiOperation("新增保存物流公司")
    @RequiresPermissions("business:omsOrderSendCompany:add")
    @Log(title = "物流公司", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(OmsOrderSendCompany omsOrderSendCompany) {
        return toAjax(omsOrderSendCompanyService.insertOmsOrderSendCompany(omsOrderSendCompany));
    }

    @ApiOperation("[跳转] 到物流公司编辑页面")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        OmsOrderSendCompany omsOrderSendCompany =omsOrderSendCompanyService.selectOmsOrderSendCompanyById(id);
        mmap.put("omsOrderSendCompany", omsOrderSendCompany);
        return prefix + "/edit";
    }

    @ApiOperation("修改保存物流公司")
    @RequiresPermissions("business:omsOrderSendCompany:edit")
    @Log(title = "物流公司", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(OmsOrderSendCompany omsOrderSendCompany) {
        return toAjax(omsOrderSendCompanyService.updateOmsOrderSendCompany(omsOrderSendCompany));
    }

    @ApiOperation("批量删除物流公司")
    @RequiresPermissions("business:omsOrderSendCompany:remove")
    @Log(title = "物流公司", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(omsOrderSendCompanyService.deleteOmsOrderSendCompanyByIds(ids));
    }

    @ApiOperation("物流公司唯一校验")
    @PostMapping("/checkUnique")
    @ResponseBody
    public String checkUnique(OmsOrderSendCompany omsOrderSendCompany) {
        //设置店铺id
        omsOrderSendCompany.setShopId(getSysUser().getDeptId());
        return omsOrderSendCompanyService.checkOmsOrderSendCompanyUnique(omsOrderSendCompany);
    }

}
