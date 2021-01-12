package com.fante.project.business.umsMember.controller;

import com.fante.common.utils.Checker;
import com.fante.common.utils.poi.ExcelUtil;
import com.fante.framework.aspectj.lang.annotation.Log;
import com.fante.framework.aspectj.lang.enums.BusinessType;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.framework.web.page.TableDataInfo;
import com.fante.project.business.umsMember.domain.UmsMember;
import com.fante.project.business.umsMember.service.IUmsMemberService;
import com.fante.project.system.user.domain.User;
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
 * 会员Controller
 *
 * @author fante
 * @date 2020-04-14
 */
@Api(tags = "UmsMemberController", description = "会员")
@Controller
@RequestMapping("/business/umsMember")
public class UmsMemberController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(UmsMemberController.class);

    private String prefix = "business/umsMember";

    @Autowired
    private IUmsMemberService umsMemberService;

    @RequiresPermissions("business:umsMember:view")
    @GetMapping()
    public String umsMember(ModelMap map) {
        map.put("isAdmin", getSysUser().isAdmin());
        return prefix + "/umsMember";
    }

    @ApiOperation("条件查询会员列表")
    @RequiresPermissions("business:umsMember:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(UmsMember umsMember) {
        startPage();
        List<UmsMember> list = umsMemberService.selectUmsMemberList(umsMember);
        return getDataTable(list);
    }

    @ApiOperation("导出会员列表")
    @RequiresPermissions("business:umsMember:export")
    @Log(title = "会员", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(UmsMember umsMember) {
        List<UmsMember> list = umsMemberService.selectUmsMemberList(umsMember);
        ExcelUtil<UmsMember> util = new ExcelUtil<UmsMember>(UmsMember. class);
        return util.exportExcel(list, "umsMember");
    }

    @ApiOperation("[跳转] 到新增会员页面")
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    @ApiOperation("新增保存会员")
    @RequiresPermissions("business:umsMember:add")
    @Log(title = "会员", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(UmsMember umsMember) {
        return toAjax(umsMemberService.insertUmsMemberFromManage(umsMember));
    }

    @ApiOperation("[跳转] 到会员编辑页面")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        UmsMember umsMember =umsMemberService.selectUmsMemberById(id);
        mmap.put("umsMember", umsMember);
        return prefix + "/edit";
    }

    @ApiOperation("修改保存会员")
    @RequiresPermissions("business:umsMember:edit")
    @Log(title = "会员", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(UmsMember umsMember) {
        return toAjax(umsMemberService.updateUmsMemberFromManage(umsMember));
    }
    @ApiOperation("设置为核销人员")
    @RequiresPermissions("business:umsMember:edit")
    @Log(title = "会员", businessType = BusinessType.UPDATE)
    @PostMapping("/verifier")
    @ResponseBody
    public AjaxResult setVerifierStatus(UmsMember umsMember) {
        return toAjax(umsMemberService.setVerifierStatus(umsMember));
    }

    @ApiOperation("批量删除会员")
    @RequiresPermissions("business:umsMember:remove")
    @Log(title = "会员", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(umsMemberService.deleteUmsMemberByIds(ids));
    }

    @ApiOperation("会员唯一校验")
    @PostMapping("/checkUnique")
    @ResponseBody
    public String checkUnique(UmsMember umsMember) {
        return umsMemberService.checkUmsMemberUnique(umsMember);
    }


    @GetMapping("/cash/{memberId}")
    public String accBalanceRecordOfUser(ModelMap map, @PathVariable("memberId") Long memberId) {
        map.put("isAdmin", getSysUser().isAdmin());
        map.put("memberId", memberId);
        return prefix + "/cash/record";
    }

    @GetMapping("/integral/{memberId}")
    public String pmsIntegralLogOfUser(ModelMap map, @PathVariable("memberId") Long memberId) {
        map.put("isAdmin", getSysUser().isAdmin());
        map.put("memberId", memberId);
        return prefix + "/integral/record";
    }

    @GetMapping("/commission/{memberId}")
    public String accCommissionRecord(ModelMap map, @PathVariable("memberId") Long memberId) {
        map.put("isAdmin", getSysUser().isAdmin());
        map.put("memberId", memberId);
        return prefix + "/commission/record";
    }

    @RequiresPermissions("business:distribution:view")
    @GetMapping("/distribution")
    public String distribution(ModelMap map) {
        User user = getSysUser();
        Checker.checkOp(user.isFranchisee(), "登录用户非店铺用户");
        return prefix + "/distribution/list";
    }

    @RequiresPermissions("business:distribution:list")
    @PostMapping("/distribution/list")
    @ResponseBody
    public TableDataInfo distributionList(UmsMember umsMember) {
        User user = getSysUser();
        Checker.checkOp(user.isFranchisee(), "登录用户非店铺用户");
        umsMember.setShopId(user.getDeptId());
        startPage();
        List<UmsMember> list = umsMemberService.selectUmsMemberList(umsMember);
        return getDataTable(list);
    }

    @RequiresPermissions("business:downline:view")
    @GetMapping("/downline/{pid}")
    public String downLine(ModelMap map, @PathVariable("pid") Long pid) {
        map.put("pid", pid);
        return prefix + "/distribution/downline";
    }

    @RequiresPermissions("business:downline:view")
    @PostMapping("/downline/list")
    @ResponseBody
    public TableDataInfo downLineList(UmsMember umsMember) {
        startPage();
        List<UmsMember> list = umsMemberService.selectUmsMemberList(umsMember);
        return getDataTable(list);
    }
}
