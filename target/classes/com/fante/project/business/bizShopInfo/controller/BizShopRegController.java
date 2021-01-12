package com.fante.project.business.bizShopInfo.controller;

import com.fante.common.business.constant.BizConstants;
import com.fante.common.constant.UserConstants;
import com.fante.framework.aspectj.lang.annotation.Log;
import com.fante.framework.aspectj.lang.enums.BusinessType;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.project.business.bizShopInfo.service.IBizShopUserService;
import com.fante.project.business.cmsDocument.service.ICmsDocumentService;
import com.fante.project.system.user.domain.User;
import com.fante.project.system.user.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: Fante
 * @Date: 2020/3/12 15:03
 * @Author: Mr.Z
 * @Description: 店铺入驻申请
 */
@Controller
@RequestMapping("/business/bizShopReg")
public class BizShopRegController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(BizShopRegController.class);

    private String prefix = "business/bizShopInfo/register";

    @Autowired
    private IUserService userService;
    @Autowired
    private IBizShopUserService bizShopUserService;
    @Autowired
    private ICmsDocumentService cmsDocumentService;

    @GetMapping("contract")
    public String contract() {
        return prefix + "/contract";
    }

    @GetMapping("userReg")
    public String userReg() {
        return prefix + "/userReg";
    }

    @Log(title = "店铺用户注册", businessType = BusinessType.INSERT)
    @PostMapping("userReg")
    @ResponseBody
    public AjaxResult userRegSave(@Validated User user, String verifyCode) {
        if (UserConstants.USER_NAME_NOT_UNIQUE.equals(userService.checkLoginNameUnique(user.getLoginName()))) {
            return error("注册店铺用户'" + user.getLoginName() + "'失败，登录账号已存在");
        } else if (UserConstants.USER_PHONE_NOT_UNIQUE.equals(userService.checkPhoneUnique(user))) {
            return error("注册店铺用户'" + user.getLoginName() + "'失败，手机号码已存在");
        } else if (UserConstants.USER_EMAIL_NOT_UNIQUE.equals(userService.checkEmailUnique(user))) {
            return error("注册店铺用户'" + user.getLoginName() + "'失败，邮箱账号已存在");
        }
        bizShopUserService.register(user, verifyCode);
        return AjaxResult.success("用户注册成功，请登录账号[" + user.getLoginName() + "]完善店铺信息");
    }

    /**
     * 校验用户名
     */
    @PostMapping("/checkLoginNameUnique")
    @ResponseBody
    public String checkLoginNameUnique(User user) {
        return userService.checkLoginNameUnique(user.getLoginName());
    }

    /**
     * 校验手机号码
     */
    @PostMapping("/checkPhoneUnique")
    @ResponseBody
    public String checkPhoneUnique(User user) {
        return userService.checkPhoneUnique(user);
    }

    /**
     * 入驻须知
     * @return
     */
    @PostMapping("/entryNotice")
    @ResponseBody
    public AjaxResult entryNotice() {
        return AjaxResult.success()
                .put("doc", cmsDocumentService.selectCmsDocumentByDocKey(BizConstants.shop.SHOP_ENTRY_NOTICE_DOC_KEY));
    }
}
