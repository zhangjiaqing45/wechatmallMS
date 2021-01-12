package com.fante.project.api.appView.controller;

import com.fante.common.business.enums.UmsMemberConst;
import com.fante.common.utils.StringUtils;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.project.api.appView.service.UmsFamilyService;
import com.fante.project.api.umsProcess.service.UmsMemberProcessService;
import com.fante.project.business.smsGroupGame.domain.SmsGroupGame;
import com.fante.project.business.umsMember.domain.UmsMember;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author ftnet
 * @Description UmsFamilyController 用户上下级关系
 * @CreatTime 2020/5/8
 */
@Api(tags = "UmsFamilyController", description = "用户上下级")
@Controller
@RequestMapping("/api/family")
public class UmsFamilyController extends BaseController {
    private static Logger log = LoggerFactory.getLogger(UmsFamilyController.class);
    /**
     * 会员相关处理服务
     */
    @Autowired
    UmsFamilyService umsFamilyService;
    /**
     * 会员相关处理服务
     */
    @Autowired
    UmsMemberProcessService umsMemberProcessService;

    @ApiOperation("获取下级")
    @PostMapping("/children")
    @ResponseBody
    public AjaxResult getMemeberChildren() {
        UmsMember member = umsMemberProcessService.get(getTokenClientId());
        //验证用户角色
        if (StringUtils.equals(member.getRoleType(), UmsMemberConst.RoleType.GENERAL.getType())){
            return AjaxResult.success()
                    .put( "childrens",null)
                    .put("canLoad", false);
        }
        //获取所有时间段
        startPage();
        List<UmsMember> childrens = umsFamilyService.getMemeberChildren(member);
        boolean canLoad = new PageInfo(childrens).isHasNextPage();
        return AjaxResult.success()
                .put( "childrens",childrens )
                .put("canLoad", canLoad);
    }


}
