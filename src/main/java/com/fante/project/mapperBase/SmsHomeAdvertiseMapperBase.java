package com.fante.project.mapperBase;

import com.fante.project.business.smsHomeAdvertise.domain.SmsHomeAdvertise;
import java.util.List;

/**
 * 广告管理Mapper基础接口
 *
 * @author fante
 * @date 2020-04-07
 */
public interface SmsHomeAdvertiseMapperBase {
    /**
     * 查询广告管理
     *
     * @param id 广告管理ID
     * @return 广告管理
     */
    public SmsHomeAdvertise selectSmsHomeAdvertiseById(Long id);

    /**
     * 查询广告管理列表
     *
     * @param smsHomeAdvertise 广告管理
     * @return 广告管理集合
     */
    public List<SmsHomeAdvertise> selectSmsHomeAdvertiseList(SmsHomeAdvertise smsHomeAdvertise);

    /**
     * 查询广告管理数量
     *
     * @param smsHomeAdvertise 广告管理
     * @return 结果
     */
    public int countSmsHomeAdvertise(SmsHomeAdvertise smsHomeAdvertise);

    /**
     * 唯一校验
     *
     * @param smsHomeAdvertise 广告管理
     * @return 结果
     */
    public int checkSmsHomeAdvertiseUnique(SmsHomeAdvertise smsHomeAdvertise);

    /**
     * 新增广告管理
     *
     * @param smsHomeAdvertise 广告管理
     * @return 结果
     */
    public int insertSmsHomeAdvertise(SmsHomeAdvertise smsHomeAdvertise);

    /**
     * 修改广告管理
     *
     * @param smsHomeAdvertise 广告管理
     * @return 结果
     */
    public int updateSmsHomeAdvertise(SmsHomeAdvertise smsHomeAdvertise);

    /**
     * 删除广告管理
     *
     * @param id 广告管理ID
     * @return 结果
     */
    public int deleteSmsHomeAdvertiseById(Long id);

    /**
     * 批量删除广告管理
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSmsHomeAdvertiseByIds(String[] ids);

}
