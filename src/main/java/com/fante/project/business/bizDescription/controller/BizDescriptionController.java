package com.fante.project.business.bizDescription.controller;

import com.fante.common.utils.Checker;
import com.fante.common.utils.StringUtils;
import com.fante.common.utils.poi.ExcelUtil;
import com.fante.framework.aspectj.lang.annotation.Log;
import com.fante.framework.aspectj.lang.enums.BusinessType;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.framework.web.domain.Ztree;
import com.fante.project.business.bizDescription.domain.BizDescription;
import com.fante.project.business.bizDescription.service.IBizDescriptionService;
import com.fante.project.business.bizDescription.utils.TreeUtils;
import com.fante.project.system.user.domain.User;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文档说明Controller
 *
 * @author fante
 * @date 2020-01-17
 */
@Controller
@RequestMapping("/business/bizDescription")
public class BizDescriptionController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(BizDescriptionController.class);

    private String prefix = "business/bizDescription";

    @Autowired
    private IBizDescriptionService bizDescriptionService;

    @RequiresPermissions("business:bizDescription:view")
    @GetMapping()
    public String bizDescription() {
        return prefix + "/bizDescription";
    }

    /**
     * 查询文档说明树列表
     */
    @RequiresPermissions("business:bizDescription:list")
    @PostMapping("/list")
    @ResponseBody
    public List<BizDescription> list(BizDescription bizDescription) {
        User user = getSysUser();
        if (!user.isAdmin()) {
            bizDescription.setUserType(user.getUserType());
        }
        List<BizDescription> list = bizDescriptionService.selectBizDescriptionList(bizDescription);
        return list;
    }

    /**
     * 系统使用说明
     */
    @GetMapping("/index")
    public String index(ModelMap map) {
        BizDescription bizDescription = new BizDescription();
        User user = getSysUser();
        if (!user.isAdmin()) {
            bizDescription.setUserType(user.getUserType());
        }
        List<BizDescription> list = bizDescriptionService.selectBizDescriptionList(bizDescription);
        map.put("list", TreeUtils.getChildPerms(list,0));
        return prefix + "/index";
    }

    /**
     * 导出文档说明列表
     */
    @RequiresPermissions("business:bizDescription:export")
    @Log(title = "文档说明", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BizDescription bizDescription) {
        List<BizDescription> list = bizDescriptionService.selectBizDescriptionList(bizDescription);
        ExcelUtil<BizDescription> util = new ExcelUtil<BizDescription>(BizDescription.class);
        return util.exportExcel(list, "bizDescription");
    }

    /**
     * 新增文档说明
     */
    @GetMapping(value = {"/add/{parentId}", "/add/"})
    public String add(@PathVariable(value = "parentId", required = false) Long parentId, ModelMap mmap) {
        BizDescription description = null;
        if (0L != parentId) {
            description = bizDescriptionService.selectBizDescriptionById(parentId);
        } else {
            description = new BizDescription();
            description.setId(0L);
            description.setDescTitle("主目录");
        }
        mmap.put("isAdmin", getSysUser().isAdmin());
        mmap.put("description", description);
        return prefix + "/add";
    }

    /**
     * 新增保存文档说明
     */
    @RequiresPermissions("business:bizDescription:add")
    @Log(title = "文档说明", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BizDescription bizDescription) {
        bizDescription.setCreateBy(getLoginName());
        return toAjax(bizDescriptionService.insertBizDescription(bizDescription));
    }

    /**
     * 修改文档说明
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        BizDescription bizDescription = bizDescriptionService.selectBizDescriptionById(id);
        mmap.put("bizDescription", bizDescription);
        mmap.put("isAdmin", getSysUser().isAdmin());
        return prefix + "/edit";
    }

    /**
     * 修改保存文档说明
     */
    @RequiresPermissions("business:bizDescription:edit")
    @Log(title = "文档说明", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BizDescription bizDescription) {
        bizDescription.setUpdateBy(getLoginName());
        return toAjax(bizDescriptionService.updateBizDescription(bizDescription));
    }

    /**
     * 删除
     */
    @RequiresPermissions("business:bizDescription:remove")
    @Log(title = "文档说明", businessType = BusinessType.DELETE)
    @GetMapping("/remove/{id}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("id") Long id) {
        return toAjax(bizDescriptionService.deleteBizDescriptionById(id));
    }

    /**
     * 选择文档说明树
     */
    @GetMapping(value = {"/selectBizDescriptionTree/{id}", "/selectBizDescriptionTree/"})
    public String selectBizDescriptionTree(@PathVariable(value = "id", required = false) Long id, ModelMap mmap) {
        if (StringUtils.isNotNull(id)) {
            mmap.put("bizDescription", bizDescriptionService.selectBizDescriptionById(id));
        }
        return prefix + "/tree";
    }

    /**
     * 加载文档说明树列表
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData() {
        List<Ztree> ztrees = bizDescriptionService.selectBizDescriptionTree();
        return ztrees;
    }

    @GetMapping("/get")
    @ResponseBody
    public AjaxResult get(Long id) {
        Checker.check(ObjectUtils.isEmpty(id), "缺少文档标识");
        return AjaxResult.success()
                .put("doc", bizDescriptionService.selectBizDescriptionById(id));
    }
}
