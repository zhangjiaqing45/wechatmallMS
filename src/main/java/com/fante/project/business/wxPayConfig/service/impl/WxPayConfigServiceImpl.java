package com.fante.project.business.wxPayConfig.service.impl;

import com.fante.common.utils.DateUtils;
import com.fante.common.utils.StringUtils;
import com.fante.common.utils.security.ShiroUtils;
import com.fante.project.business.wxPayConfig.domain.WxPayConfig;
import com.fante.project.business.wxPayConfig.mapper.WxPayConfigMapper;
import com.fante.project.business.wxPayConfig.service.IWxPayConfigService;
import com.fante.project.weixin.core.service.impl.WeixinConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.weixin4j.WeixinPayConfig;

import javax.annotation.PostConstruct;

/**
 * 微信支付配置Service业务层处理
 *
 * @author fante
 * @date 2020-04-09
 */
@Service
public class WxPayConfigServiceImpl implements IWxPayConfigService, InitializingBean {

    private static Logger log = LoggerFactory.getLogger(WxPayConfigServiceImpl.class);

    @Autowired
    private WxPayConfigMapper wxPayConfigMapper;
    @Autowired
    private WeixinConfigService weixinConfigService;


    /**
     * 项目启动时初始化配置
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        WeixinPayConfig weixinPayConfig = convert(selectWxPayConfigRecent());
        weixinConfigService.reloadWeixinPayConfig(weixinPayConfig);
    }

    /**
     * 更新配置
     * @param config
     */
    private void updateConfig(WxPayConfig config) {
        WeixinPayConfig weixinPayConfig = convert(config);
        weixinConfigService.reloadWeixinPayConfig(weixinPayConfig);
    }

    /**
     * 转换方法
     * @param config
     * @return
     */
    private WeixinPayConfig convert(WxPayConfig config) {
        if (ObjectUtils.isEmpty(config)) {
            return null;
        }
        WeixinPayConfig weixinPayConfig = new WeixinPayConfig();
        weixinPayConfig.setPartnerId(StringUtils.defaultString(config.getPartnerId()));
        weixinPayConfig.setPartnerKey(StringUtils.defaultString(config.getPartnerKey()));
        weixinPayConfig.setNotifyUrl(StringUtils.defaultString(config.getNotifyUrl()));
        weixinPayConfig.setCertPath(StringUtils.defaultString(config.getCertPath()));
        weixinPayConfig.setCertSecret(StringUtils.defaultString(config.getCertSecret()));
        return weixinPayConfig;
    }


    /**
     * 获取最新的配置
     * @return
     */
    @Override
    public WxPayConfig selectWxPayConfigRecent() {
        return wxPayConfigMapper.selectWxPayConfigRecent();
    }

    /**
     * 新增微信支付配置
     *
     * @param wxPayConfig 微信支付配置
     * @return 新增微信支付配置数量
     */
    @Override
    public int insertWxPayConfig(WxPayConfig wxPayConfig) {
        if (StringUtils.isBlank(wxPayConfig.getCreateBy())) {
            wxPayConfig.setCreateBy(ShiroUtils.getLoginName());
        }
        wxPayConfig.setCreateTime(DateUtils.getNowDate());
        int result = wxPayConfigMapper.insertWxPayConfig(wxPayConfig);

        updateConfig(wxPayConfig);
        return result;
    }


    ///**
    // * 查询微信支付配置
    // *
    // * @param id 微信支付配置ID
    // * @return 微信支付配置
    // */
    //@Override
    //public WxPayConfig selectWxPayConfigById(Long id) {
    //    if (ObjectUtils.isEmpty(id)) {
    //        return null;
    //    }
    //    return wxPayConfigMapper.selectWxPayConfigById(id);
    //}
    //
    ///**
    // * 查询微信支付配置列表
    // *
    // * @param wxPayConfig 微信支付配置
    // * @return 微信支付配置集合
    // */
    //@Override
    //public List<WxPayConfig> selectWxPayConfigList(WxPayConfig wxPayConfig) {
    //    return wxPayConfigMapper.selectWxPayConfigList(wxPayConfig);
    //}
    //
    ///**
    // * 查询微信支付配置数量
    // *
    // * @param wxPayConfig 微信支付配置
    // * @return 结果
    // */
    //@Override
    //public int countWxPayConfig(WxPayConfig wxPayConfig){
    //    return wxPayConfigMapper.countWxPayConfig(wxPayConfig);
    //}
    //
    ///**
    // * 唯一校验
    // *
    // * @param wxPayConfig 微信支付配置
    // * @return 结果
    // */
    //@Override
    //public String checkWxPayConfigUnique(WxPayConfig wxPayConfig) {
    //    return wxPayConfigMapper.checkWxPayConfigUnique(wxPayConfig) > 0 ? Constants.NOT_UNIQUE : Constants.UNIQUE;
    //}
    //
    ///**
    // * 修改微信支付配置
    // *
    // * @param wxPayConfig 微信支付配置
    // * @return 修改微信支付配置数量
    // */
    //@Override
    //public int updateWxPayConfig(WxPayConfig wxPayConfig) {
    //    wxPayConfig.setUpdateTime(DateUtils.getNowDate());
    //    return wxPayConfigMapper.updateWxPayConfig(wxPayConfig);
    //}
    //
    ///**
    // * 删除微信支付配置对象
    // *
    // * @param ids 需要删除的数据ID
    // * @return 删除微信支付配置数量
    // */
    //@Override
    //public int deleteWxPayConfigByIds(String ids) {
    //    return wxPayConfigMapper.deleteWxPayConfigByIds(Convert.toStrArray(ids));
    //}
    //
    ///**
    // * 删除微信支付配置信息
    // *
    // * @param id 微信支付配置ID
    // * @return 删除微信支付配置数量
    // */
    //@Override
    //public int deleteWxPayConfigById(Long id) {
    //    return wxPayConfigMapper.deleteWxPayConfigById(id);
    //}
}
