package com.fante.project.business.bizShopInfo.controller;

import com.fante.common.business.enums.AuditTypeEnum;
import com.fante.common.business.enums.UserTypeEnum;
import com.fante.common.utils.Checker;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.project.business.bizMainCategory.domain.BizMainCategory;
import com.fante.project.business.bizMainCategory.service.IBizMainCategoryService;
import com.fante.project.business.bizShopInfo.domain.BizShopInfo;
import com.fante.project.business.bizShopInfo.service.IBizShopInfoService;
import com.fante.project.system.user.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

/**
 * @program: Fante
 * @Date: 2020/3/12 17:40
 * @Author: Mr.Z
 * @Description: 店铺信息完善
 */
@Controller
@RequestMapping("/business/bizShopAccomplish")
public class BizShopInfoAccomplishController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(BizShopInfoAccomplishController.class);

    private String prefix = "business/bizShopInfo/register";

    @Autowired
    private IBizShopInfoService bizShopInfoService;
    @Autowired
    private IBizMainCategoryService bizMainCategoryService;

    /**
     * 进入店铺信息完善
     * @param map
     * @return
     */
    @GetMapping("/accomplish")
    public String accomplish (ModelMap map) {
        User user = getSysUser();
        Checker.checkOp(Objects.equals(UserTypeEnum.FRANCHISEE_REG.getType(), user.getUserType()), "用户状态异常");
        // 主营类目信息
        BizMainCategory mainCategory = null;
        // 店铺信息
        BizShopInfo shop = bizShopInfoService.selectBizShopInfoById(user.getDeptId());
        log.info("注册店铺信息:: ID: {}, 审核状态: {}, 登录用户: {}", shop.getId(), shop.getAudit(), user.getLoginName());
        // 审核状态为：审核拒绝，显示审核消息
        boolean showTip = Objects.equals(AuditTypeEnum.FAIL.getType(), shop.getAudit());
        // 审核状态为：待创建、审核拒绝，可以进行编辑
        boolean display = AuditTypeEnum.EDITABLE.contains(shop.getAudit());

        if (display) {
            if (!ObjectUtils.isEmpty(shop) && !ObjectUtils.isEmpty(shop.getCategoryId())) {
                mainCategory = bizMainCategoryService.selectBizMainCategoryById(shop.getCategoryId());
            }
        } else {
            // 不可编辑隐藏店铺信息
            shop = new BizShopInfo();
        }
        map.put("shop", shop);
        map.put("mainCategory", mainCategory);
        map.put("display", display);
        map.put("showTip", showTip);
        return prefix + "/accomplish";
    }

    /**
     * 执行店铺信息完善
     * @param bizShopInfo
     * @return
     */
    @PostMapping("/accomplish")
    @ResponseBody
    public AjaxResult accomplishInfo(BizShopInfo bizShopInfo) {
        bizShopInfo.setAudit(AuditTypeEnum.WAIT.getType());
        bizShopInfo.setRemark("");
        int rs = bizShopInfoService.updateBizShopInfo(bizShopInfo);
        return rs > 0
                ? AjaxResult.success("等待平台审核，审核结果将以短信形式，发至注册账号绑定的手机")
                : AjaxResult.error("信息完善失败");
    }

}
