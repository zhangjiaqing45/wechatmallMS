package com.fante.project.business.bizShopInfo.controller;

import com.fante.common.business.enums.UserTypeEnum;
import com.fante.common.constant.UserConstants;
import com.fante.common.utils.poi.ExcelUtil;
import com.fante.common.utils.security.ShiroUtils;
import com.fante.framework.aspectj.lang.annotation.Log;
import com.fante.framework.aspectj.lang.enums.BusinessType;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.framework.web.page.TableDataInfo;
import com.fante.project.business.bizShopInfo.domain.BizShopUserDTO;
import com.fante.project.business.bizShopInfo.service.IBizShopInfoService;
import com.fante.project.business.bizShopInfo.service.IBizShopUserService;
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
 * @Description: 店铺用户管理
 */
@Controller
@RequestMapping("/business/bizShopUser")
public class BizShopUserController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(BizShopUserController.class);

    private String prefix = "business/bizShopUser";

    @Autowired
    private IBizShopInfoService bizShopInfoService;
    @Autowired
    private IBizShopUserService bizShopUserService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;

    @RequiresPermissions("business:bizShopUser:view")
    @GetMapping()
    public String bizShopUser(ModelMap map) {
        map.put("isAdmin", getSysUser().isAdmin() || getSysUser().isSystem());
        return prefix + "/bizShopUser";
    }

    @ApiOperation("条件查询店铺用户信息列表")
    @RequiresPermissions("business:bizShopUser:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BizShopUserDTO bizShopUserDTO) {
        startPage();
        if (getSysUser().isFranchisee()) {
            bizShopUserDTO.setDeptId(getSysUser().getDeptId());
        }
        bizShopUserDTO.setUserType(UserTypeEnum.FRANCHISEE.getType());
        List<BizShopUserDTO> list = bizShopUserService.selectShopUserJoinList(bizShopUserDTO);
        return getDataTable(list);
    }

    @ApiOperation("导出店铺用户信息列表")
    @Log(title = "店铺用户管理" , businessType = BusinessType.EXPORT)
    @RequiresPermissions("business:bizShopUser:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BizShopUserDTO bizShopUserDTO) {
        if (getSysUser().isFranchisee()) {
            bizShopUserDTO.setDeptId(getSysUser().getDeptId());
        }
        bizShopUserDTO.setUserType(UserTypeEnum.FRANCHISEE.getType());
        List<BizShopUserDTO> list = bizShopUserService.selectShopUserJoinList(bizShopUserDTO);
        ExcelUtil<BizShopUserDTO> util = new ExcelUtil<>(BizShopUserDTO.class);
        return util.exportExcel(list, "店铺用户");
    }

    @GetMapping("/add")
    public String add(ModelMap map) {
        Role role = new Role();
        role.setRoleType(UserTypeEnum.FRANCHISEE.getType());
        map.put("roles" , roleService.selectRoleList(role));
        map.put("isAdmin", getSysUser().isAdmin() || getSysUser().isSystem());
        if (getSysUser().isFranchisee()) {
            map.put("shop", bizShopInfoService.selectBizShopInfoById(getSysUser().getDeptId()));
        }
        return prefix + "/add" ;
    }


    @RequiresPermissions("business:bizShopUser:add")
    @ApiOperation("新增店铺用户信息")
    @Log(title = "店铺用户管理" , businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated User user) {
        if (UserConstants.USER_NAME_NOT_UNIQUE.equals(userService.checkLoginNameUnique(user.getLoginName()))) {
            return error("新增店铺用户'" + user.getLoginName() + "'失败，登录账号已存在");
        } else if (UserConstants.USER_PHONE_NOT_UNIQUE.equals(userService.checkPhoneUnique(user))) {
            return error("新增店铺用户'" + user.getLoginName() + "'失败，手机号码已存在");
        } else if (UserConstants.USER_EMAIL_NOT_UNIQUE.equals(userService.checkEmailUnique(user))) {
            return error("新增店铺用户'" + user.getLoginName() + "'失败，邮箱账号已存在");
        } else if (!bizShopInfoService.checkEnable(user.getDeptId())) {
            return error("新增店铺用户'" + user.getLoginName() + "'失败，无效的归属店铺");
        }
        user.setUserType(UserTypeEnum.FRANCHISEE.getType());
        return toAjax(userService.insertUser(user));
    }

    @GetMapping("/edit/{userId}")
    public String edit(@PathVariable("userId") Long userId, ModelMap map) {
        BizShopUserDTO userDTO = bizShopUserService.selectShopUserJoinById(userId);
        map.put("user", userDTO);
        map.put("roles", roleService.selectRolesByUserId(userId, UserTypeEnum.FRANCHISEE.getType()));
        map.put("isAdmin", getSysUser().isAdmin() || getSysUser().isSystem());
        return prefix + "/edit" ;
    }

    @RequiresPermissions("business:bizShopUser:edit")
    @Log(title = "修改店铺用户信息" , businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated User user) {
        userService.checkUserAllowed(user);
        if (UserConstants.USER_PHONE_NOT_UNIQUE.equals(userService.checkPhoneUnique(user))) {
            return error("新增店铺用户'" + user.getLoginName() + "'失败，手机号码已存在");
        } else if (UserConstants.USER_EMAIL_NOT_UNIQUE.equals(userService.checkEmailUnique(user))) {
            return error("新增店铺用户'" + user.getLoginName() + "'失败，邮箱账号已存在");
        } else if (!bizShopInfoService.checkEnable(user.getDeptId())) {
            return error("新增店铺用户'" + user.getLoginName() + "'失败，无效的归属店铺");
        }
        return toAjax(userService.updateUser(user));
    }

    @RequiresPermissions("business:bizShopUser:remove")
    @Log(title = "删除店铺用户信息" , businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) throws Exception {
        return toAjax(userService.deleteUserByIds(ids));
    }


    @Log(title = "店铺用户重置密码" , businessType = BusinessType.UPDATE)
    @GetMapping("/resetPwd/{userId}")
    public String resetPwd(@PathVariable("userId") Long userId, ModelMap mmap) {
        BizShopUserDTO userDTO = bizShopUserService.selectShopUserJoinById(userId);
        mmap.put("user" , userDTO);
        return prefix + "/resetPwd" ;
    }

    @RequiresPermissions("business:bizShopUser:resetPwd")
    @Log(title = "店铺用户重置密码" , businessType = BusinessType.UPDATE)
    @PostMapping("/resetPwd")
    @ResponseBody
    public AjaxResult resetPwdSave(User user) {
        userService.checkUserAllowed(user);
        if (userService.resetUserPwd(user) > 0) {
            if (ShiroUtils.getUserId().compareTo(user.getUserId()) == 0) {
                setSysUser(userService.selectUserById(user.getUserId()));
            }
            return success();
        }
        return error();
    }

}
