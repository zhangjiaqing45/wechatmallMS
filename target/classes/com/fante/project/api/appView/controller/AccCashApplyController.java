package com.fante.project.api.appView.controller;

import com.fante.common.business.enums.UmsMemberConst;
import com.fante.common.utils.Checker;
import com.fante.common.utils.StringUtils;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.project.api.appView.service.AccCashApplyService;
import com.fante.project.api.umsProcess.service.UmsMemberProcessService;
import com.fante.project.business.accCommissionRecord.domain.AccCommissionRecord;
import com.fante.project.business.accMemberCashApply.domain.AccMemberCashApply;
import com.fante.project.business.umsMember.domain.UmsMember;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: Fante
 * @Date: 2020/4/22 10:54
 * @Author: wz
 * @Description: 现金业务
 */
@Api(tags = "AccCashApplyController", description = "现金业务")
@Controller
@RequestMapping("/api/money")
public class AccCashApplyController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(AccCashApplyController.class);
    /**
     * 会员Service接口
     */
    @Autowired
    private AccCashApplyService accCashApplyService;
    /**
     * 用户缓存
     */
    @Autowired
    private UmsMemberProcessService umsMemberProcessService;

    @ApiOperation("用户提现申请")
    @PostMapping("/cash")
    @ResponseBody
    public AjaxResult applyCashOfMember(AccMemberCashApply param) {
        //从缓存中取当前用户
        UmsMember member = umsMemberProcessService.get(getTokenClientId());
        Checker.check(StringUtils.equals(member.getRoleType(), UmsMemberConst.RoleType.GENERAL.getType()),"普通用户暂不支持此功能");
        //获取用户角色 如果是导购 设置提现店铺为
        param.setShopId(member.getShopId());
        param.setMemberId(member.getId());
        int i = accCashApplyService.applyCashOfMember(param);
        // 更新缓存
        umsMemberProcessService.updateCache(member.getId());
        return toAjax(i);
    }

    @ApiOperation("佣金转余额")
    @PostMapping("/toBalance")
    @ResponseBody
    public AjaxResult waitCommissionToCommission(AccCommissionRecord param) {
        param.setMemberId(getTokenUserId());
        int i = accCashApplyService.waitCommissionToBalance(param);
        // 更新缓存
        umsMemberProcessService.updateCache(getTokenUserId());
        return toAjax(i);
    }

}
