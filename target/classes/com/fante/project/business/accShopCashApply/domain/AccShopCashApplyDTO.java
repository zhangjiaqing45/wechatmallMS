package com.fante.project.business.accShopCashApply.domain;

/**
 * @program: Fante
 * @Date: 2020/5/12 18:14
 * @Author: Mr.Z
 * @Description:
 */
public class AccShopCashApplyDTO extends AccShopCashApply {

    private static final long serialVersionUID = 1L;

    private String code;
    private String companyName;
    private String accountType;
    private String account;
    private String bindName;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public String getBindName() {
        return bindName;
    }

    public void setBindName(String bindName) {
        this.bindName = bindName;
    }
}
