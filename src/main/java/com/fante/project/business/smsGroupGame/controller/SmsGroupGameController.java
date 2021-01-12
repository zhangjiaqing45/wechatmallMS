package com.fante.project.business.smsGroupGame.controller;

import com.fante.common.business.enums.SmsGroupGameConst;
import com.fante.common.utils.Checker;
import com.fante.common.utils.poi.ExcelUtil;
import com.fante.framework.aspectj.lang.annotation.Log;
import com.fante.framework.aspectj.lang.enums.BusinessType;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.framework.web.page.TableDataInfo;
import com.fante.project.business.pmsProduct.domain.PmsProduct;
import com.fante.project.business.smsGroupGame.domain.SmsGroupGame;
import com.fante.project.business.smsGroupGame.domain.SmsGroupGameBtnsResult;
import com.fante.project.business.smsGroupGame.domain.SmsGroupGameParam;
import com.fante.project.business.smsGroupGame.service.ISmsGroupGameService;
import com.fante.project.business.smsSetting.utils.SmsSettingUtils;
import com.fante.project.system.user.domain.User;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 团购商品Controller
 *
 * @author fante
 * @date 2020-03-30
 */
@Api(tags = "SmsGroupGameController", description = "团购商品")
@Controller
@RequestMapping("/business/smsGroupGame")
public class SmsGroupGameController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(SmsGroupGameController.class);

    private String prefix = "business/smsGroupGame";

    @Autowired
    private ISmsGroupGameService smsGroupGameService;
    @Autowired
    private SmsSettingUtils smsSettingUtils;

    @RequiresPermissions("business:smsGroupGame:view")
    @GetMapping()
    public String smsGroupGame(ModelMap mmap) {
        Checker.check(ObjectUtils.isEmpty(smsSettingUtils.groupEnable()),"活动尚未开启");
        mmap.put("isAdmin",getSysUser().isAdmin() || getSysUser().isSystem());
        return prefix + "/smsGroupGame";
    }

    @ApiOperation("条件查询团购商品列表")
    @RequiresPermissions("business:smsGroupGame:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SmsGroupGame smsGroupGame) {
        User user = getSysUser();
        if (user.isFranchisee()) {
            smsGroupGame.setShopId(user.getDeptId());
        }
        startPage();
        List<SmsGroupGameBtnsResult> list = smsGroupGameService.selectSmsGroupGameBtnList(smsGroupGame);
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(AjaxResult.Type.SUCCESS.value());
        rspData.setTotal(new PageInfo(list).getTotal());
        rspData.setRows(
            list.stream()
                    .map(item->{
                        //获取按钮组合
                        List<String> btns = SmsGroupGameConst.GroupActionEnum.getBtns(item.getStatus());
                        return new SmsGroupGameBtnsResult(item,btns);
                    }).collect(Collectors.toList())
        );
        return rspData;
    }

    @ApiOperation("条件查询团购记录列表")
    @RequiresPermissions("business:smsGroupGameRecord:list")
    @PostMapping("/select/product/list")
    @ResponseBody
    public TableDataInfo selcetProduct() {
        startPage();
        List<PmsProduct> list = smsGroupGameService.selectDistinctProductListForGroupGame();
        return getDataTable(list);
    }

    @ApiOperation("[跳转] 到新增团购记录页面")
    @GetMapping("/select/product")
    public String gotoSelcetProduct() {
        Checker.check(ObjectUtils.isEmpty(smsSettingUtils.groupEnable()),"活动尚未开启");
        return prefix + "/select/product";
    }

    @ApiOperation("导出团购商品列表")
    @RequiresPermissions("business:smsGroupGame:export")
    @Log(title = "团购商品", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SmsGroupGame smsGroupGame) {
        User user = getSysUser();
        if (user.isFranchisee()) {
            smsGroupGame.setShopId(user.getDeptId());
        }
        List<SmsGroupGame> list = smsGroupGameService.selectSmsGroupGameList(smsGroupGame);
        ExcelUtil<SmsGroupGame> util = new ExcelUtil<SmsGroupGame>(SmsGroupGame. class);
        return util.exportExcel(list, "smsGroupGame");
    }

    @ApiOperation("[跳转] 到新增团购商品页面")
    @GetMapping("/add")
    public String add() {
        Checker.check(ObjectUtils.isEmpty(smsSettingUtils.groupEnable()),"活动尚未开启");
        return prefix + "/add";
    }

    @ApiOperation("新增保存团购商品")
    @RequiresPermissions("business:smsGroupGame:add")
    @Log(title = "团购商品", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestBody SmsGroupGameParam param) {
        return toAjax(smsGroupGameService.insertSmsGroupGame(param));
    }

    @ApiOperation("[跳转] 到团购商品编辑页面")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        Checker.check(ObjectUtils.isEmpty(smsSettingUtils.groupEnable()),"活动尚未开启");
        SmsGroupGameParam smsGroupGame =smsGroupGameService.getSmsGroupGameDetailofEdit(id);
        mmap.put("smsGroupGame", smsGroupGame);
        return prefix + "/edit";
    }

    @ApiOperation("[跳转] 到团购商品编辑页面")
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, ModelMap mmap) {
        Checker.check(ObjectUtils.isEmpty(smsSettingUtils.groupEnable()),"活动尚未开启");
        SmsGroupGameParam smsGroupGame =smsGroupGameService.getSmsGroupGameDetailofEdit(id);
        mmap.put("smsGroupGame", smsGroupGame);
        return prefix + "/detail";
    }

    @ApiOperation("修改保存团购商品")
    @RequiresPermissions("business:smsGroupGame:edit")
    @Log(title = "团购商品", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@RequestBody SmsGroupGameParam param) {
        return toAjax(smsGroupGameService.updateSmsGroupGame(param));
    }

    @ApiOperation("批量删除团购商品")
    @RequiresPermissions("business:smsGroupGame:remove")
    @Log(title = "团购商品", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(Long ids) {
        return toAjax(smsGroupGameService.deleteSmsGroupGameById(ids));
    }

    @ApiOperation("开始活动")
    @RequiresPermissions("business:smsGroupGame:edit")
    @Log(title = "团购商品", businessType = BusinessType.UPDATE)
    @PostMapping("/start")
    @ResponseBody
    public AjaxResult start(String ids) {
        return toAjax(smsGroupGameService.start(ids));
    }

    @ApiOperation("结束活动")
    @RequiresPermissions("business:smsGroupGame:edit")
    @Log(title = "团购商品", businessType = BusinessType.UPDATE)
    @PostMapping("/stop")
    @ResponseBody
    public AjaxResult stop(Long id) {
        return toAjax(smsGroupGameService.stop(id));
    }

    @ApiOperation("团购商品唯一校验")
    @PostMapping("/checkUnique")
    @ResponseBody
    public String checkUnique(SmsGroupGame smsGroupGame) {
        return smsGroupGameService.checkSmsGroupGameUnique(smsGroupGame);
    }

}
