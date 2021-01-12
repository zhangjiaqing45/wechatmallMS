package com.fante.project.business.accAccountRecord.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.fante.common.business.constant.BizConstants;
import com.fante.common.business.enums.AccRecordConst;
import com.fante.common.utils.Checker;
import com.fante.common.utils.DateUtils;
import com.fante.common.constant.Constants;
import com.fante.project.business.accAccountRecord.domain.AccAccountRecordDTO;
import com.fante.project.business.accCommissionRecord.domain.AccCommissionRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fante.project.business.accAccountRecord.mapper.AccAccountRecordMapper;
import com.fante.project.business.accAccountRecord.domain.AccAccountRecord;
import com.fante.project.business.accAccountRecord.service.IAccAccountRecordService;
import com.fante.common.utils.text.Convert;
import org.springframework.util.ObjectUtils;

/**
 * 账户出入账记录Service业务层处理
 *
 * @author fante
 * @date 2020-05-07
 */
@Service
public class AccAccountRecordServiceImpl implements IAccAccountRecordService {

    private static Logger log = LoggerFactory.getLogger(AccAccountRecordServiceImpl.class);

    @Autowired
    private AccAccountRecordMapper accAccountRecordMapper;

    /**
     * 查询账户出入账记录
     *
     * @param id 账户出入账记录ID
     * @return 账户出入账记录
     */
    @Override
    public AccAccountRecord selectAccAccountRecordById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        return accAccountRecordMapper.selectAccAccountRecordById(id);
    }

    /**
     * 查询账户出入账记录列表
     *
     * @param accAccountRecord 账户出入账记录
     * @return 账户出入账记录集合
     */
    @Override
    public List<AccAccountRecord> selectAccAccountRecordList(AccAccountRecord accAccountRecord) {
        return accAccountRecordMapper.selectAccAccountRecordList(accAccountRecord);
    }

    /**
     * 查询账户出入账记录数量
     *
     * @param accAccountRecord 账户出入账记录
     * @return 结果
     */
    @Override
    public int countAccAccountRecord(AccAccountRecord accAccountRecord){
        return accAccountRecordMapper.countAccAccountRecord(accAccountRecord);
    }

    /**
     * 唯一校验
     *
     * @param accAccountRecord 账户出入账记录
     * @return 结果
     */
    @Override
    public String checkAccAccountRecordUnique(AccAccountRecord accAccountRecord) {
        return accAccountRecordMapper.checkAccAccountRecordUnique(accAccountRecord) > 0 ? Constants.NOT_UNIQUE : Constants.UNIQUE;
    }

    /**
     * 新增账户出入账记录
     *
     * @param accAccountRecord 账户出入账记录
     * @return 新增账户出入账记录数量
     */
    @Override
    public int insertAccAccountRecord(AccAccountRecord accAccountRecord) {
        accAccountRecord.setCreateTime(DateUtils.getNowDate());
        return accAccountRecordMapper.insertAccAccountRecord(accAccountRecord);
    }

    /**
     * 修改账户出入账记录
     *
     * @param accAccountRecord 账户出入账记录
     * @return 修改账户出入账记录数量
     */
    @Override
    public int updateAccAccountRecord(AccAccountRecord accAccountRecord) {
        accAccountRecord.setUpdateTime(DateUtils.getNowDate());
        return accAccountRecordMapper.updateAccAccountRecord(accAccountRecord);
    }

    /**
     * 删除账户出入账记录对象
     *
     * @param ids 需要删除的数据ID
     * @return 删除账户出入账记录数量
     */
    @Override
    public int deleteAccAccountRecordByIds(String ids) {
        return accAccountRecordMapper.deleteAccAccountRecordByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除账户出入账记录信息
     *
     * @param id 账户出入账记录ID
     * @return 删除账户出入账记录数量
     */
    @Override
    public int deleteAccAccountRecordById(Long id) {
        return accAccountRecordMapper.deleteAccAccountRecordById(id);
    }
    /**
     * 验证参数
     * @param accAccountRecord
     * @param action
     */
    private void validateParam(AccAccountRecord accAccountRecord, AccRecordConst.AccountOperation action){
        Checker.check(ObjectUtils.isEmpty(accAccountRecord.getMoney()),"佣金参数缺失");
        Checker.check(ObjectUtils.isEmpty(accAccountRecord.getShopId()),"店铺参数缺失");
        switch (action){
             //佣金入账
            case COMMISSION_IN:
                break;
             //订单入账
            case ORDER_IN:
                Checker.check(ObjectUtils.isEmpty(accAccountRecord.getOrderId()),"订单参数缺失");
                break;
             //退货订单出账
            case RETURN_OUT:
                Checker.check(ObjectUtils.isEmpty(accAccountRecord.getOrderId()),"订单参数缺失");
                Checker.check(ObjectUtils.isEmpty(accAccountRecord.getApplyId()),"退货订单参数缺失");
                break;
             //退货佣金出账
            case RETURN_COMMISSION_OUT:
                Checker.check(ObjectUtils.isEmpty(accAccountRecord.getOrderId()),"订单参数缺失");
                Checker.check(ObjectUtils.isEmpty(accAccountRecord.getApplyId()),"退货订单参数缺失");
                break;
            //店铺提现出账
            case SHOP_CASH_OUT:
                break;
                //用户提现出账
            case MEMBER_CASH_OUT:
                Checker.check(ObjectUtils.isEmpty(accAccountRecord.getApplyId()),"退货订单参数缺失");
                break;
            default:
                break;
        }
    }
    /**
     * 操作:
     * 0佣金收入
     */
    @Override
    public int addAccountRecordOfCommission(AccAccountRecord accAccountRecord){
        //验证参数 佣金入账
        validateParam(accAccountRecord,AccRecordConst.AccountOperation.COMMISSION_IN);
        //操作 佣金入账
        accAccountRecord.setOperation(AccRecordConst.AccountOperation.COMMISSION_IN.getType());
        //金额类型 分佣金额
        accAccountRecord.setType(AccRecordConst.AccountMoneyType.COMMISSION.getType());
        //描述 用户商品购买,转入佣金
        accAccountRecord.setDescription(AccRecordConst.AccountDescribe.DESCRIBE_COMMITION_ADD);
        return insertAccAccountRecord(accAccountRecord);
    }
    /**
     * 操作:
     * 1订单金额入账
     */
    @Override
    public int addAccountRecordOfProduct(AccAccountRecord accAccountRecord){
        //验证参数 订单入账
        validateParam(accAccountRecord,AccRecordConst.AccountOperation.ORDER_IN);
        //操作 订单入账
        accAccountRecord.setOperation(AccRecordConst.AccountOperation.ORDER_IN.getType());
        //金额类型 营业金额
        accAccountRecord.setType(AccRecordConst.AccountMoneyType.BASE.getType());
        //描述 用户商品购买,转入营业额
        accAccountRecord.setDescription(AccRecordConst.AccountDescribe.DESCRIBE_TURNOVER_ADD);
        return insertAccAccountRecord(accAccountRecord);
    }
    /**
     * 操作:
     * 2商品退货支出
     */
    @Override
    public int subAccountRecordOfProduct(AccAccountRecord accAccountRecord){
        //验证参数 退货订单出账
        validateParam(accAccountRecord,AccRecordConst.AccountOperation.RETURN_OUT);
        //操作 退货订单出账
        accAccountRecord.setOperation(AccRecordConst.AccountOperation.RETURN_OUT.getType());
        //金额类型 营业金额
        accAccountRecord.setType(AccRecordConst.AccountMoneyType.BASE.getType());
        //描述 商户提现成功,营业额转出
        accAccountRecord.setDescription(AccRecordConst.AccountDescribe.DESCRIBE_SHOP_CASH);
        return insertAccAccountRecord(accAccountRecord);
    }
    /**
     * 操作:
     * 3退货佣金出账
     */
    @Override
    public int subAccountRecordOfCommission(AccAccountRecord accAccountRecord){
        //验证参数 退货佣金出账
        validateParam(accAccountRecord,AccRecordConst.AccountOperation.RETURN_COMMISSION_OUT);
        //操作 退货佣金出账
        accAccountRecord.setOperation(AccRecordConst.AccountOperation.RETURN_COMMISSION_OUT.getType());
        //金额类型 分佣金额
        accAccountRecord.setType(AccRecordConst.AccountMoneyType.COMMISSION.getType());
        //描述 用户商品退货,佣金扣减
        accAccountRecord.setDescription(AccRecordConst.AccountDescribe.DESCRIBE_COMMITION_RETURN);
        return insertAccAccountRecord(accAccountRecord);
    }
    /**
     * 操作:
     * 4店铺提现出账
     */
    @Override
    public int subAccountRecordOfCash(AccAccountRecord accAccountRecord){
        //验证参数 店铺提现出账
        validateParam(accAccountRecord,AccRecordConst.AccountOperation.SHOP_CASH_OUT);
        //操作 店铺提现出账
        accAccountRecord.setOperation(AccRecordConst.AccountOperation.SHOP_CASH_OUT.getType());
        //金额类型 营业金额
        accAccountRecord.setType(AccRecordConst.AccountMoneyType.BASE.getType());
        //描述 商户提现成功,营业额转出
        accAccountRecord.setDescription(AccRecordConst.AccountDescribe.DESCRIBE_SHOP_CASH);
        return insertAccAccountRecord(accAccountRecord);
    }
    /**
     * 操作:
     * 5用户提现出账
     */
    @Override
    public int subOfMemberCashApply(AccAccountRecord accAccountRecord) {
        //验证参数 店铺提现出账
        validateParam(accAccountRecord,AccRecordConst.AccountOperation.MEMBER_CASH_OUT);
        //操作 店铺提现出账
        accAccountRecord.setOperation(AccRecordConst.AccountOperation.MEMBER_CASH_OUT.getType());
        //金额类型 营业金额
        accAccountRecord.setType(AccRecordConst.AccountMoneyType.COMMISSION.getType());
        //描述 商户提现成功,营业额转出
        accAccountRecord.setDescription(AccRecordConst.AccountDescribe.DESCRIBE_MEMBER_CASH);
        return insertAccAccountRecord(accAccountRecord);
    }

    /**
     * 核算平台欠我多少钱
     * type = 1
     */
    @Override
    public BigDecimal sumCashAccountMoney(Long shopId){
        return accAccountRecordMapper.sumAccountMoney(shopId, AccRecordConst.AccountMoneyType.BASE.getType());
    }
    /**
     * 核算我欠合伙人等多少钱
     *  type=0
     */
    @Override
    public BigDecimal sumCommissionAccountMoney(Long shopId){
        return accAccountRecordMapper.sumAccountMoney(shopId,AccRecordConst.AccountMoneyType.COMMISSION.getType());
    }
    /**
     * 根据订单 id 和 操作状态 获取唯一记录
     * @param orderId
     * @param operation
     * @return
     */
    @Override
    public AccAccountRecord getRecordByOrderIdAndOperation(Long orderId,String operation) {
        return accAccountRecordMapper.getRecordByOrderIdAndOperation(orderId,operation);
    }

    /**
     * 批量插入入账记录
     * @param accList
     * @return
     */
    @Override
    public int batchInsertAcc(List<AccAccountRecord> accList){
        return accAccountRecordMapper.batchInsertAcc(accList);
    }

    /**
     * 账户记录与店铺信息关联查询
     * @param dto
     * @return
     */
    @Override
    public List<AccAccountRecordDTO> selectJoinList(AccAccountRecordDTO dto) {
        return accAccountRecordMapper.selectJoinList(dto);
    }
    /**
     *付款未发货取消订单
     * @param id
     * @return
     */
    @Override
    public int delRecordByOrderId(Long id) {
        return accAccountRecordMapper.delRecordByOrderId(id);
    }
}
