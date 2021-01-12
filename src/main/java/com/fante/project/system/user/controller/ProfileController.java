package com.fante.project.system.user.controller;

import com.fante.common.business.constant.BizConstants;
import com.fante.common.utils.StringUtils;
import com.fante.common.utils.file.FileUploadUtils;
import com.fante.common.utils.oss.OSSUtils;
import com.fante.framework.aspectj.lang.annotation.Log;
import com.fante.framework.aspectj.lang.enums.BusinessType;
import com.fante.framework.config.AliyunOSSConfig;
import com.fante.framework.config.FanteConfig;
import com.fante.framework.shiro.service.PasswordService;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.project.system.dict.service.IDictDataService;
import com.fante.project.system.user.domain.User;
import com.fante.project.system.user.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 个人信息 业务处理
 *
 * @author fante
 */
@Controller
@RequestMapping("/system/user/profile")
public class ProfileController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(ProfileController.class);

    private String prefix = "system/user/profile" ;

    @Autowired
    private IUserService userService;
    @Autowired
    private PasswordService passwordService;
    @Autowired
    private IDictDataService dictDataService;
    @Autowired
    private FanteConfig fanteConfig;
    @Autowired
    private AliyunOSSConfig aliyunOSSConfig;
    @Autowired
    private OSSUtils ossUtils;

    /**
     * 个人信息
     */
    @GetMapping()
    public String profile(ModelMap mmap) {
        User user = getSysUser();
        mmap.put("user" , user);
        mmap.put("roleGroup" , userService.selectUserRoleGroup(user.getUserId()));
        mmap.put("postGroup" , userService.selectUserPostGroup(user.getUserId()));
        return prefix + "/profile" ;
    }

    @GetMapping("/checkPassword")
    @ResponseBody
    public boolean checkPassword(String password) {
        User user = getSysUser();
        if (passwordService.matches(user, password)) {
            return true;
        }
        return false;
    }

    @GetMapping("/resetPwd")
    public String resetPwd(ModelMap mmap) {
        User user = getSysUser();
        mmap.put("user" , userService.selectUserById(user.getUserId()));
        return prefix + "/resetPwd" ;
    }

    @Log(title = "重置密码" , businessType = BusinessType.UPDATE)
    @PostMapping("/resetPwd")
    @ResponseBody
    public AjaxResult resetPwd(String oldPassword, String newPassword) {
        User user = getSysUser();
        if (StringUtils.isNotEmpty(newPassword) && passwordService.matches(user, oldPassword)) {
            user.setPassword(newPassword);
            if (userService.resetUserPwd(user) > 0) {
                setSysUser(userService.selectUserById(user.getUserId()));
                return success();
            }
            return error();
        } else {
            return error("修改密码失败，旧密码错误");
        }

    }

    /**
     * 修改用户
     */
    @GetMapping("/edit")
    public String edit(ModelMap mmap) {
        User user = getSysUser();
        mmap.put("user" , userService.selectUserById(user.getUserId()));
        return prefix + "/edit" ;
    }

    /**
     * 修改头像
     */
    @GetMapping("/avatar")
    public String avatar(ModelMap mmap) {
        User user = getSysUser();
        mmap.put("user" , userService.selectUserById(user.getUserId()));
        return prefix + "/avatar" ;
    }

    /**
     * 修改用户
     */
    @Log(title = "个人信息" , businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    @ResponseBody
    public AjaxResult update(User user) {
        User currentUser = getSysUser();
        currentUser.setUserName(user.getUserName());
        currentUser.setEmail(user.getEmail());
        currentUser.setPhonenumber(user.getPhonenumber());
        currentUser.setSex(user.getSex());
        if (userService.updateUserInfo(currentUser) > 0) {
            setSysUser(userService.selectUserById(currentUser.getUserId()));
            return success();
        }
        return error();
    }

    /**
     * 保存头像
     */
    @Log(title = "个人信息" , businessType = BusinessType.UPDATE)
    @PostMapping("/updateAvatar")
    @ResponseBody
    public AjaxResult updateAvatar(@RequestParam("avatarfile") MultipartFile file) {
        User currentUser = getSysUser();
        try {
            if (!file.isEmpty()) {
                String avatar = "";
                String filePath = "";
                if (aliyunOSSConfig.isEnabled()) {
                    filePath = FanteConfig.getProjectCode() + "/" + BizConstants.path.AVATAR;
                    avatar = ossUtils.upload(filePath, file);
                } else {
                    filePath = FanteConfig.getProfile() + "/" + BizConstants.path.AVATAR;
                    avatar = FanteConfig.getProjectCode() + "/" + FileUploadUtils.upload(filePath, file);
                }

                currentUser.setAvatar(avatar);
                if (userService.updateUserInfo(currentUser) > 0) {
                    setSysUser(userService.selectUserById(currentUser.getUserId()));
                    return success();
                }
            }
            return error();
        } catch (Exception e) {
            log.error("修改头像失败！" , e);
            return error(e.getMessage());
        }
    }
}
