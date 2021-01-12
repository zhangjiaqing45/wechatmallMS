package com.fante.project.mapperBase;

import com.fante.project.business.umsMemberReceiveAddress.domain.UmsMemberReceiveAddress;
import java.util.List;

/**
 * 会员收货地址Mapper基础接口
 *
 * @author fante
 * @date 2020-04-10
 */
public interface UmsMemberReceiveAddressMapperBase {
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
    public int checkUmsMemberReceiveAddressUnique(UmsMemberReceiveAddress umsMemberReceiveAddress);

    /**
     * 新增会员收货地址
     *
     * @param umsMemberReceiveAddress 会员收货地址
     * @return 结果
     */
    public int insertUmsMemberReceiveAddress(UmsMemberReceiveAddress umsMemberReceiveAddress);

    /**
     * 修改会员收货地址
     *
     * @param umsMemberReceiveAddress 会员收货地址
     * @return 结果
     */
    public int updateUmsMemberReceiveAddress(UmsMemberReceiveAddress umsMemberReceiveAddress);

    /**
     * 删除会员收货地址
     *
     * @param id 会员收货地址ID
     * @return 结果
     */
    public int deleteUmsMemberReceiveAddressById(Long id);

    /**
     * 批量删除会员收货地址
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteUmsMemberReceiveAddressByIds(String[] ids);

}
