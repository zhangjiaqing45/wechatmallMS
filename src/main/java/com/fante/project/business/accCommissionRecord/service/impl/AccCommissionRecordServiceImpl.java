package com.fante.project.business.accCommissionRecord.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.fante.common.business.enums.AccRecordConst;
import com.fante.common.utils.Checker;
import com.fante.common.utils.DateUtils;
import com.fante.common.constant.Constants;
import com.fante.project.business.accCommissionRecord.domain.AccCommissionRecordDTO;
import com.fante.project.business.omsOrder.domain.OmsOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fante.project.business.accCommissionRecord.mapper.AccCommissionRecordMapper;
import com.fante.project.business.accCommissionRecord.domain.AccCommissionRecord;
import com.fante.project.business.accCommissionRecord.service.IAccCommissionRecordService;
import com.fante.common.utils.text.Convert;
import org.springframework.util.ObjectUtils;

/**
 * 用户佣金记录Service业务层处理
 *
 * @author fante
 * @date 2020-05-07
 */
@Service
public class AccCommissionRecordServiceImpl implements IAccCommissionRecordService {

    private static Logger log = LoggerFactory.getLogger(AccCommissionRecordServiceImpl.class);

    @Autowired
    private AccCommissionRecordMapper accCommissionRecordMapper;

