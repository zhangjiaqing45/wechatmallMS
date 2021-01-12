package com.fante.project.mapperBase;

import com.fante.project.business.omsOrderWriteOff.domain.OmsOrderWriteOff;
import java.util.List;

/**
 * 订单核销记录Mapper基础接口
 *
 * @author fante
 * @date 2020-11-14
 */
public interface OmsOrderWriteOffMapperBase {
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
    public int checkOmsOrderWriteOffUnique(OmsOrderWriteOff omsOrderWriteOff);

    /**
     * 新增订单核销记录
     *
     * @param omsOrderWriteOff 订单核销记录
     * @return 结果
     */
    public int insertOmsOrderWriteOff(OmsOrderWriteOff omsOrderWriteOff);

    /**
     * 修改订单核销记录
     *
     * @param omsOrderWriteOff 订单核销记录
     * @return 结果
     */
    public int updateOmsOrderWriteOff(OmsOrderWriteOff omsOrderWriteOff);

    /**
     * 删除订单核销记录
     *
     * @param id 订单核销记录ID
     * @return 结果
     */
    public int deleteOmsOrderWriteOffById(Long id);

    /**
     * 批量删除订单核销记录
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteOmsOrderWriteOffByIds(String[] ids);

}
