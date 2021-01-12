package com.fante.project.api.umsProcess.service;

import com.fante.common.business.enums.UmsMemberConst;
import com.fante.common.utils.Checker;
import com.fante.common.utils.StringUtils;
import com.fante.framework.jwt.domain.JwtToken;
import com.fante.framework.jwt.enums.JwtSource;
import com.fante.framework.jwt.utils.JwtUtils;
import com.fante.project.business.bizShopInfo.domain.BizShopInfo;
import com.fante.project.business.bizShopInfo.service.IBizShopInfoService;
import com.fante.project.business.bizShopInfo.utils.ShopRedis;
import com.fante.project.business.umsMember.domain.UmsMember;
import com.fante.project.business.umsMember.service.IUmsMemberService;
import com.fante.project.system.sms.service.SmsService;
import com.fante.project.system.sms.utils.SmsRedis;
import com.fante.project.weixin.core.utils.WechatRedis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * @program: Fante
 * @Date: 2020/4/16 15:06
 * @Author: Mr.Z
 * @Description: 会员相关处理服务
 */
@Service
public class UmsMemberProcessService {

    private static Logger log = LoggerFactory.getLogger(UmsMemberProcessService.class);

    @Autowired
    IUmsMemberService memberService;
    @Autowired
    WechatRedis wechatRedis;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    private SmsService smsService;
    @Autowired
    private SmsRedis smsRedis;
    @Autowired
    private IBizShopInfoService bizShopInfoService;
    @Autowired
    private ShopRedis shopRedis;


    /**
     * 获取会员信息
     *
     * @param openid
     * @return
     */
    public UmsMember get(String openid) {
        // 缓存中查找
        UmsMember member = wechatRedis.getUmsMember(openid);
        if (!ObjectUtils.isEmpty(member)) {
            // 缓存命中
            return member;
        }
        // 缓存未命中，从数据库中查找
        member = selectByOpenid(openid);
        // 更新缓存
        wechatRedis.setUmsMember(openid, member);
        return member;
    }

    /**
     * 生成token
     *
     * @param member
     * @return
     */
    public String token(UmsMember member) {
        JwtToken jwt = new JwtToken(member.getId(), member.getOpenid(), JwtSource.WECHAT.getType());
        String token = jwtUtils.createToken(jwt);
        log.info("微信公众号获取 TOKEN:: {}", token);
        return token;
    }

    /**
     * 根据openid查询会员
     *
     * @param openid
     * @return
     */
    public UmsMember selectByOpenid(String openid) {
        return memberService.selectUmsMemberByOpenid(openid);
    }

    /**
     * 新增会员
     *
     * @param member
     * @return
     */
    public int insert(UmsMember member) {
        return memberService.insertUmsMemberFromWechat(member);
    }

    /**
     * 更新会员
     *
     * @param member
     * @return
     */
    public int update(UmsMember member) {
        return memberService.updateUmsMemberFromWechat(member);
    }

    /**
     * 更新缓存
     * @param memberId
     */
    public void updateCache(Long memberId) {
        UmsMember member = memberService.selectUmsMemberById(memberId);
        if (ObjectUtils.isEmpty(member)) {
            return;
        }
        // 更新缓存
        wechatRedis.setUmsMember(member.getOpenid(), member);
    }

    /**
     * 分销人员注册
     * @param memberId
     * @param shopCode
     * @param type
     * @param phone
     * @param verifyCode
     */
    public void partnerRegister(Long memberId, String shopCode, String type, String phone, String verifyCode, String key) {
        // 验证参数
        Checker.check(StringUtils.isAnyBlank(shopCode, type, phone, verifyCode, key), "缺少参数");
        // 验证链接时效
        Checker.check(StringUtils.isBlank(shopRedis.getInviteEntrance(key)), "邀请链接已失效");
        // 检验验证码信息
        Checker.checkOp(smsService.verifySmsCode(phone, verifyCode), "验证码校验未通过");
        // 获取分销人员角色
        UmsMemberConst.RoleType roleType = UmsMemberConst.RoleType.get(type);
        Checker.check(ObjectUtils.isEmpty(roleType), "分销人员类型异常");
        // 获取邀请店铺
        BizShopInfo shop = bizShopInfoService.selectBizShopInfoByCode(shopCode);
        Checker.check(ObjectUtils.isEmpty(shop), "邀请店铺异常");
        // 用户信息
        UmsMember member = memberService.selectUmsMemberById(memberId);
        Checker.check(ObjectUtils.isEmpty(member), " 用户信息异常");
        // 完善分销人员信息
        member.setPhone(phone);
        member.setRoleType(roleType.getType());
        member.setShopId(shop.getId());
        // 更新用户信息
        update(member);
        // 更新缓存
        wechatRedis.setUmsMember(member.getOpenid(), member);
        // 删除短信验证码缓存
        smsRedis.del(phone);
        // 删除店铺邀请链接缓存
        shopRedis.delInviteEntrance(key);
    }

    /**
     * 分销人员信息维护
     * @param memberId
     * @param phone
     * @param verifyCode
     * @param accountType
     * @param account
     */
    public void partnerMaintain(Long memberId, String phone, String verifyCode, String accountType, String account) {
        // 验证参数
        Checker.check(StringUtils.isAnyBlank(phone, verifyCode, accountType, account), "缺少参数");
        // 检验验证码信息
        Checker.checkOp(smsService.verifySmsCode(phone, verifyCode), "验证码校验未通过");
        // 用户信息
        UmsMember member = memberService.selectUmsMemberById(memberId);
        Checker.check(ObjectUtils.isEmpty(member), " 用户信息异常");
        // 维护分销人员信息
        member.setPhone(phone);
        member.setAccountType(accountType);
        member.setAccount(account);
        // 更新用户信息
        update(member);
        // 更新缓存
        wechatRedis.setUmsMember(member.getOpenid(), member);
        // 删除短信验证码缓存
        smsRedis.del(phone);
    }

}
