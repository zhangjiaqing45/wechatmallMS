package com.fante.project.business.bizShopWriteExport.controller;

import com.fante.common.business.enums.UserTypeEnum;
import com.fante.common.constant.UserConstants;
import com.fante.common.utils.poi.ExcelUtil;
import com.fante.common.utils.security.ShiroUtils;
import com.fante.framework.aspectj.lang.annotation.Log;
import com.fante.framework.aspectj.lang.enums.BusinessType;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.framework.web.page.TableDataInfo;
import com.fante.project.business.bizShopInfo.controller.BizShopUserController;
import com.fante.project.business.bizShopInfo.domain.BizShopUserDTO;
import com.fante.project.business.bizShopInfo.service.IBizShopInfoService;
import com.fante.project.business.bizShopInfo.service.IBizShopUserService;
import com.fante.project.business.bizShopWriteExport.domain.BizShopWriteDTO;
import com.fante.project.business.bizShopWriteExport.domain.BizShopWriteExportDTO;
import com.fante.project.business.bizShopWriteExport.service.IBizShopWriteExportService;
import com.fante.project.system.role.domain.Role;
import com.fante.project.system.role.service.IRoleService;
import com.fante.project.system.user.domain.User;
import com.fante.project.system.user.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: Fante
 * @Date: 2020/3/13 15:23
 * @Author: Mr.Z
 * @Description: 商城数据汇总
 */
@Controller
@RequestMapping("/business/bizShopWriteExport")
public class BizShopWriteExportController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(BizShopWriteExportController.class);

    private String prefix = "business/bizShopWriteExport";

    @Autowired
    private IBizShopWriteExportService bizShopWriteExportService;
    @Autowired
    private IBizShopInfoService bizShopInfoService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;

    @RequiresPermissions("business:bizShopWriteExport:view")
    @GetMapping()
    public String bizShopWriteExport(ModelMap map) {
        map.put("isAdmin", getSysUser().isAdmin() || getSysUser().isSystem());
        return prefix + "/bizShopWriteExport";
    }

    @ApiOperation("条件查询商城数据汇总列表")
    @RequiresPermissions("business:bizShopWriteExport:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BizShopWriteDTO bizShopWriteDTO) {
        startPage();
        bizShopWriteDTO.setBeginTime(bizShopWriteDTO.getBeginTime().replace("-",""));
        bizShopWriteDTO.setEndTime(bizShopWriteDTO.getEndTime().replace("-",""));
        /*bizShopWriteExportDTO.setUserType(UserTypeEnum.FRANCHISEE.getType());*/

        List<BizShopWriteDTO> list = bizShopWriteExportService.selectShopWriteJoinList(bizShopWriteDTO);
        return getDataTable(list);
    }


    @ApiOperation("导出商城数据汇总列表")
    @Log(title = "商城数据汇总" , businessType = BusinessType.EXPORT)
    @RequiresPermissions("business:bizShopUser:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BizShopWriteDTO bizShopWriteDTO) {
        bizShopWriteDTO.setBeginTime(bizShopWriteDTO.getBeginTime().replace("-",""));
        bizShopWriteDTO.setEndTime(bizShopWriteDTO.getEndTime().replace("-",""));
        List<BizShopWriteDTO> list = bizShopWriteExportService.selectShopWriteJoinList(bizShopWriteDTO);
        ExcelUtil<BizShopWriteDTO> util = new ExcelUtil<>(BizShopWriteDTO.class);
        return util.exportExcel(list, "商城数据汇总");
    }


}
