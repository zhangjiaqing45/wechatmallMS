package com.fante.project.business.omsOrderWriteOff.service;

import com.fante.project.business.omsOrderWriteOff.domain.OmsOrderWriteOff;
import java.util.List;

/**
 * 订单核销记录Service接口
 *
 * @author fante
 * @date 2020-11-14
 */
public interface IOmsOrderWriteOffService {
    /**
     * 查询订单核销记录
     *
     * @param id 订单核销记录ID
     * @return 订单核销记录
     */
    public OmsOrderWriteOff selectOmsOrderWriteOffById(Long id);

    /**
     * 查询订单核销记录列表
     *
     * @param omsOrderWriteOff 订单核销记录
     * @return 订单核销记录集合
     */
    public List<OmsOrderWriteOff> selectOmsOrderWriteOffList(OmsOrderWriteOff omsOrderWriteOff);

    /**
     * 查询订单核销记录数量
     *
     * @param omsOrderWriteOff 订单核销记录
     * @return 结果
     */
    public int countOmsOrderWriteOff(OmsOrderWriteOff omsOrderWriteOff);

    /**
     * 唯一校验
     *
     * @param omsOrderWriteOff 订单核销记录
     * @return 结果
     */
    public String checkOmsOrderWriteOffUnique(OmsOrderWriteOff omsOrderWriteOff);

    /**
     * 新增订单核销记录
     *
     * @param omsOrderWriteOff 订单核销记录
     * @return 新增订单核销记录数量
     */
    public int insertOmsOrderWriteOff(OmsOrderWriteOff omsOrderWriteOff);

    /**
     * 修改订单核销记录
     *
     * @param omsOrderWriteOff 订单核销记录
     * @return 修改订单核销记录数量
     */
    public int updateOmsOrderWriteOff(OmsOrderWriteOff omsOrderWriteOff);

    /**
     * 批量删除订单核销记录
     *
     * @param ids 需要删除的数据ID
     * @return 删除订单核销记录数量
     */
    public int deleteOmsOrderWriteOffByIds(String ids);

    /**
     * 删除订单核销记录信息
     *
     * @param id 订单核销记录ID
     * @return 删除订单核销记录数量
     */
    public int deleteOmsOrderWriteOffById(Long id);
    }
