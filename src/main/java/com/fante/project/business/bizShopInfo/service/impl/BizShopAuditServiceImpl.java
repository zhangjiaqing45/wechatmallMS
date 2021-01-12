package com.fante.project.business.bizShopInfo.service.impl;

import com.fante.common.business.constant.BizConstants;
import com.fante.common.business.enums.AuditTypeEnum;
import com.fante.common.business.enums.UserTypeEnum;
import com.fante.common.constant.Constants;
import com.fante.common.utils.Checker;
import com.fante.common.utils.StringUtils;
import com.fante.project.business.bizShopInfo.domain.BizShopInfo;
import com.fante.project.business.bizShopInfo.domain.BizShopUserDTO;
import com.fante.project.business.bizShopInfo.service.IBizShopAuditService;
import com.fante.project.business.bizShopInfo.service.IBizShopInfoService;
import com.fante.project.business.bizShopInfo.service.IBizShopUserService;
import com.fante.project.system.sms.service.SmsService;
import com.fante.project.system.user.domain.User;
import com.fante.project.system.user.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

/**
 * @program: Fante
 * @Date: 2020/3/13 13:44
 * @Author: Mr.Z
 * @Description: 店铺审核
 */
@Service
public class BizShopAuditServiceImpl implements IBizShopAuditService {

    private static Logger log = LoggerFactory.getLogger(BizShopAuditServiceImpl.class);

    @Autowired
    private IUserService userService;
    @Autowired
    private SmsService smsService;
    @Autowired
    private IBizShopInfoService bizShopInfoService;
    @Autowired
    private IBizShopUserService bizShopUserService;

    /**
     * 审核通过
     * @param shop
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void auditPass(BizShopInfo shop) {
        Checker.check(ObjectUtils.isEmpty(shop) || ObjectUtils.isEmpty(shop.getId()), "缺少店铺信息");
        // 店铺注册人信息
        BizShopUserDTO regUser = selectRegUser(shop.getCreateBy(), shop.getId());
        Checker.check(ObjectUtils.isEmpty(regUser), "未找到店铺注册用户");

        if (StringUtils.isBlank(shop.getRemark())){
            shop.setRemark("店铺注册审核通过");
        }

        shop.setAudit(AuditTypeEnum.SUCCESS.getType());
        int shopUpd = bizShopInfoService.updateBizShopInfo(shop);
        log.info("审核通过，更新店铺信息: {}", shopUpd > 0);

        User user = new User();
        user.setUserId(regUser.getUserId());
        user.setUserType(UserTypeEnum.FRANCHISEE.getType());
        int userUpd = userService.updateUserInfo(user);
        log.info("审核通过，更新注册用户信息: {}", userUpd > 0);

        log.info("向店铺注册人发送短信");
        smsService.sendSmsMsg(regUser.getPhonenumber(),
                StringUtils.format(BizConstants.shortMsg.SHOP_AUDIT_PASS_MSG, regUser.getLoginName(), shop.getCompanyName()));
    }

    /**
     * 审核拒绝
     * @param shop
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void auditRefuse(BizShopInfo shop) {
        Checker.check(ObjectUtils.isEmpty(shop) || ObjectUtils.isEmpty(shop.getId()), "缺少店铺信息");
        // 店铺注册人信息
        BizShopUserDTO regUser = selectRegUser(shop.getCreateBy(), shop.getId());
        Checker.check(ObjectUtils.isEmpty(regUser), "未找到店铺注册用户");

        shop.setAudit(AuditTypeEnum.FAIL.getType());
        int shopUpd = bizShopInfoService.updateBizShopInfo(shop);
        log.info("审核拒绝，更新店铺信息: {}", shopUpd > 0);

        log.info("向店铺注册人发送短信");
        smsService.sendSmsMsg(regUser.getPhonenumber(),
                StringUtils.format(BizConstants.shortMsg.SHOP_AUDIT_REFUSE_MSG, regUser.getLoginName(), shop.getCompanyName(), shop.getRemark()));
    }

    /**
     * 根据店铺ID及店铺创建人查询注册用户
     * @param creator
     * @param shopId
     * @return
     */
    private BizShopUserDTO selectRegUser(String creator, Long shopId) {
        BizShopUserDTO queryDTO = new BizShopUserDTO();
        queryDTO.setStatus(Constants.ENABLE);
        queryDTO.setUserType(UserTypeEnum.FRANCHISEE_REG.getType());
        queryDTO.setCreateBy(creator);
        queryDTO.setDeptId(shopId);
        return bizShopUserService.selectShopUserJoinSigle(queryDTO);
    }
}
