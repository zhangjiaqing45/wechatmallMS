package com.fante.project.business.cmsDocument.controller;

import com.fante.common.utils.Checker;
import com.fante.common.utils.poi.ExcelUtil;
import com.fante.framework.aspectj.lang.annotation.Log;
import com.fante.framework.aspectj.lang.enums.BusinessType;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.framework.web.page.TableDataInfo;
import com.fante.project.business.cmsDocument.domain.CmsDocument;
import com.fante.project.business.cmsDocument.service.ICmsDocumentService;
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
 * 文档管理Controller
 *
 * @author fante
 * @date 2020-04-08
 */
@Api(tags = "CmsDocumentController", description = "文档管理")
@Controller
@RequestMapping("/business/cmsDocument")
public class CmsDocumentController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(CmsDocumentController.class);

    private String prefix = "business/cmsDocument";

    @Autowired
    private ICmsDocumentService cmsDocumentService;

    @RequiresPermissions("business:cmsDocument:view")
    @GetMapping()
    public String cmsDocument(ModelMap map) {
        map.put("isAdmin", getSysUser().isAdmin());
        return prefix + "/cmsDocument";
    }

    @ApiOperation("条件查询文档管理列表")
    @RequiresPermissions("business:cmsDocument:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CmsDocument cmsDocument) {
        startPage();
        List<CmsDocument> list = cmsDocumentService.selectCmsDocumentList(cmsDocument);
        return getDataTable(list);
    }

    @ApiOperation("导出文档管理列表")
    @RequiresPermissions("business:cmsDocument:export")
    @Log(title = "文档管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CmsDocument cmsDocument) {
        List<CmsDocument> list = cmsDocumentService.selectCmsDocumentList(cmsDocument);
        ExcelUtil<CmsDocument> util = new ExcelUtil<CmsDocument>(CmsDocument. class);
        return util.exportExcel(list, "cmsDocument");
    }

    @ApiOperation("[跳转] 到新增文档管理页面")
    @GetMapping("/add")
    public String add(ModelMap map) {
        Checker.checkOp(getSysUser().isAdmin(), "登录用户无此权限");
        return prefix + "/add";
    }

    @ApiOperation("新增保存文档管理")
    @RequiresPermissions("business:cmsDocument:add")
    @Log(title = "文档管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CmsDocument cmsDocument) {
        return toAjax(cmsDocumentService.insertCmsDocument(cmsDocument));
    }

    @ApiOperation("[跳转] 到文档管理编辑页面")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        CmsDocument cmsDocument =cmsDocumentService.selectCmsDocumentById(id);
        mmap.put("cmsDocument", cmsDocument);
        mmap.put("isAdmin", getSysUser().isAdmin());
        return prefix + "/edit";
    }

    @ApiOperation("修改保存文档管理")
    @RequiresPermissions("business:cmsDocument:edit")
    @Log(title = "文档管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CmsDocument cmsDocument) {
        return toAjax(cmsDocumentService.updateCmsDocument(cmsDocument));
    }

    @ApiOperation("批量删除文档管理")
    @RequiresPermissions("business:cmsDocument:remove")
    @Log(title = "文档管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(cmsDocumentService.deleteCmsDocumentByIds(ids));
    }

    @ApiOperation("文档管理唯一校验")
    @PostMapping("/checkUnique")
    @ResponseBody
    public String checkUnique(CmsDocument cmsDocument) {
        return cmsDocumentService.checkCmsDocumentUnique(cmsDocument);
    }

}
