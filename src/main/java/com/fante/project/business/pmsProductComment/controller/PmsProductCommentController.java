package com.fante.project.business.pmsProductComment.controller;

import java.util.List;

import com.fante.project.system.user.domain.User;
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
import com.fante.project.business.pmsProductComment.domain.PmsProductComment;
import com.fante.project.business.pmsProductComment.service.IPmsProductCommentService;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.common.utils.poi.ExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fante.framework.web.page.TableDataInfo;

/**
 * 商品评价Controller
 *
 * @author fante
 * @date 2020-03-19
 */
@Api(tags = "PmsProductCommentController", description = "商品评价")
@Controller
@RequestMapping("/business/pmsProductComment")
public class PmsProductCommentController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(PmsProductCommentController.class);

    private String prefix = "business/pmsProductComment";

    @Autowired
    private IPmsProductCommentService pmsProductCommentService;

    @RequiresPermissions("business:pmsProductComment:view")
    @GetMapping()
    public String pmsProductComment() {
        return prefix + "/pmsProductComment";
    }

    @ApiOperation("条件查询商品评价列表")
    @RequiresPermissions("business:pmsProductComment:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PmsProductComment pmsProductComment) {
        User user = getSysUser();
        if (user.isFranchisee()) {
            pmsProductComment.setShopId(user.getDeptId());
        }
        startPage();
        List<PmsProductComment> list = pmsProductCommentService.selectPmsProductCommentList(pmsProductComment);
        return getDataTable(list);
    }

    @ApiOperation("导出商品评价列表")
    @RequiresPermissions("business:pmsProductComment:export")
    @Log(title = "商品评价", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PmsProductComment pmsProductComment) {
        User user = getSysUser();
        if (user.isFranchisee()) {
            pmsProductComment.setShopId(user.getDeptId());
        }
        List<PmsProductComment> list = pmsProductCommentService.selectPmsProductCommentList(pmsProductComment);
        ExcelUtil<PmsProductComment> util = new ExcelUtil<PmsProductComment>(PmsProductComment. class);
        return util.exportExcel(list, "pmsProductComment");
    }

    @ApiOperation("[跳转] 到新增商品评价页面")
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    @ApiOperation("新增保存商品评价")
    @RequiresPermissions("business:pmsProductComment:add")
    @Log(title = "商品评价", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PmsProductComment pmsProductComment) {
        return toAjax(pmsProductCommentService.insertPmsProductComment(pmsProductComment));
    }

    @ApiOperation("[跳转] 到商品评价编辑页面")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        PmsProductComment pmsProductComment =pmsProductCommentService.selectPmsProductCommentById(id);
        mmap.put("pmsProductComment", pmsProductComment);
        return prefix + "/edit";
    }

    @ApiOperation("修改保存商品评价")
    @RequiresPermissions("business:pmsProductComment:edit")
    @Log(title = "商品评价", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PmsProductComment pmsProductComment) {
        return toAjax(pmsProductCommentService.updatePmsProductComment(pmsProductComment));
    }

    @ApiOperation("唯一校验")
    @RequiresPermissions("business:pmsProductComment:add")
    @Log(title = "商品评价", businessType = BusinessType.INSERT)
    @PostMapping("/checkUnique")
    @ResponseBody
    public String checkPmsProductCommentUnique(PmsProductComment pmsProductComment) {
        return pmsProductCommentService.checkPmsProductCommentUnique(pmsProductComment);
    }

    @ApiOperation("批量删除商品评价")
    @RequiresPermissions("business:pmsProductComment:remove")
    @Log(title = "商品评价", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(pmsProductCommentService.deletePmsProductCommentByIds(ids));
    }

    @ApiOperation("审核通过")
    @RequiresPermissions("business:pmsProductComment:edit")
    @Log(title = "商品评价", businessType = BusinessType.UPDATE)
    @PostMapping("/pass")
    @ResponseBody
    public AjaxResult pass(String ids) {
        return toAjax(pmsProductCommentService.pass(ids));
    }

    @ApiOperation("审核拒绝")
    @RequiresPermissions("business:pmsProductComment:edit")
    @Log(title = "商品评价", businessType = BusinessType.UPDATE)
    @PostMapping("/refuse")
    @ResponseBody
    public AjaxResult refuse(String ids) {
        return toAjax(pmsProductCommentService.refuse(ids));
    }

}
