package com.fante.project.mapperBase;

import com.fante.project.business.accMemberCashApply.domain.AccMemberCashApply;
import java.util.List;

/**
 * 用户提现记录Mapper基础接口
 *
 * @author fante
 * @date 2020-05-07
 */
public interface AccMemberCashApplyMapperBase {
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
    public int checkAccMemberCashApplyUnique(AccMemberCashApply accMemberCashApply);

    /**
     * 新增用户提现记录
     *
     * @param accMemberCashApply 用户提现记录
     * @return 结果
     */
    public int insertAccMemberCashApply(AccMemberCashApply accMemberCashApply);

    /**
     * 修改用户提现记录
     *
     * @param accMemberCashApply 用户提现记录
     * @return 结果
     */
    public int updateAccMemberCashApply(AccMemberCashApply accMemberCashApply);

    /**
     * 删除用户提现记录
     *
     * @param id 用户提现记录ID
     * @return 结果
     */
    public int deleteAccMemberCashApplyById(Long id);

    /**
     * 批量删除用户提现记录
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAccMemberCashApplyByIds(String[] ids);

}
