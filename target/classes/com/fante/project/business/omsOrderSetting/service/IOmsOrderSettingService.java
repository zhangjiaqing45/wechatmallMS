package com.fante.project.business.omsOrderSetting.service;

import com.fante.project.business.omsOrderSetting.domain.OmsOrderSetting;

/**
 * 订单设置Service接口
 *
 * @author fante
 * @date 2020-05-13
 */
public interface IOmsOrderSettingService {
    ///**
    // * 查询订单设置
    // *
    // * @param id 订单设置ID
    // * @return 订单设置
    // */
    //public OmsOrderSetting selectOmsOrderSettingById(Long id);
    //
    ///**
    // * 查询订单设置列表
    // *
    // * @param omsOrderSetting 订单设置
    // * @return 订单设置集合
    // */
    //public List<OmsOrderSetting> selectOmsOrderSettingList(OmsOrderSetting omsOrderSetting);
    //
    ///**
    // * 查询订单设置数量
    // *
    // * @param omsOrderSetting 订单设置
    // * @return 结果
    // */
    //public int countOmsOrderSetting(OmsOrderSetting omsOrderSetting);
    //
    ///**
    // * 唯一校验
    // *
    // * @param omsOrderSetting 订单设置
    // * @return 结果
    // */
    //public String checkOmsOrderSettingUnique(OmsOrderSetting omsOrderSetting);

    /**
     * 新增订单设置
     *
     * @param omsOrderSetting 订单设置
     * @return 新增订单设置数量
     */
    public int insertOmsOrderSetting(OmsOrderSetting omsOrderSetting);

    ///**
    // * 修改订单设置
    // *
    // * @param omsOrderSetting 订单设置
    // * @return 修改订单设置数量
    // */
    //public int updateOmsOrderSetting(OmsOrderSetting omsOrderSetting);
    //
    ///**
    // * 批量删除订单设置
    // *
    // * @param ids 需要删除的数据ID
    // * @return 删除订单设置数量
    // */
    //public int deleteOmsOrderSettingByIds(String ids);
    //
    ///**
    // * 删除订单设置信息
    // *
    // * @param id 订单设置ID
    // * @return 删除订单设置数量
    // */
    //public int deleteOmsOrderSettingById(Long id);

    /**
     * 获取最新数据
     * @return
     */
    OmsOrderSetting selectOmsOrderSettingRecent();
    }
