package com.fante.project.business.wxSettings.service;

import com.fante.project.business.wxSettings.domain.WxSettings;

/**
 * 微信公众号设置Service接口
 *
 * @author fante
 * @date 2020-02-21
 */
public interface IWxSettingsService {
    /**
     * 查询微信公众号设置
     *
     * @param id 微信公众号设置ID
     * @return 微信公众号设置
     */
    //public WxSettings selectWxSettingsById(Long id);

    /**
     * 查询微信公众号设置列表
     *
     * @param wxSettings 微信公众号设置
     * @return 微信公众号设置集合
     */
    //public List<WxSettings> selectWxSettingsList(WxSettings wxSettings);


    /**
     * 查询最新一条设置
     * @return
     */
    public WxSettings selectWxSettingsRecent();

    /**
     * 新增微信公众号设置
     *
     * @param wxSettings 微信公众号设置
     * @return 结果
     */
    public int insertWxSettings(WxSettings wxSettings);

    /**
     * 修改微信公众号设置
     *
     * @param wxSettings 微信公众号设置
     * @return 结果
     */
    public int updateWxSettings(WxSettings wxSettings);

    /**
     * 批量删除微信公众号设置
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    //public int deleteWxSettingsByIds(String ids);

    /**
     * 删除微信公众号设置信息
     *
     * @param id 微信公众号设置ID
     * @return 结果
     */
    //public int deleteWxSettingsById(Long id);
    }
