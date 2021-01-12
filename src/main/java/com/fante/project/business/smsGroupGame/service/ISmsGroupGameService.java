package com.fante.project.business.smsGroupGame.service;

import com.fante.project.api.appView.domain.PmsGroupProductDetailPageInfo;
import com.fante.project.business.pmsProduct.domain.PmsProduct;
import com.fante.project.business.smsGroupGame.domain.SmsGroupGame;
import com.fante.project.business.smsGroupGame.domain.SmsGroupGameBtnsResult;
import com.fante.project.business.smsGroupGame.domain.SmsGroupGameParam;
import com.fante.project.business.smsGroupGameRecord.domain.SmsGroupGameRecord;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 团购商品Service接口
 *
 * @author fante
 * @date 2020-03-30
 */
public interface ISmsGroupGameService {
    /**
     * 查询团购商品
     *
     * @param id 团购商品ID
     * @return 团购商品
     */
    public SmsGroupGame selectSmsGroupGameById(Long id);

    /**
     * 查询团购商品列表
     *
     * @param smsGroupGame 团购商品
     * @return 团购商品集合
     */
    public List<SmsGroupGame> selectSmsGroupGameList(SmsGroupGame smsGroupGame);
    /**
     * 查询团购商品及下级关联sku的列表
     *
     * @param id 团购商品id
     * @return 团购商品集合
     */
    public SmsGroupGameParam getSmsGroupGameDetailbyId(Long id);
    /**
     * 查询团购商品列表
     *
     * @param smsGroupGame 团购商品
     * @return 团购商品集合
     */
    public List<SmsGroupGameBtnsResult> selectSmsGroupGameBtnList(SmsGroupGame smsGroupGame);
    /**
     * 查询团购商品数量
     *
     * @param smsGroupGame 团购商品
     * @return 结果
     */
    public int countSmsGroupGame(SmsGroupGame smsGroupGame);

    /**
     * 唯一校验
     *
     * @param smsGroupGame 团购商品
     * @return 结果
     */
    public String checkSmsGroupGameUnique(SmsGroupGame smsGroupGame);

    /**
     * 新增团购商品
     *
     * @param smsGroupGame 团购商品
     * @return 新增团购商品数量
     */
    @Transactional(rollbackFor = Exception.class)
    public int insertSmsGroupGame(SmsGroupGameParam smsGroupGame);

    /**
     * 修改团购商品
     *
     * @param smsGroupGame 团购商品
     * @return 修改团购商品数量
     */
    @Transactional(rollbackFor = Exception.class)
    public int updateSmsGroupGame(SmsGroupGameParam smsGroupGame);

    /**
     * 批量删除团购商品
     *
     * @param ids 需要删除的数据ID
     * @return 删除团购商品数量
     */
    public int deleteSmsGroupGameByIds(String ids);

    /**
     * 删除团购商品信息
     *
     * @param id 团购商品ID
     * @return 删除团购商品数量
     */
    public int deleteSmsGroupGameById(Long id);

    /**
     * 开始活动
     * @param ids
     * @return
     */
    public int start(String ids);

    /**
     * 结束活动
     * @param id
     * @return
     */
    public int stop(Long id);
    /**
     * 查询团购中的不重复的商品
     * @return
     */
    public List<PmsProduct> selectDistinctProductListForGroupGame();
    /**
     * 查询团购商品及下级关联sku的列表
     * 包含空的sku
     *
     * @param id 团购商品id
     * @return 团购商品集合
     */
    public SmsGroupGameParam getSmsGroupGameDetailofEdit(Long id);

    /**
     * 判断活动是否过期
     * @param groupGameId
     * @return
     */
    public int validateGame(Long groupGameId,Long groupGameSkuId,int quantity);

    /**
     * 团购成功扣减库存 和 增加成团数量
     * @param recordId
     * @return
     */
    public int groupSuccess(Long recordId);

    /**
     * (app)获取可用团购商品
     * @return
     */
    List<SmsGroupGame> getEnableGroupProduct(SmsGroupGame game);
    /**
     * (app)获取团购商品详情
     * @param id
     * @return
     */
    PmsGroupProductDetailPageInfo getGroupProductDetailInfo(Long id);

    /**
     * 通过gameskuid查询skugame
     * @param gameId
     * @return
     */
    SmsGroupGame selectSmsGroupGameBySkuId(Long gameId);
}
