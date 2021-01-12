package com.fante.project.business.umsDistribution.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fante.common.business.enums.UmsMemberConst;
import com.fante.project.business.umsDistribution.domain.UmsDistributionResult;
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
import com.fante.project.business.umsDistribution.domain.UmsDistribution;
import com.fante.project.business.umsDistribution.service.IUmsDistributionService;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.common.utils.poi.ExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fante.framework.web.page.TableDataInfo;

/**
 * 分销比例商品角色关系Controller
 *
 * @author fante
 * @date 2020-04-30
 */
@Api(tags = "UmsDistributionController", description = "分销比例商品角色关系")
@Controller
@RequestMapping("/business/umsDistribution")
public class UmsDistributionController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(UmsDistributionController.class);

    private String prefix = "business/umsDistribution";

    @Autowired
    private IUmsDistributionService umsDistributionService;

    @RequiresPermissions("business:umsDistribution:view")
    @GetMapping()
    public String umsDistribution() {
        return prefix + "/umsDistribution";
    }

    @ApiOperation("条件查询分销比例商品角色关系列表")
    @RequiresPermissions("business:umsDistribution:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(UmsDistribution umsDistribution) {
        startPage();
        List<UmsDistribution> list = umsDistributionService.selectUmsDistributionList(umsDistribution);
        return getDataTable(list);
    }

    @ApiOperation("导出分销比例商品角色关系列表")
    @RequiresPermissions("business:umsDistribution:export")
    @Log(title = "分销比例商品角色关系", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(UmsDistribution umsDistribution) {
        List<UmsDistribution> list = umsDistributionService.selectUmsDistributionList(umsDistribution);
        ExcelUtil<UmsDistribution> util = new ExcelUtil<UmsDistribution>(UmsDistribution. class);
        return util.exportExcel(list, "umsDistribution");
    }

    @ApiOperation("[跳转] 到新增分销比例商品角色关系页面")
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    @ApiOperation("新增保存分销比例商品角色关系")
    @RequiresPermissions("business:umsDistribution:add")
    @Log(title = "分销比例商品角色关系", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(UmsDistribution umsDistribution) {
        return toAjax(umsDistributionService.insertUmsDistribution(umsDistribution));
    }

    @ApiOperation("[跳转] 到分销比例商品角色关系编辑页面")
    @GetMapping("/distribution/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        List<UmsDistributionResult> distributionList = umsDistributionService.selectUmsDistributionByProudctId(id);
        mmap.put("distributionList", distributionList);
        return prefix + "/edit";
    }

    @ApiOperation("修改保存分销比例商品角色关系")
    @RequiresPermissions("business:umsDistribution:edit")
    @Log(title = "分销比例商品角色关系", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(UmsDistribution umsDistribution) {
        return toAjax(umsDistributionService.updateUmsDistribution(umsDistribution));
    }

    @ApiOperation("批量删除分销比例商品角色关系")
    @RequiresPermissions("business:umsDistribution:remove")
    @Log(title = "分销比例商品角色关系", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(umsDistributionService.deleteUmsDistributionByIds(ids));
    }

    @ApiOperation("分销比例商品角色关系唯一校验")
    @PostMapping("/checkUnique")
    @ResponseBody
    public String checkUnique(UmsDistribution umsDistribution) {
        return umsDistributionService.checkUmsDistributionUnique(umsDistribution);
    }

}
