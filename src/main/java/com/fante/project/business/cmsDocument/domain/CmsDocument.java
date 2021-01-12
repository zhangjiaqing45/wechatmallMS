package com.fante.project.business.cmsDocument.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fante.framework.aspectj.lang.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import com.fante.framework.web.domain.BaseEntity;

/**
 * 文档管理对象 cms_document
 * 
 * @author fante
 * @date 2020-04-08
 */
public class CmsDocument extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "状态")
    @Excel(name = "状态")
    private String status;

    @ApiModelProperty(value = "文档键值")
    @Excel(name = "文档键值")
    private String docKey;

    @ApiModelProperty(value = "文档标题")
    @Excel(name = "文档标题")
    private String docTitle;

    @ApiModelProperty(value = "文档内容")
    @Excel(name = "文档内容")
    private String docContent;

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
    public void setDocKey(String docKey) {
        this.docKey = docKey;
    }

    public String getDocKey() {
        return docKey;
    }
    public void setDocTitle(String docTitle) {
        this.docTitle = docTitle;
    }

    public String getDocTitle() {
        return docTitle;
    }
    public void setDocContent(String docContent) {
        this.docContent = docContent;
    }

    public String getDocContent() {
        return docContent;
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
            .append("docKey", getDocKey())
            .append("docTitle", getDocTitle())
            .append("docContent", getDocContent())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
