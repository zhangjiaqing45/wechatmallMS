package com.fante.project.business.omsOrderReturnAddress.service.impl;

import java.util.List;

import com.fante.common.business.enums.CommonUse;
import com.fante.common.utils.Checker;
import com.fante.common.utils.DateUtils;
import com.fante.common.constant.Constants;
import com.fante.common.utils.StringUtils;
import com.fante.common.utils.security.ShiroUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fante.project.business.omsOrderReturnAddress.mapper.OmsOrderReturnAddressMapper;
import com.fante.project.business.omsOrderReturnAddress.domain.OmsOrderReturnAddress;
import com.fante.project.business.omsOrderReturnAddress.service.IOmsOrderReturnAddressService;
import com.fante.common.utils.text.Convert;
import org.springframework.util.ObjectUtils;

/**
 * 店铺收发货地址Service业务层处理
 *
 * @author fante
 * @date 2020-03-31
 */
@Service
public class OmsOrderReturnAddressServiceImpl implements IOmsOrderReturnAddressService {

    private static Logger log = LoggerFactory.getLogger(OmsOrderReturnAddressServiceImpl.class);

    @Autowired
    private OmsOrderReturnAddressMapper omsOrderReturnAddressMapper;

    /**
     * 查询店铺收发货地址
     *
     * @param id 店铺收发货地址ID
     * @return 店铺收发货地址
     */
    @Override
    public OmsOrderReturnAddress selectOmsOrderReturnAddressById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        return omsOrderReturnAddressMapper.selectOmsOrderReturnAddressById(id);
    }

    /**
     * 查询店铺收发货地址列表
     *
     * @param omsOrderReturnAddress 店铺收发货地址
     * @return 店铺收发货地址集合
     */
    @Override
    public List<OmsOrderReturnAddress> selectOmsOrderReturnAddressList(OmsOrderReturnAddress omsOrderReturnAddress) {
        return omsOrderReturnAddressMapper.selectOmsOrderReturnAddressList(omsOrderReturnAddress);
    }

    /**
     * 查询店铺收发货地址数量
     *
     * @param omsOrderReturnAddress 店铺收发货地址
     * @return 结果
     */
    @Override
    public int countOmsOrderReturnAddress(OmsOrderReturnAddress omsOrderReturnAddress){
        return omsOrderReturnAddressMapper.countOmsOrderReturnAddress(omsOrderReturnAddress);
    }

    /**
     * 唯一校验
     *
     * @param omsOrderReturnAddress 店铺收发货地址
     * @return 结果
     */
    @Override
    public String checkOmsOrderReturnAddressUnique(OmsOrderReturnAddress omsOrderReturnAddress) {
        return omsOrderReturnAddressMapper.checkOmsOrderReturnAddressUnique(omsOrderReturnAddress) > 0 ? Constants.NOT_UNIQUE : Constants.UNIQUE;
    }

    /**
     * 新增店铺收发货地址
     *
     * @param omsOrderReturnAddress 店铺收发货地址
     * @return 新增店铺收发货地址数量
     */
    @Override
    public int insertOmsOrderReturnAddress(OmsOrderReturnAddress omsOrderReturnAddress) {
        //验证
        ValidateParams(omsOrderReturnAddress);
        omsOrderReturnAddress.setShopId(ShiroUtils.getSysUser().getDeptId());
        omsOrderReturnAddress.setCreateTime(DateUtils.getNowDate());
        omsOrderReturnAddress.setCreateBy(ShiroUtils.getLoginName());
        int count = omsOrderReturnAddressMapper.insertOmsOrderReturnAddress(omsOrderReturnAddress);
        //设置默认收(发)货地址
        setDefaultSendStatus(omsOrderReturnAddress);
        return count;
    }

    /**
     * 设置默认收(发)货地址
     * @return
     */
    private void setDefaultSendStatus(OmsOrderReturnAddress omsOrderReturnAddress){
        String sendStatus = omsOrderReturnAddress.getSendStatus();
        String receiveStatus = omsOrderReturnAddress.getReceiveStatus();
        //新插入的地址设为默认发送地址
        if(StringUtils.equals(sendStatus, CommonUse.Status.ENABLE.getType())){
            //取消所有默认发送地址
            sendEnable(omsOrderReturnAddress.getId());
        }
        //新插入的地址设为默认接受地址
        if(StringUtils.equals(receiveStatus, CommonUse.Status.ENABLE.getType())){
            //取消所有默认接受地址
            receiveEnable(omsOrderReturnAddress.getId());
        }
    }

    /**
     * 验证参数
     * @param omsOrderReturnAddress
     */
    private void ValidateParams(OmsOrderReturnAddress omsOrderReturnAddress){
        Checker.check(StringUtils.isEmpty(omsOrderReturnAddress.getName()),"缺少地址名称信息.");
        Checker.check(StringUtils.isEmpty(omsOrderReturnAddress.getPersionName()),"缺少联系人名称信息.");
        Checker.check(StringUtils.isEmpty(omsOrderReturnAddress.getProvince()),"缺少地址信息.");
        Checker.check(StringUtils.isEmpty(omsOrderReturnAddress.getCity()),"缺少地址信息.");
        Checker.check(StringUtils.isEmpty(omsOrderReturnAddress.getRegion()),"缺少地址信息.");
        Checker.check(StringUtils.isEmpty(omsOrderReturnAddress.getDetailAddress()),"缺少地址详细信息.");
        String exist = checkOmsOrderReturnAddressUnique(omsOrderReturnAddress);
        Checker.checkOp(StringUtils.equals(exist,Constants.UNIQUE),"地址名称已存在.");
    }
    /**
     * 修改店铺收发货地址
     *
     * @param omsOrderReturnAddress 店铺收发货地址
     * @return 修改店铺收发货地址数量
     */
    @Override
    public int updateOmsOrderReturnAddress(OmsOrderReturnAddress omsOrderReturnAddress) {
        //验证
        Checker.check(ObjectUtils.isEmpty(omsOrderReturnAddress.getId()),"未找到修改的地址.");
        ValidateParams(omsOrderReturnAddress);
        omsOrderReturnAddress.setUpdateBy(ShiroUtils.getLoginName());
        omsOrderReturnAddress.setUpdateTime(DateUtils.getNowDate());
        return omsOrderReturnAddressMapper.updateOmsOrderReturnAddress(omsOrderReturnAddress);
    }

    /**
     * 删除店铺收发货地址对象
     *
     * @param ids 需要删除的数据ID
     * @return 删除店铺收发货地址数量
     */
    @Override
    public int deleteOmsOrderReturnAddressByIds(String ids) {
        //真实删除
        return omsOrderReturnAddressMapper.realDelOmsOrderReturnAddressByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除店铺收发货地址信息
     *
     * @param id 店铺收发货地址ID
     * @return 删除店铺收发货地址数量
     */
    @Override
    public int deleteOmsOrderReturnAddressById(Long id) {
        return omsOrderReturnAddressMapper.deleteOmsOrderReturnAddressById(id);
    }
    /**
     * 设置默认发货地址
     * @param id
     * @return
     */
    @Override
    public int sendEnable(Long id) {
        OmsOrderReturnAddress oldAddress = selectOmsOrderReturnAddressById(id);
        Checker.check(ObjectUtils.isEmpty(oldAddress),"指定的默认地址不存在！");
        return omsOrderReturnAddressMapper.setSendEnable(id,ShiroUtils.getSysUser().getDeptId());
    }
    /**
     * 设置默认收货地址
     * @param id
     * @return
     */
    @Override
    public int receiveEnable(Long id) {
        OmsOrderReturnAddress oldAddress = selectOmsOrderReturnAddressById(id);
        Checker.check(ObjectUtils.isEmpty(oldAddress),"指定的默认地址不存在！");
        return omsOrderReturnAddressMapper.setReceiveEnable(id,ShiroUtils.getSysUser().getDeptId());
    }
}
