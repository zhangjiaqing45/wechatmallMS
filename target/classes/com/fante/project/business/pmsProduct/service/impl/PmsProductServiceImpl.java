package com.fante.project.business.pmsProduct.service.impl;

import com.fante.common.business.enums.AuditTypeEnum;
import com.fante.common.business.enums.ProductAttributeCategoryConst;
import com.fante.common.constant.Constants;
import com.fante.common.utils.Checker;
import com.fante.common.utils.DateUtils;
import com.fante.common.utils.StringUtils;
import com.fante.common.utils.bean.BeanUtils;
import com.fante.common.utils.idgen.IdGenerator;
import com.fante.common.utils.security.ShiroUtils;
import com.fante.common.utils.text.Convert;
import com.fante.project.api.appView.domain.PmsProductDetailPageInfo;
import com.fante.project.business.omsOrderItem.domain.OmsOrderItem;
import com.fante.project.business.pmsBrand.domain.PmsBrand;
import com.fante.project.business.pmsBrand.mapper.PmsBrandMapper;
import com.fante.project.business.pmsBrand.service.IPmsBrandService;
import com.fante.project.business.pmsFeightTemplate.domain.PmsFeightTemplate;
import com.fante.project.business.pmsFeightTemplate.mapper.PmsFeightTemplateMapper;
import com.fante.project.business.pmsFeightTemplate.service.IPmsFeightTemplateService;
import com.fante.project.business.pmsProduct.domain.*;
import com.fante.project.business.pmsProduct.mapper.PmsProductMapper;
import com.fante.project.business.pmsProduct.service.IPmsProductService;
import com.fante.project.business.pmsProductAttributeCategory.domain.PmsProductAttributeCategory;
import com.fante.project.business.pmsProductAttributeCategory.mapper.PmsProductAttributeCategoryMapper;
import com.fante.project.business.pmsProductAttributeCategory.service.IPmsProductAttributeCategoryService;
import com.fante.project.business.pmsProductAuditLog.service.IPmsProductAuditLogService;
import com.fante.project.business.pmsProductCategory.domain.PmsProductCategory;
import com.fante.project.business.pmsProductCategory.mapper.PmsProductCategoryMapper;
import com.fante.project.business.pmsProductCategory.service.IPmsProductCategoryService;
import com.fante.project.business.pmsProductLog.domain.PmsProductLogParam;
import com.fante.project.business.pmsProductLog.service.IPmsProductLogService;
import com.fante.project.business.pmsSkuStock.domain.PmsSkuStock;
import com.fante.project.business.pmsSkuStock.mapper.PmsSkuStockMapper;
import com.fante.project.business.smsHomeRecommendProduct.service.ISmsHomeRecommendProductService;
import com.fante.project.system.user.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 商品信息Service业务层处理
 *
 * @author fante
 * @date 2020-03-12
 */
@Service
public class PmsProductServiceImpl implements IPmsProductService {

    private static Logger log = LoggerFactory.getLogger(PmsProductServiceImpl.class);
    /**商品 */
    @Autowired
    private PmsProductMapper pmsProductMapper;
    /**商品sku */
    @Autowired
    private PmsSkuStockMapper pmsSkuStockMapper;
    /**商品分类*/
    @Autowired
    private IPmsProductCategoryService iPmsProductCategoryService;
    /**商品品牌*/
    @Autowired
    private IPmsBrandService iPmsBrandService;
    /** 商品运费模板*/
    @Autowired
    private IPmsFeightTemplateService iPmsFeightTemplateService;
    /**商品属性分类 */
    @Autowired
    private ISmsHomeRecommendProductService iSmsHomeRecommendProductService;
    /**商品推荐类型*/
    @Autowired
    private IPmsProductAttributeCategoryService iPmsProductAttributeCategoryService;
    /** 商品操作记录*/
    @Autowired
    private IPmsProductLogService iPmsProductLogService;
    /** 商品审核的操作记录*/
    @Autowired
    private IPmsProductAuditLogService iPmsProductAuditLogService;



