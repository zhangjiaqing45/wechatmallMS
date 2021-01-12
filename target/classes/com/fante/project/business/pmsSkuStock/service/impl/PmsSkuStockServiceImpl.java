package com.fante.project.business.pmsSkuStock.service.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import com.fante.common.business.enums.OrderConst;
import com.fante.common.business.enums.ProductActionEnum;
import com.fante.common.business.enums.ProductAttributeCategoryConst;
import com.fante.common.exception.BusinessException;
import com.fante.common.utils.Checker;
import com.fante.common.utils.DateUtils;
import com.fante.common.constant.Constants;
import com.fante.common.utils.StringUtils;
import com.fante.common.utils.security.ShiroUtils;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.project.api.omsOrderProcess.domain.CartSkuDto;
import com.fante.project.api.omsOrderProcess.domain.ValidateStockDTO;
import com.fante.project.business.omsOrder.domain.OmsOrderDetail;
import com.fante.project.business.omsOrderItem.domain.OmsOrderItem;
import com.fante.project.business.pmsBrand.domain.PmsBrand;
import com.fante.project.business.pmsFeightTemplate.domain.PmsFeightTemplate;
import com.fante.project.business.pmsProduct.domain.PmsProduct;
import com.fante.project.business.pmsProduct.mapper.PmsProductMapper;
import com.fante.project.business.pmsProductAttributeCategory.domain.PmsProductAttributeCategory;
import com.fante.project.business.pmsProductCategory.domain.PmsProductCategory;
import com.fante.project.business.pmsProductComment.domain.PmsProductComment;
import com.fante.project.business.pmsProductLog.domain.PmsProductLog;
import com.fante.project.business.pmsProductLog.domain.PmsProductLogParam;
import com.fante.project.business.pmsProductLog.mapper.PmsProductLogMapper;
import com.fante.project.business.pmsProductLog.service.IPmsProductLogService;
import com.fante.project.business.pmsSkuStock.domain.PmsSkuStockResult;
import com.fante.project.business.umsDistribution.domain.UmsDistributionResult;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fante.project.business.pmsSkuStock.mapper.PmsSkuStockMapper;
import com.fante.project.business.pmsSkuStock.domain.PmsSkuStock;
import com.fante.project.business.pmsSkuStock.service.IPmsSkuStockService;
import com.fante.common.utils.text.Convert;
import org.springframework.util.ObjectUtils;

/**
 * sku的库存Service业务层处理
 *
 * @author fante
 * @date 2020-03-14
 */
@Service
public class PmsSkuStockServiceImpl implements IPmsSkuStockService {

    private static Logger log = LoggerFactory.getLogger(PmsSkuStockServiceImpl.class);

    @Autowired
    private PmsSkuStockMapper pmsSkuStockMapper;

    @Autowired
    private PmsProductMapper pmsProductMapper;

    @Autowired
    private IPmsProductLogService iPmsProductLogService;

