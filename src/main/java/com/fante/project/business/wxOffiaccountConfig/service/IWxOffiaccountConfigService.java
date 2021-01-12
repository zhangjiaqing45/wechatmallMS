package com.fante.project.business.wxOffiaccountConfig.service;

import com.fante.project.business.wxOffiaccountConfig.domain.WxOffiaccountConfig;

/**
 * 微信公众号配置Service接口
 *
 * @author fante
 * @date 2020-04-09
 */
public interface IWxOffiaccountConfigService {


    /**
     * 获取最新的配置
     * @return
     */
    public WxOffiaccountConfig selectWxOffiaccountConfigRecent();

    /**
     * 新增微信公众号配置
     *
     * @param wxOffiaccountConfig 微信公众号配置
     * @return 新增微信公众号配置数量
     */
    public int insertWxOffiaccountConfig(WxOffiaccountConfig wxOffiaccountConfig);


    ///**
    // * 查询微信公众号配置
    // *
    // * @param id 微信公众号配置ID
    // * @return 微信公众号配置
    // */
    //public WxOffiaccountConfig selectWxOffiaccountConfigById(Long id);
    //
    ///**
    // * 查询微信公众号配置列表
    // *
    // * @param wxOffiaccountConfig 微信公众号配置
    // * @return 微信公众号配置集合
    // */
    //public List<WxOffiaccountConfig> selectWxOffiaccountConfigList(WxOffiaccountConfig wxOffiaccountConfig);
    //
    ///**
    // * 查询微信公众号配置数量
    // *
    // * @param wxOffiaccountConfig 微信公众号配置
    // * @return 结果
    // */
    //public int countWxOffiaccountConfig(WxOffiaccountConfig wxOffiaccountConfig);
    //
    ///**
    // * 唯一校验
    // *
    // * @param wxOffiaccountConfig 微信公众号配置
    // * @return 结果
    // */
    //public String checkWxOffiaccountConfigUnique(WxOffiaccountConfig wxOffiaccountConfig);
    //
    ///**
    // * 修改微信公众号配置
    // *
    // * @param wxOffiaccountConfig 微信公众号配置
    // * @return 修改微信公众号配置数量
    // */
    //public int updateWxOffiaccountConfig(WxOffiaccountConfig wxOffiaccountConfig);
    //
    ///**
    // * 批量删除微信公众号配置
    // *
    // * @param ids 需要删除的数据ID
    // * @return 删除微信公众号配置数量
    // */
    //public int deleteWxOffiaccountConfigByIds(String ids);
    //
    ///**
    // * 删除微信公众号配置信息
    // *
    // * @param id 微信公众号配置ID
    // * @return 删除微信公众号配置数量
    // */
    //public int deleteWxOffiaccountConfigById(Long id);
    }
