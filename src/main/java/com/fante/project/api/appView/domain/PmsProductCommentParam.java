package com.fante.project.api.appView.domain;

import com.fante.framework.aspectj.lang.annotation.Excel;
import com.fante.framework.web.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 商品评价对象 pms_product_comment
 * 
 * @author fante
 * @date 2020-04-24
 */
public class PmsProductCommentParam extends BaseEntity {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "订单详情id")
    private Long orderItemId;

    @ApiModelProperty(value = "评价内容")
    private String content;

    @ApiModelProperty(value = "上传图片地址，以逗号隔开")
    private String pics;

    @ApiModelProperty(value = "5")
    private Integer star;

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPics() {
        return pics;
    }

    public void setPics(String pics) {
        this.pics = pics;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }
}
