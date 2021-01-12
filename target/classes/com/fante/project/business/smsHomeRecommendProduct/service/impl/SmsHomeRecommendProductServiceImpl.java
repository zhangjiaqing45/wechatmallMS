package com.fante.project.business.smsHomeRecommendProduct.service.impl;

import com.fante.common.business.constant.BizConstants;
import com.fante.common.business.enums.CommonUse;
import com.fante.common.business.enums.ProductAttributeCategoryConst;
import com.fante.common.business.enums.SmsRecommendConst;
import com.fante.common.constant.Constants;
import com.fante.common.utils.Checker;
import com.fante.common.utils.DateUtils;
import com.fante.common.utils.StringUtils;
import com.fante.common.utils.security.ShiroUtils;
import com.fante.common.utils.text.Convert;
import com.fante.project.api.appView.domain.CmsRecommendReq;
import com.fante.project.business.smsHomeRecommendProduct.domain.SmsHomeRecommendProduct;
import com.fante.project.business.smsHomeRecommendProduct.domain.SmsHomeRecommendProductDTO;
import com.fante.project.business.smsHomeRecommendProduct.domain.SmsHomeRecommendSelectDTO;
import com.fante.project.business.smsHomeRecommendProduct.mapper.SmsHomeRecommendProductMapper;
import com.fante.project.business.smsHomeRecommendProduct.service.ISmsHomeRecommendProductService;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;

/**
 * 推荐商品Service业务层处理
 *
 * @author fante
 * @date 2020-03-10
 */
@Service
public class SmsHomeRecommendProductServiceImpl implements ISmsHomeRecommendProductService {

    private static Logger log = LoggerFactory.getLogger(SmsHomeRecommendProductServiceImpl.class);

    @Autowired
    private SmsHomeRecommendProductMapper smsHomeRecommendProductMapper;

