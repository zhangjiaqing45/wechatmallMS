package com.fante.project.business.smsHomeRecommendProduct.controller;

import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.page.TableDataInfo;
import com.fante.project.business.smsHomeRecommendProduct.domain.SmsHomeRecommendSelectDTO;
import com.fante.project.business.smsHomeRecommendProduct.service.ISmsHomeRecommendProductService;
import com.fante.project.system.user.domain.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: Fante
 * @Date: 2020/3/24 11:54
 * @Author: Mr.Z
 * @Description: 推荐商品通用功能
 */
@Api(tags = "SmsHomeRecommendCommonController", description = "精品推荐商品")
@Controller
@RequestMapping("/business/smsHomeRecommendCommon")
public class SmsHomeRecommendCommonController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(SmsHomeRecommendCommonController.class);

    private String prefix = "business/smsHomeRecommendProduct";

    @Autowired
    private ISmsHomeRecommendProductService smsHomeRecommendProductService;


    @GetMapping("/select/{recommendType}")
    public String smsHomeRecommendProduct(ModelMap modelMap, @PathVariable("recommendType") String recommendType) {
        User user = getSysUser();
        modelMap.put("isAdmin", user.isAdmin() || user.isSystem());
        modelMap.put("recommendType", recommendType);
        return prefix + "/recommendSelect";
    }

    @ApiOperation("根据推荐类型选择商品")
    //@RequiresPermissions("business:smsHomeRecommendSelect:list")
    @PostMapping("/select")
    @ResponseBody
    public TableDataInfo recommendSelect(SmsHomeRecommendSelectDTO selectDTO) {
        User user = getSysUser();
        if (user.isFranchisee()) {
            selectDTO.setShopId(user.getDeptId());
        }
        startPage();
        List<SmsHomeRecommendSelectDTO> list = smsHomeRecommendProductService.recommendSelect(selectDTO);
        return getDataTable(list);
    }
}
