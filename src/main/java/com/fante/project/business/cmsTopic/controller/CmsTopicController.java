package com.fante.project.business.cmsTopic.controller;

import com.fante.common.utils.poi.ExcelUtil;
import com.fante.framework.aspectj.lang.annotation.Log;
import com.fante.framework.aspectj.lang.enums.BusinessType;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.framework.web.page.TableDataInfo;
import com.fante.project.business.cmsTopic.domain.CmsTopic;
import com.fante.project.business.cmsTopic.domain.CmsTopicDTO;
import com.fante.project.business.cmsTopic.service.ICmsTopicService;
import com.fante.project.system.user.domain.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

/**
 * 话题Controller
 *
 * @author fante
 * @date 2020-03-18
 */
@Api(tags = "CmsTopicController", description = "话题")
@Controller
@RequestMapping("/business/cmsTopic")
public class CmsTopicController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(CmsTopicController.class);

    private String prefix = "business/cmsTopic";

    @Autowired
    private ICmsTopicService cmsTopicService;

    @RequiresPermissions("business:cmsTopic:view")
    @GetMapping()
    public String cmsTopic() {
        return prefix + "/cmsTopic";
    }

    @ApiOperation("条件查询话题列表")
    @RequiresPermissions("business:cmsTopic:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CmsTopicDTO cmsTopicDTO) {
        startPage();
        User user = getSysUser();
        List<CmsTopicDTO> list = Collections.emptyList();
        if (user.isAdmin() || user.isSystem()) {
            list = cmsTopicService.selectJoinList(cmsTopicDTO);
        }
        return getDataTable(list);
    }

    @ApiOperation("导出话题列表")
    @RequiresPermissions("business:cmsTopic:export")
    @Log(title = "话题", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CmsTopicDTO cmsTopicDTO) {
        User user = getSysUser();
        List<CmsTopicDTO> list = Collections.emptyList();
        if (user.isAdmin() || user.isSystem()) {
            list = cmsTopicService.selectJoinList(cmsTopicDTO);
        }
        ExcelUtil<CmsTopicDTO> util = new ExcelUtil<CmsTopicDTO>(CmsTopicDTO. class);
        return util.exportExcel(list, "话题列表");
    }

    @ApiOperation("[跳转] 到新增话题页面")
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    @ApiOperation("新增保存话题")
    @RequiresPermissions("business:cmsTopic:add")
    @Log(title = "话题", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CmsTopic cmsTopic) {
        return toAjax(cmsTopicService.insertCmsTopic(cmsTopic));
    }

    @ApiOperation("[跳转] 到话题编辑页面")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        CmsTopic cmsTopic =cmsTopicService.selectCmsTopicById(id);
        mmap.put("cmsTopic", cmsTopic);
        return prefix + "/edit";
    }

    @ApiOperation("修改保存话题")
    @RequiresPermissions("business:cmsTopic:edit")
    @Log(title = "话题", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CmsTopic cmsTopic) {
        return toAjax(cmsTopicService.updateCmsTopic(cmsTopic));
    }

    @ApiOperation("批量删除话题")
    @RequiresPermissions("business:cmsTopic:remove")
    @Log(title = "话题", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(cmsTopicService.deleteCmsTopicByIds(ids));
    }

    @ApiOperation("话题唯一校验")
    @PostMapping("/checkUnique")
    @ResponseBody
    public String checkUnique(CmsTopic cmsTopic) {
        return cmsTopicService.checkCmsTopicUnique(cmsTopic);
    }

}
