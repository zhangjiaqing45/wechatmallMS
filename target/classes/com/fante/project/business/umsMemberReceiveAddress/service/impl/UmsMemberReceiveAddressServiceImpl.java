package com.fante.project.business.umsMemberReceiveAddress.service.impl;

import java.util.List;
import java.util.Objects;

import com.fante.common.business.enums.UmsAddressConst;
import com.fante.common.utils.Checker;
import com.fante.common.utils.DateUtils;
import com.fante.common.constant.Constants;
import com.fante.common.utils.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fante.project.business.umsMemberReceiveAddress.mapper.UmsMemberReceiveAddressMapper;
import com.fante.project.business.umsMemberReceiveAddress.domain.UmsMemberReceiveAddress;
import com.fante.project.business.umsMemberReceiveAddress.service.IUmsMemberReceiveAddressService;
import com.fante.common.utils.text.Convert;
import org.springframework.util.ObjectUtils;

/**
 * 会员收货地址Service业务层处理
 *
 * @author fante
 * @date 2020-04-10
 */
@Service
public class UmsMemberReceiveAddressServiceImpl implements IUmsMemberReceiveAddressService {

    private static Logger log = LoggerFactory.getLogger(UmsMemberReceiveAddressServiceImpl.class);

    @Autowired
    private UmsMemberReceiveAddressMapper umsMemberReceiveAddressMapper;

    /**
     * 查询会员收货地址
     *
     * @param id 会员收货地址ID
     * @return 会员收货地址
     */
    @Override
    public UmsMemberReceiveAddress selectUmsMemberReceiveAddressById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        return umsMemberReceiveAddressMapper.selectUmsMemberReceiveAddressById(id);
    }

    /**
     * 查询会员收货地址列表
     *
     * @param umsMemberReceiveAddress 会员收货地址
     * @return 会员收货地址集合
     */
    @Override
    public List<UmsMemberReceiveAddress> selectUmsMemberReceiveAddressList(UmsMemberReceiveAddress umsMemberReceiveAddress) {
        return umsMemberReceiveAddressMapper.getUmsMemberReceiveAddressList(umsMemberReceiveAddress);
    }

    /**
     * 查询会员收货地址列表
     *
     * @param umsMemberReceiveAddress 会员收货地址
     * @return 会员收货地址集合
     */
    @Override
    public int countMemberAddress(UmsMemberReceiveAddress umsMemberReceiveAddress) {
        return umsMemberReceiveAddressMapper.countMemberAddress(umsMemberReceiveAddress);
    }

    /**
     * 查询会员收货地址数量
     *
     * @param umsMemberReceiveAddress 会员收货地址
     * @return 结果
     */
    @Override
    public int countUmsMemberReceiveAddress(UmsMemberReceiveAddress umsMemberReceiveAddress){
        return umsMemberReceiveAddressMapper.countUmsMemberReceiveAddress(umsMemberReceiveAddress);
    }

    /**
     * 唯一校验
     *
     * @param umsMemberReceiveAddress 会员收货地址
     * @return 结果
     */
    @Override
    public String checkUmsMemberReceiveAddressUnique(UmsMemberReceiveAddress umsMemberReceiveAddress) {
        return umsMemberReceiveAddressMapper.checkUmsMemberReceiveAddressUnique(umsMemberReceiveAddress) > 0 ? Constants.NOT_UNIQUE : Constants.UNIQUE;
    }

    /**
     * 新增会员收货地址
     *
     * @param umsMemberReceiveAddress 会员收货地址
     * @return 新增会员收货地址数量
     */
    @Override
    public int insertUmsMemberReceiveAddress(UmsMemberReceiveAddress umsMemberReceiveAddress) {
        //验证地址参数是否完整
        validateAddress(umsMemberReceiveAddress);
        umsMemberReceiveAddress.setCreateTime(DateUtils.getNowDate());
        int i = umsMemberReceiveAddressMapper.insertUmsMemberReceiveAddress(umsMemberReceiveAddress);
        if(StringUtils.equals(umsMemberReceiveAddress.getStatus(), UmsAddressConst.DEFAULT)){
            umsMemberReceiveAddressMapper.setDefaultAddress(umsMemberReceiveAddress.getId(),umsMemberReceiveAddress.getMemberId());
        }
        return i ;
    }

    /**
     * 验证地址参数是否完整
     * @param address
     */
    private void validateAddress(UmsMemberReceiveAddress address){
        Checker.check(StringUtils.isEmpty(address.getProvince()),"地址参数缺失");
        Checker.check(StringUtils.isEmpty(address.getCity()),"地址参数缺失");
        Checker.check(StringUtils.isEmpty(address.getRegion()),"地址参数缺失");
        Checker.check(StringUtils.isEmpty(address.getDetailAddress()),"地址参数缺失");
        Checker.check(StringUtils.isEmpty(address.getPhoneNumber()),"电话号码缺失");
        Checker.check(StringUtils.isEmpty(address.getName()),"姓名参数缺失");
    }
    /**
     * 修改会员收货地址
     *
     * @param umsMemberReceiveAddress 会员收货地址
     * @return 修改会员收货地址数量
     */
    @Override
    public int updateUmsMemberReceiveAddress(UmsMemberReceiveAddress umsMemberReceiveAddress) {
        //验证地址参数是否完整
        validateAddress(umsMemberReceiveAddress);
        umsMemberReceiveAddress.setUpdateTime(DateUtils.getNowDate());
        int i = umsMemberReceiveAddressMapper.updateUmsMemberReceiveAddress(umsMemberReceiveAddress);
        if(StringUtils.equals(umsMemberReceiveAddress.getStatus(), UmsAddressConst.DEFAULT)){
            umsMemberReceiveAddressMapper.setDefaultAddress(umsMemberReceiveAddress.getId(),umsMemberReceiveAddress.getMemberId());
        }
        return i;
    }

    /**
     * 删除会员收货地址对象
     *
     * @param ids 需要删除的数据ID
     * @return 删除会员收货地址数量
     */
    @Override
    public int deleteUmsMemberReceiveAddressByIds(String ids) {
        return umsMemberReceiveAddressMapper.deleteUmsMemberReceiveAddressByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除会员收货地址信息
     *
     * @return 删除会员收货地址数量
     */
    @Override
    public int deleteUmsMemberReceiveAddressById(Long addressId,Long memberId) {
        return umsMemberReceiveAddressMapper.realDelUmsMemberReceiveAddressById(addressId,memberId);
    }

    /**
     * 设置默认地址
     * @param addressId
     * @param memberId
     * @return
     */
    @Override
    public int setDefaultAddress(Long addressId, Long memberId) {
        UmsMemberReceiveAddress address = umsMemberReceiveAddressMapper.selectUmsMemberReceiveAddressById(addressId);
        Checker.check(ObjectUtils.isEmpty(address),"此默认地址不存在");
        Checker.checkOp(Objects.equals(address.getMemberId(),memberId),"默认地址不存在");
        return umsMemberReceiveAddressMapper.setDefaultAddress(addressId,memberId);
    }
}
