package com.fante.project.business.smsFlashPromotion.service.impl;

import com.fante.common.business.enums.CommonUse;
import com.fante.common.business.enums.ProductAttributeCategoryConst;
import com.fante.common.business.enums.SmsFlashConst;
import com.fante.common.constant.Constants;
import com.fante.common.utils.Checker;
import com.fante.common.utils.DateUtils;
import com.fante.common.utils.StringUtils;
import com.fante.common.utils.security.ShiroUtils;
import com.fante.common.utils.text.Convert;
import com.fante.project.business.pmsProduct.domain.PmsProduct;
import com.fante.project.business.pmsProduct.service.IPmsProductService;
import com.fante.project.business.pmsSkuStock.domain.PmsSkuStock;
import com.fante.project.business.pmsSkuStock.service.IPmsSkuStockService;
import com.fante.project.business.smsFlashPromotion.domain.SmsFlashPromotion;
import com.fante.project.business.smsFlashPromotion.mapper.SmsFlashPromotionMapper;
import com.fante.project.business.smsFlashPromotion.service.ISmsFlashPromotionService;
import com.fante.project.business.smsFlashPromotionProduct.domain.SmsFlashProductAddDTO;
import com.fante.project.business.smsFlashPromotionProduct.domain.SmsFlashProductEditDTO;
import com.fante.project.business.smsFlashPromotionProduct.domain.SmsFlashPromotionProduct;
import com.fante.project.business.smsFlashPromotionProduct.service.ISmsFlashPromotionProductService;
import com.fante.project.business.smsFlashPromotionSku.domain.SmsFlashProductSkuSetDTO;
import com.fante.project.business.smsFlashPromotionSku.domain.SmsFlashPromotionSku;
import com.fante.project.business.smsFlashPromotionSku.service.ISmsFlashPromotionSkuService;
import com.fante.project.system.user.domain.User;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 秒杀活动Service业务层处理
 *
 * @author fante
 * @date 2020-03-21
 */
@Service
public class SmsFlashPromotionServiceImpl implements ISmsFlashPromotionService {

    private static Logger log = LoggerFactory.getLogger(SmsFlashPromotionServiceImpl.class);

    @Autowired
    private SmsFlashPromotionMapper smsFlashPromotionMapper;
    @Autowired
    private ISmsFlashPromotionProductService smsFlashPromotionProductService;
    @Autowired
    private ISmsFlashPromotionSkuService smsFlashPromotionSkuService;
    @Autowired
    private IPmsProductService pmsProductService;
    @Autowired
    private IPmsSkuStockService pmsSkuStockService;