    /**
     * 查询sku的库存
     *
     * @param id sku的库存ID
     * @return sku的库存
     */
    @Override
    public PmsSkuStock selectPmsSkuStockById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        return pmsSkuStockMapper.selectPmsSkuStockById(id);
    }

    /**
     * 1.单独修改sku 执行该方法,例如 改库存.
     * 2.创建和修改时在商品中已经验证,所以不需要加该方法.
     * 3.验证此操作是否符合商品状态.
     * 4.插入一条log
     * @param id 商品sku id
     * @param btn 操作
     */
    private void validateSkuStatus(Long id, ProductAttributeCategoryConst.ActivityEnum btn){
        //验证商品是否存在
        PmsSkuStock skuStock = pmsSkuStockMapper.selectPmsSkuStockById(id);
        Checker.check(ObjectUtils.isEmpty(skuStock),"提交数据异常！");
        PmsProduct oldProduct = pmsProductMapper.selectPmsProductById(skuStock.getProductId());
        Checker.check(ObjectUtils.isEmpty(oldProduct),"该商品不存在！");
        //验证商品状态
        List<String> btns = ProductAttributeCategoryConst.ActivityEnum.getBtns(oldProduct.getVerifyStatus(), oldProduct.getPublishStatus());
        Checker.checkOp(btns.contains(btn.getType()),"该商品状态不可执行此操作.");
        //插入一条log
        PmsProductLogParam productLogParam = new PmsProductLogParam();
        productLogParam.setProduct(oldProduct);
        productLogParam.setSku(skuStock);
        productLogParam.setAction(btn);
        iPmsProductLogService.insertPmsProductLog(productLogParam);
    }
    /**
     * 查询sku的库存列表
     *
     * @param pmsSkuStock sku的库存
     * @return sku的库存集合
     */
    @Override
    public List<PmsSkuStock> selectPmsSkuStockList(PmsSkuStock pmsSkuStock) {
        return pmsSkuStockMapper.selectPmsSkuStockList(pmsSkuStock);
    }

    /**
     * 查询sku的库存数量
     *
     * @param pmsSkuStock sku的库存
     * @return 结果
     */
    @Override
    public int countPmsSkuStock(PmsSkuStock pmsSkuStock){
        return pmsSkuStockMapper.countPmsSkuStock(pmsSkuStock);
    }

    /**
     * 唯一校验
     *
     * @param pmsSkuStock sku的库存
     * @return 结果
     */
    @Override
    public String checkPmsSkuStockUnique(PmsSkuStock pmsSkuStock) {
        return pmsSkuStockMapper.checkPmsSkuStockUnique(pmsSkuStock) > 0 ? Constants.NOT_UNIQUE : Constants.UNIQUE;
    }

    /**
     * 新增sku的库存
     *
     * @param pmsSkuStock sku的库存
     * @return 新增sku的库存数量
     */
    @Override
    public int insertPmsSkuStock(PmsSkuStock pmsSkuStock) {

        pmsSkuStock.setCreateTime(DateUtils.getNowDate());

        return pmsSkuStockMapper.insertPmsSkuStock(pmsSkuStock);
    }

    /**
     * 修改sku的库存
     *
     * @param pmsSkuStock sku的库存
     * @return 修改sku的库存数量
     */
    @Override
    public int updatePmsSkuStock(PmsSkuStock pmsSkuStock) {
        //验证操作的合法性
        validateSkuStatus(pmsSkuStock.getId(), ProductAttributeCategoryConst.ActivityEnum.STOCK);
        Checker.check(ObjectUtils.isEmpty(pmsSkuStock.getStock()),"修改库存数量为空,操作失败！");
        //修改库存
        pmsSkuStock.setUpdateTime(DateUtils.getNowDate());
        pmsSkuStock.setUpdateBy(ShiroUtils.getLoginName());
        return pmsSkuStockMapper.updatePmsSkuStock(pmsSkuStock);
    }

    /**
     * 删除sku的库存对象
     *
     * @param ids 需要删除的数据ID
     * @return 删除sku的库存数量
     */
    @Override
    public int deletePmsSkuStockByIds(String ids) {
        return pmsSkuStockMapper.deletePmsSkuStockByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除sku的库存信息
     *
     * @param id sku的库存ID
     * @return 删除sku的库存数量
     */
    @Override
    public int deletePmsSkuStockById(Long id) {
        return pmsSkuStockMapper.deletePmsSkuStockById(id);
    }
    /**
     * 条件查询缺货的sku的库存列表
     * 只要lowStock不为空,查询的都是缺货商品sku
     * @param pmsSkuStock
     * @return
     */
    @Override
    public List<PmsSkuStock> stockoutList(PmsSkuStockResult pmsSkuStock) {
        //根据店铺id查询
        pmsSkuStock.setShopId(ShiroUtils.getSysUser().getDeptId());
        return pmsSkuStockMapper.stockoutList(pmsSkuStock);
    }

    /**
     * 修改保存sku的库存
     * @param pmsSkuStock
     * @param field
     * @return
     */
    @Override
    public int updateEditField(PmsSkuStock pmsSkuStock,String field){
        //验证修改值
        Checker.check(StringUtils.isEmpty(field),"未指定要修改的参数！");
        Long skuId = pmsSkuStock.getId();
        Checker.check(ObjectUtils.isEmpty(skuId),"没有找到要修改的商品规格！");
        PmsSkuStock updateSku = new PmsSkuStock();
        updateSku.setId(skuId);
        switch (field){
            case "price":
                Checker.check(ObjectUtils.isEmpty(pmsSkuStock.getPrice()),"价格参数缺失！");
                BigDecimal price = pmsSkuStock.getPrice();
                validateProductStatus(skuId, ProductAttributeCategoryConst.ActivityEnum.SKUPRICE,String.valueOf(price));
                updateSku.setPrice(price);
                break;
            case "stock":
                Checker.check(ObjectUtils.isEmpty(pmsSkuStock.getStock()),"库存参数缺失！");
                Long stock = pmsSkuStock.getStock();
                validateProductStatus(skuId, ProductAttributeCategoryConst.ActivityEnum.STOCK,String.valueOf(stock));
                updateSku.setStock(stock);
                break;
            case "lowStock":
                Checker.check(ObjectUtils.isEmpty(pmsSkuStock.getLowStock()),"库存预警值缺失！");
                Long lowStock = pmsSkuStock.getLowStock();
                validateProductStatus(skuId, ProductAttributeCategoryConst.ActivityEnum.LOWSTOCK,String.valueOf(lowStock));
                updateSku.setLowStock(lowStock);
                break;
            default:
                throw new BusinessException(AjaxResult.Type.ERROR.value(), "指定修改参数不存在！");
        }
        //修改库存
        updateSku.setUpdateTime(DateUtils.getNowDate());
        updateSku.setUpdateBy(ShiroUtils.getLoginName());
        return pmsSkuStockMapper.updateStockOrLowStockOrPrice(updateSku);
    }

    /**
     * 验证此操作是否符合商品状态.
     * 插入一条log
     * @param skuId skuId
     * @param btn 操作的按钮
     */
    private void validateProductStatus(Long skuId, ProductAttributeCategoryConst.ActivityEnum btn,String newValue){
        PmsSkuStock skuStock = pmsSkuStockMapper.selectPmsSkuStockById(skuId);
        Checker.check(ObjectUtils.isEmpty(skuStock),"要修改的商品规格不存在！");
        //验证商品是否存在
        PmsProduct product = pmsProductMapper.selectPmsProductById(skuStock.getProductId());
        Checker.check(ObjectUtils.isEmpty(product),"提交数据异常！");
        //验证商品状态
        List<String> btns = ProductAttributeCategoryConst.ActivityEnum.getBtns(product.getVerifyStatus(), product.getPublishStatus());
        Checker.checkOp(btns.contains(btn.getType()),"该商品状态不可执行此操作.");
        //插入一条log
        PmsProductLogParam productLogParam = new PmsProductLogParam();
        productLogParam.setProduct(product);
        productLogParam.setSku(skuStock);
        productLogParam.setAction(btn);
        productLogParam.setNewValue(newValue);
        iPmsProductLogService.insertPmsProductLog(productLogParam);
    }

    /**
     * 查询skus
     * @param skuIds
     * @return
     */
    @Override
    public List<PmsSkuStock> selectPmsSkuStockByIds(String[] skuIds) {
        if(ObjectUtils.isEmpty(skuIds)){
            skuIds = new String[]{"0"};
        }
        return pmsSkuStockMapper.selectPmsSkuStockByIds(skuIds);
    }
    /**
     * 根据购物车ids查询sku集合和数量
     * @param cartIds
     * @return
     */
    @Override
    public List<CartSkuDto> selectPmsSkuStockByCartIds(String cartIds) {
        return pmsSkuStockMapper.selectPmsSkuStockByCartIds(Convert.toStrArray(cartIds));
    }

    /**
     * 通过skuid查询cartSku需要的源数据数据
     * 源数据:未修改价格和库存等信息的原始商品信息
     * @return
     */
    @Override
    public CartSkuDto getProductDetailBySkuId(Long skuId) {
        return pmsSkuStockMapper.getProductDetailBySkuId(skuId);
    }
    /**
     * 取消 普通订单 减锁定库存
     * @param order
     */
    @Override
    public int recoverOrderStock(OmsOrderDetail order) {
        List<OmsOrderItem> list = order.getOrderItemList();
        Checker.check(ObjectUtils.isEmpty(list),"订单详情数据不能为空");
        return pmsSkuStockMapper.recoverOrderStock(list);
    }
    /**
     * 验证库存
     * @param stockList
     * @return
     */
    @Override
    public List<ValidateStockDTO> validateStock(List<ValidateStockDTO> stockList) {
        return pmsSkuStockMapper.validateStock(stockList);
    }
    /**
     * 批量修改添加锁定库存
     * @param stockList
     * @return
     */
    @Override
    public int batchAddLockStock(List<ValidateStockDTO> stockList) {
        return pmsSkuStockMapper.batchAddLockStock(stockList);
    }

    /**
     * 支付成功:扣减库存
     * @param orderItemList
     * @return
     */
    @Override
    public int subtractStock(List<OmsOrderItem> orderItemList) {
        return pmsSkuStockMapper.subtractStock(orderItemList);
    }
}
