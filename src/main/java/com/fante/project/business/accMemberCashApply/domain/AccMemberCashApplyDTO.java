package com.fante.project.business.accMemberCashApply.domain;

/**
 * @program: Fante
 * @Date: 2020/5/11 15:23
 * @Author: Mr.Z
 * @Description:
 */
public class AccMemberCashApplyDTO extends AccMemberCashApply {

    private static final long serialVersionUID = 1L;

    private String phone;
    private String nickname;
    private String headimgurl;
    private String openid;
    private String roleType;
    private String accountType;
    private String account;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
