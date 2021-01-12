package com.fante.project.mapperBase;

import com.fante.project.business.wxOffiaccountConfig.domain.WxOffiaccountConfig;
import java.util.List;

/**
 * 微信公众号配置Mapper基础接口
 *
 * @author fante
 * @date 2020-04-09
 */
public interface WxOffiaccountConfigMapperBase {
    /**
     * 查询微信公众号配置
     *
     * @param id 微信公众号配置ID
     * @return 微信公众号配置
     */
    public WxOffiaccountConfig selectWxOffiaccountConfigById(Long id);

    /**
     * 查询微信公众号配置列表
     *
     * @param wxOffiaccountConfig 微信公众号配置
     * @return 微信公众号配置集合
     */
    public List<WxOffiaccountConfig> selectWxOffiaccountConfigList(WxOffiaccountConfig wxOffiaccountConfig);

    /**
     * 查询微信公众号配置数量
     *
     * @param wxOffiaccountConfig 微信公众号配置
     * @return 结果
     */
    public int countWxOffiaccountConfig(WxOffiaccountConfig wxOffiaccountConfig);

    /**
     * 唯一校验
     *
     * @param wxOffiaccountConfig 微信公众号配置
     * @return 结果
     */
    public int checkWxOffiaccountConfigUnique(WxOffiaccountConfig wxOffiaccountConfig);

    /**
     * 新增微信公众号配置
     *
     * @param wxOffiaccountConfig 微信公众号配置
     * @return 结果
     */
    public int insertWxOffiaccountConfig(WxOffiaccountConfig wxOffiaccountConfig);

    /**
     * 修改微信公众号配置
     *
     * @param wxOffiaccountConfig 微信公众号配置
     * @return 结果
     */
    public int updateWxOffiaccountConfig(WxOffiaccountConfig wxOffiaccountConfig);

    /**
     * 删除微信公众号配置
     *
     * @param id 微信公众号配置ID
     * @return 结果
     */
    public int deleteWxOffiaccountConfigById(Long id);

    /**
     * 批量删除微信公众号配置
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWxOffiaccountConfigByIds(String[] ids);

}
