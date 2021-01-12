package com.fante.project.business.pmsFeightTemplate.controller;

import com.fante.common.utils.Checker;
import com.fante.common.utils.poi.ExcelUtil;
import com.fante.framework.aspectj.lang.annotation.Log;
import com.fante.framework.aspectj.lang.enums.BusinessType;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.framework.web.page.TableDataInfo;
import com.fante.project.business.bizShopInfo.domain.BizShopInfo;
import com.fante.project.business.bizShopInfo.service.IBizShopInfoService;
import com.fante.project.business.pmsFeightTemplate.domain.PmsFeightTemplate;
import com.fante.project.business.pmsFeightTemplate.domain.PmsFeightTemplateUseDTO;
import com.fante.project.business.pmsFeightTemplate.service.IPmsFeightTemplateService;
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

/**
 * 运费模版Controller
 *
 * @author fante
 * @date 2020-03-16
 */
@Api(tags = "PmsFeightTemplateController", description = "运费模版")
@Controller
@RequestMapping("/business/pmsFeightTemplate")
public class PmsFeightTemplateController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(PmsFeightTemplateController.class);

    private String prefix = "business/pmsFeightTemplate";

    @Autowired
    private IPmsFeightTemplateService pmsFeightTemplateService;
    @Autowired
    private IBizShopInfoService bizShopInfoService;

    @RequiresPermissions("business:pmsFeightTemplate:view")
    @GetMapping()
    public String pmsFeightTemplate(ModelMap map) {
        map.put("isAdmin", getSysUser().isAdmin() || getSysUser().isSystem());
        return prefix + "/pmsFeightTemplate";
    }

    @ApiOperation("条件查询运费模版列表")
    @RequiresPermissions("business:pmsFeightTemplate:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PmsFeightTemplate pmsFeightTemplate) {
        startPage();
        if (getSysUser().isFranchisee()) {
            pmsFeightTemplate.setShopId(getSysUser().getDeptId());
        }
        List<PmsFeightTemplate> list = pmsFeightTemplateService.selectPmsFeightTemplateList(pmsFeightTemplate);
        return getDataTable(list);
    }

    @ApiOperation("导出运费模版列表")
    @RequiresPermissions("business:pmsFeightTemplate:export")
    @Log(title = "运费模版", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PmsFeightTemplate pmsFeightTemplate) {
        if (getSysUser().isFranchisee()) {
            pmsFeightTemplate.setShopId(getSysUser().getDeptId());
        }
        List<PmsFeightTemplate> list = pmsFeightTemplateService.selectPmsFeightTemplateList(pmsFeightTemplate);
        ExcelUtil<PmsFeightTemplate> util = new ExcelUtil<PmsFeightTemplate>(PmsFeightTemplate. class);
        return util.exportExcel(list, "运费模版");
    }

    @ApiOperation("[跳转] 到新增运费模版页面")
    @GetMapping("/add")
    public String add(ModelMap map) {
        map.put("isAdmin", getSysUser().isAdmin() || getSysUser().isSystem());
        if (getSysUser().isFranchisee()) {
            map.put("shop", bizShopInfoService.selectBizShopInfoById(getSysUser().getDeptId()));
        }
        return prefix + "/add";
    }

    @ApiOperation("新增保存运费模版")
    @RequiresPermissions("business:pmsFeightTemplate:add")
    @Log(title = "运费模版", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PmsFeightTemplate pmsFeightTemplate) {
        return toAjax(pmsFeightTemplateService.insertPmsFeightTemplate(pmsFeightTemplate));
    }

    @ApiOperation("[跳转] 到运费模版编辑页面")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        mmap.put("isAdmin", getSysUser().isAdmin() || getSysUser().isSystem());
        PmsFeightTemplate pmsFeightTemplate =pmsFeightTemplateService.selectPmsFeightTemplateById(id);
        BizShopInfo shop = null;
        if (!ObjectUtils.isEmpty(pmsFeightTemplate) && !ObjectUtils.isEmpty(pmsFeightTemplate.getShopId())) {
            shop = bizShopInfoService.selectBizShopInfoById(pmsFeightTemplate.getShopId());
        }
        mmap.put("pmsFeightTemplate", pmsFeightTemplate);
        mmap.put("shop", shop);
        return prefix + "/edit";
    }

    @ApiOperation("修改保存运费模版")
    @RequiresPermissions("business:pmsFeightTemplate:edit")
    @Log(title = "运费模版", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PmsFeightTemplate pmsFeightTemplate) {
        return toAjax(pmsFeightTemplateService.updatePmsFeightTemplate(pmsFeightTemplate));
    }

    @ApiOperation("批量删除运费模版")
    @RequiresPermissions("business:pmsFeightTemplate:remove")
    @Log(title = "运费模版", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(Long ids) {
        return toAjax(pmsFeightTemplateService.deletePmsFeightTemplateById(ids));
    }

    @ApiOperation("[跳转] 到运费模版使用情况")
    @GetMapping("/templateUse/{templateId}")
    public String templateUse(ModelMap map, @PathVariable("templateId") Long templateId) {
        map.put("templateId", templateId);
        return prefix + "/pmsFeightTemplateUse";
    }

    @ApiOperation("运费模版使用情况")
    @PostMapping("/templateUse")
    @ResponseBody
    public TableDataInfo templateUseList(PmsFeightTemplateUseDTO useDTO) {
        Checker.check(ObjectUtils.isEmpty(useDTO.getFeightTemplateId()), "缺少运费模板标识");
        startPage();
        List<PmsFeightTemplateUseDTO> list = pmsFeightTemplateService.selectTemplateUse(useDTO);
        return getDataTable(list);
    }

}
