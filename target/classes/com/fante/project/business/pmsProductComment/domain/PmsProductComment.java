package com.fante.project.business.pmsProductComment.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fante.framework.aspectj.lang.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import com.fante.framework.web.domain.BaseEntity;

/**
 * 商品评价对象 pms_product_comment
 * 
 * @author fante
 * @date 2020-04-24
 */
public class PmsProductComment extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "null")
    private Long id;

    @ApiModelProperty(value = "店铺id")
    @Excel(name = "店铺id")
    private Long shopId;

    @ApiModelProperty(value = "商品id")
    @Excel(name = "商品id")
    private Long productId;

    @ApiModelProperty(value = "商品规格id")
    @Excel(name = "商品规格id")
    private Long productSkuId;

    @ApiModelProperty(value = "用户id")
    @Excel(name = "用户id")
    private String memberId;

    @ApiModelProperty(value = "商品名称")
    @Excel(name = "商品名称")
    private String productName;

    @ApiModelProperty(value = "用户昵称")
    @Excel(name = "用户昵称")
    private String memberNickName;

    @ApiModelProperty(value = "商品图片")
    @Excel(name = "商品图片")
    private String productPic;

    @ApiModelProperty(value = "评论用户头像")
    @Excel(name = "评论用户头像")
    private String memberIcon;

    @ApiModelProperty(value = "购买时的商品属性")
    @Excel(name = "购买时的商品属性")
    private String productAttribute;

    @ApiModelProperty(value = "评价内容")
    @Excel(name = "评价内容")
    private String content;

    @ApiModelProperty(value = "上传图片地址，以逗号隔开")
    @Excel(name = "上传图片地址，以逗号隔开")
    private String pics;

    @ApiModelProperty(value = "5")
    @Excel(name = "5")
    private Integer star;

    @ApiModelProperty(value = "是否展示0 展示 1不展示")
    @Excel(name = "是否展示0 展示 1不展示")
    private Integer showStatus;

    @ApiModelProperty(value = "删除标记")
    private String delFlag;

    @ApiModelProperty(value = "(不用)评价数量")
    @Excel(name = "(不用)评价数量")
    private Long collectCount;

    @ApiModelProperty(value = "(不用)回复数量")
    @Excel(name = "(不用)回复数量")
    private Long replayCount;

    @ApiModelProperty(value = "(不用)阅读数量")
    @Excel(name = "(不用)阅读数量")
    private Long readCount;

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
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }
    public void setProductSkuId(Long productSkuId) {
        this.productSkuId = productSkuId;
    }

    public Long getProductSkuId() {
        return productSkuId;
    }
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberId() {
        return memberId;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }
    public void setMemberNickName(String memberNickName) {
        this.memberNickName = memberNickName;
    }

    public String getMemberNickName() {
        return memberNickName;
    }
    public void setProductPic(String productPic) {
        this.productPic = productPic;
    }

    public String getProductPic() {
        return productPic;
    }
    public void setMemberIcon(String memberIcon) {
        this.memberIcon = memberIcon;
    }

    public String getMemberIcon() {
        return memberIcon;
    }
    public void setProductAttribute(String productAttribute) {
        this.productAttribute = productAttribute;
    }

    public String getProductAttribute() {
        return productAttribute;
    }
    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
    public void setPics(String pics) {
        this.pics = pics;
    }

    public String getPics() {
        return pics;
    }
    public void setStar(Integer star) {
        this.star = star;
    }

    public Integer getStar() {
        return star;
    }
    public void setShowStatus(Integer showStatus) {
        this.showStatus = showStatus;
    }

    public Integer getShowStatus() {
        return showStatus;
    }
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }
    public void setCollectCount(Long collectCount) {
        this.collectCount = collectCount;
    }

    public Long getCollectCount() {
        return collectCount;
    }
    public void setReplayCount(Long replayCount) {
        this.replayCount = replayCount;
    }

    public Long getReplayCount() {
        return replayCount;
    }
    public void setReadCount(Long readCount) {
        this.readCount = readCount;
    }

    public Long getReadCount() {
        return readCount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("shopId", getShopId())
            .append("productId", getProductId())
            .append("productSkuId", getProductSkuId())
            .append("memberId", getMemberId())
            .append("productName", getProductName())
            .append("memberNickName", getMemberNickName())
            .append("productPic", getProductPic())
            .append("memberIcon", getMemberIcon())
            .append("productAttribute", getProductAttribute())
            .append("content", getContent())
            .append("pics", getPics())
            .append("star", getStar())
            .append("showStatus", getShowStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .append("collectCount", getCollectCount())
            .append("replayCount", getReplayCount())
            .append("readCount", getReadCount())
            .toString();
    }
}
