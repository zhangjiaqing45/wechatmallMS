package com.fante.project.api.appView.service;

import com.fante.common.business.enums.ProductAttributeCategoryConst;
import com.fante.common.utils.Checker;
import com.fante.framework.web.domain.AjaxResult;
import com.fante.project.api.appView.domain.PmsProductDetailPageInfo;
import com.fante.project.business.pmsProduct.service.IPmsProductService;
import com.fante.project.business.pmsProductCategory.domain.PmsProductCategory;
import com.fante.project.business.pmsProductCategory.domain.PmsShopProductCategory;
import com.fante.project.business.pmsProductCategory.service.IPmsProductCategoryService;
import com.fante.project.business.pmsProductComment.domain.PmsProductComment;
import com.fante.project.business.pmsProductComment.service.IPmsProductCommentService;
import com.fante.project.business.smsCoupon.domain.SmsCoupon;
import com.fante.project.business.smsCoupon.service.ISmsCouponService;
import com.fante.project.business.umsMemberProductStar.domain.UmsMemberProductStar;
import com.fante.project.business.umsMemberProductStar.service.IUmsMemberProductStarService;
import com.fante.project.business.umsMemberShopStar.domain.UmsMemberShopStar;
import com.fante.project.business.umsMemberShopStar.service.IUmsMemberShopStarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: Fante
 * @Date: 2020/4/22 10:56
 * @Author: Mr.Z
 * @Description: 前端推荐商品相关处理服务
 */
@Service
public class CmsProductDisplayService {

    private static Logger log = LoggerFactory.getLogger(CmsProductDisplayService.class);
    /**
     * 平台商品分类
     */
    @Autowired
    private IPmsProductCategoryService iPmsProductCategoryService;
    /**
     * 商品
     */
    @Autowired
    private IPmsProductService iPmsProductService;
    /**
     * 商品评论
     */
    @Autowired
    private IPmsProductCommentService iPmsProductCommentService;
    /**
     * 优惠券
     */
    @Autowired
    private ISmsCouponService iSmsCouponService;
    /**
     * 收藏 服务接口
     */
    @Autowired
    private CmsStarService cmsStarService;
    /**
     * 商品收藏
     */
    @Autowired
    private IUmsMemberProductStarService iUmsMemberProductStarService;
    /**
     * 获取所有分类app展示
     * @return
     */
    public List<PmsProductCategory>  getCategoryForNavInPlatform(Long level) {
        return iPmsProductCategoryService.getCategoryForNavInPlatform(level);
    }
    
    /**
     * 根据分类id查询分类信息
     * @param id
     * @return
     */
    public Map<String, Object> getPmsProductCategoryById(Long id){
        PmsProductCategory pmsProductCategory = iPmsProductCategoryService.selectPmsProductCategoryById(id);
        Map<String, Object> map = new HashMap<>();
        map.put("name", pmsProductCategory.getName());
        map.put("albumPics", pmsProductCategory.getAlbumPics());
        return map;
    }

    /**
     * 获取分类app店铺展示
     * @return
     */
    public List<PmsShopProductCategory> getCategoryForNavInShop(Long id, Long level) {
        return iPmsProductCategoryService.getCategoryForNavInShop(id, level);
    }

    /**
     * 根据商品分类,商品名称获取商品
     * @return
     */
    public List<PmsProductDetailPageInfo> getProductList(Long categoryId, String name,Long shopId) {
        return iPmsProductService.getProductList(categoryId,name,shopId);
    }

    /**
     * 根据id查询商品详情
     * @param productId
     * @return
     */
    public PmsProductDetailPageInfo detail(Long productId, Long memberId) {
        PmsProductDetailPageInfo detailInfo = iPmsProductService.getDetailById(productId);
        //设置其他关注信息等
        cmsStarService.setDataIntoDetailPageInfo(detailInfo,memberId);
        return detailInfo;
    }
}
