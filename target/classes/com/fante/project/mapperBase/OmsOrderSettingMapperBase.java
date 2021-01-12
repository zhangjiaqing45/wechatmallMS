package com.fante.project.mapperBase;

import com.fante.project.business.omsOrderSetting.domain.OmsOrderSetting;
import java.util.List;

/**
 * 订单设置Mapper基础接口
 *
 * @author fante
 * @date 2020-05-13
 */
public interface OmsOrderSettingMapperBase {
    /**
     * 查询订单设置
     *
     * @param id 订单设置ID
     * @return 订单设置
     */
    public OmsOrderSetting selectOmsOrderSettingById(Long id);

    /**
     * 查询订单设置列表
     *
     * @param omsOrderSetting 订单设置
     * @return 订单设置集合
     */
    public List<OmsOrderSetting> selectOmsOrderSettingList(OmsOrderSetting omsOrderSetting);

    /**
     * 查询订单设置数量
     *
     * @param omsOrderSetting 订单设置
     * @return 结果
     */
    public int countOmsOrderSetting(OmsOrderSetting omsOrderSetting);

    /**
     * 唯一校验
     *
     * @param omsOrderSetting 订单设置
     * @return 结果
     */
    public int checkOmsOrderSettingUnique(OmsOrderSetting omsOrderSetting);

    /**
     * 新增订单设置
     *
     * @param omsOrderSetting 订单设置
     * @return 结果
     */
    public int insertOmsOrderSetting(OmsOrderSetting omsOrderSetting);

    /**
     * 修改订单设置
     *
     * @param omsOrderSetting 订单设置
     * @return 结果
     */
    public int updateOmsOrderSetting(OmsOrderSetting omsOrderSetting);

    /**
     * 删除订单设置
     *
     * @param id 订单设置ID
     * @return 结果
     */
    public int deleteOmsOrderSettingById(Long id);

    /**
     * 批量删除订单设置
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteOmsOrderSettingByIds(String[] ids);

}
