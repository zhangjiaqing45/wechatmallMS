package com.fante.project.business.wxOffiaccountConfig.service.impl;

import com.fante.common.utils.DateUtils;
import com.fante.common.utils.StringUtils;
import com.fante.common.utils.security.ShiroUtils;
import com.fante.project.business.wxOffiaccountConfig.domain.WxOffiaccountConfig;
import com.fante.project.business.wxOffiaccountConfig.mapper.WxOffiaccountConfigMapper;
import com.fante.project.business.wxOffiaccountConfig.service.IWxOffiaccountConfigService;
import com.fante.project.weixin.core.service.impl.WeixinConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.weixin4j.WeixinConfig;

import javax.annotation.PostConstruct;

/**
 * 微信公众号配置Service业务层处理
 *
 * @author fante
 * @date 2020-04-09
 */
@Service
public class WxOffiaccountConfigServiceImpl implements IWxOffiaccountConfigService, InitializingBean {

    private static Logger log = LoggerFactory.getLogger(WxOffiaccountConfigServiceImpl.class);

    @Autowired
    private WxOffiaccountConfigMapper wxOffiaccountConfigMapper;
    @Autowired
    private WeixinConfigService weixinConfigService;

    /**
     * 项目启动时初始化配置
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        WeixinConfig weixinConfig = convert(selectWxOffiaccountConfigRecent());
        weixinConfigService.reloadWeixinConfig(weixinConfig);
    }

    /**
     * 更新配置
     * @param config
     */
    private void updateConfig(WxOffiaccountConfig config) {
        WeixinConfig weixinConfig = convert(config);
        weixinConfigService.reloadWeixinConfig(weixinConfig);
    }

    /**
     * 转换方法
     * @param config
     * @return
     */
    private WeixinConfig convert(WxOffiaccountConfig config) {
        if (ObjectUtils.isEmpty(config)) {
            return null;
        }
        WeixinConfig weixinConfig = new WeixinConfig();
        weixinConfig.setAppid(StringUtils.defaultString(config.getAppid()));
        weixinConfig.setSecret(StringUtils.defaultString(config.getSecret()));
        weixinConfig.setOriginalid(StringUtils.defaultString(config.getOriginalid()));
        weixinConfig.setEncodingtype(ObjectUtils.isEmpty(config.getEncodingtype()) ? 0 : config.getEncodingtype());
        weixinConfig.setEncodingaeskey(StringUtils.defaultString(config.getEncodingaeskey()));
        weixinConfig.setOauthUrl(StringUtils.defaultString(config.getOauthUrl()));
        weixinConfig.setApiDomain(StringUtils.defaultString(config.getApiDomain()));
        return weixinConfig;
    }

    /**
     * 获取最新的配置
     * @return
     */
    @Override
    public WxOffiaccountConfig selectWxOffiaccountConfigRecent() {
        return wxOffiaccountConfigMapper.selectWxOffiaccountConfigRecent();
    }

    /**
     * 新增微信公众号配置
     *
     * @param wxOffiaccountConfig 微信公众号配置
     * @return 新增微信公众号配置数量
     */
    @Override
    public int insertWxOffiaccountConfig(WxOffiaccountConfig wxOffiaccountConfig) {
        if (StringUtils.isBlank(wxOffiaccountConfig.getCreateBy())) {
            wxOffiaccountConfig.setCreateBy(ShiroUtils.getLoginName());
        }
        wxOffiaccountConfig.setCreateTime(DateUtils.getNowDate());
        int result = wxOffiaccountConfigMapper.insertWxOffiaccountConfig(wxOffiaccountConfig);

        updateConfig(wxOffiaccountConfig);
        return result;
    }

    ///**
    // * 查询微信公众号配置
    // *
    // * @param id 微信公众号配置ID
    // * @return 微信公众号配置
    // */
    //@Override
    //public WxOffiaccountConfig selectWxOffiaccountConfigById(Long id) {
    //    if (ObjectUtils.isEmpty(id)) {
    //        return null;
    //    }
    //    return wxOffiaccountConfigMapper.selectWxOffiaccountConfigById(id);
    //}
    //
    ///**
    // * 查询微信公众号配置列表
    // *
    // * @param wxOffiaccountConfig 微信公众号配置
    // * @return 微信公众号配置集合
    // */
    //@Override
    //public List<WxOffiaccountConfig> selectWxOffiaccountConfigList(WxOffiaccountConfig wxOffiaccountConfig) {
    //    return wxOffiaccountConfigMapper.selectWxOffiaccountConfigList(wxOffiaccountConfig);
    //}
    //
    ///**
    // * 查询微信公众号配置数量
    // *
    // * @param wxOffiaccountConfig 微信公众号配置
    // * @return 结果
    // */
    //@Override
    //public int countWxOffiaccountConfig(WxOffiaccountConfig wxOffiaccountConfig){
    //    return wxOffiaccountConfigMapper.countWxOffiaccountConfig(wxOffiaccountConfig);
    //}
    //
    ///**
    // * 唯一校验
    // *
    // * @param wxOffiaccountConfig 微信公众号配置
    // * @return 结果
    // */
    //@Override
    //public String checkWxOffiaccountConfigUnique(WxOffiaccountConfig wxOffiaccountConfig) {
    //    return wxOffiaccountConfigMapper.checkWxOffiaccountConfigUnique(wxOffiaccountConfig) > 0 ? Constants.NOT_UNIQUE : Constants.UNIQUE;
    //}
    //
    //
    //
    ///**
    // * 修改微信公众号配置
    // *
    // * @param wxOffiaccountConfig 微信公众号配置
    // * @return 修改微信公众号配置数量
    // */
    //@Override
    //public int updateWxOffiaccountConfig(WxOffiaccountConfig wxOffiaccountConfig) {
    //    wxOffiaccountConfig.setUpdateTime(DateUtils.getNowDate());
    //    return wxOffiaccountConfigMapper.updateWxOffiaccountConfig(wxOffiaccountConfig);
    //}
    //
    ///**
    // * 删除微信公众号配置对象
    // *
    // * @param ids 需要删除的数据ID
    // * @return 删除微信公众号配置数量
    // */
    //@Override
    //public int deleteWxOffiaccountConfigByIds(String ids) {
    //    return wxOffiaccountConfigMapper.deleteWxOffiaccountConfigByIds(Convert.toStrArray(ids));
    //}
    //
    ///**
    // * 删除微信公众号配置信息
    // *
    // * @param id 微信公众号配置ID
    // * @return 删除微信公众号配置数量
    // */
    //@Override
    //public int deleteWxOffiaccountConfigById(Long id) {
    //    return wxOffiaccountConfigMapper.deleteWxOffiaccountConfigById(id);
    //}
}
