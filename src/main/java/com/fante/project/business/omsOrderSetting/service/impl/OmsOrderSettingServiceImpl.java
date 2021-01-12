package com.fante.project.business.omsOrderSetting.service.impl;

import com.fante.common.utils.DateUtils;
import com.fante.common.utils.StringUtils;
import com.fante.common.utils.security.ShiroUtils;
import com.fante.project.business.omsOrderSetting.domain.OmsOrderSetting;
import com.fante.project.business.omsOrderSetting.mapper.OmsOrderSettingMapper;
import com.fante.project.business.omsOrderSetting.service.IOmsOrderSettingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * 订单设置Service业务层处理
 *
 * @author fante
 * @date 2020-05-13
 */
@Service
@CacheConfig(cacheNames = {"oms.order.setting"})
public class OmsOrderSettingServiceImpl implements IOmsOrderSettingService {

    private static Logger log = LoggerFactory.getLogger(OmsOrderSettingServiceImpl.class);

    @Autowired
    private OmsOrderSettingMapper omsOrderSettingMapper;

    ///**
    // * 查询订单设置
    // *
    // * @param id 订单设置ID
    // * @return 订单设置
    // */
    //@Override
    //public OmsOrderSetting selectOmsOrderSettingById(Long id) {
    //    if (ObjectUtils.isEmpty(id)) {
    //        return null;
    //    }
    //    return omsOrderSettingMapper.selectOmsOrderSettingById(id);
    //}
    //
    ///**
    // * 查询订单设置列表
    // *
    // * @param omsOrderSetting 订单设置
    // * @return 订单设置集合
    // */
    //@Override
    //public List<OmsOrderSetting> selectOmsOrderSettingList(OmsOrderSetting omsOrderSetting) {
    //    return omsOrderSettingMapper.selectOmsOrderSettingList(omsOrderSetting);
    //}
    //
    ///**
    // * 查询订单设置数量
    // *
    // * @param omsOrderSetting 订单设置
    // * @return 结果
    // */
    //@Override
    //public int countOmsOrderSetting(OmsOrderSetting omsOrderSetting){
    //    return omsOrderSettingMapper.countOmsOrderSetting(omsOrderSetting);
    //}
    //
    ///**
    // * 唯一校验
    // *
    // * @param omsOrderSetting 订单设置
    // * @return 结果
    // */
    //@Override
    //public String checkOmsOrderSettingUnique(OmsOrderSetting omsOrderSetting) {
    //    return omsOrderSettingMapper.checkOmsOrderSettingUnique(omsOrderSetting) > 0 ? Constants.NOT_UNIQUE : Constants.UNIQUE;
    //}

    /**
     * 新增订单设置
     *
     * @param omsOrderSetting 订单设置
     * @return 新增订单设置数量
     */
    @Override
    @CacheEvict(allEntries=true)
    public int insertOmsOrderSetting(OmsOrderSetting omsOrderSetting) {
        if (StringUtils.isBlank(omsOrderSetting.getCreateBy())) {
            omsOrderSetting.setCreateBy(ShiroUtils.getLoginName());
        }
        omsOrderSetting.setCreateTime(DateUtils.getNowDate());
        return omsOrderSettingMapper.insertOmsOrderSetting(omsOrderSetting);
    }

    ///**
    // * 修改订单设置
    // *
    // * @param omsOrderSetting 订单设置
    // * @return 修改订单设置数量
    // */
    //@Override
    //public int updateOmsOrderSetting(OmsOrderSetting omsOrderSetting) {
    //    omsOrderSetting.setUpdateTime(DateUtils.getNowDate());
    //    return omsOrderSettingMapper.updateOmsOrderSetting(omsOrderSetting);
    //}
    //
    ///**
    // * 删除订单设置对象
    // *
    // * @param ids 需要删除的数据ID
    // * @return 删除订单设置数量
    // */
    //@Override
    //public int deleteOmsOrderSettingByIds(String ids) {
    //    return omsOrderSettingMapper.deleteOmsOrderSettingByIds(Convert.toStrArray(ids));
    //}
    //
    ///**
    // * 删除订单设置信息
    // *
    // * @param id 订单设置ID
    // * @return 删除订单设置数量
    // */
    //@Override
    //public int deleteOmsOrderSettingById(Long id) {
    //    return omsOrderSettingMapper.deleteOmsOrderSettingById(id);
    //}


    @Override
    @Cacheable(key = "targetClass + methodName")
    public OmsOrderSetting selectOmsOrderSettingRecent() {
        return omsOrderSettingMapper.selectOmsOrderSettingRecent();
    }
}
