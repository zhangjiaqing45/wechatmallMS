package com.fante.project.business.smsGroupGameRecord.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fante.framework.aspectj.lang.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import com.fante.framework.web.domain.BaseEntity;

/**
 * 团购记录对象 sms_group_game_record
 * 
 * @author fante
 * @date 2020-04-20
 */
public class SmsGroupGameRecord extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "店铺名称")
    @Excel(name = "店铺名称")
    private String shopName;

    @ApiModelProperty(value = "店铺id")
    @Excel(name = "店铺id")
    private Long shopId;

    @ApiModelProperty(value = "商品团购活动id")
    @Excel(name = "商品团购活动id")
    private Long groupGameId;

    @ApiModelProperty(value = "目标人数")
    @Excel(name = "目标人数")
    private Integer targetMemberCount;

    @ApiModelProperty(value = "锁定参团人数")
    @Excel(name = "锁定参团人数")
    private Long lockMemberCount;

    @ApiModelProperty(value = "参团人数")
    @Excel(name = "参团人数")
    private Integer groupMemberCount;

    @ApiModelProperty(value = "状态:团购进行中,团购失败,团购成功")
    @Excel(name = "状态:团购进行中,团购失败,团购成功")
    private String status;

    @ApiModelProperty(value = "团购时商品名称")
    @Excel(name = "团购时商品名称")
    private String productName;

    @ApiModelProperty(value = "团购时商品图片")
    @Excel(name = "团购时商品图片")
    private String productPic;

    @ApiModelProperty(value = "删除标记")
    private String delFlag;

    @ApiModelProperty(value = "团购时商品货号")
    @Excel(name = "团购时商品货号")
    private String productSn;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopName() {
        return shopName;
    }
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getShopId() {
        return shopId;
    }
    public void setGroupGameId(Long groupGameId) {
        this.groupGameId = groupGameId;
    }

    public Long getGroupGameId() {
        return groupGameId;
    }
    public void setTargetMemberCount(Integer targetMemberCount) {
        this.targetMemberCount = targetMemberCount;
    }

    public Integer getTargetMemberCount() {
        return targetMemberCount;
    }
    public void setLockMemberCount(Long lockMemberCount) {
        this.lockMemberCount = lockMemberCount;
    }

    public Long getLockMemberCount() {
        return lockMemberCount;
    }
    public void setGroupMemberCount(Integer groupMemberCount) {
        this.groupMemberCount = groupMemberCount;
    }

    public Integer getGroupMemberCount() {
        return groupMemberCount;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }
    public void setProductPic(String productPic) {
        this.productPic = productPic;
    }

    public String getProductPic() {
        return productPic;
    }
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDelFlag() {
        return delFlag;
    }
    public void setProductSn(String productSn) {
        this.productSn = productSn;
    }

    public String getProductSn() {
        return productSn;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("shopName", getShopName())
            .append("shopId", getShopId())
            .append("groupGameId", getGroupGameId())
            .append("targetMemberCount", getTargetMemberCount())
            .append("lockMemberCount", getLockMemberCount())
            .append("groupMemberCount", getGroupMemberCount())
            .append("status", getStatus())
            .append("productName", getProductName())
            .append("productPic", getProductPic())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .append("productSn", getProductSn())
            .toString();
    }
}
