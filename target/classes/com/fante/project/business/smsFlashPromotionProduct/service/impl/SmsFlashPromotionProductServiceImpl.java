package com.fante.project.business.smsFlashPromotionProduct.service.impl;

import com.fante.common.business.enums.CommonUse;
import com.fante.common.business.enums.ProductAttributeCategoryConst;
import com.fante.common.business.enums.SmsFlashConst;
import com.fante.common.constant.Constants;
import com.fante.common.utils.Checker;
import com.fante.common.utils.text.Convert;
import com.fante.project.api.appView.domain.FlashProductParam;
import com.fante.project.api.appView.domain.PmsFlashProductDetailPageInfo;
import com.fante.project.business.smsFlashPromotionProduct.domain.SmsFlashPromotionProduct;
import com.fante.project.business.smsFlashPromotionProduct.domain.SmsFlashPromotionProductDTO;
import com.fante.project.business.smsFlashPromotionProduct.domain.SmsFlashPromotionProductSelectDTO;
import com.fante.project.business.smsFlashPromotionProduct.mapper.SmsFlashPromotionProductMapper;
import com.fante.project.business.smsFlashPromotionProduct.service.ISmsFlashPromotionProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.support.SessionStatus;

import javax.naming.CommunicationException;
import java.util.List;
import java.util.Objects;

/**
 * 秒杀活动产品Service业务层处理
 *
 * @author fante
 * @date 2020-03-23
 */
@Service
public class SmsFlashPromotionProductServiceImpl implements ISmsFlashPromotionProductService {

    private static Logger log = LoggerFactory.getLogger(SmsFlashPromotionProductServiceImpl.class);

    @Autowired
    private SmsFlashPromotionProductMapper smsFlashPromotionProductMapper;

    /**
     * 查询秒杀活动产品
     *
     * @param id 秒杀活动产品ID
     * @return 秒杀活动产品
     */
    @Override
    public SmsFlashPromotionProduct selectSmsFlashPromotionProductById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        return smsFlashPromotionProductMapper.selectSmsFlashPromotionProductById(id);
    }

    /**
     * 查询秒杀活动产品列表
     *
     * @param smsFlashPromotionProduct 秒杀活动产品
     * @return 秒杀活动产品集合
     */
    @Override
    public List<SmsFlashPromotionProduct> selectSmsFlashPromotionProductList(SmsFlashPromotionProduct smsFlashPromotionProduct) {
        return smsFlashPromotionProductMapper.selectSmsFlashPromotionProductList(smsFlashPromotionProduct);
    }

    /**
     * 查询秒杀活动产品数量
     *
     * @param smsFlashPromotionProduct 秒杀活动产品
     * @return 结果
     */
    @Override
    public int countSmsFlashPromotionProduct(SmsFlashPromotionProduct smsFlashPromotionProduct){
        return smsFlashPromotionProductMapper.countSmsFlashPromotionProduct(smsFlashPromotionProduct);
    }

    /**
     * 唯一校验
     *
     * @param smsFlashPromotionProduct 秒杀活动产品
     * @return 结果
     */
    @Override
    public String checkSmsFlashPromotionProductUnique(SmsFlashPromotionProduct smsFlashPromotionProduct) {
        return smsFlashPromotionProductMapper.checkSmsFlashPromotionProductUnique(smsFlashPromotionProduct) > 0 ? Constants.NOT_UNIQUE : Constants.UNIQUE;
    }

    /**
     * 新增秒杀活动产品
     *
     * @param smsFlashPromotionProduct 秒杀活动产品
     * @return 新增秒杀活动产品数量
     */
    @Override
    public int insertSmsFlashPromotionProduct(SmsFlashPromotionProduct smsFlashPromotionProduct) {
        return smsFlashPromotionProductMapper.insertSmsFlashPromotionProduct(smsFlashPromotionProduct);
    }

    /**
     * 修改秒杀活动产品
     *
     * @param smsFlashPromotionProduct 秒杀活动产品
     * @return 修改秒杀活动产品数量
     */
    @Override
    public int updateSmsFlashPromotionProduct(SmsFlashPromotionProduct smsFlashPromotionProduct) {
        return smsFlashPromotionProductMapper.updateSmsFlashPromotionProduct(smsFlashPromotionProduct);
    }

    /**
     * 删除秒杀活动产品对象
     *
     * @param ids 需要删除的数据ID
     * @return 删除秒杀活动产品数量
     */
    @Override
    public int deleteSmsFlashPromotionProductByIds(String ids) {
        return smsFlashPromotionProductMapper.deleteSmsFlashPromotionProductByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除秒杀活动产品信息
     *
     * @param id 秒杀活动产品ID
     * @return 删除秒杀活动产品数量
     */
    @Override
    public int deleteSmsFlashPromotionProductById(Long id) {
        return smsFlashPromotionProductMapper.deleteSmsFlashPromotionProductById(id);
    }


    /**
     * 秒杀活动设置商品查询
     * @param productDTO
     * @return
     */
    @Override
    public List<SmsFlashPromotionProductDTO> selectJoinList(SmsFlashPromotionProductDTO productDTO) {
        return smsFlashPromotionProductMapper.selectJoinList(productDTO);
    }

    /**
     * 秒杀活动选择商品查询
     * @param selectDTO
     * @return
     */
    @Override
    public List<SmsFlashPromotionProductSelectDTO> selectProductSelectList(SmsFlashPromotionProductSelectDTO selectDTO) {
        return smsFlashPromotionProductMapper.selectProductSelectList(selectDTO);
    }

    /**
     * 设置排序
     * @param id
     * @param sort
     * @return
     */
    @Override
    public int changeSort(Long id, Integer sort) {
        SmsFlashPromotionProduct product = new SmsFlashPromotionProduct();
        product.setId(id);
        product.setSort(sort);
        return updateSmsFlashPromotionProduct(product);
    }


    /**
     * 设置商品参与状态
     * @param id
     * @param status
     * @return
     */
    @Override
    public int changeStatus(Long id, String status) {
        SmsFlashPromotionProduct product = new SmsFlashPromotionProduct();
        product.setId(id);
        product.setStatus(status);
        return updateSmsFlashPromotionProduct(product);
    }
    /**
     * (app)根据秒杀时间段获取秒杀商品
     */
    @Override
    public List<SmsFlashPromotionProductDTO> getFlashProductList(FlashProductParam param){
        //验证时间段id
        Checker.check(ObjectUtils.isEmpty(param.getFlashPromotionSessionId()), "缺少时间段动标识");
        //商品状态：上架
        param.setPublishStatus( ProductAttributeCategoryConst.publicStatusEnum.PUTAWAY.getType() );
        //时间段状态： 可用
        param.setSessionStatus( Constants.ENABLE);
        //活动状态 ： 进行中
        param.setPromotionStatus( SmsFlashConst.StatusEnum.PUTAWAY.getType() );
        return smsFlashPromotionProductMapper.getFlashProductList( param );
    }
    /**
     * (app)获取秒杀商品详情
     * @param productId
     * @return
     */
    @Override
    public PmsFlashProductDetailPageInfo getFlashProductDetailInfo(Long productId) {
        return smsFlashPromotionProductMapper.getFlashProductDetailInfo(productId);
    }
}
