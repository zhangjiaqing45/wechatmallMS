package com.fante.project.business.umsMember.service.impl;

import com.fante.common.business.enums.CommonUse;
import com.fante.common.business.enums.UmsMemberConst;
import com.fante.common.constant.Constants;
import com.fante.common.utils.Checker;
import com.fante.common.utils.DateUtils;
import com.fante.common.utils.StringUtils;
import com.fante.common.utils.idgen.IdGenerator;
import com.fante.common.utils.security.ShiroUtils;
import com.fante.common.utils.text.Convert;
import com.fante.framework.shiro.service.PasswordService;
import com.fante.project.api.setting.OrderStting;
import com.fante.project.business.accCommissionRecord.domain.AccCommissionRecord;
import com.fante.project.business.smsCoupon.domain.RealNameCertification;
import com.fante.project.business.smsCoupon.domain.SelectMemberParam;
import com.fante.project.business.umsMember.domain.UmsMember;
import com.fante.project.business.umsMember.mapper.UmsMemberMapper;
import com.fante.project.business.umsMember.service.IUmsMemberService;
import com.fante.project.weixin.core.utils.WechatRedis;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * 会员Service业务层处理
 *
 * @author fante
 * @date 2020-04-14
 */
@Service
public class UmsMemberServiceImpl implements IUmsMemberService {

    private static Logger log = LoggerFactory.getLogger(UmsMemberServiceImpl.class);

    @Autowired
    private UmsMemberMapper umsMemberMapper;
    @Autowired
    private PasswordService passwordService;
    @Autowired
    private OrderStting orderStting;
    @Autowired
    WechatRedis wechatRedis;

    /**
     * 查询会员
     *
     * @param id 会员ID
     * @return 会员
     */
    @Override
    public UmsMember selectUmsMemberById(Long id) {
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        return umsMemberMapper.selectUmsMemberById(id);
    }

    /**
     * 查询会员列表
     *
     * @param umsMember 会员
     * @return 会员集合
     */
    @Override
    public List<UmsMember> selectUmsMemberList(UmsMember umsMember) {
        //return umsMemberMapper.selectUmsMemberList(umsMember);
        return umsMemberMapper.selectUmsMemberAndShopNameList(umsMember);
    }

    /**
     * 查询会员数量
     *
     * @param umsMember 会员
     * @return 结果
     */
    @Override
    public int countUmsMember(UmsMember umsMember) {
        return umsMemberMapper.countUmsMember(umsMember);
    }

    /**
     * 唯一校验
     *
     * @param umsMember 会员
     * @return 结果
     */
    @Override
    public String checkUmsMemberUnique(UmsMember umsMember) {
        return umsMemberMapper.checkUmsMemberUnique(umsMember) > 0 ? Constants.NOT_UNIQUE : Constants.UNIQUE;
    }

    /**
     * 新增会员
     *
     * @param umsMember 会员
     * @return 新增会员数量
     */
    @Override
    public int insertUmsMember(UmsMember umsMember) {
        umsMember.setCreateTime(DateUtils.getNowDate());
        return umsMemberMapper.insertUmsMember(umsMember);
    }

    /**
     * 修改会员
     *
     * @param umsMember 会员
     * @return 修改会员数量
     */
    @Override
    public int updateUmsMember(UmsMember umsMember) {
        umsMember.setUpdateTime(DateUtils.getNowDate());
        return umsMemberMapper.updateUmsMember(umsMember);
    }

