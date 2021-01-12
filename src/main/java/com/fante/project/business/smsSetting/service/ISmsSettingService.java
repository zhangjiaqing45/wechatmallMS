package com.fante.project.business.smsSetting.service;

import com.fante.project.business.smsSetting.domain.SmsSetting;

/**
 * 营销设置(优惠券、团购、签到、秒杀、积分商城是否开启)Service接口
 *
 * @author fante
 * @date 2020-03-10
 */
public interface ISmsSettingService {
    /**
     * 查询营销设置(优惠券、团购、签到、秒杀、积分商城是否开启)
     *
     * @param id 营销设置(优惠券、团购、签到、秒杀、积分商城是否开启)ID
     * @return 营销设置(优惠券、团购、签到、秒杀、积分商城是否开启)
     */
    //public SmsSetting selectSmsSettingById(Long id);

    /**
     * 查询营销设置(优惠券、团购、签到、秒杀、积分商城是否开启)列表
     *
     * @param smsSetting 营销设置(优惠券、团购、签到、秒杀、积分商城是否开启)
     * @return 营销设置(优惠券、团购、签到、秒杀、积分商城是否开启)集合
     */
    //public List<SmsSetting> selectSmsSettingList(SmsSetting smsSetting);

    /**
     * 新增营销设置(优惠券、团购、签到、秒杀、积分商城是否开启)
     *
     * @param smsSetting 营销设置(优惠券、团购、签到、秒杀、积分商城是否开启)
     * @return 新增营销设置(优惠券、团购、签到、秒杀、积分商城是否开启)数量
     */
    public int insertSmsSetting(SmsSetting smsSetting);

    /**
     * 修改营销设置(优惠券、团购、签到、秒杀、积分商城是否开启)
     *
     * @param smsSetting 营销设置(优惠券、团购、签到、秒杀、积分商城是否开启)
     * @return 修改营销设置(优惠券、团购、签到、秒杀、积分商城是否开启)数量
     */
    //public int updateSmsSetting(SmsSetting smsSetting);

    /**
     * 批量删除营销设置(优惠券、团购、签到、秒杀、积分商城是否开启)
     *
     * @param ids 需要删除的数据ID
     * @return 删除营销设置(优惠券、团购、签到、秒杀、积分商城是否开启)数量
     */
    //public int deleteSmsSettingByIds(String ids);

    /**
     * 删除营销设置(优惠券、团购、签到、秒杀、积分商城是否开启)信息
     *
     * @param id 营销设置(优惠券、团购、签到、秒杀、积分商城是否开启)ID
     * @return 删除营销设置(优惠券、团购、签到、秒杀、积分商城是否开启)数量
     */
    //public int deleteSmsSettingById(Long id);

    /**
     * 查询最新一条设置
     * @return
     */
    SmsSetting selectSmsSettingRecent();
}
