package com.fante.project.business.umsMember.service;

import com.fante.project.business.accCommissionRecord.domain.AccCommissionRecord;
import com.fante.project.business.smsCoupon.domain.RealNameCertification;
import com.fante.project.business.smsCoupon.domain.SelectMemberParam;
import com.fante.project.business.umsMember.domain.UmsMember;

import java.math.BigDecimal;
import java.util.List;

/**
 * 会员Service接口
 *
 * @author fante
 * @date 2020-04-14
 */
public interface IUmsMemberService {
    /**
     * 查询会员
     *
     * @param id 会员ID
     * @return 会员
     */
    public UmsMember selectUmsMemberById(Long id);

    /**
     * 查询会员列表
     *
     * @param umsMember 会员
     * @return 会员集合
     */
    public List<UmsMember> selectUmsMemberList(UmsMember umsMember);

    /**
     * 查询会员数量
     *
     * @param umsMember 会员
     * @return 结果
     */
    public int countUmsMember(UmsMember umsMember);

    /**
     * 唯一校验
     *
     * @param umsMember 会员
     * @return 结果
     */
    public String checkUmsMemberUnique(UmsMember umsMember);

    /**
     * 新增会员
     *
     * @param umsMember 会员
     * @return 新增会员数量
     */
    public int insertUmsMember(UmsMember umsMember);

    /**
     * 修改会员
     *
     * @param umsMember 会员
     * @return 修改会员数量
     */
    public int updateUmsMember(UmsMember umsMember);

    /**
     * 批量删除会员
     *
     * @param ids 需要删除的数据ID
     * @return 删除会员数量
     */
    public int deleteUmsMemberByIds(String ids);

    /**
     * 删除会员信息
     *
     * @param id 会员ID
     * @return 删除会员数量
     */
    public int deleteUmsMemberById(Long id);

    /**
     * 系统后台新增会员
     * @param umsMember
     * @return
     */
    public int insertUmsMemberFromManage(UmsMember umsMember);

    /**
     * 系统后台修改会员
     * @param umsMember
     * @return
     */
    public int updateUmsMemberFromManage(UmsMember umsMember);

    /**
     * 公众号新增
     * @param umsMember
     * @return
     */
    public int insertUmsMemberFromWechat(UmsMember umsMember);

    /**
     * 公众号修改
     * @param umsMember
     * @return
     */
    public int updateUmsMemberFromWechat(UmsMember umsMember);

    /**
     * 根据openid查询用户信息
     * @param openid
     * @return
     */
    public UmsMember selectUmsMemberByOpenid(String openid);

    /**
     * 支付积分
     * @param id
     * @param totalPrice
     * @return
     */
    public int payForIntegralProduct(long id, Long totalPrice);

    /**
     * 添加 更新积分
     * @param memberId
     * @param rewardTatol
     * @return
     */
    int addMemberIntegral(Long memberId,BigDecimal rewardTatol);

    /**
     * 佣金转余额
     * @param memberId
     * @param money
     * @return
     */
    int waitCommissionToBalance(Long memberId, BigDecimal money);
    /**
     * 待入佣金转佣金
     * @param memberId
     * @param money
     * @return
     */
    public int waitCommissionToCommission(Long memberId, BigDecimal money);
    /**
     * 下级购买商品 转入待入佣金
     * @param memberId
     * @param money
     * @return
     */
    public int addWaitCommission(Long memberId, BigDecimal money);
    /**
     * 退货减免待入佣金
     * @param memberId
     * @param money
     * @return
     */
    public int subWaitCommission(Long memberId, BigDecimal money);
    /**
     * 获取上级id
     * 超过一个月上级id不分佣,视为无效上级id
     * @param memberId
     * @return
     */
    UmsMember selectUmsMemberParentById(Long memberId);
    /**
     * 提现-扣减余额
     * @param memberId
     * @return
     */
    int memberBalanceToCash(Long memberId,BigDecimal money);
    /**
     * 拒绝返回余额
     * 超过一个月上级id不分佣,视为无效上级id
     * @param memberId
     * @return
     */
    int addMemberBalanceOfRefuseCashApply(Long memberId,BigDecimal money);

    /**
     * 批量修改用户账户信息--待入佣金 转入 可提现佣金
     * @param recordList 佣金记录表
     * @return
     */
    int batchUpdateCommission( List<AccCommissionRecord> recordList);

    /**
     * 批量修改用户账户信息--待入佣金 转入 可提现佣金
     * @param memberId memberId
     * @param money 佣金
     * @return
     */
    public int updateCommission( Long memberId, BigDecimal money);


    /**
     * 清除用户缓存
     * @param memberId
     */
    public void delMemberCache(Long memberId);

    /**
     * 设置核销人员状态
     * @param umsMember
     * @return
     */
    int setVerifierStatus(UmsMember umsMember);

    /**
     * 查询可以领取该优惠券的会员
     * @param param
     * @return
     */
    List<UmsMember> selectMemberByCouponId(SelectMemberParam param);
    /**
     * 查询会员集合通过ids
     * @param ids
     * @return
     */
    List<UmsMember> selectUmsMemberByIds(Long[] ids);

    /**
     * 将所有会员的openid以逗号隔开显示
     * @return
     */
    List<String> selectMemberConcat();

    /**
     * 将所有没有身份证号的会员的openid以逗号隔开显示
     * @return
     */
    List<String> selectMemberConcatForIdcard();

    /**
     * 批量更新会员存款分组标识
     * @param depositgroupList
     * @return
     */
    int bathUpdateDepositgroup(List<RealNameCertification> depositgroupList);

    /**
     * 批量更新会员存款分组标识、姓名、身份证号
     * @param depositgroupList
     * @return
     */
    int bathUpdateDepositgroupAndNameAndIDcard(List<RealNameCertification> depositgroupList);

    /**
     * 批量增加会员存款分组标识
     * @param depositgroupList
     * @return
     */
    int bathInsertDepositgroup(List<RealNameCertification> depositgroupList);
}
