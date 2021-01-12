package com.fante.project.business.umsMemberShopStar.controller;

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
import com.fante.project.business.umsMemberShopStar.domain.UmsMemberShopStar;
import com.fante.project.business.umsMemberShopStar.service.IUmsMemberShopStarService;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.common.utils.poi.ExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fante.framework.web.page.TableDataInfo;

/**
 * 店铺收藏Controller
 *
 * @author fante
 * @date 2020-04-24
 */
@Api(tags = "UmsMemberShopStarController", description = "店铺收藏")
@Controller
@RequestMapping("/business/umsMemberShopStar")
public class UmsMemberShopStarController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(UmsMemberShopStarController.class);

    private String prefix = "business/umsMemberShopStar";

    @Autowired
    private IUmsMemberShopStarService umsMemberShopStarService;

    @RequiresPermissions("business:umsMemberShopStar:view")
    @GetMapping()
    public String umsMemberShopStar() {
        return prefix + "/umsMemberShopStar";
    }

    @ApiOperation("条件查询店铺收藏列表")
    @RequiresPermissions("business:umsMemberShopStar:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(UmsMemberShopStar umsMemberShopStar) {
        startPage();
        List<UmsMemberShopStar> list = umsMemberShopStarService.selectUmsMemberShopStarList(umsMemberShopStar);
        return getDataTable(list);
    }

    @ApiOperation("导出店铺收藏列表")
    @RequiresPermissions("business:umsMemberShopStar:export")
    @Log(title = "店铺收藏", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(UmsMemberShopStar umsMemberShopStar) {
        List<UmsMemberShopStar> list = umsMemberShopStarService.selectUmsMemberShopStarList(umsMemberShopStar);
        ExcelUtil<UmsMemberShopStar> util = new ExcelUtil<UmsMemberShopStar>(UmsMemberShopStar. class);
        return util.exportExcel(list, "umsMemberShopStar");
    }

    @ApiOperation("[跳转] 到新增店铺收藏页面")
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }



    @ApiOperation("[跳转] 到店铺收藏编辑页面")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        UmsMemberShopStar umsMemberShopStar =umsMemberShopStarService.selectUmsMemberShopStarById(id);
        mmap.put("umsMemberShopStar", umsMemberShopStar);
        return prefix + "/edit";
    }

    @ApiOperation("修改保存店铺收藏")
    @RequiresPermissions("business:umsMemberShopStar:edit")
    @Log(title = "店铺收藏", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(UmsMemberShopStar umsMemberShopStar) {
        return toAjax(umsMemberShopStarService.updateUmsMemberShopStar(umsMemberShopStar));
    }

    @ApiOperation("批量删除店铺收藏")
    @RequiresPermissions("business:umsMemberShopStar:remove")
    @Log(title = "店铺收藏", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(umsMemberShopStarService.deleteUmsMemberShopStarByIds(ids));
    }

    @ApiOperation("店铺收藏唯一校验")
    @PostMapping("/checkUnique")
    @ResponseBody
    public String checkUnique(UmsMemberShopStar umsMemberShopStar) {
        return umsMemberShopStarService.checkUmsMemberShopStarUnique(umsMemberShopStar);
    }

}
