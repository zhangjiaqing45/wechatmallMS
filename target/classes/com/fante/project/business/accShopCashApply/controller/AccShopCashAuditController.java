package com.fante.project.business.accShopCashApply.controller;

import com.fante.common.business.enums.AccCashApplyConst;
import com.fante.common.utils.Checker;
import com.fante.common.utils.poi.ExcelUtil;
import com.fante.framework.aspectj.lang.annotation.Log;
import com.fante.framework.aspectj.lang.enums.BusinessType;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.framework.web.page.TableDataInfo;
import com.fante.project.business.accShopCashApply.domain.AccShopCashApplyDTO;
import com.fante.project.business.accShopCashApply.service.IAccShopCashApplyService;
import com.fante.project.system.user.domain.User;
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

import java.util.List;

/**
 * @program: Fante
 * @Date: 2020/5/13 9:13
 * @Author: Mr.Z
 * @Description: 店铺提现审核
 */
@Api(tags = "AccShopCashApplyController", description = "店铺提现审核")
@Controller
@RequestMapping("/business/accShopCashAudit")
public class AccShopCashAuditController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(AccShopCashApplyController.class);

    private String prefix = "business/accShopCashAudit";

    @Autowired
    private IAccShopCashApplyService accShopCashApplyService;

    @RequiresPermissions("business:accShopCashAudit:view")
    @GetMapping()
    public String accShopCashApply(ModelMap map) {
        map.put("isAdmin", getSysUser().isAdmin());
        map.put("auditStatus", AccCashApplyConst.AuditType.WAIT.getType());
        return prefix + "/accShopCashAudit";
    }

    @ApiOperation("条件查询店铺提现记录列表")
    @RequiresPermissions("business:accShopCashAudit:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AccShopCashApplyDTO dto) {
        startPage();
        List<AccShopCashApplyDTO> list = accShopCashApplyService.selectJoinList(dto);
        return getDataTable(list);
    }

    @ApiOperation("导出店铺提现记录列表")
    @RequiresPermissions("business:accShopCashAudit:export")
    @Log(title = "店铺提现记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AccShopCashApplyDTO dto) {
        List<AccShopCashApplyDTO> list = accShopCashApplyService.selectJoinList(dto);
        ExcelUtil<AccShopCashApplyDTO> util = new ExcelUtil<AccShopCashApplyDTO>(AccShopCashApplyDTO. class);
        return util.exportExcel(list, "店铺提现记录");
    }

    @ApiOperation("同意提现")
    @RequiresPermissions("business:accShopCashAudit:audit")
    @PostMapping("/agree")
    @ResponseBody
    public AjaxResult agree(Long id, String remark) {
        User user = getSysUser();
        Checker.checkOp(user.isAdmin() || user.isSystem(), "登录用户非管理用户");
        return toAjax(accShopCashApplyService.agree(id, remark));
    }

    @ApiOperation("拒绝提现")
    @RequiresPermissions("business:accShopCashAudit:audit")
    @PostMapping("/refuse")
    @ResponseBody
    public AjaxResult refuse(Long id, String remark) {
        User user = getSysUser();
        Checker.checkOp(user.isAdmin() || user.isSystem(), "登录用户非管理用户");
        return toAjax(accShopCashApplyService.refuse(id, remark));
    }
}
