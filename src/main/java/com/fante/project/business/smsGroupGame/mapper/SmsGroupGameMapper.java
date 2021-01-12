package com.fante.project.business.smsGroupGame.mapper;

import com.fante.project.api.appView.domain.PmsGroupProductDetailPageInfo;
import com.fante.project.business.pmsProduct.domain.PmsProduct;
import com.fante.project.business.smsGroupGame.domain.SmsGroupGame;
import com.fante.project.business.smsGroupGame.domain.SmsGroupGameBtnsResult;
import com.fante.project.business.smsGroupGame.domain.SmsGroupGameParam;
import com.fante.project.business.smsGroupGameRecord.domain.SmsGroupGameRecord;
import com.fante.project.mapperBase.SmsGroupGameMapperBase;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 团购商品Mapper扩展接口
 *
 * @author fante
 * @date 2020-03-30
 */

public interface SmsGroupGameMapper extends SmsGroupGameMapperBase {
    /**
     * 查询团购商品列表
     *
     * @param smsGroupGame 团购商品
     * @return 团购商品集合
     */
      List<SmsGroupGameBtnsResult> getSmsGroupGameList(SmsGroupGame smsGroupGame);
    /**
     * 真实的批量删除团购商品
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
      int realDelSmsGroupGameByIds(@Param("ids") String[] ids);
    /**
     * 查询团购中的不重复的商品
     * @return
     */
     List<PmsProduct> selectDistinctProductListForGroupGame(Long shopId);
    /**
     * 查询团购商品及下级关联sku的列表
     *
     * @param id 团购商品id
     * @return 团购商品集合
     */
     SmsGroupGameParam getSmsGroupGameDetailbyId(Long id);
    /**
     * 查询团购商品及下级关联sku的列表
     * 包含空的sku
     *
     * @param id 团购商品id
     * @return 团购商品集合
     */
     SmsGroupGameParam getSmsGroupGameDetailofEdit(Long id);
    /**
     * 开始活动
     * @param ids
     * @return
     */
     int startGroupGames(@Param("ids") String[] ids);

    /**
     * 根据ids查询所有活动
     * status = 未开始 或 已结束
     * @param ids
     * @return
     */
     List<SmsGroupGame>  selectSmsGroupGameByIds(@Param("ids") String[] ids);

    /**
     * 查询符合开始活动条件活动数量
     * @param ids
     * @return
     */
     int countSmsGroupGameByIdsOfwaitStart(@Param("ids") String[] ids);
    /**
     * 判断活动是否过期 并 更新库存
     * @param groupGameId
     * @return
     */
     int validateGame(@Param("groupGameId")Long groupGameId,
                      @Param("groupGameSkuId")Long groupGameSkuId,
                      @Param("quantity")int quantity);
    /**
     * 增加成团数量
     * @param recordId 团购记录表id
     * @return
     */
     int groupSuccess(Long recordId);
    /**
     * (app)获取可用团购商品
     * @return
     */
    List<SmsGroupGame> getEnableGroupProduct(SmsGroupGame groupGame);
    /**
     * (app)获取团购商品详情
     * @param id
     * @return
     */
    PmsGroupProductDetailPageInfo getGroupProductDetailInfo(@Param("id")Long id,
                                                            @Param("groupGameStatus")String groupGameStatus,
                                                            @Param("publishStatus")String publishStatus);
    /**
     * 通过gameskuid查询skugame
     * @param gameId
     * @return
     */
    SmsGroupGame selectSmsGroupGameBySkuId(Long gameId);
}