    /**
     * 删除会员对象
     *
     * @param ids 需要删除的数据ID
     * @return 删除会员数量
     */
    @Override
    public int deleteUmsMemberByIds(String ids) {
        return umsMemberMapper.deleteUmsMemberByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除会员信息
     *
     * @param id 会员ID
     * @return 删除会员数量
     */
    @Override
    public int deleteUmsMemberById(Long id) {
        return umsMemberMapper.deleteUmsMemberById(id);
    }

    /**
     * 系统后台新增会员
     *
     * @param umsMember
     * @return
     */
    @Override
    public int insertUmsMemberFromManage(UmsMember umsMember) {
        umsMember.setSalt(randomSalt());
        umsMember.setStatus(CommonUse.Status.ENABLE.getType());
        umsMember.setSourceType(UmsMemberConst.SourceEnum.PC.getType());
        umsMember.setPassword(passwordService.encryptPassword(umsMember.getUsername(), umsMember.getPassword(), umsMember.getSalt()));
        umsMember.setOpenid(IdGenerator.getUUID());
        umsMember.setCreateBy(ShiroUtils.getLoginName());
        return insertUmsMember(umsMember);
    }

    /**
     * 系统后台修改会员
     *
     * @param umsMember
     * @return
     */
    @Override
    public int updateUmsMemberFromManage(UmsMember umsMember) {
        if (StringUtils.isBlank(umsMember.getUpdateBy())) {
            umsMember.setUpdateBy(ShiroUtils.getLoginName());
        }
        return updateUmsMember(umsMember);
    }


    /**
     * 公众号新增
     *
     * @param umsMember
     * @return
     */
    @Override
    public int insertUmsMemberFromWechat(UmsMember umsMember) {
        umsMember.setSalt(randomSalt());
        umsMember.setStatus(CommonUse.Status.ENABLE.getType());
        umsMember.setSourceType(UmsMemberConst.SourceEnum.WECHAT.getType());
        return insertUmsMember(umsMember);
    }

    /**
     * 公众号修改
     *
     * @param umsMember
     * @return
     */
    @Override
    public int updateUmsMemberFromWechat(UmsMember umsMember) {
        return updateUmsMember(umsMember);
    }

    private String randomSalt() {
        // 一个Byte占两个字节，此处生成的3字节，字符串长度为6
        SecureRandomNumberGenerator secureRandom = new SecureRandomNumberGenerator();
        return secureRandom.nextBytes(3).toHex();
    }

    /**
     * 根据openid查询用户信息
     *
     * @param openid
     * @return
     */
    @Override
    public UmsMember selectUmsMemberByOpenid(String openid) {
        if (StringUtils.isBlank(openid)) {
            return null;
        }
        return umsMemberMapper.selectUmsMemberByOpenid(openid);
    }

    /**
     * 支付积分
     *
     * @param id
     * @param totalPrice
     * @return
     */
    @Override
    public int payForIntegralProduct(long id, Long totalPrice) {
        return umsMemberMapper.payForIntegralProduct(id, totalPrice);
    }

    /**
     * 添加 更新积分
     *
     * @param rewardTatol
     * @return
     */
    @Override
    public int addMemberIntegral(Long memberId, BigDecimal rewardTatol) {
        return umsMemberMapper.addMemberIntegral(memberId, rewardTatol);
    }

    /**
     * 佣金转余额
     *
     * @param memberId
     * @param money
     * @return
     */
    @Override
    public int waitCommissionToBalance(Long memberId, BigDecimal money) {
        return umsMemberMapper.waitCommissionToBalance(memberId, money);
    }

    /**
     * 待入佣金转佣金
     *
     * @param memberId
     * @param money
     * @return
     */
    @Override
    public int waitCommissionToCommission(Long memberId, BigDecimal money) {
        return umsMemberMapper.waitCommissionToCommission(memberId, money);
    }

    /**
     * 下级购买商品 转入待入佣金
     *
     * @param memberId
     * @param money
     * @return
     */
    @Override
    public int addWaitCommission(Long memberId, BigDecimal money) {
        return umsMemberMapper.addWaitCommission(memberId, money);
    }

    /**
     * 退货减免待入佣金
     *
     * @param memberId
     * @param money
     * @return
     */
    @Override
    public int subWaitCommission(Long memberId, BigDecimal money) {
        return umsMemberMapper.subWaitCommission(memberId, money);
    }

    /**
     * 获取上级id
     * 超过一个月上级id不分佣,视为无效上级id
     *
     * @param memberId
     * @return
     */
    @Override
    public UmsMember selectUmsMemberParentById(Long memberId) {
        Long commissionAging = orderStting.getCommissionAging();
        return umsMemberMapper.selectUmsMemberParentById(memberId, commissionAging);
    }

    /**
     * 提现扣减余额
     *
     * @param memberId
     * @return
     */
    @Override
    public int memberBalanceToCash(Long memberId, BigDecimal money) {
        return umsMemberMapper.memberBalanceToCash(memberId, money);
    }

    /**
     * 拒绝返回余额
     *
     * @param memberId
     * @return
     */
    @Override
    public int addMemberBalanceOfRefuseCashApply(Long memberId, BigDecimal money) {
        return umsMemberMapper.addMemberBalanceOfRefuseCashApply(memberId, money);
    }

    /**
     * 批量修改用户账户信息--待入佣金 转入 可提现佣金
     *
     * @param recordList 佣金记录表
     * @return
     */
    @Override
    public int batchUpdateCommission(List<AccCommissionRecord> recordList) {
        return umsMemberMapper.batchUpdateCommission(recordList);
    }

    /**
     * 批量修改用户账户信息--待入佣金 转入 可提现佣金
     *
     * @param memberId memberId
     * @param money    佣金
     * @return
     */
    @Override
    public int updateCommission(Long memberId, BigDecimal money) {
        return umsMemberMapper.updateCommission(memberId, money);
    }

    /**
     * 清除用户缓存
     * @param memberId
     */
    @Override
    public void delMemberCache(Long memberId) {
        UmsMember member = selectUmsMemberById(memberId);
        if (ObjectUtils.isEmpty(member)) {
            return;
        }
        wechatRedis.delUmsMember(member.getOpenid());
    }
    /**
     * 设置核销人员状态
     * @param umsMember
     * @return
     */
    @Override
    public int setVerifierStatus(UmsMember umsMember) {
        Checker.check(StringUtils.isEmpty(umsMember.getVerifier()),"参数 设置状态信息 缺失");
        Checker.check(ObjectUtils.isEmpty(umsMember.getShopId()),"参数 店铺信息 缺失");
        UmsMember member = selectUmsMemberById(umsMember.getId());
        Checker.check(ObjectUtils.isEmpty(member),"该用户不存在！");
        Checker.check(StringUtils.equals(member.getVerifier(),umsMember.getVerifier()),"设置状态与当前状态一致,不可重复设置.");

        UmsMember updateVerifier = new UmsMember();
        updateVerifier.setId(umsMember.getId());
        updateVerifier.setVerifier(umsMember.getVerifier());
        updateVerifier.setShopId(umsMember.getShopId());
        //修改状态
        int i = umsMemberMapper.updateVerifierStatus(updateVerifier);
        //删除缓存
        wechatRedis.delUmsMember(member.getOpenid());
        return i;
    }
    /**
     * 查询可以领取该优惠券的会员
     * @param param
     * @return
     */
    @Override
    public List<UmsMember> selectMemberByCouponId(SelectMemberParam param) {
        param.validateParam();
        return umsMemberMapper.selectMemberByCouponId(param);
    }
    /**
     * 查询会员集合通过ids
     * @param ids
     * @return
     */
    @Override
    public List<UmsMember> selectUmsMemberByIds(Long[] ids) {
        return umsMemberMapper.selectUmsMemberByIds(ids);
    }

    /**
     * 将所有会员的openid以逗号隔开显示
     * @return
     */
    public List<String> selectMemberConcat(){
        return umsMemberMapper.selectMemberConcat();
    }

    /**
     * 将所有没有身份证号的会员的openid以逗号隔开显示
     * @return
     */
    public List<String> selectMemberConcatForIdcard(){
        return umsMemberMapper.selectMemberConcatForIdcard();
    }

    /**
     * 批量更新会员存款分组标识
     * @param depositgroupList
     * @return
     */
    public int bathUpdateDepositgroup(List<RealNameCertification> depositgroupList){
        return umsMemberMapper.bathUpdateDepositgroup(depositgroupList);
    }

    /**
     * 批量更新会员存款分组标识、姓名、身份证号
     * @param depositgroupList
     * @return
     */
    public int bathUpdateDepositgroupAndNameAndIDcard(List<RealNameCertification> depositgroupList){
        return umsMemberMapper.bathUpdateDepositgroupAndNameAndIDcard(depositgroupList);
    }

    /**
     * 批量增加会员存款分组标识
     * @param depositgroupList
     * @return
     */
    public int bathInsertDepositgroup(List<RealNameCertification> depositgroupList){
        return umsMemberMapper.bathInsertDepositgroup(depositgroupList);
    }

}
