package com.fante.project.business.omsOrderOperateHistory.service.impl;

import java.util.Date;
import java.util.List;

import com.fante.common.business.enums.OrderConst;
import com.fante.common.utils.Checker;
import com.fante.common.utils.DateUtils;
import com.fante.common.constant.Constants;
import com.fante.common.utils.security.ShiroUtils;
import com.fante.project.business.omsOrder.domain.OmsOrder;
import com.fante.project.business.omsOrderOperateHistory.domain.OmsOrderOperateHistoryParam;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fante.project.business.omsOrderOperateHistory.mapper.OmsOrderOperateHistoryMapper;
import com.fante.project.business.omsOrderOperateHistory.domain.OmsOrderOperateHistory;
import com.fante.project.business.omsOrderOperateHistory.service.IOmsOrderOperateHistoryService;
import com.fante.common.utils.text.Convert;
import org.springframework.util.ObjectUtils;

/**
 * 订单操作历史记录Service业务层处理
 *
 * @author fante
 * @date 2020-04-02
 */
@Service
public class OmsOrderOperateHistoryServiceImpl implements IOmsOrderOperateHistoryService {

    private static Logger log = LoggerFactory.getLogger(OmsOrderOperateHistoryServiceImpl.class);

    @Autowired
    private OmsOrderOperateHistoryMapper omsOrderOperateHistoryMapper;

    /**
     * 查询订单操作历史记录
     *
     * @param id 订单操作历史记录ID
     * @return 订单操作历史记录
     */
    @Override
    public OmsOrderOperateHistory selectOmsOrderOperateHistoryById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        return omsOrderOperateHistoryMapper.selectOmsOrderOperateHistoryById(id);
    }

    /**
     * 查询订单操作历史记录列表
     *
     * @param omsOrderOperateHistory 订单操作历史记录
     * @return 订单操作历史记录集合
     */
    @Override
    public List<OmsOrderOperateHistory> selectOmsOrderOperateHistoryList(OmsOrderOperateHistory omsOrderOperateHistory) {
        return omsOrderOperateHistoryMapper.selectOmsOrderOperateHistoryList(omsOrderOperateHistory);
    }

    /**
     * 查询订单操作历史记录数量
     *
     * @param omsOrderOperateHistory 订单操作历史记录
     * @return 结果
     */
    @Override
    public int countOmsOrderOperateHistory(OmsOrderOperateHistory omsOrderOperateHistory){
        return omsOrderOperateHistoryMapper.countOmsOrderOperateHistory(omsOrderOperateHistory);
    }

    /**
     * 唯一校验
     *
     * @param omsOrderOperateHistory 订单操作历史记录
     * @return 结果
     */
    @Override
    public String checkOmsOrderOperateHistoryUnique(OmsOrderOperateHistory omsOrderOperateHistory) {
        return omsOrderOperateHistoryMapper.checkOmsOrderOperateHistoryUnique(omsOrderOperateHistory) > 0 ? Constants.NOT_UNIQUE : Constants.UNIQUE;
    }

    /**
     * 新增订单操作历史记录
     *
     * @param omsOrderOperateHistory 订单操作历史记录
     * @return 新增订单操作历史记录数量
     */
    @Override
    public int insertOmsOrderOperateHistory(OmsOrderOperateHistory omsOrderOperateHistory) {
        omsOrderOperateHistory.setCreateTime(DateUtils.getNowDate());
        return omsOrderOperateHistoryMapper.insertOmsOrderOperateHistory(omsOrderOperateHistory);
    }
    /**
     * 新增订单操作历史记录通过订单及操作
     *
     * @param param 订单操作历史记录
     * @return 新增订单操作历史记录数量
     */
    @Override
    public int insertOmsOrderOperateHistory(OmsOrderOperateHistoryParam param) {
        OrderConst.OrderActionEnum action = param.getAction();
        OmsOrder order = param.getOrder();
        Checker.check(ObjectUtils.isEmpty(order)||ObjectUtils.isEmpty(action),"生成操作记录时，参数缺失。");
        OmsOrderOperateHistory history = new OmsOrderOperateHistory();
        history.setOrderId(order.getId());
        history.setCreateTime(new Date());
        history.setCreateBy(ShiroUtils.getLoginName());
        history.setOrderStatus(order.getStatus());
        history.setPayStatus(order.getPayStatus());
        history.setSendStatus(OrderConst.SendStatus.getByOrderStatus(order.getStatus()).getType());
        history.setRemark(action.getDescribe());
        return omsOrderOperateHistoryMapper.insertOmsOrderOperateHistory(history);
    }

    /**
     * 修改订单操作历史记录
     *
     * @param omsOrderOperateHistory 订单操作历史记录
     * @return 修改订单操作历史记录数量
     */
    @Override
    public int updateOmsOrderOperateHistory(OmsOrderOperateHistory omsOrderOperateHistory) {
        omsOrderOperateHistory.setUpdateTime(DateUtils.getNowDate());
        return omsOrderOperateHistoryMapper.updateOmsOrderOperateHistory(omsOrderOperateHistory);
    }

    /**
     * 删除订单操作历史记录对象
     *
     * @param ids 需要删除的数据ID
     * @return 删除订单操作历史记录数量
     */
    @Override
    public int deleteOmsOrderOperateHistoryByIds(String ids) {
        return omsOrderOperateHistoryMapper.deleteOmsOrderOperateHistoryByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除订单操作历史记录信息
     *
     * @param id 订单操作历史记录ID
     * @return 删除订单操作历史记录数量
     */
    @Override
    public int deleteOmsOrderOperateHistoryById(Long id) {
        return omsOrderOperateHistoryMapper.deleteOmsOrderOperateHistoryById(id);
    }
    /**
     * 批量插入
     * @param operateHistoryList
     * @return
     */
    @Override
    public int batchInsertOmsOrderOperateHistory(List<OmsOrderOperateHistory> operateHistoryList) {
        return omsOrderOperateHistoryMapper.batchInsertOmsOrderOperateHistory(operateHistoryList);
    }
}
