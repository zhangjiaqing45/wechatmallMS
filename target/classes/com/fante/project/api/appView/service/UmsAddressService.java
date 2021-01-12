package com.fante.project.api.appView.service;

import com.fante.common.business.enums.UmsAddressConst;
import com.fante.common.utils.Checker;
import com.fante.project.business.omsOrder.service.IOmsOrderService;
import com.fante.project.business.umsMemberReceiveAddress.domain.UmsMemberReceiveAddress;
import com.fante.project.business.umsMemberReceiveAddress.service.IUmsMemberReceiveAddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Objects;

/**
 * @program: Fante
 * @Date: 2020/4/22 10:56
 * @Author: Mr.Z
 * @Description: 前端推荐商品相关处理服务
 */
@Service
public class UmsAddressService {

    private static Logger log = LoggerFactory.getLogger(UmsAddressService.class);
    /**
     * 用户地址
     */
    @Autowired
    private IUmsMemberReceiveAddressService iUmsMemberReceiveAddressService;

    /**
     * 获取用户所有地址
     * @param memberId
     * @return
     */
    public List<UmsMemberReceiveAddress> getMemberAddressList(Long memberId){
        UmsMemberReceiveAddress adderssParam = new UmsMemberReceiveAddress();
        adderssParam.setMemberId(memberId);
        return iUmsMemberReceiveAddressService.selectUmsMemberReceiveAddressList(adderssParam);
    }

    /**
     * 添加收货地址
     * @param address
     * @return
     */
    public int addReceiveAddress(UmsMemberReceiveAddress address){
        return iUmsMemberReceiveAddressService.insertUmsMemberReceiveAddress(address);
    }

    /**
     * 删除默认地址
     * @param addressId
     * @param memberId
     * @return
     */
    public int delReceiveAddress(Long addressId,Long memberId){
        return iUmsMemberReceiveAddressService.deleteUmsMemberReceiveAddressById(addressId,memberId);
    }

    /**
     * 设置默认地址
     * @param addressId
     * @param memberId
     * @return
     */
    public int setDefaultAddress(Long addressId,Long memberId){
        return iUmsMemberReceiveAddressService.setDefaultAddress(addressId,memberId);
    }

    /**
     * 编辑地址
     * @param param
     * @return
     */
    public int editReceiveAddress(UmsMemberReceiveAddress param) {
        return iUmsMemberReceiveAddressService.updateUmsMemberReceiveAddress(param);
    }

    /**
     *
     * @param id
     * @param memberId
     * @return
     */
    public UmsMemberReceiveAddress getAddress(Long id, Long memberId) {
        UmsMemberReceiveAddress address = iUmsMemberReceiveAddressService.selectUmsMemberReceiveAddressById(id);
        Checker.check(ObjectUtils.isEmpty(address),"地址不存在！");
        Checker.checkOp(Objects.equals(address.getMemberId(),memberId),"地址不存在");
        return address;
    }

    /**
     * 获取用户默认地址
     * @param memberId
     * @return
     */
    public UmsMemberReceiveAddress getDefAddr(Long memberId) {
        UmsMemberReceiveAddress adderssParam = new UmsMemberReceiveAddress();
        adderssParam.setMemberId(memberId);
        adderssParam.setStatus(UmsAddressConst.DEFAULT);
        List<UmsMemberReceiveAddress> defalutList = iUmsMemberReceiveAddressService.selectUmsMemberReceiveAddressList(adderssParam);
        if (ObjectUtils.isEmpty(defalutList)) {
            return null;
        }
        return defalutList.get(0);
    }
}
