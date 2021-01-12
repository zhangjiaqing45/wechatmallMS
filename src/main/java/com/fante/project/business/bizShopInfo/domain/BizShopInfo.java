package com.fante.project.business.bizShopInfo.domain;

import com.fante.common.business.constant.BizConstants;
import com.fante.framework.aspectj.lang.annotation.Excel;
import com.fante.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * 店铺信息对象 biz_shop_info
 * 
 * @author fante
 * @date 2020-03-11
 */
public class BizShopInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "店铺编号")
    @Excel(name = "店铺编号")
    private String code;

    @ApiModelProperty(value = "店铺主图")
    private String pic;

    @ApiModelProperty(value = "标签")
    private String tag;

    @ApiModelProperty(value = "推荐状态")
    private String recommend;

    @ApiModelProperty(value = "热门状态")
    private String ishot;

    @ApiModelProperty(value = "支付状态")
    private String paymentDisplay;

    @ApiModelProperty(value = "主营类目")
    @Excel(name = "主营类目")
    private Long categoryId;

    @ApiModelProperty(value = "状态")
    @Excel(name = "状态", readConverterExp = "0=启用,1=禁用")
    private String status;

    @ApiModelProperty(value = "审核状态")
    @Excel(name = "审核状态")
    private String audit;

    @ApiModelProperty(value = "公司名称")
    @Excel(name = "公司名称")
    private String companyName;

    @ApiModelProperty(value = "省")
    @Excel(name = "省")
    private String province;

    @ApiModelProperty(value = "市")
    @Excel(name = "市")
    private String city;

    @ApiModelProperty(value = "区/县")
    @Excel(name = "区/县")
    private String district;

    @ApiModelProperty(value = "详细地址")
    @Excel(name = "详细地址")
    private String address;

    @ApiModelProperty(value = "经度")
    @Excel(name = "经度")
    private String longitude;

    @ApiModelProperty(value = "纬度")
    @Excel(name = "纬度")
    private String latitude;

    @ApiModelProperty(value = "法人")
    @Excel(name = "法人")
    private String legalPerson;

    @ApiModelProperty(value = "联系电话")
    @Excel(name = "联系电话")
    private String tel;

    @ApiModelProperty(value = "客服电话")
    @Excel(name = "客服电话")
    private String serviceTel;

    @ApiModelProperty(value = "营业执照")
    @Excel(name = "营业执照")
    private String license;

    @ApiModelProperty(value = "法人身份证正面")
    @Excel(name = "法人身份证正面")
    private String sfzFront;

    @ApiModelProperty(value = "法人身份证反面")
    @Excel(name = "法人身份证反面")
    private String sfzBack;

    @ApiModelProperty(value = "主营类目需要的提交资料")
    @Excel(name = "主营类目需要的提交资料")
    private String submitInfo;

    @ApiModelProperty(value = "结算账户类型（微信、支付宝、银行卡）")
    @Excel(name = "结算账户类型", readConverterExp = "wx=微信,zfb=支付宝,card=银行卡")
    private String accountType;

    @ApiModelProperty(value = "账户绑定实名")
    @Excel(name = "账户绑定实名")
    private String bindName;

    @ApiModelProperty(value = "结算账户账号")
    @Excel(name = "结算账户账号")
    private String account;

    @ApiModelProperty(value = "机构ID")
    @Excel(name = "机构ID")
    private String orgId;

    @ApiModelProperty(value = "现金账户")
    @Excel(name = "现金账户")
    private BigDecimal cash;

    @ApiModelProperty(value = "佣金账户")
    @Excel(name = "佣金账户")
    private BigDecimal brokerage;

    @ApiModelProperty(value = "删除标记")
    private String delFlag;

    public BizShopInfo() {
    }

    public BizShopInfo(Long id) {
        this.id = id;
    }

    public BizShopInfo(String status, String audit, String createBy) {
        this.status = status;
        this.audit = audit;
        super.setCreateBy(createBy);
    }

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

    /**
     * 是否是自营店
     * @return
     */
    public boolean isSelfShop() {
        return isSelfShop(this.id);
    }

    public static boolean isSelfShop(Long id) {
        return !ObjectUtils.isEmpty(id) && Objects.equals(BizConstants.base.SELF_SHOP_ID, id);
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getCategoryId() {
        return categoryId;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
    public void setAudit(String audit) {
        this.audit = audit;
    }

    public String getAudit() {
        return audit;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
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
    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDistrict() {
        return district;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLongitude() {
        return longitude;
    }
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLegalPerson() {
        return legalPerson;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTel() {
        return tel;
    }
    public void setServiceTel(String serviceTel) {
        this.serviceTel = serviceTel;
    }

    public String getServiceTel() {
        return serviceTel;
    }
    public void setLicense(String license) {
        this.license = license;
    }

    public String getLicense() {
        return license;
    }
    public void setSfzFront(String sfzFront) {
        this.sfzFront = sfzFront;
    }

    public String getSfzFront() {
        return sfzFront;
    }
    public void setSfzBack(String sfzBack) {
        this.sfzBack = sfzBack;
    }

    public String getSfzBack() {
        return sfzBack;
    }
    public void setSubmitInfo(String submitInfo) {
        this.submitInfo = submitInfo;
    }

    public String getSubmitInfo() {
        return submitInfo;
    }
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountType() {
        return accountType;
    }
    public void setBindName(String bindName) {
        this.bindName = bindName;
    }

    public String getBindName() {
        return bindName;
    }
    public void setAccount(String account) {
        this.account = account;
    }

    public String getAccount() {
        return account;
    }

    public BigDecimal getCash() {
        return cash;
    }

    public void setCash(BigDecimal cash) {
        this.cash = cash;
    }

    public BigDecimal getBrokerage() {
        return brokerage;
    }

    public void setBrokerage(BigDecimal brokerage) {
        this.brokerage = brokerage;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public String getIshot() {
        return ishot;
    }

    public void setIshot(String ishot) {
        this.ishot = ishot;
    }

    public String getPaymentDisplay() {
        return paymentDisplay;
    }

    public void setPaymentDisplay(String paymentDisplay) {
        this.paymentDisplay = paymentDisplay;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("categoryId", getCategoryId())
            .append("status", getStatus())
            .append("audit", getAudit())
            .append("companyName", getCompanyName())
            .append("province", getProvince())
            .append("city", getCity())
            .append("district", getDistrict())
            .append("address", getAddress())
            .append("legalPerson", getLegalPerson())
            .append("tel", getTel())
            .append("serviceTel", getServiceTel())
            .append("license", getLicense())
            .append("sfzFront", getSfzFront())
            .append("sfzBack", getSfzBack())
            .append("submitInfo", getSubmitInfo())
            .append("accountType", getAccountType())
            .append("bindName", getBindName())
            .append("account", getAccount())
                .append("orgId", getOrgId())
            .append("cash", getCash())
            .append("brokerage", getBrokerage())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
