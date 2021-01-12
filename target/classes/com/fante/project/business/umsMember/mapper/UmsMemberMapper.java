package com.fante.project.business.umsMember.mapper;

import com.fante.project.business.accCommissionRecord.domain.AccCommissionRecord;
import com.fante.project.business.smsCoupon.domain.RealNameCertification;
import com.fante.project.business.smsCoupon.domain.SelectMemberParam;
import com.fante.project.business.umsMember.domain.UmsMember;
import com.fante.project.mapperBase.UmsMemberMapperBase;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * 会员Mapper扩展接口
 *
 * @author fante
 * @date 2020-04-14
 */

public interface UmsMemberMapper extends UmsMemberMapperBase {

    /**
     * 根据openid查询用户信息
     * @param openid
     * @return
     */
    public UmsMember selectUmsMemberByOpenid(String openid);
    /**
     * 支付积分
     * @param memberId
     * @param totalPrice
     * @return
     */
    int payForIntegralProduct(@Param("memberId") Long memberId, @Param("totalPrice") Long totalPrice);
    /**
     * 添加 更新积分
     * @param memberId
     * @param totalPrice
     * @return
     */
    int addMemberIntegral(@Param("memberId")Long memberId, @Param("totalPrice")BigDecimal totalPrice);
    /**
     * 佣金转余额
     * @param memberId
     * @param money
     * @return
     */
    int waitCommissionToBalance(@Param("memberId")Long memberId, @Param("money")BigDecimal money);
    /**
     * 待入佣金转佣金
     * @param memberId
     * @param money
     * @return
     */
    int waitCommissionToCommission(@Param("memberId")Long memberId, @Param("money")BigDecimal money);
    /**
     * 获取上级id
     * 超过一个月上级id不分佣,视为无效上级id
     * @param memberId
     * @return
     */
    UmsMember selectUmsMemberParentById(@Param("memberId")Long memberId, @Param("commissionAging")Long commissionAging);
    /**
     * 提现扣减余额
     * @param memberId
     * @return
     */
    int memberBalanceToCash(@Param("memberId")Long memberId, @Param("money")BigDecimal money);
    /**
     * 拒绝返回余额
     * @param memberId
     * @return
     */
    int addMemberBalanceOfRefuseCashApply(@Param("memberId")Long memberId, @Param("money")BigDecimal money);
    /**
     * 下级购买商品 转入待入佣金
     * @param memberId
     * @param money
     * @return
     */
    int addWaitCommission(@Param("memberId")Long memberId, @Param("money")BigDecimal money);
    /**
     * 退货减免待入佣金
     * @param memberId
     * @param money
     * @return
     */
     int subWaitCommission(@Param("memberId")Long memberId, @Param("money")BigDecimal money);

    /**
     * 批量修改用户账户信息--待入佣金 转入 可提现佣金
     * @param recordList 佣金记录表
     * @return
     */
    int batchUpdateCommission(@Param("recordList") List<AccCommissionRecord> recordList);
    /**
     * 批量修改用户账户信息--待入佣金 转入 可提现佣金
     * @param memberId memberId
     * @param money 佣金
     * @return
     */
    int updateCommission(@Param("memberId")Long memberId, @Param("money")BigDecimal money);
    /**
     * 设置核销人员状态
     * @param umsMember
     * @return
     */
    int updateVerifierStatus(UmsMember umsMember);
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
    List<UmsMember> selectUmsMemberByIds(@Param("ids") Long[] ids);
    /**
     * 查询会员列表(店铺名称)
     *
     * @param umsMember 会员
     * @return 会员集合
     */
    List<UmsMember> selectUmsMemberAndShopNameList(UmsMember umsMember);
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
    int bathUpdateDepositgroup(@Param("depositgroupList") List<RealNameCertification> depositgroupList);

    /**
     * 批量更新会员存款分组标识、姓名、身份证号
     * @param depositgroupList
     * @return
     */
    int bathUpdateDepositgroupAndNameAndIDcard(@Param("depositgroupList") List<RealNameCertification> depositgroupList);

    /**
     * 批量增加会员存款分组标识
     * @param depositgroupList
     * @return
     */
    int bathInsertDepositgroup(@Param("depositgroupList") List<RealNameCertification> depositgroupList);
}
