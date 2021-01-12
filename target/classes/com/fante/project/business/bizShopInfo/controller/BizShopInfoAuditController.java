package com.fante.project.business.bizShopInfo.controller;

import com.fante.common.business.enums.AuditTypeEnum;
import com.fante.common.utils.poi.ExcelUtil;
import com.fante.framework.aspectj.lang.annotation.Log;
import com.fante.framework.aspectj.lang.enums.BusinessType;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.framework.web.page.TableDataInfo;
import com.fante.project.business.bizMainCategory.domain.BizMainCategory;
import com.fante.project.business.bizMainCategory.service.IBizMainCategoryService;
import com.fante.project.business.bizShopInfo.domain.BizShopInfo;
import com.fante.project.business.bizShopInfo.domain.BizShopInfoDTO;
import com.fante.project.business.bizShopInfo.service.IBizShopAuditService;
import com.fante.project.business.bizShopInfo.service.IBizShopInfoService;
import com.fante.project.system.user.domain.User;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

/**
 * @program: Fante
 * @Date: 2020/3/13 10:17
 * @Author: Mr.Z
 * @Description: 店铺审核
 */
@Controller
@RequestMapping("/business/bizShopAudit")
public class BizShopInfoAuditController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(BizShopInfoAuditController.class);

    private String prefix = "business/bizShopInfo/audit";

    @Autowired
    private IBizShopInfoService bizShopInfoService;
    @Autowired
    private IBizMainCategoryService bizMainCategoryService;
    @Autowired
    private IBizShopAuditService bizShopAuditService;

    @RequiresPermissions("business:bizShopAudit:view")
    @GetMapping()
    public String bizShopAuditInfo() {
        return prefix + "/auditList";
    }

    @ApiOperation("条件查询店铺审核信息列表")
    @RequiresPermissions("business:bizShopAudit:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BizShopInfoDTO shopInfoDTO) {
        startPage();
        User user = getSysUser();
        List<BizShopInfoDTO> list = Collections.emptyList();
        if (user.isAdmin() || user.isSystem()) {
            list = bizShopInfoService.selectJoinList(shopInfoDTO);
        }
        return getDataTable(list);
    }

    @ApiOperation("导出店铺审核信息列表")
    @RequiresPermissions("business:bizShopAudit:export")
    @Log(title = "店铺审核信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BizShopInfoDTO shopInfoDTO) {
        User user = getSysUser();
        List<BizShopInfoDTO> list = Collections.emptyList();
        if (user.isAdmin() || user.isSystem()) {
            list = bizShopInfoService.selectJoinList(shopInfoDTO);
        }
        ExcelUtil<BizShopInfoDTO> util = new ExcelUtil<BizShopInfoDTO>(BizShopInfoDTO. class);
        return util.exportExcel(list, "店铺审核信息");
    }

    @ApiOperation("[跳转] 到店铺审核页面")
    @GetMapping("/audit/{id}")
    public String audit(@PathVariable("id") Long id, ModelMap mmap) {
        boolean auditAble = false;
        BizShopInfo bizShopInfo =bizShopInfoService.selectBizShopInfoById(id);
        BizMainCategory mainCategory = null;
        if (!ObjectUtils.isEmpty(bizShopInfo) && !ObjectUtils.isEmpty(bizShopInfo.getCategoryId())) {
            mainCategory = bizMainCategoryService.selectBizMainCategoryById(bizShopInfo.getCategoryId());
            auditAble = AuditTypeEnum.AUDITABLE.contains(bizShopInfo.getAudit());
        }
        mmap.put("auditAble", auditAble);
        mmap.put("bizShopInfo", bizShopInfo);
        mmap.put("mainCategory", mainCategory);
        return prefix + "/audit";
    }

    @ApiOperation("店铺审核通过")
    @RequiresPermissions("business:bizShopAudit:audit")
    @Log(title = "店铺审核通过", businessType = BusinessType.UPDATE)
    @PostMapping("pass")
    @ResponseBody
    public AjaxResult auditPass(BizShopInfo bizShopInfo) {
        bizShopAuditService.auditPass(bizShopInfo);
        return AjaxResult.success();
    }

    @ApiOperation("店铺审核拒绝")
    @RequiresPermissions("business:bizShopAudit:audit")
    @Log(title = "店铺审核拒绝", businessType = BusinessType.UPDATE)
    @PostMapping("refuse")
    @ResponseBody
    public AjaxResult auditRefuse(BizShopInfo bizShopInfo) {
        bizShopAuditService.auditRefuse(bizShopInfo);
        return AjaxResult.success();
    }

}
