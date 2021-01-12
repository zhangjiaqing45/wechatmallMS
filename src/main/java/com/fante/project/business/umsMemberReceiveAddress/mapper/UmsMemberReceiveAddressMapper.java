package com.fante.project.business.umsMemberReceiveAddress.mapper;

import com.fante.project.business.umsMemberReceiveAddress.domain.UmsMemberReceiveAddress;
import com.fante.project.mapperBase.UmsMemberReceiveAddressMapperBase;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 会员收货地址Mapper扩展接口
 *
 * @author fante
 * @date 2020-04-10
 */

public interface UmsMemberReceiveAddressMapper extends UmsMemberReceiveAddressMapperBase {

    /**
     * 获取用户的收货地址（传入status是可获取默认地址）
     * 通过status和时间排序
     */
    List<UmsMemberReceiveAddress> getUmsMemberReceiveAddressList(UmsMemberReceiveAddress param);
    /**
     * 获取用户的收货地址数量（传入status是可获取默认地址是否存在）
     */
    int countMemberAddress(UmsMemberReceiveAddress param);

    /**
     * 删除收货地址
     * @param id
     * @param memberId
     * @return
     */
    int realDelUmsMemberReceiveAddressById(@Param("id") Long id,@Param("memberId") Long memberId);
    /**
     * 设置默认地址
     * @param id
     * @param memberId
     * @return
     */
    int setDefaultAddress(@Param("id") Long id,@Param("memberId") Long memberId);
}
