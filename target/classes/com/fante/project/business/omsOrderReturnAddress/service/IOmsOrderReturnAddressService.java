package com.fante.project.business.omsOrderReturnAddress.service;

import com.fante.project.business.omsOrderReturnAddress.domain.OmsOrderReturnAddress;
import java.util.List;

/**
 * 店铺收发货地址Service接口
 *
 * @author fante
 * @date 2020-03-31
 */
public interface IOmsOrderReturnAddressService {
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
    public String checkOmsOrderReturnAddressUnique(OmsOrderReturnAddress omsOrderReturnAddress);

    /**
     * 新增店铺收发货地址
     *
     * @param omsOrderReturnAddress 店铺收发货地址
     * @return 新增店铺收发货地址数量
     */
    public int insertOmsOrderReturnAddress(OmsOrderReturnAddress omsOrderReturnAddress);

    /**
     * 修改店铺收发货地址
     *
     * @param omsOrderReturnAddress 店铺收发货地址
     * @return 修改店铺收发货地址数量
     */
    public int updateOmsOrderReturnAddress(OmsOrderReturnAddress omsOrderReturnAddress);

    /**
     * 批量删除店铺收发货地址
     *
     * @param ids 需要删除的数据ID
     * @return 删除店铺收发货地址数量
     */
    public int deleteOmsOrderReturnAddressByIds(String ids);

    /**
     * 删除店铺收发货地址信息
     *
     * @param id 店铺收发货地址ID
     * @return 删除店铺收发货地址数量
     */
    public int deleteOmsOrderReturnAddressById(Long id);

    /**
     * 设置默认发货地址
     * @param id
     * @return
     */
    public int sendEnable(Long id);
    /**
     * 设置默认收货地址
     * @param id
     * @return
     */
    public int receiveEnable(Long id);
}
