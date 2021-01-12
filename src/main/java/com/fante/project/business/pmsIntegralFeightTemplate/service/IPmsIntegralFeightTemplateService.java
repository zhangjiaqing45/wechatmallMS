package com.fante.project.business.pmsIntegralFeightTemplate.service;

import com.fante.project.business.pmsIntegralFeightTemplate.domain.PmsIntegralFeightTemplate;

import java.math.BigDecimal;

/**
 * 积分商品运费设置Service接口
 *
 * @author fante
 * @date 2020-04-13
 */
public interface IPmsIntegralFeightTemplateService {

    /**
     * 查询最新设置
     * @return
     */
    PmsIntegralFeightTemplate selectPmsIntegralFeightTemplateRecent();


    /**
     * 新增积分商品运费设置
     *
     * @param pmsIntegralFeightTemplate 积分商品运费设置
     * @return 新增积分商品运费设置数量
     */
    int insertPmsIntegralFeightTemplate(PmsIntegralFeightTemplate pmsIntegralFeightTemplate);

    /**
     * 查询最新模板与下单区域，得出商品是否配送<br/>
     *  true 配送 false 不配送
     * @param region
     * @return
     */
    boolean getFeightFeeByRegion(String region);
    ///**
    // * 查询积分商品运费设置
    // *
    // * @param id 积分商品运费设置ID
    // * @return 积分商品运费设置
    // */
    //public PmsIntegralFeightTemplate selectPmsIntegralFeightTemplateById(Long id);
    //
    ///**
    // * 查询积分商品运费设置列表
    // *
    // * @param pmsIntegralFeightTemplate 积分商品运费设置
    // * @return 积分商品运费设置集合
    // */
    //public List<PmsIntegralFeightTemplate> selectPmsIntegralFeightTemplateList(PmsIntegralFeightTemplate pmsIntegralFeightTemplate);
    //
    ///**
    // * 查询积分商品运费设置数量
    // *
    // * @param pmsIntegralFeightTemplate 积分商品运费设置
    // * @return 结果
    // */
    //public int countPmsIntegralFeightTemplate(PmsIntegralFeightTemplate pmsIntegralFeightTemplate);
    //
    ///**
    // * 唯一校验
    // *
    // * @param pmsIntegralFeightTemplate 积分商品运费设置
    // * @return 结果
    // */
    //public String checkPmsIntegralFeightTemplateUnique(PmsIntegralFeightTemplate pmsIntegralFeightTemplate);
    //
    //
    ///**
    // * 修改积分商品运费设置
    // *
    // * @param pmsIntegralFeightTemplate 积分商品运费设置
    // * @return 修改积分商品运费设置数量
    // */
    //public int updatePmsIntegralFeightTemplate(PmsIntegralFeightTemplate pmsIntegralFeightTemplate);
    //
    ///**
    // * 批量删除积分商品运费设置
    // *
    // * @param ids 需要删除的数据ID
    // * @return 删除积分商品运费设置数量
    // */
    //public int deletePmsIntegralFeightTemplateByIds(String ids);
    //
    ///**
    // * 删除积分商品运费设置信息
    // *
    // * @param id 积分商品运费设置ID
    // * @return 删除积分商品运费设置数量
    // */
    //public int deletePmsIntegralFeightTemplateById(Long id);
    }
