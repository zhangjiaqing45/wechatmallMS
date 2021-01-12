package com.fante.project.mapperBase;

import com.fante.project.business.omsOrderReturnAddress.domain.OmsOrderReturnAddress;
import java.util.List;

/**
 * 店铺收发货地址Mapper基础接口
 *
 * @author fante
 * @date 2020-04-01
 */
public interface OmsOrderReturnAddressMapperBase {
    /**
     * 查询店铺收发货地址
     *
     * @param id 店铺收发货地址ID
     * @return 店铺收发货地址
     */
    public OmsOrderReturnAddress selectOmsOrderReturnAddressById(Long id);

    /**
     * 查询店铺收发货地址列表
     *
     * @param omsOrderReturnAddress 店铺收发货地址
     * @return 店铺收发货地址集合
     */
    public List<OmsOrderReturnAddress> selectOmsOrderReturnAddressList(OmsOrderReturnAddress omsOrderReturnAddress);

    /**
     * 查询店铺收发货地址数量
     *
     * @param omsOrderReturnAddress 店铺收发货地址
     * @return 结果
     */
    public int countOmsOrderReturnAddress(OmsOrderReturnAddress omsOrderReturnAddress);

    /**
     * 唯一校验
     *
     * @param omsOrderReturnAddress 店铺收发货地址
     * @return 结果
     */
    public int checkOmsOrderReturnAddressUnique(OmsOrderReturnAddress omsOrderReturnAddress);

    /**
     * 新增店铺收发货地址
     *
     * @param omsOrderReturnAddress 店铺收发货地址
     * @return 结果
     */
    public int insertOmsOrderReturnAddress(OmsOrderReturnAddress omsOrderReturnAddress);

    /**
     * 修改店铺收发货地址
     *
     * @param omsOrderReturnAddress 店铺收发货地址
     * @return 结果
     */
    public int updateOmsOrderReturnAddress(OmsOrderReturnAddress omsOrderReturnAddress);

    /**
     * 删除店铺收发货地址
     *
     * @param id 店铺收发货地址ID
     * @return 结果
     */
    public int deleteOmsOrderReturnAddressById(Long id);

    /**
     * 批量删除店铺收发货地址
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteOmsOrderReturnAddressByIds(String[] ids);

}
