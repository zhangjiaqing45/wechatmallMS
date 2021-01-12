package com.fante.project.business.smsGroupGameSku.service.impl;

import java.util.List;
import com.fante.common.utils.DateUtils;
import com.fante.common.constant.Constants;
import com.fante.project.api.omsOrderProcess.domain.CartSkuDto;
import com.fante.project.business.smsGroupMemberRecord.domain.SmsGroupMemberRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fante.project.business.smsGroupGameSku.mapper.SmsGroupGameSkuMapper;
import com.fante.project.business.smsGroupGameSku.domain.SmsGroupGameSku;
import com.fante.project.business.smsGroupGameSku.service.ISmsGroupGameSkuService;
import com.fante.common.utils.text.Convert;
import org.springframework.util.ObjectUtils;

/**
 * 团购商品skuService业务层处理
 *
 * @author fante
 * @date 2020-03-30
 */
@Service
public class SmsGroupGameSkuServiceImpl implements ISmsGroupGameSkuService {

    private static Logger log = LoggerFactory.getLogger(SmsGroupGameSkuServiceImpl.class);

    @Autowired
    private SmsGroupGameSkuMapper smsGroupGameSkuMapper;

    /**
     * 查询团购商品sku
     *
     * @param id 团购商品skuID
     * @return 团购商品sku
     */
    @Override
    public SmsGroupGameSku selectSmsGroupGameSkuById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        return smsGroupGameSkuMapper.selectSmsGroupGameSkuById(id);
    }

    /**
     * 查询团购商品sku列表
     *
     * @param smsGroupGameSku 团购商品sku
     * @return 团购商品sku集合
     */
    @Override
    public List<SmsGroupGameSku> selectSmsGroupGameSkuList(SmsGroupGameSku smsGroupGameSku) {
        return smsGroupGameSkuMapper.selectSmsGroupGameSkuList(smsGroupGameSku);
    }

    /**
     * 查询团购商品sku数量
     *
     * @param smsGroupGameSku 团购商品sku
     * @return 结果
     */
    @Override
    public int countSmsGroupGameSku(SmsGroupGameSku smsGroupGameSku){
        return smsGroupGameSkuMapper.countSmsGroupGameSku(smsGroupGameSku);
    }

    /**
     * 唯一校验
     *
     * @param smsGroupGameSku 团购商品sku
     * @return 结果
     */
    @Override
    public String checkSmsGroupGameSkuUnique(SmsGroupGameSku smsGroupGameSku) {
        return smsGroupGameSkuMapper.checkSmsGroupGameSkuUnique(smsGroupGameSku) > 0 ? Constants.NOT_UNIQUE : Constants.UNIQUE;
    }

    /**
     * 新增团购商品sku
     *
     * @param smsGroupGameSku 团购商品sku
     * @return 新增团购商品sku数量
     */
    @Override
    public int insertSmsGroupGameSku(SmsGroupGameSku smsGroupGameSku) {
        smsGroupGameSku.setCreateTime(DateUtils.getNowDate());
        return smsGroupGameSkuMapper.insertSmsGroupGameSku(smsGroupGameSku);
    }

    /**
     * 修改团购商品sku
     *
     * @param smsGroupGameSku 团购商品sku
     * @return 修改团购商品sku数量
     */
    @Override
    public int updateSmsGroupGameSku(SmsGroupGameSku smsGroupGameSku) {
        smsGroupGameSku.setUpdateTime(DateUtils.getNowDate());
        return smsGroupGameSkuMapper.updateSmsGroupGameSku(smsGroupGameSku);
    }

    /**
     * 删除团购商品sku对象
     *
     * @param ids 需要删除的数据ID
     * @return 删除团购商品sku数量
     */
    @Override
    public int deleteSmsGroupGameSkuByIds(String ids) {
        return smsGroupGameSkuMapper.deleteSmsGroupGameSkuByIds(Convert.toStrArray(ids));
    }
    /**
     * 删除团购商品sku对象
     *
     * @param ids 需要删除的数据ID
     * @return 删除团购商品sku数量
     */
    @Override
    public int deleteSmsGroupGameSkuByIds(String[] ids) {
        return smsGroupGameSkuMapper.deleteSmsGroupGameSkuByIds(ids);
    }

    /**
     * 删除团购商品sku信息
     *
     * @param id 团购商品skuID
     * @return 删除团购商品sku数量
     */
    @Override
    public int deleteSmsGroupGameSkuById(Long id) {
        return smsGroupGameSkuMapper.deleteSmsGroupGameSkuById(id);
    }

    /**
     * 批量插入团购商品sku信息
     *
     * @param skus
     * @return 删除团购商品sku数量
     */
    @Override
    public int batchInsertGroupGameSku(List<SmsGroupGameSku> skus) {
        return smsGroupGameSkuMapper.batchInsertGroupGameSku(skus);
    }
    /**
     * 批量更新团购商品sku信息
     *
     * @param skus
     * @return 批量更新团购商品sku信息数量
     */
    @Override
    public int batchUpdateGameSku(List<SmsGroupGameSku> skus){
        return smsGroupGameSkuMapper.batchUpdateGameSku(skus);
    }
    /**
     * 批量删除团购商品sku信息
     *
     * @param ids 活动ids
     * @return 批量删除团购商品sku信息数量
     */
    @Override
    public int realDelSmsGroupGameSkuByGGid(String[] ids) {
        return smsGroupGameSkuMapper.realDelSmsGroupGameSkuByGGid(ids);
    }
    /**
     * 假批量删除团购商品sku信息
     *
     * @param ids 活动ids
     * @return 批量删除团购商品sku信息数量
     */
    @Override
    public int deleteSmsGroupGameSkuByGGid(String[] ids) {
        return smsGroupGameSkuMapper.deleteSmsGroupGameSkuByGGid(ids);
    }
    /**
     * 根据skuID查询CartSku需要的数据
     * @param productSkuId
     * @return
     */
    @Override
    public CartSkuDto getCartSkuDtoBySkuId(Long productSkuId) {
        return smsGroupGameSkuMapper.getCartSkuDtoBySkuId(productSkuId);
    }

    /**
     * 验证库存是否充足
     * @param quantity
     * @param promotionId
     * @return
     */
    @Override
    public int validateStock( Long quantity, Long promotionId) {
        return smsGroupGameSkuMapper.validateStock( quantity,promotionId);
    }
    /**
     * 扣减库存
     * @param records
     * @return
     */
    @Override
    public int subStockOfgroupSuccess(List<SmsGroupMemberRecord> records) {
        return smsGroupGameSkuMapper.subStockOfgroupSuccess(records);
    }

    /**
     * 1.团购中所有sku扣减库存
     * 2.团购记录中:团购状态改为成功
     * 3.更改团中的所有成员的订单为待发货
     * 4.团购活动的成团数量 + 1
     * @param recordId
     * @return
     */
    @Override
    public void notifyGroupSuccess(Long recordId) {
        smsGroupGameSkuMapper.notifyGroupSuccess(recordId);
    }
}
