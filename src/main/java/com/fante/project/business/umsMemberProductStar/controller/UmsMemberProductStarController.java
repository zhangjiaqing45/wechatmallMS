package com.fante.project.business.umsMemberProductStar.controller;

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
import com.fante.project.business.umsMemberProductStar.domain.UmsMemberProductStar;
import com.fante.project.business.umsMemberProductStar.service.IUmsMemberProductStarService;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.common.utils.poi.ExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fante.framework.web.page.TableDataInfo;

/**
 * 商品收藏表Controller
 *
 * @author fante
 * @date 2020-04-24
 */
@Api(tags = "UmsMemberProductStarController", description = "商品收藏表")
@Controller
@RequestMapping("/business/umsMemberProductStar")
public class UmsMemberProductStarController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(UmsMemberProductStarController.class);

    private String prefix = "business/umsMemberProductStar";

    @Autowired
    private IUmsMemberProductStarService umsMemberProductStarService;

    @RequiresPermissions("business:umsMemberProductStar:view")
    @GetMapping()
    public String umsMemberProductStar() {
        return prefix + "/umsMemberProductStar";
    }

    @ApiOperation("条件查询商品收藏表列表")
    @RequiresPermissions("business:umsMemberProductStar:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(UmsMemberProductStar umsMemberProductStar) {
        startPage();
        List<UmsMemberProductStar> list = umsMemberProductStarService.selectUmsMemberProductStarList(umsMemberProductStar);
        return getDataTable(list);
    }

    @ApiOperation("导出商品收藏表列表")
    @RequiresPermissions("business:umsMemberProductStar:export")
    @Log(title = "商品收藏表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(UmsMemberProductStar umsMemberProductStar) {
        List<UmsMemberProductStar> list = umsMemberProductStarService.selectUmsMemberProductStarList(umsMemberProductStar);
        ExcelUtil<UmsMemberProductStar> util = new ExcelUtil<UmsMemberProductStar>(UmsMemberProductStar. class);
        return util.exportExcel(list, "umsMemberProductStar");
    }

    @ApiOperation("[跳转] 到新增商品收藏表页面")
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    @ApiOperation("新增保存商品收藏表")
    @RequiresPermissions("business:umsMemberProductStar:add")
    @Log(title = "商品收藏表", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(UmsMemberProductStar umsMemberProductStar) {
        return toAjax(umsMemberProductStarService.insertUmsMemberProductStar(umsMemberProductStar));
    }

    @ApiOperation("[跳转] 到商品收藏表编辑页面")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        UmsMemberProductStar umsMemberProductStar =umsMemberProductStarService.selectUmsMemberProductStarById(id);
        mmap.put("umsMemberProductStar", umsMemberProductStar);
        return prefix + "/edit";
    }

    @ApiOperation("修改保存商品收藏表")
    @RequiresPermissions("business:umsMemberProductStar:edit")
    @Log(title = "商品收藏表", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(UmsMemberProductStar umsMemberProductStar) {
        return toAjax(umsMemberProductStarService.updateUmsMemberProductStar(umsMemberProductStar));
    }

    @ApiOperation("批量删除商品收藏表")
    @RequiresPermissions("business:umsMemberProductStar:remove")
    @Log(title = "商品收藏表", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(umsMemberProductStarService.deleteUmsMemberProductStarByIds(ids));
    }

    @ApiOperation("商品收藏表唯一校验")
    @PostMapping("/checkUnique")
    @ResponseBody
    public String checkUnique(UmsMemberProductStar umsMemberProductStar) {
        return umsMemberProductStarService.checkUmsMemberProductStarUnique(umsMemberProductStar);
    }

}
