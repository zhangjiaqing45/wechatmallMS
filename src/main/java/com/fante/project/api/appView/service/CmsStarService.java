package com.fante.project.api.appView.service;

import com.fante.common.business.enums.ProductAttributeCategoryConst;
import com.fante.project.api.appView.domain.IDetailPageInfo;
import com.fante.project.api.appView.domain.PmsProductDetailPageInfo;
import com.fante.project.business.bizShopInfo.domain.BizShopInfo;
import com.fante.project.business.pmsProduct.domain.PmsProduct;
import com.fante.project.business.pmsProduct.service.IPmsProductService;
import com.fante.project.business.umsMemberProductStar.domain.UmsMemberProductStar;
import com.fante.project.business.umsMemberProductStar.service.IUmsMemberProductStarService;
import com.fante.project.business.umsMemberShopStar.domain.UmsMemberShopStar;
import com.fante.project.business.umsMemberShopStar.service.IUmsMemberShopStarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: Fante
 * @Date: 2020/4/22 10:54
 * @Author: wz
 * @Description: 收藏 关注接口
 */
@Service
public class CmsStarService  {
    private static Logger log = LoggerFactory.getLogger(CmsStarService.class);
    /**
     * 商品收藏
     */
    @Autowired
    private IUmsMemberProductStarService iUmsMemberProductStarService;
    /**
     * 店铺收藏
     */
    @Autowired
    private IUmsMemberShopStarService iUmsMemberShopStarService;
    /**
     * 商品
     */
    @Autowired
    private IPmsProductService iPmsProductService;
    /**
     * 获取收藏商品
     * @param memberId
     * @return
     */
    public List<PmsProductDetailPageInfo> productList(Long memberId) {
        return iUmsMemberProductStarService.getMemberStarProduct(memberId);
    }

    /**
     * 获取收藏店铺
     * @param memberId
     * @return
     */
    public List<BizShopInfo> shopList(Long memberId) {
        return iUmsMemberShopStarService.getMemberShopStar(memberId);
    }

    /**
     * 添加收藏店铺
     * @param shopId
     * @param memberId
     * @return
     */
    public int addShop(Long shopId, Long memberId) {
        UmsMemberShopStar shopStar = new UmsMemberShopStar();
        shopStar.setMemberId(memberId);
        shopStar.setShopId(shopId);
        return iUmsMemberShopStarService.insertUmsMemberShopStar(shopStar);
    }
    /**
     *添加收藏商品
     * @param productId
     * @param memberId
     * @return
     */
    public int addProduct(Long productId, Long memberId) {
        UmsMemberProductStar productStar = new UmsMemberProductStar();
        productStar.setMemberId(memberId);
        productStar.setProductId(productId);
        return iUmsMemberProductStarService.insertUmsMemberProductStar(productStar);
    }
    /**
     *取消收藏店铺
     * @param id
     * @param memberId
     * @return
     */
    public int delShop(Long id, Long memberId) {
        return iUmsMemberShopStarService.deleteUmsMemberShopStarById(id,memberId);
    }
    /**
     *取消收藏商品
     * @param id
     * @param memberId
     * @return
     */
    public int delProduct(Long id, Long memberId) {
        return  iUmsMemberProductStarService.deleteUmsMemberProductStarById(id,memberId);
    }
    /**
     *查询所有关注店铺到人
     * @param shopId
     * @return
     */
    public int countStarOfShop(Long shopId) {
        //查询所有关注店铺的人数
        UmsMemberShopStar shopStar = new UmsMemberShopStar();
        shopStar.setShopId(shopId);
        return  iUmsMemberShopStarService.countUmsMemberShopStar(shopStar);
    }
    /**
     *查询 用户是否收藏 店铺
     * @return
     */
    public boolean isStarOfShop(Long shopId,Long memberId) {
        //查询所有关注店铺的人数
        UmsMemberShopStar shopStar = new UmsMemberShopStar();
        shopStar.setShopId(shopId);
        shopStar.setMemberId(memberId);
        //查询当前用户是否关注店铺
        return iUmsMemberShopStarService.countUmsMemberShopStar(shopStar)==1;
    }
    /**
     *查询 用户是否收藏 商品
     * @return
     */
    public boolean isStarOfProduct(Long productId,Long memberId) {
        //查询当前用户是否关注商品
        UmsMemberProductStar productStar = new UmsMemberProductStar();
        productStar.setMemberId(memberId);
        productStar.setProductId(productId);
        return iUmsMemberProductStarService.countUmsMemberProductStar(productStar)==1;
    }

    /**
     * 设置:1所有关注店铺的人数
     *     2当前用户是否关注店铺
     *     3当前用户是否关注商品
     *     4店铺下的商品数量
     * @param detailInfo
     * @param memberId
     */
    public void setDataIntoDetailPageInfo(IDetailPageInfo detailInfo, Long memberId){
        Long shopId = detailInfo.getPageInfoShopId();
        //查询所有关注店铺的人数
        int shopStarCount = countStarOfShop(shopId);
        //查询当前用户是否关注店铺
        boolean shopStar = isStarOfShop(shopId, memberId);
        //查询当前用户是否关注商品
        boolean starOfProduct = isStarOfProduct(detailInfo.getPageInfoProductId(), memberId);
        //查询店铺下的商品数量
        PmsProduct product = new PmsProduct();
        product.setShopId(shopId);
        product.setPublishStatus(ProductAttributeCategoryConst.publicStatusEnum.PUTAWAY.getType());
        int shopProductCount = iPmsProductService.countPmsProduct(product);
        detailInfo.setPageInfoData(shopStarCount,shopProductCount,shopStar,starOfProduct);
    }

}
