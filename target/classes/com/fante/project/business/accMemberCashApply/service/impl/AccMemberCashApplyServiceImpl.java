package com.fante.project.business.accMemberCashApply.service.impl;

import com.fante.common.business.enums.AccCashApplyConst;
import com.fante.common.constant.Constants;
import com.fante.common.utils.Checker;
import com.fante.common.utils.DateUtils;
import com.fante.common.utils.StringUtils;
import com.fante.common.utils.text.Convert;
import com.fante.project.business.accAccountRecord.domain.AccAccountRecord;
import com.fante.project.business.accAccountRecord.service.IAccAccountRecordService;
import com.fante.project.business.accBalanceRecord.domain.AccBalanceRecord;
import com.fante.project.business.accBalanceRecord.service.IAccBalanceRecordService;
import com.fante.project.business.accCommissionRecord.service.IAccCommissionRecordService;
import com.fante.project.business.accMemberCashApply.domain.AccMemberCashApply;
import com.fante.project.business.accMemberCashApply.domain.AccMemberCashApplyDTO;
import com.fante.project.business.accMemberCashApply.mapper.AccMemberCashApplyMapper;
import com.fante.project.business.accMemberCashApply.service.IAccMemberCashApplyService;
import com.fante.project.business.bizShopInfo.service.IBizShopInfoService;
import com.fante.project.business.umsMember.service.IUmsMemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * 用户提现记录Service业务层处理
 *
 * @author fante
 * @date 2020-05-07
 */
@Service
public class AccMemberCashApplyServiceImpl implements IAccMemberCashApplyService {

    private static Logger log = LoggerFactory.getLogger(AccMemberCashApplyServiceImpl.class);
    @Autowired
    private AccMemberCashApplyMapper accMemberCashApplyMapper;
    /**
     * 会员Service接口
     */
    @Autowired
    private IUmsMemberService iUmsMemberService;
    /**
     * 用户佣金记录Service接口
     */
    @Autowired
    private IAccCommissionRecordService iAccCommissionRecordService;
    /**
     * 用户余额记录Service接口
     */
    @Autowired
    private IAccBalanceRecordService iAccBalanceRecordService;
    /**
     * 账户出入账记录Service接口
     */
    @Autowired
    private IAccAccountRecordService iAccAccountRecordService;
    /**
     * 店铺信息Service接口
     */
    @Autowired
    private IBizShopInfoService iBizShopInfoService;

