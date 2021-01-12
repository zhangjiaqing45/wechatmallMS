package com.fante.project.system.user.controller;

import com.fante.common.business.constant.BizConstants;
import com.fante.common.business.enums.UserTypeEnum;
import com.fante.common.utils.StringUtils;
import com.fante.framework.config.FanteConfig;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.project.system.config.service.IConfigService;
import com.fante.project.system.menu.domain.Menu;
import com.fante.project.system.menu.service.IMenuService;
import com.fante.project.system.user.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * 首页 业务处理
 *
 * @author fante
 */
@Controller
public class IndexController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private IMenuService menuService;
    @Autowired
    private FanteConfig fanteConfig;
    @Autowired
    private IConfigService configService;
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    // 系统首页
    @GetMapping("/index")
    public String index(ModelMap mmap) {
        // 取身份信息
        User user = getSysUser();

        if (needSupplement(user)) {
            String url = configService.selectConfigByKey(BizConstants.shop.SHOP_INFO_ACCOMPLISH_URL);
            if (StringUtils.isBlank(url)) {
                log.info("未设置店铺入驻完善信息路径");
                return "/error/500";
            }
            log.info("店铺入驻完善信息路径: {}", url);
            return redirect(url);
        }

        // 根据用户id取出菜单
        List<Menu> menus = menuService.selectMenusByUser(user);
        mmap.put("menus" , menus);
        mmap.put("user" , user);
        mmap.put("copyrightYear" , fanteConfig.getCopyrightYear());
        mmap.put("demoEnabled" , fanteConfig.isDemoEnabled());
        mmap.put("projectName" , fanteConfig.getProjectName());
        mmap.put("name" , fanteConfig.getName());
        return "index" ;
    }

    /**
     * 是否需要进入信息完善页
     * @param
     * @return
     */
    private boolean needSupplement(User user) {
        if (!Objects.equals(UserTypeEnum.FRANCHISEE_REG.getType(), user.getUserType())) {
            return false;
        }
        log.info("店铺注册用户:: ID: {}, 用户名: {}, 用户类型: {}", user.getUserId(), user.getUserName(), user.getUserType());
        return true;
    }

    // 切换主题
    @GetMapping("/system/switchSkin")
    public String switchSkin(ModelMap mmap) {
        return "skin" ;
    }


    /**
     * 首页（统计）
     * @Date 10:10 2020-02-15
     * @Param [mmap]
     * @return java.lang.String
     **/
    @GetMapping("/system/main")
    public String main(ModelMap mmap) {

        return "main_v1" ;
    }

    @PostMapping("/system/cleanRedis")
    @ResponseBody
    public AjaxResult cleanRedis() {
        Set<String> allKeys = redisTemplate.keys("*");
        if (CollectionUtils.isEmpty(allKeys)) {
            return AjaxResult.success("无须清理");
        }
        long result = redisTemplate.delete(allKeys);
        return AjaxResult.success(result);
    }

}
