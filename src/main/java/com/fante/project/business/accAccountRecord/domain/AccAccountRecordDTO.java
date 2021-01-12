package com.fante.project.business.accAccountRecord.domain;

/**
 * @program: Fante
 * @Date: 2020/5/11 10:05
 * @Author: Mr.Z
 * @Description:
 */
public class AccAccountRecordDTO extends AccAccountRecord {

    private static final long serialVersionUID = 1L;

    private String code;
    private String companyName;

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
}