    /**
     * 查询商品信息
     *
     * @param id 商品信息ID
     * @return 商品信息
     */
    @Override
    public PmsProduct selectPmsProductById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        return pmsProductMapper.selectPmsProductById(id);
    }

    /**
     * 查询商品信息列表
     *
     * @param pmsProduct 商品信息
     * @return 商品信息集合
     */
    @Override
    public List<PmsProduct> selectPmsProductList(PmsProduct pmsProduct) {
        return pmsProductMapper.selectPmsProductList(pmsProduct);
    }

    /**
     * 查询商品信息列表 包括按钮组
     *
     * @param pmsProduct 商品信息
     * @return 商品信息集合
     */
    @Override
    public List<PmsProduct> getPmsProductList(PmsProduct pmsProduct) {
        return pmsProductMapper.getPmsProductList(pmsProduct);
    }

    /**
     * 查询商品信息数量
     *
     * @param pmsProduct 商品信息
     * @return 结果
     */
    @Override
    public int countPmsProduct(PmsProduct pmsProduct){
        return pmsProductMapper.countPmsProduct(pmsProduct);
    }

    /**
     * 唯一校验
     *
     * @param pmsProduct 商品信息
     * @return 结果
     */
    @Override
    public String checkPmsProductUnique(PmsProduct pmsProduct) {
        return pmsProductMapper.checkPmsProductUnique(pmsProduct) > 0 ? Constants.NOT_UNIQUE : Constants.UNIQUE;
    }

    /**
     * 验证商品中的各种参数id是否存在并设置其name值
     */
    private void validateProduct(PmsProduct pmsProduct){
        //查询并设置商品分类id和name
        Long categoryId = pmsProduct.getProductCategoryId();
        PmsProductCategory category = iPmsProductCategoryService.validatePmsProductCategoryById(categoryId);
        Checker.check(ObjectUtils.isEmpty(category),"选择的商品分类不可用！");
        pmsProduct.setProductCategoryName(category.getName());

        //查询并设置商品品牌id和name
        Long brandId = pmsProduct.getBrandId();
        PmsBrand pmsBrand = iPmsBrandService.selectPmsBrandById(brandId);
        Checker.check(ObjectUtils.isEmpty(pmsBrand),"选择的商品品牌不可用！");
        pmsProduct.setBrandName(pmsBrand.getName());

        //查询并验证商品运费模板
        /*Long feightTemplateId = pmsProduct.getFeightTemplateId();
        PmsFeightTemplate pmsFeightTemplate =iPmsFeightTemplateService.selectPmsFeightTemplateById(feightTemplateId);
        Checker.check(ObjectUtils.isEmpty(pmsFeightTemplate),"选择的商品运费模板不可用！");*/

        //查询并验证商品类型分类id
        Long attrCateId = pmsProduct.getProductAttributeCategoryId();
        PmsProductAttributeCategory pmsProductAttributeCategory = iPmsProductAttributeCategoryService.selectPmsProductAttributeCategoryById(attrCateId);
        Checker.check(ObjectUtils.isEmpty(pmsProductAttributeCategory),"选择的商品属性分类不可用！");
    }

    /**
     * 新增商品信息
     *
     * @param productParam 商品信息
     * @return 新增商品信息数量
     */
    @Override
    public long insertPmsProduct(PmsProductParam productParam) {
        //验证信息
        productParam.validate();
        //校验唯一性 防止 回退缓存页面 重复提交
        PmsProduct uniqueCheck = new PmsProduct();
        uniqueCheck.setName(productParam.getName());
        Checker.check(StringUtils.equals(checkPmsProductUnique(uniqueCheck),
                Constants.NOT_UNIQUE),"该商品已存在，不可重复添加！");
        //通用参数
        User sysUser = ShiroUtils.getSysUser();
        Date nowDate = DateUtils.getNowDate();
        //创建插入的商品信息
        PmsProduct pmsProduct = new PmsProduct();
        BeanUtils.copyBeanProp(pmsProduct,productParam);
        //验证商品中的各种参数id是否存在并设置其name值
        validateProduct(pmsProduct);
        //设置创建时间/人/店铺id
        pmsProduct.setCreateTime(nowDate);
        pmsProduct.setCreateBy(sysUser.getLoginName());
        pmsProduct.setShopId(sysUser.getDeptId());
        //设置商品货号 审核状态
        pmsProduct.setProductSn(String.valueOf(IdGenerator.nextId()));
        pmsProduct.setVerifyStatus(AuditTypeEnum.CREATE.getType());
        //插入数据库
        pmsProductMapper.insertPmsProduct(pmsProduct);
        //获取商品id
        Long productId = pmsProduct.getId();
        String productName = pmsProduct.getName();
        //关联推荐类型
        iSmsHomeRecommendProductService.batchInsertSmsHomeRecommendProductByTypes(productId,productParam.getRecommendProductType(),sysUser.getLoginName());
        List<PmsSkuStock> skuList = productParam.getSkuList();
        for (PmsSkuStock skuStock : skuList) {
            //设置创建时间/人/店铺id/商品id
            skuStock.setSkuCode(String.valueOf(IdGenerator.nextId()));
            //如果sku图片为空则默认显示商品主图
            if(StringUtils.isEmpty(skuStock.getPic())){
                skuStock.setPic(pmsProduct.getPic());
            }
            skuStock.setCreateTime(nowDate);
            skuStock.setCreateBy(sysUser.getLoginName());
            skuStock.setShopId(sysUser.getDeptId());
            skuStock.setProductId(productId);
        }
        pmsSkuStockMapper.beachInsert(skuList);
        //插入一条log
        PmsProductLogParam productLogParam = new PmsProductLogParam();
        productLogParam.setProduct(pmsProduct);
        productLogParam.setAction(ProductAttributeCategoryConst.ActivityEnum.ADD);
        iPmsProductLogService.insertPmsProductLog(productLogParam);
        return productId;
    }

    /**
     * 修改商品信息
     *
     * @param productParam 商品信息
     * @return 修改商品信息数量
     */
    @Override
    public long updatePmsProduct(PmsProductParam productParam) {
        Long id = productParam.getId();
        Checker.check(ObjectUtils.isEmpty(id),"未指定要修改的商品！");
        //验证此操作是否符合商品状态
        validateProductStatus(id, ProductAttributeCategoryConst.ActivityEnum.EDIT,StringUtils.EMPTY);
        //验证信息
        productParam.validate();
        //通用参数
        User sysUser = ShiroUtils.getSysUser();
        Date nowDate = DateUtils.getNowDate();
        //创建插入的商品信息
        PmsProduct pmsProduct = new PmsProduct();
        BeanUtils.copyBeanProp(pmsProduct,productParam);
        //验证商品中的各种参数id是否存在并设置其name值
        validateProduct(pmsProduct);
        //设置创建时间/人
        pmsProduct.setUpdateTime(nowDate);
        pmsProduct.setUpdateBy(sysUser.getLoginName());
        //修改审核类型为 待创建
        pmsProduct.setVerifyStatus(AuditTypeEnum.CREATE.getType());
        //更新到数据库
        int i = pmsProductMapper.updatePmsProduct(pmsProduct);
        //获取商品id
        Long productId = pmsProduct.getId();
        //更新关联推荐类型
        iSmsHomeRecommendProductService.batchInsertSmsHomeRecommendProductByTypes(productId,productParam.getRecommendProductType(),sysUser.getLoginName());
        //更新sku信息
        handleUpdateSkuStockList(id,productParam.getSkuList());
        return id;
    }

    /**
     *  1 新增的商品SKU信息不传ID，
     *  2 要修改的商品SKU信息传ID，
     *  3 删除的直接不传SKU信息
     * @param id
     * @param currSkuList
     */
    private void handleUpdateSkuStockList(Long id, List<PmsSkuStock> currSkuList) {
        //当前没有sku直接删除
        if(ObjectUtils.isEmpty(currSkuList)){
            PmsSkuStock skuStockExample = new PmsSkuStock();
            skuStockExample.setProductId(id);
            pmsSkuStockMapper.deleteByExample(skuStockExample);
            return;
        }

        //通用参数
        User sysUser = ShiroUtils.getSysUser();
        Date nowDate = DateUtils.getNowDate();

        //获取初始sku信息
        PmsSkuStock skuStockExample = new PmsSkuStock();
        skuStockExample.setProductId(id);
        List<PmsSkuStock> oriStuList = pmsSkuStockMapper.selectPmsSkuStockList(skuStockExample);
        //获取新增sku信息
        List<PmsSkuStock> insertSkuList = currSkuList.stream()
                .filter(item->item.getId()==null)
                .collect(Collectors.toList());
        //获取需要更新的sku信息
        List<PmsSkuStock> updateSkuList = currSkuList.stream()
                .filter(item->item.getId()!=null)
                .collect(Collectors.toList());
        //获取需要跟新的List<skuId>
        List<Long> updateSkuIds = updateSkuList.stream()
                .map(PmsSkuStock::getId)
                .collect(Collectors.toList());
        //获取需要删除的sku信息
        List<PmsSkuStock> removeSkuList = oriStuList.stream()
                .filter(item-> !updateSkuIds.contains(item.getId()))
                .collect(Collectors.toList());
        //新增sku
        if(!ObjectUtils.isEmpty(insertSkuList)){
            insertSkuList.forEach(skuStock->{
                skuStock.setSkuCode("skuCode_"+IdGenerator.nextId());
                skuStock.setCreateTime(nowDate);
                skuStock.setCreateBy(sysUser.getLoginName());
                skuStock.setShopId(sysUser.getDeptId());
                skuStock.setProductId(id);
            });
            pmsSkuStockMapper.beachInsert(insertSkuList);
        }
        //删除sku
        if(!ObjectUtils.isEmpty(removeSkuList)){
            String[] removeSkuIds = removeSkuList.stream()
                    .map(sku->(sku.getId()).toString())
                    .toArray(String[] :: new);
            pmsSkuStockMapper.deletePmsSkuStockByIds(removeSkuIds);
        }
        //修改sku
        if(!ObjectUtils.isEmpty(updateSkuList)){
            for (PmsSkuStock pmsSkuStock : updateSkuList) {
                pmsSkuStock.setUpdateTime(nowDate);
                pmsSkuStock.setUpdateBy(sysUser.getLoginName());
                pmsSkuStockMapper.updatePmsSkuStock(pmsSkuStock);
            }
        }
    }

    /**
     * 删除商品信息对象
     *
     * @param ids 需要删除的数据ID
     * @return 删除商品信息数量
     */
    @Override
    public int deletePmsProductByIds(String ids) {
        //验证此操作是否符合商品状态
        Arrays.stream(Convert.toStrArray(ids)).forEach(id ->{
            validateProductStatus(Long.parseLong(id), ProductAttributeCategoryConst.ActivityEnum.DEL,StringUtils.EMPTY);
        });
        return pmsProductMapper.deletePmsProductByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除商品信息信息
     *
     * @param id 商品信息ID
     * @return 删除商品信息数量
     */
    @Override
    public int deletePmsProductById(Long id) {
        //验证此操作是否符合商品状态
        validateProductStatus(id, ProductAttributeCategoryConst.ActivityEnum.DEL,StringUtils.EMPTY);

        return pmsProductMapper.deletePmsProductById(id);
    }

    /**
     * 提交审核
     * @param id
     * @return
     */
    @Override
    public int updatePmsProductVerify(Long id) {
        //验证此操作是否符合商品状态
        validateProductStatus(id, ProductAttributeCategoryConst.ActivityEnum.AUDIT,StringUtils.EMPTY);
        PmsProduct pmsProduct = new PmsProduct();
        pmsProduct.setId(id);
        pmsProduct.setVerifyStatus(AuditTypeEnum.WAIT.getType());
        pmsProductMapper.updatePmsProduct(pmsProduct);
        //创建审核log
        return iPmsProductAuditLogService.addPmsProductAuditLog(pmsProduct);
    }
    /**
     * 条件查询商品信息列表包括店铺名称
     * @param pmsProduct
     * @return
     */
    @Override
    public List<PmsProductResult> selectPmsProductShowList(PmsProduct pmsProduct) {
        return pmsProductMapper.selectPmsProductShowList(pmsProduct);
    }
    /**
     * 获取商品的审核详情
     * @param id
     * @return
     */
    @Override
    public PmsProductResult selectPmsProductAuditDetail(Long id) {
        PmsProductResult productResult = pmsProductMapper.selectPmsProductAuditById(id);
        if(ObjectUtils.isEmpty(productResult)){
            return productResult;
        }
        Long templateId = productResult.getFeightTemplateId();
        PmsFeightTemplate template = iPmsFeightTemplateService.selectPmsFeightTemplateById(templateId);
        productResult.setPmsFeightTemplate(template);
        return productResult;
    }


    /**
     * 商品审核通过
     * @param pmsProduct
     * @return
     */
    @Override
    public int pass(PmsProduct pmsProduct) {
        //验证参数
        Checker.check(ObjectUtils.isEmpty(pmsProduct.getId()),"未指定审核的商品！");
        PmsProduct updateProduct = new PmsProduct();
        updateProduct.setId(pmsProduct.getId());
        updateProduct.setAuditMsg(pmsProduct.getAuditMsg());
        updateProduct.setVerifyStatus(AuditTypeEnum.SUCCESS.getType());
        //更新审核状态
        pmsProductMapper.updatePmsProduct(updateProduct);
        //插入审核log
        return iPmsProductAuditLogService.productAuditLog(updateProduct);
    }

    /**
     * 商品审核拒绝
     * @param pmsProduct
     * @return
     */
    @Override
    public int refuse(PmsProduct pmsProduct) {
        //验证参数
        Checker.check(ObjectUtils.isEmpty(pmsProduct.getId()),"未指定审核的商品！");
        Checker.check(StringUtils.isEmpty(pmsProduct.getAuditMsg()),"未填写审核信息！");
        PmsProduct updateProduct = new PmsProduct();
        updateProduct.setId(pmsProduct.getId());
        updateProduct.setAuditMsg(pmsProduct.getAuditMsg());
        updateProduct.setVerifyStatus(AuditTypeEnum.FAIL.getType());
        //更新审核状态
        pmsProductMapper.updatePmsProduct(updateProduct);
        //插入审核log
        return iPmsProductAuditLogService.productAuditLog(updateProduct);
    }

    /**
     * 验证此操作是否符合商品状态.
     * 插入一条log
     * @param id 商品id
     * @param btn 操作的按钮
     */
    private void validateProductStatus(Long id, ProductAttributeCategoryConst.ActivityEnum btn,String newValue){
        //验证商品是否存在
        PmsProduct oldProduct = pmsProductMapper.selectPmsProductById(id);
        Checker.check(ObjectUtils.isEmpty(oldProduct),"提交数据异常！");
        //验证商品状态
        List<String> btns = ProductAttributeCategoryConst.ActivityEnum.getBtns(oldProduct.getVerifyStatus(), oldProduct.getPublishStatus());
        Checker.checkOp(btns.contains(btn.getType()),"该商品状态不可执行此操作.");
        //插入一条log
        PmsProductLogParam productLogParam = new PmsProductLogParam();
        productLogParam.setProduct(oldProduct);
        productLogParam.setAction(btn);
        productLogParam.setNewValue(newValue);
        iPmsProductLogService.insertPmsProductLog(productLogParam);
    }

    /**
     * 商品上架
     * @param id
     * @return
     */
    @Override
    public int putAway(Long id) {
        //验证此操作是否符合商品状态
        validateProductStatus(id, ProductAttributeCategoryConst.ActivityEnum.PUTAWAY,StringUtils.EMPTY);
        PmsProduct updateProduct = new PmsProduct();
        updateProduct.setId(id);
        updateProduct.setPublishStatus(ProductAttributeCategoryConst.publicStatusEnum.PUTAWAY.getType());
        return pmsProductMapper.updatePmsProduct(updateProduct);
    }
    /**
     * 商品下架
     * @param id
     * @return
     */
    @Override
    public int soldOut(Long id) {
        //验证此操作是否符合商品状态
        validateProductStatus(id, ProductAttributeCategoryConst.ActivityEnum.SOLDOUT,StringUtils.EMPTY);
        PmsProduct updateProduct = new PmsProduct();
        updateProduct.setId(id);
        updateProduct.setPublishStatus(ProductAttributeCategoryConst.publicStatusEnum.SOLDOUT.getType());
        return pmsProductMapper.updatePmsProduct(updateProduct);
    }

    /**
     * 行内修改商品序号信息(序号修改)
     *
     * @param pmsProduct 商品信息
     * @return 修改商品信息数量
     */
    @Override
    public int editable(PmsProduct pmsProduct){
        Long id = pmsProduct.getId();
        Long sort = pmsProduct.getSort();
        BigDecimal price = pmsProduct.getPrice();
        BigDecimal originalPrice = pmsProduct.getOriginalPrice();
        Long sale = pmsProduct.getSale();

        boolean bool_id = ObjectUtils.isEmpty(id);
        boolean bool_sort = ObjectUtils.isEmpty(sort);
        boolean bool_price = ObjectUtils.isEmpty(price);
        boolean bool_originalPrice = ObjectUtils.isEmpty(originalPrice);
        boolean bool_sale = ObjectUtils.isEmpty(sale);

        Checker.check(bool_id,"未指定要修改的商品！");
        Checker.check(bool_sort && bool_price && bool_originalPrice && bool_sale,"参数缺失,修改失败！");
        PmsProduct updateProduct = new PmsProduct();
        updateProduct.setId(id);
        //不允许同时修改多个
        if(!bool_sort){
            validateProductStatus(id, ProductAttributeCategoryConst.ActivityEnum.SORT,String.valueOf(sort));
            updateProduct.setSort(sort);
        }else if(!bool_price){
            validateProductStatus(id, ProductAttributeCategoryConst.ActivityEnum.PRICE,price.toString());
            updateProduct.setPrice(price);
        }else if(!bool_originalPrice){
            validateProductStatus(id, ProductAttributeCategoryConst.ActivityEnum.ORIGINALPRICE,originalPrice.toString());
            updateProduct.setOriginalPrice(originalPrice);
        }else{
            validateProductStatus(id, ProductAttributeCategoryConst.ActivityEnum.ORIGINALPRICE,sale.toString());
            updateProduct.setSale(sale);
        }
        return pmsProductMapper.updatePmsProduct(updateProduct);
    }

    /**
     * 统计店铺下的指定商品
     * @param shopId
     * @param pIds
     * @return
     */
    @Override
    public int countProductsWithInShop(Long shopId, Long[] pIds) {
        Checker.check(ObjectUtils.isEmpty(shopId), "缺少店铺标识");
        Checker.check(ObjectUtils.isEmpty(pIds), "缺少商品信息");
        return pmsProductMapper.countProductsWithInShop(shopId, pIds);
    }
    /**
     * 通过商品ids查询商品
     * @param productIds
     * @return
     */
    @Override
    public List<PmsProduct> selectPmsProductByIds(Long[] productIds) {
        return pmsProductMapper.selectPmsProductByIds(productIds);
    }
    /**
     * (app)根据商品分类,商品名称获取商品
     * @return
     */
    @Override
    public List<PmsProductDetailPageInfo> getProductList(Long categoryId, String name, Long shopId) {
        return pmsProductMapper.getProductList(ProductAttributeCategoryConst.publicStatusEnum.PUTAWAY.getType(),categoryId, name,shopId);
    }
    /**
     * (app)根据id查询商品详情
     * @param id
     * @return
     */
    @Override
    public PmsProductDetailPageInfo getDetailById(Long id) {
        Checker.check(ObjectUtils.isEmpty(id), "商品参数缺失");
        return pmsProductMapper.getDetailById(id,ProductAttributeCategoryConst.publicStatusEnum.PUTAWAY.getType());
    }
    /**
     *  商品销量增加
     * @param orderItemList
     * @return
     */
    @Override
    public int addSale(List<OmsOrderItem> orderItemList) {
        return pmsProductMapper.addSale(orderItemList);
    }
}
