package com.fante.project.business.omsOrder.domain;

import com.fante.common.utils.Checker;
import com.fante.common.utils.StringUtils;
import com.fante.framework.aspectj.lang.annotation.Excel;
import com.fante.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单对象 oms_order
 * 
 * @author fante
 * @date 2020-04-01
 */
public class RemarkParam implements Serializable {
    private static final long serialVersionUID = 1L;

    private String ids;
    private String remark;
    private String flag;

    public void validate() {
        Checker.check(ObjectUtils.isEmpty(getFlag()), "缺少标志信息！");
        Checker.check(ObjectUtils.isEmpty(getIds()), "缺少订单信息！");
        Checker.check(ObjectUtils.isEmpty(getRemark()), "缺少备注信息！");
    }
    public RemarkParam() {
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