    /**
     * 查询推荐商品
     *
     * @param id 推荐商品ID
     * @return 推荐商品
     */
    @Override
    public SmsHomeRecommendProduct selectSmsHomeRecommendProductById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        return smsHomeRecommendProductMapper.selectSmsHomeRecommendProductById(id);
    }

    /**
     * 查询推荐商品列表
     *
     * @param smsHomeRecommendProduct 推荐商品
     * @return 推荐商品集合
     */
    @Override
    public List<SmsHomeRecommendProduct> selectSmsHomeRecommendProductList(SmsHomeRecommendProduct smsHomeRecommendProduct) {
        return smsHomeRecommendProductMapper.selectSmsHomeRecommendProductList(smsHomeRecommendProduct);
    }

    /**
     * 推荐商品信息
     * @param productDTO
     * @return
     */
    @Override
    public List<SmsHomeRecommendProductDTO> selectJoinList(SmsHomeRecommendProductDTO productDTO) {
        return smsHomeRecommendProductMapper.selectJoinList(productDTO);
    }

    /**
     * 新增推荐商品
     *
     * @param smsHomeRecommendProduct 推荐商品
     * @return 新增推荐商品数量
     */
    @Override
    public int insertSmsHomeRecommendProduct(SmsHomeRecommendProduct smsHomeRecommendProduct) {
        if (StringUtils.isBlank(smsHomeRecommendProduct.getCreateBy())) {
            smsHomeRecommendProduct.setCreateBy(ShiroUtils.getLoginName());
        }
        smsHomeRecommendProduct.setCreateTime(DateUtils.getNowDate());
        return smsHomeRecommendProductMapper.insertSmsHomeRecommendProduct(smsHomeRecommendProduct);
    }

    /**
     * 批量新增--指定推荐类型添加多个商品
     * @param productIds 推荐商品
     * @return 新增推荐商品数量
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchInsertSmsHomeRecommendProducts(String productIds, String type, String createBy) {
        Checker.check(StringUtils.isBlank(productIds), "缺少商品选择信息");
        Checker.check(StringUtils.isBlank(type), "缺少推荐类型");
        Long[] ids = Convert.toLongArray(productIds);
        Checker.check(ObjectUtils.isEmpty(ids), "未选择商品");
        Checker.check(checkProductUnique(ids, type) > 0, "所选择商品信息发生变动，请刷新后重新选择商品");

        String status = Constants.ENABLE;
        Date createTime = DateUtils.getNowDate();
        if (StringUtils.isBlank(createBy)) {
            createBy = ShiroUtils.getLoginName();
        }
        List<SmsHomeRecommendProduct> batchData = Lists.newArrayList();
        for (Long id : ids) {
            batchData.add(new SmsHomeRecommendProduct(id, status, type, createBy, createTime));
        }
        return smsHomeRecommendProductMapper.batchInsertSmsHomeRecommendProduct(batchData);
    }

    /**
     * 批量新增--单个商品添加多种推荐类型
     * @param productId
     * @param types
     * @param createBy
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchInsertSmsHomeRecommendProductByTypes(Long productId, String types, String createBy) {
        Checker.check(ObjectUtils.isEmpty(productId), "缺少商品信息");
        // 清除该商品已有的推荐信息
        deleteSmsHomeRecommendProductByProductId(productId);
        if (StringUtils.isBlank(types) || ObjectUtils.isEmpty(Convert.toStrArray(types))) {
            return;
        }
        String[] typeArr = Convert.toStrArray(types);
        String status = Constants.ENABLE;
        Date createTime = DateUtils.getNowDate();
        if (StringUtils.isBlank(createBy)) {
            createBy = ShiroUtils.getLoginName();
        }
        List<SmsHomeRecommendProduct> batchData = Lists.newArrayList();
        for (String t : typeArr) {
            SmsRecommendConst.ProductType typeEnum = SmsRecommendConst.ProductType.get(t);
            Checker.check(ObjectUtils.isEmpty(typeEnum), "推荐类型异常");
            batchData.add(new SmsHomeRecommendProduct(productId, status, t, createBy, createTime));
        }
        smsHomeRecommendProductMapper.batchInsertSmsHomeRecommendProduct(batchData);
    }

    /**
     * 根据商品信息删除推荐信息
     * @param productId
     * @return
     */
    @Override
    public int deleteSmsHomeRecommendProductByProductId(Long productId) {
        return smsHomeRecommendProductMapper.deleteSmsHomeRecommendProductByProductId(productId);
    }

    /**
     * 检查当前类型中是否有重复商品信息
     * @param ids
     * @param type
     * @return
     */
    @Override
    public int checkProductUnique(Long[] ids, String type) {
        return smsHomeRecommendProductMapper.checkProductUnique(ids, type);
    }

    /**
     * 修改推荐商品
     *
     * @param smsHomeRecommendProduct 推荐商品
     * @return 修改推荐商品数量
     */
    @Override
    public int updateSmsHomeRecommendProduct(SmsHomeRecommendProduct smsHomeRecommendProduct) {
        if (StringUtils.isBlank(smsHomeRecommendProduct.getUpdateBy())) {
            smsHomeRecommendProduct.setUpdateBy(ShiroUtils.getLoginName());
        }
        smsHomeRecommendProduct.setUpdateTime(DateUtils.getNowDate());
        return smsHomeRecommendProductMapper.updateSmsHomeRecommendProduct(smsHomeRecommendProduct);
    }

    /**
     * 删除推荐商品对象
     *
     * @param ids 需要删除的数据ID
     * @return 删除推荐商品数量
     */
    @Override
    public int deleteSmsHomeRecommendProductByIds(String ids) {
        return smsHomeRecommendProductMapper.deleteSmsHomeRecommendProductByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除推荐商品信息
     *
     * @param id 推荐商品ID
     * @return 删除推荐商品数量
     */
    @Override
    public int deleteSmsHomeRecommendProductById(Long id) {
        return smsHomeRecommendProductMapper.deleteSmsHomeRecommendProductById(id);
    }

    /**
     * 推荐设置
     * @param id
     * @param status
     * @return
     */
    @Override
    public int recommendSetting(Long id, String status) {
        Checker.check(ObjectUtils.isEmpty(id), "缺少推荐标识");
        Checker.check(StringUtils.isBlank(status), "缺少状态标识");
        SmsHomeRecommendProduct product = new SmsHomeRecommendProduct();
        product.setId(id);
        product.setStatus(status);
        return updateSmsHomeRecommendProduct(product);
    }

    /**
     * 设置排序
     * @param id
     * @param sort
     * @return
     */
    @Override
    public int changeSort(Long id, Long sort) {
        Checker.check(ObjectUtils.isEmpty(id), "缺少推荐标识");
        Checker.check(ObjectUtils.isEmpty(sort), "缺少排序");
        SmsHomeRecommendProduct product = new SmsHomeRecommendProduct();
        product.setId(id);
        product.setSort(sort);
        return updateSmsHomeRecommendProduct(product);
    }

    /**
     * 选择商品
     * @param selectDTO
     * @return
     */
    @Override
    public List<SmsHomeRecommendSelectDTO> recommendSelect(SmsHomeRecommendSelectDTO selectDTO) {
        Checker.check(StringUtils.isBlank(selectDTO.getRecommendType()), "缺少推荐类型");
        return smsHomeRecommendProductMapper.recommendSelect(selectDTO);
    }

    /**
     * 按照推荐类型分类查询数据
     * @param type
     * @param showNum
     * @return
     */
    @Override
    public List<SmsHomeRecommendProductDTO> selectRecommendProductWithType(String type, int showNum) {
        CmsRecommendReq req = new CmsRecommendReq();
        req.setType(type);
        req.setStatus(CommonUse.Status.ENABLE.getType());
        req.setPublishStatus(ProductAttributeCategoryConst.publicStatusEnum.PUTAWAY.getType());
        req.setShowNum(showNum);
        return smsHomeRecommendProductMapper.selectRecommendProductWithType(req);
    }

    /**
     * 推荐商品信息(前端显示)
     * @param req
     * @return
     */
    @Override
    public List<SmsHomeRecommendProductDTO> selectAppJoinList(CmsRecommendReq req) {
        req.setStatus(CommonUse.Status.ENABLE.getType());
        req.setPublishStatus(ProductAttributeCategoryConst.publicStatusEnum.PUTAWAY.getType());
        return smsHomeRecommendProductMapper.selectAppJoinList(req);
    }
}
