package com.fante.project.business.smsFlashPromotion.domain;

import com.fante.common.utils.Checker;
import com.fante.common.utils.StringUtils;
import com.fante.framework.aspectj.lang.annotation.Excel;
import com.fante.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.util.ObjectUtils;

/**
 * 秒杀活动对象 sms_flash_promotion
 * 
 * @author fante
 * @date 2020-03-21
 */
public class SmsFlashPromotion extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "店铺ID")
    @Excel(name = "店铺ID")
    private Long shopId;

    @ApiModelProperty(value = "活动名称")
    @Excel(name = "活动名称")
    private String title;

    @ApiModelProperty(value = "开始日期")
    @Excel(name = "开始日期")
    private String startDate;

    @ApiModelProperty(value = "结束日期")
    @Excel(name = "结束日期")
    private String endDate;

    @ApiModelProperty(value = "状态")
    @Excel(name = "状态")
    private String status;

    @ApiModelProperty(value = "删除标记")
    private String delFlag;

    public void validate() {
        Checker.check(ObjectUtils.isEmpty(getShopId()), "缺少店铺标识");
        Checker.check(StringUtils.isBlank(getTitle()), "缺少活动名称");
        Checker.check(StringUtils.isAnyBlank(getStartDate(), getEndDate()), "缺少活动有效期");
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getShopId() {
        return shopId;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartDate() {
        return startDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEndDate() {
        return endDate;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("shopId", getShopId())
            .append("title", getTitle())
            .append("startDate", getStartDate())
            .append("endDate", getEndDate())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
