package com.fante.project.business.cmsAppNav.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fante.framework.aspectj.lang.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import com.fante.framework.web.domain.BaseEntity;

/**
 * 前端导航管理对象 cms_app_nav
 * 
 * @author fante
 * @date 2020-04-21
 */
public class CmsAppNav extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "标题")
    @Excel(name = "标题")
    private String title;

    @ApiModelProperty(value = "图标")
    @Excel(name = "图标")
    private String icon;

    @ApiModelProperty(value = "链接类型")
    @Excel(name = "链接类型")
    private Integer linkType;

    @ApiModelProperty(value = "跳转链接")
    @Excel(name = "跳转链接")
    private String linkUrl;

    @ApiModelProperty(value = "显示范围")
    @Excel(name = "显示范围")
    private String navShow;

    @ApiModelProperty(value = "排序")
    @Excel(name = "排序")
    private Long sort;

    @ApiModelProperty(value = "状态")
    @Excel(name = "状态")
    private String status;

    @ApiModelProperty(value = "删除标记")
    private String delFlag;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }
    public void setLinkType(Integer linkType) {
        this.linkType = linkType;
    }

    public Integer getLinkType() {
        return linkType;
    }
    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getLinkUrl() {
        return linkUrl;
    }
    public void setNavShow(String navShow) {
        this.navShow = navShow;
    }

    public String getNavShow() {
        return navShow;
    }
    public void setSort(Long sort) {
        this.sort = sort;
    }

    public Long getSort() {
        return sort;
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
            .append("title", getTitle())
            .append("icon", getIcon())
            .append("linkType", getLinkType())
            .append("linkUrl", getLinkUrl())
            .append("navShow", getNavShow())
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
