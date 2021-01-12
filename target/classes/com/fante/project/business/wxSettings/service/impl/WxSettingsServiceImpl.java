package com.fante.project.business.wxSettings.service.impl;

import com.fante.common.utils.DateUtils;
import com.fante.project.business.wxSettings.domain.WxSettings;
import com.fante.project.business.wxSettings.mapper.WxSettingsMapper;
import com.fante.project.business.wxSettings.service.IWxSettingsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * 微信公众号设置Service业务层处理
 *
 * @author fante
 * @date 2020-02-21
 */
@Service
@CacheConfig(cacheNames = {"weixin.setting"})
public class WxSettingsServiceImpl implements IWxSettingsService {

    private static Logger log = LoggerFactory.getLogger(WxSettingsServiceImpl.class);

    @Autowired
    private WxSettingsMapper wxSettingsMapper;

    ///**
    // * 查询微信公众号设置
    // *
    // * @param id 微信公众号设置ID
    // * @return 微信公众号设置
    // */
    //@Override
    //public WxSettings selectWxSettingsById(Long id) {
    //    if (ObjectUtils.isEmpty(id)) {
    //        return null;
    //    }
    //    return wxSettingsMapper.selectWxSettingsById(id);
    //}

    ///**
    // * 查询微信公众号设置列表
    // *
    // * @param wxSettings 微信公众号设置
    // * @return 微信公众号设置
    // */
    //@Override
    //public List<WxSettings> selectWxSettingsList(WxSettings wxSettings) {
    //    return wxSettingsMapper.selectWxSettingsList(wxSettings);
    //}

    /**
     * 查询最新一条设置
     * @return
     */
    @Override
    @Cacheable(key = "targetClass + methodName")
    public WxSettings selectWxSettingsRecent() {
        return wxSettingsMapper.selectWxSettingsRecent();
    }

    /**
     * 新增微信公众号设置
     *
     * @param wxSettings 微信公众号设置
     * @return 结果
     */
    @Override
    @CacheEvict(allEntries=true)
    public int insertWxSettings(WxSettings wxSettings) {
        wxSettings.setCreateTime(DateUtils.getNowDate());
        return wxSettingsMapper.insertWxSettings(wxSettings);
    }

    /**
     * 修改微信公众号设置
     *
     * @param wxSettings 微信公众号设置
     * @return 结果
     */
    @Override
    @CacheEvict(allEntries=true)
    public int updateWxSettings(WxSettings wxSettings) {
        wxSettings.setUpdateTime(DateUtils.getNowDate());
        return wxSettingsMapper.updateWxSettings(wxSettings);
    }

    /**
     * 删除微信公众号设置对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    //@Override
    //public int deleteWxSettingsByIds(String ids) {
    //    return wxSettingsMapper.deleteWxSettingsByIds(Convert.toStrArray(ids));
    //}

    /**
     * 删除微信公众号设置信息
     *
     * @param id 微信公众号设置ID
     * @return 结果
     */
    //@Override
    //public int deleteWxSettingsById(Long id) {
    //    return wxSettingsMapper.deleteWxSettingsById(id);
    //}
    }
