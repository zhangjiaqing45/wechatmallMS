package com.fante.project.business.smsSetting.service.impl;

import com.fante.common.utils.DateUtils;
import com.fante.common.utils.StringUtils;
import com.fante.common.utils.security.ShiroUtils;
import com.fante.project.business.smsSetting.domain.SmsSetting;
import com.fante.project.business.smsSetting.mapper.SmsSettingMapper;
import com.fante.project.business.smsSetting.service.ISmsSettingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * 营销设置(优惠券、团购、签到、秒杀、积分商城是否开启)Service业务层处理
 *
 * @author fante
 * @date 2020-03-10
 */
@Service
@CacheConfig(cacheNames = {"sms.setting"})
public class SmsSettingServiceImpl implements ISmsSettingService {

    private static Logger log = LoggerFactory.getLogger(SmsSettingServiceImpl.class);

    @Autowired
    private SmsSettingMapper smsSettingMapper;


    /**
     * 查询最新一条设置
     *
     * @return
     */
    @Override
    @Cacheable(key = "targetClass + methodName")
    public SmsSetting selectSmsSettingRecent() {
        return smsSettingMapper.selectSmsSettingRecent();
    }

    /**
     * 新增营销设置
     *
     * @param smsSetting
     * @return
     */
    @Override
    @CacheEvict(allEntries=true)
    public int insertSmsSetting(SmsSetting smsSetting) {
        if (StringUtils.isBlank(smsSetting.getCreateBy())) {
            smsSetting.setCreateBy(ShiroUtils.getLoginName());
        }
        smsSetting.setCreateTime(DateUtils.getNowDate());
        return smsSettingMapper.insertSmsSetting(smsSetting);
    }


    /**
     * 查询营销设置(优惠券、团购、签到、秒杀、积分商城是否开启)
     *
     * @param id 营销设置(优惠券、团购、签到、秒杀、积分商城是否开启)ID
     * @return 营销设置(优惠券、团购、签到、秒杀、积分商城是否开启)
     */
    //@Override
    //public SmsSetting selectSmsSettingById(Long id) {
    //    if (ObjectUtils.isEmpty(id)) {
    //        return null;
    //    }
    //    return smsSettingMapper.selectSmsSettingById(id);
    //}

    /**
     * 查询营销设置(优惠券、团购、签到、秒杀、积分商城是否开启)列表
     *
     * @param smsSetting 营销设置(优惠券、团购、签到、秒杀、积分商城是否开启)
     * @return 营销设置(优惠券、团购、签到、秒杀、积分商城是否开启)集合
     */
    //@Override
    //public List<SmsSetting> selectSmsSettingList(SmsSetting smsSetting) {
    //    return smsSettingMapper.selectSmsSettingList(smsSetting);
    //}


    /**
     * 修改营销设置(优惠券、团购、签到、秒杀、积分商城是否开启)
     *
     * @param smsSetting 营销设置(优惠券、团购、签到、秒杀、积分商城是否开启)
     * @return 修改营销设置(优惠券、团购、签到、秒杀、积分商城是否开启)数量
     */
    //@Override
    //public int updateSmsSetting(SmsSetting smsSetting) {
    //    smsSetting.setUpdateTime(DateUtils.getNowDate());
    //    return smsSettingMapper.updateSmsSetting(smsSetting);
    //}

    /**
     * 删除营销设置(优惠券、团购、签到、秒杀、积分商城是否开启)对象
     *
     * @param ids 需要删除的数据ID
     * @return 删除营销设置(优惠券、团购、签到、秒杀、积分商城是否开启)数量
     */
    //@Override
    //public int deleteSmsSettingByIds(String ids) {
    //    return smsSettingMapper.deleteSmsSettingByIds(Convert.toStrArray(ids));
    //}

    /**
     * 删除营销设置(优惠券、团购、签到、秒杀、积分商城是否开启)信息
     *
     * @param id 营销设置(优惠券、团购、签到、秒杀、积分商城是否开启)ID
     * @return 删除营销设置(优惠券、团购、签到、秒杀、积分商城是否开启)数量
     */
    //@Override
    //public int deleteSmsSettingById(Long id) {
    //    return smsSettingMapper.deleteSmsSettingById(id);
    //}


}
