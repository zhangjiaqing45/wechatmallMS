package com.fante.project.business.bizShopInfo.service.impl;

import com.fante.common.business.constant.BizConstants;
import com.fante.common.business.enums.AuditTypeEnum;
import com.fante.common.business.enums.UserTypeEnum;
import com.fante.common.constant.Constants;
import com.fante.common.utils.Checker;
import com.fante.common.utils.StringUtils;
import com.fante.project.business.bizShopInfo.domain.BizShopInfo;
import com.fante.project.business.bizShopInfo.domain.BizShopUserDTO;
import com.fante.project.business.bizShopInfo.mapper.BizShopUserMapper;
import com.fante.project.business.bizShopInfo.service.IBizShopInfoService;
import com.fante.project.business.bizShopInfo.service.IBizShopUserService;
import com.fante.project.system.config.service.IConfigService;
import com.fante.project.system.role.domain.Role;
import com.fante.project.system.role.service.IRoleService;
import com.fante.project.system.sms.service.SmsService;
import com.fante.project.system.sms.utils.SmsRedis;
import com.fante.project.system.user.domain.User;
import com.fante.project.system.user.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @program: Fante
 * @Date: 2020/3/12 17:15
 * @Author: Mr.Z
 * @Description: 店铺入驻
 */
@Service
public class BizShopUserServiceImpl implements IBizShopUserService {

    private static Logger log = LoggerFactory.getLogger(BizShopUserServiceImpl.class);

    @Autowired
    BizShopUserMapper bizShopUserMapper;
    @Autowired
    private IConfigService configService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private SmsService smsService;
    @Autowired
    SmsRedis smsRedis;
    @Autowired
    private IBizShopInfoService bizShopInfoService;

    /**
     * 店铺入驻<br/>
     * 1 注册用户<br/>
     * 2 生成店铺基础信息<br/>
     * 3 用户关联店铺信息<br/>
     * @param user
     * @param verifyCode
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(User user, String verifyCode) {

        // 检验验证码信息
        Checker.checkOp(smsService.verifySmsCode(user.getPhonenumber(), verifyCode), "验证码校验未通过");

        // 获取系统设置的加盟店默认注册角色
        String roleKey = configService.selectConfigByKey(BizConstants.shop.FRANCHISEE_REGISTER_ROLE);
        Checker.check(StringUtils.isBlank(roleKey), "系统设置异常");
        log.info("加盟商注册绑定的角色: {}", roleKey);
        Role role = roleService.selectByRoleKey(roleKey);
        log.info("绑定角色名称: {}", role.getRoleName());
        Checker.check(ObjectUtils.isEmpty(role), "设置异常，请联系平台管理员");

        // 创建店铺基础信息
        BizShopInfo shop = new BizShopInfo(Constants.ENABLE, AuditTypeEnum.CREATE.getType(), user.getLoginName());
        bizShopInfoService.insertBizShopInfo(shop);
        log.info("创建店铺 ID: {}", shop.getId());

        // 设置默认注册角色
        user.setRoleIds(new Long[] { role.getRoleId() });
        // 设置用户类型为店铺注册
        user.setUserType(UserTypeEnum.FRANCHISEE_REG.getType());
        // 设置用户状态为启用
        user.setStatus(Constants.ENABLE);
        // 关联用户与组织
        user.setDeptId(shop.getId());
        user.setCreateBy(user.getLoginName());
        userService.insertUser(user);
        log.info("创建用户ID: {}", user.getUserId());
        // 删除短信验证码缓存
        smsRedis.del(user.getPhonenumber());
    }

    /**
     * 店铺用户信息
     * @param bizShopUserDTO
     * @return
     */
    @Override
    public List<BizShopUserDTO> selectShopUserJoinList(BizShopUserDTO bizShopUserDTO) {
        return bizShopUserMapper.selectShopUserJoinList(bizShopUserDTO);
    }

    /**
     * 根据条件查询店铺用户信息
     * @param bizShopUserDTO
     * @return
     */
    @Override
    public BizShopUserDTO selectShopUserJoinSigle(BizShopUserDTO bizShopUserDTO) {
        return bizShopUserMapper.selectShopUserJoinSigle(bizShopUserDTO);
    }

    /**
     * 根据用户ID查询店铺用户信息
     * @param userId
     * @return
     */
    @Override
    public BizShopUserDTO selectShopUserJoinById(Long userId) {
        if (ObjectUtils.isEmpty(userId)) {
            return null;
        }
        return bizShopUserMapper.selectShopUserJoinById(userId);
    }
}
