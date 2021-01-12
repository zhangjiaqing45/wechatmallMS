package com.fante.project.business.omsPayOrder.service.impl;

import java.util.List;
import com.fante.common.utils.DateUtils;
import com.fante.common.constant.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fante.project.business.omsPayOrder.mapper.OmsPayOrderMapper;
import com.fante.project.business.omsPayOrder.domain.OmsPayOrder;
import com.fante.project.business.omsPayOrder.service.IOmsPayOrderService;
import com.fante.common.utils.text.Convert;
import org.springframework.util.ObjectUtils;

/**
 * 支付订单Service业务层处理
 *
 * @author fante
 * @date 2020-04-18
 */
@Service
public class OmsPayOrderServiceImpl implements IOmsPayOrderService {

    private static Logger log = LoggerFactory.getLogger(OmsPayOrderServiceImpl.class);

    @Autowired
    private OmsPayOrderMapper omsPayOrderMapper;

    /**
     * 查询支付订单
     *
     * @param id 支付订单ID
     * @return 支付订单
     */
    @Override
    public OmsPayOrder selectOmsPayOrderById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        return omsPayOrderMapper.selectOmsPayOrderById(id);
    }

    /**
     * 查询支付订单列表
     *
     * @param omsPayOrder 支付订单
     * @return 支付订单集合
     */
    @Override
    public List<OmsPayOrder> selectOmsPayOrderList(OmsPayOrder omsPayOrder) {
        return omsPayOrderMapper.selectOmsPayOrderList(omsPayOrder);
    }

    /**
     * 查询支付订单数量
     *
     * @param omsPayOrder 支付订单
     * @return 结果
     */
    @Override
    public int countOmsPayOrder(OmsPayOrder omsPayOrder){
        return omsPayOrderMapper.countOmsPayOrder(omsPayOrder);
    }

    /**
     * 唯一校验
     *
     * @param omsPayOrder 支付订单
     * @return 结果
     */
    @Override
    public String checkOmsPayOrderUnique(OmsPayOrder omsPayOrder) {
        return omsPayOrderMapper.checkOmsPayOrderUnique(omsPayOrder) > 0 ? Constants.NOT_UNIQUE : Constants.UNIQUE;
    }

    /**
     * 新增支付订单
     *
     * @param omsPayOrder 支付订单
     * @return 新增支付订单数量
     */
    @Override
    public int insertOmsPayOrder(OmsPayOrder omsPayOrder) {
        omsPayOrder.setCreateTime(DateUtils.getNowDate());
        return omsPayOrderMapper.insertOmsPayOrder(omsPayOrder);
    }

    /**
     * 修改支付订单
     *
     * @param omsPayOrder 支付订单
     * @return 修改支付订单数量
     */
    @Override
    public int updateOmsPayOrder(OmsPayOrder omsPayOrder) {
        omsPayOrder.setUpdateTime(DateUtils.getNowDate());
        return omsPayOrderMapper.updateOmsPayOrder(omsPayOrder);
    }

    /**
     * 删除支付订单对象
     *
     * @param ids 需要删除的数据ID
     * @return 删除支付订单数量
     */
    @Override
    public int deleteOmsPayOrderByIds(String ids) {
        return omsPayOrderMapper.deleteOmsPayOrderByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除支付订单信息
     *
     * @param id 支付订单ID
     * @return 删除支付订单数量
     */
    @Override
    public int deleteOmsPayOrderById(Long id) {
        return omsPayOrderMapper.deleteOmsPayOrderById(id);
    }

    /**
     * 修改支付订单支付状态为已经支付
     * @param payOrderSn
     * @return
     */
    @Override
    public int paySuccess(String payOrderSn) {
        return omsPayOrderMapper.paySuccess(payOrderSn);
    }
    /**
     * 查询支付订单
     * @param payOrderSn
     * @return
     */
    @Override
    public OmsPayOrder selectOmsPayOrderByOrderSn(String payOrderSn) {
        return omsPayOrderMapper.selectOmsPayOrderByOrderSn(payOrderSn, "");
    }

    /**
     * 查询支付订单
     * @param payOrderSn
     * @param status
     * @return
     */
    @Override
    public OmsPayOrder selectOmsPayOrderByOrderSn(String payOrderSn, String status) {
        return omsPayOrderMapper.selectOmsPayOrderByOrderSn(payOrderSn, status);
    }
}
