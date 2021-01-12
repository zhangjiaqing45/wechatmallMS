package com.fante.project.business.bizMainCategory.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fante.framework.aspectj.lang.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import com.fante.framework.web.domain.BaseEntity;

/**
 * 店铺主营类目对象 biz_main_category
 * 
 * @author fante
 * @date 2020-03-10
 */
public class BizMainCategory extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "状态")
    @Excel(name = "状态", readConverterExp = "0=启用,1=禁用")
    private String status;

    @ApiModelProperty(value = "类目名称")
    @Excel(name = "类目名称")
    private String category;

    @ApiModelProperty(value = "资料数量")
    @Excel(name = "资料数量")
    private String submitNum;

    @ApiModelProperty(value = "所需资料")
    @Excel(name = "所需资料")
    private String submitInfo;

    @ApiModelProperty(value = "删除标记")
    private String delFlag;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
    public void setSubmitNum(String submitNum) {
        this.submitNum = submitNum;
    }

    public String getSubmitNum() {
        return submitNum;
    }
    public void setSubmitInfo(String submitInfo) {
        this.submitInfo = submitInfo;
    }

    public String getSubmitInfo() {
        return submitInfo;
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
            .append("status", getStatus())
            .append("category", getCategory())
            .append("submitNum", getSubmitNum())
            .append("submitInfo", getSubmitInfo())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