    /**
     * 查询用户佣金记录
     *
     * @param id 用户佣金记录ID
     * @return 用户佣金记录
     */
    @Override
    public AccCommissionRecord selectAccCommissionRecordById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        return accCommissionRecordMapper.selectAccCommissionRecordById(id);
    }

    /**
     * 查询用户佣金记录列表
     *
     * @param accCommissionRecord 用户佣金记录
     * @return 用户佣金记录集合
     */
    @Override
    public List<AccCommissionRecord> selectAccCommissionRecordList(AccCommissionRecord accCommissionRecord) {
        return accCommissionRecordMapper.selectAccCommissionRecordList(accCommissionRecord);
    }

    /**
     * 查询用户佣金记录数量
     *
     * @param accCommissionRecord 用户佣金记录
     * @return 结果
     */
    @Override
    public int countAccCommissionRecord(AccCommissionRecord accCommissionRecord){
        return accCommissionRecordMapper.countAccCommissionRecord(accCommissionRecord);
    }

    /**
     * 唯一校验
     *
     * @param accCommissionRecord 用户佣金记录
     * @return 结果
     */
    @Override
    public String checkAccCommissionRecordUnique(AccCommissionRecord accCommissionRecord) {
        return accCommissionRecordMapper.checkAccCommissionRecordUnique(accCommissionRecord) > 0 ? Constants.NOT_UNIQUE : Constants.UNIQUE;
    }

    /**
     * 新增用户佣金记录
     *
     * @param accCommissionRecord 用户佣金记录
     * @return 新增用户佣金记录数量
     */
    @Override
    public int insertAccCommissionRecord(AccCommissionRecord accCommissionRecord) {
        accCommissionRecord.setCreateTime(DateUtils.getNowDate());
        return accCommissionRecordMapper.insertAccCommissionRecord(accCommissionRecord);
    }

    /**
     * 修改用户佣金记录
     *
     * @param accCommissionRecord 用户佣金记录
     * @return 修改用户佣金记录数量
     */
    @Override
    public int updateAccCommissionRecord(AccCommissionRecord accCommissionRecord) {
        accCommissionRecord.setUpdateTime(DateUtils.getNowDate());
        return accCommissionRecordMapper.updateAccCommissionRecord(accCommissionRecord);
    }

    /**
     * 删除用户佣金记录对象
     *
     * @param ids 需要删除的数据ID
     * @return 删除用户佣金记录数量
     */
    @Override
    public int deleteAccCommissionRecordByIds(String ids) {
        return accCommissionRecordMapper.deleteAccCommissionRecordByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除用户佣金记录信息
     *
     * @param id 用户佣金记录ID
     * @return 删除用户佣金记录数量
     */
    @Override
    public int deleteAccCommissionRecordById(Long id) {
        return accCommissionRecordMapper.deleteAccCommissionRecordById(id);
    }

    /**
     * 修改用户待入佣金
     * 以后购买后产生佣金-->产生佣金
     */
    @Override
    public int addBrokerage(AccCommissionRecord accCommissionRecord) {
        //验证参数
        validateParam(accCommissionRecord,AccRecordConst.CommissionOperation.GET);
        //用户商品购买,转入金额
        accCommissionRecord.setDescription(AccRecordConst.CommissionDescribe.GET_COMMISSION);
        //待入佣金
        accCommissionRecord.setStatus(AccRecordConst.CommissionStatus.COMMISSION.getType());
        //获得佣金
        accCommissionRecord.setOperation(AccRecordConst.CommissionOperation.GET.getType());
        return insertAccCommissionRecord(accCommissionRecord);
    }
    /**
     * 修改用户待入佣金
     * 退货-->扣减佣金
     */
    @Override
    public int subtrackBrokerage(AccCommissionRecord accCommissionRecord) {
        //验证参数 退货扣减佣金
        validateParam(accCommissionRecord,AccRecordConst.CommissionOperation.DEDUCT);
        //用户商品购买, 退货扣减佣金
        accCommissionRecord.setDescription(AccRecordConst.CommissionDescribe.RETURN_COMMISSION);
        //待入佣金
        accCommissionRecord.setStatus(AccRecordConst.CommissionStatus.COMMISSION.getType());
        //扣减佣金
        accCommissionRecord.setOperation(AccRecordConst.CommissionOperation.DEDUCT.getType());
        return insertAccCommissionRecord(accCommissionRecord);
    }
    /**
     * 修改用户待入佣金
     * 转入余额-->扣减佣金
     */
    @Override
    public int exchangeBrokerageToBalance(AccCommissionRecord accCommissionRecord) {
        //验证参数
        validateParam(accCommissionRecord,AccRecordConst.CommissionOperation.EXCHANGE);
        //用户商品购买,转入金额
        accCommissionRecord.setDescription(AccRecordConst.CommissionDescribe.EXCHANGE_COMMISSION);
        //待入佣金
        accCommissionRecord.setStatus(AccRecordConst.CommissionStatus.CASH.getType());
        //扣减佣金
        accCommissionRecord.setOperation(AccRecordConst.CommissionOperation.EXCHANGE.getType());
        return insertAccCommissionRecord(accCommissionRecord);
    }
    /**
     * 修改用户待入佣金
     * 待入佣金-->可提现佣金
     */
    @Override
    public int exhcangeBrokerage(Long orderId) {
        return accCommissionRecordMapper.exchangeCommissionRecordStatus(orderId,AccRecordConst.CommissionStatus.CASH.getType());
    }
    /**
     * 核算用户待入佣金
     */
    @Override
    public BigDecimal sumWaitBrokerage(Long memberId){
        return accCommissionRecordMapper.sumBrokerage( memberId,AccRecordConst.CommissionStatus.COMMISSION.getType());
    }
    /**
     * 核算用户可提现佣金
     */
    @Override
    public BigDecimal sumCashBrokerage(Long memberId){
        return  accCommissionRecordMapper.sumBrokerage( memberId,AccRecordConst.CommissionStatus.CASH.getType());
    }

    /**
     * 查询佣金记录 通过订单和 操作类型
     * @param orderId
     * @param operation 0:获得佣金 1:退货 2:转入余额
     * @return
     */
    @Override
    public AccCommissionRecord getCommissionRecordByMemberIdAndOperation(Long orderId, String operation) {
        return  accCommissionRecordMapper.getCommissionRecordByMemberIdAndOperation( orderId,operation);
    }

    /**
     * 验证参数
     * @param accCommissionRecord
     */
    private void validateParam(AccCommissionRecord accCommissionRecord,AccRecordConst.CommissionOperation action){
        Checker.check(ObjectUtils.isEmpty(accCommissionRecord.getMoney()),"佣金参数缺失");
        Checker.check(ObjectUtils.isEmpty(accCommissionRecord.getMemberId()),"用户信息参数缺失");
        switch (action){
            case GET:
                Checker.check(ObjectUtils.isEmpty(accCommissionRecord.getOrderId()),"订单参数缺失");
                //获得佣金
            case EXCHANGE:
                //转入余额
                break;
            default:
                //退货佣金扣除
                Checker.check(ObjectUtils.isEmpty(accCommissionRecord.getOrderId()),"订单参数缺失");
                Checker.check(ObjectUtils.isEmpty(accCommissionRecord.getApplyId()),"退货服务单号参数缺失");
                break;
        }
    }
    /**
     * 批量处理佣金 -->待入佣金改为佣金
     * @param orderList
     * @return
     */
    @Override
    public int batchUpdateCommissionStatus(List<OmsOrder> orderList) {
        return accCommissionRecordMapper.batchUpdateCommissionStatus(orderList);
    }
    /**
     * 处理佣金 -->待入佣金改为佣金
     * @param order
     * @return
     */
    @Override
    public int updateCommissionStatus(Long order){
        return accCommissionRecordMapper.updateCommissionStatus(order);
    }
    /**
     * 根据订单查询佣金记录
     * @param ids 订单ids
     * @return
     */
    @Override
    public List<AccCommissionRecord> selectAccCommissionRecordListByOrderIds(String ids) {
        return accCommissionRecordMapper.selectAccCommissionRecordListByOrderIds(Convert.toStrArray(ids));
    }

    /**
     * 佣金明细与用户信息关联查询
     * @param dto
     * @return
     */
    @Override
    public List<AccCommissionRecordDTO> selectJoinList(AccCommissionRecordDTO dto) {
        return accCommissionRecordMapper.selectJoinList(dto);
    }
    /**
     *付款未发货取消订单
     * @param id
     * @return
     */
    @Override
    public int delRecourdByOrderId(Long id) {
        return accCommissionRecordMapper.delRecourdByOrderId(id);
    }
}
