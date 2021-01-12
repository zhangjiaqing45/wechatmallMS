package com.fante.project.business.accBalanceRecord.service.impl;

import java.util.List;

import com.fante.common.business.enums.AccRecordConst;
import com.fante.common.utils.Checker;
import com.fante.common.utils.DateUtils;
import com.fante.common.constant.Constants;
import com.fante.project.business.accBalanceRecord.domain.AccBalanceRecordDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fante.project.business.accBalanceRecord.mapper.AccBalanceRecordMapper;
import com.fante.project.business.accBalanceRecord.domain.AccBalanceRecord;
import com.fante.project.business.accBalanceRecord.service.IAccBalanceRecordService;
import com.fante.common.utils.text.Convert;
import org.springframework.util.ObjectUtils;

/**
 * 用户余额记录Service业务层处理
 *
 * @author fante
 * @date 2020-05-07
 */
@Service
public class AccBalanceRecordServiceImpl implements IAccBalanceRecordService {

    private static Logger log = LoggerFactory.getLogger(AccBalanceRecordServiceImpl.class);

    @Autowired
    private AccBalanceRecordMapper accBalanceRecordMapper;

    /**
     * 查询用户余额记录
     *
     * @param id 用户余额记录ID
     * @return 用户余额记录
     */
    @Override
    public AccBalanceRecord selectAccBalanceRecordById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        return accBalanceRecordMapper.selectAccBalanceRecordById(id);
    }

    /**
     * 查询用户余额记录列表
     *
     * @param accBalanceRecord 用户余额记录
     * @return 用户余额记录集合
     */
    @Override
    public List<AccBalanceRecord> selectAccBalanceRecordList(AccBalanceRecord accBalanceRecord) {
        return accBalanceRecordMapper.selectAccBalanceRecordList(accBalanceRecord);
    }

    /**
     * 查询用户余额记录数量
     *
     * @param accBalanceRecord 用户余额记录
     * @return 结果
     */
    @Override
    public int countAccBalanceRecord(AccBalanceRecord accBalanceRecord){
        return accBalanceRecordMapper.countAccBalanceRecord(accBalanceRecord);
    }

    /**
     * 唯一校验
     *
     * @param accBalanceRecord 用户余额记录
     * @return 结果
     */
    @Override
    public String checkAccBalanceRecordUnique(AccBalanceRecord accBalanceRecord) {
        return accBalanceRecordMapper.checkAccBalanceRecordUnique(accBalanceRecord) > 0 ? Constants.NOT_UNIQUE : Constants.UNIQUE;
    }

    /**
     * 新增用户余额记录
     *
     * @param accBalanceRecord 用户余额记录
     * @return 新增用户余额记录数量
     */
    @Override
    public int insertAccBalanceRecord(AccBalanceRecord accBalanceRecord) {
        accBalanceRecord.setCreateTime(DateUtils.getNowDate());
        return accBalanceRecordMapper.insertAccBalanceRecord(accBalanceRecord);
    }

    /**
     * 修改用户余额记录
     *
     * @param accBalanceRecord 用户余额记录
     * @return 修改用户余额记录数量
     */
    @Override
    public int updateAccBalanceRecord(AccBalanceRecord accBalanceRecord) {
        accBalanceRecord.setUpdateTime(DateUtils.getNowDate());
        return accBalanceRecordMapper.updateAccBalanceRecord(accBalanceRecord);
    }

    /**
     * 删除用户余额记录对象
     *
     * @param ids 需要删除的数据ID
     * @return 删除用户余额记录数量
     */
    @Override
    public int deleteAccBalanceRecordByIds(String ids) {
        return accBalanceRecordMapper.deleteAccBalanceRecordByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除用户余额记录信息
     *
     * @param id 用户余额记录ID
     * @return 删除用户余额记录数量
     */
    @Override
    public int deleteAccBalanceRecordById(Long id) {
        return accBalanceRecordMapper.deleteAccBalanceRecordById(id);
    }
    /**
     * 同意提现调用
     *
     * @param  accBalanceRecord
     * @return  同意提现调用
     */
    @Override
    public int insertAccBalanceRecordOfCash(AccBalanceRecord accBalanceRecord) {
        //验证参数 提现转出
        validateParam(accBalanceRecord, AccRecordConst.BalanceOperation.CASH);
        //提现转出
        accBalanceRecord.setOperation( AccRecordConst.BalanceOperation.CASH.getType() );
        //提现申请成功,余额转出
        accBalanceRecord.setDescription( AccRecordConst.BalanceDescribe.BALANCE_TO_CASH );

        return insertAccBalanceRecord(accBalanceRecord);
    }

    /**
     * 佣金转入调用
     *
     * @param  accBalanceRecord
     * @return  佣金转入调用
     */
    @Override
    public int insertAccBalanceRecordOfCommission(AccBalanceRecord accBalanceRecord) {
        //验证参数
        validateParam(accBalanceRecord, AccRecordConst.BalanceOperation.COMMISSION);
        //提现转出
        accBalanceRecord.setOperation( AccRecordConst.BalanceOperation.COMMISSION.getType() );
        //佣金转入
        accBalanceRecord.setType( AccRecordConst.BalanceType.COMMISSION.getType() );
        //佣金转入余额
        accBalanceRecord.setDescription( AccRecordConst.BalanceDescribe.COMMISSION_TO_BALANCE );
        return insertAccBalanceRecord(accBalanceRecord);
    }

    /**
     * 验证参数
     */
    private void validateParam(AccBalanceRecord accBalanceRecord, AccRecordConst.BalanceOperation action){
        Checker.check(ObjectUtils.isEmpty( accBalanceRecord.getMemberId() ),"用户参数缺失");
        Checker.check(ObjectUtils.isEmpty( accBalanceRecord.getMoney() ),"金额参数缺失");
        switch (action){
            case CASH:
                //提现申请
                Checker.check(ObjectUtils.isEmpty( accBalanceRecord.getCashApplyId() ),"提现申请参数缺失");
                break;
            case COMMISSION:
                //余额转入
                break;
            default:
                break;
        }
    }

    /**
     * 现金明细与用户信息关联查询
     * @param dto
     * @return
     */
    @Override
    public List<AccBalanceRecordDTO> selectJoinList(AccBalanceRecordDTO dto) {
        return accBalanceRecordMapper.selectJoinList(dto);
    }
}
