package com.fante.project.business.pmsProduct.controller;

import com.fante.common.business.enums.ProductAttributeCategoryConst;
import com.fante.common.utils.Checker;
import com.fante.common.utils.poi.ExcelUtil;
import com.fante.framework.aspectj.lang.annotation.Log;
import com.fante.framework.aspectj.lang.enums.BusinessType;
import com.fante.framework.web.controller.BaseController;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.framework.web.page.TableDataInfo;
import com.fante.project.business.pmsProduct.domain.PmsProduct;
import com.fante.project.business.pmsProduct.domain.PmsProductBtn;
import com.fante.project.business.pmsProduct.domain.PmsProductParam;
import com.fante.project.business.pmsProduct.domain.PmsProductResult;
import com.fante.project.business.pmsProduct.service.IPmsProductService;
import com.fante.project.system.user.domain.User;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
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
 * 商品信息Controller
 *
 * @author fante
 * @date 2020-03-12
 */
@Api(tags = "PmsProductController", description = "商品信息")
@Controller
@RequestMapping("/business/pmsProduct")
public class PmsProductController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(PmsProductController.class);

    private String prefix = "business/pmsProduct";

    @Autowired
    private IPmsProductService pmsProductService;

    @RequiresPermissions("business:pmsProduct:view")
    @GetMapping()
    public String pmsProduct() {
        return prefix + "/pmsProduct";
    }

    @ApiOperation("条件查询商品信息列表")
    @RequiresPermissions("business:pmsProduct:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(PmsProduct pmsProduct) {
        User user = getSysUser();
        if (user.isFranchisee()) {
            pmsProduct.setShopId(user.getDeptId());
        }
        startPage();
        List<PmsProduct> list = pmsProductService.getPmsProductList(pmsProduct);
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(AjaxResult.Type.SUCCESS.value());
        rspData.setTotal(new PageInfo(list).getTotal());
        List<PmsProductBtn> collect = list.stream()
                .map(item -> {
                    //获取按钮组合
                    List<String> btns = ProductAttributeCategoryConst.ActivityEnum.getBtns(item.getVerifyStatus(), item.getPublishStatus());
                    return new PmsProductBtn(item, btns);
                }).collect(Collectors.toList());
        rspData.setRows(collect);
        return rspData;
    }

    @ApiOperation("条件查询商品信息列表包括店铺名称")
    @RequiresPermissions("business:pmsProduct:list")
    @PostMapping("/showList")
    @ResponseBody
    public TableDataInfo showList(PmsProduct pmsProduct) {
        //searchValue = 店铺name
        User user = getSysUser();
        if (user.isFranchisee()) {
            pmsProduct.setShopId(user.getDeptId());
        }
        startPage();
        List<PmsProductResult> list = pmsProductService.selectPmsProductShowList(pmsProduct);
        return getDataTable(list);
    }

    @ApiOperation("导出商品信息列表")
    @RequiresPermissions("business:pmsProduct:export")
    @Log(title = "商品信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PmsProduct pmsProduct) {
        User user = getSysUser();
        if (user.isFranchisee()) {
            pmsProduct.setShopId(user.getDeptId());
        }
        List<PmsProduct> list = pmsProductService.selectPmsProductList(pmsProduct);
        ExcelUtil<PmsProduct> util = new ExcelUtil<PmsProduct>(PmsProduct. class);
        return util.exportExcel(list, "pmsProduct");
    }

    @ApiOperation("[跳转] 到新增商品信息页面")
    @GetMapping("/add")
    public String add() {
        User user = getSysUser();
        Checker.checkOp(user.isFranchisee(), "登录用户非店铺用户");
        return prefix + "/add";
    }

    @ApiOperation("[跳转] 到商品审核页面")
    @RequiresPermissions("business:pmsProduct:audit")
    @GetMapping("/audit")
    public String audit() {
        User user = getSysUser();
        Checker.checkOp(user.isSystem()||user.isAdmin(), "登录用户非平台管理员");
        return prefix + "/auditList";
    }

    @ApiOperation("新增保存商品信息")
    @RequiresPermissions("business:pmsProduct:add")
    @Log(title = "商品信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestBody  PmsProductParam productParam){
        User user = getSysUser();
        Checker.checkOp(user.isFranchisee(), "登录用户非店铺用户");
        //返回页面商品id方便提交审核
        long productId = pmsProductService.insertPmsProduct(productParam);
        return ObjectUtils.isEmpty(productId)?AjaxResult.error("添加商品失败"):AjaxResult.success("添加商品成功",productId);
    }

    @ApiOperation("修改保存商品信息")
    @RequiresPermissions("business:pmsProduct:edit")
    @Log(title = "商品信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@RequestBody  PmsProductParam productParam) {
        User user = getSysUser();
        Checker.checkOp(user.isFranchisee(), "登录用户非店铺用户");
        //返回页面商品id方便提交审核
        long productId = pmsProductService.updatePmsProduct(productParam);
        return ObjectUtils.isEmpty(productId)?AjaxResult.error("编辑商品失败"):AjaxResult.success("编辑商品成功",productId);
    }

    @ApiOperation("[跳转] 到商品信息编辑页面")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        PmsProductResult pmsProduct =pmsProductService.selectPmsProductAuditDetail(id);
        mmap.put("pmsProduct", pmsProduct);
        return prefix + "/edit";
    }

    @ApiOperation("[跳转] 到商品信息审核页面")
    @RequiresPermissions("business:pmsProduct:audit")
    @GetMapping("/audit/{id}")
    public String audit(@PathVariable("id") Long id, ModelMap mmap) {
        PmsProductResult pmsProduct =pmsProductService.selectPmsProductAuditDetail(id);
        mmap.put("pmsProduct", pmsProduct);
        return prefix + "/audit";
    }

    @ApiOperation("[跳转] 到商品信息详情页面")
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, ModelMap mmap) {
        PmsProductResult pmsProduct =pmsProductService.selectPmsProductAuditDetail(id);
        mmap.put("pmsProduct", pmsProduct);
        return prefix + "/detail";
    }

    @ApiOperation("[跳转] 提交审核后跳转到商品列表")
    @Log(title = "商品信息", businessType = BusinessType.UPDATE)
    @GetMapping("/verify/{id}")
    public String verify(@PathVariable("id") Long id, ModelMap mmap) {
        mmap.put("verifyMsg",pmsProductService.updatePmsProductVerify(id)>0
                ? "提交审核成功！"
                : "提交审核失败！");
        return prefix + "/pmsProduct";
    }

    @ApiOperation("提交审核")
    @Log(title = "商品信息", businessType = BusinessType.UPDATE)
    @PostMapping("/pmsAudit")
    @ResponseBody
    public AjaxResult pmsAudit(Long id) {
        return pmsProductService.updatePmsProductVerify(id)>0
                ? AjaxResult.success("提交审核成功")
                : AjaxResult.error("提交审核失败");
    }

    @ApiOperation("上架")
    @PostMapping("/putAway")
    @Log(title = "商品信息", businessType = BusinessType.UPDATE)
    @ResponseBody
    public AjaxResult putAway(Long id) {
        return pmsProductService.putAway(id)>0
                ? AjaxResult.success("商品上架成功")
                : AjaxResult.error("商品上架失败");
    }

    @ApiOperation("下架")
    @PostMapping("/soldOut")
    @Log(title = "商品信息", businessType = BusinessType.UPDATE)
    @ResponseBody
    public AjaxResult soldOut(Long id) {
        return pmsProductService.soldOut(id)>0
                ? AjaxResult.success("商品下架成功")
                : AjaxResult.error("商品下架失败");
    }

    @ApiOperation("行内修改保存商品序号")
    @RequiresPermissions("business:pmsProduct:edit")
    @Log(title = "商品信息", businessType = BusinessType.UPDATE)
    @PostMapping("/editable")
    @ResponseBody
    public AjaxResult editable(PmsProduct pmsProduct) {
        User user = getSysUser();
        Checker.checkOp(user.isFranchisee(), "登录用户非店铺用户");
        return toAjax(pmsProductService.editable(pmsProduct));
    }

    @ApiOperation("唯一校验")
    @RequiresPermissions("business:pmsProduct:add")
    @Log(title = "商品信息", businessType = BusinessType.INSERT)
    @PostMapping("/checkUnique")
    @ResponseBody
    public String checkPmsProductUnique(PmsProduct pmsProduct) {
        return pmsProductService.checkPmsProductUnique(pmsProduct);
    }

    @ApiOperation("批量删除商品信息")
    @RequiresPermissions("business:pmsProduct:remove")
    @Log(title = "商品信息", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(pmsProductService.deletePmsProductByIds(ids));
    }

    @ApiOperation("商品审核通过")
    @RequiresPermissions("business:pmsProduct:update")
    @Log(title = "商品信息", businessType = BusinessType.UPDATE)
    @PostMapping("/pass")
    @ResponseBody
    public AjaxResult pass(PmsProduct pmsProduct) {
        return toAjax(pmsProductService.pass(pmsProduct));
    }

    @ApiOperation("商品审核拒绝")
    @RequiresPermissions("business:pmsProduct:update")
    @Log(title = "商品信息", businessType = BusinessType.UPDATE)
    @PostMapping("/refuse")
    @ResponseBody
    public AjaxResult refuse(PmsProduct pmsProduct) {
        return toAjax(pmsProductService.refuse(pmsProduct));
    }



}
