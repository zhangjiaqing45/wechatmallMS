package com.fante.project.business.cmsTopic.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fante.framework.aspectj.lang.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import com.fante.framework.web.domain.BaseEntity;

/**
 * 话题对象 cms_topic
 * 
 * @author fante
 * @date 2020-03-18
 */
public class CmsTopic extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "分类ID")
    @Excel(name = "分类ID")
    private Long categoryId;

    @ApiModelProperty(value = "标题")
    @Excel(name = "标题")
    private String title;

    @ApiModelProperty(value = "封面图片")
    @Excel(name = "封面图片")
    private String pic;

    @ApiModelProperty(value = "话题内容")
    @Excel(name = "话题内容")
    private String content;

    @ApiModelProperty(value = "阅读量")
    @Excel(name = "阅读量")
    private Long readCount;

    @ApiModelProperty(value = "收藏量")
    @Excel(name = "收藏量")
    private Long collectCount;

    @ApiModelProperty(value = "热门")
    @Excel(name = "热门")
    private String top;

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
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getCategoryId() {
        return categoryId;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPic() {
        return pic;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
    public void setReadCount(Long readCount) {
        this.readCount = readCount;
    }

    public Long getReadCount() {
        return readCount;
    }
    public void setCollectCount(Long collectCount) {
        this.collectCount = collectCount;
    }

    public Long getCollectCount() {
        return collectCount;
    }
    public void setTop(String top) {
        this.top = top;
    }

    public String getTop() {
        return top;
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
            .append("categoryId", getCategoryId())
            .append("title", getTitle())
            .append("pic", getPic())
            .append("content", getContent())
            .append("readCount", getReadCount())
            .append("collectCount", getCollectCount())
            .append("top", getTop())
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
