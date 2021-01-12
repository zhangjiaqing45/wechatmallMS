package com.fante.project.business.umsMemberReceiveAddress.service;

import com.fante.project.business.umsMemberReceiveAddress.domain.UmsMemberReceiveAddress;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.callback.Callback;
import java.util.List;

/**
 * 会员收货地址Service接口
 *
 * @author fante
 * @date 2020-04-10
 */
public interface IUmsMemberReceiveAddressService {
    /**
     * 查询会员收货地址
     *
     * @param id 会员收货地址ID
     * @return 会员收货地址
     */
    public UmsMemberReceiveAddress selectUmsMemberReceiveAddressById(Long id);

    /**
     * 查询会员收货地址列表
     *
     * @param umsMemberReceiveAddress 会员收货地址
     * @return 会员收货地址集合
     */
    public List<UmsMemberReceiveAddress> selectUmsMemberReceiveAddressList(UmsMemberReceiveAddress umsMemberReceiveAddress);
    /**
     * 查询会员收货地址列表
     *
     * @param umsMemberReceiveAddress 会员收货地址
     * @return 会员收货地址集合
     */
    public int countMemberAddress(UmsMemberReceiveAddress umsMemberReceiveAddress);
    /**
     * 查询会员收货地址数量
     *
     * @param umsMemberReceiveAddress 会员收货地址
     * @return 结果
     */
    public int countUmsMemberReceiveAddress(UmsMemberReceiveAddress umsMemberReceiveAddress);

    /**
     * 唯一校验
     *
     * @param umsMemberReceiveAddress 会员收货地址
     * @return 结果
     */
    public String checkUmsMemberReceiveAddressUnique(UmsMemberReceiveAddress umsMemberReceiveAddress);

    /**
     * 新增会员收货地址
     *
     * @param umsMemberReceiveAddress 会员收货地址
     * @return 新增会员收货地址数量
     */
    @Transactional(rollbackFor = Exception.class)
    public int insertUmsMemberReceiveAddress(UmsMemberReceiveAddress umsMemberReceiveAddress);

    /**
     * 修改会员收货地址
     *
     * @param umsMemberReceiveAddress 会员收货地址
     * @return 修改会员收货地址数量
     */
    @Transactional(rollbackFor = Exception.class)
    public int updateUmsMemberReceiveAddress(UmsMemberReceiveAddress umsMemberReceiveAddress);

    /**
     * 批量删除会员收货地址
     *
     * @param ids 需要删除的数据ID
     * @return 删除会员收货地址数量
     */
    public int deleteUmsMemberReceiveAddressByIds(String ids);

    /**
     * 删除会员收货地址信息
     *
     * @return 删除会员收货地址数量
     */
    public int deleteUmsMemberReceiveAddressById(Long addressId,Long memberId);

    /**
     * 设置默认地址
     * @param addressId
     * @param memberId
     * @return
     */
    int setDefaultAddress(Long addressId, Long memberId);
}
