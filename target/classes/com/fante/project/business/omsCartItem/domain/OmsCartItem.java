package com.fante.project.business.omsCartItem.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fante.framework.aspectj.lang.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import com.fante.framework.web.domain.BaseEntity;
import java.math.BigDecimal;

/**
 * 购物车对象 oms_cart_item
 * 
 * @author fante
 * @date 2020-04-08
 */
public class OmsCartItem extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "$column.columnComment")
    private Long id;

    @ApiModelProperty(value = "购物车类型1-5")
    @Excel(name = "购物车类型1-5")
    private String cartType;

    @ApiModelProperty(value = "店铺名称")
    @Excel(name = "店铺名称")
    private String shopName;

    @ApiModelProperty(value = "商品店铺id")
    @Excel(name = "商品店铺id")
    private Long shopId;

    @ApiModelProperty(value = "商品id")
    @Excel(name = "商品id")
    private Long productId;

    @ApiModelProperty(value = "skuid")
    @Excel(name = "skuid")
    private Long productSkuId;

    @ApiModelProperty(value = "用户id")
    @Excel(name = "用户id")
    private Long memberId;

    @ApiModelProperty(value = "购买数量")
    @Excel(name = "购买数量")
    private Long quantity;

    @ApiModelProperty(value = "添加到购物车的价格")
    @Excel(name = "添加到购物车的价格")
    private BigDecimal price;

    @ApiModelProperty(value = "商品主图")
    @Excel(name = "商品主图")
    private String productPic;

    @ApiModelProperty(value = "商品名称")
    @Excel(name = "商品名称")
    private String productName;

    @ApiModelProperty(value = "品牌")
    @Excel(name = "品牌")
    private String productBrand;

    @ApiModelProperty(value = "货号")
    @Excel(name = "货号")
    private String productSn;

    @ApiModelProperty(value = "商品副标题")
    @Excel(name = "商品副标题")
    private String productSubTitle;

    @ApiModelProperty(value = "商品sku条码")
    @Excel(name = "商品sku条码")
    private String productSkuCode;

    @ApiModelProperty(value = "会员昵称")
    @Excel(name = "会员昵称")
    private String memberNickname;

    @ApiModelProperty(value = "商品的分类")
    @Excel(name = "商品的分类")
    private Long productCategoryId;

    @ApiModelProperty(value = "商品销售属性")
    @Excel(name = "商品销售属性")
    private String spData;

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
    public void setCartType(String cartType) {
        this.cartType = cartType;
    }

    public String getCartType() {
        return cartType;
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
    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getMemberId() {
        return memberId;
    }
    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Long getQuantity() {
        return quantity;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }
    public void setProductPic(String productPic) {
        this.productPic = productPic;
    }

    public String getProductPic() {
        return productPic;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }
    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getProductBrand() {
        return productBrand;
    }
    public void setProductSn(String productSn) {
        this.productSn = productSn;
    }

    public String getProductSn() {
        return productSn;
    }
    public void setProductSubTitle(String productSubTitle) {
        this.productSubTitle = productSubTitle;
    }

    public String getProductSubTitle() {
        return productSubTitle;
    }
    public void setProductSkuCode(String productSkuCode) {
        this.productSkuCode = productSkuCode;
    }

    public String getProductSkuCode() {
        return productSkuCode;
    }
    public void setMemberNickname(String memberNickname) {
        this.memberNickname = memberNickname;
    }

    public String getMemberNickname() {
        return memberNickname;
    }
    public void setProductCategoryId(Long productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public Long getProductCategoryId() {
        return productCategoryId;
    }
    public void setSpData(String spData) {
        this.spData = spData;
    }

    public String getSpData() {
        return spData;
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
            .append("cartType", getCartType())
            .append("shopName", getShopName())
            .append("shopId", getShopId())
            .append("productId", getProductId())
            .append("productSkuId", getProductSkuId())
            .append("memberId", getMemberId())
            .append("quantity", getQuantity())
            .append("price", getPrice())
            .append("productPic", getProductPic())
            .append("productName", getProductName())
            .append("productBrand", getProductBrand())
            .append("productSn", getProductSn())
            .append("productSubTitle", getProductSubTitle())
            .append("productSkuCode", getProductSkuCode())
            .append("memberNickname", getMemberNickname())
            .append("productCategoryId", getProductCategoryId())
            .append("spData", getSpData())
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
