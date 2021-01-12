package com.fante.project.business.pmsIntegralFeightTemplate.service.impl;

import com.fante.common.exception.BusinessException;
import com.fante.common.utils.Checker;
import com.fante.common.utils.DateUtils;
import com.fante.common.utils.StringUtils;
import com.fante.common.utils.security.ShiroUtils;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.project.business.pmsFeightTemplate.domain.PmsFeightTemplate;
import com.fante.project.business.pmsIntegralFeightTemplate.domain.PmsIntegralFeightTemplate;
import com.fante.project.business.pmsIntegralFeightTemplate.mapper.PmsIntegralFeightTemplateMapper;
import com.fante.project.business.pmsIntegralFeightTemplate.service.IPmsIntegralFeightTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;

/**
 * 积分商品运费设置Service业务层处理
 *
 * @author fante
 * @date 2020-04-13
 */
@Service
public class PmsIntegralFeightTemplateServiceImpl implements IPmsIntegralFeightTemplateService {

    private static Logger log = LoggerFactory.getLogger(PmsIntegralFeightTemplateServiceImpl.class);

    @Autowired
    private PmsIntegralFeightTemplateMapper pmsIntegralFeightTemplateMapper;


    /**
     * 查询最新设置
     * @return
     */
    @Override
    public PmsIntegralFeightTemplate selectPmsIntegralFeightTemplateRecent() {
        return pmsIntegralFeightTemplateMapper.selectPmsIntegralFeightTemplateRecent();
    }

    /**
     * 新增积分商品运费设置
     *
     * @param pmsIntegralFeightTemplate 积分商品运费设置
     * @return 新增积分商品运费设置数量
     */
    @Override
    public int insertPmsIntegralFeightTemplate(PmsIntegralFeightTemplate pmsIntegralFeightTemplate) {
        if (StringUtils.isBlank(pmsIntegralFeightTemplate.getCreateBy())) {
            pmsIntegralFeightTemplate.setCreateBy(ShiroUtils.getLoginName());
        }
        pmsIntegralFeightTemplate.setCreateTime(DateUtils.getNowDate());
        return pmsIntegralFeightTemplateMapper.insertPmsIntegralFeightTemplate(pmsIntegralFeightTemplate);
    }

    /**
     * 查询最新模板与下单区域，得出商品是否配送<br/>
     *  true 配送 false 不配送
     * @param region
     * @return
     */
    @Override
    public boolean getFeightFeeByRegion(String region) {
        PmsIntegralFeightTemplate template = selectPmsIntegralFeightTemplateRecent();
        //Checker.check(ObjectUtils.isEmpty(template), "运费模板不存在");
        String freeDest = StringUtils.defaultString(template.getFreeDest());

        if (freeDest.contains(region)) {
            // 包邮配送区域
            return true;
        } else {
            //不配送
            return false;
        }
    }
    ///**
    // * 查询积分商品运费设置
    // *
    // * @param id 积分商品运费设置ID
    // * @return 积分商品运费设置
    // */
    //@Override
    //public PmsIntegralFeightTemplate selectPmsIntegralFeightTemplateById(Long id) {
    //    if (ObjectUtils.isEmpty(id)) {
    //        return null;
    //    }
    //    return pmsIntegralFeightTemplateMapper.selectPmsIntegralFeightTemplateById(id);
    //}
    //
    ///**
    // * 查询积分商品运费设置列表
    // *
    // * @param pmsIntegralFeightTemplate 积分商品运费设置
    // * @return 积分商品运费设置集合
    // */
    //@Override
    //public List<PmsIntegralFeightTemplate> selectPmsIntegralFeightTemplateList(PmsIntegralFeightTemplate pmsIntegralFeightTemplate) {
    //    return pmsIntegralFeightTemplateMapper.selectPmsIntegralFeightTemplateList(pmsIntegralFeightTemplate);
    //}
    //
    ///**
    // * 查询积分商品运费设置数量
    // *
    // * @param pmsIntegralFeightTemplate 积分商品运费设置
    // * @return 结果
    // */
    //@Override
    //public int countPmsIntegralFeightTemplate(PmsIntegralFeightTemplate pmsIntegralFeightTemplate){
    //    return pmsIntegralFeightTemplateMapper.countPmsIntegralFeightTemplate(pmsIntegralFeightTemplate);
    //}
    //
    ///**
    // * 唯一校验
    // *
    // * @param pmsIntegralFeightTemplate 积分商品运费设置
    // * @return 结果
    // */
    //@Override
    //public String checkPmsIntegralFeightTemplateUnique(PmsIntegralFeightTemplate pmsIntegralFeightTemplate) {
    //    return pmsIntegralFeightTemplateMapper.checkPmsIntegralFeightTemplateUnique(pmsIntegralFeightTemplate) > 0 ? Constants.NOT_UNIQUE : Constants.UNIQUE;
    //}
    //
    //
    ///**
    // * 修改积分商品运费设置
    // *
    // * @param pmsIntegralFeightTemplate 积分商品运费设置
    // * @return 修改积分商品运费设置数量
    // */
    //@Override
    //public int updatePmsIntegralFeightTemplate(PmsIntegralFeightTemplate pmsIntegralFeightTemplate) {
    //    pmsIntegralFeightTemplate.setUpdateTime(DateUtils.getNowDate());
    //    return pmsIntegralFeightTemplateMapper.updatePmsIntegralFeightTemplate(pmsIntegralFeightTemplate);
    //}
    //
    ///**
    // * 删除积分商品运费设置对象
    // *
    // * @param ids 需要删除的数据ID
    // * @return 删除积分商品运费设置数量
    // */
    //@Override
    //public int deletePmsIntegralFeightTemplateByIds(String ids) {
    //    return pmsIntegralFeightTemplateMapper.deletePmsIntegralFeightTemplateByIds(Convert.toStrArray(ids));
    //}
    //
    ///**
    // * 删除积分商品运费设置信息
    // *
    // * @param id 积分商品运费设置ID
    // * @return 删除积分商品运费设置数量
    // */
    //@Override
    //public int deletePmsIntegralFeightTemplateById(Long id) {
    //    return pmsIntegralFeightTemplateMapper.deletePmsIntegralFeightTemplateById(id);
    //}
}
