package com.fante.project.business.umsMember.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fante.framework.aspectj.lang.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import com.fante.framework.web.domain.BaseEntity;
import java.math.BigDecimal;

/**
 * 会员对象 ums_member
 * 
 * @author fante
 * @date 2020-05-07
 */
public class UmsMember extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "上级ID")
    @Excel(name = "上级ID")
    private Long pid;

    @ApiModelProperty(value = "用户名")
    @Excel(name = "用户名")
    private String username;

    @ApiModelProperty(value = "用户密码")
    @Excel(name = "用户密码")
    private String password;

    @ApiModelProperty(value = "盐加密")
    @Excel(name = "盐加密")
    private String salt;

    @ApiModelProperty(value = "帐号启用状态:0 启用,1 禁用")
    @Excel(name = "帐号启用状态:0 启用,1 禁用")
    private String status;

    @ApiModelProperty(value = "用户来源")
    @Excel(name = "用户来源")
    private String sourceType;

    @ApiModelProperty(value = "用户手机")
    @Excel(name = "用户手机")
    private String phone;

    @ApiModelProperty(value = "昵称")
    @Excel(name = "昵称")
    private String nickname;

    @ApiModelProperty(value = "用户性别(1 是男性,2 女性,0 未知)")
    @Excel(name = "用户性别(1 是男性,2 女性,0 未知)")
    private String sex;

    @ApiModelProperty(value = "用户头像")
    @Excel(name = "用户头像")
    private String headimgurl;

    @ApiModelProperty(value = "用户所在国家")
    @Excel(name = "用户所在国家")
    private String country;

    @ApiModelProperty(value = "用户所在省份")
    @Excel(name = "用户所在省份")
    private String province;

    @ApiModelProperty(value = "用户所在城市")
    @Excel(name = "用户所在城市")
    private String city;

    @ApiModelProperty(value = "结算账户类型")
    @Excel(name = "结算账户类型")
    private String accountType;

    @ApiModelProperty(value = "结算账户")
    @Excel(name = "结算账户")
    private String account;

    @ApiModelProperty(value = "订阅公众号标识(0 未关注, 1 已关注)")
    @Excel(name = "订阅公众号标识(0 未关注, 1 已关注)")
    private String subscribe;

    @ApiModelProperty(value = "公众号openid")
    @Excel(name = "公众号openid")
    private String openid;

    @ApiModelProperty(value = "微信unionid")
    @Excel(name = "微信unionid")
    private String unionid;

    @ApiModelProperty(value = "现金余额")
    @Excel(name = "现金余额")
    private BigDecimal cash;

    @ApiModelProperty(value = "积分余额")
    @Excel(name = "积分余额")
    private BigDecimal integration;

    @ApiModelProperty(value = "历史积分")
    @Excel(name = "历史积分")
    private BigDecimal historyIntegration;

    @ApiModelProperty(value = "待入佣金")
    @Excel(name = "待入佣金")
    private BigDecimal commissionWait;

    @ApiModelProperty(value = "佣金余额")
    @Excel(name = "佣金余额")
    private BigDecimal commission;

    @ApiModelProperty(value = "历史佣金")
    @Excel(name = "历史佣金")
    private BigDecimal historyCommission;

    @ApiModelProperty(value = "核销员0不是1是")
    private String verifier;

    @ApiModelProperty(value = "删除标记")
    private String delFlag;

    @ApiModelProperty(value = "分销角色 01:普通用户 02:设计师 03:自由合伙人 04:导购")
    @Excel(name = "分销角色 01:普通用户 02:设计师 03:自由合伙人 04:导购")
    private String roleType;

    @ApiModelProperty(value = "所属店铺")
    private Long shopId;

    @ApiModelProperty(value = "所属店铺")
    @Excel(name = "所属店铺")
    private String shopName;

    @ApiModelProperty(value = "存款分组")
    @Excel(name = "存款分组")
    private String depositgroup;

    @ApiModelProperty(value = "姓名")
    @Excel(name = "姓名")
    private String surname;

    @ApiModelProperty(value = "身份证号")
    @Excel(name = "身份证号")
    private String idcard;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getVerifier() {
        return verifier;
    }

    public void setVerifier(String verifier) {
        this.verifier = verifier;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Long getPid() {
        return pid;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getSalt() {
        return salt;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getSourceType() {
        return sourceType;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }
    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }
    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvince() {
        return province;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountType() {
        return accountType;
    }
    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccount() {
        return account;
    }
    public void setSubscribe(String subscribe) {
        this.subscribe = subscribe;
    }

    public String getSubscribe() {
        return subscribe;
    }
    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getOpenid() {
        return openid;
    }
    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getUnionid() {
        return unionid;
    }
    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    public BigDecimal getCash() {
        return cash;
    }
    public void setIntegration(BigDecimal integration) {
        this.integration = integration;
    }

    public BigDecimal getIntegration() {
        return integration;
    }
    public void setHistoryIntegration(BigDecimal historyIntegration) {
        this.historyIntegration = historyIntegration;
    }

    public BigDecimal getHistoryIntegration() {
        return historyIntegration;
    }
    public void setCommissionWait(BigDecimal commissionWait) {
        this.commissionWait = commissionWait;
    }

    public BigDecimal getCommissionWait() {
        return commissionWait;
    }
    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    public BigDecimal getCommission() {
        return commission;
    }
    public void setHistoryCommission(BigDecimal historyCommission) {
        this.historyCommission = historyCommission;
    }

    public BigDecimal getHistoryCommission() {
        return historyCommission;
    }
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }
    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getRoleType() {
        return roleType;
    }
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getShopId() {
        return shopId;
    }

    public String getDepositgroup() {
        return depositgroup;
    }

    public void setDepositgroup(String depositgroup) {
        this.depositgroup = depositgroup;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("pid", getPid())
            .append("username", getUsername())
            .append("password", getPassword())
            .append("salt", getSalt())
            .append("status", getStatus())
            .append("sourceType", getSourceType())
            .append("phone", getPhone())
            .append("nickname", getNickname())
            .append("sex", getSex())
            .append("headimgurl", getHeadimgurl())
            .append("country", getCountry())
            .append("province", getProvince())
            .append("city", getCity())
            .append("accountType", getAccountType())
            .append("account", getAccount())
            .append("subscribe", getSubscribe())
            .append("openid", getOpenid())
            .append("unionid", getUnionid())
            .append("cash", getCash())
            .append("integration", getIntegration())
            .append("historyIntegration", getHistoryIntegration())
            .append("commissionWait", getCommissionWait())
            .append("commission", getCommission())
            .append("historyCommission", getHistoryCommission())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .append("roleType", getRoleType())
            .append("shopId", getShopId())
            .append("depositgroup", getDepositgroup())
            .append("surname", getSurname())
            .append("idcard", getIdcard())
            .toString();
    }
}
