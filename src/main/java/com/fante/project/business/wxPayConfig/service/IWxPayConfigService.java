package com.fante.project.business.wxPayConfig.service;

import com.fante.project.business.wxPayConfig.domain.WxPayConfig;

/**
 * 微信支付配置Service接口
 *
 * @author fante
 * @date 2020-04-09
 */
public interface IWxPayConfigService {


    /**
     * 获取最新的配置
     * @return
     */
    public WxPayConfig selectWxPayConfigRecent();

    /**
     * 新增微信支付配置
     *
     * @param wxPayConfig 微信支付配置
     * @return 新增微信支付配置数量
     */
    public int insertWxPayConfig(WxPayConfig wxPayConfig);


    ///**
    // * 查询微信支付配置
    // *
    // * @param id 微信支付配置ID
    // * @return 微信支付配置
    // */
    //public WxPayConfig selectWxPayConfigById(Long id);
    //
    ///**
    // * 查询微信支付配置列表
    // *
    // * @param wxPayConfig 微信支付配置
    // * @return 微信支付配置集合
    // */
    //public List<WxPayConfig> selectWxPayConfigList(WxPayConfig wxPayConfig);
    //
    ///**
    // * 查询微信支付配置数量
    // *
    // * @param wxPayConfig 微信支付配置
    // * @return 结果
    // */
    //public int countWxPayConfig(WxPayConfig wxPayConfig);
    //
    ///**
    // * 唯一校验
    // *
    // * @param wxPayConfig 微信支付配置
    // * @return 结果
    // */
    //public String checkWxPayConfigUnique(WxPayConfig wxPayConfig);
    //
    ///**
    // * 修改微信支付配置
    // *
    // * @param wxPayConfig 微信支付配置
    // * @return 修改微信支付配置数量
    // */
    //public int updateWxPayConfig(WxPayConfig wxPayConfig);
    //
    ///**
    // * 批量删除微信支付配置
    // *
    // * @param ids 需要删除的数据ID
    // * @return 删除微信支付配置数量
    // */
    //public int deleteWxPayConfigByIds(String ids);
    //
    ///**
    // * 删除微信支付配置信息
    // *
    // * @param id 微信支付配置ID
    // * @return 删除微信支付配置数量
    // */
    //public int deleteWxPayConfigById(Long id);
    }
