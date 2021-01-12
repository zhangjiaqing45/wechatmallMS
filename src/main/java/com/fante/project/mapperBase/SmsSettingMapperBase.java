package com.fante.project.mapperBase;

import com.fante.project.business.smsSetting.domain.SmsSetting;
import java.util.List;

/**
 * 营销设置(优惠券、团购、签到、秒杀、积分商城是否开启)Mapper基础接口
 *
 * @author fante
 * @date 2020-03-10
 */
public interface SmsSettingMapperBase {
    /**
     * 查询营销设置(优惠券、团购、签到、秒杀、积分商城是否开启)
     *
     * @param id 营销设置(优惠券、团购、签到、秒杀、积分商城是否开启)ID
     * @return 营销设置(优惠券、团购、签到、秒杀、积分商城是否开启)
     */
    public SmsSetting selectSmsSettingById(Long id);

    /**
     * 查询营销设置(优惠券、团购、签到、秒杀、积分商城是否开启)列表
     *
     * @param smsSetting 营销设置(优惠券、团购、签到、秒杀、积分商城是否开启)
     * @return 营销设置(优惠券、团购、签到、秒杀、积分商城是否开启)集合
     */
    public List<SmsSetting> selectSmsSettingList(SmsSetting smsSetting);

    /**
     * 新增营销设置(优惠券、团购、签到、秒杀、积分商城是否开启)
     *
     * @param smsSetting 营销设置(优惠券、团购、签到、秒杀、积分商城是否开启)
     * @return 结果
     */
    public int insertSmsSetting(SmsSetting smsSetting);

    /**
     * 修改营销设置(优惠券、团购、签到、秒杀、积分商城是否开启)
     *
     * @param smsSetting 营销设置(优惠券、团购、签到、秒杀、积分商城是否开启)
     * @return 结果
     */
    public int updateSmsSetting(SmsSetting smsSetting);

    /**
     * 删除营销设置(优惠券、团购、签到、秒杀、积分商城是否开启)
     *
     * @param id 营销设置(优惠券、团购、签到、秒杀、积分商城是否开启)ID
     * @return 结果
     */
    public int deleteSmsSettingById(Long id);

    /**
     * 批量删除营销设置(优惠券、团购、签到、秒杀、积分商城是否开启)
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSmsSettingByIds(String[] ids);
}