    /**
     * 查询秒杀活动
     *
     * @param id 秒杀活动ID
     * @return 秒杀活动
     */
    @Override
    public SmsFlashPromotion selectSmsFlashPromotionById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        return smsFlashPromotionMapper.selectSmsFlashPromotionById(id);
    }

    /**
     * 查询秒杀活动列表
     *
     * @param smsFlashPromotion 秒杀活动
     * @return 秒杀活动集合
     */
    @Override
    public List<SmsFlashPromotion> selectSmsFlashPromotionList(SmsFlashPromotion smsFlashPromotion) {
        return smsFlashPromotionMapper.selectSmsFlashPromotionList(smsFlashPromotion);
    }

    /**
     * 查询秒杀活动数量
     *
     * @param smsFlashPromotion 秒杀活动
     * @return 结果
     */
    @Override
    public int countSmsFlashPromotion(SmsFlashPromotion smsFlashPromotion) {
        return smsFlashPromotionMapper.countSmsFlashPromotion(smsFlashPromotion);
    }

    /**
     * 唯一校验
     *
     * @param smsFlashPromotion 秒杀活动
     * @return 结果
     */
    @Override
    public String checkSmsFlashPromotionUnique(SmsFlashPromotion smsFlashPromotion) {
        return smsFlashPromotionMapper.checkSmsFlashPromotionUnique(smsFlashPromotion) > 0 ? Constants.NOT_UNIQUE : Constants.UNIQUE;
    }

    /**
     * 新增秒杀活动
     *
     * @param smsFlashPromotion 秒杀活动
     * @return 新增秒杀活动数量
     */
    @Override
    public int insertSmsFlashPromotion(SmsFlashPromotion smsFlashPromotion) {
        if (StringUtils.isBlank(smsFlashPromotion.getCreateBy())) {
            smsFlashPromotion.setCreateBy(ShiroUtils.getLoginName());
        }
        smsFlashPromotion.setCreateTime(DateUtils.getNowDate());
        smsFlashPromotion.setStatus(SmsFlashConst.StatusEnum.AWAIT.getType());
        return smsFlashPromotionMapper.insertSmsFlashPromotion(smsFlashPromotion);
    }

    /**
     * 修改秒杀活动
     *
     * @param smsFlashPromotion 秒杀活动
     * @return 修改秒杀活动数量
     */
    @Override
    public int updateSmsFlashPromotion(SmsFlashPromotion smsFlashPromotion) {
        if (StringUtils.isBlank(smsFlashPromotion.getUpdateBy())) {
            smsFlashPromotion.setUpdateBy(ShiroUtils.getLoginName());
        }
        smsFlashPromotion.setUpdateTime(DateUtils.getNowDate());
        return smsFlashPromotionMapper.updateSmsFlashPromotion(smsFlashPromotion);
    }

    /**
     * 删除秒杀活动对象
     *
     * @param ids 需要删除的数据ID
     * @return 删除秒杀活动数量
     */
    @Override
    public int deleteSmsFlashPromotionByIds(String ids) {
        return smsFlashPromotionMapper.deleteSmsFlashPromotionByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除秒杀活动信息
     *
     * @param id 秒杀活动ID
     * @return 删除秒杀活动数量
     */
    @Override
    public int deleteSmsFlashPromotionById(Long id) {
        User user = ShiroUtils.getSysUser();
        operateValidate(user.getDeptId(), id, SmsFlashConst.operateBtnEnum.BTN_DELETE);
        return smsFlashPromotionMapper.deleteSmsFlashPromotionById(id);
    }


    /**
     * 校验是否满足操作执行需要的状态
     * @param shopId
     * @param promotionId
     * @param btnEnum
     */
    @Override
    public void operateValidate(Long shopId, Long promotionId, SmsFlashConst.operateBtnEnum btnEnum) {
        SmsFlashPromotion promotion = selectSmsFlashPromotionById(promotionId);
        operateValidate(shopId, promotion, btnEnum);
    }

    /**
     * 校验是否满足操作执行需要的状态
     * @param shopId
     * @param promotion
     * @param btnEnum
     */
    @Override
    public void operateValidate(Long shopId, SmsFlashPromotion promotion, SmsFlashConst.operateBtnEnum btnEnum) {
        log.info("校验是否满足操作执行需要的状态");
        Checker.check(ObjectUtils.isEmpty(promotion), "秒杀活动数据异常");
        if (!ObjectUtils.isEmpty(shopId)) {
            Checker.checkOp(Objects.equals(shopId, promotion.getShopId()), "非本店铺秒杀活动");
        }
        Checker.checkOp(Arrays.asList(btnEnum.getStates()).contains(promotion.getStatus()), "操作异常，请刷新后重试！");
    }

    /**
     * 上架前检测
     */
    private void putawayCheck(Long promotionId, Long shopId) {
        log.info("检测: 每个店铺仅能上架一个秒杀活动");
        SmsFlashPromotion promotion = new SmsFlashPromotion();
        promotion.setShopId(shopId);
        promotion.setStatus(SmsFlashConst.StatusEnum.PUTAWAY.getType());
        Checker.check(countSmsFlashPromotion(promotion) > 0, "存在已开启的秒杀活动");

        log.info("检测：店铺是否设置商品");
        Checker.checkOp(promotionProductCheck(promotionId, null, null),"秒杀活动未设置商品");
    }

    /**
     * 活动上架
     * @param promotionId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int putawayPromotion(Long promotionId) {
        User user = ShiroUtils.getSysUser();
        operateValidate(user.getDeptId(), promotionId, SmsFlashConst.operateBtnEnum.BTN_PUTAWAY);
        putawayCheck(promotionId, user.getDeptId());
        SmsFlashPromotion promotion = new SmsFlashPromotion();
        promotion.setId(promotionId);
        promotion.setStatus(SmsFlashConst.StatusEnum.PUTAWAY.getType());
        return updateSmsFlashPromotion(promotion);
    }

    /**
     * 活动下架
     * @param promotionId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int soldoutPromotion(Long promotionId) {
        operateValidate(null, promotionId, SmsFlashConst.operateBtnEnum.BTN_SOLDOUT);
        SmsFlashPromotion promotion = new SmsFlashPromotion();
        promotion.setId(promotionId);
        promotion.setStatus(SmsFlashConst.StatusEnum.SOLDOUT.getType());
        return updateSmsFlashPromotion(promotion);
    }

    /**
     * 新增秒杀商品处理
     * @param product
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertProductProcess(SmsFlashProductAddDTO product) {
        log.info("添加秒杀商品校验");
        product.validate();

        operateValidate(null, product.getFlashPromotionId(), SmsFlashConst.operateBtnEnum.BTN_PRODUCT_ADD);

        log.info("检验当前秒杀活动时段是否已经添加该商品");
        Checker.check(promotionProductCheck(product.getFlashPromotionId(), product.getFlashPromotionSessionId(), product.getProductId()), "当前秒杀活动时段已经添加该商品");

        // 获取商品信息
        PmsProduct pmsProduct = pmsProductService.selectPmsProductById(product.getProductId());
        Checker.check(ObjectUtils.isEmpty(pmsProduct), "秒杀活动选定的商品数据异常");
        Checker.checkOp(Objects.equals(ProductAttributeCategoryConst.publicStatusEnum.PUTAWAY.getType(), pmsProduct.getPublishStatus()), "秒杀活动选定的商品非上架状态");

        // 筛选有效的库存信息
        List<SmsFlashProductSkuSetDTO> effectSkus = getEffectSkus(product.getSkus());
        Checker.check(ObjectUtils.isEmpty(effectSkus), "至少设置一条有效的（秒杀数量大于0）库存信息");

        // 商品SKU最低价格
        BigDecimal minPrice = getMinPrice(effectSkus);
        log.info("商品SKU最低价格: {}", minPrice);

        // 构建秒杀活动商品
        SmsFlashPromotionProduct promotionProduct = new SmsFlashPromotionProduct(
                product.getFlashPromotionId(),
                product.getFlashPromotionSessionId(),
                product.getProductId(),
                pmsProduct.getProductSn(),
                pmsProduct.getName(),
                pmsProduct.getPic(),
                minPrice,
                product.getSort(),
                CommonUse.Status.ENABLE.getType()
        );
        smsFlashPromotionProductService.insertSmsFlashPromotionProduct(promotionProduct);
        Long promotionProductId = promotionProduct.getId();
        log.info("秒杀活动商品ID: {}", promotionProductId);

        // 批量添加秒杀商品sku
        batchInsertPromotionSkus(promotionProductId, effectSkus);
    }

    /**
     * 编辑秒杀活动商品处理
     * @param product
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateProductProcess(SmsFlashProductEditDTO product) {
        log.info("编辑秒杀商品校验");
        product.validate();

        operateValidate(null, product.getFlashPromotionId(), SmsFlashConst.operateBtnEnum.BTN_PRODUCT_EIDT);

        // 获取秒杀活动商品
        SmsFlashPromotionProduct promotionProduct = smsFlashPromotionProductService.selectSmsFlashPromotionProductById(product.getPromotionProductId());
        Checker.check(ObjectUtils.isEmpty(promotionProduct), "编辑秒杀活动商品数据异常");
        Checker.checkOp(Objects.equals(CommonUse.Status.ENABLE.getType(), promotionProduct.getStatus()), "秒杀活动商品状态发生变更，不可编辑");

        // 筛选有效的库存信息
        List<SmsFlashProductSkuSetDTO> effectSkus = getEffectSkus(product.getSkus());
        Checker.check(ObjectUtils.isEmpty(effectSkus), "至少设置一条有效的（秒杀数量大于0）库存信息");

        // 商品SKU最低价格
        BigDecimal minPrice = getMinPrice(effectSkus);
        log.info("商品SKU最低价格: {}", minPrice);

        Long promotionProductId = promotionProduct.getId();
        log.info("秒杀活动商品ID: {}", promotionProductId);

        // 移除秒杀商品原始的库存SKU信息
        smsFlashPromotionSkuService.deleteSmsFlashPromotionSkuByPromotionPriductId(promotionProductId);
        log.info("清除秒杀活动商品SKU信息");

        // 批量添加秒杀商品sku
        batchInsertPromotionSkus(promotionProductId, effectSkus);
    }

    /**
     * 批量添加秒杀商品sku
     * @param promotionProductId
     * @param effectSkus
     */
    private void batchInsertPromotionSkus(Long promotionProductId, List<SmsFlashProductSkuSetDTO> effectSkus) {
        log.info("批量添加秒杀商品SKU");
        // 秒杀商品库存信息
        List<SmsFlashPromotionSku> promotionSkus = Lists.newArrayList();
        // 构建秒杀商品库存信息
        PmsSkuStock skuStock = null;
        for (SmsFlashProductSkuSetDTO skuDTO : effectSkus) {
            // 查询sku库存信息
            skuStock = pmsSkuStockService.selectPmsSkuStockById(skuDTO.getSkuId());
            Checker.check(ObjectUtils.isEmpty(skuStock), "秒杀活动选定的商品库存数据异常");
            promotionSkus.add(new SmsFlashPromotionSku(
                    promotionProductId,
                    skuDTO.getSkuId(),
                    skuStock.getSpData(),
                    skuStock.getPic(),
                    skuStock.getPrice(),
                    skuDTO.getFlashPromotionPrice(),
                    skuDTO.getFlashPromotionCount(),
                    skuDTO.getFlashPromotionLimit(),
                    skuDTO.getSort()
            ));
        }

        // 批量插入库存信息
        smsFlashPromotionSkuService.batchinsertSmsFlashPromotionSku(promotionSkus);
        log.info("构建秒杀商品库存信息 {} 条记录", promotionSkus.size());
    }

    /**
     * 筛选有效的库存信息
     * @param originalSkus
     * @return
     */
    private List<SmsFlashProductSkuSetDTO> getEffectSkus(List<SmsFlashProductSkuSetDTO> originalSkus) {
        log.info("筛选有效的库存信息: 是否设置秒杀数量");
        return originalSkus.stream()
                .filter(skuSetDTO -> skuSetDTO.getFlashPromotionCount() > 0L)
                .collect(Collectors.toList());
    }

    /**
     * 获取有效库存信息中的商品SKU最低价格
     * @param effectSkus
     * @return
     */
    private BigDecimal getMinPrice(List<SmsFlashProductSkuSetDTO> effectSkus) {
        return effectSkus.stream()
                .map(SmsFlashProductSkuSetDTO::getFlashPromotionPrice)
                .distinct()
                .min((o1, o2) -> o1.compareTo(o2))
                .get();
    }

    /**
     * 商品检查
     * @param flashPromotionId 活动
     * @param flashPromotionSessionId 时间段
     * @param productId 商品
     * @return
     */
    private boolean promotionProductCheck(Long flashPromotionId, Long flashPromotionSessionId, Long productId) {
        SmsFlashPromotionProduct product = new SmsFlashPromotionProduct(flashPromotionId, flashPromotionSessionId, productId);
        return smsFlashPromotionProductService.countSmsFlashPromotionProduct(product) > 0;
    }

    /**
     * 删除秒杀商品处理
     * @param flashPromotionId
     * @param promotionProductId
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteProductProcess(Long flashPromotionId, Long promotionProductId) {
        operateValidate(null, flashPromotionId, SmsFlashConst.operateBtnEnum.BTN_PRODUCT_DELETE);

        smsFlashPromotionSkuService.deleteSmsFlashPromotionSkuByPromotionPriductId(promotionProductId);
        log.info("清除秒杀活动商品SKU信息");
        smsFlashPromotionProductService.deleteSmsFlashPromotionProductById(promotionProductId);
        log.info("清除秒杀活动商品信息");
    }

}
