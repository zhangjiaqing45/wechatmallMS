package com.fante.project.business.pmsProductAttribute.controller;

import java.util.List;

import com.fante.common.business.enums.ProductAttributeCategoryConst;
import com.fante.common.utils.Checker;
import com.fante.common.utils.StringUtils;
import com.fante.project.system.user.domain.User;
import com.github.pagehelper.PageHelper;
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
import com.fante.project.business.pmsProductAttribute.domain.PmsProductAttribute;
import com.fante.project.business.pmsProductAttribute.service.IPmsProductAttributeService;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.common.utils.poi.ExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fante.framework.web.page.TableDataInfo;

/**
 * 商品属性＆参数Controller
 *
 * @author fante
 * @date 2020-03-09
 */
@Api(tags = "PmsProductAttributeController", description = "商品属性＆参数")
@Controller
@RequestMapping("/business/pmsProductAttribute")
public class PmsProductAttributeController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(PmsProductAttributeController.class);

    private String prefix = "business/pmsProductAttribute";

    @Autowired
    private IPmsProductAttributeService pmsProductAttributeService;

    @RequiresPermissions("business:pmsProductAttribute:view")
    @GetMapping()
    public String pmsProductAttribute() {
        return prefix + "/pmsProductAttribute";
    }

    @ApiOperation("条件查询商品属性＆参数列表")
    @RequiresPermissions("business:pmsProductAttribute:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PmsProductAttribute pmsProductAttribute) {
        User user = getSysUser();
        if (user.isFranchisee()) {
            pmsProductAttribute.setShopId(user.getDeptId());
        }
        startPage();
        List<PmsProductAttribute> list = pmsProductAttributeService.selectPmsProductAttributeList(pmsProductAttribute);
        return getDataTable(list);
    }

    @ApiOperation("根据属性类型查询所有属性和参数列表")
    @RequiresPermissions("business:pmsProductAttribute:list")
    @GetMapping("/listByAttrCateId")
    @ResponseBody
    public TableDataInfo list(Long attrCateId) {
        PmsProductAttribute param = new PmsProductAttribute();
        User user = getSysUser();
        if (user.isFranchisee()) {
            param.setShopId(user.getDeptId());
        }
        param.setProductAttributeCategoryId(attrCateId);
        PageHelper.startPage(1, 9999, "sort asc");
        List<PmsProductAttribute> list = pmsProductAttributeService.selectPmsProductAttributeList(param);
        return getDataTable(list);
    }

    @ApiOperation("导出商品属性＆参数列表")
    @RequiresPermissions("business:pmsProductAttribute:export")
    @Log(title = "商品属性＆参数", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PmsProductAttribute pmsProductAttribute) {
        User user = getSysUser();
        if (user.isFranchisee()) {
            pmsProductAttribute.setShopId(user.getDeptId());
        }
        List<PmsProductAttribute> list = pmsProductAttributeService.selectPmsProductAttributeList(pmsProductAttribute);
        ExcelUtil<PmsProductAttribute> util = new ExcelUtil<PmsProductAttribute>(PmsProductAttribute. class);
        return util.exportExcel(list, "pmsProductAttribute");
    }

    @ApiOperation("[跳转] 到新增商品属性＆参数页面")
    @GetMapping("/add/{type}/{parentId}")
    public String add(@PathVariable("type") Long type,@PathVariable("parentId") Long parentId, ModelMap mmap) {
        mmap.put("parentId",parentId);
        mmap.put("typeId",type);
        return prefix + "/add";
    }

    @ApiOperation("新增保存商品属性＆参数")
    @RequiresPermissions("business:pmsProductAttribute:add")
    @Log(title = "商品属性＆参数", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PmsProductAttribute pmsProductAttribute) {
        return toAjax(pmsProductAttributeService.insertPmsProductAttribute(pmsProductAttribute));
    }

    @ApiOperation("[跳转] 到商品属性＆参数编辑页面")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        PmsProductAttribute pmsProductAttribute =pmsProductAttributeService.selectPmsProductAttributeById(id);
        mmap.put("pmsProductAttribute", pmsProductAttribute);
        return prefix + "/edit";
    }

    @ApiOperation("修改保存商品属性＆参数")
    @RequiresPermissions("business:pmsProductAttribute:edit")
    @Log(title = "商品属性＆参数", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PmsProductAttribute pmsProductAttribute) {
        return toAjax(pmsProductAttributeService.updatePmsProductAttribute(pmsProductAttribute));
    }

    @ApiOperation("批量删除商品属性＆参数")
    @RequiresPermissions("business:pmsProductAttribute:remove")
    @Log(title = "商品属性＆参数", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(pmsProductAttributeService.deletePmsProductAttributeByIds(ids));
    }

    @ApiOperation("唯一校验")
    @RequiresPermissions("business:pmsProductAttribute:add")
    @Log(title = "商品属性＆参数", businessType = BusinessType.INSERT)
    @PostMapping("/checkUnique")
    @ResponseBody
    public String checkPmsProductAttributeUnique(PmsProductAttribute pmsProductAttribute) {
        return pmsProductAttributeService.checkPmsProductAttributeUnique(pmsProductAttribute);
    }
}
