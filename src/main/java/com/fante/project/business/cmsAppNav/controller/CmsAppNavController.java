package com.fante.project.business.cmsAppNav.controller;

import com.fante.common.business.enums.CommonUse;
import com.fante.common.utils.Checker;
import com.fante.common.utils.poi.ExcelUtil;
import com.fante.framework.aspectj.lang.annotation.Log;
import com.fante.framework.aspectj.lang.enums.BusinessType;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.framework.web.page.TableDataInfo;
import com.fante.project.business.cmsAppNav.domain.CmsAppNav;
import com.fante.project.business.cmsAppNav.service.ICmsAppNavService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 前端导航管理Controller
 *
 * @author fante
 * @date 2020-04-21
 */
@Api(tags = "CmsAppNavController", description = "前端导航管理")
@Controller
@RequestMapping("/business/cmsAppNav")
public class CmsAppNavController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(CmsAppNavController.class);

    private String prefix = "business/cmsAppNav";

    @Autowired
    private ICmsAppNavService cmsAppNavService;

    @RequiresPermissions("business:cmsAppNav:view")
    @GetMapping()
    public String cmsAppNav(ModelMap map) {
        map.put("isAdmin", getSysUser().isAdmin());
        map.put("enable", CommonUse.Status.ENABLE.getType());
        return prefix + "/cmsAppNav";
    }

    @ApiOperation("条件查询前端导航管理列表")
    @RequiresPermissions("business:cmsAppNav:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CmsAppNav cmsAppNav) {
        startPage();
        List<CmsAppNav> list = cmsAppNavService.selectCmsAppNavList(cmsAppNav);
        return getDataTable(list);
    }

    @ApiOperation("导出前端导航管理列表")
    @RequiresPermissions("business:cmsAppNav:export")
    @Log(title = "前端导航管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CmsAppNav cmsAppNav) {
        List<CmsAppNav> list = cmsAppNavService.selectCmsAppNavList(cmsAppNav);
        ExcelUtil<CmsAppNav> util = new ExcelUtil<CmsAppNav>(CmsAppNav. class);
        return util.exportExcel(list, "cmsAppNav");
    }

    @ApiOperation("[跳转] 到新增前端导航管理页面")
    @GetMapping("/add")
    public String add(ModelMap map) {
        Checker.checkOp(getSysUser().isAdmin(), "登录用户无新增导航权限");
        return prefix + "/add";
    }

    @ApiOperation("新增保存前端导航管理")
    @RequiresPermissions("business:cmsAppNav:add")
    @Log(title = "前端导航管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CmsAppNav cmsAppNav) {
        return toAjax(cmsAppNavService.insertCmsAppNav(cmsAppNav));
    }

    @ApiOperation("[跳转] 到前端导航管理编辑页面")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        mmap.put("isAdmin", getSysUser().isAdmin());
        CmsAppNav cmsAppNav =cmsAppNavService.selectCmsAppNavById(id);
        mmap.put("cmsAppNav", cmsAppNav);
        return prefix + "/edit";
    }

    @ApiOperation("修改保存前端导航管理")
    @RequiresPermissions("business:cmsAppNav:edit")
    @Log(title = "前端导航管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CmsAppNav cmsAppNav) {
        return toAjax(cmsAppNavService.updateCmsAppNav(cmsAppNav));
    }

    @ApiOperation("批量删除前端导航管理")
    @RequiresPermissions("business:cmsAppNav:remove")
    @Log(title = "前端导航管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        Checker.checkOp(getSysUser().isAdmin(), "登录用户无删除导航权限");
        return toAjax(cmsAppNavService.deleteCmsAppNavByIds(ids));
    }

    @ApiOperation("前端导航管理唯一校验")
    @PostMapping("/checkUnique")
    @ResponseBody
    public String checkUnique(CmsAppNav cmsAppNav) {
        return cmsAppNavService.checkCmsAppNavUnique(cmsAppNav);
    }


    @ApiOperation("前端导航停用")
    @Log(title = "前端导航停用", businessType = BusinessType.UPDATE)
    @PostMapping("/stateOff")
    @ResponseBody
    public AjaxResult changeProductOff(Long id) {
        return cmsAppNavService.changeStatus(id, CommonUse.Status.DISABLE.getType()) > 0
                ? AjaxResult.success("前端导航停用成功")
                : AjaxResult.error("前端导航停用失败");
    }

    @ApiOperation("前端导航启用")
    @Log(title = "前端导航启用", businessType = BusinessType.UPDATE)
    @PostMapping("/stateOn")
    @ResponseBody
    public AjaxResult changeProductOn(Long id) {
        return cmsAppNavService.changeStatus(id, CommonUse.Status.ENABLE.getType()) > 0
                ? AjaxResult.success("前端导航启用成功")
                : AjaxResult.error("前端导航启用失败");
    }

    @ApiOperation("设置前端导航排序")
    @Log(title = "设置前端导航排序", businessType = BusinessType.UPDATE)
    @PostMapping("/changeSort")
    @ResponseBody
    public AjaxResult changeSort(Long id, Long sort) {
        return cmsAppNavService.changeSort(id, sort) > 0
                ? AjaxResult.success("设置排序成功")
                : AjaxResult.error("设置排序失败");
    }
}
