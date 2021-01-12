package com.fante.project.business.bizShopInfo.domain;

import com.fante.framework.aspectj.lang.annotation.Excel;
import com.fante.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;

/**
 * @program: Fante
 * @Date: 2020/3/13 14:09
 * @Author: Mr.Z
 * @Description: 店铺用户DTO
 */
public class BizShopUserDTO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "部门ID")
    private Long deptId;

    @Excel(name = "所属店铺")
    private String deptName;

    @ApiModelProperty(value = "店铺主图")
    private String pic;
    @ApiModelProperty(value = "标签")
    private String tag;

    @ApiModelProperty(value = "推荐状态")
    private String recommend;

    @ApiModelProperty(value = "热门状态")
    private String ishot;

    @ApiModelProperty(value = "登录账号")
    @Excel(name = "登录账号")
    private String loginName;

    @ApiModelProperty(value = "用户昵称")
    @Excel(name = "用户昵称")
    private String userName;

    @ApiModelProperty(value = "用户类型（00 管理员，01 平台, 02 店铺，20 店铺注册）")
    @Excel(name = "用户类型", readConverterExp = "00=管理员,01=平台,02=店铺,20=店铺注册")
    private String userType;

    @ApiModelProperty(value = "用户邮箱")
    @Excel(name = "用户邮箱")
    private String email;

    @ApiModelProperty(value = "手机号码")
    @Excel(name = "手机号码")
    private String phonenumber;

    @ApiModelProperty(value = "用户性别（1 男 2 女）")
    @Excel(name = "用户性别", readConverterExp = "1=男,2=女")
    private String sex;

    @ApiModelProperty(value = "头像路径")
    private String avatar;

    //@ApiModelProperty(value = "密码")
    //@Excel(name = "密码")
    //private String password;
    //
    //@ApiModelProperty(value = "盐加密")
    //@Excel(name = "盐加密")
    //private String salt;

    @ApiModelProperty(value = "帐号状态（0正常 1停用）")
    @Excel(name = "帐号状态", readConverterExp = "0=正常,1=停用")
    private String status;

    //@ApiModelProperty(value = "删除标志（0代表存在 2代表删除）")
    //private String delFlag;

    //@ApiModelProperty(value = "最后登陆IP")
    //@Excel(name = "最后登陆IP")
    //private String loginIp;

    //@ApiModelProperty(value = "最后登陆时间")
    //@Excel(name = "最后登陆时间", width = 30, dateFormat = "yyyy-MM-dd")
    //private Date loginDate;

    @ApiModelProperty(value = "角色ID")
    private Long roleId;

    @Excel(name = "角色名称")
    private String roleName;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
    /**
     * 角色组
     */
    private Long[] roleIds;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Long[] getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Long[] roleIds) {
        this.roleIds = roleIds;
    }

    public String getIshot() {
        return ishot;
    }

    public void setIshot(String ishot) {
        this.ishot = ishot;
    }
}
