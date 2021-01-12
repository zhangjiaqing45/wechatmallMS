package com.fante.project.business.bizDescription.domain;

import com.fante.framework.aspectj.lang.annotation.Excel;
import com.fante.framework.web.domain.TreeEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;

/**
 * 文档说明对象 biz_description
 * 
 * @author fante
 * @date 2020-01-17
 */
public class BizDescription extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 父级ID */
    @Excel(name = "父级ID")
    private Long parentId;

    /** 标题 */
    @Excel(name = "标题")
    private String descTitle;

    /** 说明文本 */
    @Excel(name = "说明文本")
    private String descText;

    /** 排序 */
    @Excel(name = "排序")
    private String sort;

    @Excel(name = "用户类型")
    private String userType;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 删除标记 */
    private String delFlag;

    /**
     * 子菜单
     */
    private List<BizDescription> children = new ArrayList<BizDescription>();

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setDescTitle(String descTitle)
    {
        this.descTitle = descTitle;
    }

    @Override
    public Long getParentId() {
        return parentId;
    }

    @Override
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getDescTitle()
    {
        return descTitle;
    }
    public void setDescText(String descText) 
    {
        this.descText = descText;
    }

    public String getDescText() 
    {
        return descText;
    }
    public void setSort(String sort) 
    {
        this.sort = sort;
    }

    public String getSort() 
    {
        return sort;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public List<BizDescription> getChildren() {
        return children;
    }

    public void setChildren(List<BizDescription> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("parentId", getParentId())
            .append("descTitle", getDescTitle())
            .append("descText", getDescText())
            .append("sort", getSort())
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
