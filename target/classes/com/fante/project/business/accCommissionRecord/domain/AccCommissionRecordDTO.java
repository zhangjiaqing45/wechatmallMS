package com.fante.project.business.accCommissionRecord.domain;

/**
 * @program: Fante
 * @Date: 2020/5/10 18:00
 * @Author: Mr.Z
 * @Description:
 */
public class AccCommissionRecordDTO extends AccCommissionRecord {

    private static final long serialVersionUID = 1L;

    private String phone;
    private String nickname;
    private String headimgurl;
    private String openid;
    private String roleType;
    private Long shopId;

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

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
}
