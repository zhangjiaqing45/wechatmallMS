package com.fante.project.business.pmsIntegralFeightTemplate.controller;

import com.fante.framework.aspectj.lang.annotation.Log;
import com.fante.framework.aspectj.lang.enums.BusinessType;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.project.business.pmsIntegralFeightTemplate.domain.PmsIntegralFeightTemplate;
import com.fante.project.business.pmsIntegralFeightTemplate.service.IPmsIntegralFeightTemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 积分商品运费设置Controller
 *
 * @author fante
 * @date 2020-04-13
 */
@Api(tags = "PmsIntegralFeightTemplateController", description = "积分商品运费设置")
@Controller
@RequestMapping("/business/pmsIntegralFeightTemplate")
public class PmsIntegralFeightTemplateController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(PmsIntegralFeightTemplateController.class);

    private String prefix = "business/pmsIntegralFeightTemplate";

    @Autowired
    private IPmsIntegralFeightTemplateService pmsIntegralFeightTemplateService;

    @RequiresPermissions("business:pmsIntegralFeightTemplate:view")
    @GetMapping()
    public String pmsIntegralFeightTemplate(ModelMap map) {
        PmsIntegralFeightTemplate template = pmsIntegralFeightTemplateService.selectPmsIntegralFeightTemplateRecent();
        map.put("template", template);
        return prefix + "/pmsIntegralFeightTemplate";
    }

    @ApiOperation("新增保存积分商品运费设置")
    @RequiresPermissions("business:pmsIntegralFeightTemplate:add")
    @Log(title = "积分商品运费设置", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PmsIntegralFeightTemplate pmsIntegralFeightTemplate) {
        return toAjax(pmsIntegralFeightTemplateService.insertPmsIntegralFeightTemplate(pmsIntegralFeightTemplate));
    }


    //@ApiOperation("条件查询积分商品运费设置列表")
    //@RequiresPermissions("business:pmsIntegralFeightTemplate:list")
    //@PostMapping("/list")
    //@ResponseBody
    //public TableDataInfo list(PmsIntegralFeightTemplate pmsIntegralFeightTemplate) {
    //    startPage();
    //    List<PmsIntegralFeightTemplate> list = pmsIntegralFeightTemplateService.selectPmsIntegralFeightTemplateList(pmsIntegralFeightTemplate);
    //    return getDataTable(list);
    //}
    //
    //@ApiOperation("导出积分商品运费设置列表")
    //@RequiresPermissions("business:pmsIntegralFeightTemplate:export")
    //@Log(title = "积分商品运费设置", businessType = BusinessType.EXPORT)
    //@PostMapping("/export")
    //@ResponseBody
    //public AjaxResult export(PmsIntegralFeightTemplate pmsIntegralFeightTemplate) {
    //    List<PmsIntegralFeightTemplate> list = pmsIntegralFeightTemplateService.selectPmsIntegralFeightTemplateList(pmsIntegralFeightTemplate);
    //    ExcelUtil<PmsIntegralFeightTemplate> util = new ExcelUtil<PmsIntegralFeightTemplate>(PmsIntegralFeightTemplate. class);
    //    return util.exportExcel(list, "pmsIntegralFeightTemplate");
    //}
    //
    //@ApiOperation("[跳转] 到新增积分商品运费设置页面")
    //@GetMapping("/add")
    //public String add() {
    //    return prefix + "/add";
    //}
    //
    //@ApiOperation("[跳转] 到积分商品运费设置编辑页面")
    //@GetMapping("/edit/{id}")
    //public String edit(@PathVariable("id") Long id, ModelMap mmap) {
    //    PmsIntegralFeightTemplate pmsIntegralFeightTemplate =pmsIntegralFeightTemplateService.selectPmsIntegralFeightTemplateById(id);
    //    mmap.put("pmsIntegralFeightTemplate", pmsIntegralFeightTemplate);
    //    return prefix + "/edit";
    //}
    //
    //@ApiOperation("修改保存积分商品运费设置")
    //@RequiresPermissions("business:pmsIntegralFeightTemplate:edit")
    //@Log(title = "积分商品运费设置", businessType = BusinessType.UPDATE)
    //@PostMapping("/edit")
    //@ResponseBody
    //public AjaxResult editSave(PmsIntegralFeightTemplate pmsIntegralFeightTemplate) {
    //    return toAjax(pmsIntegralFeightTemplateService.updatePmsIntegralFeightTemplate(pmsIntegralFeightTemplate));
    //}
    //
    //@ApiOperation("批量删除积分商品运费设置")
    //@RequiresPermissions("business:pmsIntegralFeightTemplate:remove")
    //@Log(title = "积分商品运费设置", businessType = BusinessType.DELETE)
    //@PostMapping("/remove")
    //@ResponseBody
    //public AjaxResult remove(String ids) {
    //    return toAjax(pmsIntegralFeightTemplateService.deletePmsIntegralFeightTemplateByIds(ids));
    //}
    //
    //@ApiOperation("积分商品运费设置唯一校验")
    //@PostMapping("/checkUnique")
    //@ResponseBody
    //public String checkUnique(PmsIntegralFeightTemplate pmsIntegralFeightTemplate) {
    //    return pmsIntegralFeightTemplateService.checkPmsIntegralFeightTemplateUnique(pmsIntegralFeightTemplate);
    //}

}
