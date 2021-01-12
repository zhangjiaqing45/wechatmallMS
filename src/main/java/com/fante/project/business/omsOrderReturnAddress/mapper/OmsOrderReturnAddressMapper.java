package com.fante.project.business.omsOrderReturnAddress.mapper;

import com.fante.project.mapperBase.OmsOrderReturnAddressMapperBase;
import org.apache.ibatis.annotations.Param;

/**
 * 店铺收发货地址Mapper扩展接口
 *
 * @author fante
 * @date 2020-03-31
 */

public interface OmsOrderReturnAddressMapper extends OmsOrderReturnAddressMapperBase {
    /**
     * 批量删除店铺收发货地址
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int realDelOmsOrderReturnAddressByIds(String[] ids);
    /**
     * 设置默认发货地址
     * @param id
     * @return
     */
    public int setSendEnable(@Param("id") Long id,@Param("shopId") Long shopId);
    /**
     * 设置默认收货地址
     * @param id
     * @return
     */
    public int setReceiveEnable(@Param("id") Long id,@Param("shopId") Long shopId);
}
