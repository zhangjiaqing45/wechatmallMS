package com.fante.project.mapperBase;

import com.fante.project.business.smsGroupGame.domain.SmsGroupGame;
import java.util.List;

/**
 * 团购商品Mapper基础接口
 *
 * @author fante
 * @date 2020-04-08
 */
public interface SmsGroupGameMapperBase {
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
    public int checkSmsGroupGameUnique(SmsGroupGame smsGroupGame);

    /**
     * 新增团购商品
     *
     * @param smsGroupGame 团购商品
     * @return 结果
     */
    public int insertSmsGroupGame(SmsGroupGame smsGroupGame);

    /**
     * 修改团购商品
     *
     * @param smsGroupGame 团购商品
     * @return 结果
     */
    public int updateSmsGroupGame(SmsGroupGame smsGroupGame);

    /**
     * 删除团购商品
     *
     * @param id 团购商品ID
     * @return 结果
     */
    public int deleteSmsGroupGameById(Long id);

    /**
     * 批量删除团购商品
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSmsGroupGameByIds(String[] ids);

}