    /**
     * 查询用户提现记录
     *
     * @param id 用户提现记录ID
     * @return 用户提现记录
     */
    @Override
    public AccMemberCashApply selectAccMemberCashApplyById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        return accMemberCashApplyMapper.selectAccMemberCashApplyById(id);
    }

    /**
     * 查询用户提现记录列表
     *
     * @param accMemberCashApply 用户提现记录
     * @return 用户提现记录集合
     */
    @Override
    public List<AccMemberCashApply> selectAccMemberCashApplyList(AccMemberCashApply accMemberCashApply) {
        return accMemberCashApplyMapper.selectAccMemberCashApplyList(accMemberCashApply);
    }

    /**
     * 查询用户提现记录数量
     *
     * @param accMemberCashApply 用户提现记录
     * @return 结果
     */
    @Override
    public int countAccMemberCashApply(AccMemberCashApply accMemberCashApply) {
        return accMemberCashApplyMapper.countAccMemberCashApply(accMemberCashApply);
    }

    /**
     * 唯一校验
     *
     * @param accMemberCashApply 用户提现记录
     * @return 结果
     */
    @Override
    public String checkAccMemberCashApplyUnique(AccMemberCashApply accMemberCashApply) {
        return accMemberCashApplyMapper.checkAccMemberCashApplyUnique(accMemberCashApply) > 0 ? Constants.NOT_UNIQUE : Constants.UNIQUE;
    }

    /**
     * 新增用户提现记录
     *
     * @param accMemberCashApply 用户提现记录
     * @return 新增用户提现记录数量
     */
    @Override
    public int insertAccMemberCashApply(AccMemberCashApply accMemberCashApply) {
        accMemberCashApply.setCreateTime(DateUtils.getNowDate());
        accMemberCashApply.setStatus(AccCashApplyConst.AuditType.WAIT.getType());
        return accMemberCashApplyMapper.insertAccMemberCashApply(accMemberCashApply);
    }

    /**
     * 修改用户提现记录
     *
     * @param accMemberCashApply 用户提现记录
     * @return 修改用户提现记录数量
     */
    @Override
    public int updateAccMemberCashApply(AccMemberCashApply accMemberCashApply) {
        accMemberCashApply.setUpdateTime(DateUtils.getNowDate());
        return accMemberCashApplyMapper.updateAccMemberCashApply(accMemberCashApply);
    }

    /**
     * 删除用户提现记录对象
     *
     * @param ids 需要删除的数据ID
     * @return 删除用户提现记录数量
     */
    @Override
    public int deleteAccMemberCashApplyByIds(String ids) {
        return accMemberCashApplyMapper.deleteAccMemberCashApplyByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除用户提现记录信息
     *
     * @param id 用户提现记录ID
     * @return 删除用户提现记录数量
     */
    @Override
    public int deleteAccMemberCashApplyById(Long id) {
        return accMemberCashApplyMapper.deleteAccMemberCashApplyById(id);
    }

    /**
     * 拒绝用户提现
     *
     * @param id 用户提现记录ID
     * @return
     */
    @Override
    public int refuseCashApply(Long id, String remark) {
        //修改审核状态
        AccMemberCashApply update = new AccMemberCashApply();
        update.setId(id);
        update.setRemark(remark);
        update.setStatus(AccCashApplyConst.AuditType.FAIL.getType());
        int i = updateAccMemberCashApply(update);
        //增加用户佣金
        AccMemberCashApply apply = selectAccMemberCashApplyById(id);
        i += iUmsMemberService.addMemberBalanceOfRefuseCashApply(apply.getMemberId(), apply.getMoney());
        Checker.check(i != 2, "操作失败！");
        // 清除会员缓存
        iUmsMemberService.delMemberCache(apply.getMemberId());
        return i;
    }

    /**
     * 同意用户提现
     *
     * @param id 用户提现记录ID
     * @return
     */
    @Override
    public int agreeCashApply(Long id, String remark) {
        //查询审核记录
        AccMemberCashApply apply = selectAccMemberCashApplyById(id);
        Checker.check(ObjectUtils.isEmpty(apply), "该申请不存在");
        //修改审核状态
        AccMemberCashApply update = new AccMemberCashApply();
        update.setId(id);
        update.setRemark(StringUtils.isBlank(remark) ? "审核通过" : remark);
        update.setStatus(AccCashApplyConst.AuditType.SUCCESS.getType());
        int i = updateAccMemberCashApply(update);
        //插入 用户余额记录表
        AccBalanceRecord insert = new AccBalanceRecord();
        insert.setMemberId(apply.getMemberId());
        insert.setCashApplyId(apply.getId());
        insert.setMoney(apply.getMoney().negate());
        i += iAccBalanceRecordService.insertAccBalanceRecordOfCash(insert);
        //插入 店铺账户记录
        AccAccountRecord isnertAccountRecord = new AccAccountRecord();
        isnertAccountRecord.setShopId(apply.getShopId());
        isnertAccountRecord.setMoney(apply.getMoney().negate());
        isnertAccountRecord.setApplyId(apply.getId());
        i += iAccAccountRecordService.subOfMemberCashApply(isnertAccountRecord);
        //店铺佣金表减
        i += iBizShopInfoService.subCommissionToAccount(apply.getShopId(), apply.getMoney());
        Checker.check(i != 4, "同意用户提现操作失败");
        // 清除会员缓存
        iUmsMemberService.delMemberCache(apply.getMemberId());
        return i;
    }

    /**
     * 提现记录与用户信息关联查询
     *
     * @param dto
     * @return
     */
    @Override
    public List<AccMemberCashApplyDTO> selectJoinList(AccMemberCashApplyDTO dto) {
        return accMemberCashApplyMapper.selectJoinList(dto);
    }
}
