package com.fante.project.business.smsHomeAdvertise.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fante.framework.aspectj.lang.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import com.fante.framework.web.domain.BaseEntity;

/**
 * 广告管理对象 sms_home_advertise
 * 
 * @author fante
 * @date 2020-04-07
 */
public class SmsHomeAdvertise extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "广告标题")
    @Excel(name = "广告标题")
    private String title;

    @ApiModelProperty(value = "广告位置")
    @Excel(name = "广告位置")
    private String position;

    @ApiModelProperty(value = "广告图片")
    @Excel(name = "广告图片")
    private String pic;

    @ApiModelProperty(value = "链接类型")
    @Excel(name = "链接类型", readConverterExp = "none=无跳转,shop=跳转店铺,promotion=跳转活动,product=跳转商品,other=外部链接")
    private String urlType;

    @ApiModelProperty(value = "类型目标链接")
    @Excel(name = "类型目标链接")
    private String urlTarget;

    @ApiModelProperty(value = "类型目标链接说明")
    @Excel(name = "类型目标链接说明")
    private String urlDesp;

    @ApiModelProperty(value = "链接地址")
    @Excel(name = "链接地址")
    private String url;

    @ApiModelProperty(value = "广告备注")
    @Excel(name = "广告备注")
    private String note;

    @ApiModelProperty(value = "排序")
    @Excel(name = "排序")
    private Integer sort;

    @ApiModelProperty(value = "状态")
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
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
    public void setPosition(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }
    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPic() {
        return pic;
    }
    public void setUrlType(String urlType) {
        this.urlType = urlType;
    }

    public String getUrlType() {
        return urlType;
    }
    public void setUrlTarget(String urlTarget) {
        this.urlTarget = urlTarget;
    }

    public String getUrlTarget() {
        return urlTarget;
    }
    public void setUrlDesp(String urlDesp) {
        this.urlDesp = urlDesp;
    }

    public String getUrlDesp() {
        return urlDesp;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
    public void setNote(String note) {
        this.note = note;
    }

    public String getNote() {
        return note;
    }
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getSort() {
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
            .append("position", getPosition())
            .append("pic", getPic())
            .append("urlType", getUrlType())
            .append("urlTarget", getUrlTarget())
            .append("urlDesp", getUrlDesp())
            .append("url", getUrl())
            .append("note", getNote())
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
