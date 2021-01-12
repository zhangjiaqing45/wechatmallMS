package com.fante.project.mapperBase;

import com.fante.project.business.smsGroupGameSku.domain.SmsGroupGameSku;
import java.util.List;

/**
 * 团购商品skuMapper基础接口
 *
 * @author fante
 * @date 2020-04-08
 */
public interface SmsGroupGameSkuMapperBase {
    /**
     * 查询团购商品sku
     *
     * @param id 团购商品skuID
     * @return 团购商品sku
     */
    public SmsGroupGameSku selectSmsGroupGameSkuById(Long id);

    /**
     * 查询团购商品sku列表
     *
     * @param smsGroupGameSku 团购商品sku
     * @return 团购商品sku集合
     */
    public List<SmsGroupGameSku> selectSmsGroupGameSkuList(SmsGroupGameSku smsGroupGameSku);

    /**
     * 查询团购商品sku数量
     *
     * @param smsGroupGameSku 团购商品sku
     * @return 结果
     */
    public int countSmsGroupGameSku(SmsGroupGameSku smsGroupGameSku);

    /**
     * 唯一校验
     *
     * @param smsGroupGameSku 团购商品sku
     * @return 结果
     */
    public int checkSmsGroupGameSkuUnique(SmsGroupGameSku smsGroupGameSku);

    /**
     * 新增团购商品sku
     *
     * @param smsGroupGameSku 团购商品sku
     * @return 结果
     */
    public int insertSmsGroupGameSku(SmsGroupGameSku smsGroupGameSku);

    /**
     * 修改团购商品sku
     *
     * @param smsGroupGameSku 团购商品sku
     * @return 结果
     */
    public int updateSmsGroupGameSku(SmsGroupGameSku smsGroupGameSku);

    /**
     * 删除团购商品sku
     *
     * @param id 团购商品skuID
     * @return 结果
     */
    public int deleteSmsGroupGameSkuById(Long id);

    /**
     * 批量删除团购商品sku
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSmsGroupGameSkuByIds(String[] ids);

}
