package com.fante.project.business.accMemberCashApply.service;

import com.fante.project.business.accMemberCashApply.domain.AccMemberCashApply;
import com.fante.project.business.accMemberCashApply.domain.AccMemberCashApplyDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户提现记录Service接口
 *
 * @author fante
 * @date 2020-05-07
 */
public interface IAccMemberCashApplyService {
    /**
     * 查询用户提现记录
     *
     * @param id 用户提现记录ID
     * @return 用户提现记录
     */
    public AccMemberCashApply selectAccMemberCashApplyById(Long id);

    /**
     * 查询用户提现记录列表
     *
     * @param accMemberCashApply 用户提现记录
     * @return 用户提现记录集合
     */
    public List<AccMemberCashApply> selectAccMemberCashApplyList(AccMemberCashApply accMemberCashApply);

    /**
     * 查询用户提现记录数量
     *
     * @param accMemberCashApply 用户提现记录
     * @return 结果
     */
    public int countAccMemberCashApply(AccMemberCashApply accMemberCashApply);

    /**
     * 唯一校验
     *
     * @param accMemberCashApply 用户提现记录
     * @return 结果
     */
    public String checkAccMemberCashApplyUnique(AccMemberCashApply accMemberCashApply);

    /**
     * 新增用户提现记录
     *
     * @param accMemberCashApply 用户提现记录
     * @return 新增用户提现记录数量
     */
    public int insertAccMemberCashApply(AccMemberCashApply accMemberCashApply);

    /**
     * 修改用户提现记录
     *
     * @param accMemberCashApply 用户提现记录
     * @return 修改用户提现记录数量
     */
    public int updateAccMemberCashApply(AccMemberCashApply accMemberCashApply);

    /**
     * 批量删除用户提现记录
     *
     * @param ids 需要删除的数据ID
     * @return 删除用户提现记录数量
     */
    public int deleteAccMemberCashApplyByIds(String ids);

    /**
     * 删除用户提现记录信息
     *
     * @param id 用户提现记录ID
     * @return 删除用户提现记录数量
     */
    public int deleteAccMemberCashApplyById(Long id);
    /**
     * 拒绝用户提现
     *
     * @param id 用户提现记录ID
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public int refuseCashApply(Long id, String remark);
    /**
     * 同意用户提现
     *
     * @param id 用户提现记录ID
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public int agreeCashApply(Long id, String remark);

    /**
     * 提现记录与用户信息关联查询
     * @param dto
     * @return
     */
    List<AccMemberCashApplyDTO> selectJoinList(AccMemberCashApplyDTO dto);
}
